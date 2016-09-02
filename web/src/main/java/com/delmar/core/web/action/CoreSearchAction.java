/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年2月11日 上午10:26:32
 */
public abstract class CoreSearchAction extends CoreAction {
	public abstract List search();
	protected String value;
	private String jsonValue;
	/**
	 * @return the jsonValue
	 */
	public String getJsonValue() {
		return jsonValue;
	}
	/**
	 * @param jsonValue the jsonValue to set
	 */
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
	public String list()
	{
		System.out.println(ServletActionContext.getRequest().getParameter("value"));
		System.out.println(ServletActionContext.getRequest().getParameter("data"));
		Enumeration<String> enums=	ServletActionContext.getRequest().getParameterNames();
		while(enums.hasMoreElements())
		{
			String key=enums.nextElement();
			System.out.println(key+"="+ServletActionContext.getRequest().getParameter(key));
		}
		
		if(!PrivilegeOperator.isCreate())
		{
			return NOPRIVILEGE;
		}
	
		try {
			if(value!=null)
			value = URLDecoder.decode(value,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		System.out.println("value="+value);
		List list=search();
		if(list!=null&&list.size()>10)
		{
			list=list.subList(0, 10);
		}
		Gson gson=new Gson();
		jsonValue=gson.toJson(list);
		return LIST;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
