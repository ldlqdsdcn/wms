package com.delmar.core.service.exception;

import com.delmar.core.excep.CoreException;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月15日 上午9:58:23 
 * 类说明 
 */
public class DataNotFoundException extends CoreException {
	
	public DataNotFoundException(String msg) {
		super(msg);
	}

	public DataNotFoundException() {
	}
}
