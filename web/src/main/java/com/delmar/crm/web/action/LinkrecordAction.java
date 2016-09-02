/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.crm.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Currency;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.model.PortTrl;
import com.delmar.base.service.CurrencyService;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.PortTrlService;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.FileRelationService;
import com.delmar.common.service.FileSettingService;
import com.delmar.core.DelmarConst;
import com.delmar.core.service.CoreCreatCodeService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.BusForecast;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerExtra;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.BusForecastService;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.crm.web.model.CreatCodeConst;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Module;
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


/**
 * @author 刘大磊 2015-03-11 14:05:50
 */
public class LinkrecordAction extends CoreEditPrivAction {
	private Linkrecord  linkrecord;
	private List<BusForecast> lrbfList;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private LinkrecordService linkrecordService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerExterService customerExterService;	
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;	
	@Autowired
	private CoreCreatCodeService coreCreatCodeService;	
	@Autowired
	private CurrencyService  currencyService;
	@Autowired
	private BusForecastService  busForecastService;	
	@Autowired
	private PortTrlService portTrlService;
	
	@Autowired
	private FileSettingService fileSettingService;
	@Autowired
	private FileRelationService fileRelationService;	
	
	
	private List<DatadictTrl> contactModeList;	
	private List<DatadictTrl> forceOnList;
	private List<DatadictTrl> resultList;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表	
	
	
	
	
	//记录是否可以进行再次更改
	private boolean beAmend=true;
	
	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CRM联系记录");
	}
			
	
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
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
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
		
		contactModeList=datadictService.getDatadictTrlByValue(DatadictType.LINKRECORD_CONTACTMODE,ur.getLocale().toString(),up.getLoginClientId());
		forceOnList=datadictService.getDatadictTrlByValue(DatadictType.LINKRECORD_FORCEON, ur.getLocale().toString(),up.getLoginClientId());
		resultList=datadictService.getDatadictTrlByValue(DatadictType.LINKRECORD_RESULT, ur.getLocale().toString(),up.getLoginClientId());
		


		
		//定义Display Tag的
         
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "linkrecord";
	}

	@Override
	public boolean validateDelete(Integer id)
	{
		Linkrecord linkrecordObj=linkrecordService.selectByPrimaryKey(id);
		if (((new Date()).getTime()-linkrecordObj.getContactDate().getTime())>7)
		{
			FacesUtils.setValueInHashtableOfSession("errorMessage",getText("linkrecord.operate.candelete.overdate"));			
			return false;
		}
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {		
		
		if (validateDelete(linkrecord.getId())==false)
			return "error";
		

		linkrecordService.deleteById(linkrecord.getId());
		
		return this.list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		linkrecordService.deleteLinkrecordList(ids);
		
		

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return linkrecord.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		
		Module module=PrivilegeOperator.getModule();
		FacesUtils.setValueInHashtableOfSession("module", module);
		Map<String,Object> param1=new HashMap<String,Object>();
		param1.put("moduleId", module.getId());
		FileSetting fs=fileSettingService.getByExample(param1);
		FacesUtils.setValueInHashtableOfSession("fileSetting", fs);
		


		
		
		 linkrecord= linkrecordService.selectByPrimaryKey(id);
		 
		 
		if(this.linkrecord!=null)
		{
			Org org=orgService.selectByPrimaryKey(linkrecord.getOrgId());
			linkrecord.setOrg(org);
			
			User user=userService.selectByPrimaryKey(linkrecord.getUserId());
			linkrecord.setUser(user);			
			User createdby=userService.selectByPrimaryKey(linkrecord.getCreatedBy());
			linkrecord.setCreatedByUser(createdby);
			User updateby=userService.selectByPrimaryKey(linkrecord.getUpdatedBy());
			linkrecord.setUpdatedByUser(updateby);
			
			Customer customer=customerService.selectByPrimaryKey(linkrecord.getCustomerId());
			linkrecord.setCustomer(customer);
			Linkman linkman=linkmanService.selectByPrimaryKey(linkrecord.getLinkmanId());
			linkrecord.setLinkman(linkman);
		} 
		
		
		
		 //如果拜访时间在当前时间7天之前，不允许修改
		 if (((new Date()).getTime()-linkrecord.getContactDate().getTime())>3)
			 beAmend=false;
		 //设定返回的列表
		 FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkrecord_list.action"));
		 
		 //获取实际业务
		 lrbfList=busForecastService.selectByLinkRecordId(id);
		 UserResource resource= (UserResource)FacesUtils.getSession().getAttribute("resource");		 
	 	 for (BusForecast bfObj:lrbfList)
		 {
				
				PortTrl pol=this.portTrlService.getPortTrl(resource.getLocale().toString(), bfObj.getPolId());
				bfObj.setPol(pol);	
				
				PortTrl poa=this.portTrlService.getPortTrl(resource.getLocale().toString(), bfObj.getPoaId());
				bfObj.setPoa(poa);		
				
				Currency currency=this.currencyService.selectByPrimaryKey(bfObj.getCurrencyId());
				bfObj.setCurrency(currency);					
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
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","group");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());
		
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		
		
		String linkRecordId=request.getParameter("linkRecordId");
		FacesUtils.setValueInHashtableOfSession("linkRecordId", linkRecordId);
		
		String customerName=request.getParameter("customerName");
		FacesUtils.setValueInHashtableOfSession("customerName", customerName);
		
		String linkCode=request.getParameter("linkCode");
		FacesUtils.setValueInHashtableOfSession("linkCode", linkCode);
		String linkManName=request.getParameter("linkManName");
		FacesUtils.setValueInHashtableOfSession("linkManName", linkManName);
		String contactTitle=request.getParameter("contactTitle");
		FacesUtils.setValueInHashtableOfSession("contactTitle", contactTitle);
		String contactModeId=request.getParameter("contactModeId");
		FacesUtils.setValueInHashtableOfSession("contactModeId", contactModeId);	
		String resultId=request.getParameter("resultId");
		FacesUtils.setValueInHashtableOfSession("resultId", resultId);	
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	
		
		String contactDateBegin=request.getParameter("contactDateBegin");
		FacesUtils.setValueInHashtableOfSession("contactDateBegin", contactDateBegin);	

		
		String contactDateEnd=request.getParameter("contactDateEnd");
		FacesUtils.setValueInHashtableOfSession("contactDateEnd",contactDateEnd);		
		
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
				
			}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
			{
				if(beGroup.equals("false"))
				{
					sb.append("and user_id=").append(up.getUserId()).append(" ");
				}		
				
			}
			else
			{
				
			}
					
		
		if(StringUtil.isNotEmpty(linkRecordId))
		{
			sb.append("and linkRecordId='"+StringUtil.fullYhStr(linkRecordId.trim())+"' ");
		}
				       
		if(StringUtil.isNotEmpty(customerName))
		{
			sb.append("and customer_id in (select id from b_crm_customer where name like '%"+StringUtil.fullYhStr(customerName.trim())+"%' )");
		}
		

		boolean applinkman=false;
		StringBuilder lksb=new StringBuilder("and linkman_id in (select id from b_crm_linkman where ");
		
		 if(StringUtil.isNotEmpty(linkCode))
		{
			 applinkman=true;
			 lksb.append(" linkcode like '%").append(StringUtil.fullYhStr(linkCode.trim())).append("%' ");
		}
		else if(StringUtil.isNotEmpty(linkManName))
		{
			if(applinkman)
			{
				lksb.append(" and ");
			}
			
			lksb.append(" name like '%").append(StringUtil.fullYhStr(linkManName.trim())).append("%' ");
			 applinkman=true;
		}
		lksb.append(")");
		
		if(applinkman)
		{
			sb.append(lksb);
		}
		
		if(StringUtil.isNotEmpty(contactTitle))
		{
			sb.append("and contact_title like '%"+StringUtil.fullYhStr(contactTitle.trim())+"%' ");
		}
		
				
		if(StringUtil.isNotEmpty(contactModeId))
		{
			sb.append("and contactmode_id in (").append(contactModeId.trim()).append(") ");
		}
		
		if(StringUtil.isNotEmpty(resultId))
		{
			sb.append("and result_Id in (").append(resultId.trim()).append(") ");
		}		
		
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
		}
		
		if(StringUtil.isNotEmpty(contactDateBegin))
		{
			sb.append("and contact_date>='").append(contactDateBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(contactDateEnd))
		{
			sb.append("and contact_date<='").append(contactDateEnd.trim()).append("' ");
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
		
		if(StringUtil.isNotEmpty(userName))
		{
			sb.append(" and user_id in (Select id from sys_user where  ((name in (").append(StringUtil.convertToSqlParam(userName)).append(")) or (username in (").append(StringUtil.convertToSqlParam(userName)).append(")))) ");
		}
		
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		
		param.put("accessString", accessString);
		
		return linkrecordService.selectByExample(param);
	}

	
	

	/** 
	 * @Title:        searchcargo 
	 * @Description:  根据传递过来的OpsCode数据进行查询
	 * @param:        @return    
	 * @return:       List    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年7月2日 下午3:14:08 
	 */
	public String searchcargo() {
		
		
		init();	
		
		//判断用户有没有权限
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
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		//判断用户有没有权限
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);		
		

		
		String opsCodes=(String)request.getSession().getAttribute("opsCodes");
		String days=(String)request.getSession().getAttribute("days");
		
		

		String contactDateBegin=ProDateUtil.getShortHisDateStr(0);
		String contactDateEnd=ProDateUtil.getShortHisDateStr(Integer.parseInt(days));
		
		
        //标记是从这里过去的
		FacesUtils.setValueInHashtableOfSession("fromOthers", "true");					


		
		StringBuffer sb=new StringBuffer("");
		//权限过滤代码

		Map<String,Object> param=new HashMap<String,Object>();
		
		
		
		if(StringUtil.isNotEmpty(opsCodes.toString()))
		{
			sb.append("and customer_id in (select id from b_crm_customer where opsCode in ("+opsCodes.toString()+"))");
		}
		
		
		if(StringUtil.isNotEmpty(contactDateBegin))
		{
			sb.append("and contact_date>='").append(contactDateBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(contactDateEnd))
		{
			sb.append("and contact_date<='").append(contactDateEnd.trim()).append("' ");
		}			
		
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		
		param.put("accessString", accessString);
		
		
		List<Linkrecord> list=linkrecordService.selectByExample(param);
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"List", list);
		return LIST;		
	}

	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		init();
		
		beAmend=true;
		Linkrecord oldlinkrecord=new Linkrecord();
		if (linkrecord!=null)   //如果当前的linkrecord不为空
			oldlinkrecord=linkrecord;
		
		linkrecord=new Linkrecord();
		linkrecord.setLinkRecordId("LR"+coreCreatCodeService.getMaxValue(CreatCodeConst.LINKRECORD_RECORDID, up.getLoginClientId()).toString());
		Date now=new Date();
		//复制之前的有用的信息和数据
		linkrecord.setCustomer(oldlinkrecord.getCustomer());
		linkrecord.setCustomerId(oldlinkrecord.getCustomerId());
		linkrecord.setCustomerName(oldlinkrecord.getCustomerName());
		linkrecord.setLinkman(oldlinkrecord.getLinkman());
		linkrecord.setLinkmanId(oldlinkrecord.getLinkmanId());
		linkrecord.setLinkmanName(oldlinkrecord.getLinkmanName());
		
		
		linkrecord.setUser(up.getUser());
		linkrecord.setUserId(up.getUserId());
		linkrecord.setUserName(up.getUser().getName());		
		linkrecord.setCreatedBy(up.getLoginUserId());
		linkrecord.setCreatedByName(up.getLoginUser().getName());
		linkrecord.setCreatedByUser(up.getLoginUser());
		linkrecord.setUpdatedBy(up.getLoginUserId());
		linkrecord.setUpdatedByName(up.getLoginUser().getName());
		linkrecord.setUpdatedByUser(up.getLoginUser());
		linkrecord.setCreated(now);
		linkrecord.setUpdated(now);
		linkrecord.setClientId(up.getLoginClientId());
		linkrecord.setOrgId(up.getLoginOrgId());
		linkrecord.setClient(new Client(up.getLoginClientId(),up.getLoginClientName()));		
		linkrecord.setOrg(new Org(up.getLoginOrgId(),up.getLoginOrgName()));		
		
		
		linkrecord.setFlag(0);
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String from=request.getParameter("from");
		String fromid=request.getParameter("fromid");
		
		if ("customer".equals(from))  //说明是从客户档案处过来的
		{
			Customer customer=customerService.selectByPrimaryKey(new Integer(fromid));
			Linkman linkman=linkmanService.getLinkman(customer.getId(), "Y");
			
			linkrecord.setCustomerId(customer.getId());
			linkrecord.setCustomer(customer);
			linkrecord.setLinkmanId(linkman.getId());
			linkrecord.setLinkman(linkman);
			
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/customer_list.action"));			
		}
		else if ("linkman".equals(from))  //说明是从联系人那里过来的
		{
			Linkman linkman=linkmanService.selectByPrimaryKey(new Integer(fromid));
			Customer customer=customerService.selectByPrimaryKey(linkman.getCustomerId());
			
			linkrecord.setCustomerId(customer.getId());
			linkrecord.setCustomer(customer);
			linkrecord.setLinkmanId(linkman.getId());
			linkrecord.setLinkman(linkman);
			
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkman_list.action"));	
			
		}
		else
		    FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkrecord_list.action"));
			
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		Date now=new Date();
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		if(linkrecord.isnew())
		{
			linkrecord.setCreated(now);
			linkrecord.setCreatedBy(up.getLoginUserId());
			linkrecord.setCreatedByName(up.getLoginUser().getName());
			linkrecord.setOrgId(up.getLoginOrgId());
			linkrecord.setUserId(up.getUserId());
			linkrecord.setUserName(up.getUser().getName());
			linkrecord.setClientId(up.getLoginClientId());
			linkrecord.setFlag(0);
		}
		linkrecord.setUpdated(now);
		linkrecord.setUpdatedBy(up.getLoginUserId());
		linkrecord.setUpdatedByName(up.getLoginUser().getName());
		
		linkrecordService.save(linkrecord);
		
		//此处还需要根据拜访记录更新联系人以及客户的拜访时间
		//此处进行下次联系日期等的更新
		CustomerExtra customerextra=customerExterService.selectFieldsByPrimaryKey("Id,firstLinkTime,nextLinkTime,LastLinkTime",linkrecord.getCustomerId());
		Linkman linkman=linkmanService.selectFieldsByPrimaryKey("Id,firstLinkTime,nextLinkTime,LastLinkTime",linkrecord.getLinkmanId());
		boolean beCustomerExtra=false;
		boolean beLinkman=false;
		if (customerextra.getNextLinkTime()==null||linkrecord.getNextTime().compareTo(customerextra.getNextLinkTime())>0)
		{
			customerextra.setNextLinkTime(linkrecord.getNextTime());
			beCustomerExtra=true;
		}
		if (customerextra.getFirstLinkTime()==null||linkrecord.getContactDate().compareTo(customerextra.getFirstLinkTime())<0)
		{
			customerextra.setFirstLinkTime(linkrecord.getContactDate());
			beCustomerExtra=true;
		}
		if (customerextra.getLastLinkTime()==null||linkrecord.getContactDate().compareTo(customerextra.getLastLinkTime())>0)
		{
		   customerextra.setLastLinkTime(linkrecord.getContactDate());
			beCustomerExtra=true;
		}
		
		if (beCustomerExtra)
		  customerExterService.update(customerextra);

		
		if (linkman.getNextLinkTime()==null||linkrecord.getNextTime().compareTo(linkman.getNextLinkTime())>0)
		{
			linkman.setNextLinkTime(linkrecord.getNextTime());
			beLinkman=true;
		}
		if (linkman.getFirstLinkTime()==null||linkrecord.getContactDate().compareTo(linkman.getFirstLinkTime())<0)
		{
			linkman.setFirstLinkTime(linkrecord.getContactDate());
			beLinkman=true;			
		}
		if (linkman.getLastLinkTime()==null||linkrecord.getContactDate().compareTo(linkman.getLastLinkTime())>0)
		{
			linkman.setLastLinkTime(linkrecord.getContactDate());
			beLinkman=true;			
		}
		if (beLinkman)
		  linkmanService.update(linkman);
		
		
		
		this.id=linkrecord.getId();
		edit();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Linkrecord getLinkrecord() {
		return linkrecord;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setLinkrecord(Linkrecord linkrecord) {
		this.linkrecord = linkrecord;
	}

	public List<DatadictTrl> getContactModeList() {
		return contactModeList;
	}

	public void setContactModeList(List<DatadictTrl> contactModeList) {
		this.contactModeList = contactModeList;
	}

	public List<DatadictTrl> getForceOnList() {
		return forceOnList;
	}

	public void setForceOnList(List<DatadictTrl> forceOnList) {
		this.forceOnList = forceOnList;
	}

	public List<DatadictTrl> getResultList() {
		return resultList;
	}

	public void setResultList(List<DatadictTrl> resultList) {
		this.resultList = resultList;
	}


	public boolean isBeAmend() {
		return beAmend;
	}


	public void setBeAmend(boolean beAmend) {
		this.beAmend = beAmend;
	}


	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}


	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}


	public List<BusForecast> getLrbfList() {
		return lrbfList;
	}


	public void setLrbfList(List<BusForecast> lrbfList) {
		this.lrbfList = lrbfList;
	}


	


}
