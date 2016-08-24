package com.delmar.officeTest.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.officeTest.model.OfficeTestExam;
import com.delmar.officeTest.model.OfficeTestExamDetail;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestBankService;
import com.delmar.officeTest.service.OfficeTestExamDetailService;
import com.delmar.officeTest.service.OfficeTestExamService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import com.google.gson.Gson;

public class OfficeTestExamAction extends CoreEditPrivAction{

	protected PrivilegesDataFilter up;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	private List<DatadictTrl> bankCategoryList; //测试分类
	private String orgIds;
	private String bankCategoryId;
	private String examUserName;
	private String beCancel;
	private OfficeTestExam officeTestExam;
	private List<DatadictTrl> booleanList;
	private List<OfficeTestExam> officeTestExamList;
	
	@Autowired
	private OfficeTestBankService officeTestBankService;
	
	@Autowired
	private OfficeTestBankCategoryService officeTestBankCategoryService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	private OfficeTestExamService officeTestExamService;
	
	@Autowired
	private OfficeTestExamDetailService officeTestExamDetailService;
	

	@Override
	public String list() {
		//默认显示不作废的
		//beCancel = "0";
    	init();
		
		return super.list();
	}
    
    private void init(){
		
    	try {
    		
			UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			booleanList = datadictService.getDatadictTrlByValue(DatadictType.BOOLEANLIST,ur.getLocale().toString());
			
			//isList 是false 则下拉框包括 请选择..
			bankCategoryList = officeTestBankService.getAllActiveBankCategorys(true);	
			
			FacesUtils.setValueInHashtableOfSession("booleanList", booleanList);
			
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
		
		if (StringUtils.isNotEmpty(orgIds)){
			sb.append(" and org_id in (").append(orgIds).append(")");
	    }
		if (StringUtils.isNotEmpty(bankCategoryId)){
			sb.append(" and testBank_Id in(").append(bankCategoryId).append(")");
	    }
		
	    if (StringUtils.isNotEmpty(examUserName)){
	    	sb.append(" and examUserName like '%").append(StringUtil.fullYhStr(examUserName.trim())).append("%'");
	    }
	    
	    if (StringUtils.isNotEmpty(beCancel)){
	    	sb.append(" and beCancel in (").append(beCancel).append(")");
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
		officeTestExamList = officeTestExamService.selectByExample(param);
		
	    return officeTestExamList;
	}
    
    public void ajaxCancelExam () {
    	try {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");   		
		
        up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		String id = request.getParameter("id");
		officeTestExam = officeTestExamService.selectByPrimaryKey(Integer.parseInt(id));
		if (officeTestExam != null) {
			officeTestExam.setBeCancel(1);
			officeTestExam.setUpdatedBy(up.getLoginUserId());
			officeTestExam.setUpdatedByName(up.getLoginUser().getName());
			
			officeTestExamService.saveOrUpdate(officeTestExam);
			
			Set<OfficeTestExamDetail> details = officeTestExam.getOfficeTestExamDetails();
			if (details != null && details.size() > 0) {
				for (OfficeTestExamDetail detail : details) {
					detail.setZfbz(1);
					officeTestExamDetailService.saveOrUpdate(detail);
				}
			}
		}
		
		response.getWriter().write((new Gson()).toJson(""));
		} catch (IOException e) {
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
		
	}
	
	@Override
	public Integer getModelId() {
		return null;
	}
	
	@Override
	public void editForm() {
	}
	
	
	@Override
	public void createForm() {
		
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

	public List<DatadictTrl> getBankCategoryList() {
		return bankCategoryList;
	}

	public void setBankCategoryList(List<DatadictTrl> bankCategoryList) {
		this.bankCategoryList = bankCategoryList;
	}
	public PrivilegesDataFilter getUp() {
		return up;
	}

	public void setUp(PrivilegesDataFilter up) {
		this.up = up;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getBankCategoryId() {
		return bankCategoryId;
	}

	public void setBankCategoryId(String bankCategoryId) {
		this.bankCategoryId = bankCategoryId;
	}

	public String getExamUserName() {
		return examUserName;
	}

	public void setExamUserName(String examUserName) {
		this.examUserName = examUserName;
	}

	public String getBeCancel() {
		return beCancel;
	}

	public void setBeCancel(String beCancel) {
		this.beCancel = beCancel;
	}

	public OfficeTestExam getOfficeTestExam() {
		return officeTestExam;
	}

	public void setOfficeTestExam(OfficeTestExam officeTestExam) {
		this.officeTestExam = officeTestExam;
	}
	
	public List<DatadictTrl> getBooleanList() {
		return booleanList;
	}

	public void setBooleanList(List<DatadictTrl> booleanList) {
		this.booleanList = booleanList;
	}

	public List<OfficeTestExam> getOfficeTestExamList() {
		return officeTestExamList;
	}

	public void setOfficeTestExamList(List<OfficeTestExam> officeTestExamList) {
		this.officeTestExamList = officeTestExamList;
	}

	
}
