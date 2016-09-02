/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.dao.SqlSessionForLog;
import com.delmar.core.model.Changelog;
import com.delmar.core.model.SearchColumnList;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.ChangelogService;
import com.delmar.core.service.TableColumnService;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.ResourceMessage;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年1月20日 上午11:11:10
 */
public class ChangeLogAction extends CoreEditPrivAction{
	private SqlSessionForLog sqlSessionForLog;
	@Autowired
	private ChangelogService changelogService;
	private TableColumnService tableColumnService; 
	private List<TableColumn> tableColumnList;
	@Autowired
	private UserService userService;
	private Map<String,String> headerMap=new TreeMap<String,String>(); 
	private List bodyList=new ArrayList(); 

	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "操作日志查看");
	}

	
	
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	public List getBodyList() {
		return bodyList;
	}

	public void setBodyList(List bodyList) {
		this.bodyList = bodyList;
	}

	private Changelog changelog;
	
	
	
	public Changelog getChangelog() {
		return changelog;
	}

	public void setChangelog(Changelog changelog) {
		this.changelog = changelog;
	}

	public void setSqlSessionForLog(SqlSessionForLog sqlSessionForLog) {
		this.sqlSessionForLog = sqlSessionForLog;
	}
	
	@Override
	public String getModuleName() {
		return "changeLog";
	}

	@Override
	public String delete() {
		
		return "list";
	}

	@Override
	public void deleteList(Integer[] ids) {
		
		 

	}

	@Override
	public Integer getModelId() {
		
		return null;
	}

	@Override
	public void editForm() {
		

	}
	@SuppressWarnings("unchecked")
	public String view()
	{
		if(!PrivilegeOperator.isView())
		{
			return NOPRIVILEGE;
		}
		
		changelog=changelogService.selectByPrimaryKey(id);
		
		UserResource resource= (UserResource) ServletActionContext.getRequest().getSession().getAttribute("resource");
		@SuppressWarnings("rawtypes")
		Map map=new HashMap();
		map.put("accessString", "table_id="+changelog.getTableId()+" and out_log='Y'");
		this.tableColumnList=tableColumnService.selectByExample(map);
		if(tableColumnList!=null)
		this.headerMap.put("username", resource.get("changelog.column.username"));
		this.headerMap.put("operateType",  resource.get("changelog.column.action"));
		this.headerMap.put("inDate", resource.get("changelog.column.operatedate"));
		//this.headerMap.put("requestUrl", resource.get("changelog.column.operatedate"));
		for(TableColumn tc: tableColumnList)
		{
			this.headerMap.put(tc.getColumnName(), tc.getColumnNameTr());
		}
		jsonToMap(changelog);
		FacesUtils.setValueInHashtableOfSession("tableColumnList", tableColumnList);
		return "view";
	}
	public String viewAll()
	{
		UserResource resource= (UserResource) ServletActionContext.getRequest().getSession().getAttribute("resource");
		if(!PrivilegeOperator.isView())
		{
			return NOPRIVILEGE;
		}
		changelog=this.changelogService.selectByPrimaryKey(changelog.getId());
		Map map=new HashMap();
		map.put("accessString", "pk="+changelog.getPk()+" and table_id="+changelog.getTableId());
		List<Changelog>changelogList=this.changelogService.selectByExample(map);
		tableColumnList=(List)FacesUtils.getValueInHashtableOfSession("tableColumnList");
		if(tableColumnList!=null)
		this.headerMap.put("username", resource.get("changelog.column.username"));
		this.headerMap.put("operateType", resource.get("changelog.column.action"));
		this.headerMap.put("inDate", resource.get("changelog.column.operatedate"));
		//this.headerMap.put("requestUrl", "请求地址");
		for(TableColumn tc: tableColumnList)
		{
			this.headerMap.put(tc.getColumnName(), tc.getColumnNameTr());
		}
		for(Changelog cl:changelogList)
		{
			jsonToMap(cl);
		}
		return "view";
	}
	@Override
	public List search() {
		Map<String,Object> param=new HashMap();
		SearchColumnList searchColumnList=(SearchColumnList)FacesUtils.getValueInHashtableOfSession("searchColumnList");
		if(searchColumnList!=null)
		{
			param.put("accessString", searchColumnList.buildSql());
		}
		param.put("orderByClause", " created desc ");
		
		return changelogService.selectByExample(param);
	}

	@Override
	public void createForm() {
		

	}

	@Override
	public String saveForm() {
		
		return null;
	}
	public void setTableColumnService(TableColumnService tableColumnService) {
		this.tableColumnService = tableColumnService;
	}

	public List<TableColumn> getTableColumnList() {
		return tableColumnList;
	}

	public void setTableColumnList(List<TableColumn> tableColumnList) {
		this.tableColumnList = tableColumnList;
	}
	private void jsonToMap(Changelog cl)
	{
		Gson gson=new Gson();
		Map<String,Object> valueMap=new HashMap<String,Object>();
		
		User user=userService.selectByPrimaryKey(cl.getUserId());
		valueMap.put("username", user!=null?user.getUsername():"");
		String operateType=cl.getOperateType();
		if("D".equals(operateType))
		{
			valueMap.put("operateType", "删除");
		}
		else if("I".equals(operateType))
		{
			valueMap.put("operateType", "新建");
		}
		else if("U".equals(operateType))
		{
			valueMap.put("operateType", "更新");
		}
		

		valueMap.put("inDate", DateTimeDecorator.dateToLongString(cl.getCreated()));
		valueMap.put("requestUrl", "");
		// JSONObject jsonObject=JSONObject.fromObject(cl.getContext());
		 Map<String,String> object=gson.fromJson(cl.getContext(), HashMap.class);
		 Set<String> set = object.keySet();
	        String key = null; 
	        Object value = null; 
	        Iterator<String> iterator=set.iterator();
	        while (iterator.hasNext()) 
	        { 
	            key = iterator.next(); 
	            value = object.get(key); 
	            valueMap.put(key, value); 
	        } 
	        this.bodyList.add(valueMap);
	}
}
