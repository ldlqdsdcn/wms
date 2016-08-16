package com.delmar.sysSettings.dao.mybatis;


import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsItemDao;
import com.delmar.sysSettings.model.SysSettingsItem;

@Repository("SysSettingsItemDao")
public class SysSettingsItemDaoMybatis extends CoreDaoMyBatis<SysSettingsItem> implements SysSettingsItemDao{


	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsItemMapper";
	}

}
