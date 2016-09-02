/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.dao;

import com.delmar.core.model.CoreCreatCode;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-07-31 10:10:09
 */
public interface CoreCreatCodeDao extends CoreDao<CoreCreatCode> {

	
	 void updateMaxValue(CoreCreatCode creatCode);
	 Integer getMaxValue(String code, Integer clientId);
}
