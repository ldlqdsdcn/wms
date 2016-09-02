/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.PortModeDao;
import com.delmar.base.model.PortMode;
import com.delmar.base.service.PortModeService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-29 16:12:54
 */
@Service("portModeService")
public class PortModeServiceImpl extends CoreServiceImpl<PortMode> implements
		PortModeService {
	@Autowired
	private PortModeDao portModeDao;
	/** (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<PortMode> getCoreDao() {
		return portModeDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.PortModeService#getPortModeListByPortId(java.lang.Integer)
	 */
	public List<PortMode> getPortModeListByPortId(Integer portId) {
		return portModeDao.getPortModeListByPortId(portId);
	}

	
}
