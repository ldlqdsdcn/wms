package com.delmar.sysSettings.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettingsItemTrl;

public interface SysSettingsItemTrlService extends CoreService<SysSettingsItemTrl>{

	public SysSettingsItemTrl saveOrUpdate(SysSettingsItemTrl sysSettingsItemTrl);
	
	public List<SysSettingsItemTrl> getItemTrlList(Integer itemId);
}
