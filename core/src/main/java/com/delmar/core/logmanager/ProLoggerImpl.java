package com.delmar.core.logmanager;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

public class ProLoggerImpl extends ProLogger {
	

	/** the core log object */
	private Category cat;

	/** debug flag */
	private boolean logDebug = true;

	/** which class need log */
	private String className = "";

	public void SetDebug(boolean debugFlag) {
		logDebug = debugFlag;
	}

	private ProLoggerImpl(Category cat) {
		this.cat = cat;
	}

	private ProLoggerImpl(Category cat, String className) {
		this.className = className;
		this.cat = cat;
	}

	public void fatal(Object message) {
		if (logDebug)
			cat.fatal(message);
	}

	public void fatal(Object message, Throwable t) {
		if (logDebug)
			cat.fatal(message, t);
	}

	public void error(Object message) {
		if (logDebug)
			cat.error(message);
	}

	public void error(Object message, Throwable t) {
		if (logDebug)
			cat.error(message, t);
	}

	public void warn(Object message) {
		if (logDebug)
			cat.warn(message);
	}

	public void warn(Object message, Throwable t) {
		if (logDebug)
			cat.warn(message, t);
	}

	public void info(Object message) {
		if (logDebug)
			cat.info(message);
	}

	public void info(Object message, Throwable t) {
		if (logDebug)
			cat.info(message, t);
	}

	public void debug(Object message) {
		if (logDebug)
			cat.debug(message);
	}

	public void debug(Object message, Throwable t) {
		if (logDebug)
			cat.debug(message, t);
	}

	public boolean isDebugEnabled() {
		return cat.isDebugEnabled();
	}

	public boolean isLogEnabled() {
		return cat.isEnabledFor(Level.FATAL);
	}

	public static ProLogger getLogger(String className) {
		return new ProLoggerImpl(LogManager.getLogger(className), className);
	}
	

}
