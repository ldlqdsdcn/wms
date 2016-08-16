package com.delmar.sysSettings.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsDao;
import com.delmar.sysSettings.model.SysSettings;

@Repository("SysSettingsDao")
public class SysSettingsDaoMybatis extends CoreDaoMyBatis<SysSettings> implements SysSettingsDao{

	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsMapper";
	}

}
