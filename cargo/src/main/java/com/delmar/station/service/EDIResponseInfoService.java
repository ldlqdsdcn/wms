package com.delmar.station.service;

import com.delmar.station.model.EDIResponseInfo;

public interface EDIResponseInfoService {

	public EDIResponseInfo saveOrUpdate(EDIResponseInfo edirInfo);

	public EDIResponseInfo getEDIRByTrustFileCode(String trustFileCode);

	public void updateTrustFileInfoFCRDate(String currentDate,
			String trustFileCode);

}
