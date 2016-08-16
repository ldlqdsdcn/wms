package com.delmar.sysSettings.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;

public interface SysSettingsItemValueTrlService extends CoreService<SysSettingsItemValueTrl>{

	public SysSettingsItemValueTrl saveOrUpdate(SysSettingsItemValueTrl sysSettingsItemValueTrl);

	public List<SysSettingsItemValueTrl> getItemTrlList(Integer id);
}
