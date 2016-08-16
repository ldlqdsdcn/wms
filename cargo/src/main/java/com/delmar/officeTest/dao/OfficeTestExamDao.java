package com.delmar.officeTest.dao;

import java.util.List;
import java.util.Map;

import com.delmar.core.dao.CoreDao;
import com.delmar.officeTest.model.OfficeTestExam;

public interface OfficeTestExamDao extends CoreDao<OfficeTestExam>{

	public List<OfficeTestExam> selectByCustom(Map<String, Object> param);

}
