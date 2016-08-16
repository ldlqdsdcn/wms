package com.delmar.sysSettings.service;

import java.util.List;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.service.CoreService;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemTrl;

public interface SysSettingsItemService extends CoreService<SysSettingsItem>{

	public SysSettingsItem saveOrUpdate(SysSettingsItem sysSettingsItem);

	public void deleteSysSettingsItemList(Integer[] ids);

	public List<DatadictTrl> getInputTypeList();

	public List<DatadictTrl> getItemList();

	public List<DatadictTrl> getDefaultedList();

}
