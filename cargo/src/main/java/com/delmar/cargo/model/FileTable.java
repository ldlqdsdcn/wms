package com.delmar.cargo.model;

/**
 * FileTable entity. @author MyEclipse Persistence Tools
 */

public class FileTable implements java.io.Serializable {

	// Fields
    private String planeOcean="Air";
	private String trustFileCode;
	private String companyId;
	private Integer indexOrder;
	private Integer beZhiDan;
	private Integer beFinish;
	private String hawb;
	private String finishDate;
	private String orderCode;
	private Integer beConfirm;
	private Integer beFareFinish;
	private Integer beSpelling;
	private String fareFinishDate;
	private String mainCode;
	private String bupMainCode;
	private String billProp;
	private String billVest;
	private String incoterm;
	private String busType;
	private String confirmDate;
	private Integer beDrMark;
	private Integer beNotStat;
	private String trustState;
	private String drMarkDate;
	private Integer beCwConfirm;
	private String fileNo;
	private String cwConfirmDate;
	private Integer beShMark;
	private String shMarkDate;
	private Integer beCoLoad;
	private String coLoadFrom;
	private Integer beCommend;
	private String aic;
	private Integer beWareHouse;
	private String transType;
	private String wareHouseDate;
	private String shipperCode;
	private String shipperName;
	private String shipperRelaCode;
	private String accountNo;
	private String consignCode;
	private String goodsdesc;
	private String expLicense;
	private String consignName;
	private String agentCode;
	private String agentName;
	private String agentNameTwo;
	private String serviceType;
	private String trustType;
	private String operType;
	private String planeCompany;
	private String coloadCompany;
	private String trustSource;
	private String shipper;
	private String shipperMainfest;
	private String consigneeMainfest;
	private String consignee;
	private String agentAcc;
	private String informarCode;
	private String informarName;
	private String informar;
	private String informarTwo;
	private String informarThree;
	private String podesc;
	private String goodsDescJc;
	private Double goodsNumber;
	private String goodsNumberDesc;
	private String goodsPackage;
	private Double goodsWeight;
	private String stationCode;
	private Double goodsChargeWeight;
	private String goodsWeightPackage;
	private Double goodsSize;
	private String formula;
	private String goodsSizePackage;
	private String reciPlace;
	private String airPortFrom;
	private String airPortFromDesc;
	private String airPortToDesc;
	private String airPortTo;
	private String arrivePort;
	private String arrivePortDesc;
	private String destPlace;
	private String accInfo;
	private String curr;
	private String accInsurance;
	private String chgs;
	private String ppd;
	private String coll;
	private String oppd;
	private String preCollDesc;
	private String ocoll;
	private String dvc;
	private String dvcus;
	private Double amount;
	private Double unitPrice;
	private Double srate;
	private Double addOn;
	private String handling;
	private String wcharge;
	private Double prePay;
	private String vpreCharge;
	private Double dpay;
	private String acharge;
	private String tpreCharge;
	private String ochargeAll;
	private String ocs;
	private String vdcharge;
	private Double preTotal;
	private String execDate;
	private String tdcharge;
	private Double toapre;
	private String qddate;
	private String operatorName;
	private String execPlace;
	private Double toacollect;
	private Double tocpre;
	private Double dtotal;
	private Integer outIn;
	private Double toccollect;
	private String operator;
	private String securityCurr;
	private Double securityAcount;
	private String securityPackage;
	private String otherDesc;
	private String filiale;
	private String bussiness;
	private Double credit;
	private Double debit;
	private Double profit;
	private String standBy1;
	private Double realCredit;
	private Double profitPercent;
	private Double profitPercentTwo;
	private String modiOper;
	private String to1;
	private String flight;
	private Double realDebit;
	private String standBy2;
	private String flightDate;
	private Double balance;
	private String to2;
	private String standBy3;
	private String flight2;
	private String flightDate2;
	private String arriveDate;
	private String arrivePortDate;
	private String to3;
	private String flight3;
	private String flightdate3;
	private String relaCode;
	private String remark2;
	private String remark3;
	private String kmdm;
	private String profitReason;
	private Integer operateMark;
	private String lastModifyDate;
	private String shipperDcmsid;
	private String consignDcmsid;
	private Double bussinessProfit;
	private String zcKmdm;
	private String remark;
	private String standBy4;
	private String standBy5;
	private String standBy6;
	private String cscode;
	private String docOpCode;
	private Integer beTrans;
	private String transParty;
	private Integer beImportTrans;
	private String goodsTypeCode;
	
	private FileTable mainBusiness;

	// Constructors

	/** default constructor */
	public FileTable() {
	}

	/** minimal constructor */
	public FileTable(String trustFileCode) {
		this.trustFileCode = trustFileCode;
	}



	// Property accessors

	public String getTrustFileCode() {
		return this.trustFileCode;
	}

	public void setTrustFileCode(String trustFileCode) {
		this.trustFileCode = trustFileCode;
	}

	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getIndexOrder() {
		return this.indexOrder;
	}

	public void setIndexOrder(Integer indexOrder) {
		this.indexOrder = indexOrder;
	}

	public Integer getBeZhiDan() {
		return this.beZhiDan;
	}

	public void setBeZhiDan(Integer beZhiDan) {
		this.beZhiDan = beZhiDan;
	}

	public Integer getBeFinish() {
		return this.beFinish;
	}

	public void setBeFinish(Integer beFinish) {
		this.beFinish = beFinish;
	}

	public String getHawb() {
		return this.hawb;
	}

	public void setHawb(String hawb) {
		this.hawb = hawb;
	}

	public String getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Integer getBeConfirm() {
		return this.beConfirm;
	}

	public void setBeConfirm(Integer beConfirm) {
		this.beConfirm = beConfirm;
	}

	public Integer getBeFareFinish() {
		return this.beFareFinish;
	}

	public void setBeFareFinish(Integer beFareFinish) {
		this.beFareFinish = beFareFinish;
	}

	public Integer getBeSpelling() {
		return this.beSpelling;
	}

	public void setBeSpelling(Integer beSpelling) {
		this.beSpelling = beSpelling;
	}

	public String getFareFinishDate() {
		return this.fareFinishDate;
	}

	public void setFareFinishDate(String fareFinishDate) {
		this.fareFinishDate = fareFinishDate;
	}

	public String getMainCode() {
		return this.mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public String getBupMainCode() {
		return this.bupMainCode;
	}

	public void setBupMainCode(String bupMainCode) {
		this.bupMainCode = bupMainCode;
	}

	public String getBillProp() {
		return this.billProp;
	}

	public void setBillProp(String billProp) {
		this.billProp = billProp;
	}

	public String getBillVest() {
		return this.billVest;
	}

	public void setBillVest(String billVest) {
		this.billVest = billVest;
	}

	public String getIncoterm() {
		return this.incoterm;
	}

	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Integer getBeDrMark() {
		return this.beDrMark;
	}

	public void setBeDrMark(Integer beDrMark) {
		this.beDrMark = beDrMark;
	}

	public Integer getBeNotStat() {
		return this.beNotStat;
	}

	public void setBeNotStat(Integer beNotStat) {
		this.beNotStat = beNotStat;
	}

	public String getTrustState() {
		return this.trustState;
	}

	public void setTrustState(String trustState) {
		this.trustState = trustState;
	}

	public String getDrMarkDate() {
		return this.drMarkDate;
	}

	public void setDrMarkDate(String drMarkDate) {
		this.drMarkDate = drMarkDate;
	}

	public Integer getBeCwConfirm() {
		return this.beCwConfirm;
	}

	public void setBeCwConfirm(Integer beCwConfirm) {
		this.beCwConfirm = beCwConfirm;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getCwConfirmDate() {
		return this.cwConfirmDate;
	}

	public void setCwConfirmDate(String cwConfirmDate) {
		this.cwConfirmDate = cwConfirmDate;
	}

	public Integer getBeShMark() {
		return this.beShMark;
	}

	public void setBeShMark(Integer beShMark) {
		this.beShMark = beShMark;
	}

	public String getShMarkDate() {
		return this.shMarkDate;
	}

	public void setShMarkDate(String shMarkDate) {
		this.shMarkDate = shMarkDate;
	}

	public Integer getBeCoLoad() {
		return this.beCoLoad;
	}

	public void setBeCoLoad(Integer beCoLoad) {
		this.beCoLoad = beCoLoad;
	}

	public String getCoLoadFrom() {
		return this.coLoadFrom;
	}

	public void setCoLoadFrom(String coLoadFrom) {
		this.coLoadFrom = coLoadFrom;
	}

	public Integer getBeCommend() {
		return this.beCommend;
	}

	public void setBeCommend(Integer beCommend) {
		this.beCommend = beCommend;
	}

	public String getAic() {
		return this.aic;
	}

	public void setAic(String aic) {
		this.aic = aic;
	}

	public Integer getBeWareHouse() {
		return this.beWareHouse;
	}

	public void setBeWareHouse(Integer beWareHouse) {
		this.beWareHouse = beWareHouse;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getWareHouseDate() {
		return this.wareHouseDate;
	}

	public void setWareHouseDate(String wareHouseDate) {
		this.wareHouseDate = wareHouseDate;
	}

	public String getShipperCode() {
		return this.shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperRelaCode() {
		return this.shipperRelaCode;
	}

	public void setShipperRelaCode(String shipperRelaCode) {
		this.shipperRelaCode = shipperRelaCode;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getConsignCode() {
		return this.consignCode;
	}

	public void setConsignCode(String consignCode) {
		this.consignCode = consignCode;
	}

	public String getGoodsdesc() {
		return this.goodsdesc;
	}

	public void setGoodsdesc(String goodsdesc) {
		this.goodsdesc = goodsdesc;
	}

	public String getExpLicense() {
		return this.expLicense;
	}

	public void setExpLicense(String expLicense) {
		this.expLicense = expLicense;
	}

	public String getConsignName() {
		return this.consignName;
	}

	public void setConsignName(String consignName) {
		this.consignName = consignName;
	}

	public String getAgentCode() {
		return this.agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return this.agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentNameTwo() {
		return this.agentNameTwo;
	}

	public void setAgentNameTwo(String agentNameTwo) {
		this.agentNameTwo = agentNameTwo;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getTrustType() {
		return this.trustType;
	}

	public void setTrustType(String trustType) {
		this.trustType = trustType;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getPlaneCompany() {
		return this.planeCompany;
	}

	public void setPlaneCompany(String planeCompany) {
		this.planeCompany = planeCompany;
	}

	public String getColoadCompany() {
		return this.coloadCompany;
	}

	public void setColoadCompany(String coloadCompany) {
		this.coloadCompany = coloadCompany;
	}

	public String getTrustSource() {
		return this.trustSource;
	}

	public void setTrustSource(String trustSource) {
		this.trustSource = trustSource;
	}

	public String getShipper() {
		return this.shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getShipperMainfest() {
		return this.shipperMainfest;
	}

	public void setShipperMainfest(String shipperMainfest) {
		this.shipperMainfest = shipperMainfest;
	}

	public String getConsigneeMainfest() {
		return this.consigneeMainfest;
	}

	public void setConsigneeMainfest(String consigneeMainfest) {
		this.consigneeMainfest = consigneeMainfest;
	}

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getAgentAcc() {
		return this.agentAcc;
	}

	public void setAgentAcc(String agentAcc) {
		this.agentAcc = agentAcc;
	}

	public String getInformarCode() {
		return this.informarCode;
	}

	public void setInformarCode(String informarCode) {
		this.informarCode = informarCode;
	}

	public String getInformarName() {
		return this.informarName;
	}

	public void setInformarName(String informarName) {
		this.informarName = informarName;
	}

	public String getInformar() {
		return this.informar;
	}

	public void setInformar(String informar) {
		this.informar = informar;
	}

	public String getInformarTwo() {
		return this.informarTwo;
	}

	public void setInformarTwo(String informarTwo) {
		this.informarTwo = informarTwo;
	}

	public String getInformarThree() {
		return this.informarThree;
	}

	public void setInformarThree(String informarThree) {
		this.informarThree = informarThree;
	}

	public String getPodesc() {
		return this.podesc;
	}

	public void setPodesc(String podesc) {
		this.podesc = podesc;
	}

	public String getGoodsDescJc() {
		return this.goodsDescJc;
	}

	public void setGoodsDescJc(String goodsDescJc) {
		this.goodsDescJc = goodsDescJc;
	}

	public Double getGoodsNumber() {
		return this.goodsNumber;
	}

	public void setGoodsNumber(Double goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getGoodsNumberDesc() {
		return this.goodsNumberDesc;
	}

	public void setGoodsNumberDesc(String goodsNumberDesc) {
		this.goodsNumberDesc = goodsNumberDesc;
	}

	public String getGoodsPackage() {
		return this.goodsPackage;
	}

	public void setGoodsPackage(String goodsPackage) {
		this.goodsPackage = goodsPackage;
	}

	public Double getGoodsWeight() {
		return this.goodsWeight;
	}

	public void setGoodsWeight(Double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public String getStationCode() {
		return this.stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public Double getGoodsChargeWeight() {
		return this.goodsChargeWeight;
	}

	public void setGoodsChargeWeight(Double goodsChargeWeight) {
		this.goodsChargeWeight = goodsChargeWeight;
	}

	public String getGoodsWeightPackage() {
		return this.goodsWeightPackage;
	}

	public void setGoodsWeightPackage(String goodsWeightPackage) {
		this.goodsWeightPackage = goodsWeightPackage;
	}

	public Double getGoodsSize() {
		return this.goodsSize;
	}

	public void setGoodsSize(Double goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getGoodsSizePackage() {
		return this.goodsSizePackage;
	}

	public void setGoodsSizePackage(String goodsSizePackage) {
		this.goodsSizePackage = goodsSizePackage;
	}

	public String getReciPlace() {
		return this.reciPlace;
	}

	public void setReciPlace(String reciPlace) {
		this.reciPlace = reciPlace;
	}

	public String getAirPortFrom() {
		return this.airPortFrom;
	}

	public void setAirPortFrom(String airPortFrom) {
		this.airPortFrom = airPortFrom;
	}

	public String getAirPortFromDesc() {
		return this.airPortFromDesc;
	}

	public void setAirPortFromDesc(String airPortFromDesc) {
		this.airPortFromDesc = airPortFromDesc;
	}

	public String getAirPortToDesc() {
		return this.airPortToDesc;
	}

	public void setAirPortToDesc(String airPortToDesc) {
		this.airPortToDesc = airPortToDesc;
	}

	public String getAirPortTo() {
		return this.airPortTo;
	}

	public void setAirPortTo(String airPortTo) {
		this.airPortTo = airPortTo;
	}

	public String getArrivePort() {
		return this.arrivePort;
	}

	public void setArrivePort(String arrivePort) {
		this.arrivePort = arrivePort;
	}

	public String getArrivePortDesc() {
		return this.arrivePortDesc;
	}

	public void setArrivePortDesc(String arrivePortDesc) {
		this.arrivePortDesc = arrivePortDesc;
	}

	public String getDestPlace() {
		return this.destPlace;
	}

	public void setDestPlace(String destPlace) {
		this.destPlace = destPlace;
	}

	public String getAccInfo() {
		return this.accInfo;
	}

	public void setAccInfo(String accInfo) {
		this.accInfo = accInfo;
	}

	public String getCurr() {
		return this.curr;
	}

	public void setCurr(String curr) {
		this.curr = curr;
	}

	public String getAccInsurance() {
		return this.accInsurance;
	}

	public void setAccInsurance(String accInsurance) {
		this.accInsurance = accInsurance;
	}

	public String getChgs() {
		return this.chgs;
	}

	public void setChgs(String chgs) {
		this.chgs = chgs;
	}

	public String getPpd() {
		return this.ppd;
	}

	public void setPpd(String ppd) {
		this.ppd = ppd;
	}

	public String getColl() {
		return this.coll;
	}

	public void setColl(String coll) {
		this.coll = coll;
	}

	public String getOppd() {
		return this.oppd;
	}

	public void setOppd(String oppd) {
		this.oppd = oppd;
	}

	public String getPreCollDesc() {
		return this.preCollDesc;
	}

	public void setPreCollDesc(String preCollDesc) {
		this.preCollDesc = preCollDesc;
	}

	public String getOcoll() {
		return this.ocoll;
	}

	public void setOcoll(String ocoll) {
		this.ocoll = ocoll;
	}

	public String getDvc() {
		return this.dvc;
	}

	public void setDvc(String dvc) {
		this.dvc = dvc;
	}

	public String getDvcus() {
		return this.dvcus;
	}

	public void setDvcus(String dvcus) {
		this.dvcus = dvcus;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getSrate() {
		return this.srate;
	}

	public void setSrate(Double srate) {
		this.srate = srate;
	}

	public Double getAddOn() {
		return this.addOn;
	}

	public void setAddOn(Double addOn) {
		this.addOn = addOn;
	}

	public String getHandling() {
		return this.handling;
	}

	public void setHandling(String handling) {
		this.handling = handling;
	}

	public String getWcharge() {
		return this.wcharge;
	}

	public void setWcharge(String wcharge) {
		this.wcharge = wcharge;
	}

	public Double getPrePay() {
		return this.prePay;
	}

	public void setPrePay(Double prePay) {
		this.prePay = prePay;
	}

	public String getVpreCharge() {
		return this.vpreCharge;
	}

	public void setVpreCharge(String vpreCharge) {
		this.vpreCharge = vpreCharge;
	}

	public Double getDpay() {
		return this.dpay;
	}

	public void setDpay(Double dpay) {
		this.dpay = dpay;
	}

	public String getAcharge() {
		return this.acharge;
	}

	public void setAcharge(String acharge) {
		this.acharge = acharge;
	}

	public String getTpreCharge() {
		return this.tpreCharge;
	}

	public void setTpreCharge(String tpreCharge) {
		this.tpreCharge = tpreCharge;
	}

	public String getOchargeAll() {
		return this.ochargeAll;
	}

	public void setOchargeAll(String ochargeAll) {
		this.ochargeAll = ochargeAll;
	}

	public String getOcs() {
		return this.ocs;
	}

	public void setOcs(String ocs) {
		this.ocs = ocs;
	}

	public String getVdcharge() {
		return this.vdcharge;
	}

	public void setVdcharge(String vdcharge) {
		this.vdcharge = vdcharge;
	}

	public Double getPreTotal() {
		return this.preTotal;
	}

	public void setPreTotal(Double preTotal) {
		this.preTotal = preTotal;
	}

	public String getExecDate() {
		return this.execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getTdcharge() {
		return this.tdcharge;
	}

	public void setTdcharge(String tdcharge) {
		this.tdcharge = tdcharge;
	}

	public Double getToapre() {
		return this.toapre;
	}

	public void setToapre(Double toapre) {
		this.toapre = toapre;
	}

	public String getQddate() {
		return this.qddate;
	}

	public void setQddate(String qddate) {
		this.qddate = qddate;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getExecPlace() {
		return this.execPlace;
	}

	public void setExecPlace(String execPlace) {
		this.execPlace = execPlace;
	}

	public Double getToacollect() {
		return this.toacollect;
	}

	public void setToacollect(Double toacollect) {
		this.toacollect = toacollect;
	}

	public Double getTocpre() {
		return this.tocpre;
	}

	public void setTocpre(Double tocpre) {
		this.tocpre = tocpre;
	}

	public Double getDtotal() {
		return this.dtotal;
	}

	public void setDtotal(Double dtotal) {
		this.dtotal = dtotal;
	}

	public Integer getOutIn() {
		return this.outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public Double getToccollect() {
		return this.toccollect;
	}

	public void setToccollect(Double toccollect) {
		this.toccollect = toccollect;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSecurityCurr() {
		return this.securityCurr;
	}

	public void setSecurityCurr(String securityCurr) {
		this.securityCurr = securityCurr;
	}

	public Double getSecurityAcount() {
		return this.securityAcount;
	}

	public void setSecurityAcount(Double securityAcount) {
		this.securityAcount = securityAcount;
	}

	public String getSecurityPackage() {
		return this.securityPackage;
	}

	public void setSecurityPackage(String securityPackage) {
		this.securityPackage = securityPackage;
	}

	public String getOtherDesc() {
		return this.otherDesc;
	}

	public void setOtherDesc(String otherDesc) {
		this.otherDesc = otherDesc;
	}

	public String getFiliale() {
		return this.filiale;
	}

	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public String getBussiness() {
		return this.bussiness;
	}

	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}

	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Double getDebit() {
		return this.debit;
	}

	public void setDebit(Double debit) {
		this.debit = debit;
	}

	public Double getProfit() {
		return this.profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getStandBy1() {
		return this.standBy1;
	}

	public void setStandBy1(String standBy1) {
		this.standBy1 = standBy1;
	}

	public Double getRealCredit() {
		return this.realCredit;
	}

	public void setRealCredit(Double realCredit) {
		this.realCredit = realCredit;
	}

	public Double getProfitPercent() {
		return this.profitPercent;
	}

	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}

	public Double getProfitPercentTwo() {
		return this.profitPercentTwo;
	}

	public void setProfitPercentTwo(Double profitPercentTwo) {
		this.profitPercentTwo = profitPercentTwo;
	}

	public String getModiOper() {
		return this.modiOper;
	}

	public void setModiOper(String modiOper) {
		this.modiOper = modiOper;
	}

	public String getTo1() {
		return this.to1;
	}

	public void setTo1(String to1) {
		this.to1 = to1;
	}

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public Double getRealDebit() {
		return this.realDebit;
	}

	public void setRealDebit(Double realDebit) {
		this.realDebit = realDebit;
	}

	public String getStandBy2() {
		return this.standBy2;
	}

	public void setStandBy2(String standBy2) {
		this.standBy2 = standBy2;
	}

	public String getFlightDate() {
		return this.flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getTo2() {
		return this.to2;
	}

	public void setTo2(String to2) {
		this.to2 = to2;
	}

	public String getStandBy3() {
		return this.standBy3;
	}

	public void setStandBy3(String standBy3) {
		this.standBy3 = standBy3;
	}

	public String getFlight2() {
		return this.flight2;
	}

	public void setFlight2(String flight2) {
		this.flight2 = flight2;
	}

	public String getFlightDate2() {
		return this.flightDate2;
	}

	public void setFlightDate2(String flightDate2) {
		this.flightDate2 = flightDate2;
	}

	public String getArriveDate() {
		return this.arriveDate;
	}

	public void setArriveDate(String arriveDate) {
		this.arriveDate = arriveDate;
	}

	public String getArrivePortDate() {
		return this.arrivePortDate;
	}

	public void setArrivePortDate(String arrivePortDate) {
		this.arrivePortDate = arrivePortDate;
	}

	public String getTo3() {
		return this.to3;
	}

	public void setTo3(String to3) {
		this.to3 = to3;
	}

	public String getFlight3() {
		return this.flight3;
	}

	public void setFlight3(String flight3) {
		this.flight3 = flight3;
	}

	public String getFlightdate3() {
		return this.flightdate3;
	}

	public void setFlightdate3(String flightdate3) {
		this.flightdate3 = flightdate3;
	}

	public String getRelaCode() {
		return this.relaCode;
	}

	public void setRelaCode(String relaCode) {
		this.relaCode = relaCode;
	}

	public String getRemark2() {
		return this.remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getKmdm() {
		return this.kmdm;
	}

	public void setKmdm(String kmdm) {
		this.kmdm = kmdm;
	}

	public String getProfitReason() {
		return this.profitReason;
	}

	public void setProfitReason(String profitReason) {
		this.profitReason = profitReason;
	}

	public Integer getOperateMark() {
		return this.operateMark;
	}

	public void setOperateMark(Integer operateMark) {
		this.operateMark = operateMark;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public String getShipperDcmsid() {
		return this.shipperDcmsid;
	}

	public void setShipperDcmsid(String shipperDcmsid) {
		this.shipperDcmsid = shipperDcmsid;
	}

	public String getConsignDcmsid() {
		return this.consignDcmsid;
	}

	public void setConsignDcmsid(String consignDcmsid) {
		this.consignDcmsid = consignDcmsid;
	}

	public Double getBussinessProfit() {
		return this.bussinessProfit;
	}

	public void setBussinessProfit(Double bussinessProfit) {
		this.bussinessProfit = bussinessProfit;
	}

	public String getZcKmdm() {
		return this.zcKmdm;
	}

	public void setZcKmdm(String zcKmdm) {
		this.zcKmdm = zcKmdm;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStandBy4() {
		return this.standBy4;
	}

	public void setStandBy4(String standBy4) {
		this.standBy4 = standBy4;
	}

	public String getStandBy5() {
		return this.standBy5;
	}

	public void setStandBy5(String standBy5) {
		this.standBy5 = standBy5;
	}

	public String getStandBy6() {
		return this.standBy6;
	}

	public void setStandBy6(String standBy6) {
		this.standBy6 = standBy6;
	}

	public String getCscode() {
		return this.cscode;
	}

	public void setCscode(String cscode) {
		this.cscode = cscode;
	}

	public String getDocOpCode() {
		return this.docOpCode;
	}

	public void setDocOpCode(String docOpCode) {
		this.docOpCode = docOpCode;
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

	public Integer getBeImportTrans() {
		return this.beImportTrans;
	}

	public void setBeImportTrans(Integer beImportTrans) {
		this.beImportTrans = beImportTrans;
	}

	public String getGoodsTypeCode() {
		return this.goodsTypeCode;
	}

	public void setGoodsTypeCode(String goodsTypeCode) {
		this.goodsTypeCode = goodsTypeCode;
	}

	public FileTable getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(FileTable mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public  String getPlaneOcean() {
		return planeOcean;
	}
	
	

}