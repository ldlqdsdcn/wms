package com.delmar.cargo.model;

import java.math.BigDecimal;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月25日 下午2:49:52 
 * 类说明 
 */
public class BusinessForwarder {
	
	private String companyId = "";
	private String trustFileCode = "";
	private String mainCode;
	private String mawbCode;
	private String fileNo;
	private String hawb;
	private Integer beZhiDan;
	private String zhiDanDesc;
	private Integer beSpelling;
	private String spellingDesc;
	private Integer beOweSpell;
	private String fareFinishDate;
	private String shMarkDate;
	private String cwConfirmDate;
	private String shipperCode;
	private String shipperName;
	private String mawbShipperCode;
	private String mawbShipperName;
	private String shipper;
	private String consignCode;
	private String consignName;
	private String mawbConsignCode;
	private String mawbConsignName;
	private String consignee;

	private String agentCode;
	private String agentName;
	private String informar;

	private String trustType;
	private String carrierCode;
	private String carrierName;
	private String trustSource;
	private String trustSourceName;
	private String coloadCompany;
	private String coloadCompanyName;
	private String goodsDesc;
	private BigDecimal goodsNumber;
	private String goodsPackage;
	private BigDecimal goodsSize;
	private BigDecimal goodsWeight;
	private BigDecimal goodsChargeWeight;
	private String containerType;
	private String containerLable;

	private BigDecimal teuNum;
	private BigDecimal mawbTeuNum;
	private String shipName;
	private String flight;
	private String flightDate;

	private String airPortFrom;
	private String airPortFromDesc;
	private String airPortFromCountry;
	private String airPortTo;
	private String airPortToDesc;
	private String airPortToCountry;
	private String arrivePort;
	private String arrivePortDesc;
	private String arrivePortCountry;
	private String destPlace;
	private String ArrivePortDate;
	private Integer outIn;
	private String outInDesc;

	private BigDecimal debit;

	private BigDecimal credit;

	 private BigDecimal profit;


	 private String business;

	 private String businessName;

	 private String operator;

	 private String operatorName;

	 private String operateDate;

	
 	 private String freight;

	
	 private String planeOcean;
	 private String bookingID;
	 private String csCode;
	 private String csCodeName;
	 private String csCodeEMail;
	   
	 private String DocOpCode;
	 private String DocOpName;
	 private String DocOpEMail;
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTrustFileCode() {
		return trustFileCode;
	}
	public void setTrustFileCode(String trustFileCode) {
		this.trustFileCode = trustFileCode;
	}
	public String getMainCode() {
		return mainCode;
	}
	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}
	public String getMawbCode() {
		return mawbCode;
	}
	public void setMawbCode(String mawbCode) {
		this.mawbCode = mawbCode;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getHawb() {
		return hawb;
	}
	public void setHawb(String hawb) {
		this.hawb = hawb;
	}
	public Integer getBeZhiDan() {
		return beZhiDan;
	}
	public void setBeZhiDan(Integer beZhiDan) {
		this.beZhiDan = beZhiDan;
	}
	public String getZhiDanDesc() {
		return zhiDanDesc;
	}
	public void setZhiDanDesc(String zhiDanDesc) {
		this.zhiDanDesc = zhiDanDesc;
	}
	public Integer getBeSpelling() {
		return beSpelling;
	}
	public void setBeSpelling(Integer beSpelling) {
		this.beSpelling = beSpelling;
	}
	public String getSpellingDesc() {
		return spellingDesc;
	}
	public void setSpellingDesc(String spellingDesc) {
		this.spellingDesc = spellingDesc;
	}
	public Integer getBeOweSpell() {
		return beOweSpell;
	}
	public void setBeOweSpell(Integer beOweSpell) {
		this.beOweSpell = beOweSpell;
	}
	public String getFareFinishDate() {
		return fareFinishDate;
	}
	public void setFareFinishDate(String fareFinishDate) {
		this.fareFinishDate = fareFinishDate;
	}
	public String getShMarkDate() {
		return shMarkDate;
	}
	public void setShMarkDate(String shMarkDate) {
		this.shMarkDate = shMarkDate;
	}
	public String getCwConfirmDate() {
		return cwConfirmDate;
	}
	public void setCwConfirmDate(String cwConfirmDate) {
		this.cwConfirmDate = cwConfirmDate;
	}
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getMawbShipperCode() {
		return mawbShipperCode;
	}
	public void setMawbShipperCode(String mawbShipperCode) {
		this.mawbShipperCode = mawbShipperCode;
	}
	public String getMawbShipperName() {
		return mawbShipperName;
	}
	public void setMawbShipperName(String mawbShipperName) {
		this.mawbShipperName = mawbShipperName;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getConsignCode() {
		return consignCode;
	}
	public void setConsignCode(String consignCode) {
		this.consignCode = consignCode;
	}
	public String getConsignName() {
		return consignName;
	}
	public void setConsignName(String consignName) {
		this.consignName = consignName;
	}
	public String getMawbConsignCode() {
		return mawbConsignCode;
	}
	public void setMawbConsignCode(String mawbConsignCode) {
		this.mawbConsignCode = mawbConsignCode;
	}
	public String getMawbConsignName() {
		return mawbConsignName;
	}
	public void setMawbConsignName(String mawbConsignName) {
		this.mawbConsignName = mawbConsignName;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getInformar() {
		return informar;
	}
	public void setInformar(String informar) {
		this.informar = informar;
	}
	public String getTrustType() {
		return trustType;
	}
	public void setTrustType(String trustType) {
		this.trustType = trustType;
	}
	public String getCarrierCode() {
		return carrierCode;
	}
	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}
	public String getCarrierName() {
		return carrierName;
	}
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	public String getTrustSource() {
		return trustSource;
	}
	public void setTrustSource(String trustSource) {
		this.trustSource = trustSource;
	}
	public String getTrustSourceName() {
		return trustSourceName;
	}
	public void setTrustSourceName(String trustSourceName) {
		this.trustSourceName = trustSourceName;
	}
	public String getColoadCompany() {
		return coloadCompany;
	}
	public void setColoadCompany(String coloadCompany) {
		this.coloadCompany = coloadCompany;
	}
	public String getColoadCompanyName() {
		return coloadCompanyName;
	}
	public void setColoadCompanyName(String coloadCompanyName) {
		this.coloadCompanyName = coloadCompanyName;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public BigDecimal getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(BigDecimal goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsPackage() {
		return goodsPackage;
	}
	public void setGoodsPackage(String goodsPackage) {
		this.goodsPackage = goodsPackage;
	}
	public BigDecimal getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}
	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}
	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}
	public BigDecimal getGoodsChargeWeight() {
		return goodsChargeWeight;
	}
	public void setGoodsChargeWeight(BigDecimal goodsChargeWeight) {
		this.goodsChargeWeight = goodsChargeWeight;
	}
	public String getContainerType() {
		return containerType;
	}
	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}
	public String getContainerLable() {
		return containerLable;
	}
	public void setContainerLable(String containerLable) {
		this.containerLable = containerLable;
	}
	public BigDecimal getTeuNum() {
		return teuNum;
	}
	public void setTeuNum(BigDecimal teuNum) {
		this.teuNum = teuNum;
	}
	public BigDecimal getMawbTeuNum() {
		return mawbTeuNum;
	}
	public void setMawbTeuNum(BigDecimal mawbTeuNum) {
		this.mawbTeuNum = mawbTeuNum;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getFlight() {
		return flight;
	}
	public void setFlight(String flight) {
		this.flight = flight;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getAirPortFrom() {
		return airPortFrom;
	}
	public void setAirPortFrom(String airPortFrom) {
		this.airPortFrom = airPortFrom;
	}
	public String getAirPortFromDesc() {
		return airPortFromDesc;
	}
	public void setAirPortFromDesc(String airPortFromDesc) {
		this.airPortFromDesc = airPortFromDesc;
	}
	public String getAirPortFromCountry() {
		return airPortFromCountry;
	}
	public void setAirPortFromCountry(String airPortFromCountry) {
		this.airPortFromCountry = airPortFromCountry;
	}
	public String getAirPortTo() {
		return airPortTo;
	}
	public void setAirPortTo(String airPortTo) {
		this.airPortTo = airPortTo;
	}
	public String getAirPortToDesc() {
		return airPortToDesc;
	}
	public void setAirPortToDesc(String airPortToDesc) {
		this.airPortToDesc = airPortToDesc;
	}
	public String getAirPortToCountry() {
		return airPortToCountry;
	}
	public void setAirPortToCountry(String airPortToCountry) {
		this.airPortToCountry = airPortToCountry;
	}
	public String getArrivePort() {
		return arrivePort;
	}
	public void setArrivePort(String arrivePort) {
		this.arrivePort = arrivePort;
	}
	public String getArrivePortDesc() {
		return arrivePortDesc;
	}
	public void setArrivePortDesc(String arrivePortDesc) {
		this.arrivePortDesc = arrivePortDesc;
	}
	public String getArrivePortCountry() {
		return arrivePortCountry;
	}
	public void setArrivePortCountry(String arrivePortCountry) {
		this.arrivePortCountry = arrivePortCountry;
	}
	public String getDestPlace() {
		return destPlace;
	}
	public void setDestPlace(String destPlace) {
		this.destPlace = destPlace;
	}
	public String getArrivePortDate() {
		return ArrivePortDate;
	}
	public void setArrivePortDate(String arrivePortDate) {
		ArrivePortDate = arrivePortDate;
	}
	public Integer getOutIn() {
		return outIn;
	}
	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}
	public String getOutInDesc() {
		return outInDesc;
	}
	public void setOutInDesc(String outInDesc) {
		this.outInDesc = outInDesc;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getFreight() {
		return freight;
	}
	public void setFreight(String freight) {
		this.freight = freight;
	}
	public String getPlaneOcean() {
		return planeOcean;
	}
	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}
	public String getBookingID() {
		return bookingID;
	}
	public void setBookingID(String bookingID) {
		this.bookingID = bookingID;
	}
	public String getCsCode() {
		return csCode;
	}
	public void setCsCode(String csCode) {
		this.csCode = csCode;
	}
	public String getCsCodeName() {
		return csCodeName;
	}
	public void setCsCodeName(String csCodeName) {
		this.csCodeName = csCodeName;
	}
	public String getCsCodeEMail() {
		return csCodeEMail;
	}
	public void setCsCodeEMail(String csCodeEMail) {
		this.csCodeEMail = csCodeEMail;
	}
	public String getDocOpCode() {
		return DocOpCode;
	}
	public void setDocOpCode(String docOpCode) {
		DocOpCode = docOpCode;
	}
	public String getDocOpName() {
		return DocOpName;
	}
	public void setDocOpName(String docOpName) {
		DocOpName = docOpName;
	}
	public String getDocOpEMail() {
		return DocOpEMail;
	}
	public void setDocOpEMail(String docOpEMail) {
		DocOpEMail = docOpEMail;
	}
	 
	 
	 
	 
		   
   	

}
