/******************************************************************************
 * 版权所有 刘大磊 2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/
package com.delmar.core.web.action;

import com.delmar.core.def.RelOperDef;
import com.delmar.core.def.SearchDataTypeDef;
import com.delmar.core.def.SearchShowTypeDef;
import com.delmar.core.model.Search;
import com.delmar.core.model.SearchColumn;
import com.delmar.core.service.SearchService;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "search.name", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "search.pageUrl", message = "不允许为空") })
public class SearchAction extends CoreEditPrivAction {
	private Search  search;
	private List<SearchColumn> searchColumnList=new ArrayList<SearchColumn>();
	private Map<String,String> operMap=new TreeMap<>();
	private Map<Integer,String> dataTypeMap=new TreeMap<>();
	private Map<Integer,String> showTypeMap=new TreeMap<>();
	@Autowired
	private SearchService searchService;
	
	private void init()
	{
		RelOperDef[] relOperDefs=RelOperDef.values();
		for(RelOperDef relOperDef:relOperDefs)
		{
			operMap.put(relOperDef.getKey()+"",relOperDef.getDesc());
		}
		SearchDataTypeDef[] searchDataTypeDefs=SearchDataTypeDef.values();
		for(SearchDataTypeDef relOperDef:searchDataTypeDefs)
		{
			dataTypeMap.put(relOperDef.getKey(),relOperDef.getDesc());
		}
		SearchShowTypeDef[] searchShowTypeDefs=SearchShowTypeDef.values();
		for(SearchShowTypeDef relOperDef:searchShowTypeDefs)
		{
			showTypeMap.put(relOperDef.getKey(),relOperDef.getDesc());
		}
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
		return list();
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
		init();
		 search= searchService.selectByPrimaryKey(id);
		searchColumnList=searchService.getSearchColumnListBySearchId(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		Map<String,Object> param=new HashMap();
		param.put("searchString",getSearchWhere());
		return searchService.selectByExample(param);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		init();
		search=new Search();
		searchColumnList=new ArrayList<SearchColumn>();
	}
    @SkipValidation
    public String addSearchColumn()
    {
		init();
    SearchColumn  searchColumn=new SearchColumn();
	searchColumnList.add(searchColumn);
    return "edit";
    }
    @SkipValidation
    public String deleteSearchColumns()
    {
		init();
    String[] ids=ServletActionContext.getRequest().getParameterValues("SearchColumn_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   SearchColumn column=searchColumnList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	searchColumnList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		searchService.saveSearch(search,searchColumnList);
		this.id=search.getId();
		return edit();
	}
	/**
	 * @return the usergroup
	 */
	public Search getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(Search search) {
		this.search = search;
	}
public List<SearchColumn> getSearchColumnList()
{
	return searchColumnList;
}
public void setSearchColumnList(List<SearchColumn> searchColumnList)
{
	this.searchColumnList=searchColumnList;
}

	public Map<String, String> getOperMap() {
		return operMap;
	}

	public Map<Integer, String> getDataTypeMap() {
		return dataTypeMap;
	}

	public Map<Integer, String> getShowTypeMap() {
		return showTypeMap;
	}
}
