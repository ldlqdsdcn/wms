/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.utils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author 刘大磊 2014年12月29日 下午5:24:50 德玛日志处理
 */
public class DmLog {
	private Log logger;
	private final static boolean usingLogger;
	static {
		File classesDir = new File(DmLog.class.getResource("/").getFile());
		
		File logFile = new File(classesDir, "log4j.properties");
		if(logFile.exists())
		{
		PropertyConfigurator.configure(logFile.getPath());
		usingLogger=true;
		}
	else
	{
		usingLogger=false;
	}
	}
	private DmLog(Class clazz) {
		if(usingLogger)
		{
			logger = new Log4jLog(clazz);
		}
		else
		{
			logger=new ConsoleLog(clazz);
		}
	
	}

	private DmLog(String tag) {
		if(usingLogger)
		{
		logger = new Log4jLog(tag);
		}
		else
		{
			logger=new ConsoleLog(tag);
		}
	}

	public static DmLog getLogger(Class clazz) {
		DmLog delmarLogger = new DmLog(clazz);
		return delmarLogger;
	}

	public static DmLog getLogger(String tag) {
		DmLog delmarLogger = new DmLog(tag);
		return delmarLogger;
	}

	public void debug(String message) {
		logger.debug(message);
	}

	public void debug(Object message) {
		logger.debug(message);
	}

	public void info(String message) {
		logger.info(message);
	}

	public void info(Object message) {
		logger.info(message);
	}

	public void warn(String message) {
		logger.warn(message);
	}

	public void warn(Object message) {
		logger.warn(message);
	}

	public void error(String message) {
		logger.error(message);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}
	/**
	 * @author 刘大磊 2015年7月21日 下午2:18:09
	 */
	private static interface Log {
		public void debug(String message);

		public void debug(Object message);

		public void info(String message);

		public void info(Object message);

		public void warn(String message);

		public void warn(Object message);

		public void error(String message);

		public void error(Object message);

		public void error(Object message, Throwable t);
	}
	
	private static  class Log4jLog implements Log {
		private Logger logger;
		
		private Log4jLog(Class clazz) {
			logger = Logger.getLogger(clazz.getName());
		}

		private Log4jLog(String tag) {
			logger = Logger.getLogger(tag);
		}
		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#debug(java.lang.String)
		 */
		public void debug(String message) {
			logger.debug(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#debug(java.lang.Object)
		 */
		public void debug(Object message) {
			logger.debug(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#info(java.lang.String)
		 */
		public void info(String message) {
			logger.info(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#info(java.lang.Object)
		 */
		public void info(Object message) {
			logger.info(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#warn(java.lang.String)
		 */
		public void warn(String message) {
			logger.warn(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#warn(java.lang.Object)
		 */
		public void warn(Object message) {
			logger.warn(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.String)
		 */
		public void error(String message) {
			logger.error(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.Object)
		 */
		public void error(Object message) {
			logger.error(message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.Object, java.lang.Throwable)
		 */
		public void error(Object message, Throwable t) {
			logger.error(message, t);
			
		}

	}
	private static class ConsoleLog implements Log {
		private String TAG;
		private ConsoleLog(Class clazz) {
			TAG=clazz.getName();
			if(TAG.length()>30)
			{
				TAG=TAG.substring(TAG.length()-30);
			}
		}

		private ConsoleLog(String tag) {
			TAG = tag;
		}
		private  void outputlog(String level,String message)
		{
			System.out.printf("%20s %-7s %-30s %s\n", DateTimeDecorator.dateToLongString(new Date()),level,TAG,message);
		}
		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#debug(java.lang.String)
		 */
		public void debug(String message) {
		
			outputlog("DEBUG",message);
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#debug(java.lang.Object)
		 */
		public void debug(Object message) {
			outputlog("DEBUG",message==null?null:message.toString());
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#info(java.lang.String)
		 */
		public void info(String message) {
			outputlog("INFO",message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#info(java.lang.Object)
		 */
		public void info(Object message) {
			outputlog("INFO",message==null?null:message.toString());
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#warn(java.lang.String)
		 */
		public void warn(String message) {
			outputlog("WARN",message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#warn(java.lang.Object)
		 */
		public void warn(Object message) {
			outputlog("WARN",message==null?null:message.toString());
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.String)
		 */
		public void error(String message) {
			outputlog("ERROR",message);
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.Object)
		 */
		public void error(Object message) {
			outputlog("ERROR",message==null?null:message.toString());
			
		}

		/* (non-Javadoc)
		 * @see com.delmar.utils.Log#error(java.lang.Object, java.lang.Throwable)
		 */
		public void error(Object message, Throwable t) {
			outputlog("ERROR",message==null?null:message.toString()+getErrorInfoFromException(t));
			
		}

	}
	  private  static String getErrorInfoFromException(Throwable e) {  
	        try {  
	            StringWriter sw = new StringWriter();  
	            PrintWriter pw = new PrintWriter(sw);  
	            e.printStackTrace(pw);  
	            return "\r\n" + sw.toString() + "\r\n";  
	        } catch (Exception ee) {  
	            return "bad getErrorInfoFromException";  
	        }  
	    }
}
