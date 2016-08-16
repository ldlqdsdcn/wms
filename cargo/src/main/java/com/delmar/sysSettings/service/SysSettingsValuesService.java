package com.delmar.sysSettings.service;

import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettingsValues;

public interface SysSettingsValuesService extends CoreService<SysSettingsValues>{

	public SysSettingsValues saveOrUpdate(SysSettingsValues sysSettingsValues);

	
}
