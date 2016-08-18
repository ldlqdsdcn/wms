/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.excep.DataBaseException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;

import com.delmar.core.dao.TableDao;
import com.delmar.core.model.Table;

/**
 * @author 刘大磊 2015-01-09 10:48:25
 */
@Repository("tableDao")
public class TableDaoMybatis extends CoreDaoMyBatis<Table> implements TableDao {
    private static final String INDEX_NAME = "INDEX_NAME";
    private static final String INDEX_COLUMN_NAME = "COLUMN_NAME";
    private static final String PRIMARY_KEY_KEY_SEQ = "KEY_SEQ";

    private static final String EXPORT_KEY_PK_TABLE_NAME = "PKTABLE_NAME";
    private static final String EXPORT_KEY_PK_COLUMN_NAME = "PKCOLUMN_NAME";
    private static final String EXPORT_KEY_FK_TABLE_NAME = "FKTABLE_NAME";
    private static final String EXPORT_KEY_FK_COLUMN_NAME = "FKCOLUMN_NAME";
    private static final String EXPORT_KEY_FK_NAME = "FK_NAME";
    private static final String EXPORT_KEY_PK_NAME = "PK_NAME";

    private static final String COLUMN_NAME = "COLUMN_NAME";
    private static final String TYPE_NAME = "TYPE_NAME";
    private static final String DATA_TYPE = "DATA_TYPE";
    private static final String COLUMN_SIZE = "COLUMN_SIZE";
    private static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    private static final String NULLABLE = "NULLABLE";

    /* (non-Javadoc)
     * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
     */
    @Override
    protected String getSqlName() {

        return "com.delmar.core.mybatis.sql.TableMapper";
    }

    /* (non-Javadoc)
     * @see com.delmar.core.dao.TableDao#getTablebyClassName(java.lang.String)
     */
    public Table getTablebyClassName(String className) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("className", className);
        List<Table> tableList = this.selectByExample(param);
        if (tableList != null && tableList.size() > 1) {
            return tableList.get(0);
        }
        return null;
    }

    /**
     * @param tableName
     * @return Json String
     */
    public String getPrimaryKey(String tableName) {
        JsonObject jsonObject = new JsonObject();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getPrimaryKeys(null, null, tableName);
            while (rs.next()) {

                String indexName = rs.getString(INDEX_COLUMN_NAME);
                String keySeq = rs.getString(PRIMARY_KEY_KEY_SEQ);
                jsonObject.addProperty(INDEX_COLUMN_NAME, indexName);
                jsonObject.addProperty(PRIMARY_KEY_KEY_SEQ, keySeq);

            }

        } catch (Exception e) {
            throw new DataBaseException("获取 PrimaryKey失败", e);
        }
        return jsonObject.toString();
    }

    public String getUniqueIndex(String tableName) {
        JsonArray jsonArray = new JsonArray();

        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getIndexInfo(null, null, tableName, true, true);
            while (rs.next()) {
                JsonObject jsonObject = new JsonObject();
                String indexName = rs.getString(INDEX_COLUMN_NAME);
                String columnName = rs.getString(INDEX_NAME);
                jsonObject.addProperty(INDEX_COLUMN_NAME, indexName);
                jsonObject.addProperty(INDEX_NAME, columnName);
                jsonArray.add(jsonObject);
            }

        } catch (Exception e) {
            throw new DataBaseException("获取 Index失败", e);
        }
        return jsonArray.toString();
    }

    public String getExportedKeys(String tableName) {
        JsonArray jsonArray = new JsonArray();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getExportedKeys(null, null, tableName);
            while (rs.next()) {

                JsonObject jsonObject = new JsonObject();
                String fkColumn = rs.getString(EXPORT_KEY_FK_COLUMN_NAME);
                String fkTable = rs.getString(EXPORT_KEY_FK_TABLE_NAME);
                String pkColumn = rs.getString(EXPORT_KEY_PK_COLUMN_NAME);
                String pkTable = rs.getString(EXPORT_KEY_PK_TABLE_NAME);
                String fkName = rs.getString(EXPORT_KEY_FK_NAME);
                String pkName = rs.getString(EXPORT_KEY_PK_NAME);

                jsonObject.addProperty(EXPORT_KEY_FK_COLUMN_NAME, fkColumn);
                jsonObject.addProperty(EXPORT_KEY_FK_TABLE_NAME, fkTable);
                jsonObject.addProperty(EXPORT_KEY_PK_COLUMN_NAME, pkColumn);
                jsonObject.addProperty(EXPORT_KEY_PK_TABLE_NAME, pkTable);
                jsonObject.addProperty(EXPORT_KEY_FK_NAME, fkName);
                jsonObject.addProperty(EXPORT_KEY_PK_NAME, pkName);
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            throw new DataBaseException("获取 外部引用键值失败", e);
        }
        return jsonArray.toString();
    }

    public String getImportedKeys(String tableName) {
        JsonArray jsonArray = new JsonArray();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getImportedKeys(null, null, tableName);
            while (rs.next()) {
                JsonObject jsonObject = new JsonObject();
                String fkColumn = rs.getString(EXPORT_KEY_FK_COLUMN_NAME);
                String fkTable = rs.getString(EXPORT_KEY_FK_TABLE_NAME);
                String pkColumn = rs.getString(EXPORT_KEY_PK_COLUMN_NAME);
                String pkTable = rs.getString(EXPORT_KEY_PK_TABLE_NAME);
                String fkName = rs.getString(EXPORT_KEY_FK_NAME);
                String pkName = rs.getString(EXPORT_KEY_PK_NAME);

                jsonObject.addProperty(EXPORT_KEY_FK_COLUMN_NAME, fkColumn);
                jsonObject.addProperty(EXPORT_KEY_FK_TABLE_NAME, fkTable);
                jsonObject.addProperty(EXPORT_KEY_PK_COLUMN_NAME, pkColumn);
                jsonObject.addProperty(EXPORT_KEY_PK_TABLE_NAME, pkTable);
                jsonObject.addProperty(EXPORT_KEY_FK_NAME, fkName);
                jsonObject.addProperty(EXPORT_KEY_PK_NAME, pkName);
                jsonArray.add(jsonObject);
            }
        } catch (SQLException e) {
            throw new DataBaseException("获取 引用外部键值失败", e);
        }
        return jsonArray.toString();
    }

    public String getTableColumns(String tableName) {
        JsonArray jsonArray = new JsonArray();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();

        try {
            System.out.println("获取 index");
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String columnName;
            String columnType;
            ResultSet colRet = databaseMetaData.getColumns(null, "%", tableName, "%");
            while (colRet.next()) {
                JsonObject jsonObject = new JsonObject();
                columnName = colRet.getString(COLUMN_NAME);
                columnType = colRet.getString(TYPE_NAME);
                int column_size = colRet.getInt(COLUMN_SIZE);
                int digits = colRet.getInt(DECIMAL_DIGITS);
                int nullable = colRet.getInt(NULLABLE);
                jsonObject.addProperty(COLUMN_NAME, columnName);
                jsonObject.addProperty(TYPE_NAME, columnType);
                jsonObject.addProperty(COLUMN_SIZE, column_size);
                jsonObject.addProperty(DECIMAL_DIGITS, digits);
                jsonObject.addProperty(NULLABLE, nullable);
                jsonObject.addProperty(DATA_TYPE, colRet.getInt(DATA_TYPE));
                jsonArray.add(jsonObject);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("获取数据库列信息异常", e);
        }
        return jsonArray.toString();
    }


}
