/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.rate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.rate.dao.RateDetailStrategyDao;
import com.delmar.rate.model.RateDetailStrategy;
import com.delmar.rate.service.RateDetailStrategyService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 17:09:41
 */
@Service("rateDetailStrategyService")
public class RateDetailStrategyServiceImpl extends CoreServiceImpl<RateDetailStrategy> implements
		RateDetailStrategyService {
	@Autowired
	private RateDetailStrategyDao rateDetailStrategyDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<RateDetailStrategy> getCoreDao() {
		return rateDetailStrategyDao;
	}

	
}
