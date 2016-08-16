package com.delmar.officeTest.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.officeTest.dao.OfficeTestBankDao;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;

@Repository("OfficeTesBankDao")
public class OfficeTesBankDaoMybatis extends CoreDaoMyBatis<OfficeTestBank> implements OfficeTestBankDao{

	protected static final String selectName_SQL=".selectNameById";

	@Override
	protected String getSqlName() {		
		return "com.delmar.officeTest.mybatis.sql.OfficeTestBankMapper";
	}

	public String getNamebyId(Integer id){
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}
}
