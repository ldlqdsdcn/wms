/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.ChargenameDao;
import com.delmar.base.dao.ChargenameExtraDao;
import com.delmar.base.model.Chargename;
import com.delmar.base.model.ChargenameExtra;
import com.delmar.base.service.ChargenameService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 13:26:54
 */
@Service("chargenameService")
public class ChargenameServiceImpl extends CoreServiceImpl<Chargename> implements
		ChargenameService {
	@Autowired
	private ChargenameDao chargenameDao;
	@Autowired
	private ChargenameExtraDao chargenameExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Chargename> getCoreDao() {
		return chargenameDao;
	}
	
	public Integer GetIdByCode(String code) throws Exception
	{
		HashMap filterMap=new HashMap();
		filterMap.put("code",code);
		
		List<Chargename> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return new Integer(0);
		}
		else
		{
			return objList.get(0).getId();
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.service.ChargenameService#selectConformCharenameExtra(java.math.BigDecimal, java.math.BigDecimal)
	 */
	public Chargename selectConformTruck(BigDecimal grossWeight,
			BigDecimal volume,String extraType) {
		List<ChargenameExtra> extraList=chargenameExtraDao.selectConformCharenameExtra(grossWeight, volume,extraType);
		if (extraList==null)
			return null;
		
		StringBuffer sb=new StringBuffer();
		for (ChargenameExtra objOne:extraList)
		{
			sb.append(objOne.getBaseChargenameId()+",");
		}
		
		sb.deleteCharAt(sb.length()-1);
		
		Map<String,Object> param=new HashMap<String,Object>();
	    param.put("accessString", " id in ("+sb.toString()+")");
	    List<Chargename> chargenameList=chargenameDao.selectByExample(param);
	    if (chargenameList.size()==0)
	    	return null;
	    
	    return chargenameList.get(0);
	    
	    

	}
	

	
}
