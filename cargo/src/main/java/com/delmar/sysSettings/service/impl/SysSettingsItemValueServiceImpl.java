package com.delmar.sysSettings.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sysSettings.dao.SysSettingsItemValueDao;
import com.delmar.sysSettings.dao.SysSettingsItemValueTrlDao;
import com.delmar.sysSettings.dao.SysSettingsValuesDao;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;
import com.delmar.sysSettings.service.SysSettingsItemValueService;

@Service("SysSettingsItemValueService")
public class SysSettingsItemValueServiceImpl extends CoreServiceImpl<SysSettingsItemValue> implements SysSettingsItemValueService{

	@Autowired
	SysSettingsItemValueDao sysSettingsItemValueDao;
	
	@Autowired
	SysSettingsItemValueTrlDao sysSettingsItemValueTrlDao;
	
	@Autowired
	SysSettingsValuesDao sysSettingsValuesDao;

	/**
	 * 保存或更新.
	 */
	public SysSettingsItemValue saveOrUpdate(SysSettingsItemValue sysSettingsItemValue) {
		
		if (sysSettingsItemValue.getId() == null) {
			sysSettingsItemValueDao.insert(sysSettingsItemValue);
    	} else {
    		sysSettingsItemValueDao.update(sysSettingsItemValue);
    	}
		
		return sysSettingsItemValue;
	}

	@Override
	protected CoreDao<SysSettingsItemValue> getCoreDao() {
		return sysSettingsItemValueDao;
	}

	public void deleteSysSettingsItemValueList(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			for (Integer id : ids) {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("accessString", " 1=1 and itemvalue_id = " + id);
				
				sysSettingsItemValueTrlDao.deleteByExample(param);
				sysSettingsValuesDao.deleteByExample(param);
				sysSettingsItemValueDao.deleteByPrimaryKey(id);
			}
		}
		
	}
	
	public String getItemValueName(SysSettingsItemValue itemValue, String language) {
		
		Set<SysSettingsItemValueTrl> itemValueTrls = itemValue.getSysSettingsItemValueTrls();
		if (itemValueTrls == null || itemValueTrls.size() <= 0) {
			return itemValue.getName();
		}
		
		for (SysSettingsItemValueTrl trl : itemValueTrls) {
			if (language.equals(trl.getLanguage())) {
				return trl.getName();
			}
		}
		return "";
	}

}
