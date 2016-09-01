/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.service.CoreService;
import com.delmar.core.web.controller.displaytag.paging.PaginatedListHelper;
import com.delmar.core.web.def.PagingType;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.system.model.UserFootmark;
import com.delmar.system.service.UserFootmarkService;
/**
 * @author 刘大磊 2016-09-01 17:30:01
 */
@Validations(requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "userFootmark.visiteDate",message = "不允许为空")})
public class UserFootmarkAction extends CoreEditPrivAction {
	private UserFootmark  userFootmark;
	@Autowired
	private UserFootmarkService userFootmarkService;
	public UserFootmarkAction()
	{
		pagingType= PagingType.DATABASE;
	}
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



	@Override
	public PaginatedListHelper searchPaginatedList() {
		Map<String, Object> param = new HashMap();
		param.put("searchString", getSearchWhere());
		Integer pageNumber=(Integer)FacesUtils.getValueInHashtableOfSession("pageNumber");
		param.put("pageNo", FacesUtils.getValueInHashtableOfSession("pageNumber"));
		param.put("pageSize",20);
		int fullListSize = userFootmarkService.countObjects(param);
		List list = userFootmarkService.selectByExample(param);
		PaginatedListHelper paginatedListHelper = new PaginatedListHelper(pageNumber, fullListSize, list);
		return paginatedListHelper;

	}

	/* (non-Javadoc)
         * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
         */

	public void createForm() {
		userFootmark=new UserFootmark();
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */

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
