/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * @author 刘大磊 2015年2月2日 上午11:08:28
 * 国际化信息读取
 */
public class ResourceMessage {

	public static final String BUNDLE_NAME="ApplicationMessages";
	private static ResourceBundle bundle;
	
	public static String getMessage(String msgCode)
	{
		bundle=ResourceBundle.getBundle(BUNDLE_NAME);
		try
		{
			return bundle.getString(msgCode);
		} catch (Exception ex)
		{
			return "";
		}
	}
	
	public static String getMessageWithDefault(String msgCode,String defaultMessage)
	{
		String message=getMessage(msgCode);
		if (message.equals(""))
			message=defaultMessage;
		
		return message;
	}
	
	/** 
	 * @Title:        getMessage 
	 * @Description:  TODO
	 * @param:        @param msgCode
	 * @param:        @param arg0   //Format的参数
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月10日 下午3:29:18 
	 */
	public static String getMessage(String msgCode, Object arg0) {
		bundle=ResourceBundle.getBundle(BUNDLE_NAME);
		return MessageFormat.format(bundle.getString(msgCode),arg0.toString());
	}	
}
