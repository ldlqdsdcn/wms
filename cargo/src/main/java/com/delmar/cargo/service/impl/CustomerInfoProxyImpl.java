package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.delmar.cargo.model.CustomerInfo;
import com.delmar.cargo.service.CustomerInfoService;
import com.delmar.crm.proxy.pub.CustomerProxy;
import com.delmar.crm.proxy.pub.CustomerProxyManage;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年7月2日 上午11:29:09 
 * 类说明 
 */
@Controller
public class CustomerInfoProxyImpl implements CustomerProxy{
	
	@Autowired
	private CustomerInfoService customerInfoService;
	
	public CustomerInfoProxyImpl()
	{
		CustomerProxyManage.getInstance().registProxy(this);
	}
	
	
	public boolean checkOpsCode(String opsCode)
	{
		
		CustomerInfo objCustomer=customerInfoService.getObject(opsCode);
		
		if (objCustomer!=null)   //说明客户对象存在
		{
			//下面要检查此客户是否属于这个业务员的
			//这个就不检查了，后续查看也是要过滤业务员的
		   return true;
		}
		else
		{
			return false;
		}
		
	}
	

}
