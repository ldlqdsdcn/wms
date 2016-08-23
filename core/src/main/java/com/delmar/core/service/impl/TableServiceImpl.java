/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.api.ApiResult;
import com.delmar.core.api.StatusCode;
import com.delmar.core.def.ColumnDataType;
import com.delmar.core.def.FieldType;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.TableMetaDataDto;
import com.delmar.core.dto.UniqueIndexDto;
import com.delmar.core.excep.DataBaseException;
import com.delmar.core.excep.ValidateException;
import com.delmar.utils.DmLog;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

	public ApiResult<TableMetaDataDto> getTableDescription(String tableName) {
		TableMetaDataDto tableMetaDataDto= null;
		try {
			log.debug(tableName);
			tableMetaDataDto = new TableMetaDataDto();
			tableMetaDataDto.setName(tableName);
			String primaryKey=tableDao.getPrimaryKey(tableName);
			tableMetaDataDto.setPkColumn(primaryKey);
			List<UniqueIndexDto> uniqueIndexList=tableDao.getUniqueIndex(tableName);
			tableMetaDataDto.setUniqueKeyList(uniqueIndexList);
			log.debug("primaryKey="+primaryKey);
			tableMetaDataDto.setExportedFK(tableDao.getExportedKeys(tableName));
			tableMetaDataDto.setImportedFK(tableDao.getExportedKeys(tableName));
			List<ColumnMetaDataDto> columns=tableDao.getTableColumns(tableName);
			FieldType[] columnDataTypes= FieldType.values();
			for(ColumnMetaDataDto dto:columns)
			{
					for(FieldType type:columnDataTypes)
					{

						if(type.getDesc().equals(dto.getType()))
						{
							dto.setDataType(type.getType());
						}
					}
			}

			tableMetaDataDto.setColumnList(columns);


			log.debug("columns="+columns);
		} catch (DataBaseException e) {
			return ApiResult.fail(StatusCode.BUSINESS_EXCEPTION.getCode(),e.getMessage());
		}
		return ApiResult.success(tableMetaDataDto);
	}

	@Override
	public void saveTableInfoByWizard(TableMetaDataDto tableMetaDataDto) {
		Map<String,String> param=new HashMap<>();
		param.put("name",tableMetaDataDto.getName().trim());
		List<Table> tableList=tableDao.selectByExample(param);
		if(tableList!=null&&tableList.size()>0)
		{
			throw new ValidateException("该表已经存在，不能重复创建");
		}
	}


}
