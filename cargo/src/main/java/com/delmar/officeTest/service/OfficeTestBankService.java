package com.delmar.officeTest.service;

import java.util.List;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.service.CoreService;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;

public interface OfficeTestBankService extends CoreService<OfficeTestBank>{

	public OfficeTestBank saveOrUpdate(OfficeTestBank testCategory, String categoryIds);

	public List<DatadictTrl> getAllBankCategorys(boolean isList);
	
	public List<DatadictTrl> getAllActiveBankCategorys(boolean isList);
	

	public List<DatadictTrl> categoryDatadictTrlList(Integer testBankId);
	
	public List<OfficeTestBankCategory> categoryList(Integer testBankId);

	public void deleteOfficeTestBankList(Integer[] ids);
}
