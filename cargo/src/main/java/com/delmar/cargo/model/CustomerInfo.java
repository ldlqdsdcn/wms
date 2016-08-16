package com.delmar.cargo.model;

/**
 * CustomerInfo entity. @author MyEclipse Persistence Tools
 */

public class CustomerInfo implements java.io.Serializable {

	// Fields

	private String cusCode;
	private String companyId;
	private Integer beInnerCompany;
	private Integer beCompanyDeal;
	private String companyGroup;
	private String specialMark;
	private String americanSpecialMark;
	private String acifare;
	private String cusShortName;
	private String pptrustType;
	private String cctrustType;
	private String wcamember;
	private String iatacode;
	private String amsCode;
	private String aciCode;
	private String iacode;
	private String cusName;
	private String cusCname;
	private String cusAddress;
	private String cusTypeDesc;
	private String cusType;
	private String cusTypeB;
	private String cusPost;
	private String cusCaddress;
	private String cusTele;
	private String cusCable;
	private String cusFax;
	private String cusRelation;
	private String cusTradeType;
	private String countryCode;
	private String proviceCode;
	private String cityCode;
	private String cusEmail;
	private String cusRmbAccount;
	private String cusDescript;
	private String bank;
	private String bankDollor;
	private String cusDollarAccount;
	private String bussinessCode;
	private String agentType;
	private String remark;
	private String agentCity;
	private String agentComm;
	private String standBy1;
	private String agentAc;
	private Integer outIn;
	private String agentTerms;
	private String standBy2;
	private String planeOcean;
	private String ciqcode;
	private String foreignSource;
	private String standBy3;
	private String writeDate;
	private String foreignRequest;
	private Integer beRoutingOrder;
	private Integer beFreeHand;
	private Integer beSale;
	private String roagent;
	private String roagentTele;
	private String roagentFax;
	private String roagentCountry;
	private String roagentPort;
	private String roagentRela;
	private String roagentEmail;
	private String roagentRequestOper;
	private String fhstation;
	private String fhbussiness;
	private String fhbussinessRequest;
	private String saleAgent;
	private String saleAgentTele;
	private String saleAgentRequestOper;
	private String saleAgentRela;
	private String saleStation;
	private String saleBussiness;
	private String saleBussinessRequest;
	private String appraiseOper;
	private String appraiseFinance;
	private String appraiseCs;
	private String analysisTrade;
	private String analysisBussiness;
	private String protocal;
	private String requestOper;
	private String requestBg;
	private String requestCk;
	private String requestCar;
	private String requestOther;
	private String feedBackCs;
	private String feedBackOper;
	private String feedBackFinance;
	private String parentCusCode;
	private String cwCusCode;
	private Integer level;
	private String cwCusCodeTwo;
	private String dcmsid;
	private String cwCusCodeNameTwo;
	private String cwCusCodeName;
	private String webCusCode;
	private Integer beUse;
	private String beginUseDate;
	private String recentUseDate;
	private String cusGroup;
	private String cusState;
	private String cusInvoiceName;
	private String cusServiceName;
	private String protocolNo;
	private String effectDateBegin;
	private String effectDateEnd;
	private Double arrearageAcount;
	private Integer arrearageMonth;
	private Integer arrearageDay;
	private String balanceType;
	private Integer taxType;
	private Integer receTaxType;
	private Integer dutyTax;
	private Integer distanceFlightDate;
	private String operator;
	private String cneeNumberType;
	private String cneeNumber;
	private String operatorName;
	private String protocolContext;
	private Integer isChina;
	private String lclSpecialMark;
	private Integer beAttention;

	// Constructors

	/** default constructor */
	public CustomerInfo() {
	}

	/** minimal constructor */
	public CustomerInfo(String cusCode, String companyId) {
		this.cusCode = cusCode;
		this.companyId = companyId;
	}


	// Property accessors

	public String getCusCode() {
		return this.cusCode;
	}

	public void setCusCode(String cusCode) {
		this.cusCode = cusCode;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getBeInnerCompany() {
		return this.beInnerCompany;
	}

	public void setBeInnerCompany(Integer beInnerCompany) {
		this.beInnerCompany = beInnerCompany;
	}

	public Integer getBeCompanyDeal() {
		return this.beCompanyDeal;
	}

	public void setBeCompanyDeal(Integer beCompanyDeal) {
		this.beCompanyDeal = beCompanyDeal;
	}

	public String getCompanyGroup() {
		return this.companyGroup;
	}

	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}

	public String getSpecialMark() {
		return this.specialMark;
	}

	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}

	public String getAmericanSpecialMark() {
		return this.americanSpecialMark;
	}

	public void setAmericanSpecialMark(String americanSpecialMark) {
		this.americanSpecialMark = americanSpecialMark;
	}

	public String getAcifare() {
		return this.acifare;
	}

	public void setAcifare(String acifare) {
		this.acifare = acifare;
	}

	public String getCusShortName() {
		return this.cusShortName;
	}

	public void setCusShortName(String cusShortName) {
		this.cusShortName = cusShortName;
	}

	public String getPptrustType() {
		return this.pptrustType;
	}

	public void setPptrustType(String pptrustType) {
		this.pptrustType = pptrustType;
	}

	public String getCctrustType() {
		return this.cctrustType;
	}

	public void setCctrustType(String cctrustType) {
		this.cctrustType = cctrustType;
	}

	public String getWcamember() {
		return this.wcamember;
	}

	public void setWcamember(String wcamember) {
		this.wcamember = wcamember;
	}

	public String getIatacode() {
		return this.iatacode;
	}

	public void setIatacode(String iatacode) {
		this.iatacode = iatacode;
	}

	public String getAmsCode() {
		return this.amsCode;
	}

	public void setAmsCode(String amsCode) {
		this.amsCode = amsCode;
	}

	public String getAciCode() {
		return this.aciCode;
	}

	public void setAciCode(String aciCode) {
		this.aciCode = aciCode;
	}

	public String getIacode() {
		return this.iacode;
	}

	public void setIacode(String iacode) {
		this.iacode = iacode;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusCname() {
		return this.cusCname;
	}

	public void setCusCname(String cusCname) {
		this.cusCname = cusCname;
	}

	public String getCusAddress() {
		return this.cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusTypeDesc() {
		return this.cusTypeDesc;
	}

	public void setCusTypeDesc(String cusTypeDesc) {
		this.cusTypeDesc = cusTypeDesc;
	}

	public String getCusType() {
		return this.cusType;
	}

	public void setCusType(String cusType) {
		this.cusType = cusType;
	}

	public String getCusTypeB() {
		return this.cusTypeB;
	}

	public void setCusTypeB(String cusTypeB) {
		this.cusTypeB = cusTypeB;
	}

	public String getCusPost() {
		return this.cusPost;
	}

	public void setCusPost(String cusPost) {
		this.cusPost = cusPost;
	}

	public String getCusCaddress() {
		return this.cusCaddress;
	}

	public void setCusCaddress(String cusCaddress) {
		this.cusCaddress = cusCaddress;
	}

	public String getCusTele() {
		return this.cusTele;
	}

	public void setCusTele(String cusTele) {
		this.cusTele = cusTele;
	}

	public String getCusCable() {
		return this.cusCable;
	}

	public void setCusCable(String cusCable) {
		this.cusCable = cusCable;
	}

	public String getCusFax() {
		return this.cusFax;
	}

	public void setCusFax(String cusFax) {
		this.cusFax = cusFax;
	}

	public String getCusRelation() {
		return this.cusRelation;
	}

	public void setCusRelation(String cusRelation) {
		this.cusRelation = cusRelation;
	}

	public String getCusTradeType() {
		return this.cusTradeType;
	}

	public void setCusTradeType(String cusTradeType) {
		this.cusTradeType = cusTradeType;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getProviceCode() {
		return this.proviceCode;
	}

	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCusEmail() {
		return this.cusEmail;
	}

	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}

	public String getCusRmbAccount() {
		return this.cusRmbAccount;
	}

	public void setCusRmbAccount(String cusRmbAccount) {
		this.cusRmbAccount = cusRmbAccount;
	}

	public String getCusDescript() {
		return this.cusDescript;
	}

	public void setCusDescript(String cusDescript) {
		this.cusDescript = cusDescript;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankDollor() {
		return this.bankDollor;
	}

	public void setBankDollor(String bankDollor) {
		this.bankDollor = bankDollor;
	}

	public String getCusDollarAccount() {
		return this.cusDollarAccount;
	}

	public void setCusDollarAccount(String cusDollarAccount) {
		this.cusDollarAccount = cusDollarAccount;
	}

	public String getBussinessCode() {
		return this.bussinessCode;
	}

	public void setBussinessCode(String bussinessCode) {
		this.bussinessCode = bussinessCode;
	}

	public String getAgentType() {
		return this.agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAgentCity() {
		return this.agentCity;
	}

	public void setAgentCity(String agentCity) {
		this.agentCity = agentCity;
	}

	public String getAgentComm() {
		return this.agentComm;
	}

	public void setAgentComm(String agentComm) {
		this.agentComm = agentComm;
	}

	public String getStandBy1() {
		return this.standBy1;
	}

	public void setStandBy1(String standBy1) {
		this.standBy1 = standBy1;
	}

	public String getAgentAc() {
		return this.agentAc;
	}

	public void setAgentAc(String agentAc) {
		this.agentAc = agentAc;
	}

	public Integer getOutIn() {
		return this.outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public String getAgentTerms() {
		return this.agentTerms;
	}

	public void setAgentTerms(String agentTerms) {
		this.agentTerms = agentTerms;
	}

	public String getStandBy2() {
		return this.standBy2;
	}

	public void setStandBy2(String standBy2) {
		this.standBy2 = standBy2;
	}

	public String getPlaneOcean() {
		return this.planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public String getCiqcode() {
		return this.ciqcode;
	}

	public void setCiqcode(String ciqcode) {
		this.ciqcode = ciqcode;
	}

	public String getForeignSource() {
		return this.foreignSource;
	}

	public void setForeignSource(String foreignSource) {
		this.foreignSource = foreignSource;
	}

	public String getStandBy3() {
		return this.standBy3;
	}

	public void setStandBy3(String standBy3) {
		this.standBy3 = standBy3;
	}

	public String getWriteDate() {
		return this.writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getForeignRequest() {
		return this.foreignRequest;
	}

	public void setForeignRequest(String foreignRequest) {
		this.foreignRequest = foreignRequest;
	}

	public Integer getBeRoutingOrder() {
		return this.beRoutingOrder;
	}

	public void setBeRoutingOrder(Integer beRoutingOrder) {
		this.beRoutingOrder = beRoutingOrder;
	}

	public Integer getBeFreeHand() {
		return this.beFreeHand;
	}

	public void setBeFreeHand(Integer beFreeHand) {
		this.beFreeHand = beFreeHand;
	}

	public Integer getBeSale() {
		return this.beSale;
	}

	public void setBeSale(Integer beSale) {
		this.beSale = beSale;
	}

	public String getRoagent() {
		return this.roagent;
	}

	public void setRoagent(String roagent) {
		this.roagent = roagent;
	}

	public String getRoagentTele() {
		return this.roagentTele;
	}

	public void setRoagentTele(String roagentTele) {
		this.roagentTele = roagentTele;
	}

	public String getRoagentFax() {
		return this.roagentFax;
	}

	public void setRoagentFax(String roagentFax) {
		this.roagentFax = roagentFax;
	}

	public String getRoagentCountry() {
		return this.roagentCountry;
	}

	public void setRoagentCountry(String roagentCountry) {
		this.roagentCountry = roagentCountry;
	}

	public String getRoagentPort() {
		return this.roagentPort;
	}

	public void setRoagentPort(String roagentPort) {
		this.roagentPort = roagentPort;
	}

	public String getRoagentRela() {
		return this.roagentRela;
	}

	public void setRoagentRela(String roagentRela) {
		this.roagentRela = roagentRela;
	}

	public String getRoagentEmail() {
		return this.roagentEmail;
	}

	public void setRoagentEmail(String roagentEmail) {
		this.roagentEmail = roagentEmail;
	}

	public String getRoagentRequestOper() {
		return this.roagentRequestOper;
	}

	public void setRoagentRequestOper(String roagentRequestOper) {
		this.roagentRequestOper = roagentRequestOper;
	}

	public String getFhstation() {
		return this.fhstation;
	}

	public void setFhstation(String fhstation) {
		this.fhstation = fhstation;
	}

	public String getFhbussiness() {
		return this.fhbussiness;
	}

	public void setFhbussiness(String fhbussiness) {
		this.fhbussiness = fhbussiness;
	}

	public String getFhbussinessRequest() {
		return this.fhbussinessRequest;
	}

	public void setFhbussinessRequest(String fhbussinessRequest) {
		this.fhbussinessRequest = fhbussinessRequest;
	}

	public String getSaleAgent() {
		return this.saleAgent;
	}

	public void setSaleAgent(String saleAgent) {
		this.saleAgent = saleAgent;
	}

	public String getSaleAgentTele() {
		return this.saleAgentTele;
	}

	public void setSaleAgentTele(String saleAgentTele) {
		this.saleAgentTele = saleAgentTele;
	}

	public String getSaleAgentRequestOper() {
		return this.saleAgentRequestOper;
	}

	public void setSaleAgentRequestOper(String saleAgentRequestOper) {
		this.saleAgentRequestOper = saleAgentRequestOper;
	}

	public String getSaleAgentRela() {
		return this.saleAgentRela;
	}

	public void setSaleAgentRela(String saleAgentRela) {
		this.saleAgentRela = saleAgentRela;
	}

	public String getSaleStation() {
		return this.saleStation;
	}

	public void setSaleStation(String saleStation) {
		this.saleStation = saleStation;
	}

	public String getSaleBussiness() {
		return this.saleBussiness;
	}

	public void setSaleBussiness(String saleBussiness) {
		this.saleBussiness = saleBussiness;
	}

	public String getSaleBussinessRequest() {
		return this.saleBussinessRequest;
	}

	public void setSaleBussinessRequest(String saleBussinessRequest) {
		this.saleBussinessRequest = saleBussinessRequest;
	}

	public String getAppraiseOper() {
		return this.appraiseOper;
	}

	public void setAppraiseOper(String appraiseOper) {
		this.appraiseOper = appraiseOper;
	}

	public String getAppraiseFinance() {
		return this.appraiseFinance;
	}

	public void setAppraiseFinance(String appraiseFinance) {
		this.appraiseFinance = appraiseFinance;
	}

	public String getAppraiseCs() {
		return this.appraiseCs;
	}

	public void setAppraiseCs(String appraiseCs) {
		this.appraiseCs = appraiseCs;
	}

	public String getAnalysisTrade() {
		return this.analysisTrade;
	}

	public void setAnalysisTrade(String analysisTrade) {
		this.analysisTrade = analysisTrade;
	}

	public String getAnalysisBussiness() {
		return this.analysisBussiness;
	}

	public void setAnalysisBussiness(String analysisBussiness) {
		this.analysisBussiness = analysisBussiness;
	}

	public String getProtocal() {
		return this.protocal;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public String getRequestOper() {
		return this.requestOper;
	}

	public void setRequestOper(String requestOper) {
		this.requestOper = requestOper;
	}

	public String getRequestBg() {
		return this.requestBg;
	}

	public void setRequestBg(String requestBg) {
		this.requestBg = requestBg;
	}

	public String getRequestCk() {
		return this.requestCk;
	}

	public void setRequestCk(String requestCk) {
		this.requestCk = requestCk;
	}

	public String getRequestCar() {
		return this.requestCar;
	}

	public void setRequestCar(String requestCar) {
		this.requestCar = requestCar;
	}

	public String getRequestOther() {
		return this.requestOther;
	}

	public void setRequestOther(String requestOther) {
		this.requestOther = requestOther;
	}

	public String getFeedBackCs() {
		return this.feedBackCs;
	}

	public void setFeedBackCs(String feedBackCs) {
		this.feedBackCs = feedBackCs;
	}

	public String getFeedBackOper() {
		return this.feedBackOper;
	}

	public void setFeedBackOper(String feedBackOper) {
		this.feedBackOper = feedBackOper;
	}

	public String getFeedBackFinance() {
		return this.feedBackFinance;
	}

	public void setFeedBackFinance(String feedBackFinance) {
		this.feedBackFinance = feedBackFinance;
	}

	public String getParentCusCode() {
		return this.parentCusCode;
	}

	public void setParentCusCode(String parentCusCode) {
		this.parentCusCode = parentCusCode;
	}

	public String getCwCusCode() {
		return this.cwCusCode;
	}

	public void setCwCusCode(String cwCusCode) {
		this.cwCusCode = cwCusCode;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCwCusCodeTwo() {
		return this.cwCusCodeTwo;
	}

	public void setCwCusCodeTwo(String cwCusCodeTwo) {
		this.cwCusCodeTwo = cwCusCodeTwo;
	}

	public String getDcmsid() {
		return this.dcmsid;
	}

	public void setDcmsid(String dcmsid) {
		this.dcmsid = dcmsid;
	}

	public String getCwCusCodeNameTwo() {
		return this.cwCusCodeNameTwo;
	}

	public void setCwCusCodeNameTwo(String cwCusCodeNameTwo) {
		this.cwCusCodeNameTwo = cwCusCodeNameTwo;
	}

	public String getCwCusCodeName() {
		return this.cwCusCodeName;
	}

	public void setCwCusCodeName(String cwCusCodeName) {
		this.cwCusCodeName = cwCusCodeName;
	}

	public String getWebCusCode() {
		return this.webCusCode;
	}

	public void setWebCusCode(String webCusCode) {
		this.webCusCode = webCusCode;
	}

	public Integer getBeUse() {
		return this.beUse;
	}

	public void setBeUse(Integer beUse) {
		this.beUse = beUse;
	}

	public String getBeginUseDate() {
		return this.beginUseDate;
	}

	public void setBeginUseDate(String beginUseDate) {
		this.beginUseDate = beginUseDate;
	}

	public String getRecentUseDate() {
		return this.recentUseDate;
	}

	public void setRecentUseDate(String recentUseDate) {
		this.recentUseDate = recentUseDate;
	}

	public String getCusGroup() {
		return this.cusGroup;
	}

	public void setCusGroup(String cusGroup) {
		this.cusGroup = cusGroup;
	}

	public String getCusState() {
		return this.cusState;
	}

	public void setCusState(String cusState) {
		this.cusState = cusState;
	}

	public String getCusInvoiceName() {
		return this.cusInvoiceName;
	}

	public void setCusInvoiceName(String cusInvoiceName) {
		this.cusInvoiceName = cusInvoiceName;
	}

	public String getCusServiceName() {
		return this.cusServiceName;
	}

	public void setCusServiceName(String cusServiceName) {
		this.cusServiceName = cusServiceName;
	}

	public String getProtocolNo() {
		return this.protocolNo;
	}

	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}

	public String getEffectDateBegin() {
		return this.effectDateBegin;
	}

	public void setEffectDateBegin(String effectDateBegin) {
		this.effectDateBegin = effectDateBegin;
	}

	public String getEffectDateEnd() {
		return this.effectDateEnd;
	}

	public void setEffectDateEnd(String effectDateEnd) {
		this.effectDateEnd = effectDateEnd;
	}

	public Double getArrearageAcount() {
		return this.arrearageAcount;
	}

	public void setArrearageAcount(Double arrearageAcount) {
		this.arrearageAcount = arrearageAcount;
	}

	public Integer getArrearageMonth() {
		return this.arrearageMonth;
	}

	public void setArrearageMonth(Integer arrearageMonth) {
		this.arrearageMonth = arrearageMonth;
	}

	public Integer getArrearageDay() {
		return this.arrearageDay;
	}

	public void setArrearageDay(Integer arrearageDay) {
		this.arrearageDay = arrearageDay;
	}

	public String getBalanceType() {
		return this.balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public Integer getTaxType() {
		return this.taxType;
	}

	public void setTaxType(Integer taxType) {
		this.taxType = taxType;
	}

	public Integer getReceTaxType() {
		return this.receTaxType;
	}

	public void setReceTaxType(Integer receTaxType) {
		this.receTaxType = receTaxType;
	}

	public Integer getDutyTax() {
		return this.dutyTax;
	}

	public void setDutyTax(Integer dutyTax) {
		this.dutyTax = dutyTax;
	}

	public Integer getDistanceFlightDate() {
		return this.distanceFlightDate;
	}

	public void setDistanceFlightDate(Integer distanceFlightDate) {
		this.distanceFlightDate = distanceFlightDate;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCneeNumberType() {
		return this.cneeNumberType;
	}

	public void setCneeNumberType(String cneeNumberType) {
		this.cneeNumberType = cneeNumberType;
	}

	public String getCneeNumber() {
		return this.cneeNumber;
	}

	public void setCneeNumber(String cneeNumber) {
		this.cneeNumber = cneeNumber;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getProtocolContext() {
		return this.protocolContext;
	}

	public void setProtocolContext(String protocolContext) {
		this.protocolContext = protocolContext;
	}

	public Integer getIsChina() {
		return this.isChina;
	}

	public void setIsChina(Integer isChina) {
		this.isChina = isChina;
	}

	public String getLclSpecialMark() {
		return this.lclSpecialMark;
	}

	public void setLclSpecialMark(String lclSpecialMark) {
		this.lclSpecialMark = lclSpecialMark;
	}

	public Integer getBeAttention() {
		return this.beAttention;
	}

	public void setBeAttention(Integer beAttention) {
		this.beAttention = beAttention;
	}

}