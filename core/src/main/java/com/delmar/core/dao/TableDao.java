/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
     * 获取系统中所有的表
     * @return
     */
    List<String> getAllTableName();

    /**
     * 执行sql命令
     * @param sql
     */
    void execute(String sql);

    /**
     * 获取表注释信息
     * @param table
     * @return
     */
    String getCommentByTableName(String table);
}
