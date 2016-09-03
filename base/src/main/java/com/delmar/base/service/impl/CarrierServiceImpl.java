/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.CarrierDao;
import com.delmar.base.dao.CarrierTrlDao;
import com.delmar.base.model.Carrier;
import com.delmar.base.model.CarrierTrl;
import com.delmar.base.service.CarrierService;
import com.delmar.common.model.FileRelation;
import com.delmar.common.service.DelmarFileService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 13:26:54
 */
@Service("carrierService")
public class CarrierServiceImpl extends CoreServiceImpl<Carrier> implements
		CarrierService {
	@Autowired
	private CarrierDao carrierDao;
	@Autowired
	private CarrierTrlDao carrierTrlDao;
	@Autowired
	private DelmarFileService delmarFileService;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Carrier> getCoreDao() {
		return carrierDao;
	}
	
	
	public Integer GetIdByCode(String code)
	{
		Map<String,Object> filterMap=new HashMap<String,Object>();
		filterMap.put("code",code);
		
		List<Carrier> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return 0;
		}
		else
		{
			return objList.get(0).getId();
		}
		
		
	}


	/* (non-Javadoc)
	 * @see com.delmar.base.service.CarrierService#deleteCarrierList(java.lang.Integer[])
	 */
	public void deleteCarrierList(Integer[] ids) {
		if(ids!=null)
			for(Integer id:ids)
			{
				deleteByPrimaryKey(id);
			}
		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("carrierId", id);
		this.carrierTrlDao.deleteByExample(map);
		return super.deleteByPrimaryKey(id);
	}


	/* (non-Javadoc)
	 * @see com.delmar.base.service.CarrierService#saveCarrier(com.delmar.base.model.Carrier, java.util.List)
	 */
	public void saveCarrier(Carrier carrier, List<CarrierTrl> trlList) {
			this.save(carrier);
			if(trlList!=null)
			{
				for(CarrierTrl ctrl:trlList)
				{
					ctrl.setCarrierId(carrier.getId());
					carrierTrlDao.save(ctrl);
				}
			}
		
	}


	/* (non-Javadoc)
	 * @see com.delmar.base.service.CarrierService#saveCarrier(com.delmar.base.model.Carrier, java.util.List, java.util.List)
	 */
	public void saveCarrier(Carrier carrier, List<CarrierTrl> carrierTrlList,
			List<FileRelation> fileRelationList) {
		this.saveCarrier(carrier, carrierTrlList);
		
		for(FileRelation fr:fileRelationList)
		{
			delmarFileService.save(fr.getDelmarFile());
		}
		
	}

	
}
