package com.delmar.crm.web.dwr;

import java.util.ArrayList;
import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.crm.model.CustomerExtraPropDefine;
import com.delmar.crm.service.CustomerExtraPropDefineService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;


@Repository("customerExtraPropDwr") 
public class CustomerExtraPropDwr {
	@Autowired
	private CustomerExtraPropDefineService customerExtraPropDefineService;
	@Autowired
	private DatadictService datadictService;
	
	
	public CustomerExtraPropDefine[] getCustomerExtraPropDefineList(String currentPropLabel)
	{
		//if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		//{
		//	return null;
		//}
	
		UserResource ur=(UserResource)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute("resource");		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		List<DatadictTrl> extraPropList=datadictService.getDatadictTrlByValue(DatadictType.CUSTOMER_EXTRAPROPLABEL,ur.getLocale().toString(),up.getLoginClientId());
		List<CustomerExtraPropDefine> userPropList=customerExtraPropDefineService.selectPropByUserId(up.getLoginUserId());
		List<CustomerExtraPropDefine> list=new ArrayList<CustomerExtraPropDefine>();
		currentPropLabel=","+currentPropLabel+",";
		List<String> propLabelList=new ArrayList<String>();
		for (DatadictTrl obj:extraPropList)
		{
			if (currentPropLabel.indexOf(","+obj.getName()+",")>-1)
				continue;
			CustomerExtraPropDefine objCustomerExtraPropDefine=new CustomerExtraPropDefine();
			objCustomerExtraPropDefine.setId(obj.getDatadictId());
			objCustomerExtraPropDefine.setPropValue(obj.getName());
			objCustomerExtraPropDefine.setType("FIX");
			
			propLabelList.add(obj.getName());
			
			
			list.add(objCustomerExtraPropDefine);
		}
		
		for (CustomerExtraPropDefine obj:userPropList)
		{
			if (currentPropLabel.indexOf(","+obj.getPropValue()+",")>-1)
				continue;
			
			if (propLabelList.indexOf(obj.getPropValue())>-1)
				continue;
			
			propLabelList.add(obj.getPropValue());
			
			obj.setType("");
			
			list.add(obj);
		}
		
		
		
		if(list!=null)
		{
			CustomerExtraPropDefine[] customerArray=new CustomerExtraPropDefine[list.size()];
			list.toArray(customerArray);
			return customerArray;
		}
		else 
			return null;
	}
	
	
	public String getCustomerExtraPropDefine(String currentPropLabel,Integer colCount)
	{
		CustomerExtraPropDefine[] propDefineArray=getCustomerExtraPropDefineList(currentPropLabel);
		if (propDefineArray==null)
			return "";
		
		StringBuilder sb=new StringBuilder("");
		for(int i=0;i<propDefineArray.length;i++)
		{
			CustomerExtraPropDefine oneObject=propDefineArray[i];
			sb.append("<div class='d-checkitem'><input type='checkbox' name='propLabels' id='propLabels' value='").append(oneObject.getPropValue()).append("' ");
			sb.append("/>&nbsp;");
			sb.append(oneObject.getPropValue());
			if (oneObject.getType().equals(""))
			   sb.append("<a href='#' onclick='deletePropLabel(\"").append(oneObject.getId()).append("\")'></a>");

			sb.append("</div>&nbsp;");
			if((i+1)%colCount==0)
			{
				sb.append("<br>");
			}
		}
		return sb.toString();
	}
	
	
	
	public String deleteCustomerExtraPropDefine(String currentPropLabel,Integer id,Integer colCount)
	{
		
		customerExtraPropDefineService.deleteByPrimaryKey(id);
		
		return  getCustomerExtraPropDefine(currentPropLabel,colCount);

	}
	
	

}
