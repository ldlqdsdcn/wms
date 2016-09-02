package com.delmar.core.test;

import com.delmar.core.dao.TableDao;
import com.delmar.core.dto.ColumnMetaDataDto;
import com.delmar.core.dto.ForeignKey;
import com.delmar.utils.DmLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by admin on 2016/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TableDaoTest {
    DmLog log=DmLog.getLogger(TableDaoTest.class);
    @Autowired
    private TableDao tableDao;
    //client_id org_id user_id role_id module_id javabean_id,menu_id,menu_id,operator_id,page_id,module_role_id,scheduled_id,usergroup_id,createdby,updatedby
    private String key="updatedby";
    private String fk_table="sys_user";
    private String fk_table_pk="id";
    @Test
    public void testAddForeignKeys()
    {

      List<String> allTables=  tableDao.getAllTableName();
        for(String table:allTables)
        {
            modifyOneTableForeign(table);
        }
    }

    private void modifyOneTableForeign(String tableName)
    {
        List<ForeignKey>  foreignKeyList= tableDao.getImportedKeys(tableName);
        for(ForeignKey foreignKey:foreignKeyList)
        {
            if(foreignKey.getFkColumnName().equals(key))
            {
                return;
            }
        }
        List<ColumnMetaDataDto>  columnMetaDataDtoList= tableDao.getTableColumns(tableName);
        boolean hasKey=false;
        for(ColumnMetaDataDto cmdd:columnMetaDataDtoList)
        {
            if(cmdd.getColumnName().equalsIgnoreCase(key))
            {
                hasKey=true;
                break;
            }
        }
        if(hasKey)
        {
            execSql(tableName);
        }
    }
    private void execSql(String tableName)
    {
        System.out.println("------------>"+tableName);
        if(tableName.equals(fk_table))
        {
            return;
        }
        String sql1="ALTER TABLE "+tableName+"    ADD INDEX `"+tableName+"_"+key+"_idx` (`"+key+"` ASC)";
        String sql2="ALTER TABLE "+tableName+" " +
                "ADD CONSTRAINT `fk_"+tableName+"_"+key+"`" +
                "  FOREIGN KEY (`"+key+"`)" +
                "  REFERENCES "+fk_table+" (`"+fk_table_pk+"`)" +
                "  ON DELETE NO ACTION" +
                "  ON UPDATE NO ACTION";
        tableDao.execute(sql1);
        tableDao.execute(sql2);
    }
}
