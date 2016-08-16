/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.UnitDao;
import com.delmar.base.model.Unit;
import com.delmar.base.service.UnitService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22014-12-22 13:26:54
 */
@Service("unitService")
public class UnitServiceImpl extends CoreServiceImpl<Unit> implements
		UnitService {
	@Autowired
	private UnitDao unitDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Unit> getCoreDao() {
		return unitDao;
	}

	public Integer GetIdByCode(String code) throws Exception
	{
		HashMap filterMap=new HashMap();
		filterMap.put("code",code);
		
		List<Unit> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return new Integer(0);
		}
		else
		{
			return objList.get(0).getId();
		}
		
		
	}	
	
}
