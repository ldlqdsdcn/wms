/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.model.SearchColumnList;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserSession;
import com.delmar.sys.service.UserSessionService;

/**
 * @author 刘大磊 2015年1月16日 下午5:08:23
 */
public class UserSessionAction extends CoreAction {
	private UserSessionService userSessionService;
	public void setUserSessionService(UserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}
	public String searchList()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return CoreEditPrivAction.NOPRIVILEGE;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		SearchColumnList searchColumnList=(SearchColumnList)FacesUtils.getValueInHashtableOfSession("searchColumnList");
		if(searchColumnList!=null)
		{
			map.put("accessString",searchColumnList.buildSql());
		}
		
		List<UserSession> list=userSessionService.selectByExample(null);
		FacesUtils.setValueInHashtableOfSession("userSessionList", list);
		return CoreEditAction.LIST;
	}
}
