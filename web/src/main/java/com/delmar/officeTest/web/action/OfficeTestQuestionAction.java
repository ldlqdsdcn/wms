package com.delmar.officeTest.web.action;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.model.OfficeTestQuestion;
import com.delmar.officeTest.service.OfficeTestBankService;
import com.delmar.officeTest.service.OfficeTestQuestionService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import com.google.gson.Gson;

public class OfficeTestQuestionAction extends CoreEditPrivAction{

	private OfficeTestQuestion officeTestQuestion;
	private OfficeTestBank officeTestBank;
	private List<OfficeTestQuestion> officeTestQuestionList;
	protected PrivilegesDataFilter up;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	private List<DatadictTrl> bankCategoryList; //测试分类
	private List<DatadictTrl> categoryList;  //知识点
	private List<DatadictTrl> questionTypeList;  //试题类型
	private String bankCategoryId;
	private String questionTypeId;
	private String content;
	private String categoryId;
	private String saveType;
	private File excelFile;
	private String impMsg;
	

	

	@Autowired
	private OfficeTestBankService officeTestBankService;
	
	@Autowired
	private OfficeTestQuestionService officeTestQuestionService;
	
	@Autowired
	private DatadictService datadictService;
	

	@Override
	public String list() {
		
    	init(true);
		
		return super.list();
	}
    
    private void init(boolean isList){
		
    	try {
    		
			UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			
			bankCategoryList = officeTestBankService.getAllActiveBankCategorys(isList);
			questionTypeList = datadictService.getDatadictTrlByValue(DatadictType.QUESTIONTYPE, ur.getLocale().toString());
			categoryList = officeTestBankService.categoryDatadictTrlList(null);
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
		
		if (StringUtils.isNotEmpty(bankCategoryId)){
			sb.append(" and testbank_id in (").append(bankCategoryId).append(")");
	    }
		
		if (StringUtils.isNotEmpty(questionTypeId)){
			sb.append(" and type_id in (").append(questionTypeId).append(")");
	    }
		
	    if (StringUtils.isNotEmpty(content)){
	    	sb.append(" and content like '%").append(StringUtil.fullYhStr(content.trim())).append("%'");
	    }
	    
	    if (StringUtils.isNotEmpty(categoryId)){
	    	sb.append(" and category_id in (").append(categoryId).append(")");
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
			accessString=" 1=1 "+" "+sb.toString();
		} else {
			accessString=accessString+" "+sb.toString();	
		}

		param.put("accessString", accessString);
		officeTestQuestionList = officeTestQuestionService.selectByExample(param);
	    return officeTestQuestionList;
	}
	
    
    public String saveOrUpdate() {
    	
    	//更新
    	if (officeTestQuestion.getId() != null && officeTestQuestion.getId() > 0) {
			up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			officeTestQuestion.setUpdated(new Timestamp((new Date()).getTime()));
			officeTestQuestion.setUpdatedBy(up.getLoginUserId());
			officeTestQuestion.setUpdatedByName(up.getLoginUser().getName());
		}
    	if ("0".equals(saveType)) {
    		officeTestQuestion = officeTestQuestionService.saveOrUpdate(officeTestQuestion);
    		init(false);
    		
    		//保存并新增
    	} else {
    		 officeTestQuestionService.saveOrUpdate(officeTestQuestion);
    		 create();
    	}
    		
    	return "edit";
    }
    
    /**
     * 进入到导入页面.
     * @return
     */
    public String toImpExcel() {
    	
    	return "toExcelImp";
    }
    
    /**
     * 开始执行导入.
     * @return
     */
    public String impExcel() {
    	
    	try {
    		UserResource ur = (UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
    		String language = ur.getLocale().toString();
    		up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
    		OfficeTestQuestion question = new OfficeTestQuestion();
    		
    		question.setUserId(up.getUserId());
    		question.setUserName(up.getUser().getName());		
    		question.setCreated(new Date());
    		question.setCreatedBy(up.getLoginUserId());
    		question.setCreatedByName(up.getLoginUser().getName());
    		question.setUpdated(new Date());
    		question.setUpdatedBy(up.getLoginUserId());
    		question.setUpdatedByName(up.getLoginUser().getName());
    		question.setClientId(up.getLoginClientId());
    		question.setOrgId(up.getLoginOrgId());
    		
    		impMsg = officeTestQuestionService.impExcel(excelFile,officeTestBank.getId(), question, language);
		} catch (Exception e) {
			e.printStackTrace();
			impMsg = "导入失败！";
		}
    	return "toExcelImp";
    }
    
    /* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		officeTestQuestionService.deleteOfficeTestQuestionList(ids);

	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deletes()
	 */
	@Override
	public String deletes() {
		init(true);
		return super.deletes();
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
		officeTestQuestion = officeTestQuestionService.selectByPrimaryKey(id);
		init(false);
	}
	
	@Override
	public void createForm() {
		
		officeTestQuestion = new OfficeTestQuestion();
		up = (PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		officeTestQuestion.setUserId(up.getUserId());
		officeTestQuestion.setUserName(up.getUser().getName());		
		officeTestQuestion.setCreated(new Timestamp((new Date()).getTime()));
		officeTestQuestion.setCreatedBy(up.getLoginUserId());
		officeTestQuestion.setCreatedByName(up.getLoginUser().getName());
		officeTestQuestion.setUpdated(new Timestamp((new Date()).getTime()));
		officeTestQuestion.setUpdatedBy(up.getLoginUserId());
		officeTestQuestion.setUpdatedByName(up.getLoginUser().getName());
		officeTestQuestion.setClientId(up.getLoginClientId());
		officeTestQuestion.setOrgId(up.getLoginOrgId());
		init(false);
	}
	
	public void ajaxGetCategorys() {
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        List<OfficeTestBankCategory> list = new ArrayList<OfficeTestBankCategory>();
			String testBankId = request.getParameter("testBankId");
			if (testBankId != null && testBankId.length() > 0) {
				list = officeTestBankService.categoryList(Integer.parseInt(testBankId));
			}
			
			OfficeTestBankCategory[] detailArray = new OfficeTestBankCategory[list.size()];
			list.toArray(detailArray);
				
			response.getWriter().write((new Gson()).toJson(detailArray));
		} catch (Throwable e) {
			e.printStackTrace();
		}
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


		public List<DatadictTrl> getCategoryList() {
			return categoryList;
		}

		public void setCategoryList(List<DatadictTrl> categoryList) {
			this.categoryList = categoryList;
		}


		public OfficeTestQuestion getOfficeTestQuestion() {
			return officeTestQuestion;
		}

		public void setOfficeTestQuestion(OfficeTestQuestion officeTestQuestion) {
			this.officeTestQuestion = officeTestQuestion;
		}

		public List<OfficeTestQuestion> getOfficeTestQuestionList() {
			return officeTestQuestionList;
		}

		public void setOfficeTestQuestionList(List<OfficeTestQuestion> officeTestQuestionList) {
			this.officeTestQuestionList = officeTestQuestionList;
		}

		public List<DatadictTrl> getBankCategoryList() {
			return bankCategoryList;
		}

		public void setBankCategoryList(List<DatadictTrl> bankCategoryList) {
			this.bankCategoryList = bankCategoryList;
		}

		public List<DatadictTrl> getQuestionTypeList() {
			return questionTypeList;
		}

		public void setQuestionTypeList(List<DatadictTrl> questionTypeList) {
			this.questionTypeList = questionTypeList;
		}
		
		public String getBankCategoryId() {
			return bankCategoryId;
		}

		public void setBankCategoryId(String bankCategoryId) {
			this.bankCategoryId = bankCategoryId;
		}

		public String getQuestionTypeId() {
			return questionTypeId;
		}

		public void setQuestionTypeId(String questionTypeId) {
			this.questionTypeId = questionTypeId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(String categoryId) {
			this.categoryId = categoryId;
		}

		public String getSaveType() {
			return saveType;
		}

		public void setSaveType(String saveType) {
			this.saveType = saveType;
		}

		public File getExcelFile() {
			return excelFile;
		}

		public void setExcelFile(File excelFile) {
			this.excelFile = excelFile;
		}
		
		public OfficeTestBank getOfficeTestBank() {
			return officeTestBank;
		}

		public void setOfficeTestBank(OfficeTestBank officeTestBank) {
			this.officeTestBank = officeTestBank;
		}

		public String getImpMsg() {
			return impMsg;
		}

		public void setImpMsg(String impMsg) {
			this.impMsg = impMsg;
		}
}
