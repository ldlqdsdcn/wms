/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.StrategyDao;
import com.delmar.base.model.Strategy;
import com.delmar.base.service.StrategyService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 13:26:54
 */
@Service("strategyService")
public class StrategyServiceImpl extends CoreServiceImpl<Strategy> implements
		StrategyService {
	@Autowired
	private StrategyDao strategyDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Strategy> getCoreDao() {
		return strategyDao;
	}

	
}
