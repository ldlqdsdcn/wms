/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.crm.dao.SaleSheetQuotaDao;
import com.delmar.crm.model.SaleSheetQuota;
import com.delmar.crm.service.SaleSheetQuotaService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-21 08:44:27
 */
@Service("saleSheetQuotaService")
public class SaleSheetQuotaServiceImpl extends CoreServiceImpl<SaleSheetQuota> implements
		SaleSheetQuotaService {
	@Autowired
	private SaleSheetQuotaDao saleSheetQuotaDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<SaleSheetQuota> getCoreDao() {
		return saleSheetQuotaDao;
	}
	public void deleteSaleSheetQuotaList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			saleSheetQuotaDao.deleteByPrimaryKey(id);
		}
	}
	
}
