package com.delmar.officeTest.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.officeTest.dao.OfficeTestBankDao;
import com.delmar.officeTest.dao.OfficeTestExamDao;
import com.delmar.officeTest.dao.OfficeTestExamDetailDao;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.officeTest.service.OfficeTestBankService;
import com.delmar.officeTest.service.OfficeTestExamDetailService;
import com.delmar.officeTest.service.OfficeTestExamService;
import com.delmar.officeTest.service.OfficeTestQuestionService;

@Service("OfficeTestBankService")
public class OfficeTestBankServiceImpl extends CoreServiceImpl<OfficeTestBank> implements OfficeTestBankService{

	@Autowired
	private OfficeTestBankDao officeTestBankDao;
	
	@Autowired
	private OfficeTestExamDetailService officeTestExamDetailService;
	
	@Autowired
	private OfficeTestExamService officeTestExamService;
	
	@Autowired
	private OfficeTestBankCategoryService officeTestBankCategoryService;
	
	@Autowired
	private OfficeTestQuestionService officeTestQuestionService;
	

	@Override
	protected CoreDao<OfficeTestBank> getCoreDao() {
		return officeTestBankDao;
	}
	
	public OfficeTestBank saveOrUpdate(OfficeTestBank officeTestBank, String categoryIds) {
		
		if (officeTestBank.getId() == null) {
			
			officeTestBankDao.insert(officeTestBank);
    	} else {
    		
    		officeTestBankDao.update(officeTestBank);
    	}
		
		officeTestBankCategoryService.saveCategoryIds(officeTestBank, categoryIds);
		
		return officeTestBank;
	}

	public List<DatadictTrl> getAllBankCategorys(boolean isList) {
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
		List<OfficeTestBank> cagegorys = officeTestBankDao.selectByExample(new HashMap<String, Object>());
		
		if (!isList) {
			DatadictTrl datadictTrl = new DatadictTrl();
			datadictTrl.setDatadictId(0);
			datadictTrl.setName("请选择..");
			datadictTrl.setLanguage(null);
			datadictTrl.setValue("");
			list.add(datadictTrl);
		}
		
		for(OfficeTestBank cagegory : cagegorys) {
			DatadictTrl trl = new DatadictTrl();
			trl.setDatadictId(cagegory.getId());
			trl.setName(cagegory.getName());
			trl.setLanguage(null);
			trl.setValue(String.valueOf(cagegory.getId()));
			
			list.add(trl);
		}
		return list;
	}
	
	public List<DatadictTrl> getAllActiveBankCategorys(boolean isList) {
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accessString", " 1=1 and beState = 0");
		List<OfficeTestBank> cagegorys = officeTestBankDao.selectByExample(map);
		
		if (!isList) {
			DatadictTrl datadictTrl = new DatadictTrl();
			datadictTrl.setDatadictId(0);
			datadictTrl.setName("请选择..");
			datadictTrl.setLanguage(null);
			datadictTrl.setValue("");
			list.add(datadictTrl);
		}
		
		for(OfficeTestBank cagegory : cagegorys) {
			DatadictTrl trl = new DatadictTrl();
			trl.setDatadictId(cagegory.getId());
			trl.setName(cagegory.getName());
			trl.setLanguage(null);
			trl.setValue(String.valueOf(cagegory.getId()));
			
			list.add(trl);
		}
		return list;
	}

	public List<DatadictTrl> categoryDatadictTrlList(Integer testBankId) {
		List<DatadictTrl> list = new ArrayList<DatadictTrl>();
			
		List<OfficeTestBankCategory> detailList = categoryList(testBankId);
			for (OfficeTestBankCategory cagegory : detailList) {
				DatadictTrl trl=new DatadictTrl();
				trl.setDatadictId(cagegory.getId());
				trl.setName(cagegory.getName());
				trl.setLanguage(null);
				trl.setValue(String.valueOf(cagegory.getId()));
				
				list.add(trl);
			}
		return list;
	}

	public List<OfficeTestBankCategory> categoryList(Integer testBankId) {
		
		List<OfficeTestBankCategory> detailList = new ArrayList<OfficeTestBankCategory>();
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
	    if (testBankId == null){
	    	return detailList;
	    }
	    
	    sb.append(" and testBank_id = ").append(testBankId).append("");
		String accessString=" 1=1 " + " " + sb.toString();
		param.put("accessString", accessString);
		
		return officeTestBankCategoryService.selectByExample(param);
	}

	public void deleteOfficeTestBankList(Integer[] ids) {
		if (ids != null) {
			for (Integer id : ids) {
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("testBank_id", id);
							
				officeTestExamDetailService.deleteByExample(param);
				officeTestExamService.deleteByExample(param);
				officeTestQuestionService.deleteByExample(param);
				officeTestBankCategoryService.deleteByExample(param);
				officeTestBankDao.deleteByPrimaryKey(id);
			}
		}
	}


}
