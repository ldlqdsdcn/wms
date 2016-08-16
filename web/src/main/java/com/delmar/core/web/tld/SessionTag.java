
package com.delmar.core.web.tld;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;


/**
 *@ClassName:   SessionTag.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月28日 下午4:13:12
 * @version V2.0
 */
public class SessionTag  extends BodyTagSupport {
	private static final  Logger logger=Logger.getLogger(SessionTag.class);
	public static final String MAP_KEY_OF_SESSION = "MAP_KEY_OF_SESSION";	
	private String key;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		HttpSession session=request.getSession();
		Hashtable objTemp = (Hashtable) session
				.getAttribute(MAP_KEY_OF_SESSION);
	  try
	  {
		String keyValue="";
		if (objTemp == null) {
			keyValue="null";
		} else {
		  keyValue=(String)objTemp.get(key);
		}
		
		pageContext.getOut().print(keyValue);
	  }
		catch (Exception e) {
			logger.error("翻译错误"+key,e);
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

	
}
