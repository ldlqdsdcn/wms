/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import org.apache.struts2.ServletActionContext;

import com.delmar.sys.model.Module;

/**
 * @author 刘大磊 2015年1月15日 下午4:17:48
 * 
 * Struts2 权限集设定
 */
public class PrivilegeOperator {
	public static boolean isView()
	{
		return PrivilegeOperatorComon.isView(ServletActionContext.getRequest());
	}
	public static boolean isCreate()
	{
		return PrivilegeOperatorComon.isCreate(ServletActionContext.getRequest());
	}
	public static boolean isUpdate()
	{
		return PrivilegeOperatorComon.isUpdate(ServletActionContext.getRequest());
	}
	public static boolean isDelete()
	{
			return PrivilegeOperatorComon.isDelete(ServletActionContext.getRequest());
	}
	/**
	 * Struts Action 类，获取当前所在模块
	 * @return
	 */
	public static Module getModule()
	{
		return PrivilegeOperatorComon.getModule(ServletActionContext.getRequest());
	}
}
