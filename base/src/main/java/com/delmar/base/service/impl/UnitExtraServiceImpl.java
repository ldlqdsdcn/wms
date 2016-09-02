/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.UnitExtraDao;
import com.delmar.base.model.UnitExtra;
import com.delmar.base.service.UnitExtraService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-16 18:01:56
 */
@Service("unitExtraService")
public class UnitExtraServiceImpl extends CoreServiceImpl<UnitExtra> implements
		UnitExtraService {
	@Autowired
	private UnitExtraDao unitExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<UnitExtra> getCoreDao() {
		return unitExtraDao;
	}
	public void deleteUnitExtraList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			unitExtraDao.deleteByPrimaryKey(id);
		}
	}
	
}
