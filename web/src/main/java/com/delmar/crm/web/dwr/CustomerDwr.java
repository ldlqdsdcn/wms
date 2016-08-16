/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.crm.web.dwr;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Customer;
import com.delmar.crm.service.CustomerService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 2015年3月10日 下午4:50:28
 */
@Repository("customerDwr") 
public class CustomerDwr {
	@Autowired
	private CustomerService customerService;
	
	public Customer[] getCustomerList(String name)
	{
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		if(name==null)
		{
			name="";
		}
		name=name.trim();
		Map param=new HashMap();
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String accessString=up.getDWRAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString="name like '%"+StringUtil.fullYhStr(name)+"%'";
		else
			accessString=accessString+ " and name like '%"+StringUtil.fullYhStr(name)+"%'";
		

		
		param.put("accessString",accessString);
		
		List<Customer> list=this.customerService.selectByExample(param);
		if(list!=null)
		{
			Customer[] customerArray=new Customer[list.size()];
			list.toArray(customerArray);
			return customerArray;
		}
		else 
			return null;
	}
	
	public Customer getCustomer(String cusCode)
	{
		Map param=new HashMap();
		param.put("accessString", "cusCode='"+cusCode+"'");
		
		List<Customer> list=this.customerService.selectByExample(param);
		if((list!=null)&&(list.size()>0))
		{
			
			return list.get(0);
		}
		else 
			return null;
		
	}
}
