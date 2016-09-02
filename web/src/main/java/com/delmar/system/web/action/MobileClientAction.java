/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.common.model.FileRelation;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.DelmarFileService;
import com.delmar.common.service.FileRelationService;
import com.delmar.common.service.FileSettingService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.MobileClient;
import com.delmar.sys.model.Module;
import com.delmar.sys.service.MobileClientService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;

/**
 * @author 刘大磊 2015年7月22日 上午10:46:26
 */
public class MobileClientAction extends CoreEditPrivAction {
	private MobileClient mobileClient= new MobileClient();;
	@Autowired
	private DelmarFileService delmarFileService;
	@Autowired
	private MobileClientService mobileClientService;
	private 	List<FileRelation> fileRelationList=new ArrayList<FileRelation>();
	@Autowired
	private FileSettingService fileSettingService;
	@Autowired
	private FileRelationService fileRelationService;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "mobileClient";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		mobileClientService.deleteByPrimaryKey(mobileClient.getId());
		return "list";
	}
	private void init()
	{
		Module module=PrivilegeOperator.getModule();
		FacesUtils.setValueInHashtableOfSession("module", module);
		Map<String,Object> param1=new HashMap<String,Object>();
		param1.put("moduleId", module.getId());
		FileSetting fs=fileSettingService.getByExample(param1);
		FacesUtils.setValueInHashtableOfSession("fileSetting", fs);
		if(!mobileClient.isnew())
		{
			Map param=new HashMap();
			param.put("tableName", "sys_mobile_client");
			param.put("tableId", mobileClient.getId());
			fileRelationList=fileRelationService.selectByExample(param);
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer
	 * [])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		mobileClientService.deleteMobileClientList(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {
		return mobileClient.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		mobileClient = mobileClientService.selectByPrimaryKey(id);
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {

		return this.mobileClientService.selectByExample(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		mobileClient = new MobileClient();
		init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		PrivilegesDataFilter	 up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		boolean isnew=false;
		Date date=new Date();
		if(mobileClient.isnew())
		{
			isnew=true;
			mobileClient.setCreated(date);
			mobileClient.setCreatedby(up.getUserId());
			mobileClient.setCreatedbyname(up.getLoginUser().getUsername());
			mobileClient.setClientId(up.getLoginClientId());
			mobileClient.setOrgId(up.getLoginOrgId());
			mobileClient.setUserId(up.getLoginUserId());
			mobileClient.setUserName(up.getLoginUser().getUsername());
		}
		mobileClient.setUpdated(date);
		mobileClient.setUpdatedby(up.getUserId());
		mobileClient.setUpdatedbyname(up.getLoginUser().getUsername());

		FileSetting fs=(FileSetting)FacesUtils.getValueInHashtableOfSession("fileSetting");
		for(FileRelation fr:fileRelationList)
		{
			fr.getDelmarFile().setCommonFileSettingId(fs.getId());
		}
		
		mobileClientService.save(mobileClient,fileRelationList);	
		
		this.id=mobileClient.getId();
		if(isnew)
		{
			fileRelationList.add(new FileRelation());
		}
		return EDIT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getPurpose()
	 */
	@Override
	public String getPurpose() {
		return "手机客户端";
	}

	/**
	 * @return the fileRelationList
	 */
	public List<FileRelation> getFileRelationList() {
		return fileRelationList;
	}

	/**
	 * @param fileRelationList the fileRelationList to set
	 */
	public void setFileRelationList(List<FileRelation> fileRelationList) {
		this.fileRelationList = fileRelationList;
	}

	/**
	 * @return the mobileClient
	 */
	public MobileClient getMobileClient() {
		return mobileClient;
	}

	/**
	 * @param mobileClient the mobileClient to set
	 */
	public void setMobileClient(MobileClient mobileClient) {
		this.mobileClient = mobileClient;
	}

}
