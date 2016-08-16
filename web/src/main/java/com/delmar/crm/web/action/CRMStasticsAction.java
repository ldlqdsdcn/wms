/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 ******************************************************************************/
package com.delmar.crm.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.common.model.BusCoreModel;
import com.delmar.core.DelmarConst;
import com.delmar.core.web.action.CoreAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.CustomerTrace;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.CustomerTraceService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.crm.web.model.ObjStastics;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;



/**
 *@ClassName:   StatisticsAction.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年7月2日 下午10:33:54
 * @version V2.0
 */
public class CRMStasticsAction extends CoreAction {

	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserService userService;	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerTraceService customerTraceService;	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private LinkrecordService linkrecordService;	
	@Autowired
	private CustomerExterService customerExterService;
	
	protected PrivilegesDataFilter up;
	protected UserResource ur;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "CRM客户档案数据统计");
	}
			

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#list()
	 */

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
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"cusList", null);				
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"manList", null);
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"recList", null);		
		
		return "list";	

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	public String getModuleName() {
		return "stastics";
	}
	
	
	private void init()
	{
		
		
		ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		

		
	}
	






	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	public String search() {
		
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
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		

		
	
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	

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
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
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
		}
					
		
		//logger.debug(up.getAccessStringByStruts2());
		
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		

		param.put("accessString", accessString);
		//param.put("orderByClause", " lastLinkTime desc");		
		// 对标准进行排序
		List<Customer> customerList=customerService.selectByExample(param);
		List<Linkman> linkmanList=linkmanService.selectByExample(param);
		List<Linkrecord> linkrecordList=linkrecordService.selectByExample(param);
		
		
		
		
		
		String orgGroup=request.getParameter("orgGroup");
		if (orgGroup==null)
			orgGroup="";
		FacesUtils.setValueInHashtableOfSession("orgGroup", orgGroup);	
		
		
		String userGroup=request.getParameter("userGroup");
		if (userGroup==null)
			userGroup="";
		FacesUtils.setValueInHashtableOfSession("userGroup", userGroup);	
		
		String monthGroup=request.getParameter("monthGroup");
		if (monthGroup==null)
			monthGroup="false";		
		else
			monthGroup="true";
		FacesUtils.setValueInHashtableOfSession("monthGroup", monthGroup);	
		
		
		
	    List cusStaticsList=staticsList(customerList,orgGroup,userGroup,monthGroup);
	    List manStaticsList=staticsList(linkmanList,orgGroup,userGroup,monthGroup);
	    List recStaticsList=staticsList(linkrecordList,orgGroup,userGroup,monthGroup);	    
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"cusList", cusStaticsList);				
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"manList", manStaticsList);
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"recList", recStaticsList);		
    
		
		return 	"list";
	}
	
	
	
	private List<ObjStastics> staticsList(List list,String orgGroup,String userGroup,String monthGroup)
	{
	    List<ObjStastics> perList=new ArrayList<ObjStastics>();
	    
		HashMap perMap=new HashMap();
		for (Object oneobj:list)
        {
			  BusCoreModel obj=(BusCoreModel)oneobj;
			  
			  ObjStastics objPer=new ObjStastics(); 
			  String key="";			  
			  if (orgGroup.equals("yes"))  //区分
			  {
				  key=key+"-"+obj.getOrgId();
				  objPer.getGroupList().add(orgService.selectByPrimaryKey(obj.getOrgId()).getName());
			  }
			  
			  if (userGroup.equals("yes"))  //区分
			  {
				  key=key+"-"+obj.getUserId();
				  objPer.getGroupList().add(userService.selectByPrimaryKey(obj.getUserId()).getName());
			  }
			  
			  if (monthGroup.equals("true"))  //区分
			  {
				  SimpleDateFormat shortformatter = new SimpleDateFormat(
						"yyyy-MM");
				  String dateStr = shortformatter.format(obj.getCreated().getTime());
				  key=key+"-"+dateStr;
				  objPer.getGroupList().add(dateStr);
			  }
			  
			  
			  if (key.equals(""))
			  {
				  key="all";
			  }
			  
			  if (perMap.containsKey(key))
			  {
				  objPer=(ObjStastics)perMap.get(key);	
				  objPer.addCount();			 
			  }
			  else
			  {
				  objPer.setCount(1);
				  perMap.put(key, objPer);
			  }
	  	  }
				
		Object[] secondList=perMap.values().toArray();
		List thirdList=Arrays.asList(secondList);
		
		return thirdList;
	}
	
	
	public String listt() {
		
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
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"cusList", null);				

		
		return "listt";	

	}
	
	
	
	public String trace()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
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
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}

		

		
	
		String createDateBegin=request.getParameter("createDateBegin");
		FacesUtils.setValueInHashtableOfSession("createDateBegin", createDateBegin);	

		
		String createDateEnd=request.getParameter("createDateEnd");
		FacesUtils.setValueInHashtableOfSession("createDateEnd",createDateEnd);	

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
		if(StringUtil.isNotEmpty(createDateBegin))
		{
			sb.append("and created>='").append(createDateBegin.trim()).append("' ");
		}
				
		if(StringUtil.isNotEmpty(createDateEnd))
		{
			sb.append("and created<='").append(createDateEnd.trim()).append("' ");
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
	       
	       
	       

			if(StringUtil.isNotEmpty(userName))
			{
				sb.append(" and user_id in (Select id from sys_user where  ((name in (").append(StringUtil.convertToSqlParam(userName)).append(")) or (username in (").append(StringUtil.convertToSqlParam(userName)).append(")))) ");
			}
					
	       
			
		
		if (StringUtil.isNotEmpty(sb.toString()))
		{
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}
					
		
		//logger.debug(up.getAccessStringByStruts2());
		
		String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals(""))
			accessString=" 1=1 "+" "+sb.toString();
		else
			accessString=accessString+" "+sb.toString();	
		

		param.put("accessString", accessString);
		param.put("orderByClause", " created asc ");
		//param.put("orderByClause", " lastLinkTime desc");		
		// 对标准进行排序
		List<CustomerTrace> customerTraceList=customerTraceService.selectByExample(param);
		
        
		//进行整理

		
		HashMap traceMap=new HashMap();		
		for (CustomerTrace obj:customerTraceList)
		{
		   Integer customerId=obj.getCustomerId();
		   Integer userId=obj.getUserId();
		   Integer fromStatusId=obj.getFromStatus();
		   Integer toStatusId=obj.getToStatus();
		   
		   
		   if (traceMap.containsKey(userId+"-"+customerId))
		   {
   			   CustomerTrace customerTrace=(CustomerTrace)traceMap.get(userId+"-"+customerId);
   			   customerTrace.setToStatus(toStatusId);
   			   customerTrace.setRemark(customerTrace.getRemark()+"-"+toStatusId);
		   } else
		   {
			   CustomerTrace customerTrace=new CustomerTrace();
			   customerTrace.setUserId(userId);
			   customerTrace.setUserName(obj.getUserName());
			   customerTrace.setFromStatus(fromStatusId);
			   customerTrace.setToStatus(toStatusId);
			   customerTrace.setRemark(toStatusId.toString());
			   customerTrace.setCustomerId(customerId);
			   traceMap.put(userId+"-"+customerId, customerTrace);
		   }
		}
		
		List<DatadictTrl> customerStatusList=datadictService.getDatadictTrlByValue(DatadictType.CUSSTATUS,ur.getLocale().toString(),up.getLoginClientId());
		HashMap statusMap=new HashMap();
		for (DatadictTrl datadicttrl:customerStatusList)
		{
			statusMap.put(datadicttrl.getDatadictId(), datadicttrl);
		}
		
		Iterator customerIterator=traceMap.values().iterator();
		List<CustomerTrace> customerTraceListTwo=new ArrayList<CustomerTrace>();
		while (customerIterator.hasNext()) {
			CustomerTrace detailObject = (CustomerTrace) customerIterator.next();
			
			if (getIndexOrder(detailObject.getFromStatus(),statusMap).intValue()<getIndexOrder(detailObject.getToStatus(),statusMap).intValue())
			{
				detailObject.setChangeType(1);  //向上的
			} else
			{
				detailObject.setChangeType(-1); 				
			}
			detailObject.setUserName(detailObject.getUserName());
			detailObject.setFromStatusName(getStatusName(detailObject.getFromStatus(),statusMap));
			detailObject.setToStatusName(getStatusName(detailObject.getToStatus(),statusMap));
			
			Customer customer=customerService.selectByPrimaryKey(detailObject.getCustomerId());
			detailObject.setCustomer(customer);
			
			
			customerTraceListTwo.add(detailObject);			
		}
		
		FacesUtils.setValueInHashtableOfSession(getModuleName()+"cusList", customerTraceListTwo);				
    
		
		return 	"trace";	  	
	}
	
	
	private Integer getIndexOrder(Integer statusId,HashMap statusMap)
	{
		if (statusMap.containsKey(statusId))
		{
			return ((DatadictTrl)statusMap.get(statusId)).getIndexOrder();
		}
		else
		{
			return 0;
		}
	}
	
	private String getStatusName(Integer statusId,HashMap statusMap)
	{
		if (statusMap.containsKey(statusId))
		{
			return ((DatadictTrl)statusMap.get(statusId)).getName();
		}
		else
		{
			return statusId.toString();
		}
	}	
	

	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	





}
