
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
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