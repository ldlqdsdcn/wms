package com.delmar.officeTest.service;

import java.util.List;
import java.util.Map;

import com.delmar.core.service.CoreService;
import com.delmar.officeTest.model.OfficeTestExam;
import com.delmar.officeTest.model.OfficeTestExamDetail;

public interface OfficeTestExamService extends CoreService<OfficeTestExam>{

	public OfficeTestExam saveOrUpdate(OfficeTestExam testCategory);

	public List<OfficeTestExam> selectByCustom(Map<String, Object> param);
}
