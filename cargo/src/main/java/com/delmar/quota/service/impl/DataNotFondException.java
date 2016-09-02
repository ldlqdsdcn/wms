/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.service.impl;

/**
 * @author 刘大磊 2014年12月25日 下午5:01:30
 * 找不到数据异常
 */
public class DataNotFondException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public DataNotFondException(String msg) {
		super(msg);
	}
}
