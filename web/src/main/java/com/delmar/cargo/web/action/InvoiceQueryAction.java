package com.delmar.cargo.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
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
public class InvoiceQueryAction  extends CargoCoreAction  {

	
	private List<DatadictTrl> creditDebitList;   //应收应付
	private List<DatadictTrl> invoiceStatusList;   //收支状态
	
	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private BusinessQueryService businessQueryService;	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CargoPro账单记录");
	}
	
	
	public String listi()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
		
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
		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", null);				
		
		return "listi";
	}
	
	
	public String listdebit()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}		
		init();
		
		List list=new ArrayList();
		list.addAll(searchStateDebit("","","","2007-01-01","2099-01-01"));		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);				
		
		return "listdebit";
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
		
	
		String paymentDay=request.getParameter("paymentDay");
		FacesUtils.setValueInHashtableOfSession("paymentDay", paymentDay);		
		
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
			list.addAll(searchInvoice(orgIds,beGroup,userName,fileNo,hawb,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,invoiceObject,creditDebitId,invoiceStatusId,paymentDay,"Ocean"));
		
		if (mode.equals("")||mode.indexOf("Air")>-1)		
			list.addAll(searchInvoice(orgIds,beGroup,userName,fileNo,hawb,flightDateBegin,flightDateEnd,
					cwConfirmDateBegin,cwConfirmDateEnd,invoiceObject,creditDebitId,invoiceStatusId,paymentDay,"Air"));
		

		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);		
		
		
	
		
		return "searchi";
	}	
	

	
	/** 
	 * @Title:        searchdebit 
	 * @Description:  TODO
	 * @param:        @return    
	 * @return:       String    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月29日 下午1:38:57 
	 */
	public String searchdebit()
	{
		if(!(PrivilegeOperator.isView()))
		{
			return NOPRIVILEGE;
		}
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		init();
		
		String flightDateBegin=request.getParameter("flightDateBegin");
		FacesUtils.setValueInHashtableOfSession("flightDateBegin", flightDateBegin);
		String flightDateEnd=request.getParameter("flightDateEnd");
		FacesUtils.setValueInHashtableOfSession("flightDateEnd", flightDateEnd);
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);

		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);				
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);				
		
	

		
		List list=new ArrayList();
		

		list.addAll(searchStateDebit(orgIds,beGroup,userName,flightDateBegin,flightDateEnd));
		

		

		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);		
		
		
	
		
		return "searchdebit";
	}	
		
	
	@Override
	public void init()
	{
		super.init();
		

		busModeList=datadictService.getDatadictTrlByValue(DatadictType.MODE,ur.getLocale().toString());		

		creditDebitList=datadictService.getDatadictTrlByValue(DatadictType.CREDITDEBIT,ur.getLocale().toString());		
		invoiceStatusList=datadictService.getDatadictTrlByValue(DatadictType.INVOICESTATUS,ur.getLocale().toString());
		
		FacesUtils.setValueInHashtableOfSession("paymentDay", 0);
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
	private List<BusinessInvoice> searchInvoice(String orgIds,String beGroup,String userName,String fileNo,String hawb,String flightDateBegin,String flightDateEnd,
			String cwConfirmDateBegin,String cwConfirmDateEnd,
			String invoiceObject,String creditDebitId,String invoiceStatusId,String paymentDay,String mode)
	{
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(BusinessInvoice.class);


	    int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
	        if (!orgIds.equals(""))
	        {
	      		hbmwhere.addWhereCell("b.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	        }  		
		}
		else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			 hbmwhere.addWhereCell("b.companyId", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);			
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			if (beGroup.equals("true"))
  			    hbmwhere.addWhereCell("b.Bussiness", getCargoProSalesGroup(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			else
				hbmwhere.addWhereCell("b.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		else
		{
		   hbmwhere.addWhereCell("b.Bussiness", getCargoSalesCode(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
		}
		
		if(StringUtil.isNotEmpty(userName))
		{
			hbmwhere.addWhereCell("b.Bussiness", getCargoProSalesBySysUserName(userName), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);		  
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
			hbmwhere.addWhereCell("b.cwConfirmDate", cwConfirmDateBegin, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);			
		}

		if(StringUtil.isNotEmpty(cwConfirmDateEnd))
		{
			hbmwhere.addWhereCell("b.cwConfirmDate", cwConfirmDateEnd+" 24", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
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
			
	
		
		if(!paymentDay.equals("0"))
		{
			hbmwhere.addWhereCell("DATEDIFF(day,b.flightDate,getDate())", paymentDay, HbnHsql.VALUE_TYPE_INT, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);		  
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
		


	/** 
	 * @Title:        searchStateDebit 
	 * @Description:  TODO
	 * @param:        @param orgId
	 * @param:        @param flightDateBegin
	 * @param:        @param flightDateEnd
	 * @param:        @return    
	 * @return:       List<BusinessInvoice>    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月29日 下午1:40:19 
	 */
	private List<BusinessInvoice> searchStateDebit(String orgIds,String beGroup,String userName,String flightDateBegin,String flightDateEnd)
	{
		FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		List<BusinessInvoice> invoiceList=businessQueryService.searchBusinessInvoiceState(getCargoSalesCode(),flightDateBegin,flightDateEnd+" 24");
		return 	invoiceList;		
	}	
	
	
	public String getModuleName() {
		return "business";
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
	



}

