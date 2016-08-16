package com.delmar.sysSettings.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sysSettings.dao.SysSettingsItemValueTrlDao;
import com.delmar.sysSettings.model.SysSettingsItemTrl;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;
import com.delmar.sysSettings.service.SysSettingsItemValueTrlService;

@Service("SysSettingsItemValueTrlService")
public class SysSettingsItemValueTrlServiceImpl extends CoreServiceImpl<SysSettingsItemValueTrl> implements SysSettingsItemValueTrlService{

	@Autowired
	SysSettingsItemValueTrlDao sysSettingsItemValueTrlDao;

	/**
	 * 保存或更新.
	 */
	public SysSettingsItemValueTrl saveOrUpdate(SysSettingsItemValueTrl sysSettingsItemValueTrl) {
		
		if (sysSettingsItemValueTrl.getId() == null) {
			sysSettingsItemValueTrlDao.insert(sysSettingsItemValueTrl);
    	} else {
    		sysSettingsItemValueTrlDao.update(sysSettingsItemValueTrl);
    	}
		
		return sysSettingsItemValueTrl;
	}

	@Override
	protected CoreDao<SysSettingsItemValueTrl> getCoreDao() {
		return sysSettingsItemValueTrlDao;
	}
	
	public List<SysSettingsItemValueTrl> getItemTrlList(Integer itemvalueId) {
		
		Map<String, String> param = new HashMap<String, String> ();
		StringBuilder sb = new StringBuilder();
		sb.append(" and itemvalue_id =").append(itemvalueId);
		param.put("accessString", " 1=1 "+sb.toString());
		
		return sysSettingsItemValueTrlDao.selectByExample(param);
	}

}
