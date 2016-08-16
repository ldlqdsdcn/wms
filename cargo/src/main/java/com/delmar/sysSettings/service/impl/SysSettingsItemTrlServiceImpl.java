package com.delmar.sysSettings.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sysSettings.dao.SysSettingsItemTrlDao;
import com.delmar.sysSettings.model.SysSettingsItemTrl;
import com.delmar.sysSettings.service.SysSettingsItemTrlService;
import com.delmar.utils.StringUtil;

@Service("SysSettingsItemTrlService")
public class SysSettingsItemTrlServiceImpl extends CoreServiceImpl<SysSettingsItemTrl> implements SysSettingsItemTrlService{

	@Autowired
	SysSettingsItemTrlDao sysSettingsItemTrlDao;

	/**
	 * 保存或更新.
	 */
	public SysSettingsItemTrl saveOrUpdate(SysSettingsItemTrl sysSettingsItemTrl) {
		
		if (sysSettingsItemTrl.getId() == null) {
			sysSettingsItemTrlDao.insert(sysSettingsItemTrl);
    	} else {
    		sysSettingsItemTrlDao.update(sysSettingsItemTrl);
    	}
		
		return sysSettingsItemTrl;
	}

	@Override
	protected CoreDao<SysSettingsItemTrl> getCoreDao() {
		return sysSettingsItemTrlDao;
	}
	
	public List<SysSettingsItemTrl> getItemTrlList(Integer itemId) {
		
		Map<String, String> param = new HashMap<String, String> ();
		StringBuilder sb=new StringBuilder();
		sb.append(" and item_id=").append(itemId);
		param.put("accessString", " 1=1 "+sb.toString());
		
		return sysSettingsItemTrlDao.selectByExample(param);
	}

}
