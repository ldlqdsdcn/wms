/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.crm.dao.SaleSheetDao;
import com.delmar.crm.model.SaleSheet;
import com.delmar.crm.service.SaleSheetService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-21 08:38:38
 */
@Service("saleSheetService")
public class SaleSheetServiceImpl extends CoreServiceImpl<SaleSheet> implements
		SaleSheetService {
	@Autowired
	private SaleSheetDao saleSheetDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<SaleSheet> getCoreDao() {
		return saleSheetDao;
	}
	public void deleteSaleSheetList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			saleSheetDao.deleteByPrimaryKey(id);
		}
	}
	
}
