/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.OperatorModuleDao;
import com.delmar.sys.model.OperatorModule;
import com.delmar.sys.service.OperatorModuleService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-16 16:05:34
 */
@Service("operatorModuleService")
public class OperatorModuleServiceImpl extends CoreServiceImpl<OperatorModule> implements
		OperatorModuleService {
	@Autowired
	private OperatorModuleDao operatorModuleDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<OperatorModule> getCoreDao() {
		return operatorModuleDao;
	}

	
}
