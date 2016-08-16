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

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerExtraPropDefineDao;
import com.delmar.crm.model.CustomerExtraPropDefine;
import com.delmar.crm.service.CustomerExtraPropDefineService;

/**
 * @author 刘大磊 22015-09-08 15:44:27
 */
@Service("customerExtraPropDefineService")
public class CustomerExtraPropDefineServiceImpl extends CoreServiceImpl<CustomerExtraPropDefine> implements
		CustomerExtraPropDefineService {
	@Autowired
	private CustomerExtraPropDefineDao customerExtraPropDefineDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CustomerExtraPropDefine> getCoreDao() {
		return customerExtraPropDefineDao;
	}
	public void deleteCustomerExtraPropDefineList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			customerExtraPropDefineDao.deleteByPrimaryKey(id);
		}
	}
	
	
	public List selectPropByUserId(Integer userId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", userId);
		return this.selectByExample(param);
	}
	

	
}
