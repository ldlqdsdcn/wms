package com.delmar.base.model;

import java.math.BigDecimal;

import com.delmar.core.model.CoreModel;

/**
 * @author 刘大磊 2015年3月24日 上午10:58:54
 */
public class ChargenameExtra extends CoreModel{

	private BigDecimal limitweight;
	private BigDecimal limitweightmin;

    private BigDecimal limitsize;

    private BigDecimal limitlength;

    private BigDecimal limitwidth;

    private BigDecimal limitheight;
    private BigDecimal weightsizemin;
    private BigDecimal weightsizemax;
    private String extraType;

    private String remark;

    private Integer baseChargenameId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getBaseChargenameId() {
        return baseChargenameId;
    }

    public void setBaseChargenameId(Integer baseChargenameId) {
        this.baseChargenameId = baseChargenameId;
    }

	public BigDecimal getLimitweightmin() {
		return limitweightmin;
	}

	public void setLimitweightmin(BigDecimal limitweightmin) {
		this.limitweightmin = limitweightmin;
	}

	public BigDecimal getWeightsizemin() {
		return weightsizemin;
	}

	public void setWeightsizemin(BigDecimal weightsizemin) {
		this.weightsizemin = weightsizemin;
	}

	public BigDecimal getWeightsizemax() {
		return weightsizemax;
	}

	public void setWeightsizemax(BigDecimal weightsizemax) {
		this.weightsizemax = weightsizemax;
	}

	public String getExtraType() {
		return extraType;
	}

	public void setExtraType(String extraType) {
		this.extraType = extraType;
	}
    
    
}
