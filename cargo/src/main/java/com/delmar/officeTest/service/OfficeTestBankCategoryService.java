package com.delmar.officeTest.service;

import java.util.List;
import java.util.Map;

import com.delmar.base.model.DatadictTrl;
import com.delmar.core.service.CoreService;
import com.delmar.officeTest.model.OfficeTestBank;
import com.delmar.officeTest.model.OfficeTestBankCategory;

public interface OfficeTestBankCategoryService extends CoreService<OfficeTestBankCategory>{

	public OfficeTestBankCategory saveOrUpdate(OfficeTestBankCategory officeTestBankCategory);

	public void saveCategoryIds(OfficeTestBank officeTestBank, String categoryIds);

}
