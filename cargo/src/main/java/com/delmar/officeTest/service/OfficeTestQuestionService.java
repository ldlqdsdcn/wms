package com.delmar.officeTest.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.delmar.core.service.CoreService;
import com.delmar.officeTest.model.OfficeTestQuestion;

public interface OfficeTestQuestionService extends CoreService<OfficeTestQuestion>{

	public OfficeTestQuestion saveOrUpdate(OfficeTestQuestion testCategory);

	public List<OfficeTestQuestion> searchQuestion(Map<String, Object> param);

	public void deleteOfficeTestQuestionList(Integer[] ids);

	public String impExcel(File excelFile, Integer testBankId, OfficeTestQuestion question, String language) throws Exception;
}
