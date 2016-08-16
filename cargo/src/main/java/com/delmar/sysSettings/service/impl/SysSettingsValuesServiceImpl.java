package com.delmar.sysSettings.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sysSettings.dao.SysSettingsValuesDao;
import com.delmar.sysSettings.model.SysSettingsValues;
import com.delmar.sysSettings.service.SysSettingsValuesService;

@Service("SysSettingsValuesService")
public class SysSettingsValuesServiceImpl extends CoreServiceImpl<SysSettingsValues> implements SysSettingsValuesService{

	@Autowired
	SysSettingsValuesDao sysSettingsValuesDao;

	/**
	 * 保存或更新.
	 */
	public SysSettingsValues saveOrUpdate(SysSettingsValues sysSettingsValues) {
		
		if (sysSettingsValues.getId() == null) {
			sysSettingsValuesDao.insert(sysSettingsValues);
    	} else {
    		sysSettingsValuesDao.update(sysSettingsValues);
    	}
		
		return sysSettingsValues;
	}

	@Override
	protected CoreDao<SysSettingsValues> getCoreDao() {
		return sysSettingsValuesDao;
	}

}
