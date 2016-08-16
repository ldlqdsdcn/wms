package com.delmar.sysSettings.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sysSettings.dao.SysSettingsDao;
import com.delmar.sysSettings.model.SysSettings;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsValues;
import com.delmar.sysSettings.service.SysSettingsItemService;
import com.delmar.sysSettings.service.SysSettingsService;
import com.delmar.sysSettings.service.SysSettingsValuesService;

@Service("SysSettingsService")
public class SysSettingsServiceImpl extends CoreServiceImpl<SysSettings> implements SysSettingsService{

	@Autowired
	SysSettingsDao sysSettingsDao;
	
	@Autowired
	SysSettingsItemService sysSettingsItemService;
	
	@Autowired
	SysSettingsValuesService sysSettingsValuesService;

	/**
	 * 保存或更新.
	 */
	public SysSettings saveOrUpdate(SysSettings sysSettings) {
		
		//boolean save = true;
		if (sysSettings.getId() == null) {
			sysSettingsDao.insert(sysSettings);
			//save = false;
    	} else {
    		sysSettingsDao.update(sysSettings);
    	}
		
		/*//删除后重新添加
		if (save) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("accessString", " 1=1 and settings_id = " + sysSettings.getId());
			
			sysSettingsValuesService.deleteByExample(param);
		}
		
		SysSettingsItem sysSettingsItem = sysSettingsItemService.selectByPrimaryKey(sysSettings.getItemId());
		Set<SysSettingsItemValue> itemValues = sysSettingsItem.getSysSettingsItemValues();
		
		for (SysSettingsItemValue itemValue : itemValues) {
			SysSettingsValues sysSettingsValues = new SysSettingsValues();
			sysSettingsValues.setItemvalueId(itemValue.getId());
			sysSettingsValues.setSettingsId(sysSettings.getId());
			
			sysSettingsValuesService.saveOrUpdate(sysSettingsValues);
		}*/
		
		return sysSettings;
	}
	
	@Override
	protected CoreDao<SysSettings> getCoreDao() {
		return sysSettingsDao;
	}

	public void deleteSysSettingsList(Integer[] ids) {
		
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("accessString", " 1=1 and settings_id = " + id);
				
				sysSettingsValuesService.deleteByExample(param);
				sysSettingsDao.deleteByPrimaryKey(id);
			}
		}
	}

}
