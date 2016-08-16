package com.delmar.core.logmanager;

/**
 * @author Charles 2015年4月21日 下午5:41:20
 * @version V2.0
 */

public abstract class ProLogger {
	
   /** the system logger mothed */
	public abstract void debug(Object message);
	public abstract void debug(Object message, Throwable t);
	public abstract void error(Object message);
	public abstract void error(Object message, Throwable t);
	public abstract void fatal(Object message);
	public abstract void fatal(Object message, Throwable t);
	public abstract void info(Object message);
	public abstract void info(Object message, Throwable t);
	public abstract void warn(Object message);
	public abstract void warn(Object message, Throwable t);

    /** the app system logger mothed */

    /** other assist mothed */
	public abstract boolean isDebugEnabled();
	public abstract boolean isLogEnabled();

    /** the get a logger implements instance */
	public static ProLogger getLogger(String className) {
		return ProLoggerImpl.getLogger(className);
	}
		

}
