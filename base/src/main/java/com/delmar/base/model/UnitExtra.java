package com.delmar.base.model;

import java.math.BigDecimal;

import com.delmar.core.model.CoreModel;

public class UnitExtra  extends CoreModel{

    private Integer baseUnitId;

    private BigDecimal limitweight;

    private BigDecimal limitsize;

    private BigDecimal limitlength;

    private BigDecimal limitwidth;

    private BigDecimal limitheight;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBaseUnitId() {
        return baseUnitId;
    }

    public void setBaseUnitId(Integer baseUnitId) {
        this.baseUnitId = baseUnitId;
    }

    public BigDecimal getLimitweight() {
        return limitweight;
    }

    public void setLimitweight(BigDecimal limitweight) {
        this.limitweight = limitweight;
    }

    public BigDecimal getLimitsize() {
        return limitsize;
    }

    public void setLimitsize(BigDecimal limitsize) {
        this.limitsize = limitsize;
    }

    public BigDecimal getLimitlength() {
        return limitlength;
    }

    public void setLimitlength(BigDecimal limitlength) {
        this.limitlength = limitlength;
    }

    public BigDecimal getLimitwidth() {
        return limitwidth;
    }

    public void setLimitwidth(BigDecimal limitwidth) {
        this.limitwidth = limitwidth;
    }

    public BigDecimal getLimitheight() {
        return limitheight;
    }

    public void setLimitheight(BigDecimal limitheight) {
        this.limitheight = limitheight;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}