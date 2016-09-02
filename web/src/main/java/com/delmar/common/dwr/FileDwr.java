/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.dwr;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

/**
 * @author 刘大磊 2015年8月19日 下午12:54:40
 */
public class FileDwr {
	public String setFileAttribute(String fileAbstract,String fileKeyword)
	{
		WebContext webContext = WebContextFactory.get();
		HttpServletRequest request=webContext.getHttpServletRequest();
		request.getSession().setAttribute("fileAbstract", fileAbstract);
		request.getSession().setAttribute("fileKeyword", fileKeyword);
		return "Y";
	}
}
