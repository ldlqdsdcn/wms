/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CityCompanyDao;
import com.delmar.base.model.CityCompany;
import com.delmar.base.service.CityCompanyService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-18 11:41:34
 */
@Service("cityCompanyService")
public class CityCompanyServiceImpl extends CoreServiceImpl<CityCompany> implements
		CityCompanyService {
	@Autowired
	private CityCompanyDao cityCompanyDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CityCompany> getCoreDao() {
		return cityCompanyDao;
	}
	public void deleteCityCompanyList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			cityCompanyDao.deleteByPrimaryKey(id);
		}
	}
	
}
