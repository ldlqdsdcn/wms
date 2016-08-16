package com.delmar.sysSettings.service;

import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettingsItemValue;

public interface SysSettingsItemValueService extends CoreService<SysSettingsItemValue>{

	public SysSettingsItemValue saveOrUpdate(SysSettingsItemValue sysSettingsItemValue);

	public void deleteSysSettingsItemValueList(Integer[] ids);

	public String getItemValueName(SysSettingsItemValue sysSettingsItemValue, String language);
}
