/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.util;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

/**
 * @author 刘大磊 2015年1月14日 下午3:51:01
 */
public class FacesUtils {
	public static final String MAP_KEY_OF_SESSION = "MAP_KEY_OF_SESSION";

	public static final String MAP_KEY_OF_GLOBAL = "MAP_KEY_OF_GLOBAL";


	



	/**
	 * Add information message.
	 * 
	 * @param msg
	 *            the information message
	 */
	
	/**
	 * Add information message to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the information message
	 */
	

	/**
	 * Add error message.
	 * 
	 * @param msg
	 *            the error message
	 */
	

	/**
	 * Add error message to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id
	 * @param msg
	 *            the error message
	 */
	
	/**
	 * Add user-defined Internationalizing messages to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id----formId:clientId
	 * @param messageKey
	 *            identifier in the resource bundle
	 * 
	 * Here are some examples of usage: in web page: <h:form id="loginForm">
	 * <h:message id="msgid" for="msgid"/> </h:form> so,if you want to display
	 * the custom message,only add the follow code:
	 * FacesUtils.addInternationalMessage("loginForm:msgid","login.error"); the
	 * param "login.error" is the messageKey of resource bundle.
	 */
	

	/**
	 * Add user-defined Internationalizing messages to a sepcific client.
	 * 
	 * @param clientId
	 *            the client id----formId:clientId
	 * @param messageKey
	 *            identifier in the resource bundle
	 * @param strMsg
	 *            display ArgumentIndex value message like {0},{1}.
	 * 
	 * Here are some examples of usage: String[] message = {"msg1","msg2"};
	 * FacesUtils.addInternationalMessages("loginForm:msgid","login.error",message);
	 * in resource bundle: login.error={0}login is incorrect!{1}
	 */
	

	/**
	 * Get a FacesMessage instance
	 * 
	 * @param defaultObject
	 *            message level
	 * @return instance of facesMessage
	 */
	

	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	/**
	 * Evaluate the integer value of a JSF expression.
	 * 
	 * @param strKey
	 *            the JSF expression
	 * @return the integer value associated with the JSF expression
	 */





	// add by jason @ 2007-01-25 bgn for master data in session
	

	public static boolean setValueInHashtableOfSession(String strKey,
			Object objValue, HttpSession session) {
		if (session == null)
			return false;

		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_SESSION);
		if (StringUtils.isNotBlank(strKey) && objValue != null) {
			if (objTemp == null) {
				objTemp = new Hashtable();
			}
			objTemp.put(strKey, objValue);
			session.setAttribute(FacesUtils.MAP_KEY_OF_SESSION, objTemp);
			return true;
		}
		return false;
	}



	public static Object getValueInHashtableOfSession(String strKey,
			HttpSession session) {
		if (session == null) {
			return null;
		}
		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_SESSION);
		if (objTemp == null) {
			return null;
		} else {
			return objTemp.get(strKey);
		}
	}

	public static boolean setValueInHashtableOfSession(String strKey,
			Object objValue) {
		HttpSession session=getSession();
		if (session == null)
			return false;
		
		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_SESSION);
		if (StringUtils.isNotBlank(strKey) && objValue != null) {
			if (objTemp == null) {
				objTemp = new Hashtable();
			}
			objTemp.put(strKey, objValue);
			session.setAttribute(FacesUtils.MAP_KEY_OF_SESSION, objTemp);
			return true;
		}
		return false;
	}

	public static HttpSession getSession()
	{
		 return ServletActionContext.getRequest().getSession();
	}
	
	public static String getLocationURL(String servletPath)
	{
		return ServletActionContext.getRequest().getContextPath()+servletPath;
	}

	public static Object getValueInHashtableOfSession(String strKey) 
	{
		HttpSession session=getSession();
		if (session == null) {
			return null;
		}
		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_SESSION);
		if (objTemp == null) {
			return null;
		} else {
			return objTemp.get(strKey);
		}
	}

	/*
	 * public String removeValueInHashtableOfSession(){ WebContext ctx =
	 * WebContextFactory.get(); HttpSession session=ctx.getSession();
	 * System.out.println("�������session����"); if(session!=null) {
	 * session.removeAttribute(FacesUtils.MAP_KEY_OF_SESSION);
	 * System.out.println("�Ѿ����session����"); } else
	 * System.out.println("session����Ϊ�գ�"); return "�Ѿ������Session����"; }
	 */

	public static void clearValueInHashtableOfSession(HttpSession session) {
		session.removeAttribute(FacesUtils.MAP_KEY_OF_SESSION);
	}

	// add by jason @ 2007-01-25 end for master data in session



	

	/**
	 * use recursive way to locate a Component
	 * 
	 * @param UIComponent
	 *            base
	 * @param String
	 *            id
	 * @return UIComponent
	 */
	

	/**
	 * locate an UIComponent in view root,and the UIComponent must implements
	 * ValueHolder ValueHolder is an Interface,that may be implemented by any
	 * concrete UIComponent that wishes to support a local value, as well as
	 * access data in the model tier via a value binding expression. All Known
	 * Implementing Classes:UIInput, UIOutput.
	 * 
	 * @param String
	 *            id--Component'Id
	 * @return UIComponent's local value --String


	/**
	 * get key.value from bundle file
	 * 
	 * @param key
	 *            the key of ApplicationResources.properties
	 * @return the key's value
	 * 
	 * @author Draco Modifier Peter Shen
	 */


	/**
	 * get key.value from bundle file
	 * 
	 * @param key
	 *            the key of ApplicationResources.properties
	 * @return the key's value
	 * 
	 * @author Peter Shen
	 */
	

	/**
	 * Iterator all the parameter
	 * 
	 * @param key
	 *            the Component Id --format,formID:componentID
	 * @return the component's value
	 * @author Draco
	 */
	



	public static boolean setGlobalSessionValue(String key, Object obj,
			HttpSession session) {
		if (session == null) {
			return false;
		}
		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_GLOBAL);
		if (StringUtils.isNotBlank(key) && obj != null) {
			if (objTemp == null) {
				objTemp = new Hashtable();
			}
			objTemp.put(key, obj);
			session.setAttribute(FacesUtils.MAP_KEY_OF_GLOBAL, objTemp);
			return true;
		}
		return false;
	}



	public static Object getGlobalSessionValue(String key, HttpSession session) {
		if (session == null) {
			return null;
		}
		Hashtable objTemp = (Hashtable) session
				.getAttribute(FacesUtils.MAP_KEY_OF_GLOBAL);
		if (objTemp == null) {
			return null;
		} else {
			return objTemp.get(key);
		}
	}

	public static void clearGlobalSessionValue(HttpSession session) 
	{
		session.removeAttribute(FacesUtils.MAP_KEY_OF_GLOBAL);
	}



	public static boolean isEmpty(String value)
	{
		if(value==null) return true;
		if(value.trim().equals(""))return true;
		return false;
	}
}
