package com.delmar.sysSettings.web.action;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.service.ClientService;
import com.delmar.sysSettings.enums.InputTypeEnum;
import com.delmar.sysSettings.model.SysSettings;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsValues;
import com.delmar.sysSettings.service.SysSettingsItemService;
import com.delmar.sysSettings.service.SysSettingsItemValueService;
import com.delmar.sysSettings.service.SysSettingsService;
import com.delmar.sysSettings.service.SysSettingsValuesService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class SysSettingsAction extends CoreEditPrivAction{
	
	protected PrivilegesDataFilter up;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	protected List<SysSettings> sysSettingsList;  
	private String itemId;
	private String setValueString;
	private SysSettings sysSettings;
	private SysSettingsValues sysSettingsValues;
	private String saveType;
	private List<DatadictTrl> itemList;
	private List<DatadictTrl> itemValueList;
	private String userName;
	private String orgIds;
	

	private static final long serialVersionUID = -1245836418680729739L;
	
	@Autowired
	private SysSettingsItemService sysSettingsItemService;
	
	@Autowired
	private SysSettingsService sysSettingsService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	SysSettingsValuesService sysSettingsValuesService;
	
	@Autowired
	SysSettingsItemValueService sysSettingsItemValueService;


	@Override
	public String list() {
    	
    	checkItemSetting();
    	init();
		return super.list();
	}
    
	/**
	 * 检查setting表中数据是否完整，若缺少数据则从item表初始化
	 */
    public void checkItemSetting() {
		
    	up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
    	List<SysSettingsItem> items = sysSettingsItemService.selectByExample(null);
    	
    	Map<String, Object> example = new HashMap<String, Object>();
    	example.put("accessString", " 1 = 1 and set_user_id = " + up.getUserId());
    	List<SysSettings> settings = sysSettingsService.selectByExample(example);
    	
    	Map<Integer, SysSettings> map = new HashMap<Integer, SysSettings>();
    	for (SysSettings setting : settings) {
    		map.put(setting.getItemId(), setting);
    	}
    	
		for (SysSettingsItem item : items) {
			
			//如果存在就检查下indexOrder是否相同，否则添加数据
			if (map.containsKey(item.getId())) {
				SysSettings setting = map.get(item.getId());
				if (item.getIndexOrder() != setting.getIndexOrder()) {
					setting.setIndexOrder(item.getIndexOrder());
					sysSettingsService.saveOrUpdate(setting);
				}
			} else {
				SysSettings setting = new SysSettings();
				setting.setUserId(up.getUserId());
				setting.setUserName(up.getUser().getName());	
				setting.setCreated(new Timestamp((new Date()).getTime()));
				setting.setCreatedBy(up.getLoginUserId());
				setting.setCreatedByName(up.getLoginUser().getName());
				setting.setUpdatedBy(up.getLoginUserId());
				setting.setUpdatedByName(up.getLoginUser().getName());
				setting.setClientId(up.getLoginClientId());
				setting.setOrgId(up.getLoginOrgId());
				setting.setSetClientId(up.getLoginClientId());
				setting.setSetOrgId(up.getLoginOrgId());
				setting.setSetUserId(up.getUserId());
				setting.setItemId(item.getId());
				setting.setIndexOrder(item.getIndexOrder());
				setting.setSetValueString("");
				
				sysSettingsService.saveOrUpdate(setting);
				
				Set<SysSettingsItemValue> itemValues = item.getSysSettingsItemValues();
				for (SysSettingsItemValue itemValue : itemValues) {
					
					if (1 == itemValue.getDefaulted()) {
						SysSettingsValues sysSettingsValues = new SysSettingsValues();
						sysSettingsValues.setItemvalueId(itemValue.getId());
						sysSettingsValues.setSettingsId(setting.getId());
						
						sysSettingsValuesService.saveOrUpdate(sysSettingsValues);
					}
				}
			}
    	}
	}

	private void init(){
		
    	try {
    		
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			
			itemList = sysSettingsItemService.getItemList();
			FacesUtils.setValueInHashtableOfSession("itemList", itemList);
			
			int accessLevel=up.getAccessDataLevelByStruts2().intValue();
			if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL) {
				FacesUtils.setValueInHashtableOfSession("orgVisible","true");
				userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
			} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG) {
				FacesUtils.setValueInHashtableOfSession("orgVisible","org");
				FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
			} else {
				FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
				FacesUtils.setValueInHashtableOfSession("orgVisible","false");
			}
			
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
	}
	
	public void ajaxUpdate() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			String id = request.getParameter("id");
			String setValueString = request.getParameter("setValueString");
			
			sysSettings = sysSettingsService.selectByPrimaryKey(Integer.parseInt(id));
			if (sysSettings != null) {
				sysSettings.setSetValueString(setValueString);
				sysSettingsService.saveOrUpdate(sysSettings);
			}
			
			response.getWriter().write((new Gson()).toJson(""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ajaxUpdateSelect() {
		
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			String itemValueIds = request.getParameter("itemValueIds");
			String settingsId = request.getParameter("settingsId");
			
			Map<String, Object> example = new HashMap<String, Object>();
	    	example.put("accessString", " 1 = 1 and settings_id = " + settingsId);
			sysSettingsValuesService.deleteByExample(example);
			
			String[] ids = itemValueIds.split(",");
			for (String id : ids) {
				SysSettingsValues values = new SysSettingsValues();
				values.setItemvalueId(Integer.parseInt(id));
				values.setSettingsId(Integer.parseInt(settingsId));
				
				sysSettingsValuesService.saveOrUpdate(values);
			}
			
			response.getWriter().write((new Gson()).toJson(""));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
    
	@Override
	public String getModuleName() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public void deleteList(Integer[] ids) {
		try {
			
			sysSettingsService.deleteSysSettingsList(ids);
		
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public Integer getModelId() {
		return null;
	}

	@Override
	public void editForm() {
		
		try {
			
		itemValueList = new ArrayList<DatadictTrl>();
		sysSettings = sysSettingsService.selectByPrimaryKey(id);
		initForEdit();
		
		init();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void initForEdit() {
		
		itemValueList = new ArrayList<DatadictTrl>();
		sysSettings = sysSettingsService.selectByPrimaryKey(sysSettings.getId());
		sysSettingsValues = sysSettings.getSysSettingsValues().iterator().next();
		Integer setType = sysSettings.getSysSettingsItem().getSetType();
		
		UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		String laguage = ur.getLocale();
		if (InputTypeEnum.NumInput.getValue().equals(setType) || InputTypeEnum.TestInput.getValue().equals(setType) ) {
			
			String setValueString = sysSettingsItemValueService.getItemValueName(sysSettingsValues.getSysSettingsItemValue(),laguage);
			sysSettings.setSetValueString(setValueString);
		}
		
		if (InputTypeEnum.SingelSelect.getValue().equals(setType)) {
			
			Set<SysSettingsItemValue> itemValuesSet = sysSettings.getSysSettingsItem().getSysSettingsItemValues();
			for (SysSettingsItemValue itemValue : itemValuesSet) {
				DatadictTrl trl = new DatadictTrl();
				trl.setDatadictId(itemValue.getId());
				
				String name = sysSettingsItemValueService.getItemValueName(itemValue,laguage);
				trl.setName(name);
				trl.setLanguage(null);
				trl.setValue(null);
				
				itemValueList.add(trl);
			}
			
			FacesUtils.setValueInHashtableOfSession("itemValueList","itemValueList");
		}
		
	}

	@Override
	public List search() {
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL){
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG){
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		}else{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		if (StringUtils.isNotEmpty(itemId)){
			sb.append(" and item_id in (").append(itemId).append(")");
	    }
		
		if (StringUtils.isNotEmpty(setValueString)){
			sb.append(" and set_value_string like '%").append(StringUtil.fullYhStr(setValueString).trim()).append("%'");
	    }
		
		if (StringUtils.isNotEmpty(userName)){
			sb.append(" and user_name like '%").append(StringUtil.fullYhStr(userName).trim()).append("%'");
	    }
		
		if (StringUtils.isNotEmpty(orgIds)){
			sb.append(" and set_org_id in (").append(orgIds).append(")");
	    }
		
	    if (StringUtil.isNotEmpty(sb.toString())) {
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		} else {
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			param.put("topnumber", " top 20 ");
		}
	    
	    String accessString = up.getAccessStringByStruts2();
		if (accessString.trim().equals("")) {
			accessString=" 1=1 " + " " + sb.toString();
		} else {
			accessString = accessString+  " " + sb.toString();	
		}
		
		param.put("accessString", accessString);
		param.put("orderByClause", " indexOrder asc ");
		
		sysSettingsList = sysSettingsService.selectByExample(param);
		/*UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		String language = ur.getLocale().toString();
		
		for (SysSettings sysSetting : sysSettingsList) {
			
			Set<SysSettingsValues> sysSettingsValues = sysSetting.getSysSettingsValues();
			Set<SysSettingsItemValue> values = sysSetting.getSysSettingsItem().getSysSettingsItemValues();
			List<SysSettingsValues> list = new ArrayList<SysSettingsValues>();
			for (SysSettingsItemValue value : values) {
				
				SysSettingsItemValue itemValue = value.getSysSettingsItemValue();
				String name = sysSettingsItemService.g getItemValueName(itemValue, language);
				value.setName(name);
			}
			
			// TODO
			sysSetting.setSysSettingsValuesList(list);
		}*/
	    return sysSettingsList;
	}

	@Override
	public void createForm() {
		
		sysSettings = new SysSettings();
		
		up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		sysSettings.setUserId(up.getUserId());
		sysSettings.setUserName(up.getUser().getName());	
		sysSettings.setCreated(new Timestamp((new Date()).getTime()));
		sysSettings.setCreatedBy(up.getLoginUserId());
		sysSettings.setCreatedByName(up.getLoginUser().getName());
		sysSettings.setUpdatedBy(up.getLoginUserId());
		sysSettings.setUpdatedByName(up.getLoginUser().getName());
		sysSettings.setClientId(up.getLoginClientId());
		sysSettings.setOrgId(up.getLoginOrgId());
		sysSettings.setSetClientId(up.getLoginClientId());
		sysSettings.setSetOrgId(up.getLoginOrgId());
		sysSettings.setSetUserId(up.getUserId());
		init();
	}
	
	public String saveOrUpdate() {
			
		try {
			
		
		if (sysSettings.getId() != null && sysSettings.getId() > 0) {
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			sysSettings.setUpdated(new Timestamp((new Date()).getTime()));
			sysSettings.setUpdatedBy(up.getLoginUserId());
			sysSettings.setUpdatedByName(up.getLoginUser().getName());
		}
		sysSettingsService.saveOrUpdate(sysSettings);
		sysSettings = sysSettingsService.selectByPrimaryKey(sysSettings.getId());
		Integer setType = sysSettings.getSysSettingsItem().getSetType();
		if (InputTypeEnum.SingelSelect.getValue().equals(setType)) {
			//删除后重新添加
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("accessString", " 1=1 and settings_id = " + sysSettings.getId());
			
			sysSettingsValuesService.deleteByExample(param);
			
			SysSettingsItemValue itemValue = sysSettingsItemValueService.selectByPrimaryKey(sysSettingsValues.getItemvalueId());
			
			SysSettingsValues sysSettingsValues = new SysSettingsValues();
			sysSettingsValues.setItemvalueId(itemValue.getId());
			sysSettingsValues.setSettingsId(sysSettings.getId());
			
			sysSettingsValuesService.saveOrUpdate(sysSettingsValues);
		}
		
		initForEdit();
		init();
		
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "edit";
	}
	

	@Override
	public String saveForm() {
		return null;
	}

	@Override
	public String getPurpose() {
		return null;
	}
	
	public PrivilegesDataFilter getUp() {
		return up;
	}

	public void setUp(PrivilegesDataFilter up) {
		this.up = up;
	}

	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<DatadictTrl> getItemList() {
		return itemList;
	}

	public void setItemList(List<DatadictTrl> itemList) {
		this.itemList = itemList;
	}

	public String getSetValueString() {
		return setValueString;
	}

	public void setSetValueString(String setValueString) {
		this.setValueString = setValueString;
	}


	public SysSettings getSysSettings() {
		return sysSettings;
	}

	public void setSysSettings(SysSettings sysSettings) {
		this.sysSettings = sysSettings;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<SysSettings> getSysSettingsList() {
		return sysSettingsList;
	}

	public void setSysSettingsList(List<SysSettings> sysSettingsList) {
		this.sysSettingsList = sysSettingsList;
	}
	
	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public List<DatadictTrl> getItemValueList() {
		return itemValueList;
	}

	public void setItemValueList(List<DatadictTrl> itemValueList) {
		this.itemValueList = itemValueList;
	}

	public SysSettingsValues getSysSettingsValues() {
		return sysSettingsValues;
	}

	public void setSysSettingsValues(SysSettingsValues sysSettingsValues) {
		this.sysSettingsValues = sysSettingsValues;
	}


}
