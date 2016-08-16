package com.delmar.cargo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.BusinessDao;
import com.delmar.cargo.dao.BusinessHisDao;
import com.delmar.cargo.model.BusinessClient;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.cargo.model.ObjPerformancePK;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.cargo.service.FileTableService;
import com.delmar.cargo.service.ReportFareInvoiceService;
import com.delmar.cargo.service.TrustContextService;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午9:52:38 
 * 类说明 
 */
@Service("businessQueryService")
public class BusinessQueryServiceImpl  implements BusinessQueryService  {

	@Autowired
	private BusinessDao  businessDaoHibernate;
	@Autowired
	private BusinessHisDao  businessHisDaoHibernate;
	
	@Autowired
	private TrustContextService  trustContextService;
	@Autowired
	private FileTableService  fileTableService;
	@Autowired
	private ReportFareInvoiceService  reportFareInvoiceService;	
	
	
	public List<BusinessInvoice> searchBusinessInvoiceList(HbnHsql hbnhsql,String planeOcean)	{
		
		return businessDaoHibernate.searchBusinessInvoiceList(hbnhsql, planeOcean);
		
	}
	
	public List<BusinessInvoice> searchBusinessInvoiceState(String salesCode,String flightDateBegin,String flightDateEnd)	{
		
		return businessDaoHibernate.searchBusinessInvoiceState(salesCode,flightDateBegin,flightDateEnd);
	}
	
	public List<BusinessForwarder> searchBusinessList(HbnHsql hbnhsql,String planeOcean){
		
		return businessDaoHibernate.searchBusinessList(hbnhsql, planeOcean);
		
	}
	
	public List<CustomerBusiness> searchCustomerBusiness(HbnHsql hbnhsql)
	{
		return businessHisDaoHibernate.searchCustomerBusiness(hbnhsql);
	}
	
	public List<BusinessPerformance> searchSalesPerformance(HbnHsql hbnhsql)
	{
		return businessHisDaoHibernate.searchSalesPerformance(hbnhsql);
	}	
	
	public BusinessPerformance getSalesPerformance(HbnHsql hbnhsql)
	{
		return businessHisDaoHibernate.getSalesPerformance(hbnhsql);	
	}
	
	public ObjPerformancePK getSalesPerformancePK(HbnHsql hbnhsql,String currentUserCode)
	{
		List<BusinessPerformance> performanceList=businessHisDaoHibernate.searchSalesPerformancePK(hbnhsql);
		
		ObjPerformancePK objPerformancePK=new ObjPerformancePK();
		boolean beStart=false;
		StringBuffer topToYou=new StringBuffer();
		StringBuffer topToYouProfit=new StringBuffer();
		int yourPos=0;
		for (BusinessPerformance objPer:performanceList)
		{
			if (beStart)
			{
				yourPos++;
				topToYou.append(objPer.getName()).append("<br>");
				topToYouProfit.append(objPer.getName()).append("(").append(objPer.getProfit()).append(")").append("<br>");
			}
			else
			{
				if (objPer.getCode().equals(currentUserCode))
				{
					beStart=true;
					continue;
				}
			}
		}
		
		
		objPerformancePK.setYourPos(yourPos+1);
		objPerformancePK.setTopToYou(topToYou.toString());
		objPerformancePK.setTopToYouProfit(topToYouProfit.toString());

		return objPerformancePK;
		 
	}	
	
	
	public List<BusinessClient> searchClientState(HbnHsql hbnhsql,String exportImport,String planeOcean,String chinaForeign)
	{
		List<BusinessClient> list=new ArrayList<BusinessClient>();
		if (chinaForeign.indexOf("china")>-1)
		{

			list.addAll(searchChinaClientState(hbnhsql,exportImport,planeOcean));
		}
		
		if (exportImport.indexOf("foreign")>-1)
		{
			list.addAll(searchForeignClientState(hbnhsql,exportImport,planeOcean));			
		}
		
		
		return list;
	}	
	
	
	
	public List<BusinessClient> searchChinaClientState(HbnHsql hbnhsql,String exportImport,String planeOcean)
	{
		List<BusinessClient> list=new ArrayList<BusinessClient>();
		if (exportImport.indexOf("export")>-1)
		{
			if (planeOcean.indexOf("ocean")>-1)
				list.addAll(businessHisDaoHibernate.searchChinaClientState(hbnhsql,"export","ocean"));

			if (planeOcean.indexOf("air")>-1)
				list.addAll(businessHisDaoHibernate.searchChinaClientState(hbnhsql,"export","air"));
			
		}
		
		if (exportImport.indexOf("import")>-1)
		{
			if (planeOcean.indexOf("ocean")>-1)
				list.addAll(businessHisDaoHibernate.searchChinaClientState(hbnhsql,"import","ocean"));

			if (planeOcean.indexOf("air")>-1)
				list.addAll(businessHisDaoHibernate.searchChinaClientState(hbnhsql,"import","air"));
			
		}
		
		
		return list;
	}	

	
	public List<BusinessClient> searchForeignClientState(HbnHsql hbnhsql,String exportImport,String planeOcean)
	{
		List<BusinessClient> list=new ArrayList<BusinessClient>();
		if (exportImport.indexOf("export")>-1)
		{
			if (planeOcean.indexOf("ocean")>-1)
				list.addAll(businessHisDaoHibernate.searchForeignClientState(hbnhsql,"export","ocean"));

			if (planeOcean.indexOf("air")>-1)
				list.addAll(businessHisDaoHibernate.searchForeignClientState(hbnhsql,"export","air"));
			
		}
		
		if (exportImport.indexOf("import")>-1)
		{
			if (planeOcean.indexOf("ocean")>-1)
				list.addAll(businessHisDaoHibernate.searchForeignClientState(hbnhsql,"import","ocean"));

			if (planeOcean.indexOf("air")>-1)
				list.addAll(businessHisDaoHibernate.searchForeignClientState(hbnhsql,"import","air"));
			
		}
		
		
		return list;
	}
	
	/** 
	 * @Title:        getDebitStatus 
	 * @Description:  TODO
	 * @param:        @param hblNo
	 * @param:        @param mawbNo
	 * @param:        @return    
	 * @return:       boolean    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月24日 下午3:23:33 
	 */
	public boolean getDebitStatus(String hblNo,String mawbNo)
	{
		String trustFileCode="";
		if (!hblNo.equals(""))
		{
			trustFileCode=trustContextService.getTrustFileCodeByHawb(hblNo);
			if (trustFileCode.equals(""))
				trustFileCode=fileTableService.getTrustFileCodeByHawb(hblNo);
		}
		
		if (trustFileCode.equals(""))
		{
			if (!mawbNo.equals(""))
			{
				trustFileCode=trustContextService.getTrustFileCodeByMawb(mawbNo);
				if (trustFileCode.equals(""))
					trustFileCode=fileTableService.getTrustFileCodeByMawb(mawbNo);
			}
		}
		
		
		if (trustFileCode.equals(""))
			return false;
		
		
		if (reportFareInvoiceService.getDebitBalance(trustFileCode).intValue()>0)
			return false;
		else
			return true;
				
	}
	
}
