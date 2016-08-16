package com.delmar.sysSettings.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.sysSettings.dao.SysSettingsDao;
import com.delmar.sysSettings.dao.SysSettingsItemDao;
import com.delmar.sysSettings.dao.SysSettingsItemTrlDao;
import com.delmar.sysSettings.dao.SysSettingsItemValueDao;
import com.delmar.sysSettings.dao.SysSettingsItemValueTrlDao;
import com.delmar.sysSettings.dao.SysSettingsValuesDao;
import com.delmar.sysSettings.enums.InputTypeEnum;
import com.delmar.sysSettings.model.SysSettings;
import com.delmar.sysSettings.model.SysSettingsItem;
import com.delmar.sysSettings.model.SysSettingsItemTrl;
import com.delmar.sysSettings.model.SysSettingsItemValue;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;
import com.delmar.sysSettings.model.SysSettingsValues;
import com.delmar.sysSettings.service.SysSettingsItemService;

@Service("SysSettingsItemService")
public class SysSettingsItemServiceImpl extends CoreServiceImpl<SysSettingsItem> implements SysSettingsItemService{
	
	@Autowired
	SysSettingsItemDao sysSettingsItemDao;
	
	@Autowired
	SysSettingsItemTrlDao sysSettingsItemTrlDao;
	
	@Autowired
	SysSettingsValuesDao sysSettingsValuesDao;
	
	@Autowired
	SysSettingsDao sysSettingsDao;
	
	@Autowired
	SysSettingsItemValueTrlDao sysSettingsItemValueTrlDao;
	
	@Autowired
	SysSettingsItemValueDao sysSettingsItemValueDao;

	/**
	 * 保存或更新.
	 */
	public SysSettingsItem saveOrUpdate(SysSettingsItem sysSettingsItem) {
		
		if (sysSettingsItem.getId() == null) {
			sysSettingsItemDao.insert(sysSettingsItem);
    	} else {
    		sysSettingsItemDao.update(sysSettingsItem);
    	}
		
		return sysSettingsItem;
	}

	@Override
	protected CoreDao<SysSettingsItem> getCoreDao() {
		return sysSettingsItemDao;
	}

	public void deleteSysSettingsItemList(Integer[] ids) {
		if (ids != null) {
			for (Integer id : ids) {
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("accessString", " 1=1 and item_id = " + id);
							
				sysSettingsItemTrlDao.deleteByExample(param);
				List<SysSettings> sysSettingsList = sysSettingsDao.selectByExample(param);
				for (SysSettings sysSettings : sysSettingsList) {
					Set<SysSettingsValues> sysSettingsValues = sysSettings.getSysSettingsValues();
					for (SysSettingsValues sysSettingsValue : sysSettingsValues) {
						sysSettingsValuesDao.deleteByPrimaryKey(sysSettingsValue.getId());
					}
					sysSettingsDao.deleteByPrimaryKey(sysSettings.getId());
				}
				
				List<SysSettingsItemValue> itemValues = sysSettingsItemValueDao.selectByExample(param);
				for (SysSettingsItemValue itemValue : itemValues) {
					Set<SysSettingsItemValueTrl> trls = itemValue.getSysSettingsItemValueTrls();
					for (SysSettingsItemValueTrl trl : trls) {
						sysSettingsItemValueTrlDao.deleteByPrimaryKey(trl.getId());
					}
					sysSettingsItemValueDao.deleteByPrimaryKey(itemValue.getId());
				}
				
				sysSettingsItemDao.deleteByPrimaryKey(id);
			}
		}
		
	}

	public List<DatadictTrl> getInputTypeList() {
		
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
		
		InputTypeEnum[] inputTypes = InputTypeEnum.values();
		
		for(InputTypeEnum inputTypeEnum : inputTypes) {
			DatadictTrl trl = new DatadictTrl();
			trl.setDatadictId(null);
			trl.setName(inputTypeEnum.getName());
			trl.setLanguage(null);
			trl.setValue(String.valueOf(inputTypeEnum.getValue()));
			
			list.add(trl);
		}
		return list;
	}

	/**
	 * 获取所有的系统类别.
	 */
	public List<DatadictTrl> getItemList() {
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
		List<SysSettingsItem> items = sysSettingsItemDao.selectByExample(null);
		if (items == null || items.size() <= 0) {
			return list;
		}
		
		for (SysSettingsItem item : items) {
			DatadictTrl trl = new DatadictTrl();
			trl.setDatadictId(item.getId());
			trl.setName(item.getName());
			trl.setLanguage(null);
			trl.setValue(String.valueOf(item.getValue()));
			
			list.add(trl);
		}
		
		return list;
	}

	public List<DatadictTrl> getDefaultedList() {
		
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
		
		DatadictTrl trl = new DatadictTrl();
		trl.setDatadictId(0);
		trl.setName("否");
		trl.setLanguage(null);
		trl.setValue("0");
		list.add(trl);
		
		trl = new DatadictTrl();
		trl.setDatadictId(0);
		trl.setName("是");
		trl.setLanguage(null);
		trl.setValue("1");
		list.add(trl);
		
		return list;
	}
	
}
