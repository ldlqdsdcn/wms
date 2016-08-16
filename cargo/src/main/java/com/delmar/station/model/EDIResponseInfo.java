package com.delmar.station.model;

import com.delmar.core.model.CoreModel;

public class EDIResponseInfo extends CoreModel{
    private String warehouseID;
    private String trustFileCode;
    private String carrier;
    private String ediType;
    private String version;
    private String ediAction;
    private String csReferenceNo;
    private String ediStatus;
    private String batchNo;
    private String responseDesc;
    private int isSend;
    private String companyID;
    private String operator;
    private String operatorName;
    private String createDate;
    private String updateDate;
    private String ackDate;
    private String bookingNumber;
    private int beUse;
    private String inDate;

	public String getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(String warehouseID) {
		this.warehouseID = warehouseID;
	}

	public String getTrustFileCode() {
		return trustFileCode;
	}

	public void setTrustFileCode(String trustFileCode) {
		this.trustFileCode = trustFileCode;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}


	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getResponseDesc() {
		return responseDesc;
	}

	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}

	public int getIsSend() {
		return isSend;
	}

	public void setIsSend(int isSend) {
		this.isSend = isSend;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getAckDate() {
		return ackDate;
	}

	public void setAckDate(String ackDate) {
		this.ackDate = ackDate;
	}

	public String getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public int getBeUse() {
		return beUse;
	}

	public void setBeUse(int beUse) {
		this.beUse = beUse;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getEdiType() {
		return ediType;
	}

	public void setEdiType(String ediType) {
		this.ediType = ediType;
	}

	public String getEdiAction() {
		return ediAction;
	}

	public void setEdiAction(String ediAction) {
		this.ediAction = ediAction;
	}

	public String getCsReferenceNo() {
		return csReferenceNo;
	}

	public void setCsReferenceNo(String csReferenceNo) {
		this.csReferenceNo = csReferenceNo;
	}

	public String getEdiStatus() {
		return ediStatus;
	}

	public void setEdiStatus(String ediStatus) {
		this.ediStatus = ediStatus;
	}
    
	
    
}
