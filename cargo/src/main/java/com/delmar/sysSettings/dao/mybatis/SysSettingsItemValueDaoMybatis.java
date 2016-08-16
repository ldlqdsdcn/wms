package com.delmar.sysSettings.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsItemValueDao;
import com.delmar.sysSettings.model.SysSettingsItemValue;

@Repository("SysSettingsItemValueDao")
public class SysSettingsItemValueDaoMybatis extends CoreDaoMyBatis<SysSettingsItemValue> implements SysSettingsItemValueDao{

	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsItemValueMapper";
	}

}
