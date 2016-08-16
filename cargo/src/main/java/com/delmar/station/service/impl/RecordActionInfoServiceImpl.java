package com.delmar.station.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.station.dao.RecordActionInfoDao;
import com.delmar.station.model.RecordActionInfo;
import com.delmar.station.service.RecordActionInfoService;

@Service("RecordActionInfoService")
public class RecordActionInfoServiceImpl implements RecordActionInfoService{

	@Autowired
	private RecordActionInfoDao recordActionInfoDao;
	
	public RecordActionInfo saveOrUpdate(RecordActionInfo inf) {
		return recordActionInfoDao.saveOrUpdate(inf);
	}

}
