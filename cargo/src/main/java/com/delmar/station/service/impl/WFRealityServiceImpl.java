package com.delmar.station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.station.dao.WFRealityDao;
import com.delmar.station.model.WFReality;
import com.delmar.station.service.WFRealityService;

@Service("WFRealityService")
public class WFRealityServiceImpl implements WFRealityService{

	@Autowired
	WFRealityDao wfRealityDao;
	public WFReality getWFRealityByMasterId(Integer masterId) {
		return wfRealityDao.getWFRealityByMasterId(masterId);
	}
	public WFReality saveOrUpdate(WFReality wfReality) {
		return wfRealityDao.saveOrUpdate(wfReality);
	}

}
