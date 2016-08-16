package com.delmar.cargo.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.service.DatadictService;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.common.model.BusCoreModel;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.web.model.ObjStastics;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;



/**
 *@ClassName:   BusinessQueryAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月27日 下午3:25:22
 * @version V2.0
 */
public class CustomerStateAction  extends CargoCoreAction  {

	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private BusinessQueryService businessQueryService;	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CargoPro客户统计信息");
	}
	
	public String lists()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		init();
		
		FacesUtils.setValueInHashtableOfSession("outInOut", "0");
		FacesUtils.setValueInHashtableOfSession("outInIn", "1");
		FacesUtils.setValueInHashtableOfSession("customerGroup", "true");			

		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);		
		
		return "lists";
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
		
		
		
		String outInOut=request.getParameter("outInOut");
		if (outInOut==null)
			outInOut="1";		
		FacesUtils.setValueInHashtableOfSession("outInOut", outInOut);

		String outInIn=request.getParameter("outInIn");
		if (outInIn==null)
			outInIn="0";
		FacesUtils.setValueInHashtableOfSession("outInIn", outInIn);				
		
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
		
				
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);				
		
		
        String exportImport="";
        if (outInOut.equals("1")&&outInIn.equals("0"))
        	exportImport="export,import";
        else if (outInOut.equals("0")&&outInIn.equals("1"))
        	exportImport="export,import";
        else if  (outInOut.equals("0"))
        	exportImport="export";
        else if  (outInIn.equals("1"))
        	exportImport="import";
        	
        

		
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>();
		
		if (mode.equals("")||mode.indexOf("Ocean")>-1)
			list.addAll(searchShipment(orgIds,beGroup,userName,exportImport,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,"Ocean"));
		
		if (mode.equals("")||mode.indexOf("Air")>-1)		
			list.addAll(searchShipment(orgIds,beGroup,userName,exportImport,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,"Air"));
		
		
		//下面针对查询出来的提单数据进行客户级别的统计
		//只统计的客户的票数/Teu数/毛利数
		//客户,国家,港口
		
		String orgGroup=request.getParameter("orgGroup");
		if (orgGroup==null)
			orgGroup="";
		FacesUtils.setValueInHashtableOfSession("orgGroup", orgGroup);	
		
		
		String userGroup=request.getParameter("userGroup");
		if (userGroup==null)
			userGroup="";
		FacesUtils.setValueInHashtableOfSession("userGroup", userGroup);	
		
		String customerGroup=request.getParameter("customerGroup");
		if (customerGroup==null)
			customerGroup="false";		
		else
			customerGroup="true";
		FacesUtils.setValueInHashtableOfSession("customerGroup", customerGroup);	
		
		
		String countryGroup=request.getParameter("countryGroup");
		if (countryGroup==null)
			countryGroup="false";		
		else
			countryGroup="true";
		FacesUtils.setValueInHashtableOfSession("countryGroup", countryGroup);	
		
		String portGroup=request.getParameter("portGroup");
		if (portGroup==null)
			portGroup="false";		
		else
			portGroup="true";
		FacesUtils.setValueInHashtableOfSession("portGroup", portGroup);		
		
		
		
		List cusStaticsList=staticsList(list,orgGroup,userGroup,customerGroup,countryGroup,portGroup);
			

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", cusStaticsList);		
		
		
	
		
		return "searchs";
	}	
	
	
	private List<ObjStastics> staticsList(List list,String orgGroup,String userGroup,String customerGroup,String countryGroup,String portGroup)
	{
	    List<ObjStastics> perList=new ArrayList<ObjStastics>();
	    
		HashMap perMap=new HashMap();

		for (Object oneobj:list)
        {
			 BusinessForwarder obj=(BusinessForwarder)oneobj;
			  
			  ObjStastics objPer=new ObjStastics(); 
			  String key="";			  
			  if (orgGroup.equals("yes"))  //区分
			  {
				  key=key+"-"+obj.getCompanyId();
				  objPer.getGroupList().add(obj.getCompanyId());
			  }
			  
			  if (userGroup.equals("yes"))  //区分
			  {
				  key=key+"-"+obj.getBusiness();
				  objPer.getGroupList().add(obj.getBusinessName());
			  }
			  
			  if (customerGroup.equals("true"))  //区分
			  {
				  if (obj.getOutIn().intValue()==0)
				  {
				    key=key+"-"+obj.getShipperCode();
				    objPer.getGroupList().add(obj.getShipperName()+"("+obj.getShipperCode()+")");
				  }
				  else
				  {
					    key=key+"-"+obj.getConsignCode();
					    objPer.getGroupList().add(obj.getConsignName()+"("+obj.getConsignCode()+")");
					  
				  }
			  }
			  
			  if (countryGroup.equals("true"))  //区分
			  {
				  if (obj.getOutIn().intValue()==0)
				  {
				    key=key+"-"+obj.getArrivePortCountry();
				    objPer.getGroupList().add(obj.getArrivePortCountry());
				  }
				  else
				  {
					 key=key+"-"+obj.getAirPortFromCountry();
					 objPer.getGroupList().add(obj.getAirPortFromCountry());
				  }
			  }			
			  
			  if (portGroup.equals("true"))  //区分
			  {
				  if (obj.getOutIn().intValue()==0)
				  {
				    key=key+"-"+obj.getArrivePort();
				    objPer.getGroupList().add(obj.getArrivePort());
				  }
				  else
				  {
					 key=key+"-"+obj.getAirPortFrom();
					 objPer.getGroupList().add(obj.getAirPortFrom());
				  }
			  }					  
			  
			  
			  if (key.equals(""))
			  {
				  key="all";
			  }
			  
			  if (perMap.containsKey(key))
			  {
				  objPer=(ObjStastics)perMap.get(key);	
				  objPer.addCount();	
				  if (obj.getPlaneOcean().equals("Ocean"))
					  objPer.addOceanTeu(obj.getTeuNum());
				  else
					  objPer.addAirVolume(obj.getGoodsChargeWeight());
				  
				  objPer.addProfit(obj.getProfit());
			  }
			  else
			  {
				  objPer.setCount(1);
				  if (obj.getPlaneOcean().equals("Ocean"))
					  objPer.setOceanTeu(obj.getTeuNum());
				  else
					  objPer.setAirVolume(obj.getGoodsChargeWeight());
				  
				  objPer.setProfit(obj.getProfit());
				  
				  perMap.put(key, objPer);
			  }
	  	  }

				
		Object[] secondList=perMap.values().toArray();
		List thirdList=Arrays.asList(secondList);
		
		return thirdList;
	}
		
	
	
	@Override
	public void init()
	{
        super.init();
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
	private List<BusinessForwarder> searchShipment(String orgIds,String beGroup,String userName,String exportImport,String flightDateBegin,String flightDateEnd,
			String cwConfirmDateBegin,String cwConfirmDateEnd,String mode)
	{

		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessForwarder.class);
		
		
		//直分单
		
	    hbmwhere.addWhereCell("a.beZhiDan", "1,2", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		
	    hbmwhere.addWhereCell("a.beNotStat", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
	    hbmwhere.addWhereCell("a.TrustType", "F/H,S/L", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	    
	    if (!exportImport.equals("export,import"))
	    {
	    	if (exportImport.equals("export"))
	    		hbmwhere.addWhereCell("a.outIn", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
	    	else
	    		hbmwhere.addWhereCell("a.outIn", "1", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
	    
	    int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)	{
	        if (!orgIds.equals(""))
	        {
	      		hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	        }  		
		}
		else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			 hbmwhere.addWhereCell("a.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);			
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
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
 		    hbmwhere.addWhereCell("a.cwConfirmDate", cwConfirmDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateEnd))
		{
			hbmwhere.addWhereCell("a.cwConfirmDate", cwConfirmDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
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
	
	
		

	public String getModuleName() {
		return "business";
	}

	

	

}

