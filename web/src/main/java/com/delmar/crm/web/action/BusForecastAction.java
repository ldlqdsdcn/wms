
package com.delmar.crm.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.delmar.core.DelmarConst;
import com.delmar.core.service.CoreCreatCodeService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.BusForecast;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.BusForecastService;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.crm.web.model.CreatCodeConst;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;


/**
 *@ClassName:   BusForecastAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年8月13日 下午2:58:43
 * @version V2.0
 */
public class BusForecastAction extends CoreEditPrivAction {
	
	private BusForecast  busForecast;
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private BusForecastService busForecastService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;	
	@Autowired
	private CoreCreatCodeService coreCreatCodeService;	
	@Autowired
	private CurrencyService currencyService;	
	@Autowired
	private CustomerService customerService;	
	@Autowired
	private LinkrecordService linkrecordService;	
	@Autowired
	private PortTrlService portTrlService;
	
	private List<DatadictTrl> modeList;
	private List<Currency> currencyList;
	
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表	
	private List<ObjSelect> outinList;
	
	//记录是否可以进行再次更改
	private boolean beAmend=true;
	
	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "业务展望");
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
		
		modeList=datadictService.getDatadictTrlByValue(DatadictType.MODE,ur.getLocale().toString(),up.getLoginClientId());
		currencyList=currencyService.selectByExample(null);
		
		 outinList=new ArrayList<ObjSelect>();
		 
		 outinList.add(new ObjSelect(0,ResourceMessage.getMessage("cargo.public.outinout"),ResourceMessage.getMessage("cargo.public.outinout")));
		 outinList.add(new ObjSelect(1,ResourceMessage.getMessage("cargo.public.outinin"),ResourceMessage.getMessage("cargo.public.outinin")));
		
		

		
		//定义Display Tag的
         
		
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "busForecast";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {		
		
		busForecastService.deleteByPrimaryKey(busForecast.getId());
		
		return this.list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		busForecastService.deleteBusForecastList(ids);
		
		

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return busForecast.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String from=request.getParameter("from");
		FacesUtils.setValueInHashtableOfSession("from",from);		

		
	
		
		busForecast= busForecastService.selectByPrimaryKey(id);
		
		if(this.busForecast!=null)
		{
			
			Org org=orgService.selectByPrimaryKey(busForecast.getOrgId());
			busForecast.setOrg(org);
			
			User user=userService.selectByPrimaryKey(busForecast.getUserId());
			busForecast.setUser(user);		
			
			User createdby=userService.selectByPrimaryKey(busForecast.getCreatedBy());
			busForecast.setCreatedByUser(createdby);
			User updateby=userService.selectByPrimaryKey(busForecast.getUpdatedBy());
			busForecast.setUpdatedByUser(updateby);
			
			Customer customer=customerService.selectByPrimaryKey(busForecast.getCustomerId());
			busForecast.setCustomer(customer);
		} 
		
		
		
		if (busForecast.getLinkRecordId()!=null)
		{
			if (busForecast.getLinkRecordId().intValue()>0)
			{
				busForecast.setLinkRecord(linkrecordService.selectByPrimaryKey(busForecast.getLinkRecordId()));
				busForecast.setLinkRecordCode(busForecast.getLinkRecord().getLinkRecordId());
			}
		}
		
		UserResource resource= (UserResource)FacesUtils.getSession().getAttribute("resource");
		
		PortTrl pol=this.portTrlService.getPortTrl(resource.getLocale().toString(), busForecast.getPolId());
		busForecast.setPol(pol);	
		
		PortTrl poa=this.portTrlService.getPortTrl(resource.getLocale().toString(), busForecast.getPoaId());
		busForecast.setPoa(poa);	
		
		if ("linkrecord".equals(from))
		{
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkrecord_edit.action?id="+busForecast.getLinkRecordId()));			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/busForecast_list.action"));
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
		
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	
		
		String fromDateBegin=request.getParameter("fromDateBegin");
		FacesUtils.setValueInHashtableOfSession("fromDateBegin", fromDateBegin);	

		
		String fromDateEnd=request.getParameter("fromDateEnd");
		FacesUtils.setValueInHashtableOfSession("fromDateEnd",fromDateEnd);	
		
		String toDateBegin=request.getParameter("toDateBegin");
		FacesUtils.setValueInHashtableOfSession("toDateBegin", toDateBegin);	

		
		String toDateEnd=request.getParameter("toDateEnd");
		FacesUtils.setValueInHashtableOfSession("toDateEnd",toDateEnd);				
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);

		
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
				
	       
	       
		if(StringUtil.isNotEmpty(customerName))
		{
			sb.append("and customer_id in (select id from b_crm_customer where name like '%"+StringUtil.fullYhStr(customerName.trim())+"%' )");
		}
		
				       
		if(StringUtil.isNotEmpty(linkRecordId))
		{
			sb.append("and linkrecord_id in (select id from b_crm_linkrecord where linkRecordId='"+StringUtil.fullYhStr(linkRecordId.trim())+"' )");
		}
		
		
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
		}
		
		if(StringUtil.isNotEmpty(fromDateBegin))
		{
			sb.append("and fromDate>='").append(fromDateBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(fromDateEnd))
		{
			sb.append("and fromDate<='").append(fromDateEnd.trim()).append("' ");
		}
		
		if(StringUtil.isNotEmpty(toDateBegin))
		{
			sb.append("and toDate>='").append(toDateBegin.trim()).append("' ");
		}
		
		
		if(StringUtil.isNotEmpty(toDateEnd))
		{
			sb.append("and toDate<='").append(toDateEnd.trim()).append("' ");
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
		
		return busForecastService.selectByExample(param);
	}

	
	


	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		init();
		
		beAmend=true;
		BusForecast oldbusForecast=new BusForecast();
		if (busForecast!=null)  
			oldbusForecast=busForecast;
		
		busForecast=new BusForecast();
		busForecast.setBusForecastId("BF"+coreCreatCodeService.getMaxValue(CreatCodeConst.BUSFORECAST_RECORDID, up.getLoginClientId()).toString());
		Date now=new Date();
		//复制之前的有用的信息和数据
		busForecast.setCustomer(oldbusForecast.getCustomer());
		busForecast.setCustomerId(oldbusForecast.getCustomerId());
		
		busForecast.setGoodsWeight(new BigDecimal(0));
		busForecast.setGoodsSize(new BigDecimal(0));
		busForecast.setTeuNum(0);
		busForecast.setProfit(new BigDecimal(0));;
		busForecast.setOutIn(0);
		
		busForecast.setUser(up.getUser());
		busForecast.setUserId(up.getUserId());
		busForecast.setUserName(up.getUser().getName());		
		busForecast.setCreatedBy(up.getLoginUserId());
		busForecast.setCreatedByName(up.getLoginUser().getName());
		busForecast.setCreatedByUser(up.getLoginUser());
		busForecast.setUpdatedBy(up.getLoginUserId());
		busForecast.setUpdatedByName(up.getLoginUser().getName());
		busForecast.setUpdatedByUser(up.getLoginUser());
		busForecast.setCreated(now);
		busForecast.setUpdated(now);
		busForecast.setClientId(up.getLoginClientId());
		busForecast.setOrgId(up.getLoginOrgId());
		busForecast.setClient(new Client(up.getLoginClientId(),up.getLoginClientName()));		
		busForecast.setOrg(new Org(up.getLoginOrgId(),up.getLoginOrgName()));		
		
		
		busForecast.setFlag(0);
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String from=request.getParameter("from");
		String fromid=request.getParameter("fromid");
		FacesUtils.setValueInHashtableOfSession("from",from);		
		
		if ("linkrecord".equals(from))  //说明是从拜访记录过来的
		{
			
			Linkrecord linkrecord=linkrecordService.selectByPrimaryKey(new Integer(fromid));
			Customer customer=customerService.selectByPrimaryKey(linkrecord.getCustomerId());
			
			busForecast.setCustomerId(linkrecord.getCustomerId());
			busForecast.setCustomer(customer);
			busForecast.setLinkRecordCode(linkrecord.getLinkRecordId());
			busForecast.setLinkRecord(linkrecord);
			busForecast.setLinkRecordId(linkrecord.getId());
			
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkrecord_edit.action?id="+fromid));			
		}
		else
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/busForecast_list.action"));
			
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		Date now=new Date();
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		if(busForecast.isnew())
		{
			busForecast.setCreated(now);
			busForecast.setCreatedBy(up.getLoginUserId());
			busForecast.setCreatedByName(up.getLoginUser().getName());
			busForecast.setOrgId(up.getLoginOrgId());
			busForecast.setUserId(up.getUserId());
			busForecast.setUserName(up.getUser().getName());
			busForecast.setClientId(up.getLoginClientId());
			busForecast.setFlag(0);
		}
		busForecast.setUpdated(now);
		busForecast.setUpdatedBy(up.getLoginUserId());
		busForecast.setUpdatedByName(up.getLoginUser().getName());
		
		busForecastService.save(busForecast);		
		
		
		HttpServletRequest request=ServletActionContext.getRequest();		
		String from=request.getParameter("from");
		FacesUtils.setValueInHashtableOfSession("from",from);
		if ("linkrecord".equals(from))  //说明是从拜访记录过来的
		{
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/linkrecord_edit.action?from=linkrecord&fromid="+busForecast.getLinkRecordId()));			
		}
		else
			FacesUtils.setValueInHashtableOfSession("returnaction",FacesUtils.getLocationURL("/crm/busForecast_list.action"));
		
		
		
		this.id=busForecast.getId();
		edit();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public BusForecast getBusForecast() {
		return busForecast;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setBusForecast(BusForecast busForecast) {
		this.busForecast = busForecast;
	}




	public List<DatadictTrl> getModeList() {
		return modeList;
	}


	public void setModeList(List<DatadictTrl> modeList) {
		this.modeList = modeList;
	}


	public List<Currency> getCurrencyList() {
		return currencyList;
	}


	public void setCurrencyList(List<Currency> currencyList) {
		this.currencyList = currencyList;
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


	public List<ObjSelect> getOutinList() {
		return outinList;
	}


	public void setOutinList(List<ObjSelect> outinList) {
		this.outinList = outinList;
	}



}
