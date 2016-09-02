/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.delmar.core.model.EaModelContent;
import com.delmar.core.model.SearchColumnList;
import com.delmar.core.model.Table;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.TableColumnService;
import com.delmar.core.service.TableService;
import com.delmar.core.web.util.FacesUtils;

/**
 * @author 刘大磊 2015年1月20日 上午10:22:59
 */
public class TableAction extends CoreEditAction {

	private TableService tableService;
	private TableColumnService tableColumnService;
	private Table table=new Table();
	private List<TableColumn> columns=new ArrayList<TableColumn>();
	@Override
	public void editForm() {
		if(id==null||id==0)
		{
			table=new Table();
			columns=new ArrayList<TableColumn>();
		}
		else
		{
			table=this.tableService.selectByPrimaryKey(id);
			Map map=new HashMap();
			map.put("accessString", "table_id="+id);
			columns=tableColumnService.selectByExample(map);
		}
		

	}
	public String addColumn()
	{
		TableColumn column=new TableColumn();
		columns.add(column);
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
			TableColumn column=columns.get(index);
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
	public String addTableByWizard()
	{
		return  "wizard";
	}
	public String saveTableByWizard()
	{
		return "wizard";
	}

	public String save()
	{
		table=tableService.saveTable(table, columns);
		id=table.getId();
		edit();
		if("Y".equals(table.getOutLog()))
		{
			List<TableColumn> tableColumnList=new ArrayList<TableColumn>();
			if(this.columns!=null)
			{
				for(TableColumn tc:columns)
				{
					if("Y".equals(tc.getOutLog()))
					{
						tableColumnList.add(tc);
					}
				}
			}
			table.setTableColumnList(tableColumnList);
			EaModelContent.logTableMap.put(table.getClassName(), table);
		}	
		FacesUtils.getSession().setAttribute("msg", "保存表信息成功！");
		return "edit";
	}
	@Override
	public String getModuleName() {
		
		return "table";
	}

	@Override
	public List search() {
		Map example=new HashMap();
		SearchColumnList searchColumnList=(SearchColumnList)FacesUtils.getValueInHashtableOfSession("searchColumnList");
		if(searchColumnList!=null)
		{
			//example.set("accessString", searchColumnList.buildSql());
			//example.setAccessString();
			example.put("accessString", searchColumnList.buildSql());
		}
		//Object param=FacesUtils.getValueInHashtableOfSession("table_param");
		
		
		return tableService.selectByExample(example);
	}

	public void setTableService(TableService tableService) {
		this.tableService = tableService;
	}

	@Override
	public void deleteList(Integer[] ids) {
		tableService.deleteTables(ids);
		
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<TableColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<TableColumn> columns) {
		this.columns = columns;
	}
	@Override
	public String delete() {
		tableService.deleteByPrimaryKey(this.table.getId());
		FacesUtils.getSession().setAttribute("msg", "删除表信息成功！");
		return list();
	}
	public void setTableColumnService(TableColumnService tableColumnService) {
		this.tableColumnService = tableColumnService;
	}
	@Override
	public Integer getModelId() {
		
		return table.getId();
	}

}
