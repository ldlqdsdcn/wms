package com.delmar.officeTest.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;

public class OfficeTestBankCategoryAction extends CoreEditPrivAction{

	private OfficeTestBankCategory testCategory;
	private String buttonType;
	
	@Autowired
	private OfficeTestBankCategoryService categoryService;
	
	
	public OfficeTestBankCategory getTestCategory() {
		return testCategory;
	}

	public void setTestCategory(OfficeTestBankCategory testCategory) {
		this.testCategory = testCategory;
	}

	@Override
	public String getModuleName() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deletes()
	 */
	@Override
	public String deletes() {
		return null;
	}

	@Override
	public Integer getModelId() {
		return null;
	}

	@Override
	public void editForm() {
		
	}

	@Override
	public List search() {
		return null;
	}

	@Override
	public void createForm() {
		
		testCategory = new OfficeTestBankCategory();
		testCategory.setTestBankId(id);
	}

	@Override
	public String saveForm() {
		return null;
	}

	@Override
	public String getPurpose() {
		return null;
	}
	
	/**
     * 进仓单列表
     *
     * @return
     */
    @Override
	public String list() {
		return null;
	}
    
    public String saveOrUpdate() {
    	
    	testCategory = categoryService.saveOrUpdate(testCategory);
    	return "edit";
    }
    
    
	@Override
	public String delete() {
		
		categoryService.deleteByPrimaryKey(id);
		return "delete";
	}
	
	public String initBankCagegory() {
		
		testCategory = categoryService.selectByPrimaryKey(id);
		return "edit";
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

}
