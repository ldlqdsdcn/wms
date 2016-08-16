package com.delmar.officeTest.web.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.Language;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestBankService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

public class OfficeTestBankAction extends CoreEditPrivAction{

	private OfficeTestBank officeTestBank;
	private List<OfficeTestBank> officeTestBankList;
	protected PrivilegesDataFilter up;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	private List<DatadictTrl> booleanList;
	private List<DatadictTrl> booleanListSort;
	private List<DatadictTrl> categoryList;
	private String code;
	private String name;
	private String beCalcScoreId;
	private String beOpenId;
	private String beStateId;
	private String orgIds;
	private String remark;
	private String categoryIds;
	private List<OfficeTestBankCategory> categories;
	

	@Autowired
	private OfficeTestBankService officeTestBankService;
	
	//@Autowired
	//private OfficeTestCategoryService officeTestCategoryService;
	
	@Autowired
	private OfficeTestBankCategoryService officeTestBankCategoryService;
	
	@Autowired
	private DatadictService datadictService;
	

	@Override
	public String list() {
		
    	init();
		
		return super.list();
	}
    
    private void init(){
		
    	try {
    		
			UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			booleanList = datadictService.getDatadictTrlByValue(DatadictType.BOOLEANLIST,ur.getLocale().toString());
			
			//是否开卷、作废默认 否
			booleanListSort = datadictService.getDatadictTrlByValue(DatadictType.BOOLEANLIST,ur.getLocale().toString());
			Collections.sort(booleanListSort, new Comparator<DatadictTrl>() {
	            public int compare(DatadictTrl arg0, DatadictTrl arg1) {
	                return arg0.getName().compareTo(arg1.getName());
	            }
	        });
			//categoryList = officeTestCategoryService.getAllCategorys();
					
			FacesUtils.setValueInHashtableOfSession("booleanList", booleanList);
			FacesUtils.setValueInHashtableOfSession("booleanListSort", booleanListSort);
			//FacesUtils.setValueInHashtableOfSession("categoryList", categoryList);
			
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
		
		if (StringUtils.isNotEmpty(code)){
			sb.append(" and code like '%").append(StringUtil.fullYhStr(code.trim())).append("%'");
	    }
		
		if (StringUtils.isNotEmpty(name)){
			sb.append(" and name like '%").append(StringUtil.fullYhStr(name.trim())).append("%'");
	    }
	    
	    if (StringUtils.isNotEmpty(beCalcScoreId)){
	    	sb.append(" and beCalcScore in (").append(beCalcScoreId).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(beOpenId)){
	    	sb.append(" and beOpen in (").append(beOpenId).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(beStateId)){
	    	sb.append(" and beState in (").append(beStateId).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(orgIds)){
	    	sb.append(" and org_id in (").append(orgIds).append(")");
	    }
	    
	    if (StringUtils.isNotEmpty(remark)){
	    	sb.append(" and remark = '").append(StringUtil.fullYhStr(remark.trim())).append("'");
	    }
	    
	    if (StringUtil.isNotEmpty(sb.toString())) {
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		} else {
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			param.put("topnumber", " top 20 ");
		}
	    
	    String accessString=up.getAccessStringByStruts2();
		if (accessString.trim().equals("")) {
			accessString=" 1=1 "+" "+sb.toString();
		} else {
			accessString=accessString+" "+sb.toString();	
		}

		param.put("accessString", accessString);
		officeTestBankList = officeTestBankService.selectByExample(param);
		
	    return officeTestBankList;
	}
	
    
    public String saveOrUpdate() {
    	
		if (officeTestBank.getId() != null && officeTestBank.getId() > 0) {
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			officeTestBank.setUpdated(new Timestamp((new Date()).getTime()));
			officeTestBank.setUpdatedBy(up.getLoginUserId());
			officeTestBank.setUpdatedByName(up.getLoginUser().getName());
		}
    	
    	officeTestBank = officeTestBankService.saveOrUpdate(officeTestBank, categoryIds);
    	
    	if (categories != null && categories.size() > 0) {
    		for (OfficeTestBankCategory category : categories) {
    			if (StringUtils.isNotEmpty(category.getCode()) && StringUtils.isNotEmpty(category.getName())) {
    				
    				category.setTestBankId(officeTestBank.getId());
    				officeTestBankCategoryService.saveOrUpdate(category);
    			}
    		}
    	}
    	
    	officeTestBank = officeTestBankService.selectByPrimaryKey(officeTestBank.getId());
    	
    	//增加3条空白记录
		for (int i = 0; i < 3; i++) {
			officeTestBank.getOfficeTestBankCategories().add(new OfficeTestBankCategory());
		}
		
    	init();
    	
    	return "edit";
    }
	
	public List<OfficeTestBank> getOfficeTestBankList() {
		return officeTestBankList;
	}

	public void setOfficeTestBankList(List<OfficeTestBank> officeTestBankList) {
		this.officeTestBankList = officeTestBankList;
	}

	public OfficeTestBank getOfficeTestBank() {
		return officeTestBank;
	}

	public void setOfficeTestBank(OfficeTestBank officeTestBank) {
		this.officeTestBank = officeTestBank;
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
	public Integer getModelId() {
		return null;
	}
	
	@Override
	public void editForm() {
		
		officeTestBank = officeTestBankService.selectByPrimaryKey(id);
		categories = new ArrayList<OfficeTestBankCategory>();
		categories.addAll(officeTestBank.getOfficeTestBankCategories());
		
		//增加3条空白记录
		for (int i = 0; i < 3; i++) {
			categories.add(new OfficeTestBankCategory());
		}
		init();
	}
	
	
	@Override
	public void createForm() {
		
		officeTestBank = new OfficeTestBank();
		up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		officeTestBank.setUserId(up.getUserId());
		officeTestBank.setUserName(up.getUser().getName());		
		officeTestBank.setCreated(new Date());
		officeTestBank.setCreatedBy(up.getLoginUserId());
		officeTestBank.setCreatedByName(up.getLoginUser().getName());
		officeTestBank.setUpdated(new Date());
		officeTestBank.setUpdatedBy(up.getLoginUserId());
		officeTestBank.setUpdatedByName(up.getLoginUser().getName());
		
		officeTestBank.setClientId(up.getLoginClientId());
		officeTestBank.setOrgId(up.getLoginOrgId());
		
		//增加5条空白记录
		categories = new ArrayList<OfficeTestBankCategory>();
		for (int i = 0; i < 5; i++) {
			categories.add(new OfficeTestBankCategory());
		}
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		officeTestBankService.deleteOfficeTestBankList(ids);

	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deletes()
	 */
	@Override
	public String deletes() {
		init();
		return super.deletes();
	}
	
	@Override
	public String saveForm() {
		return null;
	}
	
	@Override
	public String getPurpose() {
		return null;
	}
	
	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	public List<DatadictTrl> getBooleanList() {
		return booleanList;
	}

	public void setBooleanList(List<DatadictTrl> booleanList) {
		this.booleanList = booleanList;
	}
	
	 public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public List<DatadictTrl> getCategoryList() {
			return categoryList;
		}

		public void setCategoryList(List<DatadictTrl> categoryList) {
			this.categoryList = categoryList;
		}

		public String getCategoryIds() {
			return categoryIds;
		}

		public void setCategoryIds(String categoryIds) {
			this.categoryIds = categoryIds;
		}

		public List<OfficeTestBankCategory> getCategories() {
			return categories;
		}

		public void setCategories(List<OfficeTestBankCategory> categories) {
			this.categories = categories;
		}
		

		public PrivilegesDataFilter getUp() {
			return up;
		}

		public void setUp(PrivilegesDataFilter up) {
			this.up = up;
		}

		public String getBeCalcScoreId() {
			return beCalcScoreId;
		}

		public void setBeCalcScoreId(String beCalcScoreId) {
			this.beCalcScoreId = beCalcScoreId;
		}

		public String getBeOpenId() {
			return beOpenId;
		}

		public void setBeOpenId(String beOpenId) {
			this.beOpenId = beOpenId;
		}

		public String getBeStateId() {
			return beStateId;
		}

		public void setBeStateId(String beStateId) {
			this.beStateId = beStateId;
		}

		public String getOrgIds() {
			return orgIds;
		}

		public void setOrgIds(String orgIds) {
			this.orgIds = orgIds;
		}

		public List<DatadictTrl> getBooleanListSort() {
			return booleanListSort;
		}

		public void setBooleanListSort(List<DatadictTrl> booleanListSort) {
			this.booleanListSort = booleanListSort;
		}
}
