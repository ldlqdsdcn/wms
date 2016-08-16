package com.delmar.officeTest.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.officeTest.dao.OfficeTestQuestionDao;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.model.OfficeTestQuestion;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestExamDetailService;
import com.delmar.officeTest.service.OfficeTestQuestionService;

@Service("OfficeTestQuestionService")
public class OfficeTestQuestionServiceImpl extends CoreServiceImpl<OfficeTestQuestion> implements OfficeTestQuestionService{

	@Autowired
	private OfficeTestQuestionDao officeTestQuestionDao;
	
	@Autowired
	private OfficeTestExamDetailService officeTestExamDetailService;
	
	@Autowired
	private OfficeTestBankCategoryService officeTestBankCategoryService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Override
	protected CoreDao<OfficeTestQuestion> getCoreDao() {
		return officeTestQuestionDao;
	}
	
	public OfficeTestQuestion saveOrUpdate(OfficeTestQuestion officeTestQuestion) {
		
		if (officeTestQuestion.getId() == null) {
			
			officeTestQuestionDao.insert(officeTestQuestion);
    	} else {
    		Date now = new Date();
    		officeTestQuestion.setUpdated(new Timestamp(now.getTime()));
    		officeTestQuestionDao.update(officeTestQuestion);
    	}
		
		return officeTestQuestion;
	}

	public List<OfficeTestQuestion> searchQuestion(Map<String, Object> param) {
		return officeTestQuestionDao.searchQuestion(param);
	}

	public void deleteOfficeTestQuestionList(Integer[] ids) {
		if (ids != null) {
			for (Integer id : ids) {
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("testQuestion_id", id);
							
				officeTestExamDetailService.deleteByExample(param);
				officeTestQuestionDao.deleteByPrimaryKey(id);
			}
		}
		
	}

	public String impExcel(File excelFile, Integer testBankId, OfficeTestQuestion officeTestQuestion, String language) throws Exception {
		
		String msg = "";
		XSSFWorkbook workBook = new XSSFWorkbook(excelFile);
		
		//默认读取第一个sheet页
		XSSFSheet sheet = (XSSFSheet)workBook.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		if (columns < 11) {
			msg = "Excel模板格式错误，导入的Excel应为11列。";
			workBook.close();
			return msg;
		}
		
		if (rows <= 1) {
			msg = "Excel模板格式错误，导入的Excel应多于1行。";
			workBook.close();
			return msg;
		}
		
		for (int i = 1; i <= rows; i++) {
			
			XSSFRow row = sheet.getRow(i);
			OfficeTestQuestion question = new OfficeTestQuestion();
			
			String type = row.getCell(1).getStringCellValue();
			question.setTypeId(getTypeId(type, language));
			
			String category = row.getCell(2).getStringCellValue();
			question.setCategoryId(getCategoryId(category, testBankId));
			
			Double point = row.getCell(3).getNumericCellValue();
			Integer pointNum = getPoint(point);
			
			question.setPoint(pointNum);
			question.setContent(getCellValue(row.getCell(4)));
			question.setOptionOne(getCellValue(row.getCell(5)));
			question.setOptionTwo(getCellValue(row.getCell(6)));
			question.setOptionThree(getCellValue(row.getCell(7)));
			question.setOptionFour(getCellValue(row.getCell(8)));
			question.setOptionFive(getCellValue(row.getCell(9)));
			question.setAnswer(getCellValue(row.getCell(10)));
			question.setTestBankId(testBankId);
			question.setClientId(officeTestQuestion.getClientId());
			question.setOrgId(officeTestQuestion.getOrgId());
			question.setUserId(officeTestQuestion.getUserId());
			question.setUserName(officeTestQuestion.getUserName());
			question.setCreated(officeTestQuestion.getCreated());
			question.setCreatedBy(officeTestQuestion.getCreatedBy());
			question.setCreatedByName(officeTestQuestion.getCreatedByName());
			question.setUpdated(officeTestQuestion.getUpdated());
			question.setUpdatedBy(officeTestQuestion.getUpdatedBy());
			question.setUpdatedByName(officeTestQuestion.getUpdatedByName());
			question.setRemark("");
			
			saveOrUpdate(question);
		}
		
		if (msg.length() <= 0) {
			msg = "导入成功!";
		}
		
		workBook.close();
		return msg;
	}
	
	public String getCellValue(XSSFCell cell) { 
		
		String ret;  
        switch (cell.getCellType()) {  
	        
	        case XSSFCell.CELL_TYPE_NUMERIC:  
	        	ret = String.valueOf(cell.getNumericCellValue());  
	            break;  
	        case XSSFCell.CELL_TYPE_STRING:  
	            ret = cell.getRichStringCellValue().getString();  
	            break;  
	        default:  
	            ret = "";  
        }  
        
        if (ret == null) {
        	ret = "";
        }
          
        return ret;
	}
	
	public String trim (String str) {
		
		return str == null ? "" : str;
	}

	/**
	 * 如果没有输入分数 或者分钟不为数字都默认0.
	 * @param point
	 * @return
	 */
	public Integer getPoint(Double point) {
		if (point == null || String.valueOf(point).length() <= 0) {
			return 0;
		}
		boolean isNum = String.valueOf(point).matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		if (isNum) {
			int num = Integer.parseInt(String.valueOf(point).replace(".0", ""));
			if (num < 0) {
				return -1;
			} else {
				return num;
			}
		}
		return -1;
	}

	public Integer getCategoryId(String category, Integer testBankId) {
		
		Map<String, Object> example = new HashMap<String, Object>();
		example.put("accessString", " 1=1 and name = '" + category + "' and testbank_id = " + testBankId);
		
		List<OfficeTestBankCategory> categorys = officeTestBankCategoryService.selectByExample(example);
		if (categorys == null || categorys.size() <= 0) {
			OfficeTestBankCategory officeTestBankCategory = new OfficeTestBankCategory();
			officeTestBankCategory.setCode(category);
			officeTestBankCategory.setName(category);
			officeTestBankCategory.setTestBankId(testBankId);
			officeTestBankCategory.setRemark("");
			officeTestBankCategoryService.saveOrUpdate(officeTestBankCategory);
			
			return officeTestBankCategory.getId();
		} else {
			return categorys.get(0).getId();
		}
	}

	public Integer getTypeId(String type, String language) {
		
		List<DatadictTrl> list = datadictService.getDatadictTrlByValue(DatadictType.QUESTIONTYPE, language);
		for (DatadictTrl trl : list) {
			
			if (type.equals(trl.getName())) {
				return trl.getDatadictId();
			}
		}
		return null;
	}


}
