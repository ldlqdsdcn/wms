package com.delmar.officeTest.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.officeTest.dao.OfficeTestExamDao;
import com.delmar.officeTest.model.OfficeTestExam;

@Repository("OfficeTestExamDao")
public class OfficeTestExamDaoMybatis extends CoreDaoMyBatis<OfficeTestExam> implements OfficeTestExamDao{

	protected static final String selectName_SQL=".selectNameById";
	protected static final String selectByParm_SQL=".selectByParm";

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {		
		return "com.delmar.officeTest.mybatis.sql.OfficeTestExamMapper";
	}

	
	
	public String getNamebyId(Integer id){
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}

	public List<OfficeTestExam> selectByCustom(Map<String, Object> param) {
		return sqlSessionTemplate.selectList(this.getSqlName() + selectByParm_SQL, param);
	}
}
