/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.common.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreAction;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.utils.MD5;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年1月30日 上午11:44:17
 * 用户设置，主要是修改密码，目前
 */
public class UserSettingAction extends CoreAction{
	@Autowired
	private UserService userService;
	private String oldpassword;
	private String password;
	private String confirmpassword;
	public String save()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		User loginuser=(User)request.getSession().getAttribute(WebConst.SESSION_LOGINUSER);
		MD5 md5=new MD5();
		User user=userService.getUserById(loginuser.getId());
		String md5password=md5.getMD5ofStr(oldpassword);
		if(md5password.equals(user.getPassword()))
		{
			if(StringUtil.isEmpty(password))
			{
				addActionMessage("密码不允许为空！");
				return "input";
			}
			if(StringUtil.isEmpty(confirmpassword))
			{
				addActionMessage("确认密码不允许为空！");
				return "input";
			}
			if(!password.equals(confirmpassword))
			{
				addActionMessage("密码输入不一致！");
				return "input";
			}
			
			user.setPassword(md5.getMD5ofStr(password));
			
			userService.save(user);
			request.setAttribute(WebConst.SESSION_LOGINUSER, loginuser);
			addActionMessage("密码修改成功！");
		}
		else
		{
			addActionMessage("原始密码输入错误！");
		}
		
		return "input";
	}
	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}
	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * @return the oldpassword
	 */
	public String getOldpassword() {
		return oldpassword;
	}
	/**
	 * @param oldpassword the oldpassword to set
	 */
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the confirmpassword
	 */
	public String getConfirmpassword() {
		return confirmpassword;
	}
	/**
	 * @param confirmpassword the confirmpassword to set
	 */
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	
}
