package com.delmar.station.model;

import com.delmar.core.model.CoreModel;

public class WFReality extends CoreModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5074841123607742514L;
	
	private String trustFileCode;
    /****/
    private String goodsDesc;
    /****/
    private String maiTou;
    /****/
    private int goodsNumber = 0;
    /****/
    private String numberPackage;
    /****/
    private double goodsWeight = 0;
    /****/
    private double goodsSize = 0;
    /****/
    private String remark;
    /****/
    private String companyID;
    /****/
    private String createDate;
    /****/
    private int zfbz;
    /****/
    private String operator;
    /****/
    private String operatorName;
    private Integer masterID;

    public void setTrustFileCode(String trustFileCode)   /****/
    {
        this.trustFileCode = trustFileCode;
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

    public String getTrustFileCode( )  /****/
    {
        return this.trustFileCode;
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
	public Integer getMasterID() {
		return masterID;
	}
	public void setMasterID(Integer masterID) {
		this.masterID = masterID;
	}
	public int getZfbz() {
		return zfbz;
	}
	public void setZfbz(int zfbz) {
		this.zfbz = zfbz;
	}
}
