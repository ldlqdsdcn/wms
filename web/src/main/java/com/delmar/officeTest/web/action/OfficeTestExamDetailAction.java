package com.delmar.officeTest.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestExam;
import com.delmar.officeTest.model.OfficeTestExamDetail;
import com.delmar.officeTest.model.OfficeTestQuestion;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestBankService;
import com.delmar.officeTest.service.OfficeTestExamDetailService;
import com.delmar.officeTest.service.OfficeTestExamService;
import com.delmar.officeTest.service.OfficeTestQuestionService;
import com.delmar.officeTest.utils.XMLParseImpl;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;
import com.google.gson.Gson;

public class OfficeTestExamDetailAction extends CoreEditPrivAction{

	private String testBankId;
	private List<DatadictTrl> bankCategoryList; //测试分类
	private List<DatadictTrl> questionTypeList;  //试题类型
	private List<DatadictTrl> selectTypeList;  //查询类型
	protected PrivilegesDataFilter up;
	private String beOpen;
	private String ksrq;
	private String kstesttime;
	private String ksstcount;
	private String beCalcScore;
	private OfficeTestExam officeTestExam;
	private OfficeTestExamDetail officeTestExamDetail;
	private OfficeTestBank officeTestBank;
	private boolean belastquestion;
	private String useTime;
	private String savestate;
	private String reminder;
	private List<OfficeTestExamDetail> officeTestExamDetailList;
	private String testId;
	private String bankCategoryId;
	private String questionTypeId;
	private String selectTypeId;
	private String peopleJob;
	private List<OfficeTestExamDetail> officeTestExamDetails;
	private String doAction;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	
	

	@Autowired
	private OfficeTestBankService officeTestBankService;
	
	@Autowired
	private OfficeTestExamDetailService officeTestExamDetailService;
	
	@Autowired
	private OfficeTestExamService officeTestExamService;
	
	@Autowired
	private OfficeTestQuestionService officeTestQuestionService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	OfficeTestBankCategoryService officeTestBankCategoryService;
	
	
	public String toExam() {
		
		bankCategoryList = officeTestBankService.getAllActiveBankCategorys(false);
		return "toExam";
	}
	
	public String start() {
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Integer operatorID = up.getLoginUserId();

		String beIndex = "sort";
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		// 首先这里进行查找，看一看此人是否已经做过考试
		sb.append(" and examUserId = ").append(operatorID);
		sb.append(" and testBank_Id = ").append(testBankId);
	    sb.append(" and beCancel = 0");
	    
		param.put("accessString", " 1=1 " + " " + sb.toString());
		
		//从成绩表查询
		List<OfficeTestExam> list = officeTestExamService.selectByExample(param);
		
		//查询这套题目
		officeTestBank = officeTestBankService.selectByPrimaryKey(Integer.parseInt(testBankId));

		if (list == null || list.size() <= 0) {
			// 创建试题，准备考试
			
			Map<String, Object> param2 = new HashMap<String, Object>();
			StringBuffer strBuf = new StringBuffer();
			if (beIndex.equals("sort")) {
				strBuf.append(" order by newid()");
			} else {
				strBuf.append(" order by a.questionID ");
			}
		    
			param2.put("orderBy", strBuf.toString());
			param2.put("examUserId", operatorID);
			param2.put("testBankId", testBankId);
			
			param2.put("typeId1", getTypeIds("value = 'RADIO_TOPIC' or value = 'JUDGE_TOPIC' or value = 'MULTISELECT_TOPIC'"));
			param2.put("typeId2", getTypeIds("value = 'SHORT_TOPIC' "));
			
			/*单选题 -- RADIO_TOPIC  
			判断题 -- JUDGE_TOPIC
			多选题 -- MULTISELECT_TOPIC
			简答题 -- SHORT_TOPIC*/
			List<OfficeTestQuestion> questionList = officeTestQuestionService.searchQuestion(param2);

			XMLParseImpl.getInstance(String.valueOf(operatorID)).initDocument(questionList, officeTestBank);
			
			//判断一下是否是开放统计
			beOpen = String.valueOf(officeTestBank.getBeOpen());
			ksrq = getLongHisDateStr(0);
			kstesttime = String.valueOf(officeTestBank.getTimeLimit());
			ksstcount = String.valueOf(officeTestBank.getQuestionCount());

			return "start";
		} else {
			officeTestExam = list.get(0);
			
			return "alreadyExam";
		}
	}
	
	public String getTypeIds(String andStr) {
		
		Map<String, Object> example = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		// 首先这里进行查找，看一看此人是否已经做过考试
		sb.append(" and (" + andStr + ")").append("");
	    
		example.put("accessString", " 1=1 " + " " + sb.toString());
		List<Datadict> typeIds1 = datadictService.selectByExample(example);
		String typeId1 = "";
		int i = 0;
		for (Datadict data : typeIds1) {
			if (i == 0) {
				typeId1 += data.getId();
			} else {
				typeId1 += "," + data.getId();
			}
			
			i ++;
		}
		
		return typeId1;
	}
	
	public void ajaxGetTM() {
		
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        List<OfficeTestExamDetail> list = new ArrayList<OfficeTestExamDetail>();
			String currtmbh = request.getParameter("currtmbh");
			String nexttmbh = request.getParameter("nexttmbh");
			String tmda = request.getParameter("tmda");
			//String time = request.getParameter("time");
			
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			Integer operatorID = up.getLoginUserId();
			
			if (tmda == null)
				tmda = "";
			
			if (currtmbh == null)
				currtmbh = "0";

			if (nexttmbh == null)
				nexttmbh = "1";
			
			// 首先把当前的答案存入相应的XML文件当中
			if (!tmda.equals("")) {
				XMLParseImpl.getInstance(String.valueOf(operatorID)).
					saveOneKsda(Integer.parseInt(currtmbh), tmda);
			}
				
			// 这里在得到下一题的信息
			// 考题数量
			String ktsl = XMLParseImpl.getInstance(String.valueOf(operatorID)).
						getKsxx("ktsl");
			belastquestion = false;
			if (nexttmbh.equals(ktsl)) {
				nexttmbh = String.valueOf(Integer.parseInt(ktsl) - 1);
				belastquestion = true;
			} else if (nexttmbh.equals(String.valueOf(Integer.parseInt(ktsl) - 1))) {
				belastquestion = true;
			}

			OfficeTestExamDetail officetestexamdetail = XMLParseImpl.getInstance(String.valueOf(operatorID)
					).getNextTM(Integer.parseInt(nexttmbh));
			
			officetestexamdetail.setBelastquestion(belastquestion);
			
			OfficeTestExamDetail[] detailArray = new OfficeTestExamDetail[1];
			list.add(officetestexamdetail);
			list.toArray(detailArray);
			response.getWriter().write((new Gson()).toJson(detailArray));
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String saveTest() {
			
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		Integer operatorID = up.getLoginUserId();

		String kssc = useTime;
		if (kssc == null)
			kssc = "0";

		if (savestate == null)
			savestate = "";
		
		if (savestate.equals(""))
			savestate = "submit";

		String ksTotalSc = XMLParseImpl.getInstance(String.valueOf(operatorID)).getKsxx("kssc");

		int useTime = Integer.parseInt(ksTotalSc) - Integer.parseInt(kssc) + 1;

		XMLParseImpl.getInstance(String.valueOf(operatorID)).saveKsxx("kssc", String.valueOf(useTime));

		// 对XML文件进行解析，把解析出来的内容存入到数据库中
		List<OfficeTestExamDetail> testPaperVec = new ArrayList<OfficeTestExamDetail>(); // 存储考试的答案
		officeTestExam = new OfficeTestExam(); // 考试成绩
		XMLParseImpl.getInstance(String.valueOf(operatorID)).parseXMLToObj(officeTestExam, testPaperVec);
		
		if (savestate.equals("submit")) {
			
			officeTestExam.setUserId(up.getUserId());
			officeTestExam.setUserName(up.getUser().getName());		
			officeTestExam.setExamUserId(up.getUserId());
			officeTestExam.setExamUserName(up.getUser().getName());
			officeTestExam.setCreated(new Date());
			officeTestExam.setCreatedBy(up.getLoginUserId());
			officeTestExam.setCreatedByName(up.getLoginUser().getName());
			officeTestExam.setCreated(new Date());
			officeTestExam.setUpdatedBy(up.getLoginUserId());
			officeTestExam.setUpdatedByName(up.getLoginUser().getName());
			
			officeTestExam.setClientId(up.getLoginClientId());
			officeTestExam.setOrgId(up.getLoginOrgId());
			
			officeTestExamService.saveOrUpdate(officeTestExam);
		} 
		
		//删掉重新插入
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" examUserId = ").append(operatorID);
		sb.append(" and testBank_Id = ").append(officeTestExam.getTestBankId());
		
		map.put("accessString ", sb.toString());
		officeTestExamDetailService.deleteByExample(map);
		for (int i = 0; i < testPaperVec.size(); i++) {
			
			OfficeTestExamDetail detail = (OfficeTestExamDetail) testPaperVec.get(i);
			detail.setExamUserId(up.getUserId());
			detail.setExamUserName(up.getUser().getName());
			detail.setExamId(officeTestExam.getId());
			detail.setZfbz(0);
			officeTestExamDetailService.saveOrUpdate(detail);
		}

		officeTestBank = officeTestBankService.selectByPrimaryKey(officeTestExam.getTestBankId());
		officeTestExamDetailList = testPaperVec;
		XMLParseImpl.getInstance(String.valueOf(operatorID)).removeDocument();

		return "end";
		
		
	}
	
	public void ajaxCanViewTest() {
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			Integer operatorID = up.getLoginUserId();
			
			if (testId == null)
				testId = "0";

			officeTestExam = officeTestExamService.selectByPrimaryKey(Integer.parseInt(testId));
			
			boolean canView = true;
			if (!operatorID.equals(officeTestExam.getExamUserId())){
				if (!(PrivilegeOperator.isView())){
					canView = false;
					//throw new Exception("你不具有相应的权限察看别人的考试记录");
				}
			}
			
			response.getWriter().write((new Gson()).toJson(canView));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String viewTest() {
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		if (testId == null)
			testId = "0";

		officeTestExam = officeTestExamService.selectByPrimaryKey(Integer.parseInt(testId));
		
		Map<String, Object> parm = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		sb.append(" and b.exam_id = ").append(testId);
		
		parm.put("andSelect", sb.toString());
		
		List<OfficeTestExamDetail> testPaperVec = officeTestExamDetailService.searchQuestionTestDetail(parm);

		officeTestBank = officeTestBankService.selectByPrimaryKey(officeTestExam.getTestBankId());
		officeTestExamDetailList = testPaperVec;
		
		return "end";
	}
	
	public void ajaxValidateSave() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
	        response.setCharacterEncoding("UTF-8");   		
			
	        up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			Integer operatorID = up.getLoginUserId();
	        // 对XML文件进行解析，把解析出来的内容存入到数据库中
			List<OfficeTestExamDetail> testPaperVec = new ArrayList<OfficeTestExamDetail>(); // 存储考试的答案
			officeTestExam = new OfficeTestExam(); // 考试成绩
			XMLParseImpl.getInstance(String.valueOf(operatorID)).parseXMLToObj(officeTestExam, testPaperVec);
			
			// 判断是否有没有做的题
			boolean nullflag = true;
			for (int i = 0; i < testPaperVec.size(); i++) {
				OfficeTestExamDetail detail = (OfficeTestExamDetail) testPaperVec.get(i);
				
				// 简单题不判断
				OfficeTestQuestion question = officeTestQuestionService.selectByPrimaryKey(detail.getTestQuestionId());
				Datadict datadicet = datadictService.selectByPrimaryKey(question.getTypeId());
				if(!"4".equals(datadicet.getValue())){
					if(StringUtils.isEmpty(detail.getExamAnswer())){
						nullflag = false;
						break;
					}
				}
				
			}
			
			response.getWriter().write((new Gson()).toJson(nullflag));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String list() {
		
		try {
			
		
    	init(true);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return super.list();
	}
    
    private void init(boolean isList){
		
    	UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		bankCategoryList = officeTestBankService.getAllActiveBankCategorys(isList);
		questionTypeList = datadictService.getDatadictTrlByValue(DatadictType.QUESTIONTYPE, ur.getLocale().toString());
		selectTypeList = datadictService.getDatadictTrlByValue(DatadictType.SELECTTYPE, ur.getLocale().toString());
		
		FacesUtils.setValueInHashtableOfSession("bankCategoryList", bankCategoryList);
		FacesUtils.setValueInHashtableOfSession("questionTypeList", questionTypeList);
		FacesUtils.setValueInHashtableOfSession("selectTypeList", selectTypeList);
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
	public List search() {
		try {
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
		
		if (doAction == null || doAction.equals("")) {
			doAction = "start";
		}
		
		//题目类型
		Map<Integer, String> map = new HashMap<Integer, String>();
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		questionTypeList = datadictService.getDatadictTrlByValue(DatadictType.QUESTIONTYPE, ur.getLocale().toString());
		for (DatadictTrl trl : questionTypeList) {
			map.put(trl.getDatadictId(), trl.getName());
		}
		
		//从菜单进入查看人员分析
		if (doAction == "start") {
			
			officeTestExamDetails = getStartList(map);
			
		} else {
			
			//进入人员分析后 按照汇总方式，查看具体明细
			
			officeTestExamDetails = getDetailList(viewDetail(), map);
		}
	} catch (Throwable e) {
		e.printStackTrace();
	}
		return officeTestExamDetails;
	}
	
	private List<OfficeTestExamDetail> getStartList(Map<Integer, String> map) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		if (StringUtils.isNotEmpty(bankCategoryId)){
			sb.append(" and d.testbank_Id in (").append(bankCategoryId).append(")");
	    }
		
		if (StringUtils.isNotEmpty(questionTypeId)){
			sb.append(" and t.type_id in (").append(questionTypeId).append(")");
	    }
		
		if (StringUtils.isNotEmpty(peopleJob)){
			sb.append(" and d.examUserId in (").append(getJobIds(peopleJob)).append(")");
	    }
		
	    if (StringUtil.isNotEmpty(sb.toString())) {
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		} else {
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			param.put("topnumber", " top 20 ");
		}
	    
		param.put("andSelect", sb.toString());
		param.put("orderBy", " order by d.ExamUserId, d.testBank_Id, d.testQuestion_id");
		officeTestExamDetails = officeTestExamDetailService.selectByParm(param);
		
		if (selectTypeId == null) {
			selectTypeId = "0";
		}
		
		//汇总情况
		if ("0".equals(selectTypeId)) {
			
			officeTestExamDetails = getSummaryList(officeTestExamDetails, map);
			
			return officeTestExamDetails;
			
		//明细情况 
		} else {
			
			officeTestExamDetails = getDetailList(officeTestExamDetails, map);
			
			return officeTestExamDetails;
			
		}
	}

	private List<OfficeTestExamDetail> getDetailList( List<OfficeTestExamDetail> officeTestExamDetails2, Map<Integer, String> map) {
		int aAcount = 0;
		int bAcount = 0;
		int cAcount = 0;
		int dAcount = 0;
		int eAcount = 0;
		for (OfficeTestExamDetail detail : officeTestExamDetails2) {
			detail.setQuestionType(map.get(detail.getOfficeTestQuestion().getTypeId()));
			if ("A".equals(detail.getExamAnswer())) {
				aAcount += 1;
			}
			if ("B".equals(detail.getExamAnswer())) {
				bAcount += 1;
			}
			if ("C".equals(detail.getExamAnswer())) {
				cAcount += 1;
			}
			if ("D".equals(detail.getExamAnswer())) {
				dAcount += 1;
			}
			if ("E".equals(detail.getExamAnswer())) {
				eAcount += 1;
			}
			detail.setaAcount(aAcount);
			detail.setbAcount(bAcount);
			detail.setcAcount(cAcount);
			detail.setdAcount(dAcount);
			detail.seteAcount(eAcount);
			detail.setContent(detail.getOfficeTestQuestion().getContent());
			detail.setOptionFive(detail.getOfficeTestQuestion().getOptionFive());
			detail.setOptionFour(detail.getOfficeTestQuestion().getOptionFour());
			detail.setOptionOne(detail.getOfficeTestQuestion().getOptionOne());
			detail.setOptionThree(detail.getOfficeTestQuestion().getOptionThree());
			detail.setOptionTwo(detail.getOfficeTestQuestion().getOptionTwo());
			detail.setAnswer(detail.getOfficeTestQuestion().getAnswer());
		}
		
		return officeTestExamDetails2;
	}

	private List<OfficeTestExamDetail> getSummaryList(List<OfficeTestExamDetail> officeTestExamDetails2, Map<Integer, String> map) {
		
		Set<OfficeTestQuestion> questionSet = new HashSet<OfficeTestQuestion>();
		for (OfficeTestExamDetail detail : officeTestExamDetails2) {
			questionSet.add(detail.getOfficeTestQuestion());
		}
		
		List<OfficeTestExamDetail> returnDetails = new ArrayList<OfficeTestExamDetail>();
		for (OfficeTestQuestion question : questionSet) {
			int aAcount = 0;
			int bAcount = 0;
			int cAcount = 0;
			int dAcount = 0;
			int eAcount = 0;
			
			for (OfficeTestExamDetail detail : officeTestExamDetails2) {
				if (question.getId() == detail.getTestQuestionId()) {
					if ("A".equals(detail.getExamAnswer())) {
						aAcount += 1;
					}
					if ("B".equals(detail.getExamAnswer())) {
						bAcount += 1;
					}
					if ("C".equals(detail.getExamAnswer())) {
						cAcount += 1;
					}
					if ("D".equals(detail.getExamAnswer())) {
						dAcount += 1;
					}
					if ("E".equals(detail.getExamAnswer())) {
						eAcount += 1;
					}
				}
			}
			
			OfficeTestExamDetail detail = new OfficeTestExamDetail();
			detail.setId(null);
			detail.setTestQuestionId(question.getId());
			detail.setExamUserName(null);
			detail.setQuestionType(map.get(question.getTypeId()));
			detail.setaAcount(aAcount);
			detail.setbAcount(bAcount);
			detail.setcAcount(cAcount);
			detail.setdAcount(dAcount);
			detail.seteAcount(eAcount);
			detail.setContent(question.getContent());
			detail.setOptionFive(question.getOptionFive());
			detail.setOptionFour(question.getOptionFour());
			detail.setOptionOne(question.getOptionOne());
			detail.setOptionThree(question.getOptionThree());
			detail.setOptionTwo(question.getOptionTwo());
			detail.setAnswer(question.getAnswer());
			
			returnDetails.add(detail);
		}
		
		return returnDetails;
	}

	public List<OfficeTestExamDetail> viewDetail() {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
		if (StringUtils.isNotEmpty(bankCategoryId)){
			sb.append(" and d.testQuestion_id in (").append(id).append(")");
	    }
		
	    if (StringUtil.isNotEmpty(sb.toString())) {
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		} else {
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			param.put("topnumber", " top 20 ");
		}
	    
		param.put("andSelect", sb.toString());
		param.put("orderBy", " order by d.ExamUserId, d.testBank_Id, d.testQuestion_id");
		return officeTestExamDetailService.selectByParm(param);
	}

	public Object getJobIds(String peopleJob2) {
		String[] typeArray = peopleJob2.split(";");
		String sql = " select id from ProLimsSH.dbo.CommonUMUser1015UserExtendInfo where ";
		for (int k = 0; k < typeArray.length; k++) {	
			if (k == 0) {
				sql += " job like '%" + typeArray[k] + "%'";
			} else {
				sql += " or job like '%" + typeArray[k] + "%'";
			}
	    }
		return sql;
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
	
	
	/**
	 * 获得历史多少天之前的这一天的日期，包括时间
	 * 
	 * @返回日期类型
	 */
	public static String getLongHisDateStr(int his) {
		GregorianCalendar thisday = new GregorianCalendar();
		thisday.add(GregorianCalendar.DATE, -1 * his);// 今天前的**天
		SimpleDateFormat shortformatter = new SimpleDateFormat(
				"yyyy-MM-dd kk:mm:ss");
		String dateStr = shortformatter.format(thisday.getTime());
		return dateStr;
	}


	public String getTestBankId() {
		return testBankId;
	}


	public void setTestBankId(String testBankId) {
		this.testBankId = testBankId;
	}


	public List<DatadictTrl> getBankCategoryList() {
		return bankCategoryList;
	}

	public void setBankCategoryList(List<DatadictTrl> bankCategoryList) {
		this.bankCategoryList = bankCategoryList;
	}
	
	public String getBeOpen() {
		return beOpen;
	}

	public void setBeOpen(String beOpen) {
		this.beOpen = beOpen;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getKstesttime() {
		return kstesttime;
	}

	public void setKstesttime(String kstesttime) {
		this.kstesttime = kstesttime;
	}

	public String getKsstcount() {
		return ksstcount;
	}

	public void setKsstcount(String ksstcount) {
		this.ksstcount = ksstcount;
	}

	public String getBeCalcScore() {
		return beCalcScore;
	}

	public void setBeCalcScore(String beCalcScore) {
		this.beCalcScore = beCalcScore;
	}

	public OfficeTestExam getOfficeTestExam() {
		return officeTestExam;
	}

	public void setOfficeTestExam(OfficeTestExam officeTestExam) {
		this.officeTestExam = officeTestExam;
	}

	public boolean isBelastquestion() {
		return belastquestion;
	}

	public void setBelastquestion(boolean belastquestion) {
		this.belastquestion = belastquestion;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getSavestate() {
		return savestate;
	}

	public void setSavestate(String savestate) {
		this.savestate = savestate;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public OfficeTestExamDetail getOfficeTestExamDetail() {
		return officeTestExamDetail;
	}

	public void setOfficeTestExamDetail(OfficeTestExamDetail officeTestExamDetail) {
		this.officeTestExamDetail = officeTestExamDetail;
	}

	public OfficeTestBank getOfficeTestBank() {
		return officeTestBank;
	}

	public void setOfficeTestBank(OfficeTestBank officeTestBank) {
		this.officeTestBank = officeTestBank;
	}

	public List<OfficeTestExamDetail> getOfficeTestExamDetailList() {
		return officeTestExamDetailList;
	}

	public void setOfficeTestExamDetailList(List<OfficeTestExamDetail> officeTestExamDetailList) {
		this.officeTestExamDetailList = officeTestExamDetailList;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	public List<DatadictTrl> getQuestionTypeList() {
		return questionTypeList;
	}

	public void setQuestionTypeList(List<DatadictTrl> questionTypeList) {
		this.questionTypeList = questionTypeList;
	}

	public List<DatadictTrl> getSelectTypeList() {
		return selectTypeList;
	}

	public void setSelectTypeList(List<DatadictTrl> selectTypeList) {
		this.selectTypeList = selectTypeList;
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

	public String getSelectTypeId() {
		return selectTypeId;
	}

	public void setSelectTypeId(String selectTypeId) {
		this.selectTypeId = selectTypeId;
	}

	public List<OfficeTestExamDetail> getOfficeTestExamDetails() {
		return officeTestExamDetails;
	}

	public void setOfficeTestExamDetails(List<OfficeTestExamDetail> officeTestExamDetails) {
		this.officeTestExamDetails = officeTestExamDetails;
	}

	public String getPeopleJob() {
		return peopleJob;
	}

	public void setPeopleJob(String peopleJob) {
		this.peopleJob = peopleJob;
	}

	public String getDoAction() {
		return doAction;
	}

	public void setDoAction(String doAction) {
		this.doAction = doAction;
	}

	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

}
