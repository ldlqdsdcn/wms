/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import com.delmar.core.dao.TableDao;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.ForeignKey;
import com.delmar.core.dto.UniqueIndexDto;
import com.delmar.core.excep.DataBaseException;
import com.delmar.core.model.Table;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String DATA_TYPE="DATA_TYPE";
    private static final String COLUMN_SIZE = "COLUMN_SIZE";
    private static final String DECIMAL_DIGITS = "DECIMAL_DIGITS";
    private static final String NULLABLE = "NULLABLE";
    private static final String REMARKS="REMARKS";
    private static final String COLUMN_DEF="COLUMN_DEF";

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
        Map<String, String> param = new HashMap<>();
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
        String primaryKey="";
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
                primaryKey=indexName;

            }

        } catch (Exception e) {
            throw new DataBaseException("获取 PrimaryKey失败", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return primaryKey;
    }
    public String getTableRemark(String tableName)
    {
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String[] types={ "TABLE" };
            ResultSet rs= databaseMetaData.getTables(null,null,tableName,types );

                    ResultSetMetaData rsmd=rs.getMetaData();

            int count= rsmd.getColumnCount();
            while (rs.next())
            {
                for(int i=1;i<=count;i++)
                {
                    System.out.print(" "+rsmd.getColumnName(i)+" "+rsmd.getColumnTypeName(i)+" "+rs.getString(i));
                }
                System.out.println(rs.getString("REMARKS"));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new DataBaseException("获取 表信息失败", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return "";
    }
    public List<UniqueIndexDto> getUniqueIndex(String tableName) {
        List<UniqueIndexDto> list=new ArrayList<>();

        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getIndexInfo(null, null, tableName, true, true);
            while (rs.next()) {
                String indexName = rs.getString(INDEX_COLUMN_NAME);
                String columnName = rs.getString(INDEX_NAME);
                UniqueIndexDto uniqueIndexDto=new UniqueIndexDto();
                uniqueIndexDto.setIndexColumnName(columnName);
                uniqueIndexDto.setIndexName(indexName);
                list.add(uniqueIndexDto);
            }

        } catch (Exception e) {
            throw new DataBaseException("获取 Index失败", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return list;
    }

    public List<ForeignKey> getExportedKeys(String tableName) {
        List<ForeignKey> list=new ArrayList<>();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getExportedKeys(null, null, tableName);
            while (rs.next()) {

                ForeignKey foreignKey=getForeignKeyByResultSet(rs);
                list.add(foreignKey);
            }
        } catch (SQLException e) {
            throw new DataBaseException("获取 外部引用键值失败", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return list;
    }

    public List<ForeignKey> getImportedKeys(String tableName) {
       List<ForeignKey> list=new ArrayList<>();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        try {

            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet rs = databaseMetaData.getImportedKeys(null, null, tableName);
            while (rs.next()) {
                ForeignKey foreignKey=getForeignKeyByResultSet(rs);
                list.add(foreignKey);
            }
        } catch (SQLException e) {
            throw new DataBaseException("获取 引用外部键值失败", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return list;
    }
    private ForeignKey getForeignKeyByResultSet(ResultSet rs)
    {
        ForeignKey foreignKey=new ForeignKey();

        try {
            String fkColumn = rs.getString(EXPORT_KEY_FK_COLUMN_NAME);
            String fkTable = rs.getString(EXPORT_KEY_FK_TABLE_NAME);
            String pkColumn = rs.getString(EXPORT_KEY_PK_COLUMN_NAME);
            String pkTable = rs.getString(EXPORT_KEY_PK_TABLE_NAME);
            String fkName = rs.getString(EXPORT_KEY_FK_NAME);
            String pkName = rs.getString(EXPORT_KEY_PK_NAME);
            foreignKey.setFkColumnName(fkColumn);
            foreignKey.setFkTableName(fkTable);
            foreignKey.setPkColumnName(pkColumn);
            foreignKey.setPkTableName(pkTable);
            foreignKey.setFkName(fkName);
            foreignKey.setPkName(pkName);
        } catch (SQLException e) {
            throw new DataBaseException("获取 外部键值失败", e);
        }

        return foreignKey;
    }
    public List<ColumnMetaDataDto> getTableColumns(String tableName) {
        List<ColumnMetaDataDto> list=new ArrayList<>();
        Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();

        try {
            //System.out.println("获取 index");
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            String columnName;
            String columnType;
            ResultSet colRet = databaseMetaData.getColumns(null, "%", tableName, "%");
            while (colRet.next()) {
                ColumnMetaDataDto columnMetaDataDto=new ColumnMetaDataDto();
                columnName = colRet.getString(COLUMN_NAME);
                columnType = colRet.getString(TYPE_NAME);
                 Integer dataType=colRet.getInt(DATA_TYPE);
                int column_size = colRet.getInt(COLUMN_SIZE);
                int digits = colRet.getInt(DECIMAL_DIGITS);
                int nullable = colRet.getInt(NULLABLE);
                String remarks=colRet.getString(REMARKS);
                String colDef=colRet.getString(COLUMN_DEF);
                columnMetaDataDto.setDataType(dataType);
                columnMetaDataDto.setColumnName(columnName);
                columnMetaDataDto.setType(columnType);
                columnMetaDataDto.setColumnSize(column_size);
                columnMetaDataDto.setDecimalDigits(digits);
                columnMetaDataDto.setNullable(nullable == 1);
                columnMetaDataDto.setRemarks(remarks);
                columnMetaDataDto.setColumnDefault(colDef);
                list.add(columnMetaDataDto);
               // System.out.println(columnMetaDataDto.toString());
//                for(int i=1;i<resultSetMetaData.getColumnCount();i++)
//                {
//                    System.out.print(resultSetMetaData.getColumnName(i)+" "+resultSetMetaData.getColumnLabel(i)+" "+colRet.getObject(i)+"   ");
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("获取数据库列信息异常", e);
        }
        try {
            conn.close();
        } catch (SQLException e) {
            throw new DataBaseException("关闭数据连接失败", e);
        }
        return list;
    }
    public  List   getAllTableName() {
        List<String>   tables   =   new   ArrayList();
        try {
            Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();


            DatabaseMetaData   dbMetaData   =   conn.getMetaData();

            //可为:"TABLE",   "VIEW",   "SYSTEM   TABLE",
            //"GLOBAL   TEMPORARY",   "LOCAL   TEMPORARY",   "ALIAS",   "SYNONYM"
            String[]   types   =   {"TABLE"};

            ResultSet   tabs   =   dbMetaData.getTables(null,   null,   null,types/*只要表就好了*/);
            while(tabs.next()){
                //只要表名这一列
                tables.add(tabs.getString("TABLE_NAME"));

            }
            tabs.close();
            conn.close();
        } catch (SQLException e) {
            logger.error("获取所有表目录异常",e);
            throw new DataBaseException("获取数据库列信息异常", e);
        }
        logger.debug(tables);
        return   tables;

    }

    @Override
    public void execute(String sql) {
        try {
            Connection conn = this.sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
            Statement statement=conn.createStatement();
            statement.execute(sql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            logger.error("执行sql语句异常"+sql,e);
            throw new DataBaseException("sql执行异常："+sql, e);
        }
    }

}
