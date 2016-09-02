
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import com.delmar.base.model.ChargenameExtra;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-03-24 11:04:26
 */
public interface ChargenameExtraService extends CoreService<ChargenameExtra> {
	/**
	 * @param ids
	 */
	public void deleteChargenameExtraList(Integer[] ids);
}