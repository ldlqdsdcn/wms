/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.dto.SearchColumnDto;
import com.delmar.core.model.SearchColumnList;
import com.delmar.core.service.SearchService;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserSession;
import com.delmar.sys.service.UserSessionService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 刘大磊 2015年1月16日 下午5:08:23
 */
public class UserSessionAction extends CoreAction {
	private UserSessionService userSessionService;
	@Autowired
	private SearchService searchService;
	public void setUserSessionService(UserSessionService userSessionService) {
		this.userSessionService = userSessionService;
	}
	public String getSearchWhere()
	{
		List<SearchColumnDto> searchColumnDtoList=FacesUtils.getSearchColumnList();
		if(searchColumnDtoList==null)
		{
			return null;
		}
		else
		{
			return searchService.buildSqlBySearchColumnList(searchColumnDtoList);
		}
	}
	public String searchList()
	{
		if(!PrivilegeOperator.isView())
		{
			return CoreEditPrivAction.NO_PRIVILEGE;
		}
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("searchString",getSearchWhere());

		List<UserSession> list=userSessionService.selectByExample(param);
		FacesUtils.setValueInHashtableOfSession("userSessionList", list);
		return CoreEditAction.LIST;
	}
}
