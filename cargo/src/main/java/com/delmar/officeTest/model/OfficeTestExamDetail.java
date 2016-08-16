package com.delmar.officeTest.model;

import com.delmar.common.model.BusCoreModel;
import com.delmar.core.model.CoreModel;
// default package

/**
 * OfficeTestExamDetail entity. @author MyEclipse Persistence Tools
 */

public class OfficeTestExamDetail extends CoreModel{

	// Fields

	private OfficeTestQuestion officeTestQuestion;
	private OfficeTestExam officeTestExam;
	private Integer examUserId;
	private String examAnswer;
	private String rightAnswer;
	private Integer examScore;
	private String examUserName;
	private Integer zfbz;
	private Integer examId;
	private Integer testQuestionId;
	private Integer testBankId;
	
	
	private boolean belastquestion;
	private String questionType;
	private Integer aAcount;
	private Integer bAcount;
	private Integer cAcount;
	private Integer dAcount;
	private Integer eAcount;
	
	private String content;
	private String optionOne;
	private String optionTwo;
	private String optionThree;
	private String optionFour;
	private String optionFive;
	private String answer;

	
	// Constructors

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Integer getaAcount() {
		return aAcount;
	}

	public void setaAcount(Integer aAcount) {
		this.aAcount = aAcount;
	}

	public Integer getbAcount() {
		return bAcount;
	}

	public void setbAcount(Integer bAcount) {
		this.bAcount = bAcount;
	}

	public Integer getcAcount() {
		return cAcount;
	}

	public void setcAcount(Integer cAcount) {
		this.cAcount = cAcount;
	}

	public Integer getdAcount() {
		return dAcount;
	}

	public void setdAcount(Integer dAcount) {
		this.dAcount = dAcount;
	}

	public Integer geteAcount() {
		return eAcount;
	}

	public void seteAcount(Integer eAcount) {
		this.eAcount = eAcount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptionOne() {
		return optionOne;
	}

	public void setOptionOne(String optionOne) {
		this.optionOne = optionOne;
	}

	public String getOptionTwo() {
		return optionTwo;
	}

	public void setOptionTwo(String optionTwo) {
		this.optionTwo = optionTwo;
	}

	public String getOptionThree() {
		return optionThree;
	}

	public void setOptionThree(String optionThree) {
		this.optionThree = optionThree;
	}

	public String getOptionFour() {
		return optionFour;
	}

	public void setOptionFour(String optionFour) {
		this.optionFour = optionFour;
	}

	public String getOptionFive() {
		return optionFive;
	}

	public void setOptionFive(String optionFive) {
		this.optionFive = optionFive;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/** default constructor */
	public OfficeTestExamDetail() {
		this.officeTestQuestion = new OfficeTestQuestion();
	}

	/** minimal constructor */
	public OfficeTestExamDetail(Integer id) {
		this.id = id;
	}
	
	/** full constructor */
	public OfficeTestExamDetail(Integer id,
			OfficeTestQuestion officeTestQuestion,
			OfficeTestExam officeTestExam, Integer examUserId,
			String examAnswer, String rightAnswer, Integer examScore,
			String examUserName) {
		this.id = id;
		this.officeTestQuestion = officeTestQuestion;
		this.officeTestExam = officeTestExam;
		this.examUserId = examUserId;
		this.examAnswer = examAnswer;
		this.rightAnswer = rightAnswer;
		this.examScore = examScore;
		this.examUserName = examUserName;
	}

	// Property accessors

	public OfficeTestQuestion getOfficeTestQuestion() {
		return this.officeTestQuestion;
	}

	public void setOfficeTestQuestion(OfficeTestQuestion officeTestQuestion) {
		this.officeTestQuestion = officeTestQuestion;
	}

	public OfficeTestExam getOfficeTestExam() {
		return this.officeTestExam;
	}

	public void setOfficeTestExam(OfficeTestExam officeTestExam) {
		this.officeTestExam = officeTestExam;
	}

	public Integer getExamUserId() {
		return this.examUserId;
	}

	public void setExamUserId(Integer examUserId) {
		this.examUserId = examUserId;
	}

	public String getExamAnswer() {
		return this.examAnswer;
	}

	public void setExamAnswer(String examAnswer) {
		this.examAnswer = examAnswer;
	}

	public String getRightAnswer() {
		return this.rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public Integer getExamScore() {
		return this.examScore;
	}

	public void setExamScore(Integer examScore) {
		this.examScore = examScore;
	}

	public String getExamUserName() {
		return this.examUserName;
	}

	public void setExamUserName(String examUserName) {
		this.examUserName = examUserName;
	}

	public Integer getZfbz() {
		return zfbz;
	}

	public void setZfbz(Integer zfbz) {
		this.zfbz = zfbz;
	}

	public boolean isBelastquestion() {
		return belastquestion;
	}

	public void setBelastquestion(boolean belastquestion) {
		this.belastquestion = belastquestion;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public Integer getTestQuestionId() {
		return testQuestionId;
	}

	public void setTestQuestionId(Integer testQuestionId) {
		this.testQuestionId = testQuestionId;
	}

	public Integer getTestBankId() {
		return testBankId;
	}

	public void setTestBankId(Integer testBankId) {
		this.testBankId = testBankId;
	}
}