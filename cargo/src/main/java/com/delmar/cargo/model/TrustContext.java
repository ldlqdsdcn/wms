package com.delmar.cargo.model;

/**
 * TrustContext entity. @author MyEclipse Persistence Tools
 */

public class TrustContext implements java.io.Serializable {

	// Fields
	private String planeOcean="Ocean";
	private String trustFileCode;
	private String companyId;
	private Integer indexOrder;
	private Integer beZhiDan;
	private Integer beFinish;
	private String hawb;
	private String sono;
	private String finishDate;
	private String orderCode;
	private Integer beConfirm;
	private Integer beFareFinish;
	private Integer beBg;
	private Integer beSpelling;
	private String billProp;
	private String billVest;
	private String incoterm;
	private String busType;
	private String fareFinishDate;
	private Integer beOweSpell;
	private String mainCode;
	private String confirmDate;
	private Integer beDrMark;
	private Integer beNotStat;
	private String trustState;
	private String drMarkDate;
	private Integer beCwConfirm;
	private String cwConfirmDate;
	private String fileNo;
	private String shMarkDate;
	private Integer beShMark;
	private Integer beCoLoad;
	private String coLoadFrom;
	private Integer beCommend;
	private String shipperCode;
	private Integer beWareHouse;
	private String shipperName;
	private String shipperRealCode;
	private String wareHouseDate;
	private String consignCode;
	private String goodsDesc;
	private String consignName;
	private String agentCode;
	private String agentName;
	private String agentNameTwo;
	private String transType;
	private String trustType;
	private String operType;
	private String shipCompany;
	private String coloadCompany;
	private String shipOrderAgent;
	private String shipPxAgent;
	private String trustSource;
	private String shipper;
	private String consignee;
	private String informarCode;
	private String informarName;
	private String routeCode;
	private String informar;
	private String informarTwo;
	private String informarThree;
	private String poDesc;
	private Double goodsNumber;
	private String goodsDescJc;
	private String goodsPackage;
	private Double goodsWeight;
	private String goodsNumberDesc;
	private String stationCode;
	private String formula;
	private Double goodsChargeWeight;
	private String goodsWeightPackage;
	private Double goodsSize;
	private String goodsSizePackage;
	private Double unitPrice;
	private Double srate;
	private String reciPlace;
	private String airPortFrom;
	private String containerLable;
	private String containerType;
	private Double teuNum;
	private String otherContent;
	private String airPortFromDesc;
	private String airPortTo;
	private String airPortToDesc;
	private String arrivePort;
	private String arrivePortDesc;
	private String destPlace;
	private String maiTou;
	private String freight;
	private String freightDesc;
	private String prePaid;
	private String collectPaid;
	private String otherPaid;
	private String acifreight;
	private String prePlace;
	private String collectPlace;
	private String containerTerm;
	private String moveMent;
	private String upperNumber;
	private String execDate;
	private String operatorName;
	private String execPlace;
	private Integer outIn;
	private String operator;
	private String filiale;
	private String bussiness;
	private String relaCode;
	private String qddate;
	private Double credit;
	private Double debit;
	private Double profit;
	private String standBy1;
	private Double realCredit;
	private String modiOper;
	private Double profitPercent;
	private Double profitPercentTwo;
	private String flight;
	private String kmdm;
	private Double realDebit;
	private String zcKmdm;
	private String standBy2;
	private String flightDate;
	private Double balance;
	private String to2;
	private String standBy3;
	private String shipName;
	private String flight2;
	private String flightDate2;
	private String arriveDate;
	private String arrivePortDate;
	private String remark2;
	private String remark3;
	private String to3;
	private String shipName2;
	private String lastModifyDate;
	private String flight3;
	private String flightdate3;
	private String profitReason;
	private String finishReason;
	private Integer operateMark;
	private String shipperDcmsid;
	private String consignDcmsid;
	private String isfref;
	private String carrierMbl;
	private String scaccode;
	private String remark;
	private Double bussinessProfit;
	private String shipName3;
	private String standBy4;
	private String standBy5;
	private String standBy6;
	private String routing;
	private String commerceNo;
	private String lcno;
	private String die;
	private String scn;
	private String cscode;
	private String docOpCode;
	private Integer beTrans;
	private String transParty;
	private Integer beImportTrans;
	private String goodsTypeCode;
	
	private TrustContext mainBusiness;
	
	private PortInfo airPortToPort;
	
	// Constructors


	public PortInfo getAirPortToPort() {
		return airPortToPort;
	}

	public void setAirPortToPort(PortInfo airPortToPort) {
		this.airPortToPort = airPortToPort;
	}


	/** default constructor */
	public TrustContext() {
	}

	/** minimal constructor */
	public TrustContext(String trustFileCode) {
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

	public String getSono() {
		return this.sono;
	}

	public void setSono(String sono) {
		this.sono = sono;
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

	public Integer getBeBg() {
		return this.beBg;
	}

	public void setBeBg(Integer beBg) {
		this.beBg = beBg;
	}

	public Integer getBeSpelling() {
		return this.beSpelling;
	}

	public void setBeSpelling(Integer beSpelling) {
		this.beSpelling = beSpelling;
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

	public String getFareFinishDate() {
		return this.fareFinishDate;
	}

	public void setFareFinishDate(String fareFinishDate) {
		this.fareFinishDate = fareFinishDate;
	}

	public Integer getBeOweSpell() {
		return this.beOweSpell;
	}

	public void setBeOweSpell(Integer beOweSpell) {
		this.beOweSpell = beOweSpell;
	}

	public String getMainCode() {
		return this.mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
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

	public String getCwConfirmDate() {
		return this.cwConfirmDate;
	}

	public void setCwConfirmDate(String cwConfirmDate) {
		this.cwConfirmDate = cwConfirmDate;
	}

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getShMarkDate() {
		return this.shMarkDate;
	}

	public void setShMarkDate(String shMarkDate) {
		this.shMarkDate = shMarkDate;
	}

	public Integer getBeShMark() {
		return this.beShMark;
	}

	public void setBeShMark(Integer beShMark) {
		this.beShMark = beShMark;
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

	public String getShipperCode() {
		return this.shipperCode;
	}

	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}

	public Integer getBeWareHouse() {
		return this.beWareHouse;
	}

	public void setBeWareHouse(Integer beWareHouse) {
		this.beWareHouse = beWareHouse;
	}

	public String getShipperName() {
		return this.shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperRealCode() {
		return this.shipperRealCode;
	}

	public void setShipperRealCode(String shipperRealCode) {
		this.shipperRealCode = shipperRealCode;
	}

	public String getWareHouseDate() {
		return this.wareHouseDate;
	}

	public void setWareHouseDate(String wareHouseDate) {
		this.wareHouseDate = wareHouseDate;
	}

	public String getConsignCode() {
		return this.consignCode;
	}

	public void setConsignCode(String consignCode) {
		this.consignCode = consignCode;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
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

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
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

	public String getShipCompany() {
		return this.shipCompany;
	}

	public void setShipCompany(String shipCompany) {
		this.shipCompany = shipCompany;
	}

	public String getColoadCompany() {
		return this.coloadCompany;
	}

	public void setColoadCompany(String coloadCompany) {
		this.coloadCompany = coloadCompany;
	}

	public String getShipOrderAgent() {
		return this.shipOrderAgent;
	}

	public void setShipOrderAgent(String shipOrderAgent) {
		this.shipOrderAgent = shipOrderAgent;
	}

	public String getShipPxAgent() {
		return this.shipPxAgent;
	}

	public void setShipPxAgent(String shipPxAgent) {
		this.shipPxAgent = shipPxAgent;
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

	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
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

	public String getRouteCode() {
		return this.routeCode;
	}

	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
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

	public String getPoDesc() {
		return this.poDesc;
	}

	public void setPoDesc(String poDesc) {
		this.poDesc = poDesc;
	}

	public Double getGoodsNumber() {
		return this.goodsNumber;
	}

	public void setGoodsNumber(Double goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getGoodsDescJc() {
		return this.goodsDescJc;
	}

	public void setGoodsDescJc(String goodsDescJc) {
		this.goodsDescJc = goodsDescJc;
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

	public String getGoodsNumberDesc() {
		return this.goodsNumberDesc;
	}

	public void setGoodsNumberDesc(String goodsNumberDesc) {
		this.goodsNumberDesc = goodsNumberDesc;
	}

	public String getStationCode() {
		return this.stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getFormula() {
		return this.formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
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

	public String getGoodsSizePackage() {
		return this.goodsSizePackage;
	}

	public void setGoodsSizePackage(String goodsSizePackage) {
		this.goodsSizePackage = goodsSizePackage;
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

	public String getContainerLable() {
		return this.containerLable;
	}

	public void setContainerLable(String containerLable) {
		this.containerLable = containerLable;
	}

	public String getContainerType() {
		return this.containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public Double getTeuNum() {
		return this.teuNum;
	}

	public void setTeuNum(Double teuNum) {
		this.teuNum = teuNum;
	}

	public String getOtherContent() {
		return this.otherContent;
	}

	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}

	public String getAirPortFromDesc() {
		return this.airPortFromDesc;
	}

	public void setAirPortFromDesc(String airPortFromDesc) {
		this.airPortFromDesc = airPortFromDesc;
	}

	public String getAirPortTo() {
		return this.airPortTo;
	}

	public void setAirPortTo(String airPortTo) {
		this.airPortTo = airPortTo;
	}

	public String getAirPortToDesc() {
		return this.airPortToDesc;
	}

	public void setAirPortToDesc(String airPortToDesc) {
		this.airPortToDesc = airPortToDesc;
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

	public String getMaiTou() {
		return this.maiTou;
	}

	public void setMaiTou(String maiTou) {
		this.maiTou = maiTou;
	}

	public String getFreight() {
		return this.freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getFreightDesc() {
		return this.freightDesc;
	}

	public void setFreightDesc(String freightDesc) {
		this.freightDesc = freightDesc;
	}

	public String getPrePaid() {
		return this.prePaid;
	}

	public void setPrePaid(String prePaid) {
		this.prePaid = prePaid;
	}

	public String getCollectPaid() {
		return this.collectPaid;
	}

	public void setCollectPaid(String collectPaid) {
		this.collectPaid = collectPaid;
	}

	public String getOtherPaid() {
		return this.otherPaid;
	}

	public void setOtherPaid(String otherPaid) {
		this.otherPaid = otherPaid;
	}

	public String getAcifreight() {
		return this.acifreight;
	}

	public void setAcifreight(String acifreight) {
		this.acifreight = acifreight;
	}

	public String getPrePlace() {
		return this.prePlace;
	}

	public void setPrePlace(String prePlace) {
		this.prePlace = prePlace;
	}

	public String getCollectPlace() {
		return this.collectPlace;
	}

	public void setCollectPlace(String collectPlace) {
		this.collectPlace = collectPlace;
	}

	public String getContainerTerm() {
		return this.containerTerm;
	}

	public void setContainerTerm(String containerTerm) {
		this.containerTerm = containerTerm;
	}

	public String getMoveMent() {
		return this.moveMent;
	}

	public void setMoveMent(String moveMent) {
		this.moveMent = moveMent;
	}

	public String getUpperNumber() {
		return this.upperNumber;
	}

	public void setUpperNumber(String upperNumber) {
		this.upperNumber = upperNumber;
	}

	public String getExecDate() {
		return this.execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
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

	public Integer getOutIn() {
		return this.outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String getRelaCode() {
		return this.relaCode;
	}

	public void setRelaCode(String relaCode) {
		this.relaCode = relaCode;
	}

	public String getQddate() {
		return this.qddate;
	}

	public void setQddate(String qddate) {
		this.qddate = qddate;
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

	public String getModiOper() {
		return this.modiOper;
	}

	public void setModiOper(String modiOper) {
		this.modiOper = modiOper;
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

	public String getFlight() {
		return this.flight;
	}

	public void setFlight(String flight) {
		this.flight = flight;
	}

	public String getKmdm() {
		return this.kmdm;
	}

	public void setKmdm(String kmdm) {
		this.kmdm = kmdm;
	}

	public Double getRealDebit() {
		return this.realDebit;
	}

	public void setRealDebit(Double realDebit) {
		this.realDebit = realDebit;
	}

	public String getZcKmdm() {
		return this.zcKmdm;
	}

	public void setZcKmdm(String zcKmdm) {
		this.zcKmdm = zcKmdm;
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

	public String getShipName() {
		return this.shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
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

	public String getTo3() {
		return this.to3;
	}

	public void setTo3(String to3) {
		this.to3 = to3;
	}

	public String getShipName2() {
		return this.shipName2;
	}

	public void setShipName2(String shipName2) {
		this.shipName2 = shipName2;
	}

	public String getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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

	public String getProfitReason() {
		return this.profitReason;
	}

	public void setProfitReason(String profitReason) {
		this.profitReason = profitReason;
	}

	public String getFinishReason() {
		return this.finishReason;
	}

	public void setFinishReason(String finishReason) {
		this.finishReason = finishReason;
	}

	public Integer getOperateMark() {
		return this.operateMark;
	}

	public void setOperateMark(Integer operateMark) {
		this.operateMark = operateMark;
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

	public String getIsfref() {
		return this.isfref;
	}

	public void setIsfref(String isfref) {
		this.isfref = isfref;
	}

	public String getCarrierMbl() {
		return this.carrierMbl;
	}

	public void setCarrierMbl(String carrierMbl) {
		this.carrierMbl = carrierMbl;
	}

	public String getScaccode() {
		return this.scaccode;
	}

	public void setScaccode(String scaccode) {
		this.scaccode = scaccode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getBussinessProfit() {
		return this.bussinessProfit;
	}

	public void setBussinessProfit(Double bussinessProfit) {
		this.bussinessProfit = bussinessProfit;
	}

	public String getShipName3() {
		return this.shipName3;
	}

	public void setShipName3(String shipName3) {
		this.shipName3 = shipName3;
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

	public String getRouting() {
		return this.routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}

	public String getCommerceNo() {
		return this.commerceNo;
	}

	public void setCommerceNo(String commerceNo) {
		this.commerceNo = commerceNo;
	}

	public String getLcno() {
		return this.lcno;
	}

	public void setLcno(String lcno) {
		this.lcno = lcno;
	}

	public String getDie() {
		return this.die;
	}

	public void setDie(String die) {
		this.die = die;
	}

	public String getScn() {
		return this.scn;
	}

	public void setScn(String scn) {
		this.scn = scn;
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

	public TrustContext getMainBusiness() {
		return mainBusiness;
	}

	public void setMainBusiness(TrustContext mainBusiness) {
		this.mainBusiness = mainBusiness;
	}

	public String getPlaneOcean() {
		return planeOcean;
	}
	
	

}