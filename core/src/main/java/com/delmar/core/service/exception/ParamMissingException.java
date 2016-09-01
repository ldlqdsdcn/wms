package com.delmar.core.service.exception;

/**
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月24日 下午4:57:40 
 * 类说明   参数缺少
 */
public class ParamMissingException extends ServiceException {
	
	public ParamMissingException(String msg) {
		super(msg);
	}

	public ParamMissingException() {
	}

}
