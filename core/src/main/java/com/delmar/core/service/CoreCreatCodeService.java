
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.CoreCreatCode;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-07-31 10:10:10
 */
public interface CoreCreatCodeService extends CoreService<CoreCreatCode> {
	/**
	 * @param ids
	 */
	 void deleteCoreCreatCodeList(Integer[] ids);
	 Integer getMaxValue(String code, Integer clientId);
	 void updateMaxValue(CoreCreatCode coreCreatCode);
}