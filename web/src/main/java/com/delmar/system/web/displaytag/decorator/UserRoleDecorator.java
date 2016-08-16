/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system.web.displaytag.decorator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.model.Role;
import com.delmar.sys.service.RoleService;

/**
 * @author 刘大磊 2015年1月19日 上午10:51:44
 */
public class UserRoleDecorator implements DisplaytagColumnDecorator{
	private static RoleService roleService;
	public UserRoleDecorator() {
		if(roleService==null)
		 roleService=EaContext.ApplicationContext.getBean("roleService", RoleService.class);
	}
	
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
