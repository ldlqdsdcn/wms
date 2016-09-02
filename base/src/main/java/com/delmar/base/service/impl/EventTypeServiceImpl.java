/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.EventTypeDao;
import com.delmar.base.model.EventType;
import com.delmar.base.service.EventTypeService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-31 09:57:16
 */
@Service("eventTypeService")
public class EventTypeServiceImpl extends CoreServiceImpl<EventType> implements
		EventTypeService {
	@Autowired
	private EventTypeDao eventTypeDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<EventType> getCoreDao() {
		return eventTypeDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.EventTypeService#getEventTypeListByMode(java.lang.String)
	 */
	public List<EventType> getEventTypeListByMode(String mode) {

		return eventTypeDao.getEventTypeListByMode(mode);
	}

	
}
