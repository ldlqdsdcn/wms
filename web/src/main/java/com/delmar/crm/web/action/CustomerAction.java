/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 ******************************************************************************/
package com.delmar.crm.web.action;

import com.delmar.base.model.CityTrl;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.CityService;
import com.delmar.base.service.DatadictService;
import com.delmar.common.exception.ValidateException;
import com.delmar.common.pub.ValidateCommon;
import com.delmar.core.DelmarConst;
import com.delmar.core.service.CoreCreatCodeService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.crm.model.CustomerTrace;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.proxy.pub.CustomerProxyManage;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.CustomerTraceService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.web.comparator.CustomerLastLinkTimeComparator;
import com.delmar.crm.web.model.CreatCodeConst;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author 刘大磊 2015-01-23 17:50:29R
 */
public class CustomerAction extends CoreEditPrivAction {
	private Customer  customer;
	private CustomerExtra customerExter;


	private Linkman linkman;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	private List<DatadictTrl> custypeList;  //直客还是同行
	private List<DatadictTrl> cussourceList;  //客户来源
	private List<DatadictTrl> industryList;  //所属行业	
	private List<DatadictTrl> statusList;   //客户所处状态
	
	private String oldOpsCode;   //旧的关键代码
	private Integer oldStatusId; //旧的状态	
	
	@Autowired
	private OrgService orgService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private CustomerExterService customerExterService;
	@Autowired
	private CityService cityService;
	@Autowired
	private UserService userService;
	@Autowired
	private CoreCreatCodeService coreCreatCodeService;	
	@Autowired
	private CustomerTraceService customerTraceService;
	
	
	
	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
			
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CRM客户档案");
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#list()
	 */
	@Override
	public String list() {
		
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

		
		
		
		return super.list();
	}
	
	private void init()
	{
		
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		

				
		
		cussourceList=datadictService.getDatadictTrlByValue(DatadictType.CUSSOURCE,ur.getLocale().toString(),up.getLoginClientId());
		industryList=datadictService.getDatadictTrlByValue(DatadictType.INDUSTRY,ur.getLocale().toString(),up.getLoginClientId());
		custypeList=datadictService.getDatadictTrlByValue(DatadictType.CUSTYPE,ur.getLocale().toString(),up.getLoginClientId());		
		statusList=datadictService.getDatadictTrlByValue(DatadictType.CUSSTATUS,ur.getLocale().toString(),up.getLoginClientId());

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "customer";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		customerService.deleteById(customer.getId());
		
		return this.list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		customerService.deleteCustomerList(ids);

	}
/* (non-Javadoc)
 * @see com.delmar.core.web.action.CoreEditPrivAction#deletes()
 */
@Override
public String deletes() {
	init();
	return super.deletes();
}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return customer.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
   @Override
	public void editForm() {
	    customer= customerService.selectByPrimaryKey(id);
	    oldOpsCode=customer.getOpsCode();
	    oldStatusId=customer.getStatusId();
	    
		if(customer!=null)
		{
			
			UserResource resource= (UserResource)FacesUtils.getSession().getAttribute("resource");
			CityTrl city=this.cityService.getCityTrl(resource.getLocale().toString(), customer.getCityId());
			customer.setCity(city);
			CityTrl province=this.cityService.getCityTrl(resource.getLocale().toString(), customer.getProvinceId());
			customer.setProvince(province);
			CityTrl country=this.cityService.getCityTrl(resource.getLocale().toString(), customer.getCountryId());
			customer.setCountry(country);	
			
			Org org=orgService.selectByPrimaryKey(customer.getOrgId());
			customer.setOrg(org);
			
			User user=userService.selectByPrimaryKey(customer.getUserId());
			customer.setUser(user);			
			
			User createdby=userService.selectByPrimaryKey(customer.getCreatedBy());
			customer.setCreatedByUser(createdby);
			User updateby=userService.selectByPrimaryKey(customer.getUpdatedBy());
			customer.setUpdatedByUser(updateby);
			
		
			if (customer.getId().intValue()>0)
			   linkman=	linkmanService.getLinkman(customer.getId(), "Y");
			if (customer.getId().intValue()>0)
			{
			    customerExter=customerExterService.selectByPrimaryKey(customer.getId());
			
				if (customerExter.getBusVolume()==null)
					  customerExter.setBusVolume(new BigDecimal(0));
				
				if (customerExter.getLatitude()==null)
				  customerExter.setLatitude(new BigDecimal(-1));
				
				if (customerExter.getLongtitude()==null)
				  customerExter.setLongtitude(new BigDecimal(-1));
			}
			

		}
		
		init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
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
		
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		
		
		
		
		String cuscode=request.getParameter("cusCode");
		FacesUtils.setValueInHashtableOfSession("cusCode", cuscode);
		String opsCode=request.getParameter("opsCode");
		FacesUtils.setValueInHashtableOfSession("opsCode", opsCode);
		String propLabel=request.getParameter("propLabel");
		FacesUtils.setValueInHashtableOfSession("propLabel", propLabel);		
		String cusName=request.getParameter("cusName");
		FacesUtils.setValueInHashtableOfSession("cusName", cusName);
		String address=request.getParameter("address");
		FacesUtils.setValueInHashtableOfSession("address", address);
		
		String statusId=request.getParameter("statusId");
		FacesUtils.setValueInHashtableOfSession("statusId", statusId);
		
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	

		
		String visitDateBegin=request.getParameter("visitDateBegin");
		FacesUtils.setValueInHashtableOfSession("visitDateBegin", visitDateBegin);
		
		String visitDateEnd=request.getParameter("visitDateEnd");
		FacesUtils.setValueInHashtableOfSession("visitDateEnd",visitDateEnd);			
		

		String overDays=request.getParameter("overDays");
		FacesUtils.setValueInHashtableOfSession("overDays",overDays);	
		
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		String userName=request.getParameter("userName");
		FacesUtils.setValueInHashtableOfSession("userName",userName);		
		
		
		String beGroup=request.getParameter("beGroup");
		if (beGroup==null)
			beGroup="false";
		FacesUtils.setValueInHashtableOfSession("beGroup",beGroup);		
		
		StringBuffer sb=new StringBuffer("");
		//权限过滤代码

		Map<String,Object> param=new HashMap<String,Object>();
		if(StringUtil.isNotEmpty(cuscode))
		{
			sb.append("and cuscode like '%").append(StringUtil.fullYhStr(cuscode.trim())).append("%' ");
		}
		
		if(StringUtil.isNotEmpty(opsCode))
		{
			sb.append("and opsCode like '%").append(StringUtil.fullYhStr(opsCode.trim())).append("%' ");
		}
		
		if(StringUtil.isNotEmpty(cusName))
		{
			sb.append("and name like '%").append(StringUtil.fullYhStr(cusName.trim())).append("%' ");
		}
		
		if(StringUtil.isNotEmpty(address))
		{
			sb.append("and address like '%").append(StringUtil.fullYhStr(address.trim())).append("%' ");
		}
		
		if(StringUtil.isNotEmpty(statusId))
		{
			sb.append("and status_Id in (").append(statusId.trim()).append(") ");
		}
		
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(propLabel))
		{
			sb.append(" and id in (Select id from b_crm_customer_extra where  propLabel like '%").append(propLabel.trim()).append("%') ");
		}		
		
		if(StringUtil.isNotEmpty(visitDateBegin))
		{
			sb.append(" and id in (Select id from b_crm_customer_extra where  lastLinkTime>='").append(visitDateBegin.trim()).append("') ");
		}
		
		
		if(StringUtil.isNotEmpty(visitDateEnd))
		{
			sb.append(" and id in (Select id from b_crm_customer_extra where  lastLinkTime<='").append(visitDateEnd.trim()).append("') ");			
		}
		
		
		if(StringUtil.isNotEmpty(overDays))
		{
			sb.append(" and id in (Select id from b_crm_customer_extra where   lastLinkTime<='").append(ProDateUtil.getLongHisDateStr(Integer.parseInt(visitDateEnd.trim()))).append("') ");
		}
		

		if(StringUtil.isNotEmpty(userName))
		{
			sb.append(" and user_id in (Select id from sys_user where  ((name in (").append(StringUtil.convertToSqlParam(userName)).append(")) or (username in (").append(StringUtil.convertToSqlParam(userName)).append(")))) ");
		}
				

		

	
       if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
	        if (StringUtil.isNotEmpty(orgIds))
	        {
	        	sb.append("and org_id in (").append(orgIds.trim()).append(") ");
	        }  		
		}
		else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
	        if (StringUtil.isNotEmpty(orgIds))
	        {
	        	sb.append("and org_id in (").append(orgIds.trim()).append(") ");
	        }  		
			
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			if(beGroup.equals("false"))
			{
				sb.append("and user_id=").append(up.getUserId()).append(" ");
			}		
			
		}
		else
		{
			
		}
		
		
		
		if (StringUtil.isNotEmpty(sb.toString()))
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			param.put("topnumber", " top 20 ");
		}
					
		
	
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		

		param.put("accessString", accessString);
		//param.put("orderByClause", " lastLinkTime desc");		
		// 对标准进行排序
		List<Customer> customerList=customerService.selectByExample(param);
		CustomerLastLinkTimeComparator lastLinkComparator=new CustomerLastLinkTimeComparator();
		Collections.sort(customerList, lastLinkComparator);
		return 	customerList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {

		init();
		Date now=new Date();
		customer=new Customer();
		
		customer.setCuscode("C"+coreCreatCodeService.getMaxValue(CreatCodeConst.CUSTOMER_CUSCODE, up.getLoginClientId()).toString());
		customer.setUser(up.getUser());
		customer.setUserId(up.getUserId());
		customer.setUserName(up.getUser().getName());		
		customer.setCreatedBy(up.getLoginUserId());
		customer.setCreatedByName(up.getLoginUser().getName());
		customer.setCreatedByUser(up.getLoginUser());
		customer.setUpdatedBy(up.getLoginUserId());
		customer.setUpdatedByName(up.getLoginUser().getName());
		customer.setUpdatedByUser(up.getLoginUser());
		customer.setCreated(now);
		customer.setUpdated(now);
		customer.setClientId(up.getLoginClientId());
		customer.setOrgId(up.getLoginOrgId());
		customer.setClient(new Client(up.getLoginClientId(),up.getLoginClientName()));		
		customer.setOrg(new Org(up.getLoginOrgId(),up.getLoginOrgName()));		
		customer.setFlag(0);
		
		
	    customerExter=new CustomerExtra();
	    customerExter.setLatitude(new BigDecimal(-1));
	    customerExter.setLongtitude(new BigDecimal(-1));
	   
		linkman=new Linkman();
		linkman.setLinkCode("L"+coreCreatCodeService.getMaxValue(CreatCodeConst.LINKMAN_LINKCODE, up.getLoginClientId()).toString());
		
		
	
		
		
	}
	
	
    private boolean validateFormData()
    {
		//数据检查
		HttpServletRequest request=ServletActionContext.getRequest();
		String errorStr="";
		try
		{
		String[][] columnA={{"cuscode","name","countryid","provinceid","cityid","address"},{"string","string","string","string","string","string"}};
		errorStr=ValidateCommon.validateData(customer, columnA, "customer", request);
		if (errorStr.length()>0)
		{
		  FacesUtils.setValueInHashtableOfSession("errorMessage",errorStr);	
		  return false;	
		}
		} catch (ValidateException ex)
		{
			FacesUtils.setValueInHashtableOfSession("errorMessage",errorStr);
			FacesUtils.setValueInHashtableOfSession("errorDetail",ex.getMessage());
		    return false;	
		}    	
		
		return true;
    }

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		
		
		if (validateFormData()==false)
			return "error";

		
		//权限过滤代码
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Date now=new Date();
		if(customer.isnew())
		{
			customer.setCreated(now);
			customer.setUser(up.getUser());
			customer.setUserId(up.getUserId());
			customer.setUserName(up.getUser().getName());
			customer.setCreatedBy(up.getLoginUserId());
			customer.setCreatedByName(up.getLoginUser().getName());			
			customer.setCreatedByUser(up.getLoginUser());
			customer.setOrgId(up.getLoginOrgId());
			customer.setClientId(up.getLoginClientId());
			customer.setFlag(0);
		}
		
		if(linkman.isnew())
		{
			linkman.setCreated(now);
			linkman.setCreatedBy(up.getLoginUserId());
			linkman.setCreatedByName(up.getLoginUser().getName());			
			linkman.setUserId(up.getUserId());
			linkman.setUserName(up.getUser().getName());
			linkman.setOrgId(up.getLoginOrgId());
			linkman.setClientId(up.getLoginClientId());			
			linkman.setIsMain("Y");
			linkman.setFlag(0);
		//	linkman.setOrgId(up.get);
			
		}

		
		linkman.setIsMain("Y");
		linkman.setUpdated(now);
		linkman.setUpdatedBy(up.getLoginUserId());
		linkman.setUpdatedByName(up.getLoginUser().getName());

		customer.setUserId(up.getUserId());
		customer.setUserName(up.getUser().getName());
		customer.setCreatedBy(up.getLoginUserId());
		customer.setCreatedByName(up.getLoginUser().getName());			
		customer.setCreatedByUser(up.getLoginUser());
		customer.setOrgId(up.getLoginOrgId());
		customer.setClientId(up.getLoginClientId());
		customer.setUpdated(now);
		customer.setUpdatedBy(up.getLoginUserId());
		customer.setUpdatedByUser(up.getLoginUser());
		customer.setUpdatedByName(up.getLoginUser().getName());
		
		customer.setFlag(0);
		customer.setIsactive("Y");
		
		
		//如果关键代码不为空，并且被改动过，则需要进行检查
		boolean opsCodeCheck=true;
		if (!customer.getOpsCode().equals(""))
		{
			if (!customer.getOpsCode().equalsIgnoreCase(oldOpsCode))   //进行检查
			//这里为了避免和之前的系统写上硬代码，所以这里采用代理的形式
			{
				if (CustomerProxyManage.getInstance().processByProxy(customer.getOpsCode())==false)
					opsCodeCheck=false;
			}
		}
		
		if (opsCodeCheck==false)
		{
			clearMessages();
			addActionMessage(getText("customer.message.opscodenotexist"));
			addActionError(getText("customer.message.opscodenotexist"));
			addFieldError("opsCode",getText("customer.message.opscodenotexist"));
			FacesUtils.setValueInHashtableOfSession("errorMessage",getText("customer.message.opscodenotexist"));
			//init();
			return "error";
		}
	
		if(!customer.isnew())  //编辑的时候才判断
		{
			CustomerTrace customerTrace=new CustomerTrace();
			customerTrace.setCustomerId(customer.getId());
			customerTrace.setCreated(now);
			customerTrace.setUser(up.getUser());
			customerTrace.setUserId(up.getUserId());
			customerTrace.setUserName(up.getUser().getName());
			customerTrace.setCreatedBy(up.getLoginUserId());
			customerTrace.setCreatedByName(up.getLoginUser().getName());			
			customerTrace.setCreatedByUser(up.getLoginUser());
			customerTrace.setUpdatedBy(up.getLoginUserId());
			customerTrace.setUpdatedByName(up.getLoginUser().getName());			
			customerTrace.setUpdatedByUser(up.getLoginUser());			
			customerTrace.setOrgId(up.getLoginOrgId());
			customerTrace.setClientId(up.getLoginClientId());
			customerTrace.setFlag(0);
			
			customerTraceService.saveCustomerTrace(customer.getStatusId(),oldStatusId,customerTrace);
			
		}
		customerService.saveCustomer(customer, linkman, customerExter);
		

		id=customer.getId();
		edit();
		return "edit";
	}
	
	
	
	
	
	/**
	 * @return the usergroup
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the usergroup to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	/**
	 * @return the customerExter
	 */
	public CustomerExtra getCustomerExter() {
		return customerExter;
	}

	/**
	 * @param customerExter the customerExter to set
	 */
	public void setCustomerExter(CustomerExtra customerExter) {
		this.customerExter = customerExter;
	}

	/**
	 * @return the linkman
	 */
	public Linkman getLinkman() {
		return linkman;
	}

	/**
	 * @param linkman the linkman to set
	 */
	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}


	/**
	 * @return the cussourceList
	 */
	public List<DatadictTrl> getCussourceList() {
		return cussourceList;
	}

	/**
	 * @param cussourceList the cussourceList to set
	 */
	public void setCussourceList(List<DatadictTrl> cussourceList) {
		this.cussourceList = cussourceList;
	}

	/**
	 * @return the industryList
	 */
	public List<DatadictTrl> getIndustryList() {
		return industryList;
	}

	/**
	 * @param industryList the industryList to set
	 */
	public void setIndustryList(List<DatadictTrl> industryList) {
		this.industryList = industryList;
	}

	public List<DatadictTrl> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<DatadictTrl> statusList) {
		this.statusList = statusList;
	}

	public List<DatadictTrl> getCustypeList() {
		return custypeList;
	}

	public void setCustypeList(List<DatadictTrl> custypeList) {
		this.custypeList = custypeList;
	}

	public String getOldOpsCode() {
		return oldOpsCode;
	}

	public void setOldOpsCode(String oldOpsCode) {
		this.oldOpsCode = oldOpsCode;
	}

	public Integer getOldStatusId() {
		return oldStatusId;
	}

	public void setOldStatusId(Integer oldStatusId) {
		this.oldStatusId = oldStatusId;
	}



}
