/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.Datadict;
import com.delmar.base.service.DatadictService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerTraceDao;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerTrace;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.CustomerTraceService;

/**
 * @author 刘大磊 22015-08-14 14:07:47
 */
@Service("customerTraceService")
public class CustomerTraceServiceImpl extends CoreServiceImpl<CustomerTrace> implements
		CustomerTraceService {
	@Autowired
	private CustomerTraceDao customerTraceDao;
	@Autowired
	private DatadictService datadictService;	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<CustomerTrace> getCoreDao() {
		return customerTraceDao;
	}
	public void deleteCustomerTraceList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			customerTraceDao.deleteByPrimaryKey(id);
		}
	}
	
	
	public List<CustomerTrace> selectByCustomerId(Integer customerId)
	{
		return customerTraceDao.selectByCustomerId(customerId);
	}
	
	public void saveCustomerTrace(Integer currentStatusId,Integer oldStatusId,CustomerTrace customerTrace)
	{
		if (currentStatusId==oldStatusId)
			return;
		
		
		Datadict currentStatus=datadictService.selectByPrimaryKey(currentStatusId);
		Datadict oldStatus=datadictService.selectByPrimaryKey(oldStatusId);
		
		Integer changeType=0;
		if (currentStatus.getIndexOrder()>oldStatus.getIndexOrder())
		{
			changeType=1;  //前进
		}
		else
		{
			changeType=-1;  //倒退
		}
		
		

		customerTrace.setChangeType(changeType);
		Date now=new Date();
		customerTrace.setChangeTime(now);
		customerTrace.setFromStatus(oldStatusId);
		customerTrace.setToStatus(currentStatusId);
		customerTrace.setFlag(0);
		
		
		this.save(customerTrace);
	}
	
	
	public void deleteByCustomerId(Integer customerId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("customerId", customerId);
		
		List<CustomerTrace> customerTraceList=this.selectByExample(param);		
		
		for (CustomerTrace obj:customerTraceList)
		{
			this.deleteById(obj.getId());
		}
	}
	
	public void deleteById(Integer id)
	{
		this.deleteByPrimaryKey(id);
	}
	
}
