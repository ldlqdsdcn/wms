/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.rate.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.rate.dao.RatemarkupDao;
import com.delmar.rate.model.Ratemarkup;
import com.delmar.rate.service.RatemarkupService;

/**
 * @author 刘大磊 22014-12-22 17:09:41
 */
@Service("ratemarkupService")
public class RatemarkupServiceImpl extends CoreServiceImpl<Ratemarkup> implements
		RatemarkupService {
	@Autowired
	private RatemarkupDao ratemarkupDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Ratemarkup> getCoreDao() {
		return ratemarkupDao;
	}
	/** (non-Javadoc)
	 * @see com.delmar.rate.service.RatemarkupService#getRatemarkup(int, int, int)
	 * 
	 * 报价单优先级别
	 *     			nameaccount    ratemaster  chargename freighttype
	 *			1			1					1					1				1
	 *			2			1					1					1				0
	 *			3			1					1					0				1
	 *			4			1					1					0				0
	 *			5			1					0					1				1
	 *			6			1					0					1				0
	 *			7			1					0					0				1
	 *			8			1					0					0				0
	 *			9			0					1					1				1
	 *			10			0					1					1				0
	 *			11			0					1					0				1
	 *			12			0					1					0				0
	 *			13			0					0					1				1
	 *			14			0					0					1				0
	 *			15			0					0					0				1
	 *			16			0					0					0				0
	 *          9所有条件都不满足
	 */
	public Ratemarkup getRatemarkup(int nameaccountId, int ratemasterId,
			int chargenameId,int freighttype) {
		int[] nameaccountIds={nameaccountId,-1};
		int[] ratemasterIds={ratemasterId,-1};
		int[]  chargenameIds={chargenameId,-1};
		int[] freighttypes={freighttype,-1};
		for(int a1:nameaccountIds)
		{
			for(int a2:ratemasterIds)
			{
				for(int a3:chargenameIds)
				{
					for(int a4:freighttypes)
					{
						Map<String,Object> selectParam=new HashMap<String,Object>();
						selectParam.put("baseNameaccountId", a1);
						selectParam.put("rRateMasterId", a2);
						selectParam.put("baseChargenameId", a3);
						selectParam.put("freighttype", a4);
						List<Ratemarkup> list=ratemarkupDao.selectByExample(selectParam);
						if(list!=null&&list.size()>0)
						{
							return list.get(0);
						}
					}
				}
			}
		}
	
		
		return null;
	}
	
}
