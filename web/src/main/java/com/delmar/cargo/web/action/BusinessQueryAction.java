package com.delmar.cargo.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.cargo.model.ObjPerformancePK;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.cargo.service.FileTableService;
import com.delmar.cargo.service.ReportFareInvoiceService;
import com.delmar.cargo.service.TrustContextService;
import com.delmar.cargo.web.comparator.CustomerBusinessLastEtdComparator;
import com.delmar.cargo.web.comparator.PerformanceIMonthComparator;
import com.delmar.cargo.web.pub.ObjCustomerBusiness;
import com.delmar.cargo.web.pub.ObjPerformance;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.web.action.CustomerAction;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.JSChartsItem;
import com.delmar.web.model.JSChartsMain;
import com.google.gson.Gson;



/**
 *@ClassName:   BusinessQueryAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月27日 下午3:25:22
 * @version V2.0
 */
public class BusinessQueryAction  extends CoreAction  {


	private List<Org> orgs;
	private List<DatadictTrl> busModeList;   //业务类型
	private List<DatadictTrl> creditDebitList;   //应收应付
	private List<DatadictTrl> invoiceStatusList;   //收支状态
	
	
	@Autowired
	private OrgService orgService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private UserService userService;
	@Autowired
	private TrustContextService trustContextService;
	
	@Autowired
	private BusinessQueryService businessQueryService;	
	@Autowired
	private FileTableService fileTableService;		
	
	@Autowired
	private ReportFareInvoiceService reportFareInvoiceService;	
	
	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
			
	
	
	
	public String lists()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		init();
		
		

 

		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);		
		
		return "lists";
	}
	
	public String listi()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
		initInvoice();


		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listi";
	}
	
	public String listc()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
	
		FacesUtils.setValueInHashtableOfSession("soutIn", "0");
		FacesUtils.setValueInHashtableOfSession("smode", "0");
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listc";
	}	
	
	public String listp()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
	

		FacesUtils.setValueInHashtableOfSession("soutIn", "0");
		FacesUtils.setValueInHashtableOfSession("smode", "0");

		JSChartsMain chart=new JSChartsMain();
		JSChartsItem oneItem=new JSChartsItem("line","first line");
		oneItem.AddOneData("10","20");
		oneItem.AddOneData("15","10");
		oneItem.AddOneData("20","30");
		oneItem.AddOneData("25","10");
		oneItem.AddOneData("30","5");
		
		chart.getDatasets().add(oneItem);
		chart.setOptionset(null);
		
		
		Gson gson=new Gson();
		String json = "{\"JSChart\":"+gson.toJson(chart)+"}";
		FacesUtils.setValueInHashtableOfSession("mydatatest",json);
		
		System.out.println(json);
		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listp";
	}	
	
	/** 
	 * @Title:        searchs 
	 * @Description:  查询提单
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:27:33 
	 */
	public String searchs()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();
		
		String fileNo=request.getParameter("fileNo");
		FacesUtils.setValueInHashtableOfSession("fileNo", fileNo);
		String flightDateBegin=request.getParameter("flightDateBegin");
		FacesUtils.setValueInHashtableOfSession("flightDateBegin", flightDateBegin);
		String flightDateEnd=request.getParameter("flightDateEnd");
		FacesUtils.setValueInHashtableOfSession("flightDateEnd", flightDateEnd);
		String cwConfirmDateBegin=request.getParameter("cwConfirmDateBegin");
		FacesUtils.setValueInHashtableOfSession("cwConfirmDateBegin", cwConfirmDateBegin);
		String cwConfirmDateEnd=request.getParameter("cwConfirmDateEnd");
		FacesUtils.setValueInHashtableOfSession("cwConfirmDateEnd", cwConfirmDateEnd);	
		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
				
		String shipperName=request.getParameter("shipperName");
		FacesUtils.setValueInHashtableOfSession("shipperName", shipperName);
		
		String consignName=request.getParameter("consignName");
		FacesUtils.setValueInHashtableOfSession("consignName", consignName);		
		
		
		String orgId=request.getParameter("orgId");
		FacesUtils.setValueInHashtableOfSession("orgId",orgId);
		String org=request.getParameter("org");
		FacesUtils.setValueInHashtableOfSession("org",org);
		
		
		
		
		
		List list=new ArrayList();
		
		if (mode.equals("")||mode.indexOf("Ocean")>-1)
			list.addAll(searchShipment(orgId,fileNo,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,shipperName,consignName,"Ocean"));
		
		if (mode.equals("")||mode.indexOf("Air")>-1)		
			list.addAll(searchShipment(orgId,fileNo,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,shipperName,consignName,"Air"));
		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);		
		
		
	
		
		return "searchs";
	}	
	
	
	/** 
	 * @Title:        searchi 
	 * @Description:  查询账单
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:27:42 
	 */
	public String searchi()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();
		initInvoice();
		
		String fileNo=request.getParameter("fileNo");
		FacesUtils.setValueInHashtableOfSession("fileNo", fileNo);
		String hawb=request.getParameter("hawb");
		FacesUtils.setValueInHashtableOfSession("hawb", hawb);		
		String flightDateBegin=request.getParameter("flightDateBegin");
		FacesUtils.setValueInHashtableOfSession("flightDateBegin", flightDateBegin);
		String flightDateEnd=request.getParameter("flightDateEnd");
		FacesUtils.setValueInHashtableOfSession("flightDateEnd", flightDateEnd);
		String cwConfirmDateBegin=request.getParameter("cwConfirmDateBegin");
		FacesUtils.setValueInHashtableOfSession("cwConfirmDateBegin", cwConfirmDateBegin);
		String cwConfirmDateEnd=request.getParameter("cwConfirmDateEnd");
		FacesUtils.setValueInHashtableOfSession("cwConfirmDateEnd", cwConfirmDateEnd);	
		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
		
		String invoiceObject=request.getParameter("invoiceObject");
		FacesUtils.setValueInHashtableOfSession("invoiceObject", invoiceObject);
		
		String creditDebitId=request.getParameter("creditDebitId");
		FacesUtils.setValueInHashtableOfSession("creditDebitId", creditDebitId);		
		
		String invoiceStatusId=request.getParameter("invoiceStatusId");
		FacesUtils.setValueInHashtableOfSession("invoiceStatusId", invoiceStatusId);		
		
		
		String orgId=request.getParameter("orgId");
		FacesUtils.setValueInHashtableOfSession("orgId",orgId);
		String org=request.getParameter("org");
		FacesUtils.setValueInHashtableOfSession("org",org);
		
		
		
	

		
		List list=new ArrayList();
		
		
		if (mode.equals("")||mode.indexOf("Ocean")>-1)
			list.addAll(searchInvoice(orgId,fileNo,hawb,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,invoiceObject,creditDebitId,invoiceStatusId,"Ocean"));
		
		if (mode.equals("")||mode.indexOf("Air")>-1)		
			list.addAll(searchInvoice(orgId,fileNo,hawb,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,invoiceObject,creditDebitId,invoiceStatusId,"Air"));
		
		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);		
		
		
	
		
		return "searchi";
	}	
	
	
	/** 
	 * @Title:        searchc 
	 * @Description:  查询走货和未走货记录
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:27:57 
	 */
	public String searchc()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();

		String soutIn=request.getParameter("soutIn");
		if (soutIn==null)
			soutIn="0";
		FacesUtils.setValueInHashtableOfSession("soutIn", soutIn);		
		
		String outInOut=request.getParameter("outInOut");
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		FacesUtils.setValueInHashtableOfSession("outInIn", outInIn);		
		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
		String smode=request.getParameter("smode");
		if (smode==null)
			smode="0";		
		FacesUtils.setValueInHashtableOfSession("smode", mode);
		
		

		String cusName=request.getParameter("cusName");
		FacesUtils.setValueInHashtableOfSession("cusName", cusName);

		
		String haveBusiness=request.getParameter("haveBusiness");
		if (haveBusiness==null)
			haveBusiness="0";
		FacesUtils.setValueInHashtableOfSession("haveBusiness", haveBusiness);
		
		
		String daysBegin=request.getParameter("daysBegin");
		if (daysBegin==null)
			daysBegin="30";
		FacesUtils.setValueInHashtableOfSession("daysBegin", daysBegin);
		
		String daysEnd=request.getParameter("daysEnd");
		if (daysEnd==null)
			daysEnd="30";
		FacesUtils.setValueInHashtableOfSession("daysEnd", daysEnd);
		
		
		String orgId=request.getParameter("orgId");
		FacesUtils.setValueInHashtableOfSession("orgId",orgId);
		String org=request.getParameter("org");
		FacesUtils.setValueInHashtableOfSession("org",org);
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(CustomerBusiness.class);		
		
		hbmwhere.addWhereCell("a.SalesCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        if (!orgId.equals(""))
        {
      		hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgId), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        }    
        
		//直分单
		if (!((outInOut==null&&outInIn==null)||(outInOut!=null&&outInIn!=null)))
		{
			if (outInOut!=null)
			   hbmwhere.addWhereCell("a.outIn", outInOut, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			
			if (outInIn!=null)
				   hbmwhere.addWhereCell("a.outIn", outInIn, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			
		}

		if(StringUtil.isNotEmpty(cusName))
		{
		  hbmwhere.addWhereCell("b.cusName", cusName, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}
		
		if (haveBusiness.equals("0"))  //未走货
		{
			String hisDate=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd));
			hbmwhere.addWhereCell("a.lastEtd", hisDate, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
			String hisDateBegin=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd)+30);
			hbmwhere.addWhereCell("a.lastEtd", hisDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
		} else   //走货
		{
			String hisDateEnd=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd));
			String hisDateBegin=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysBegin));
			hbmwhere.addWhereCell("a.lastEtd", hisDateEnd, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
			hbmwhere.addWhereCell("a.lastEtd", hisDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
			
		}
			
        if (!mode.equals(""))
        {
        	if (mode.equals("Ocean"))
        		hbmwhere.addWhereCell("a.planeOcean", mode, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        	
        	if (mode.equals("Air"))
        		hbmwhere.addWhereCell("a.planeOcean", mode, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        	
        }


        
		if (hbmwhere.hasWhereCell())
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}
		
		
		

		
		List<CustomerBusiness> list=new ArrayList<CustomerBusiness>();
		list=businessQueryService.searchCustomerBusiness(hbmwhere);
		
		
	    List<ObjCustomerBusiness> perList=new ArrayList<ObjCustomerBusiness>();
	    
		HashMap perMap=new HashMap();
		for (CustomerBusiness obj:list)
        {
			  ObjCustomerBusiness objPer=new ObjCustomerBusiness(); 
			  String key=obj.getCusCode();
			  objPer.getGroupList().add(obj.getCusCode());
			  objPer.getGroupList().add(obj.getCusName());
			  objPer.setCusCode(obj.getCusCode());
			  objPer.setCusName(obj.getCusName());
			  
			  if (smode.equals("1"))  //区分
			  {
				  key=key+obj.getPlaneOcean();
				  objPer.getGroupList().add(obj.getPlaneOceanTrl());
				  objPer.setPlaneOcean(obj.getPlaneOcean());				  
			  }
			  
			  if (soutIn.equals("1"))  //区分
			  {
				  key=key+obj.getOutIn().toString();
				  objPer.getGroupList().add(obj.getOutInTrl());
				  objPer.setOutIn(obj.getOutIn());				  
			  }
			  
			  if (perMap.containsKey(key))
			  {
				  objPer=(ObjCustomerBusiness)perMap.get(key);	
				  if (objPer.getFirstEtd().compareTo(obj.getFirstEtd())>0)
				  {
					  objPer.setFirstEtd(obj.getFirstEtd());
					  
				  }
				  if (objPer.getLastEtd().compareTo(obj.getLastEtd())<0)
				  {
					  objPer.setLastEtd(obj.getLastEtd());;
					  objPer.setPlaneOcean(obj.getPlaneOcean());
					  objPer.setOutIn(obj.getOutIn());
					  objPer.setCompanyId(obj.getCompanyId());
					  
				  }

			  }
			  else
			  {
				  objPer.setLastEtd(obj.getLastEtd());;			
				  objPer.setFirstEtd(obj.getFirstEtd());
				  
				  objPer.setPlaneOcean(obj.getPlaneOcean());
				  objPer.setOutIn(obj.getOutIn());
				  objPer.setCompanyId(obj.getCompanyId());
				  perMap.put(key, objPer);
			  }
	  	  }
		
		
		Object[] secondList=perMap.values().toArray();
		List thridList=Arrays.asList(secondList);

		CustomerBusinessLastEtdComparator customerComparator=new CustomerBusinessLastEtdComparator();
		Collections.sort(thridList, customerComparator);		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", thridList);		
		
		
	
		
		return "searchc";
	}		
	
	
	/** 
	 * @Title:        searchp 
	 * @Description:  查询业务员的业绩
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:28:17 
	 */
	public String searchp()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();

		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
		String smode=request.getParameter("smode");
		if (smode==null)
			smode="0";  //不分区空海运
		FacesUtils.setValueInHashtableOfSession("smode", smode);
		
		
		String outInOut=request.getParameter("outInOut");
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		FacesUtils.setValueInHashtableOfSession("outInIn", outInIn);	

		String soutIn=request.getParameter("soutIn");
		if (soutIn==null)
			soutIn="0";  //不分区进出口
		FacesUtils.setValueInHashtableOfSession("soutIn", soutIn);

		
		
		String monthBegin=request.getParameter("monthBegin");
		if (monthBegin==null)
			monthBegin=ProDateUtil.getShortHisDateStr(0).substring(0,7);
		FacesUtils.setValueInHashtableOfSession("monthBegin", monthBegin);
		
		String monthEnd=request.getParameter("monthEnd");
		if (monthEnd==null)
			monthEnd=ProDateUtil.getShortHisDateStr(0).substring(0,7);
		FacesUtils.setValueInHashtableOfSession("monthEnd", monthEnd);
		
		
		String orgId=request.getParameter("orgId");
		FacesUtils.setValueInHashtableOfSession("orgId",orgId);
		String org=request.getParameter("org");
		FacesUtils.setValueInHashtableOfSession("org",org);
		
		
		

		

		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessPerformance.class);		
		hbmwhere.addWhereCell("a.PersonCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		
		hbmwhere.addWhereCell("a.PerforType", "SALES", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        if (!orgId.equals(""))
        {
      		hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgId), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        }  		
		
		//直分单
		if (!((outInOut==null&&outInIn==null)||(outInOut!=null&&outInIn!=null)))
		{
			if (outInOut!=null)
			   hbmwhere.addWhereCell("a.outIn", outInOut, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			
			if (outInIn!=null)
				   hbmwhere.addWhereCell("a.outIn", outInIn, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			
		}
		
		if(StringUtil.isNotEmpty(monthBegin))
		{
		  hbmwhere.addWhereCell("a.IMonth", monthBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);		  
		}
		
		if(StringUtil.isNotEmpty(monthEnd))
		{
		  hbmwhere.addWhereCell("a.IMonth", monthEnd, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);		  
		}		
	
        if (!mode.equals(""))
        {
        	if (mode.equals("Ocean"))
        		hbmwhere.addWhereCell("a.planeOcean", mode, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        	
        	if (mode.equals("Air"))
        		hbmwhere.addWhereCell("a.planeOcean", mode, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        	
        }

        

        
		if (hbmwhere.hasWhereCell())
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}

		
	    List<BusinessPerformance> list=businessQueryService.searchSalesPerformance(hbmwhere);
	    
	    List<ObjPerformance> perList=new ArrayList<ObjPerformance>();
	    
		HashMap perMap=new HashMap();
		for (BusinessPerformance obj:list)
        {
			  ObjPerformance objPer=new ObjPerformance(); 
			  String key=obj.getCode();
			  objPer.getGroupList().add(obj.getCode());
			  objPer.getGroupList().add(obj.getName());
			  objPer.setCode(obj.getCode());
			  objPer.setName(obj.getName());
			  
			  key=key+obj.getImonth();
			  objPer.getGroupList().add(obj.getImonth());
			  objPer.setImonth(obj.getImonth());
			  
			  if (smode.equals("1"))  //区分
			  {
				  key=key+obj.getPlaneOcean();
				  objPer.getGroupList().add(obj.getPlaneOceanTrl());
				  objPer.setPlaneOcean(obj.getPlaneOcean());				  
			  }
			  
			  if (soutIn.equals("1"))  //区分
			  {
				  key=key+obj.getOutIn().toString();
				  objPer.getGroupList().add(obj.getOutInTrl());
				  objPer.setOutIn(obj.getOutIn());				  
			  }
			  


			  if (perMap.containsKey(key))
			  {
				  objPer=(ObjPerformance)perMap.get(key);	
				  objPer.setInumber(objPer.getInumber()+obj.getInumber());
				  objPer.setProfit(objPer.getProfit().add(obj.getProfit()));
			  }
			  else
			  {
				  objPer.setInumber(obj.getInumber());
				  objPer.setProfit(obj.getProfit());
				  perMap.put(key, objPer);
			  }
	  	  }
		
		
		  //整理HashMap
		//首先要按照Imonth排序
		Object[] secondList=perMap.values().toArray();
		List thridList=Arrays.asList(secondList);
		List fourList=new ArrayList();
		//下面执行计算同比和环比
		for (Object obj:thridList)
		{
			ObjPerformance objper=(ObjPerformance)obj;			
			HbnHsql hwhere=new HbnHsql();
			HbnHsql hwheress=new HbnHsql();
			HbnHsql hwherepos=new HbnHsql();
			hwhere.addWhereCell("a.personCode", objper.getCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			hwheress.addWhereCell("a.personCode", objper.getCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			if (!objper.getPlaneOcean().equals(""))
			{
		       hwhere.addWhereCell("a.planeOcean", objper.getPlaneOcean(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
		       hwheress.addWhereCell("a.planeOcean", objper.getPlaneOcean(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);		       
		       hwherepos.addWhereCell("a.planeOcean", objper.getPlaneOcean(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);		       
			}
			if (objper.getOutIn().intValue()!=-1)
			{
			    hwhere.addWhereCell("a.OutIn", objper.getOutIn().toString(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			    hwheress.addWhereCell("a.OutIn", objper.getOutIn().toString(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			    
			    hwherepos.addWhereCell("a.OutIn", objper.getOutIn().toString(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			    
			}
		    
			String currentMonth=objper.getImonth();
			hwherepos.addWhereCell("a.Imonth",currentMonth, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			
			
			String yyMonth=ProDateUtil.getHisMonth(currentMonth,12);
		    hwhere.addWhereCell("a.Imonth",yyMonth, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			String ssMonth=ProDateUtil.getHisMonth(currentMonth,1);
			hwheress.addWhereCell("a.Imonth",ssMonth, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			
			BusinessPerformance  yyPerformance=businessQueryService.getSalesPerformance(hwhere);
			if (yyPerformance!=null)
			{
				objper.setYyInumber(yyPerformance.getInumber());
				objper.setYyProfit(yyPerformance.getProfit());
			}

			BusinessPerformance  ssPerformance=businessQueryService.getSalesPerformance(hwheress);
			if (ssPerformance!=null)
			{
				objper.setSsInumber(ssPerformance.getInumber());
				objper.setSsProfit(ssPerformance.getProfit());
			}
			
			ObjPerformancePK  objPerformancePK=businessQueryService.getSalesPerformancePK(hwherepos,objper.getCode());
			if (objPerformancePK!=null)
			{
				objper.setYourPos(objPerformancePK.getYourPos());
				objper.setTopToYou(objPerformancePK.getTopToYou());
				objper.setTopToYouProfit(objPerformancePK.getTopToYouProfit());
			}			
				
			fourList.add(objper);
		}
		
		
		PerformanceIMonthComparator imonthComparator=new PerformanceIMonthComparator();
		Collections.sort(fourList, imonthComparator);		
		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", fourList);		
		
		
	
		
		return "searchp";
	}		
	
	
	
	private void init()
	{
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		if (!((up.getAccessDataLevelByStruts2().intValue()==DelmarConst.ACCESS_LEVEL_ALL)||
				(up.getAccessDataLevelByStruts2().intValue()==DelmarConst.ACCESS_LEVEL_ORG)))
		{
			FacesUtils.setValueInHashtableOfSession("orgId",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("org",up.getLoginOrgName());
			FacesUtils.setValueInHashtableOfSession("orgVisible"," false");
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible"," true");			
		}		
		
		/*
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("accessString", up.getOrgAccessString());
		orgs=orgService.selectByExample(param);
		*/
		

		busModeList=datadictService.getDatadictTrlByValue(DatadictType.MODE,ur.getLocale().toString());		

	}
	
	private void initInvoice()
	{
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		
		creditDebitList=datadictService.getDatadictTrlByValue(DatadictType.CREDITDEBIT,ur.getLocale().toString());		
		invoiceStatusList=datadictService.getDatadictTrlByValue(DatadictType.INVOICESTATUS,ur.getLocale().toString());
	}
	
	/** 
	 * @Title:        searchShipment 
	 * @Description:  查询提单
	 * @param:        @param fileNo
	 * @param:        @param flightDateBegin
	 * @param:        @param flightDateEnd
	 * @param:        @param cwConfirmDateBegin
	 * @param:        @param cwConfirmDateEnd
	 * @param:        @param shipperName
	 * @param:        @param consignName
	 * @param:        @param mode
	 * @param:        @return    
	 * @return:       List<BusinessForwarder>    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:26:41 
	 */
	private List<BusinessForwarder> searchShipment(String orgId,String fileNo,String flightDateBegin,String flightDateEnd,
			String cwConfirmDateBegin,String cwConfirmDateEnd,
			String shipperName,String consignName,String mode)
	{

		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessForwarder.class);
		
		
		//直分单
		
	    hbmwhere.addWhereCell("a.beZhiDan", "1,2", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		
	    hbmwhere.addWhereCell("a.beNotStat", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
		hbmwhere.addWhereCell("a.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        if (!orgId.equals(""))
        {
      		hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgId), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        }  		

        
		if(StringUtil.isNotEmpty(fileNo))
		{
		  hbmwhere.addWhereCell("a.fileNo", fileNo, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}
		if(StringUtil.isNotEmpty(flightDateBegin))
		{
		  hbmwhere.addWhereCell("a.flightDate", flightDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);		  
		}

		if(StringUtil.isNotEmpty(flightDateEnd))
		{
 		    hbmwhere.addWhereCell("a.flightDate", flightDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateBegin))
		{
 		    hbmwhere.addWhereCell("a.cwConfirmDate", flightDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateEnd))
		{
			hbmwhere.addWhereCell("a.cwConfirmDate", flightDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(shipperName))
		{
		  hbmwhere.addWhereCell("a.shipperName", shipperName, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}	
		
		if(StringUtil.isNotEmpty(consignName))
		{
		  hbmwhere.addWhereCell("a.consignName", consignName, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}			
	


        
		
		if (hbmwhere.hasWhereCell())
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}

		
		
		List<BusinessForwarder> businessList=businessQueryService.searchBusinessList(hbmwhere,mode);

		return 	businessList;		
	}
	
	
	
	/** 
	 * @Title:        searchInvoice 
	 * @Description:  查询账单
	 * @param:        @param fileNo
	 * @param:        @param hawb
	 * @param:        @param flightDateBegin
	 * @param:        @param flightDateEnd
	 * @param:        @param cwConfirmDateBegin
	 * @param:        @param cwConfirmDateEnd
	 * @param:        @param invoiceObject
	 * @param:        @param creditDebitId
	 * @param:        @param invoiceStatusId
	 * @param:        @param mode
	 * @param:        @return    
	 * @return:       List<BusinessInvoice>    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年5月27日 下午3:26:21 
	 */
	private List<BusinessInvoice> searchInvoice(String orgId,String fileNo,String hawb,String flightDateBegin,String flightDateEnd,
			String cwConfirmDateBegin,String cwConfirmDateEnd,
			String invoiceObject,String creditDebitId,String invoiceStatusId,String mode)
	{
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessInvoice.class);

		hbmwhere.addWhereCell("b.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		
        if (!orgId.equals(""))
        {
      		hbmwhere.addWhereCell("b.companyId", convertCargoCompanyId(orgId), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        }  
        
		if(StringUtil.isNotEmpty(fileNo))
		{
			hbmwhere.addWhereCell("b.fileNo", fileNo, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}
		
		if(StringUtil.isNotEmpty(hawb))
		{
			hbmwhere.addWhereCell("b.hawb", hawb, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}
		
		if(StringUtil.isNotEmpty(flightDateBegin))
		{
			hbmwhere.addWhereCell("b.flightDate", flightDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);		  
	  
		}

		if(StringUtil.isNotEmpty(flightDateEnd))
		{
			hbmwhere.addWhereCell("b.flightDate", flightDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateBegin))
		{
			hbmwhere.addWhereCell("b.cwConfirmDate", flightDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateEnd))
		{
			hbmwhere.addWhereCell("b.cwConfirmDate", flightDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(invoiceObject))
		{
			hbmwhere.addWhereCell("a.cusCodeName", invoiceObject, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}	
		
		if(StringUtil.isNotEmpty(creditDebitId))
		{
			if (!(creditDebitId.indexOf("CREDIT")>-1&&creditDebitId.indexOf("DEBIT")>-1))
			{
				if (creditDebitId.indexOf("CREDIT")>-1)
				{
					hbmwhere.addWhereCell("a.recedeal", "-1", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
				}
				else
				{
					hbmwhere.addWhereCell("a.recedeal", "1", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
				}
			}
		}	
		
		
		if(StringUtil.isNotEmpty(invoiceStatusId))
		{
			if (!(invoiceStatusId.indexOf("YESSETTLED")>-1&&invoiceStatusId.indexOf("NOSETTLED")>-1))
			{
				if (creditDebitId.indexOf("YESSETTLED")>-1)
				{
					hbmwhere.addWhereCell("a.balance", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
				}
				else
				{
					hbmwhere.addWhereCell("a.balance", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_NOTEQ,HbnHsql.REL_TYPE_AND);
				}
			}
		}			
			
	
		

		
		
		
		if (hbmwhere.hasWhereCell())
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}

		
		
		List<BusinessInvoice> invoiceList=businessQueryService.searchBusinessInvoiceList(hbmwhere,mode);
		return 	invoiceList;		
	}	
		

	private String getModuleName() {
		return "business";
	}

	/**
	 * @return the orgs
	 */
	public List<Org> getOrgs() {
		return orgs;
	}

	/**
	 * @param orgs the orgs to set
	 */
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}

	public List<DatadictTrl> getBusModeList() {
		return busModeList;
	}

	public void setBusModeList(List<DatadictTrl> busModeList) {
		this.busModeList = busModeList;
	}

	public List<DatadictTrl> getCreditDebitList() {
		return creditDebitList;
	}

	public void setCreditDebitList(List<DatadictTrl> creditDebitList) {
		this.creditDebitList = creditDebitList;
	}



	public List<DatadictTrl> getInvoiceStatusList() {
		return invoiceStatusList;
	}

	public void setInvoiceStatusList(List<DatadictTrl> invoiceStatusList) {
		this.invoiceStatusList = invoiceStatusList;
	}
	
	private String getCargoSalesCode()
	{    
		if (getCurrentUserThird("CargoProSales").equals(""))
		  return "0";
	     else
		   return getCurrentUserThird("CargoProSales");
	}
	
	private String convertCargoCompanyId(String orgIds)
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		List<UserorgAccess> userorgListStr=(List<UserorgAccess>)request.getSession().getAttribute("userorgaccessList");
		
		if (orgIds.equals(""))
			return "";
		
		StringBuffer cargoCompanyIds=new StringBuffer();
		orgIds=","+orgIds+",";
		for (UserorgAccess userorgaccess:userorgListStr)
		{
			if (orgIds.indexOf(","+userorgaccess.getOrgId().toString()+",")>-1)
				cargoCompanyIds.append(userorgaccess.getOrg().getValue()+",");
		}
		
		if (cargoCompanyIds.length()>0)
		   cargoCompanyIds.deleteCharAt(cargoCompanyIds.length()-1);
		
		return cargoCompanyIds.toString();
	}

}

