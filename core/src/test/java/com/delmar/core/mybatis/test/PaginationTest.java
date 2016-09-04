package com.delmar.core.mybatis.test;

import com.delmar.core.dao.WindowDao;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by admin on 2016/9/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PaginationTest {
    @Autowired
    private WindowDao windowDao;
    @Test
    public void testPagination()
    {
       List list= windowDao.selectByPaging(null);
        Assert.assertTrue(list.size()>0);
    }
}
