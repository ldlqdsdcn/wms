package com.delmar.officeTest.utils;

import java.io.StringBufferInputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.delmar.base.model.Datadict;
import com.delmar.base.service.DatadictService;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.model.OfficeTestExam;
import com.delmar.officeTest.model.OfficeTestExamDetail;
import com.delmar.officeTest.model.OfficeTestQuestion;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestExamDetailService;

// 完成对XML文件的所有操作，包括存储
public class XMLParseImpl {

	private static String currOperator;

	private HashMap documentMap = null;

	private static XMLParseImpl instance = null;
	
	static OfficeTestBankCategoryService officeTestBankCategoryService;
	
	static DatadictService datadictService;
	
	static OfficeTestExamDetailService officeTestExamDetailService;
	

	public XMLParseImpl() {

		documentMap = new HashMap();

	}

	public static XMLParseImpl getInstance(String operator) {
		
		if (instance == null)
			instance = new XMLParseImpl();

		officeTestBankCategoryService = SpringContextWrapper.getBean(OfficeTestBankCategoryService.class);
		datadictService = SpringContextWrapper.getBean(DatadictService.class);
		officeTestExamDetailService = SpringContextWrapper.getBean(OfficeTestExamDetailService.class);
		currOperator = operator;
		return instance;
	}

	public Document getDocument() {
		Document currentDoc = null;
		if (documentMap.containsKey(currOperator)) {
			currentDoc = (Document) documentMap.get(currOperator);
		}

		return currentDoc;
	}
	
	public void removeDocument()
	{
		if (documentMap.containsKey(currOperator)) {
			documentMap.remove(currOperator);
		}
	}

	public List getKsst() {
		Element root = getDocument().getRootElement();
		Element ks1 = root.element("kstk");
		List ks2 = ks1.elements();
		return ks2;
	}

	public void initDocument(List<OfficeTestQuestion> questionList,OfficeTestBank officeTestBank) {
		try {
			Document currentDoc = buildXML(questionList,officeTestBank);
			documentMap.put(currOperator, currentDoc);

		} catch (Exception ee) {
			System.out.println("create a new document fail:" + ee.getMessage());
		}

	}

	/**
	 * use the string gived to build a xml document if error throw JDOMException
	 * 
	 * @param xmlString
	 *            The xml string
	 * @return document the xml's document
	 * @throws DocumentException 
	 * @exception JDOMException
	 */

	public static Document buildXML(String xmlString) throws DocumentException {
		SAXReader  builder = new SAXReader ();
		Document doc = builder.read(new StringBufferInputStream(xmlString));
		return doc;
	}

	/***************************************************************************
	 * 根据传递过来的试题库进行XML的创建
	 **************************************************************************/
	public Document buildXML(List<OfficeTestQuestion>  questionList,OfficeTestBank officeTestBank) {
		
		Element root, ksxx, kstk, tm;

		DocumentFactory factory = DocumentFactory.getInstance();
		root = factory.createElement("root");
		ksxx = factory.createElement("ksxx"); // 考试信息

		ksxx.add(factory.createElement("ksxm")); // 考试姓名
		Element ksfl = factory.createElement("ksfl");   //测试分类
		ksfl.setText(String.valueOf(officeTestBank.getId()));
		ksxx.add(ksfl); // 测试分类

		
		Element ksrq = factory.createElement("ksrq");
		ksrq.setText(getLongHisDateStr(0));
		ksxx.add(ksrq); // 考试日期
		Element kssc = factory.createElement("kssc");
		kssc.setText(String.valueOf(officeTestBank.getTimeLimit()));
		ksxx.add(kssc); // 考试时长
		
		Element ktsl = factory.createElement("ktsl");   //考题数量
		ktsl.setText(String.valueOf(officeTestBank.getQuestionCount()));
		ksxx.add(ktsl); // 考题数量			
		ksxx.add(factory.createElement("ksys")); // 考试用时
		ksxx.add(factory.createElement("kscj")); // 考试成绩
		ksxx.add(factory.createElement("hgfs")); // 合格成绩

		Document doc = factory.createDocument(root);
		root.add(ksxx);

		kstk = factory.createElement("kstk"); // 考试试题
		root.add(kstk);

		for (int i = 0; i < questionList.size(); i++) {
			tm = factory.createElement("tm"); // 考试试题
			OfficeTestQuestion officeTestQuestion = (OfficeTestQuestion) questionList.get(i);

			Element th = factory.createElement("th");
			th.setText(String.valueOf(i + 1));
			tm.add(th); // 题号

			Element tkid = factory.createElement("tkid");
			tkid.setText(String.valueOf(officeTestQuestion.getId()));
			tm.add(tkid); // 试题ID

			Element tmnr = factory.createElement("tmnr");
			tmnr.setText(officeTestQuestion.getContent());
			tm.add(tmnr); // 题目内容

			Element tmsm = factory.createElement("tmsm");
			tmsm.setText(getCategoryById(officeTestQuestion.getCategoryId()));
			tm.add(tmsm);// 题目类型

			Element tmlb = factory.createElement("tmlb");
			tmlb.setText(getQuestionType(officeTestQuestion.getTypeId()));
			tm.add(tmlb); // 多选题 // 单选题 // 判断题

			Element da1 = factory.createElement("da1");
			da1.setText(officeTestQuestion.getOptionOne());
			tm.add(da1); // 答案1

			Element da2 = factory.createElement("da2");
			da2.setText(officeTestQuestion.getOptionTwo());
			tm.add(da2); // 答案1

			Element da3 = factory.createElement("da3");
			da3.setText(officeTestQuestion.getOptionThree());
			tm.add(da3); // 答案1

			Element da4 = factory.createElement("da4");
			da4.setText(officeTestQuestion.getOptionFour());
			tm.add(da4); // 答案1
			
			Element da5 = factory.createElement("da5");
			da5.setText(officeTestQuestion.getOptionFive());
			tm.add(da5); // 答案1			

			Element zqda = factory.createElement("zqda");
			zqda.setText(officeTestQuestion.getAnswer());
			tm.add(zqda); // 正确答案

			Element ksda = factory.createElement("ksda");
			
			//查找 这个考试分类的 这个人的 这个题目的 没有作废的做题答案 
			Map<String, Object> map = new HashMap<String, Object>();
			String sql = "";
			sql += " testBank_Id =" + officeTestQuestion.getTestBankId();
			sql += " and testQuestion_Id =" + officeTestQuestion.getId();
			
			sql += " and examUserId =" + currOperator;
			sql += " and IsNull(ZFBZ,0)=0";
			
			map.put("accessString", sql);
			List<OfficeTestExamDetail> detetails = officeTestExamDetailService.selectByExample(map);
			String examAnswer = "";
			if (detetails != null && detetails.size() == 1) {
				 
				examAnswer = detetails.get(0).getExamAnswer();
			}
			ksda.setText(examAnswer);
			tm.add(ksda); // 考试答案

			Element ksfs = factory.createElement("ksfs");
			ksfs.setText(String.valueOf(officeTestQuestion.getPoint()));
			tm.add(ksfs); // 试题分数

			kstk.add(tm);
		}
		
		return doc;
	}

	public String getQuestionType(Integer typeId) {
		
		Datadict datadict = datadictService.selectByPrimaryKey(typeId);
		return datadict == null ? "" : datadict.getValue();
	}

	public String getCategoryById(Integer categoryId) {
		
		OfficeTestBankCategory category = officeTestBankCategoryService.selectByPrimaryKey(categoryId);
		return category == null ? "" : category.getName();
	}

	// 保存考试答案
	public void saveOneKsda(int ksIndex, String answerStr) {
		Element tmEL = (Element) getKsst().get(ksIndex);
		Element ksda = tmEL.element("ksda");

		ksda.setText(answerStr);

	}

	// 保存考试答案
	public void saveKsxx(String xxName, String xxValue) {

		Element root = getDocument().getRootElement();
		Element ks1 = root.element("ksxx");
		Element ks2 = ks1.element(xxName);
		ks2.setText(xxValue);

	}
	
	public String getKsxx(String xxName) {

		Element root = getDocument().getRootElement();
		Element ks1 = root.element("ksxx");
		Element ks2 = ks1.element(xxName);
		return ks2.getText();

	}
	

	// 得到下一题的ObjTestPaper对象
	public OfficeTestExamDetail getNextTM(int ksIndex) {
		Element tmEL = (Element) getKsst().get(ksIndex);

		OfficeTestExamDetail examDetail = new OfficeTestExamDetail();

		examDetail.getOfficeTestQuestion().setQuestionTh(ksIndex);

		Element stxx = tmEL.element("tmnr");
		examDetail.getOfficeTestQuestion().setContent(stxx.getText());

		stxx = tmEL.element("tmsm");
		examDetail.getOfficeTestQuestion().setCategoryName(stxx.getText());

		stxx = tmEL.element("tmlb");
		examDetail.getOfficeTestQuestion().setTypeName(stxx.getText());

		stxx = tmEL.element("da1");
		examDetail.getOfficeTestQuestion().setOptionOne(stxx.getText());

		stxx = tmEL.element("da2");
		examDetail.getOfficeTestQuestion().setOptionTwo(stxx.getText());

		stxx = tmEL.element("da3");
		examDetail.getOfficeTestQuestion().setOptionThree(stxx.getText());

		stxx = tmEL.element("da4");
		examDetail.getOfficeTestQuestion().setOptionFour(stxx.getText());
		
		stxx = tmEL.element("da5");
		examDetail.getOfficeTestQuestion().setOptionFive(stxx.getText());
		

		stxx = tmEL.element("ksda");
		examDetail.setExamAnswer(stxx.getText());

		return examDetail;

	}

	// 对XML进行解析，然后转换成相应的Obj对象
	// 返回对象为成绩
	public void parseXMLToObj(OfficeTestExam officeTestExam, List<OfficeTestExamDetail> testPaperVec) {
		int scoreInt = 0;
		int ksIndex = 1;
		
		Element ksxx = (Element) getDocument().getRootElement()
				.element("ksxx");
		String ksrq = ((Element) ksxx.element("ksrq")).getText();
		String ksfl = ((Element) ksxx.element("ksfl")).getText();// 考试分类		
		officeTestExam.setCreated(Timestamp.valueOf(ksrq));
		officeTestExam.setTestBankId(Integer.parseInt(ksfl));
		String kssc = ((Element) ksxx.element("kssc")).getText();
		officeTestExam.setTimeUsed(Integer.parseInt(kssc));
		officeTestExam.setBeCancel(0);

		for (Iterator iter = getKsst().iterator(); iter.hasNext();) {
			Element el = (Element) iter.next();

			String zqda = ((Element) el.element("zqda")).getText();// 正确答案
			String ksda = ((Element) el.element("ksda")).getText();// 考试答案
			String tkid = ((Element) el.element("tkid")).getText();// 试题ID
			String ksfs = ((Element) el.element("ksfs")).getText();// 试题分数
			
			OfficeTestExamDetail officeTestExamDetail = new OfficeTestExamDetail();
			officeTestExamDetail.setTestQuestionId(Integer.parseInt(tkid));
			officeTestExamDetail.setRightAnswer(zqda);
			officeTestExamDetail.setExamAnswer(ksda);
			if (zqda.equalsIgnoreCase(ksda)) {
				if (ksfs == null || "null".equals(ksfs) || "".equals(ksfs)) {
					ksfs = "0";
				}
				officeTestExamDetail.setExamScore(Integer.parseInt(ksfs));
				scoreInt = scoreInt + Integer.parseInt(ksfs);
			} else
				officeTestExamDetail.setExamScore(0);

			officeTestExamDetail.getOfficeTestQuestion().setQuestionTh(ksIndex);

			Element stxx = el.element("tmnr");
			officeTestExamDetail.getOfficeTestQuestion().setContent(stxx.getText());

			stxx = el.element("tmsm");
			officeTestExamDetail.getOfficeTestQuestion().setCategoryName(stxx.getText());

			stxx = el.element("tmlb");
			officeTestExamDetail.getOfficeTestQuestion().setTypeName(stxx.getText());

			stxx = el.element("da1");
			officeTestExamDetail.getOfficeTestQuestion().setOptionOne(stxx.getText());

			stxx = el.element("da2");
			officeTestExamDetail.getOfficeTestQuestion().setOptionTwo(stxx.getText());

			stxx = el.element("da3");
			officeTestExamDetail.getOfficeTestQuestion().setOptionThree(stxx.getText());

			stxx = el.element("da4");
			officeTestExamDetail.getOfficeTestQuestion().setOptionFour(stxx.getText());

			stxx = el.element("da5");
			officeTestExamDetail.getOfficeTestQuestion().setOptionFive(stxx.getText());	
			
			officeTestExamDetail.setTestBankId(Integer.parseInt(ksfl));

			testPaperVec.add(officeTestExamDetail);

			ksIndex = ksIndex + 1;
		}

		officeTestExam.setExamScore(scoreInt);


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

}
