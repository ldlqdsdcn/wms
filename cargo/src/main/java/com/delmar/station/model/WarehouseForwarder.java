package com.delmar.station.model;

import com.delmar.common.model.BusCoreModel;

/** 
 * @author Jack Zhang zhangja@delmarchina.com
 * @version V1.0 2015年7月9日 上午17:56:52 
 * 进仓单业务实体
 */
public class WarehouseForwarder extends BusCoreModel implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

    private String planeOcean;
    private String trustFileCode;
    private String referenceNo;
    private String warehouseNo;
    private String warehouseName;
    private String toWarehouse;
    private String voyageData;
    private String flightDate;
    private String airPortFrom;
    private String airPortTo;
    private String wAddress;
    private String wTele;
    private String wFax;
    private String cargoCusName;
    private String wRelation;
    private String wMailAddress;
    private String cargoCusCode;
    private String cAddress;
    private String cTele;
    private String cFax;
    private String cRelation;
    private String cargoCutOffDesc;
    private String documentCutOffDesc;
    private String fRelation;
    private String chargeData;
    private String cargoCutoffDate;
    private String goodsDesc;
    private String maiTou;
    private int goodsNumber;
    private String numberPackage;
    private double goodsWeight;
    private double goodsSize;
    private String documentCutOffDate;
    private Integer id;
    private String remark;
    private String companyID;
    private String createDate;
    private int zfbz;
    private String operator;
    private String operatorName;
    private String operatorEMail;
    private String status;
    private String lockDate;
    private String gdNo;
    private WFReality wfReality;
    
    private String inDate;
    
    private int goodsNumberTo;
    private double goodsWeightTo;
    private double goodsSizeTo;
    
   
    public WarehouseForwarder() {
    	
    	setWfReality(new WFReality());
    }

	public String getPlaneOcean() {
		return planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public String getTrustFileCode() {
		return trustFileCode;
	}

	public void setTrustFileCode(String trustFileCode) {
		this.trustFileCode = trustFileCode;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getToWarehouse() {
		return toWarehouse;
	}

	public void setToWarehouse(String toWarehouse) {
		this.toWarehouse = toWarehouse;
	}

	public String getVoyageData() {
		return voyageData;
	}

	public void setVoyageData(String voyageData) {
		this.voyageData = voyageData;
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

	public String getAirPortTo() {
		return airPortTo;
	}

	public void setAirPortTo(String airPortTo) {
		this.airPortTo = airPortTo;
	}

	public String getwAddress() {
		return wAddress;
	}

	public void setwAddress(String wAddress) {
		this.wAddress = wAddress;
	}

	public String getwTele() {
		return wTele;
	}

	public void setwTele(String wTele) {
		this.wTele = wTele;
	}

	public String getwFax() {
		return wFax;
	}

	public void setwFax(String wFax) {
		this.wFax = wFax;
	}

	public String getCargoCusName() {
		return cargoCusName;
	}

	public void setCargoCusName(String cargoCusName) {
		this.cargoCusName = cargoCusName;
	}

	public String getwRelation() {
		return wRelation;
	}

	public void setwRelation(String wRelation) {
		this.wRelation = wRelation;
	}

	public String getwMailAddress() {
		return wMailAddress;
	}

	public void setwMailAddress(String wMailAddress) {
		this.wMailAddress = wMailAddress;
	}

	public String getCargoCusCode() {
		return cargoCusCode;
	}

	public void setCargoCusCode(String cargoCusCode) {
		this.cargoCusCode = cargoCusCode;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcTele() {
		return cTele;
	}

	public void setcTele(String cTele) {
		this.cTele = cTele;
	}

	public String getcFax() {
		return cFax;
	}

	public void setcFax(String cFax) {
		this.cFax = cFax;
	}

	public String getcRelation() {
		return cRelation;
	}

	public void setcRelation(String cRelation) {
		this.cRelation = cRelation;
	}

	public String getCargoCutOffDesc() {
		return cargoCutOffDesc;
	}

	public void setCargoCutOffDesc(String cargoCutOffDesc) {
		this.cargoCutOffDesc = cargoCutOffDesc;
	}

	public String getDocumentCutOffDesc() {
		return documentCutOffDesc;
	}

	public void setDocumentCutOffDesc(String documentCutOffDesc) {
		this.documentCutOffDesc = documentCutOffDesc;
	}

	public String getfRelation() {
		return fRelation;
	}

	public void setfRelation(String fRelation) {
		this.fRelation = fRelation;
	}

	public String getChargeData() {
		return chargeData;
	}

	public void setChargeData(String chargeData) {
		this.chargeData = chargeData;
	}

	public String getCargoCutoffDate() {
		return cargoCutoffDate;
	}

	public void setCargoCutoffDate(String cargoCutoffDate) {
		this.cargoCutoffDate = cargoCutoffDate;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getMaiTou() {
		return maiTou;
	}

	public void setMaiTou(String maiTou) {
		this.maiTou = maiTou;
	}

	public int getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public String getNumberPackage() {
		return numberPackage;
	}

	public void setNumberPackage(String numberPackage) {
		this.numberPackage = numberPackage;
	}

	public double getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(double goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public double getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(double goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getDocumentCutOffDate() {
		return documentCutOffDate;
	}

	public void setDocumentCutOffDate(String documentCutOffDate) {
		this.documentCutOffDate = documentCutOffDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getOperatorEMail() {
		return operatorEMail;
	}

	public void setOperatorEMail(String operatorEMail) {
		this.operatorEMail = operatorEMail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLockDate() {
		return lockDate;
	}

	public void setLockDate(String lockDate) {
		this.lockDate = lockDate;
	}

	public String getGdNo() {
		return gdNo;
	}

	public void setGdNo(String gdNo) {
		this.gdNo = gdNo;
	}

	public WFReality getWfReality() {
		return wfReality;
	}

	public void setWfReality(WFReality wfReality) {
		this.wfReality = wfReality;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public int getGoodsNumberTo() {
		return goodsNumberTo;
	}

	public void setGoodsNumberTo(int goodsNumberTo) {
		this.goodsNumberTo = goodsNumberTo;
	}

	public double getGoodsWeightTo() {
		return goodsWeightTo;
	}

	public void setGoodsWeightTo(double goodsWeightTo) {
		this.goodsWeightTo = goodsWeightTo;
	}

	public double getGoodsSizeTo() {
		return goodsSizeTo;
	}

	public void setGoodsSizeTo(double goodsSizeTo) {
		this.goodsSizeTo = goodsSizeTo;
	}

	public int getZfbz() {
		return zfbz;
	}

	public void setZfbz(int zfbz) {
		this.zfbz = zfbz;
	}
   
}
