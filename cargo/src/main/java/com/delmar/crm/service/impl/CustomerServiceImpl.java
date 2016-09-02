/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.crm.model.CustomerExtraPropDefine;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.CustomerExtraPropDefineService;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.CustomerTraceService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.utils.ProObjectUtil;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22015-01-23 17:47:07
 */
@Service("customerService")
public class CustomerServiceImpl extends CoreServiceImpl<Customer> implements
		CustomerService {
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private CustomerExterService customerExterService;
	@Autowired
	private LinkrecordService linkrecordService;
	@Autowired
	private CustomerTraceService customerTraceService;	
	@Autowired
	private CustomerExtraPropDefineService customerExtraPropDefineService;	
	
	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Customer> getCoreDao() {
		return customerDao;
	}
	public void deleteCustomerList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
            this.deleteById(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.crm.service.CustomerService#saveCustomer(com.delmar.crm.model.Customer, com.delmar.crm.model.Linkman, com.delmar.crm.model.CustomerExtra)
	 */
	public void saveCustomer(Customer customer, Linkman linkman,
			CustomerExtra exter) {
		if (customer.getId()==null)
		{
			exter.setLatitude(new BigDecimal(-1));
			exter.setLongtitude(new BigDecimal(-1));
			
		}
		
		//此处存储PropLabel
		if (StringUtil.isNotEmpty(exter.getPropLabel()))
		{
			saveCustomerExtraPropDefine(exter.getPropLabel(),customer);
	
		}
 
		

		customerDao.save(customer);
		linkman.setCustomerId(customer.getId());
		linkmanService.save(linkman);
		exter.setId(customer.getId());
		String propLabel=exter.getPropLabel();
		if (propLabel==null)
			propLabel="";
		else
			propLabel=propLabel.replaceAll("，", ",");
		
		exter.setPropLabel(propLabel);
		customerExterService.save(exter);
	}
	
	public void deleteById(Integer id)
	{
		
		linkmanService.deleteByCustomerId(id);
		customerExterService.deleteByPrimaryKey(id);
		customerTraceService.deleteByCustomerId(id);
		customerDao.deleteByPrimaryKey(id);
		
	}
	
	public String getNamebyId(Integer id)
	{
		return customerDao.getNamebyId(id);	
	}
	
	private void saveCustomerExtraPropDefine(String propLabel,Customer customer)
	{
		List<CustomerExtraPropDefine> propList=customerExtraPropDefineService.selectPropByUserId(customer.getUserId());
	    List<String> propValueList=new ArrayList<String>();
	    try
	    {
	    	propValueList =ProObjectUtil.retrieveFieldList(propList, "propValue");
	    } catch (Exception ex)
	    {
	    	
	    }
	    
	    propLabel=propLabel.replaceAll("，","," );
	    
		String[] propArray=propLabel.split(",");
		for (String propValue:propArray)
		{
			if (propValue.trim().equals(""))
				continue;
				
			if (propValueList.indexOf(propValue)==-1)
			{
				CustomerExtraPropDefine onePropObject=new CustomerExtraPropDefine();
				onePropObject.setPropValue(propValue);

				onePropObject.setUserId(customer.getUserId());
				onePropObject.setUserName(customer.getUserName());
				onePropObject.setCreated(customer.getUpdated());				
				onePropObject.setCreatedBy(customer.getUpdatedBy());
				onePropObject.setCreatedByName(customer.getUpdatedByName());			
				onePropObject.setOrgId(customer.getOrgId());
				onePropObject.setClientId(customer.getClientId());	
				customerExtraPropDefineService.save(onePropObject);
				
				
			}
		}
				
		
	}
	

	
	
}
