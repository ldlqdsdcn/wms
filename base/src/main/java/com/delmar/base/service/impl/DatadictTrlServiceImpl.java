/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.DatadictTrlDao;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.service.DatadictTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-02-06 11:07:48
 */
@Service("datadictTrlService")
public class DatadictTrlServiceImpl extends CoreServiceImpl<DatadictTrl> implements
		DatadictTrlService {
	@Autowired
	private DatadictTrlDao datadictTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<DatadictTrl> getCoreDao() {
		return datadictTrlDao;
	}
	public void deleteDatadictTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			datadictTrlDao.deleteByPrimaryKey(id);
		}
	}
	
	
	public void updateIndexOrder(Integer indexOrder,Integer datadictId) 
	{
		datadictTrlDao.updateIndexOrder(indexOrder, datadictId);
	}
}
