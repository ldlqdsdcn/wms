/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.displaytag.decorator;

import com.delmar.core.web.bean.SystemContextHelper;
import com.delmar.sys.model.Role;
import com.delmar.sys.service.RoleService;
import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import javax.servlet.jsp.PageContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 2015年1月19日 上午10:51:44
 */
public class UserRoleDecorator implements DisplaytagColumnDecorator{
	private static RoleService roleService=SystemContextHelper.getBean("roleService", RoleService.class);
	public Object decorate(Object userId, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		String value="";
		if(userId==null) return value;
		Integer userId2=(Integer)userId;
		Map param=new HashMap();
		param.put("accessString", " id in(select role_id from sys_user_role where user_id="+userId2+")");
		List<Role> roles=roleService.selectByExample(param);
		for(Role role:roles)
		{
			value=value+role.getName()+"  ";
		}
		return value;
	}
}
