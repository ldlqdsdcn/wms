/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.TransactionDao;
import com.delmar.corebus.model.Transaction;
import com.delmar.corebus.service.TransactionService;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22014-12-26 11:48:40
 */
@Service("transactionService")
public class TransactionServiceImpl extends CoreServiceImpl<Transaction> implements
		TransactionService {
	@Autowired
	private TransactionDao transactionDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Transaction> getCoreDao() {
		return transactionDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.EBusinessService#selectByExampleWithBLOBs(java.util.Map)
	 */
	public List<Transaction> selectByExampleWithBLOBs(Map<String, Object> param) {
		return transactionDao.selectByExampleWithBLOBs(param);
	}
	public String generateDoccode(String modeKey)
	{
		String prefix="DM1"+modeKey;
		//modeKey
		String datemonth=DateTimeDecorator.getDateMonth(new Date());
		prefix=prefix+datemonth;
		String maxvalue=transactionDao.maxTranscode(prefix);
		if ((StringUtil.isNotEmpty(maxvalue))&&(maxvalue.indexOf(prefix)>-1))
		{
			String value=maxvalue.replace(prefix, "");
			int nextvalue=Integer.parseInt(value)+1;
			 String  append= String.format("%04d", nextvalue);    
			 return prefix+append;
		}
		else
		{
			 String  append= String.format("%04d", 1);    
			 return prefix+append;
		}

	}
}
