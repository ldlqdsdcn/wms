package com.delmar.officeTest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.CustomerDao;
import com.delmar.crm.model.Customer;
import com.delmar.officeTest.dao.OfficeTestBankCategoryDao;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;

@Service("OfficeTestBankCategoryService")
public class OfficeTestBankCategoryServiceImpl extends CoreServiceImpl<OfficeTestBankCategory> implements OfficeTestBankCategoryService{

	@Autowired
	private OfficeTestBankCategoryDao officeTestBankCategoryDao;

	@Override
	protected CoreDao<OfficeTestBankCategory> getCoreDao() {
		return officeTestBankCategoryDao;
	}


	public OfficeTestBankCategory saveOrUpdate(OfficeTestBankCategory testCategory) {
		
		if (testCategory.getId() == null) {
			officeTestBankCategoryDao.insert(testCategory);
    	} else {
    		officeTestBankCategoryDao.update(testCategory);
    	}
		
		return testCategory;
	}


	public void saveCategoryIds(OfficeTestBank officeTestBank, String categoryIds) {
		
		if (categoryIds != null && categoryIds.length() > 0) {
			String[] ids = categoryIds.split(",");
			for (String id : ids) {
				OfficeTestBankCategory otbc = new OfficeTestBankCategory();
				otbc.setTestBankId(officeTestBank.getId());
				otbc.setCode("");
				otbc.setName("");
				otbc.setRemark("");
				saveOrUpdate(otbc);
			}
		}
	}

}
