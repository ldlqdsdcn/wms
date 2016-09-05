package com.delmar.sysSettings.web.action;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.service.UserService;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemTrl;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;
import com.delmar.sysSettings.service.SysSettingsItemService;
import com.delmar.sysSettings.service.SysSettingsItemTrlService;
import com.delmar.sysSettings.service.SysSettingsItemValueService;
import com.delmar.sysSettings.service.SysSettingsItemValueTrlService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.*;

public class SysSettingsItemAction extends CoreEditPrivAction{
	
	protected PrivilegesDataFilter up;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	protected List<SysSettingsItem> sysSettingsItemList;  
	private String value;
	private String name;
	private String setLevelId;
	private String setTypeId;
	private String remark;
	private SysSettingsItem sysSettingsItem;
	private List<DatadictTrl> accessList; // 访问级别
	private List<SysSettingsItemTrl> itemTrlList;
	private List<Language> languageList;
	private List<SysSettingsItemValue> itemValueList;
	private List<DatadictTrl> defaultedList;
	
	private List<DatadictTrl> inputTypeList; // 输入类型
	private String saveType;


	private static final long serialVersionUID = -1245836418680729739L;
	
	@Autowired
	private SysSettingsItemService sysSettingsItemService;
	
	@Autowired
	private SysSettingsItemTrlService sysSettingsItemTrlService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	SysSettingsItemValueService sysSettingsItemValueService;
	
	@Autowired
	SysSettingsItemValueTrlService sysSettingsItemValueTrlService;
	
	@Autowired
	UserService userService;
	
	@Override
	public String list() {
		
    	init();
		
		return super.list();
	}
    
    private void init(){
		
    	try {
    		
			UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			accessList = datadictService.getDatadictTrlByValue(DatadictType.ACCESS_LEVEL,ur.getLocale().toString());
			inputTypeList = sysSettingsItemService.getInputTypeList();
			defaultedList = sysSettingsItemService.getDefaultedList();
			
			FacesUtils.setValueInHashtableOfSession("accessList", accessList);
			FacesUtils.setValueInHashtableOfSession("inputTypeList", inputTypeList);
			FacesUtils.setValueInHashtableOfSession("defaultedList", defaultedList);
			
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
			
			languageList = languageService.selectByExample(null);
			 Collections.sort(languageList, new Comparator<Language>() {
		            public int compare(Language arg0, Language arg1) {
		                return arg0.getCode().compareTo(arg1.getCode());
		            }
		        });
			itemTrlList = initItemTrlList(sysSettingsItem, languageList);
			itemValueList = initItemValueList(sysSettingsItem, languageList);
    	} catch (Throwable e) {
    		e.printStackTrace();
    	}
	}
    

	private List<SysSettingsItemValue> initItemValueList(SysSettingsItem item, List<Language> languages) {
		
		List<SysSettingsItemValue> returnList = new ArrayList<SysSettingsItemValue>();
		if (item == null || item.getId() == null || item.getId() == 0) {
			//创建5条空白记录
			int nums = 5;
			returnList = createItemValues(nums);
			
		} else {
			
			Map<String, Object> example = new HashMap<String, Object>();
			example.put("accessString", " 1 = 1 and item_id = " + item.getId());
			
			List<SysSettingsItemValue> itemValues = sysSettingsItemValueService.selectByExample(example);
			for (SysSettingsItemValue itemValue : itemValues) {
				
				List<SysSettingsItemValueTrl> valueTrlList = new ArrayList<SysSettingsItemValueTrl>();
				Set<SysSettingsItemValueTrl> valueTrls = itemValue.getSysSettingsItemValueTrls();
				valueTrlList.addAll(valueTrls);
				Collections.sort(valueTrlList);
				
				itemValue.setSysSettingsItemValueTrlList(valueTrlList);
				returnList.add(itemValue);
			}
			
			returnList.addAll(createItemValues(3));
		}
		return returnList;
	}
	

	/**
	 * 创建指定条数的空白记录
	 * @param length
	 * @return
	 */
	
	private List<SysSettingsItemValue> createItemValues(int length) {
		
		List<SysSettingsItemValue> returnList = new ArrayList<SysSettingsItemValue>();
		for (int i = 0; i < length; i ++) {
			SysSettingsItemValue itemValue = new SysSettingsItemValue();
			returnList.add(itemValue);
		}
		return returnList;
	}

	private List<SysSettingsItemTrl> initItemTrlList(SysSettingsItem item, List<Language> languages) {
		
		List<SysSettingsItemTrl> list = new ArrayList<SysSettingsItemTrl>();
		if (item == null || item.getId() == null || item.getId() == 0) {
			for (Language language : languages) {
				SysSettingsItemTrl trl = new SysSettingsItemTrl();
				trl.setLanguage(language.getCode());
				list.add(trl);
			}
			return list;
		} else {
			itemTrlList = sysSettingsItemTrlService.getItemTrlList(item.getId());
			for (Language language : languages) {
				
				boolean hasLanguage = false;
				for (SysSettingsItemTrl trl : itemTrlList) {
					if (trl.getLanguage().equals(language.getCode())) {
						list.add(trl);
						hasLanguage = true;
					}
				}
				
				if (!hasLanguage) {
					SysSettingsItemTrl trl = new SysSettingsItemTrl();
					trl.setLanguage(language.getCode());
					list.add(trl);
				}
			}
			
			return list;
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
			
		
		sysSettingsItemService.deleteSysSettingsItemList(ids);
		
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
		sysSettingsItem = sysSettingsItemService.selectByPrimaryKey(id);
		itemTrlList = new ArrayList<SysSettingsItemTrl>();
		itemTrlList.addAll(sysSettingsItem.getSysSettingsItemTrls());
		
		init();
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
		
		if (StringUtils.isNotEmpty(value)){
			sb.append(" and value like '%").append(StringUtil.fullYhStr(value).trim()).append("%'");
	    }
		
		if (StringUtils.isNotEmpty(name)){
			sb.append(" and name like '%").append(StringUtil.fullYhStr(name).trim()).append("%'");
	    }
		
	    if (StringUtils.isNotEmpty(setLevelId)){
	    	sb.append(" and set_level in (").append(setLevelId).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(setTypeId)){
	    	sb.append(" and set_type in (").append(setTypeId).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(remark)){
			sb.append(" and remark like '%").append(StringUtil.fullYhStr(remark).trim()).append("%'");
	    }
	    
	    if (StringUtil.isNotEmpty(sb.toString())) {
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		} else {
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
		}
	    
	    String accessString = up.getAccessStringByStruts2();
		if (accessString.trim().equals("")) {
			accessString=" 1=1 " + " " + sb.toString();
		} else {
			accessString = accessString+  " " + sb.toString();	
		}

		param.put("accessString", accessString);
		param.put("orderByClause", " indexOrder asc ");
		
		sysSettingsItemList = sysSettingsItemService.selectByExample(param);
		
	    return sysSettingsItemList;
	}

	@Override
	public void createForm() {
		
		sysSettingsItem = new SysSettingsItem();
		up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		sysSettingsItem.setUserId(up.getUserId());
		sysSettingsItem.setUserName(up.getUser().getName());	
		sysSettingsItem.setCreated(new Timestamp((new Date()).getTime()));
		sysSettingsItem.setCreatedBy(up.getLoginUserId());
		sysSettingsItem.setCreatedByName(up.getLoginUser().getName());
		sysSettingsItem.setUpdatedBy(up.getLoginUserId());
		sysSettingsItem.setUpdatedByName(up.getLoginUser().getName());
		sysSettingsItem.setClientId(up.getLoginClientId());
		sysSettingsItem.setOrgId(up.getLoginOrgId());
		
		init();
	}
	
	public String saveOrUpdate() {
		
		boolean save = true;
		if (sysSettingsItem.getId() != null && sysSettingsItem.getId() > 0) {
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			sysSettingsItem.setUpdated(new Timestamp((new Date()).getTime()));
			sysSettingsItem.setUpdatedBy(up.getLoginUserId());
			sysSettingsItem.setUpdatedByName(up.getLoginUser().getName());
			save = false;
		}
		
		sysSettingsItemService.saveOrUpdate(sysSettingsItem);
		if (itemTrlList != null && itemTrlList.size() > 0) {
			for (SysSettingsItemTrl trl : itemTrlList) {
				
				if (trl.getName() == null || trl.getName().trim().length() <= 0) {
					continue;
				}
				trl.setItemId(sysSettingsItem.getId());
				sysSettingsItemTrlService.saveOrUpdate(trl);
			}
		}
		
		if (itemValueList != null && itemValueList.size() > 0) {
			for (SysSettingsItemValue itemValue : itemValueList) {
				
				if (itemValue.getValue() == null || itemValue.getValue().trim().length() <= 0) {
					continue;
				}
				
				itemValue.setItemId(sysSettingsItem.getId());
				sysSettingsItemValueService.saveOrUpdate(itemValue);
				
				List<SysSettingsItemValueTrl> valueTrls = itemValue.getSysSettingsItemValueTrlList();
				for (SysSettingsItemValueTrl trl : valueTrls) {
					
					if (trl == null || trl.getName() == null || trl.getName().trim().length() <= 0) {
						continue;
					}
					
					trl.setItemvalueId(itemValue.getId());
					sysSettingsItemValueTrlService.saveOrUpdate(trl);
				}
			}
		}
		
		init();
		
		//保存并新增
		if ("1".equals(saveType)) {
			create();
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

	public List<SysSettingsItem> getSysSettingsItemList() {
		return sysSettingsItemList;
	}

	public void setSysSettingsItemList(List<SysSettingsItem> sysSettingsItemList) {
		this.sysSettingsItemList = sysSettingsItemList;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SysSettingsItem getSysSettingsItem() {
		return sysSettingsItem;
	}

	public void setSysSettingsItem(SysSettingsItem sysSettingsItem) {
		this.sysSettingsItem = sysSettingsItem;
	}
	
	public List<DatadictTrl> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<DatadictTrl> accessList) {
		this.accessList = accessList;
	}

	public List<SysSettingsItemTrl> getItemTrlList() {
		return itemTrlList;
	}

	public void setItemTrlList(List<SysSettingsItemTrl> itemTrlList) {
		this.itemTrlList = itemTrlList;
	}

	public List<DatadictTrl> getInputTypeList() {
		return inputTypeList;
	}

	public void setInputTypeList(List<DatadictTrl> inputTypeList) {
		this.inputTypeList = inputTypeList;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSetLevelId() {
		return setLevelId;
	}

	public void setSetLevelId(String setLevelId) {
		this.setLevelId = setLevelId;
	}

	public String getSetTypeId() {
		return setTypeId;
	}

	public void setSetTypeId(String setTypeId) {
		this.setTypeId = setTypeId;
	}

	public List<Language> getLanguageList() {
		return languageList;
	}

	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	public List<SysSettingsItemValue> getItemValueList() {
		return itemValueList;
	}

	public void setItemValueList(List<SysSettingsItemValue> itemValueList) {
		this.itemValueList = itemValueList;
	}

	public List<DatadictTrl> getDefaultedList() {
		return defaultedList;
	}

	public void setDefaultedList(List<DatadictTrl> defaultedList) {
		this.defaultedList = defaultedList;
	}


}
