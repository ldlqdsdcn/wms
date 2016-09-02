
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service;

import java.util.List;
import java.util.Map;

import com.delmar.core.service.CoreService;
import com.delmar.corebus.model.Transaction;

/**
 * @author 刘大磊 2014-12-26 11:48:40
 */
public interface TransactionService extends CoreService<Transaction> {
	public List<Transaction> selectByExampleWithBLOBs(Map<String,Object> param);
	public String generateDoccode(String modeKey);
}