/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.tld;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年3月9日 上午11:09:48
 */
public class WarnMsgTag extends BodyTagSupport{
	private static final  Logger logger=Logger.getLogger(WarnMsgTag.class);
	private String key;
	private String errorKey;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		UserResource resource= (UserResource)request.getSession().getAttribute("resource");
		try {
			String message=resource.get(key);
			String errorMessage=resource.get(errorKey);
			String outMsg=MessageFormat.format(errorMessage, message);
			pageContext.getOut().print(outMsg);
		} catch (Exception e) {
			logger.error("国际化输出错误"+key,e);
		}
		return super.doEndTag();
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the errorKey
	 */
	public String getErrorKey() {
		return errorKey;
	}
	/**
	 * @param errorKey the errorKey to set
	 */
	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}
	
}
