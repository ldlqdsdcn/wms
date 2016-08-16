/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.ScheduledDao;
import com.delmar.sys.model.Scheduled;
import com.delmar.sys.service.ScheduledService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-08-24 16:48:41
 */
@Service("scheduledService")
public class ScheduledServiceImpl extends CoreServiceImpl<Scheduled> implements
		ScheduledService {
	@Autowired
	private ScheduledDao scheduledDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Scheduled> getCoreDao() {
		return scheduledDao;
	}
	public void deleteScheduledList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			scheduledDao.deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ScheduledService#getScheduledByClassName(java.lang.String)
	 */
	@Override
	public Scheduled getScheduledByClassName(String className) {
		// TODO Auto-generated method stub
		return scheduledDao.getScheduledByClassName(className);
	}
	
}
