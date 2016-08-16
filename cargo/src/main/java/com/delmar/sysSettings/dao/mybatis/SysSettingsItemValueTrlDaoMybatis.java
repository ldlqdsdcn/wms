package com.delmar.sysSettings.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsItemValueTrlDao;
import com.delmar.sysSettings.model.SysSettingsItemValueTrl;

@Repository("SysSettingsItemValueTrlDao")
public class SysSettingsItemValueTrlDaoMybatis extends CoreDaoMyBatis<SysSettingsItemValueTrl> implements SysSettingsItemValueTrlDao{

	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsItemValueTrlMapper";
	}

}
