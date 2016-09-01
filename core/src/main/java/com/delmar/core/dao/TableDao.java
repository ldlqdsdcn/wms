/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.dao;

import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.ForeignKey;
import com.delmar.core.dto.UniqueIndexDto;
import com.delmar.core.model.Table;
import com.delmar.core.dao.CoreDao;

import java.util.List;

/**
 * @author 刘大磊 2015-01-09 10:48:25
 */
public interface TableDao extends CoreDao<Table> {
    Table getTablebyClassName(String className);

    /**
     * 获取表的所有唯一索引
     *
     * @param tableName
     * @return
     */
    List<UniqueIndexDto> getUniqueIndex(String tableName);

    /**
     * 获取表的主键
     *
     * @param tableName
     * @return
     */
    String getPrimaryKey(String tableName);

    /**
     * @param tableName
     * @return
     */
    List<ForeignKey> getExportedKeys(String tableName);

    List<ForeignKey> getImportedKeys(String tableName);

    List<ColumnMetaDataDto> getTableColumns(String tableName);

    /**
     * 获取表备注信息
     *
     * @param tableName
     * @return
     */
    String getTableRemark(String tableName);
}
