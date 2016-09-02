/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.DatadictTypeDao;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictTypeService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-21 10:29:53
 */
@Service("datadictTypeService")
public class DatadictTypeServiceImpl extends CoreServiceImpl<DatadictType> implements
		DatadictTypeService {
	@Autowired
	private DatadictTypeDao datadictTypeDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<DatadictType> getCoreDao() {
		return datadictTypeDao;
	}
	public void deleteDatadictTypeList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			datadictTypeDao.deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.DatadictTypeService#getDatadictType(java.lang.String)
	 */
	public DatadictType getDatadictType(String value) {

		return datadictTypeDao.datadictTypeDao(value);
	}
	
}
