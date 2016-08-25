/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;

import com.delmar.core.model.Page;

import com.delmar.core.service.PageService;


/**
 * @author 刘大磊 2016-08-25 11:11:32
 */
public class PageAction extends CoreEditPrivAction {
	private Page  page;
	
	@Autowired
	private PageService pageService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "page";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		pageService.deleteByPrimaryKey(page.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		pageService.deletePageList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return page.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 page= pageService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return pageService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		page=new Page();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		pageService.save(page);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Page getPage() {
		return page;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

}
