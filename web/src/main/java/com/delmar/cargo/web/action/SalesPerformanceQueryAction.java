package com.delmar.cargo.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.ObjPerformancePK;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.cargo.service.FileTableService;
import com.delmar.cargo.service.ReportFareInvoiceService;
import com.delmar.cargo.service.TrustContextService;
import com.delmar.cargo.web.comparator.PerformanceIMonthComparator;
import com.delmar.cargo.web.pub.ObjPerformance;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Org;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ResourceMessage;
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
public class SalesPerformanceQueryAction  extends CargoCoreAction  {

	
	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private BusinessQueryService businessQueryService;	
			
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CargoPro销售业绩");
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

		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listp";
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
		if (outInOut==null)
			outInOut="1";		
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		if (outInIn==null)
			outInIn="0";
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
		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				

	
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);		
		

		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessPerformance.class);	
		hbmwhere.addWhereCell("a.PerforType", "SALES", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);		
		
	    int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
	        if (!orgIds.equals(""))
	        {
	      		hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	        }  		
		}
		else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			 hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);			
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			if (beGroup.equals("true"))
  			    hbmwhere.addWhereCell("a.personCode", getCargoProSalesGroup(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			else
				hbmwhere.addWhereCell("a.PersonCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		else
		{
		   hbmwhere.addWhereCell("a.PersonCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(userName))
		{
			hbmwhere.addWhereCell("a.PersonCode", getCargoProSalesBySysUserName(userName), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
		}			
		
	
		//直分单	
        String exportImport="";
        if (outInOut.equals("1")&&outInIn.equals("0"))
        	exportImport="export,import";
        else if (outInOut.equals("0")&&outInIn.equals("1"))
        	exportImport="export,import";
        else if  (outInOut.equals("0"))
        	hbmwhere.addWhereCell("a.outIn", outInOut, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
        else if  (outInIn.equals("1"))
        	 hbmwhere.addWhereCell("a.outIn", outInIn, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
        
        


		
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
			
			hwhere.addWhereCell("a.PerforType", "SALES", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			hwheress.addWhereCell("a.PerforType", "SALES", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);			
			hwherepos.addWhereCell("a.PerforType", "SALES", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			
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
		
		
		JSChartsMain chart=new JSChartsMain();
		JSChartsItem currentItem=new JSChartsItem("line","first line");
		JSChartsItem yyItem=new JSChartsItem("line","second line");
		
		for (Object obj:fourList)
		{
			ObjPerformance objper=(ObjPerformance)obj;			
			currentItem.AddOneData(objper.getImonth(),objper.getProfit().toBigInteger());
			if (objper.getYyProfit()!=null)
			   yyItem.AddOneData(objper.getImonth(),objper.getYyProfit().toBigInteger());
			else
			   yyItem.AddOneData(objper.getImonth(),0);
			 
		}
		
		chart.getDatasets().add(currentItem);
		chart.getDatasets().add(yyItem);		
		chart.setOptionset(null);
		Gson gson=new Gson();
		String json = gson.toJson(chart);
		
		FacesUtils.setValueInHashtableOfSession("mydatajson",json);
		

		
	
		
		return "searchp";
	}		
	
	
	@Override
	public void init()
	{
		
		super.init();
	}
	



	public String getModuleName() {
		return "business";
	}


	public List<DatadictTrl> getBusModeList() {
		return busModeList;
	}

	public void setBusModeList(List<DatadictTrl> busModeList) {
		this.busModeList = busModeList;
	}




}

