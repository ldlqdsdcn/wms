package com.delmar.cargo.web.action;

import java.math.BigDecimal;
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
import com.delmar.cargo.model.BusinessClient;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.cargo.web.comparator.BusinessClientProfitComparator;
import com.delmar.cargo.web.comparator.BusinessClientVolumeComparator;
import com.delmar.cargo.web.comparator.CustomerBusinessLastEtdComparator;
import com.delmar.cargo.web.pub.ObjCustomerBusiness;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;



/**
 *@ClassName:   BusinessQueryAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月27日 下午3:25:22
 * @version V2.0
 */
public class CustomerQueryAction  extends CargoCoreAction  {


	private List<DatadictTrl> clientTypeList;   //客户分析类型	
	private List<DatadictTrl> haveBusinessList;   //已走货未走货
	private List<DatadictTrl> sortTypeList;   //排序类型
	protected List<DatadictTrl> trustTypeList;   //委托类型

	
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	private BusinessQueryService businessQueryService;	

	
			
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CargoPro客户档案记录");
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
		

		FacesUtils.setValueInHashtableOfSession("haveBusiness", "no");
		

		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listc";
	}	
	

	public String listtop()
	{
		HttpServletRequest request=ServletActionContext.getRequest();		
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
		FacesUtils.setValueInHashtableOfSession("outInOut", "0");
		FacesUtils.setValueInHashtableOfSession("outInIn", "1");	
		FacesUtils.setValueInHashtableOfSession("topNumber", "10");
		FacesUtils.setValueInHashtableOfSession("clientType", "china");
		FacesUtils.setValueInHashtableOfSession("sortType", "profit");	
		
	
	
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listtop";
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
		if (outInOut==null)
			outInOut="1";		
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		if (outInIn==null)
			outInIn="0";
		FacesUtils.setValueInHashtableOfSession("outInIn", outInIn);		
		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
		String trustType=request.getParameter("trustTypeId");
		FacesUtils.setValueInHashtableOfSession("trustTypeId", trustType);
		
		
		String smode=request.getParameter("smode");
		if (smode==null)
			smode="0";		
		FacesUtils.setValueInHashtableOfSession("smode", mode);
		
		

		String cusName=request.getParameter("cusName");
		FacesUtils.setValueInHashtableOfSession("cusName", cusName);

		
		String haveBusiness=request.getParameter("haveBusiness");
		if (haveBusiness==null)
			haveBusiness="no";
		FacesUtils.setValueInHashtableOfSession("haveBusiness", haveBusiness);
		
		
		String daysBegin=request.getParameter("daysBegin");
		if (daysBegin==null)
			daysBegin="30";
		FacesUtils.setValueInHashtableOfSession("daysBegin", daysBegin);
		
		String daysEnd=request.getParameter("daysEnd");
		if (daysEnd==null)
			daysEnd="30";
		FacesUtils.setValueInHashtableOfSession("daysEnd", daysEnd);
		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				

		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(CustomerBusiness.class);	
		
    			
		
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
  			    hbmwhere.addWhereCell("a.SalesCode", getCargoProSalesGroup(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			else
				hbmwhere.addWhereCell("a.SalesCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		else
		{
		   hbmwhere.addWhereCell("a.SalesCode", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(userName))
		{
			hbmwhere.addWhereCell("a.SalesCode", getCargoProSalesBySysUserName(userName), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
		}		
		
		
		if(StringUtil.isNotEmpty(trustType))
		{
			hbmwhere.addWhereCell("a.trustType", trustType, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
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
        
		if(StringUtil.isNotEmpty(cusName))
		{
		  hbmwhere.addWhereCell("b.cusName", cusName, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		  
		}
		
		if (haveBusiness.equalsIgnoreCase("no"))  //未走货
		{
			String hisDate=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd));
			hbmwhere.addWhereCell("a.lastEtd", hisDate, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
			String hisDateBegin=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd)+30);
			hbmwhere.addWhereCell("a.lastEtd", hisDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
		} else if (haveBusiness.equalsIgnoreCase("yes"))   //走货
		{
			String hisDateEnd=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd));
			String hisDateBegin=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysBegin));
			hbmwhere.addWhereCell("a.lastEtd", hisDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
			hbmwhere.addWhereCell("a.lastEtd", hisDateEnd, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
			
		}else if (haveBusiness.equalsIgnoreCase("new"))   //新走货
		{
			String hisDateBegin=ProDateUtil.getShortHisDateStr(Integer.parseInt(daysEnd));
			hbmwhere.addWhereCell("a.firstEtd", hisDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
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
			  objPer.setSalesCode(obj.getSalesCode());
			  objPer.setSalesName(obj.getSalesName());
			  
			  
			  
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
	
	
	public String searchcargo()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		
		String[] opsCodeArray=request.getParameterValues("ids");
		StringBuffer opsCodes = new StringBuffer();
		for (String s:opsCodeArray)
		{
			opsCodes.append("'").append(s).append("'").append(",");
		}
		opsCodes.deleteCharAt(opsCodes.length()-1);
		
		request.getSession().setAttribute("opsCodes", opsCodes.toString());
		
		
		String days=request.getParameter("days");
		request.getSession().setAttribute("days", days);
		
		return "searchcargo";
	}
	

	
	/** 
	 * @Title:        searchtop 
	 * @Description:  查询Top多少位的
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月29日 下午4:29:52 
	 */
	public String searchtop()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();

	
		
		String outInOut=request.getParameter("outInOut");
		if (outInOut==null)
			outInOut="1";		
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		if (outInIn==null)
			outInIn="0";
		FacesUtils.setValueInHashtableOfSession("outInIn", outInIn);	
		
		
		String sortType=request.getParameter("sortType");
		FacesUtils.setValueInHashtableOfSession("sortType", sortType);		
		
		
		String topNumber=request.getParameter("topNumber");
		FacesUtils.setValueInHashtableOfSession("topNumber", topNumber);
		int topNumberInt=Integer.parseInt(topNumber) ;
		
		
		String mode=request.getParameter("busModeId");
		FacesUtils.setValueInHashtableOfSession("busModeId", mode);
		
		String trustType=request.getParameter("trustTypeId");
		FacesUtils.setValueInHashtableOfSession("trustTypeId", trustType);		
		
		String clientType=request.getParameter("clientType");
		FacesUtils.setValueInHashtableOfSession("clientType", clientType);
		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);

		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);	
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				

		
		
		String monthBegin=request.getParameter("monthBegin");
		if (monthBegin==null)
			monthBegin=ProDateUtil.getShortHisDateStr(0).substring(0,7);
		
		FacesUtils.setValueInHashtableOfSession("monthBegin", monthBegin);
		monthBegin=monthBegin+"-01";
		
		String monthEnd=request.getParameter("monthEnd");
		if (monthEnd==null)
			monthEnd=ProDateUtil.getShortHisDateStr(0).substring(0,7);

		FacesUtils.setValueInHashtableOfSession("monthEnd", monthEnd);
		monthEnd=monthEnd+"-31 24";
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(CustomerBusiness.class);		
		
		
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
  			    hbmwhere.addWhereCell("a.Bussiness", getCargoProSalesGroup(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			else
				hbmwhere.addWhereCell("a.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		else
		{
		   hbmwhere.addWhereCell("a.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(userName))
		{
			hbmwhere.addWhereCell("a.Bussiness", getCargoProSalesBySysUserName(userName), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
		}		
 
        
		if(StringUtil.isNotEmpty(monthBegin))
		{
		  hbmwhere.addWhereCell("a.cwConfirmDate", monthBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);		  
		}
		
		if(StringUtil.isNotEmpty(monthEnd))
		{
		  hbmwhere.addWhereCell("a.cwConfirmDate", monthEnd, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);		  
		}	
		
		if(StringUtil.isNotEmpty(trustType))
		{
			hbmwhere.addWhereCell("a.trustType", trustType, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
		}				
		
		
        String exportImport="";
        if (outInOut.equals("1")&&outInIn.equals("0"))
        	exportImport="export,import";
        else if (outInOut.equals("0")&&outInIn.equals("1"))
        	exportImport="export,import";
        else if  (outInOut.equals("0"))
        	exportImport="export";
        else if  (outInIn.equals("1"))
        	exportImport="import";
		
		
		

		String planeOcean="";	
        if (!mode.equals(""))
        {
        	planeOcean=mode.toLowerCase();
        }
        else
        {
        	planeOcean="ocean,air";
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
		
		
		

		
		List<BusinessClient> list=new ArrayList<BusinessClient>();
		

		list=businessQueryService.searchClientState(hbmwhere,exportImport,planeOcean,clientType);
		
	    List<BusinessClient> perList=new ArrayList<BusinessClient>();
	    
		HashMap perMap=new HashMap();
		//进行合并
		for (BusinessClient obj:list)
        {

			  String key=obj.getClientCode();
			  if (perMap.containsKey(key))
			  {
				  BusinessClient objPer=(BusinessClient)perMap.get(key);
				  
				  objPer.setInumber(objPer.getInumber()+obj.getInumber());
				  objPer.setCargoVolume(objPer.getCargoVolume().add(obj.getCargoVolume()));
				  objPer.setProfit(objPer.getProfit().add(obj.getProfit()));
			  }
			  else
			  {
				  perMap.put(key, obj);
			  }
	  	  }
		
		
		Object[] secondList=perMap.values().toArray();
		List thridList=Arrays.asList(secondList);
		
		
		
        if (sortType.equalsIgnoreCase("volume"))  //Volumne
        {
        	BusinessClientVolumeComparator businessClientVolumeComparator=new BusinessClientVolumeComparator();
		    Collections.sort(thridList, businessClientVolumeComparator);
        }
        else
        {
        	BusinessClientProfitComparator businessClientProfitComparator=new BusinessClientProfitComparator();
		    Collections.sort(thridList, businessClientProfitComparator);
        	
        }
        
        
        int i=0;
        BigDecimal totalProfit=new BigDecimal(0);
        BigDecimal topProfit=new BigDecimal(0);
        
        List<BusinessClient> fourlist=new ArrayList<BusinessClient>(); 
        for (Object obj:thridList)
        {
           if (i<topNumberInt)
           {
               fourlist.add((BusinessClient)obj);
               topProfit=topProfit.add(((BusinessClient)obj).getProfit());
               i++;        	   
           }
           
           totalProfit=totalProfit.add(((BusinessClient)obj).getProfit());

        }
        
        
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", fourlist);	
		
		
		double profitPercent=0;
		if (totalProfit.intValue()>0)
			profitPercent=topProfit.divide(totalProfit,4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).doubleValue();
		
		
		FacesUtils.setValueInHashtableOfSession("totalNumber", thridList.size());
		FacesUtils.setValueInHashtableOfSession("profitMargin", profitPercent);		
		
		return "searchtop";
	}		
	
	
	
	@Override
	public void init()
	{

		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","group");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());
			FacesUtils.setValueInHashtableOfSession("beGroup","false");
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		
		
        super.init();
        
		clientTypeList=datadictService.getDatadictTrlByValue(DatadictType.CLIENTTYPE,ur.getLocale().toString());
		haveBusinessList=datadictService.getDatadictTrlByValue(DatadictType.HAVEBUSINESS,ur.getLocale().toString());
		sortTypeList=datadictService.getDatadictTrlByValue(DatadictType.SORTTYPE,ur.getLocale().toString());
		trustTypeList=datadictService.getDatadictTrlByValue(DatadictType.TRUSTTYPE,ur.getLocale().toString(),up.getLoginClientId());

	}
	


	public String getModuleName() {
		return "business";
	}


	public List<DatadictTrl> getClientTypeList() {
		return clientTypeList;
	}


	public void setClientTypeList(List<DatadictTrl> clientTypeList) {
		this.clientTypeList = clientTypeList;
	}


	public List<DatadictTrl> getHaveBusinessList() {
		return haveBusinessList;
	}


	public void setHaveBusinessList(List<DatadictTrl> haveBusinessList) {
		this.haveBusinessList = haveBusinessList;
	}


	public List<DatadictTrl> getSortTypeList() {
		return sortTypeList;
	}


	public void setSortTypeList(List<DatadictTrl> sortTypeList) {
		this.sortTypeList = sortTypeList;
	}



	public List<DatadictTrl> getTrustTypeList() {
		return trustTypeList;
	}



	public void setTrustTypeList(List<DatadictTrl> trustTypeList) {
		this.trustTypeList = trustTypeList;
	}

	
}

