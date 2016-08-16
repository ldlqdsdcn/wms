package com.delmar.rate.busmodel;

import java.util.ArrayList;
import java.util.List;

import com.delmar.rate.model.Ratemaster;

public class RateMasterBus extends Ratemaster {

  private List<RateDetailBus> rateDetails;
  
  public RateMasterBus()
  {
	  rateDetails=new ArrayList<RateDetailBus>();
  }

  public List<RateDetailBus> getRateDetails() {
		return rateDetails;
	}
	
	public void setRateDetails(List<RateDetailBus> rateDetails) {
		this.rateDetails = rateDetails;
	}
	  
  
  
}
