/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-05-29 15:11:25
 */
public class UserThirdPartyAction extends CoreEditPrivAction {
	private UserThirdParty  userThirdParty;
	
	@Autowired
	private UserThirdPartyService userThirdPartyService;
	@Autowired
	private DatadictService datadictService;		
	
	private List<UserThirdParty> userThirdPartyList;	
	private List<DatadictTrl> thirdpartySysList;
	private List<DatadictTrl> thirdpartyTypeList;
	
	private Integer sysUserId;
	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "用户第三方系统关联");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "userThirdParty";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		userThirdPartyService.deleteByPrimaryKey(userThirdParty.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		userThirdPartyService.deleteUserThirdPartyList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return userThirdParty.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 userThirdParty= userThirdPartyService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		thirdpartySysList=datadictService.getDatadictTrlByValue(DatadictType.THRIDPARTY_SYSTEM,ur.getLocale().toString(),up.getLoginClientId());
		thirdpartyTypeList=datadictService.getDatadictTrlByValue(DatadictType.THRIDPARTY_PARTYTYPE,ur.getLocale().toString(),up.getLoginClientId());	
		

	
		if (sysUserId!=null)
		{
			Map paramParty=new HashMap();
			paramParty.put("sysuserid", sysUserId);
			userThirdPartyList=this.userThirdPartyService.selectByExample(paramParty);
			
			
		
			for(UserThirdParty utp:userThirdPartyList)
			{
				DatadictTrl systemTrl=datadictService.getTrlByDataId(utp.getSystemId(), ur.getLocale().toString());
				if (systemTrl!=null)
					utp.setSystemName(systemTrl.getName());
	
				DatadictTrl partyTypeTrl=datadictService.getTrlByDataId(utp.getPartyTypeId(), ur.getLocale().toString());
				if (partyTypeTrl!=null)
					utp.setPartyTypeName(partyTypeTrl.getName());				
			}
		} else
		{
			userThirdPartyList=new ArrayList<UserThirdParty>();
		}
		
		
		
		
		return userThirdPartyList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		userThirdParty=new UserThirdParty();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		userThirdPartyService.save(userThirdParty);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public UserThirdParty getUserThirdParty() {
		return userThirdParty;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setUserThirdParty(UserThirdParty userThirdParty) {
		this.userThirdParty = userThirdParty;
	}

	public List<UserThirdParty> getUserThirdPartyList() {
		return userThirdPartyList;
	}

	public void setUserThirdPartyList(List<UserThirdParty> userThirdPartyList) {
		this.userThirdPartyList = userThirdPartyList;
	}

	public List<DatadictTrl> getThirdpartySysList() {
		return thirdpartySysList;
	}

	public void setThirdpartySysList(List<DatadictTrl> thirdpartySysList) {
		this.thirdpartySysList = thirdpartySysList;
	}

	public List<DatadictTrl> getThirdpartyTypeList() {
		return thirdpartyTypeList;
	}

	public void setThirdpartyTypeList(List<DatadictTrl> thirdpartyTypeList) {
		this.thirdpartyTypeList = thirdpartyTypeList;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	
	
	
	

}
