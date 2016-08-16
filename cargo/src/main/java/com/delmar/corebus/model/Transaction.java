package com.delmar.corebus.model;

import java.math.BigDecimal;

import com.delmar.common.model.BusCoreModel;
import com.delmar.rate.model.Ratemaster;

/**
 * @author 刘大磊 2015年3月23日 上午10:48:28
 */
public class Transaction extends BusCoreModel {
    private String transno;

    private Integer rRateMasterId;
    
    private Ratemaster ratemaster;
    
    private Integer providerId;
    
    private BigDecimal totalAmount;
    
    
    public String getTransno() {
        return transno;
    }

    public void setTransno(String transno) {
        this.transno = transno == null ? null : transno.trim();
    }


    public Integer getrRateMasterId() {
        return rRateMasterId;
    }

    public void setrRateMasterId(Integer rRateMasterId) {
        this.rRateMasterId = rRateMasterId;
    }

	/**
	 * @return the ratemaster
	 */
	public Ratemaster getRatemaster() {
		return ratemaster;
	}

	/**
	 * @param ratemaster the ratemaster to set
	 */
	public void setRatemaster(Ratemaster ratemaster) {
		this.ratemaster = ratemaster;
	}


	/**
	 * @return the providerId
	 */
	public Integer getProviderId() {
		return providerId;
	}

	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	/**
	 * @return the totalAmount
	 */
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

    
}