package com.delmar.cargo.model;

/**
 * ReportFareInvoice entity. @author MyEclipse Persistence Tools
 */

public class ReportFareInvoice implements java.io.Serializable {

	// Fields

	private String invoiceNo;
	private String companyId;
	private String invoicePzno;
	private String invoiceRefNo;
	private String invoicePzdate;
	private String fileCode;
	private String cusCode;
	private String trustCode;
	private String cusCodeName;
	private String cusCodeDesc;
	private Integer bePrint;
	private String noGroup;
	private String printDate;
	private String billContentOne;
	private String billContentTwo;
	private String billContentThree;
	private String fareDesc;
	private String currencyType;
	private Double transDebit;
	private String subjectCode;
	private Double transCredit;
	private Double transProfit;
	private String operator;
	private Double cwAcount;
	private Integer receDeal;
	private String operateDate;
	private Double balance;
	private String remark;
	private String operateType;
	private String billNo;
	private String confirmDate;
	private String standBy1;
	private Integer cwConfirm;
	private String standBy2;
	private String operatorName;
	private String standBy3;
	private String refInvoiceNo;
	private Integer operateMark;
	private String shmark;
	private String shmarkDate;
	private String shfinishDate;
	private String cwfinishDate;
	private String hqcwfinishDate;
	private String cwcontext;
	private Integer printNumber;
	private String billDate;
	private String billType;
	private String returnType;
	private Integer beGeneral;
	private String doMark;
	private Double localProfit;
	private Long taxAcount;
	private Long taxBalance;
	private Long taxCwacount;
	private String usoftMark;
	private String usoftDate;
	private Integer beTax;
	private Integer beDsdf;
	private Integer beImportTrans;
	private String relatedParty;
	private String relatedPartyName;
	private Integer beSecondCurr;
	private String secondCurrType;
	private Double secondCurrRate;
	private Double secondTransProfit;
	private Integer beTrans;
	private String transParty;
	private Double taxRate;
	private String receiveDate;
	private String creditDebit;
	private TrustContext oceanBusiness;
	private FileTable airBusiness;

	// Constructors

	/** default constructor */
	public ReportFareInvoice() {
	}

	/** minimal constructor */
	public ReportFareInvoice(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	// Property accessors

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getInvoicePzno() {
		return this.invoicePzno;
	}

	public void setInvoicePzno(String invoicePzno) {
		this.invoicePzno = invoicePzno;
	}

	public String getInvoiceRefNo() {
		return this.invoiceRefNo;
	}

	public void setInvoiceRefNo(String invoiceRefNo) {
		this.invoiceRefNo = invoiceRefNo;
	}

	public String getInvoicePzdate() {
		return this.invoicePzdate;
	}

	public void setInvoicePzdate(String invoicePzdate) {
		this.invoicePzdate = invoicePzdate;
	}

	public String getFileCode() {
		return this.fileCode;
	}

	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
	}

	public String getCusCode() {
		return this.cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getTrustCode() {
		return this.trustCode;
	}

	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
	}

	public String getCusCodeName() {
		return this.cusCodeName;
	}

	public void setCusCodeName(String cusCodeName) {
		this.cusCodeName = cusCodeName;
	}

	public String getCusCodeDesc() {
		return this.cusCodeDesc;
	}

	public void setCusCodeDesc(String cusCodeDesc) {
		this.cusCodeDesc = cusCodeDesc;
	}

	public Integer getBePrint() {
		return this.bePrint;
	}

	public void setBePrint(Integer bePrint) {
		this.bePrint = bePrint;
	}

	public String getNoGroup() {
		return this.noGroup;
	}

	public void setNoGroup(String noGroup) {
		this.noGroup = noGroup;
	}

	public String getPrintDate() {
		return this.printDate;
	}

	public void setPrintDate(String printDate) {
		this.printDate = printDate;
	}

	public String getBillContentOne() {
		return this.billContentOne;
	}

	public void setBillContentOne(String billContentOne) {
		this.billContentOne = billContentOne;
	}

	public String getBillContentTwo() {
		return this.billContentTwo;
	}

	public void setBillContentTwo(String billContentTwo) {
		this.billContentTwo = billContentTwo;
	}

	public String getBillContentThree() {
		return this.billContentThree;
	}

	public void setBillContentThree(String billContentThree) {
		this.billContentThree = billContentThree;
	}

	public String getFareDesc() {
		return this.fareDesc;
	}

	public void setFareDesc(String fareDesc) {
		this.fareDesc = fareDesc;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public Double getTransDebit() {
		return this.transDebit;
	}

	public void setTransDebit(Double transDebit) {
		this.transDebit = transDebit;
	}

	public String getSubjectCode() {
		return this.subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Double getTransCredit() {
		return this.transCredit;
	}

	public void setTransCredit(Double transCredit) {
		this.transCredit = transCredit;
	}

	public Double getTransProfit() {
		return this.transProfit;
	}

	public void setTransProfit(Double transProfit) {
		this.transProfit = transProfit;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Double getCwAcount() {
		return this.cwAcount;
	}

	public void setCwAcount(Double cwAcount) {
		this.cwAcount = cwAcount;
	}

	public Integer getReceDeal() {
		return this.receDeal;
	}

	public void setReceDeal(Integer receDeal) {
		this.receDeal = receDeal;
		
		if (receDeal.intValue()==1)
			creditDebit="Debit";
		else
			creditDebit="Credit";		
	}

	public String getOperateDate() {
		return this.operateDate;
	}

	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getBillNo() {
		if (billNo==null)
			billNo="";		
		return this.billNo;
	}

	public void setBillNo(String billNo) {

		this.billNo = billNo;
	}

	public String getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getStandBy1() {
		return this.standBy1;
	}

	public void setStandBy1(String standBy1) {
		this.standBy1 = standBy1;
	}

	public Integer getCwConfirm() {
		return this.cwConfirm;
	}

	public void setCwConfirm(Integer cwConfirm) {
		this.cwConfirm = cwConfirm;
	}

	public String getStandBy2() {
		return this.standBy2;
	}

	public void setStandBy2(String standBy2) {
		this.standBy2 = standBy2;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getStandBy3() {
		return this.standBy3;
	}

	public void setStandBy3(String standBy3) {
		this.standBy3 = standBy3;
	}

	public String getRefInvoiceNo() {
		return this.refInvoiceNo;
	}

	public void setRefInvoiceNo(String refInvoiceNo) {
		this.refInvoiceNo = refInvoiceNo;
	}

	public Integer getOperateMark() {
		return this.operateMark;
	}

	public void setOperateMark(Integer operateMark) {
		this.operateMark = operateMark;
	}

	public String getShmark() {
		return this.shmark;
	}

	public void setShmark(String shmark) {
		this.shmark = shmark;
	}

	public String getShmarkDate() {
		return this.shmarkDate;
	}

	public void setShmarkDate(String shmarkDate) {
		this.shmarkDate = shmarkDate;
	}

	public String getShfinishDate() {
		return this.shfinishDate;
	}

	public void setShfinishDate(String shfinishDate) {
		this.shfinishDate = shfinishDate;
	}

	public String getCwfinishDate() {
		return this.cwfinishDate;
	}

	public void setCwfinishDate(String cwfinishDate) {
		this.cwfinishDate = cwfinishDate;
	}

	public String getHqcwfinishDate() {
		return this.hqcwfinishDate;
	}

	public void setHqcwfinishDate(String hqcwfinishDate) {
		this.hqcwfinishDate = hqcwfinishDate;
	}

	public String getCwcontext() {
		return this.cwcontext;
	}

	public void setCwcontext(String cwcontext) {
		this.cwcontext = cwcontext;
	}

	public Integer getPrintNumber() {
		return this.printNumber;
	}

	public void setPrintNumber(Integer printNumber) {
		this.printNumber = printNumber;
	}

	public String getBillDate() {
		return this.billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getReturnType() {
		return this.returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public Integer getBeGeneral() {
		return this.beGeneral;
	}

	public void setBeGeneral(Integer beGeneral) {
		this.beGeneral = beGeneral;
	}

	public String getDoMark() {
		return this.doMark;
	}

	public void setDoMark(String doMark) {
		this.doMark = doMark;
	}

	public Double getLocalProfit() {
		return this.localProfit;
	}

	public void setLocalProfit(Double localProfit) {
		this.localProfit = localProfit;
	}

	public Long getTaxAcount() {
		return this.taxAcount;
	}

	public void setTaxAcount(Long taxAcount) {
		this.taxAcount = taxAcount;
	}

	public Long getTaxBalance() {
		return this.taxBalance;
	}

	public void setTaxBalance(Long taxBalance) {
		this.taxBalance = taxBalance;
	}

	public Long getTaxCwacount() {
		return this.taxCwacount;
	}

	public void setTaxCwacount(Long taxCwacount) {
		this.taxCwacount = taxCwacount;
	}

	public String getUsoftMark() {
		return this.usoftMark;
	}

	public void setUsoftMark(String usoftMark) {
		this.usoftMark = usoftMark;
	}

	public String getUsoftDate() {
		return this.usoftDate;
	}

	public void setUsoftDate(String usoftDate) {
		this.usoftDate = usoftDate;
	}

	public Integer getBeTax() {
		return this.beTax;
	}

	public void setBeTax(Integer beTax) {
		this.beTax = beTax;
	}

	public Integer getBeDsdf() {
		return this.beDsdf;
	}

	public void setBeDsdf(Integer beDsdf) {
		this.beDsdf = beDsdf;
	}

	public Integer getBeImportTrans() {
		return this.beImportTrans;
	}

	public void setBeImportTrans(Integer beImportTrans) {
		this.beImportTrans = beImportTrans;
	}

	public String getRelatedParty() {
		return this.relatedParty;
	}

	public void setRelatedParty(String relatedParty) {
		this.relatedParty = relatedParty;
	}

	public String getRelatedPartyName() {
		return this.relatedPartyName;
	}

	public void setRelatedPartyName(String relatedPartyName) {
		this.relatedPartyName = relatedPartyName;
	}

	public Integer getBeSecondCurr() {
		return this.beSecondCurr;
	}

	public void setBeSecondCurr(Integer beSecondCurr) {
		this.beSecondCurr = beSecondCurr;
	}

	public String getSecondCurrType() {
		return this.secondCurrType;
	}

	public void setSecondCurrType(String secondCurrType) {
		this.secondCurrType = secondCurrType;
	}

	public Double getSecondCurrRate() {
		return this.secondCurrRate;
	}

	public void setSecondCurrRate(Double secondCurrRate) {
		this.secondCurrRate = secondCurrRate;
	}

	public Double getSecondTransProfit() {
		return this.secondTransProfit;
	}

	public void setSecondTransProfit(Double secondTransProfit) {
		this.secondTransProfit = secondTransProfit;
	}

	public Integer getBeTrans() {
		return this.beTrans;
	}

	public void setBeTrans(Integer beTrans) {
		this.beTrans = beTrans;
	}

	public String getTransParty() {
		return this.transParty;
	}

	public void setTransParty(String transParty) {
		this.transParty = transParty;
	}

	public Double getTaxRate() {
		return this.taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getReceiveDate() {
		return this.receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	
	
	public void setCreditDebit(String creditDebit) {
		this.creditDebit = creditDebit;
	}

	public String getCreditDebit()
	{
      return creditDebit;
				
	}

	public TrustContext getOceanBusiness() {
		return oceanBusiness;
	}

	public void setOceanBusiness(TrustContext oceanBusiness) {
		this.oceanBusiness = oceanBusiness;
	}

	public FileTable getAirBusiness() {
		return airBusiness;
	}

	public void setAirBusiness(FileTable airBusiness) {
		this.airBusiness = airBusiness;
	}
	
	
	

}