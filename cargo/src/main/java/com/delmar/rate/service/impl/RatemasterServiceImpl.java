/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.rate.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.rate.dao.RatemasterDao;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatemasterService;

/**
 * @author 刘大磊 22014-12-22 17:09:41
 */
@Service("ratemasterService")
public class RatemasterServiceImpl extends CoreServiceImpl<Ratemaster> implements
		RatemasterService {
	@Autowired
	private RatemasterDao ratemasterDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Ratemaster> getCoreDao() {
		return ratemasterDao;
	}
	
	
	public Ratemaster getRateMasterByRateNo(String rateNo) throws Exception
	{
		HashMap filterMap=new HashMap();
		filterMap.put("_parameter","true");
		filterMap.put("accessString"," rateNo=#{rateNo}");		
		filterMap.put("rateNo",rateNo);
		filterMap.put("Flag","N");
		filterMap.put("IsActive","1");
				
		
		
		
		List<Ratemaster> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return null;
		}
		else
		{
			return objList.get(0);
		}
		
	}


	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RatemasterService#deleteRatemasterList(java.lang.Integer[])
	 */
	public void deleteRatemasterList(Integer[] ids) {
		if(ids!=null)
			for(Integer id:ids)
			{
				this.deleteRatemaster(id);
			}
		
	}


	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RatemasterService#deleteRatemaster(java.lang.Integer)
	 */
	public void deleteRatemaster(Integer id) {
		
		
		this.deleteByPrimaryKey(id);
		
	}
	

	
}
