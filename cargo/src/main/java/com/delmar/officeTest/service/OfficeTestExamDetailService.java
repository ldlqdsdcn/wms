package com.delmar.officeTest.service;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.delmar.core.service.CoreService;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestExamDetail;

public interface OfficeTestExamDetailService extends CoreService<OfficeTestExamDetail>{

	public OfficeTestExamDetail saveOrUpdate(OfficeTestExamDetail testCategory);
	
	public List<OfficeTestExamDetail> searchQuestionTestDetail(Map<String, Object> parm);

	public List<OfficeTestExamDetail> selectByParm(Map<String, Object> param);

}
