package com.delmar.cargo.web.dwr;

import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.cargo.service.ReportFareInvoiceService;
import com.delmar.core.model.CriteriaH;
import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 下午5:17:48 
 * 类说明 
 */
@Repository("reportfareinvoiceDwr") 
public class ReportFareInvoiceDwr {
	@Autowired
	private ReportFareInvoiceService reportfareinvoiceService;
	
	public ReportFareInvoice[] getInvoiceList(String trustFileCode,String planeOcean)
	{
		/*
		if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
		{
			return null;
		}
		*/

		
		
		PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String accessString=up.getDWRAccessStringByStruts2();
		
		CriteriaH ch=new CriteriaH(ReportFareInvoice.class);
		if (planeOcean.equals("Ocean"))
		  ch.addField("trustCode", "=",trustFileCode);
		else
		  ch.addField("fileCode", "=",trustFileCode);			
		
		List<ReportFareInvoice> invoiceList=reportfareinvoiceService.search(ch);
	
		ReportFareInvoice[] invoiceArray=new ReportFareInvoice[invoiceList.size()];
		invoiceList.toArray(invoiceArray);
		return invoiceArray;
	}
	
	public String getInvoiceGson(String trustFileCode,String planeOcean)
	{
		ReportFareInvoice[] invoiceArray=getInvoiceList(trustFileCode,planeOcean);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();  
		return gson.toJson(invoiceArray);
	}
	
	
   	
}
