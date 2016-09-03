/******************************************************************************
 * 版权所有 刘大磊 2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/
package com.delmar.core.web.action;

import com.delmar.core.model.Window;
import com.delmar.core.service.WindowService;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 2016-08-28 17:16:34
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "window.name", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "window.isactive", message = "不允许为空") })
public class WindowAction extends CoreEditPrivAction {
	private Window  window;
	@Autowired
	private WindowService windowService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "window";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		windowService.deleteByPrimaryKey(window.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		windowService.deleteWindowList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return window.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 window= windowService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		Map<String,Object> param=new HashedMap();
		param.put("searchString",getSearchWhere());
		return windowService.selectByExample(param);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		window=new Window();
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		Date date=new Date();
		Integer currentUserId=getCurrentUser();
		if(window.isnew())
		{
			window.setCreated(date);
			window.setCreatedby(currentUserId);
		}
		window.setUpdated(date);
		window.setUpdatedby(currentUserId);
		windowService.saveWindow(window);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
}
