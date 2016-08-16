/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.temp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.temp.dao.TempCityDao;
import com.delmar.temp.model.TempCity;
import com.delmar.temp.service.TempCityService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-06 02:59:26
 */
@Service("tempCityService")
public class TempCityServiceImpl extends CoreServiceImpl<TempCity> implements
		TempCityService {
	@Autowired
	private TempCityDao tempCityDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<TempCity> getCoreDao() {
		return tempCityDao;
	}

	
}
