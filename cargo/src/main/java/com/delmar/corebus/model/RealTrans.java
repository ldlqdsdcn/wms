package com.delmar.corebus.model;

import java.math.BigDecimal;
import java.util.Date;

import com.delmar.common.model.BusCoreModel;

public class RealTrans  extends BusCoreModel{
    
    private Integer bEbusinessId;

    private String businessno;

    private String hawbbillno;

    private String masterbillno;

    private Date flightdate;

    private String flight;

    private String reciplace;

    private Integer pol;

    private Integer pod;

    private Integer poa;

    private String destplace;

    private String carriername;

    private String carriercontact;

    private Integer goodsnumber;

    private BigDecimal goodsweight;

    private BigDecimal goodssize;

    private Date eta;

    private String remark;


    public Integer getbEbusinessId() {
        return bEbusinessId;
    }

    public void setbEbusinessId(Integer bEbusinessId) {
        this.bEbusinessId = bEbusinessId;
    }

    public String getBusinessno() {
        return businessno;
    }

    public void setBusinessno(String businessno) {
        this.businessno = businessno == null ? null : businessno.trim();
    }

    public String getHawbbillno() {
        return hawbbillno;
    }

    public void setHawbbillno(String hawbbillno) {
        this.hawbbillno = hawbbillno == null ? null : hawbbillno.trim();
    }

    public String getMasterbillno() {
        return masterbillno;
    }

    public void setMasterbillno(String masterbillno) {
        this.masterbillno = masterbillno == null ? null : masterbillno.trim();
    }

    public Date getFlightdate() {
        return flightdate;
    }

    public void setFlightdate(Date flightdate) {
        this.flightdate = flightdate;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight == null ? null : flight.trim();
    }

    public String getReciplace() {
        return reciplace;
    }

    public void setReciplace(String reciplace) {
        this.reciplace = reciplace == null ? null : reciplace.trim();
    }

    public Integer getPol() {
        return pol;
    }

    public void setPol(Integer pol) {
        this.pol = pol;
    }

    public Integer getPod() {
        return pod;
    }

    public void setPod(Integer pod) {
        this.pod = pod;
    }

    public Integer getPoa() {
        return poa;
    }

    public void setPoa(Integer poa) {
        this.poa = poa;
    }

    public String getDestplace() {
        return destplace;
    }

    public void setDestplace(String destplace) {
        this.destplace = destplace == null ? null : destplace.trim();
    }

    public String getCarriername() {
        return carriername;
    }

    public void setCarriername(String carriername) {
        this.carriername = carriername == null ? null : carriername.trim();
    }

    public String getCarriercontact() {
        return carriercontact;
    }

    public void setCarriercontact(String carriercontact) {
        this.carriercontact = carriercontact == null ? null : carriercontact.trim();
    }

    public Integer getGoodsnumber() {
        return goodsnumber;
    }

    public void setGoodsnumber(Integer goodsnumber) {
        this.goodsnumber = goodsnumber;
    }

    public BigDecimal getGoodsweight() {
        return goodsweight;
    }

    public void setGoodsweight(BigDecimal goodsweight) {
        this.goodsweight = goodsweight;
    }

    public BigDecimal getGoodssize() {
        return goodssize;
    }

    public void setGoodssize(BigDecimal goodssize) {
        this.goodssize = goodssize;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


}