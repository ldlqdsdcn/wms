/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.system.web.displaytag.decorator;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.displaytag.decorator.DisplaytagColumnDecorator;
import org.displaytag.exception.DecoratorException;
import org.displaytag.properties.MediaTypeEnum;

import com.delmar.core.web.bean.EaContext;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;

/**
 * @author 刘大磊 2015年1月19日 下午5:12:05
 */
public class UserLoginnameDecorator  implements DisplaytagColumnDecorator{
	private static UserService userService;
	public UserLoginnameDecorator() {
		if(userService==null)
			userService=EaContext.ApplicationContext.getBean("userService", UserService.class);
	}
	/* (non-Javadoc)
	 * @see org.displaytag.decorator.DisplaytagColumnDecorator#decorate(java.lang.Object, javax.servlet.jsp.PageContext, org.displaytag.properties.MediaTypeEnum)
	 */
	@Override
	public Object decorate(Object userId, PageContext arg1, MediaTypeEnum arg2)
			throws DecoratorException {
		String value="";
		if(userId==null) return value;
		Integer userId2=(Integer)userId;
		Map param=new HashMap();
		param.put("accessString", " id in(select role_id from sys_user_role where user_id="+userId2+")");
		User user=userService.selectByPrimaryKey(userId2);
		if(user!=null)
		{
			return user.getName();
		}
	return null;	
	}

}
