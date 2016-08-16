package com.delmar.officeTest.dao.mybatis;


import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.officeTest.dao.OfficeTestBankCategoryDao;
import com.delmar.officeTest.model.OfficeTestBankCategory;

@Repository("OfficeTestBankCategoryDao")
public class OfficeTestBankCategoryDaoMybatis extends CoreDaoMyBatis<OfficeTestBankCategory> implements OfficeTestBankCategoryDao{

	protected static final String selectName_SQL=".selectNameById";

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {		
		return "com.delmar.officeTest.mybatis.sql.OfficeTestBankCategoryMapper";
	}

	
	
	public String getNamebyId(Integer id){
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}



}
