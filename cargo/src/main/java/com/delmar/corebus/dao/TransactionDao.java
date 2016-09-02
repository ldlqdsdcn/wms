/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.dao;

import java.util.List;
import java.util.Map;

import com.delmar.core.dao.CoreDao;
import com.delmar.corebus.model.Transaction;

/**
 * @author 刘大磊 2014-12-26 11:48:40
 */
public interface TransactionDao extends CoreDao<Transaction> {
	/**
	 * @param param
	 * @return
	 */
	public List<Transaction> selectByExampleWithBLOBs(Map<String,Object> param);
	
	
	/**
	 * @param prefix
	 * @return
	 */
	public String maxTranscode(String prefix);
}
