package com.delmar.station.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.delmar.common.model.BusCoreModel;
import com.delmar.utils.ProDateUtil;
import com.delmar.utils.StringUtil;


public class ObjWarehouseForwarder extends BusCoreModel{
	/****/
    private String planeOcean;
    /****/
    private String trustFileCode;
    /****/
    private String referenceNo;
    /****/
    private String warehouseNo;
    /****/
    private String warehouseName;
    /****/
    private String toWarehouse;
    /****/
    private String voyageData;
    /****/
    private String flightDate;
    /****/
    private String airPortFrom;
    /****/
    private String airPortTo;
    /****/
    private String wAddress;
    /****/
    private String wTele;
    /****/
    private String wFax;
    /****/
    private String cargoCusName;
    /****/
    private String wRelation;
    private String wMailAddress;
    /****/
    private String cargoCusCode;
    /****/
    private String cAddress;
    /****/
    private String cTele;
    /****/
    private String cFax;
    /****/
    private String cRelation;
    /****/
    private String cargoCutOffDesc;
    /****/
    private String documentCutOffDesc;
    /****/
    private String fRelation;
    /****/
    private String chargeData;
    /****/
    private String cargoCutoffDate;
    /****/
    private String goodsDesc;
    /****/
    private String maiTou;
    /****/
    private int goodsNumber;
    /****/
    private String numberPackage;
    /****/
    private java.math.BigDecimal goodsWeight;
    /****/
    private java.math.BigDecimal goodsSize;
    /****/
    private String documentCutOffDate;
    /****/
    /****/
    private String remark;
    /****/
    private String companyID;
    /****/
    private String createDate;
    /****/
    private int zFBZ;
    /****/
    private String operator;
    /****/
    private String operatorName;
    private String operatorEMail;
    /****/
    private String status;

    private String lockDate;
    
    private String gdNo;
    private String nowDate;
   
    public String getFlightDateStart() {
		return flightDateStart;
	}

	public void setFlightDateStart(String flightDateStart) {
		this.flightDateStart = flightDateStart;
	}

	public String getFlightDateEnd() {
		return flightDateEnd;
	}

	public void setFlightDateEnd(String flightDateEnd) {
		this.flightDateEnd = flightDateEnd;
	}

	private String flightDateStart;
    
    private String flightDateEnd;
    
    private ObjWFReality wfReality;
 
	private Integer realID;      
    
    public Integer getRealID() {
		return realID;
	}

	public void setRealID(Integer realID) {
		this.realID = realID;
	}

	private Integer realGoodsNumber;

    private java.math.BigDecimal realGoodsWeight;
    
    private java.math.BigDecimal realGoodsSize;   
    
    
    public ObjWarehouseForwarder() {
    	
    	wfReality=new ObjWFReality();
    	
    }
    
    public void setPlaneOcean(String planeOcean)   /****/
    {
        this.planeOcean = planeOcean;
    }
    public void setTrustFileCode(String trustFileCode)   /****/
    {
        this.trustFileCode = trustFileCode;
    }
    public void setReferenceNo(String referenceNo)   /****/
    {
        this.referenceNo = referenceNo;
    }
    public void setWarehouseNo(String warehouseNo)   /****/
    {
        this.warehouseNo = warehouseNo;
    }
    public void setWarehouseName(String warehouseName)   /****/
    {
        this.warehouseName = warehouseName;
    }
    public void setToWarehouse(String toWarehouse)   /****/
    {
        this.toWarehouse = toWarehouse;
    }
    public void setVoyageData(String voyageData)   /****/
    {
        this.voyageData = voyageData;
    }
    public void setFlightDate(String flightDate)   /****/
    {
        this.flightDate = flightDate;
    }
    public void setAirPortFrom(String airPortFrom)   /****/
    {
        this.airPortFrom = airPortFrom;
    }
    public void setAirPortTo(String airPortTo)   /****/
    {
        this.airPortTo = airPortTo;
    }
    public void setWAddress(String wAddress)   /****/
    {
        this.wAddress = wAddress;
    }
    public void setWTele(String wTele)   /****/
    {
        this.wTele = wTele;
    }
    public void setWFax(String wFax)   /****/
    {
        this.wFax = wFax;
    }
    public void setCargoCusName(String cargoCusName)   /****/
    {
        this.cargoCusName = cargoCusName;
    }
    public void setWRelation(String wRelation)   /****/
    {
        this.wRelation = wRelation;
    }
    public void setCargoCusCode(String cargoCusCode)   /****/
    {
        this.cargoCusCode = cargoCusCode;
    }
    public void setCAddress(String cAddress)   /****/
    {
        this.cAddress = cAddress;
    }
    public void setCTele(String cTele)   /****/
    {
        this.cTele = cTele;
    }
    public void setCFax(String cFax)   /****/
    {
        this.cFax = cFax;
    }
    public void setCRelation(String cRelation)   /****/
    {
        this.cRelation = cRelation;
    }
    public void setCargoCutOffDesc(String cargoCutOffDesc)   /****/
    {
        this.cargoCutOffDesc = cargoCutOffDesc;
    }
    public void setDocumentCutOffDesc(String documentCutOffDesc)   /****/
    {
        this.documentCutOffDesc = documentCutOffDesc;
    }
    public void setFRelation(String fRelation)   /****/
    {
        this.fRelation = fRelation;
    }
    public void setChargeData(String chargeData)   /****/
    {
        this.chargeData = chargeData;
    }
    public void setCargoCutoffDate(String cargoCutoffDate)   /****/
    {
        this.cargoCutoffDate = cargoCutoffDate;
    }
    public void setGoodsDesc(String goodsDesc)   /****/
    {
        this.goodsDesc = goodsDesc;
    }
    public void setMaiTou(String maiTou)   /****/
    {
        this.maiTou = maiTou;
    }
    public void setGoodsNumber(int goodsNumber)   /****/
    {
        this.goodsNumber = goodsNumber;
    }
    public void setNumberPackage(String numberPackage)   /****/
    {
        this.numberPackage = numberPackage;
    }
    public void setDocumentCutOffDate(String documentCutOffDate)   /****/
    {
        this.documentCutOffDate = documentCutOffDate;
    }
   
    public void setRemark(String remark)   /****/
    {
        this.remark = remark;
    }
    public void setCompanyID(String companyID)   /****/
    {
        this.companyID = companyID;
    }
    public void setCreateDate(String createDate)   /****/
    {
        this.createDate = createDate;
    }
    public void setZFBZ(int zFBZ)   /****/
    {
        this.zFBZ = zFBZ;
    }
    public void setOperator(String operator)   /****/
    {
        this.operator = operator;
    }
    public void setOperatorName(String operatorName)   /****/
    {
        this.operatorName = operatorName;
    }
    public void setStatus(String status)   /****/
    {
        this.status = status;
    }

    /** ����Ϊgetter����  **/

    public String getPlaneOcean( )  /****/
    {
        return this.planeOcean;
    }
    public String getTrustFileCode( )  /****/
    {
        return this.trustFileCode;
    }
    public String getReferenceNo( )  /****/
    {
        return this.referenceNo;
    }
    public String getWarehouseNo( )  /****/
    {
        return this.warehouseNo;
    }
    public String getWarehouseName( )  /****/
    {
        return this.warehouseName;
    }
    public String getToWarehouse( )  /****/
    {
        return this.toWarehouse;
    }
    public String getVoyageData( )  /****/
    {
        return this.voyageData;
    }
    public String getFlightDate( )  /****/
    {
        return this.flightDate;
    }
    public String getAirPortFrom( )  /****/
    {
        return this.airPortFrom;
    }
    public String getAirPortTo( )  /****/
    {
        return this.airPortTo;
    }
    public String getWAddress( )  /****/
    {
        return this.wAddress;
    }
    public String getWTele( )  /****/
    {
        return this.wTele;
    }
    public String getWFax( )  /****/
    {
        return this.wFax;
    }
    public String getCargoCusName( )  /****/
    {
        return this.cargoCusName;
    }
    public String getWRelation( )  /****/
    {
        return this.wRelation;
    }
    public String getCargoCusCode( )  /****/
    {
        return this.cargoCusCode;
    }
    public String getCAddress( )  /****/
    {
        return this.cAddress;
    }
    public String getCTele( )  /****/
    {
        return this.cTele;
    }
    public String getCFax( )  /****/
    {
        return this.cFax;
    }
    public String getCRelation( )  /****/
    {
        return this.cRelation;
    }
    public String getCargoCutOffDesc( )  /****/
    {
        return this.cargoCutOffDesc;
    }
    public String getDocumentCutOffDesc( )  /****/
    {
        return this.documentCutOffDesc;
    }
    public String getFRelation( )  /****/
    {
        return this.fRelation;
    }
    public String getChargeData( )  /****/
    {
        return this.chargeData;
    }
    public String getCargoCutoffDate( )  /****/
    {
        return this.cargoCutoffDate;
    }
    public String getGoodsDesc( )  /****/
    {
        return this.goodsDesc;
    }
    public String getMaiTou( )  /****/
    {
        return this.maiTou;
    }
    public int getGoodsNumber( )  /****/
    {
        return this.goodsNumber;
    }
    public String getNumberPackage( )  /****/
    {
        return this.numberPackage;
    }
    public String getDocumentCutOffDate( )  /****/
    {
        return this.documentCutOffDate;
    }
    
    public String getRemark( )  /****/
    {
        return this.remark;
    }
    public String getCompanyID( )  /****/
    {
        return this.companyID;
    }
    public String getCreateDate( )  /****/
    {
        return this.createDate;
    }
    public int getZFBZ( )  /****/
    {
        return this.zFBZ;
    }
    public String getOperator( )  /****/
    {
        return this.operator;
    }
    public String getOperatorName( )  /****/
    {
        return this.operatorName;
    }
    public String getStatus( )  /****/
    {
        return this.status;
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

	public ObjWFReality getWfReality() {
		return wfReality;
	}

	public void setWfReality(ObjWFReality wfReality) {
		this.wfReality = wfReality;
	}

	public String getOperatorEMail() {
		return operatorEMail;
	}

	public void setOperatorEMail(String operatorEMail) {
		this.operatorEMail = operatorEMail;
	}

	public String getWMailAddress() {
		return wMailAddress;
	}

	public void setWMailAddress(String wMailAddress) {
		this.wMailAddress = wMailAddress;
	}

	public Integer getRealGoodsNumber() {
		return realGoodsNumber;
	}

	public void setRealGoodsNumber(Integer realGoodsNumber) {
		this.realGoodsNumber = realGoodsNumber;
	}

	public java.math.BigDecimal getRealGoodsWeight() {
		return realGoodsWeight;
	}

	public void setRealGoodsWeight(java.math.BigDecimal realGoodsWeight) {
		this.realGoodsWeight = realGoodsWeight;
	}

	public java.math.BigDecimal getRealGoodsSize() {
		return realGoodsSize;
	}

	public void setRealGoodsSize(java.math.BigDecimal realGoodsSize) {
		this.realGoodsSize = realGoodsSize;
	}

	public java.math.BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(java.math.BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public java.math.BigDecimal getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(java.math.BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getNowDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(new Date());
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	
}
