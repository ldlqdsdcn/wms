package com.delmar.crm.model;

import java.math.BigDecimal;
import java.util.Date;

import com.delmar.base.model.Port;
import com.delmar.common.model.BusCoreModel;

public class SaleSheet extends BusCoreModel{
	

    private String goodsDesc;

    private String hsCode;

    private Date etd;

    private Integer beBrokage;

    private String brokageDesc;

    private Integer beTruck;

    private String truckDesc;

    private Integer shipperId;

    private String shipperName;

    private String shipperAttn;

    private String shipperTel;

    private String consignee;

    private String planeOcean;

    private Integer outIn;

    private Integer incotermId;
    private String incotermName;

    private BigDecimal goodsWeight;

    private BigDecimal goodsSize;

    private String containerDesc;

    private Integer carrierId;

    private Integer polId;
    private Port pol;

    private Integer poaId;
    private Port poa;

    private String remark;

    private Integer status;


	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public Integer getBeBrokage() {
		return beBrokage;
	}

	public void setBeBrokage(Integer beBrokage) {
		this.beBrokage = beBrokage;
	}

	public String getBrokageDesc() {
		return brokageDesc;
	}

	public void setBrokageDesc(String brokageDesc) {
		this.brokageDesc = brokageDesc;
	}

	public Integer getBeTruck() {
		return beTruck;
	}

	public void setBeTruck(Integer beTruck) {
		this.beTruck = beTruck;
	}

	public String getTruckDesc() {
		return truckDesc;
	}

	public void setTruckDesc(String truckDesc) {
		this.truckDesc = truckDesc;
	}

	public Integer getShipperId() {
		return shipperId;
	}

	public void setShipperId(Integer shipperId) {
		this.shipperId = shipperId;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperAttn() {
		return shipperAttn;
	}

	public void setShipperAttn(String shipperAttn) {
		this.shipperAttn = shipperAttn;
	}

	public String getShipperTel() {
		return shipperTel;
	}

	public void setShipperTel(String shipperTel) {
		this.shipperTel = shipperTel;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getPlaneOcean() {
		return planeOcean;
	}

	public void setPlaneOcean(String planeOcean) {
		this.planeOcean = planeOcean;
	}

	public Integer getOutIn() {
		return outIn;
	}

	public void setOutIn(Integer outIn) {
		this.outIn = outIn;
	}

	public Integer getIncotermId() {
		return incotermId;
	}

	public void setIncotermId(Integer incotermId) {
		this.incotermId = incotermId;
	}

	public String getIncotermName() {
		return incotermName;
	}

	public void setIncotermName(String incotermName) {
		this.incotermName = incotermName;
	}

	public BigDecimal getGoodsWeight() {
		return goodsWeight;
	}

	public void setGoodsWeight(BigDecimal goodsWeight) {
		this.goodsWeight = goodsWeight;
	}

	public BigDecimal getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(BigDecimal goodsSize) {
		this.goodsSize = goodsSize;
	}

	public String getContainerDesc() {
		return containerDesc;
	}

	public void setContainerDesc(String containerDesc) {
		this.containerDesc = containerDesc;
	}

	public Integer getCarrierId() {
		return carrierId;
	}

	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	public Integer getPolId() {
		return polId;
	}

	public void setPolId(Integer polId) {
		this.polId = polId;
	}

	public Port getPol() {
		return pol;
	}

	public void setPol(Port pol) {
		this.pol = pol;
	}

	public Integer getPoaId() {
		return poaId;
	}

	public void setPoaId(Integer poaId) {
		this.poaId = poaId;
	}

	public Port getPoa() {
		return poa;
	}

	public void setPoa(Port poa) {
		this.poa = poa;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    

}