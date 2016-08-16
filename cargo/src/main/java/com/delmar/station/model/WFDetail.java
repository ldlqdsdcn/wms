package com.delmar.station.model;

import com.delmar.core.model.CoreModel;

public class WFDetail extends CoreModel{
	  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int masterID;
      /****/
      private String trustFileCode;
      
      private String referenceNo;
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
      private double totalAMount;
      /****/
      private String chargeData;
      /****/
      private String goodsDesc;
      /****/
      private String maiTou;
      /****/
      private int goodsNumber;
      /****/
      private String numberPackage;
      /****/
      private double goodsWeight;
      /****/
      private double goodsSize;
      /****/
      private String remark;
      /****/
      private String companyID;
      private String toBranch;
      /****/
      private String createDate;
      /****/
      private int zfbz;
      /****/
      private String operator;
      /****/
      private String operatorName;
      /****/
      private String status;
      
      private String shippingSpace;
      
      private String resultMessage;
      
      //
	  private ObjWarehouseForwarder objMaster;
	  
	  public WFDetail() {
		  objMaster=new ObjWarehouseForwarder();
	  }

      public void setMasterID(int masterID)   /****/
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
      public void setTotalAMount(double totalAMount)   /****/
      {
          this.totalAMount = totalAMount;
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
      public void setGoodsNumber(int goodsNumber)   /****/
      {
          this.goodsNumber = goodsNumber;
      }
      public void setNumberPackage(String numberPackage)   /****/
      {
          this.numberPackage = numberPackage;
      }
      public void setGoodsWeight(double goodsWeight)   /****/
      {
          this.goodsWeight = goodsWeight;
      }
      public void setGoodsSize(double goodsSize)   /****/
      {
          this.goodsSize = goodsSize;
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

      public int getMasterID( )  /****/
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
      public double getTotalAMount( )  /****/
      {
          return this.totalAMount;
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
      public int getGoodsNumber( )  /****/
      {
          return this.goodsNumber;
      }
      public String getNumberPackage( )  /****/
      {
          return this.numberPackage;
      }
      public double getGoodsWeight( )  /****/
      {
          return this.goodsWeight;
      }
      public double getGoodsSize( )  /****/
      {
          return this.goodsSize;
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

	public String getShippingSpace() {
		return shippingSpace;
	}

	public void setShippingSpace(String shippingSpace) {
		this.shippingSpace = shippingSpace;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public int getZfbz() {
		return zfbz;
	}

	public void setZfbz(int zfbz) {
		this.zfbz = zfbz;
	}

      
      
}
