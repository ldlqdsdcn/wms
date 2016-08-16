/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.EBusinessEventDao;
import com.delmar.corebus.model.EBusinessEvent;
import com.delmar.corebus.service.EBusinessEventService;

/**
 * @author 刘大磊 22014-12-31 09:58:50
 */
@Service("eBusinessEventService")
public class EBusinessEventServiceImpl extends CoreServiceImpl<EBusinessEvent> implements
		EBusinessEventService {
	@Autowired
	private EBusinessEventDao eBusinessEventDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<EBusinessEvent> getCoreDao() {
		return eBusinessEventDao;
	}

	
}
