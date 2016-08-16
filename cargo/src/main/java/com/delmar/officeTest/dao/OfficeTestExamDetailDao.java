package com.delmar.officeTest.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.delmar.core.dao.CoreDao;
import com.delmar.officeTest.model.OfficeTestExamDetail;

public interface OfficeTestExamDetailDao extends CoreDao<OfficeTestExamDetail>{

	public List<OfficeTestExamDetail> searchQuestionTestDetail(Map<String, Object> parm);

	public List<OfficeTestExamDetail> selectByParm(Map<String, Object> param);
}
