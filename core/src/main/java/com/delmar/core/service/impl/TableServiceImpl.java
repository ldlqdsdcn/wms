/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.dao.TableColumnDao;
import com.delmar.core.dao.TableDao;
import com.delmar.core.model.Table;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.TableService;

/**
 * @author 刘大磊 22015-01-09 10:48:25
 */
@Service("tableService")
public class TableServiceImpl extends CoreServiceImpl<Table> implements
		TableService {
	@Autowired
	private TableDao tableDao;
	
	@Autowired
	private TableColumnDao tableColumnDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Table> getCoreDao() {
		return tableDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.TableService#deleteTables(java.lang.Integer[])
	 */
	public void deleteTables(Integer[] ids) {
		if(ids!=null)
			for(Integer id:ids)
			{
				tableDao.deleteByPrimaryKey(id);
			}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.TableService#saveTable(com.delmar.core.model.Table, java.util.List)
	 */
	public Table saveTable(Table table, List<TableColumn> columns) {
		Integer id=tableDao.save(table);
		for(TableColumn column:columns)
		{
			column.setTableId(id);
			tableColumnDao.save(column);
		}
		return table;
	}

	
}
