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
    //client_id org_id user_id role_id module_id javabean_id,menu_id,menu_id,operator_id,page_id,module_role_id,scheduled_id,usergroup_id,createdby,updatedby,datadict_id,table_id,label_id,message_id eventtype_id
    private String key="eventtype_id";
    private String fk_table="base_eventtype";
    private String fk_table_pk="id";
    @Test
    public void testGetTableColumns()
    {
        tableDao.getTableColumns("core_table");
    }
}
