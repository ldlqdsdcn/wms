/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.ScheduledLogDao;
import com.delmar.sys.model.ScheduledLog;
import com.delmar.sys.service.ScheduledLogService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-08-24 16:48:41
 */
@Service("scheduledLogService")
public class ScheduledLogServiceImpl extends CoreServiceImpl<ScheduledLog> implements
		ScheduledLogService {
	@Autowired
	private ScheduledLogDao scheduledLogDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<ScheduledLog> getCoreDao() {
		return scheduledLogDao;
	}
	public void deleteScheduledLogList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			scheduledLogDao.deleteByPrimaryKey(id);
		}
	}
	
}
