/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.EventDao;
import com.delmar.corebus.model.Event;
import com.delmar.corebus.service.EventService;

/**
 * @author 刘大磊 22014-12-31 09:58:50
 */
@Service("eventService")
public class EventServiceImpl extends CoreServiceImpl<Event> implements
		EventService {
	@Autowired
	private EventDao eventDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Event> getCoreDao() {
		return eventDao;
	}

	
}
