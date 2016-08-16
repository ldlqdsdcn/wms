package com.delmar.station.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.delmar.common.model.BusCoreModel;

public class ObjWFDetail extends BusCoreModel{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String referenceNo;

	  
	  private Integer masterID;
      /****/
      private String trustFileCode;
      /****/
      private String sizeDescription;
      /****/
      private String carLicenseNo;
      /****/
      private String carDriver;
      /****/
      private String inDate;
      /****/
      private String cargoRemark;
      /****/
      private String receiptPerson;
      /****/
      private java.math.BigDecimal totalAMount;
      /****/
      private String chargeData;
      /****/
      private String goodsDesc;
      /****/
      private String maiTou;
      /****/
      private Integer goodsNumber;
      /****/
      private String numberPackage;
      /****/
      private java.math.BigDecimal goodsWeight;
      /****/
      private java.math.BigDecimal goodsSize;
      
      /****/
      private String remark;
      /****/
      private String companyID;
      private String toBranch;
      /****/
      private String createDate;
      /****/
      private Integer zFBZ;
      /****/
      private String operator;
      /****/
      private String operatorName;
      /****/
      private String status;
      
      private String warehouseNo;
      
      private String FlightDate;
      
      private String LockDate;
      
      private java.math.BigDecimal sumGoodsWeight;
      
      private Integer sumGoodsNumber;
      
      private java.math.BigDecimal sumGoodsSize;
      
      private String masterstatus;
      
      private String nowDate;
      
      
      public java.math.BigDecimal getSumGoodsWeight() {
		return sumGoodsWeight;
	}


	public void setSumGoodsWeight(java.math.BigDecimal sumGoodsWeight) {
		this.sumGoodsWeight = sumGoodsWeight;
	}


	public Integer getSumGoodsNumber() {
		return sumGoodsNumber;
	}


	public void setSumGoodsNumber(Integer sumGoodsNumber) {
		this.sumGoodsNumber = sumGoodsNumber;
	}


	public java.math.BigDecimal getSumGoodsSize() {
		return sumGoodsSize;
	}


	public void setSumGoodsSize(java.math.BigDecimal sumGoodsSize) {
		this.sumGoodsSize = sumGoodsSize;
	}


	public java.math.BigDecimal getTotalAMount() {
		return totalAMount;
	}


	public void setTotalAMount(java.math.BigDecimal totalAMount) {
		this.totalAMount = totalAMount;
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

	public String getWarehouseNo() {
		return warehouseNo;
	}


	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}


	public String getFlightDate() {
		return FlightDate;
	}


	public void setFlightDate(String flightDate) {
		FlightDate = flightDate;
	}


	public String getLockDate() {
		return LockDate;
	}


	public void setLockDate(String lockDate) {
		LockDate = lockDate;
	}


	//
	  private ObjWarehouseForwarder objMaster;
	  
	  public ObjWFDetail() {
		  objMaster=new ObjWarehouseForwarder();
	  }


      public void setMasterID(Integer masterID)   /****/
      {
          this.masterID = masterID;
      }
      public void setTrustFileCode(String trustFileCode)   /****/
      {
          this.trustFileCode = trustFileCode;
      }
      public void setSizeDescription(String sizeDescription)   /****/
      {
          this.sizeDescription = sizeDescription;
      }
      public void setCarLicenseNo(String carLicenseNo)   /****/
      {
          this.carLicenseNo = carLicenseNo;
      }
      public void setCarDriver(String carDriver)   /****/
      {
          this.carDriver = carDriver;
      }
      public void setInDate(String inDate)   /****/
      {
          this.inDate = inDate;
      }
      public void setCargoRemark(String cargoRemark)   /****/
      {
          this.cargoRemark = cargoRemark;
      }
      public void setReceiptPerson(String receiptPerson)   /****/
      {
          this.receiptPerson = receiptPerson;
      }
      
      public void setChargeData(String chargeData)   /****/
      {
          this.chargeData = chargeData;
      }
      public void setGoodsDesc(String goodsDesc)   /****/
      {
          this.goodsDesc = goodsDesc;
      }
      public void setMaiTou(String maiTou)   /****/
      {
          this.maiTou = maiTou;
      }
      public void setGoodsNumber(Integer goodsNumber)   /****/
      {
          this.goodsNumber = goodsNumber;
      }
      public void setNumberPackage(String numberPackage)   /****/
      {
          this.numberPackage = numberPackage;
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
      public void setZFBZ(Integer zFBZ)   /****/
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

      public Integer getMasterID( )  /****/
      {
          return this.masterID;
      }
      public String getTrustFileCode( )  /****/
      {
          return this.trustFileCode;
      }
      public String getSizeDescription( )  /****/
      {
          return this.sizeDescription;
      }
      public String getCarLicenseNo( )  /****/
      {
          return this.carLicenseNo;
      }
      public String getCarDriver( )  /****/
      {
          return this.carDriver;
      }
      public String getInDate( )  /****/
      {
          return this.inDate;
      }
      public String getCargoRemark( )  /****/
      {
          return this.cargoRemark;
      }
      public String getReceiptPerson( )  /****/
      {
          return this.receiptPerson;
      }
     
      public String getChargeData( )  /****/
      {
          return this.chargeData;
      }
      public String getGoodsDesc( )  /****/
      {
          return this.goodsDesc;
      }
      public String getMaiTou( )  /****/
      {
          return this.maiTou;
      }
      public Integer getGoodsNumber( )  /****/
      {
          return this.goodsNumber;
      }
      public String getNumberPackage( )  /****/
      {
          return this.numberPackage;
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
      public Integer getZFBZ( )  /****/
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
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public ObjWarehouseForwarder getObjMaster() {
		return objMaster;
	}


	public void setObjMaster(ObjWarehouseForwarder objMaster) {
		this.objMaster = objMaster;
	}


	public String getToBranch() {
		return toBranch;
	}


	public void setToBranch(String toBranch) {
		this.toBranch = toBranch;
	}


	public String getMasterstatus() {
		return masterstatus;
	}


	public void setMasterstatus(String masterstatus) {
		this.masterstatus = masterstatus;
	}


	public String getNowDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		return sdf.format(new Date());
	}


	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

      
      
}
