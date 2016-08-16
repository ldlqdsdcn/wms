package com.delmar.officeTest.dao.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.officeTest.dao.OfficeTestQuestionDao;
import com.delmar.officeTest.model.OfficeTestQuestion;

@Repository("OfficeTestQuestionDao")
public class OfficeTestQuestionDaoMybatis extends CoreDaoMyBatis<OfficeTestQuestion> implements OfficeTestQuestionDao{

	protected static final String selectName_SQL=".selectNameById";
	
	protected static final String selectByParm_SQL=".selectByParm";
	
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {		
		return "com.delmar.officeTest.mybatis.sql.OfficeTestQuestionMapper";
	}

	public String getNamebyId(Integer id){
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}

	public List<OfficeTestQuestion> searchQuestion(Map<String, Object> param) {
		
		List<OfficeTestQuestion> list = new ArrayList<OfficeTestQuestion>();
		String sql1 = "Select a.*,b.*,c.*  From Office_TestQuestion a "
				+ " INNER JOIN Office_TestBank b ON a.testBank_Id=b.id "
				+ " LEFT OUTER JOIN Office_TestExam_Detail c ON a.id=c.testQuestion_Id "
				+ " and IsNull(c.ZFBZ,0)=0 and c.examUserId= " + param.get("examUserId")			
				+ " where a.testBank_Id=" + param.get("testBankId") + " and a.type_id in(" + param.get("typeId1") + ") ";
		
		if (param != null) {
			param.put("customSelect", sql1);
		}
		
		list = sqlSessionTemplate.selectList(this.getSqlName() + selectByParm_SQL, param);
		
		String sql2 = "Select a.*,b.*,c.* From Office_TestQuestion a "
				+ " INNER JOIN Office_TestBank b ON a.testBank_Id=b.id "
				+ " LEFT OUTER JOIN Office_TestExam_Detail c ON a.id=c.testQuestion_Id "
				+ " and IsNull(c.ZFBZ,0)=0 and c.examUserId= " + param.get("examUserId")			
				+ " where a.testBank_Id=" + param.get("testBankId") + " and a.type_id in(" + param.get("typeId2") + ") ";
		if (param != null) {
			param.put("customSelect", sql2);
		}
		
		List<OfficeTestQuestion> list2 = sqlSessionTemplate.selectList(this.getSqlName() + selectByParm_SQL, param);
		
		list.addAll(list2);
		
		return list;
	}

	
}
