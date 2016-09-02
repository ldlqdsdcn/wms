/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.rate.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.rate.dao.RatedetailDao;
import com.delmar.rate.model.Ratedetail;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatedetailService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 17:09:41
 */
@Service("ratedetailService")
public class RatedetailServiceImpl extends CoreServiceImpl<Ratedetail> implements
		RatedetailService {
	@Autowired
	private RatedetailDao ratedetailDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Ratedetail> getCoreDao() {
		return ratedetailDao;
	}
	
	
	/** (non-Javadoc)
	 * @see com.delmar.rate.service.RatedetailService#getRateDetailBy(int, String, String)
	 * 
	 * 
	 */
	public Ratedetail getRateDetailBy(int masterId,String chargeCode,String fcllcl) throws Exception
	{
		HashMap filterMap=new HashMap();
		filterMap.put("_parameter","true");
		filterMap.put("accessString"," rateNo=#{rateNo}");		
		filterMap.put("base_chargename_id",chargeCode);
		filterMap.put("fcllcl",fcllcl);
		filterMap.put("Flag","N");
		
		filterMap.put("orderByClause"," effectEndDate desc ");
		
		List<Ratedetail> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return null;
		}
		else
		{
			return objList.get(0);
		}
		
	}


	/** (non-Javadoc)
	 * @see com.delmar.rate.service.RatedetailService#caculatePrice(java.math.BigDecimal, java.math.BigDecimal, java.math.BigDecimal)
	 * 
	 *  totalPrice< minValue  取 minValue
	 *  totalPrice> maxValue 取 maxValue
	 */
	public BigDecimal caculatePrice(BigDecimal totalPrice, BigDecimal minValue,
			BigDecimal maxValue) {
		double zero=0;
		if(minValue.doubleValue()!=zero&&totalPrice.compareTo(minValue)<0)
		{
			return minValue;
		}
		if(maxValue.intValue()!=zero&&totalPrice.compareTo(maxValue)>0)
		{
			return maxValue;
		}
		return totalPrice;
	}
	
}
