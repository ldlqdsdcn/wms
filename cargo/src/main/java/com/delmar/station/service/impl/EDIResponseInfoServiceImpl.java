package com.delmar.station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.station.dao.EDIResponseInfoDao;
import com.delmar.station.model.EDIResponseInfo;
import com.delmar.station.service.EDIResponseInfoService;

@Service("EDIResponseInfoService")
public class EDIResponseInfoServiceImpl implements EDIResponseInfoService {

	@Autowired
	private EDIResponseInfoDao ediResponseInfoDao;
	public EDIResponseInfo saveOrUpdate(EDIResponseInfo edirInfo) {
		
		return ediResponseInfoDao.saveOrUpdate(edirInfo);
	}
	public EDIResponseInfo getEDIRByTrustFileCode(String trustFileCode) {
		return ediResponseInfoDao.getEDIRByTrustFileCode(trustFileCode);
	}
	public void updateTrustFileInfoFCRDate(String currentDate, String trustFileCode) {
		ediResponseInfoDao.updateTrustFileInfoFCRDate(currentDate, trustFileCode);
	}

}
