/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import com.delmar.core.model.CoreModel;
import com.delmar.core.web.util.FacesUtils;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘大磊 2015年1月15日 下午4:16:51
 */
public abstract class CoreEditPrivAction extends CoreEditBasePrivAction {
	public  abstract List<CoreModel> search();
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@SkipValidation
	public String list()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
        List<CoreModel> list=search();
        List<Integer> ids=new ArrayList<Integer>();
        for(CoreModel model:list)
        {
            ids.add(model.getId());
        }
        FacesUtils.setValueInHashtableOfSession(getModuleName()+"IdList", ids);
        FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);
		return LIST;
	}




}
