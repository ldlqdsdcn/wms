package com.delmar.officeTest.dao.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.officeTest.dao.OfficeTestExamDetailDao;
import com.delmar.officeTest.model.OfficeTestExamDetail;

@Repository("OfficeTestExamDetailDao")
public class OfficeTestExamDetailDaoMybatis extends CoreDaoMyBatis<OfficeTestExamDetail> implements OfficeTestExamDetailDao{

	protected static final String selectName_SQL=".selectNameById";
	
	protected static final String selectByParm_SQL=".selectByParm";

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {		
		return "com.delmar.officeTest.mybatis.sql.OfficeTestExamDetailMapper";
	}

	
	
	public String getNamebyId(Integer id) {
		return sqlSessionTemplate.selectOne(getSqlName()+this.selectName_SQL,id);
	}

	public List<OfficeTestExamDetail> searchQuestionTestDetail(Map<String, Object> parm) {
		
		List<OfficeTestExamDetail> result = new ArrayList<OfficeTestExamDetail>();
		String sql = "Select b.* "
				+ " From Office_TestExam_Detail b,Office_TestQuestion a,Office_TestBank c "
				+ " Where a.id = b.testQuestion_ID and a.testBank_id=c.id ";
		
		sql += parm.get("andSelect");
		
		if (parm != null) {
			parm.put("customSelect", sql);
		}
		
		List<OfficeTestExamDetail> list = sqlSessionTemplate.selectList(this.getSqlName() + selectByParm_SQL, parm);
		for (OfficeTestExamDetail detail : list) {
			result.add(detail);
		}
		return result;
	}



	public List<OfficeTestExamDetail> selectByParm(Map<String, Object> param) {
		
		String sql = "select d.* from Office_TestExam_Detail d " +
						"left join Office_TestQuestion t on d.testQuestion_id = t.id " + 
						"left join Office_TestExam e on d.exam_id = e.id " +
						"where 1=1 and e.BeCancel = 0 ";
		
		sql += param.get("andSelect");
		if (param != null) {
			param.put("customSelect", sql);
		}
		List<OfficeTestExamDetail> list = sqlSessionTemplate.selectList(this.getSqlName() + selectByParm_SQL, param);
		return list;
	}

}
