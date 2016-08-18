package com.delmar.core.test;

import com.delmar.core.service.TableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by admin on 2016/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TableServiceTest {
    @Autowired
    private TableService tableService;
    @Test
    public void testGetTableInfo()
    {

        tableService.getTableDescription("core_table_column");

    }
}
