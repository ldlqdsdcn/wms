/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.PortTrl;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.BusForecastDao;
import com.delmar.crm.model.BusForecast;
import com.delmar.crm.service.BusForecastService;

/**
 * @author 刘大磊 22015-04-21 08:33:00
 */
@Service("busForecastService")
public class BusForecastServiceImpl extends CoreServiceImpl<BusForecast> implements
		BusForecastService {
	@Autowired
	private BusForecastDao busForecastDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<BusForecast> getCoreDao() {
		return busForecastDao;
	}
	public void deleteBusForecastList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			busForecastDao.deleteByPrimaryKey(id);
		}
	}
	
	
	public List<BusForecast> selectByLinkRecordId(Integer linkRecordId)
	{
		
		return busForecastDao.selectByLinkRecordId(linkRecordId);
	}
	
	public void deleteBusForecastByRecordId(Integer linkRecordId)
	{
		List<BusForecast> busForecastList=this.selectByLinkRecordId(linkRecordId);
		
		for (BusForecast obj:busForecastList)
		{
			this.deleteBusForecastById(obj.getId());
		}
	}
	public void deleteBusForecastById(Integer id)
	{
		this.deleteByPrimaryKey(id);
	}
	
}
