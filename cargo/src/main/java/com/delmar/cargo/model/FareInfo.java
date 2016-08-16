package com.delmar.cargo.model;

/**
 * FareInfo entity. @author MyEclipse Persistence Tools
 */

public class FareInfo implements java.io.Serializable {

	// Fields

	private String fareNo;
	private String companyId;
	private String fileCode;
	private String fareCode;
	private String trustCode;
	private String fareType;
	private String preCollect;
	private Double operAcount;
	private String cusCode;
	private String cusCodeName;
	private String currencyType;
	private String subjectCode;
	private String writeDate;
	private String calcCurrType;
	private Double calcCurrRate;
	private Double foreignAcount;
	private Double unitPrice;
	private Double unitNumber;
	private String unitPackage;
	private String unitDescript;
	private Integer recedeal;
	private Double currencyRate;
	private String operator;
	private String remark;
	private String operatorName;
	private String billNo;
	private String invoiceNo;
	private Double cwAcount;
	private String otherBillNo;
	private Double balance;
	private String appendDescript;
	private Double otherOperAcount;
	private String standBy1;
	private String relaCode;
	private String beFt;
	private String realFareFt;
	private String sourceType;
	private String sourceCode;
	private String pxCalc;
	private Integer cwConfirm;
	private String confirmDate;
	private Integer operateMark;
	private String modiOper;
	private String standBy2;
	private String standBy3;
	private Integer beTax;
	private Double localOperAcount;
	private Double taxAcount;
	private Double taxPercent;
	private Double markUpPercent;
	private Integer beTrans;
	private Integer beImportTrans;
	private Integer beAddOn;
	private Double taxRate;
	private String localFareName;
	private String fareName;
	private FareNameInfo fareNameObj;

	// Constructors

	/** default constructor */
	public FareInfo() {
	}

	/** minimal constructor */
	public FareInfo(String fareNo) {
		this.fareNo = fareNo;
	}

	// Property accessors

	public String getFareNo() {
		return this.fareNo;
	}

	public void setFareNo(String fareNo) {
		this.fareNo = fareNo;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getFileCode() {
		return this.fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getFareCode() {
		return this.fareCode;
	}

	public void setFareCode(String fareCode) {
		this.fareCode = fareCode;
	}

	public String getTrustCode() {
		return this.trustCode;
	}

	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
	}

	public String getFareType() {
		return this.fareType;
	}

	public void setFareType(String fareType) {
		this.fareType = fareType;
	}

	public String getPreCollect() {
		return this.preCollect;
	}

	public void setPreCollect(String preCollect) {
		this.preCollect = preCollect;
	}

	public Double getOperAcount() {
		return this.operAcount;
	}

	public void setOperAcount(Double operAcount) {
		this.operAcount = operAcount;
	}

	public String getCusCode() {
		return this.cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCusCodeName() {
		return this.cusCodeName;
	}

	public void setCusCodeName(String cusCodeName) {
		this.cusCodeName = cusCodeName;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getCalcCurrType() {
		return this.calcCurrType;
	}

	public void setCalcCurrType(String calcCurrType) {
		this.calcCurrType = calcCurrType;
	}

	public Double getCalcCurrRate() {
		return this.calcCurrRate;
	}

	public void setCalcCurrRate(Double calcCurrRate) {
		this.calcCurrRate = calcCurrRate;
	}

	public Double getForeignAcount() {
		return this.foreignAcount;
	}

	public void setForeignAcount(Double foreignAcount) {
		this.foreignAcount = foreignAcount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getUnitNumber() {
		return this.unitNumber;
	}

	public void setUnitNumber(Double unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getUnitPackage() {
		return this.unitPackage;
	}

	public void setUnitPackage(String unitPackage) {
		this.unitPackage = unitPackage;
	}

	public String getUnitDescript() {
		return this.unitDescript;
	}

	public void setUnitDescript(String unitDescript) {
		this.unitDescript = unitDescript;
	}

	public Integer getRecedeal() {
		return this.recedeal;
	}

	public void setRecedeal(Integer recedeal) {
		this.recedeal = recedeal;
	}

	public Double getCurrencyRate() {
		return this.currencyRate;
	}

	public void setCurrencyRate(Double currencyRate) {
		this.currencyRate = currencyRate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Double getCwAcount() {
		return this.cwAcount;
	}

	public void setCwAcount(Double cwAcount) {
		this.cwAcount = cwAcount;
	}

	public String getOtherBillNo() {
		return this.otherBillNo;
	}

	public void setOtherBillNo(String otherBillNo) {
		this.otherBillNo = otherBillNo;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getAppendDescript() {
		return this.appendDescript;
	}

	public void setAppendDescript(String appendDescript) {
		this.appendDescript = appendDescript;
	}

	public Double getOtherOperAcount() {
		return this.otherOperAcount;
	}

	public void setOtherOperAcount(Double otherOperAcount) {
		this.otherOperAcount = otherOperAcount;
	}

	public String getStandBy1() {
		return this.standBy1;
	}

	public void setStandBy1(String standBy1) {
		this.standBy1 = standBy1;
	}

	public String getRelaCode() {
		return this.relaCode;
	}

	public void setRelaCode(String relaCode) {
		this.relaCode = relaCode;
	}

	public String getBeFt() {
		return this.beFt;
	}

	public void setBeFt(String beFt) {
		this.beFt = beFt;
	}

	public String getRealFareFt() {
		return this.realFareFt;
	}

	public void setRealFareFt(String realFareFt) {
		this.realFareFt = realFareFt;
	}

	public String getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceCode() {
		return this.sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getPxCalc() {
		return this.pxCalc;
	}

	public void setPxCalc(String pxCalc) {
		this.pxCalc = pxCalc;
	}

	public Integer getCwConfirm() {
		return this.cwConfirm;
	}

	public void setCwConfirm(Integer cwConfirm) {
		this.cwConfirm = cwConfirm;
	}

	public String getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Integer getOperateMark() {
		return this.operateMark;
	}

	public void setOperateMark(Integer operateMark) {
		this.operateMark = operateMark;
	}

	public String getModiOper() {
		return this.modiOper;
	}

	public void setModiOper(String modiOper) {
		this.modiOper = modiOper;
	}

	public String getStandBy2() {
		return this.standBy2;
	}

	public void setStandBy2(String standBy2) {
		this.standBy2 = standBy2;
	}

	public String getStandBy3() {
		return this.standBy3;
	}

	public void setStandBy3(String standBy3) {
		this.standBy3 = standBy3;
	}

	public Integer getBeTax() {
		return this.beTax;
	}

	public void setBeTax(Integer beTax) {
		this.beTax = beTax;
	}

	public Double getLocalOperAcount() {
		return this.localOperAcount;
	}

	public void setLocalOperAcount(Double localOperAcount) {
		this.localOperAcount = localOperAcount;
	}

	public Double getTaxAcount() {
		return this.taxAcount;
	}

	public void setTaxAcount(Double taxAcount) {
		this.taxAcount = taxAcount;
	}

	public Double getTaxPercent() {
		return this.taxPercent;
	}

	public void setTaxPercent(Double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public Double getMarkUpPercent() {
		return this.markUpPercent;
	}

	public void setMarkUpPercent(Double markUpPercent) {
		this.markUpPercent = markUpPercent;
	}

	public Integer getBeTrans() {
		return this.beTrans;
	}

	public void setBeTrans(Integer beTrans) {
		this.beTrans = beTrans;
	}

	public Integer getBeImportTrans() {
		return this.beImportTrans;
	}

	public void setBeImportTrans(Integer beImportTrans) {
		this.beImportTrans = beImportTrans;
	}

	public Integer getBeAddOn() {
		return this.beAddOn;
	}

	public void setBeAddOn(Integer beAddOn) {
		this.beAddOn = beAddOn;
	}

	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getLocalFareName() {
		return this.localFareName;
	}

	public void setLocalFareName(String localFareName) {
		this.localFareName = localFareName;
	}

	public FareNameInfo getFareNameObj() {
		return fareNameObj;
	}

	public void setFareNameObj(FareNameInfo fareNameObj) {
		this.fareNameObj = fareNameObj;
		
		if (fareNameObj==null)
			fareName="";
		else
			fareName=fareNameObj.getFareCname();		
	}
	
	
	
	public void setFareName(String fareName) {
		this.fareName = fareName;
	}

	public String getFareName()
	{
		return fareName;
	}
	
	

}