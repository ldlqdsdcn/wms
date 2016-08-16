/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.EventTypeModeDao;
import com.delmar.base.model.EventTypeMode;
import com.delmar.base.service.EventTypeModeService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-25 15:22:29
 */
@Service("eventTypeModeService")
public class EventTypeModeServiceImpl extends CoreServiceImpl<EventTypeMode> implements
		EventTypeModeService {
	@Autowired
	private EventTypeModeDao eventTypeModeDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<EventTypeMode> getCoreDao() {
		return eventTypeModeDao;
	}
	public void deleteEventTypeModeList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			eventTypeModeDao.deleteByPrimaryKey(id);
		}
	}
	
}
