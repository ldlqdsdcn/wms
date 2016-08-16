/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.tld;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;

import com.delmar.core.web.bean.UserResource;

/**
 * @author 刘大磊 2015年2月3日 下午2:27:01
 */
public class MessageTag  extends BodyTagSupport {
	private static final  Logger logger=Logger.getLogger(MessageTag.class);
	private String key;
	private String cn;
	private String paramValue;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		UserResource resource= (UserResource)request.getSession().getAttribute("resource");
		try {
		  String keyValue="";	
		  try
		  {
		     keyValue=resource.get(key);
		     if ("".equals(keyValue))
		     {
				if ("".equals(cn))
					keyValue=key;
				else
					keyValue=cn;
		     }
		  } catch (Exception e) {
			if ("".equals(cn))
				keyValue=key;
			else
				keyValue=cn;
		  }		  
		  
		  if (paramValue==null)
			  paramValue="";
		  
		  if (!("").equals(paramValue))
		  {
			  Object[] paramArray=paramValue.split("#");
			  keyValue=String.format(keyValue,paramArray);
		  }
		  
			pageContext.getOut().print(keyValue);
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
	public String getCn() {
		if (cn==null)
			cn="";		
		return cn;
	}
	public void setCn(String cn) {

		this.cn = cn;
	}
	public String getParamValue() {
		if (paramValue==null)
			paramValue="";		
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		
		this.paramValue = paramValue;
	}
	
	
}
