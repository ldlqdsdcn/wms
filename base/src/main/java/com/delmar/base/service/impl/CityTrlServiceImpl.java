/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CityTrlDao;
import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-02-09 19:51:15
 */
@Service("cityTrlService")
public class CityTrlServiceImpl extends CoreServiceImpl<CityTrl> implements
		CityTrlService {
	@Autowired
	private CityTrlDao cityTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CityTrl> getCoreDao() {
		return cityTrlDao;
	}
	public void deleteCityTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			cityTrlDao.deleteByPrimaryKey(id);
		}
	}
	
}
