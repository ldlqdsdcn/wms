
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import java.util.List;

import com.delmar.core.api.ApiResult;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.model.Table;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-09 10:48:25
 */
public interface TableService extends CoreService<Table> {

	/**
	 * @param ids
	 */
	void deleteTables(Integer[] ids);

	/**
	 * @param table
	 * @param columns
	 * @return
	 */
	Table saveTable(Table table, List<TableColumn> columns);

	ApiResult<TableMetaDataDto> getTableDescription(String tableName);

	 void saveTableInfoByWizard(TableMetaDataDto tableMetaDataDto);

}