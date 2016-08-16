package com.delmar.core.service.exception;

import com.delmar.core.excep.CoreException;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月15日 上午9:56:31 
 * 类说明 
 */
public class ServiceException extends CoreException {
	
	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException() {
	}

}
