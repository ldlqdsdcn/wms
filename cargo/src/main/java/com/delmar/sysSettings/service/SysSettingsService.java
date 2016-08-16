package com.delmar.sysSettings.service;

import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettings;

public interface SysSettingsService extends CoreService<SysSettings>{

	public SysSettings saveOrUpdate(SysSettings sysSettings);

	public void deleteSysSettingsList(Integer[] ids);
}
