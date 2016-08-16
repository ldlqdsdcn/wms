/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CarrierTrlDao;
import com.delmar.base.model.CarrierTrl;
import com.delmar.base.service.CarrierTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-02-05 16:23:46
 */
@Service("carrierTrlService")
public class CarrierTrlServiceImpl extends CoreServiceImpl<CarrierTrl> implements
		CarrierTrlService {
	@Autowired
	private CarrierTrlDao carrierTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CarrierTrl> getCoreDao() {
		return carrierTrlDao;
	}
	public void deleteCarrierTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			carrierTrlDao.deleteByPrimaryKey(id);
		}
	}
	
}
