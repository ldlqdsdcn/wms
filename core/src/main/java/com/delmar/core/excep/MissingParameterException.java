/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.excep;

/**
 * @author 刘大磊 2015年1月27日 下午4:56:18
 * 缺少参数异常
 */
public class MissingParameterException extends RuntimeException {
	/**
	 * 
	 */
	public MissingParameterException(String exception) {
		super(exception);
	}
}
