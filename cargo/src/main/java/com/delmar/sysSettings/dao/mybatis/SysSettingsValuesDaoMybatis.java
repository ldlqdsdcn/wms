package com.delmar.sysSettings.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsValuesDao;
import com.delmar.sysSettings.model.SysSettingsValues;

@Repository("SysSettingsValuesDao")
public class SysSettingsValuesDaoMybatis extends CoreDaoMyBatis<SysSettingsValues> implements SysSettingsValuesDao{

	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsValuesMapper";
	}

}
