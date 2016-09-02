/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import java.math.BigDecimal;
import java.util.List;

import com.delmar.base.model.ChargenameExtra;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-03-24 11:04:26
 */
public interface ChargenameExtraDao extends CoreDao<ChargenameExtra> {
	/**
	 * 根据重量体积，获取合适的卡车类型
	 * @param grossWeight
	 * @param volume
	 * @return
	 */
	public List<ChargenameExtra> selectConformCharenameExtra(BigDecimal grossWeight,BigDecimal volume,String extraType);
}
