package com.delmar.system;

import com.delmar.core.dao.WindowDao;
import com.delmar.sys.dao.UserSessionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/9/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserSessionTest {
    @Autowired
    private UserSessionDao userSessionDao;
    @Test
    public void testPagination()
    {
        Map<String,Object> param=new HashMap<>();
        param.put("pageNo",2);
        param.put("pageSize",15);
        List list= userSessionDao.selectByExample(param);
        System.out.println(list.size());
        List list2= userSessionDao.selectByExample(null);
    }
}
