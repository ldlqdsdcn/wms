package com.delmar.officeTest.dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.delmar.core.dao.CoreDao;
import com.delmar.officeTest.model.OfficeTestQuestion;

public interface OfficeTestQuestionDao extends CoreDao<OfficeTestQuestion>{

	public List<OfficeTestQuestion> searchQuestion(Map<String, Object> param);

}
