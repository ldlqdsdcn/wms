/******************************************************************************
 * 版权所有 刘大磊 2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.web.action.CoreEditPagingAction;
import com.delmar.core.web.controller.displaytag.paging.PaginatedListHelper;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.system.model.UserFootmark;
import com.delmar.system.service.UserFootmarkService;
/**
 * @author 刘大磊 2016-09-02 10:18:25
 */
@Validations(requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "userFootmark.visiteDate",message = "不允许为空")})
public class UserFootmarkAction extends CoreEditPagingAction {
	private UserFootmark  userFootmark;
	@Autowired
	private UserFootmarkService userFootmarkService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "userFootmark";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		userFootmarkService.deleteByPrimaryKey(userFootmark.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		userFootmarkService.deleteUserFootmarkList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return userFootmark.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 userFootmark= userFootmarkService.selectByPrimaryKey(id);

	}
public PaginatedListHelper searchPaginatedList()
{
Map<String, Object> param = new HashMap();
param.put("searchString", getSearchWhere());
param.put("pageNo", page);
param.put("pageSize",20);
int fullListSize = userFootmarkService.countObjects(param);
List list = userFootmarkService.selectByExample(param);
PaginatedListHelper paginatedListHelper = new PaginatedListHelper(page, fullListSize, list);
return paginatedListHelper;
}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		userFootmark=new UserFootmark();
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
		userFootmarkService.saveUserFootmark(userFootmark);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public UserFootmark getUserFootmark() {
		return userFootmark;
	}

	/**
	 * @param userFootmark the userFootmark to set
	 */
	public void setUserFootmark(UserFootmark userFootmark) {
		this.userFootmark = userFootmark;
	}
}
