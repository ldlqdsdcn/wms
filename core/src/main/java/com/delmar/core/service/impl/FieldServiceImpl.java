/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.FieldDao;
import com.delmar.core.model.Field;
import com.delmar.core.service.FieldService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22016-08-26 17:08:24
 */
@Service("fieldService")
public class FieldServiceImpl extends CoreServiceImpl<Field> implements
		FieldService {
	@Autowired
	private FieldDao fieldDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Field> getCoreDao() {
		return fieldDao;
	}
	public void deleteFieldList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			fieldDao.deleteByPrimaryKey(id);
		}
	}
	
}
