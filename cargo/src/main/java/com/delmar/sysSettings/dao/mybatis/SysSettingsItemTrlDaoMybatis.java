package com.delmar.sysSettings.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sysSettings.dao.SysSettingsItemTrlDao;
import com.delmar.sysSettings.model.SysSettingsItemTrl;

@Repository("SysSettingsItemTrlDao")
public class SysSettingsItemTrlDaoMybatis extends CoreDaoMyBatis<SysSettingsItemTrl> implements SysSettingsItemTrlDao{

	@Override
	protected String getSqlName() {
		return "com.delmar.sysSettings.mybatis.sql.SysSettingsItemTrlMapper";
	}

}
