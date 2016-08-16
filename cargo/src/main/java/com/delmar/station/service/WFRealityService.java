package com.delmar.station.service;

import java.util.List;

import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WFReality;

public interface WFRealityService {

	public WFReality getWFRealityByMasterId(Integer id);

	public WFReality saveOrUpdate(WFReality wfReality);

	
}
