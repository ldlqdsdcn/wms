/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.SearchColumnService;
import com.delmar.core.service.SearchService;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-03-02 11:42:53
 */
public class SearchAction extends CoreEditPrivAction {
	private Search  search;
	private List<SearchColumn> columns=new ArrayList<SearchColumn>();
	@Autowired
	private SearchService searchService;
	@Autowired
	private SearchColumnService searchColumnService;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "查询条件");
	}

	
	private void init()
	{
		Map<String,Object>param=new HashMap<String,Object>();
		param.put("searchId", id);
		columns=searchColumnService.selectByExample(param);
	}
	public String addColumn()
	{
		columns.add(new SearchColumn());
		return "edit";
	}
	public String deleteColumns()
	{
		String[] ids=ServletActionContext.getRequest().getParameterValues("ids");
		List<String> idList=new ArrayList<String>();
		
		//
		Integer[] intids=new Integer[ids.length];
		
		for(int i=0;i<ids.length;i++)
		{
			idList.add(ids[i]);
			Integer index=Integer.parseInt(ids[i]);
			SearchColumn column=columns.get(index);
			if(column.getId()!=null&&column.getId()!=0)
			{
				intids[i]=column.getId();
			}
		}
		java.util.Collections.sort(idList);
		System.out.println("columns.size="+columns.size());
		for(int i=idList.size()-1;i>=0;i--)
		{
			
			this.columns.remove(Integer.parseInt(idList.get(i)));
		}
		System.out.println("columns.size="+columns.size());
		return "edit";
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "search";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		searchService.deleteByPrimaryKey(search.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		searchService.deleteSearchList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return search.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 search= searchService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return searchService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		search=new Search();
		 init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		searchService.save(search);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setSearch(Search search) {
		this.search = search;
	}

	/**
	 * @return the columns
	 */
	public List<SearchColumn> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<SearchColumn> columns) {
		this.columns = columns;
	}

}
