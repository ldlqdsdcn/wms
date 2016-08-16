/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CompanysSopDao;
import com.delmar.base.model.CompanysSop;
import com.delmar.base.service.CompanysSopService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-18 11:41:34
 */
@Service("companysSopService")
public class CompanysSopServiceImpl extends CoreServiceImpl<CompanysSop> implements
		CompanysSopService {
	@Autowired
	private CompanysSopDao companysSopDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CompanysSop> getCoreDao() {
		return companysSopDao;
	}
	public void deleteCompanysSopList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			companysSopDao.deleteByPrimaryKey(id);
		}
	}
	
}
