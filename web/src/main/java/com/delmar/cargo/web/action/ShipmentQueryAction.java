package com.delmar.cargo.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.service.DatadictService;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
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
public class ShipmentQueryAction  extends CargoCoreAction  {

	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private BusinessQueryService businessQueryService;	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CargoPro提单信息");
	}
	
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
		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);				
		
		
		
		List list=new ArrayList();
		
		if (mode.equals("")||mode.indexOf("Ocean")>-1)
			list.addAll(searchShipment(orgIds,beGroup,userName,fileNo,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,shipperName,consignName,"Ocean"));
		
		if (mode.equals("")||mode.indexOf("Air")>-1)		
			list.addAll(searchShipment(orgIds,beGroup,userName,fileNo,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,shipperName,consignName,"Air"));
		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);		
		
		
	
		
		return "searchs";
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
	private List<BusinessForwarder> searchShipment(String orgIds,String beGroup,String userName,String fileNo,String flightDateBegin,String flightDateEnd,
			String cwConfirmDateBegin,String cwConfirmDateEnd,
			String shipperName,String consignName,String mode)
	{

		
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessForwarder.class);
		
		
		//直分单
		
	    hbmwhere.addWhereCell("a.beZhiDan", "1,2", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		
	    hbmwhere.addWhereCell("a.beNotStat", "0", HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
	    
	    
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
 		    hbmwhere.addWhereCell("a.cwConfirmDate", cwConfirmDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateEnd))
		{
			hbmwhere.addWhereCell("a.cwConfirmDate", cwConfirmDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
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
	
	
		

	public String getModuleName() {
		return "business";
	}

	

	

}

