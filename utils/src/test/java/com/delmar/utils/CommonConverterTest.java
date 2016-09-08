package com.delmar.utils;

import com.delmar.utils.model.User;
import com.delmar.utils.model.UserVo;
import junit.framework.Assert;
import org.junit.Test;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/5.
 */
public class CommonConverterTest {
    @Test
    public void testConvertObject()
    {
        User user=new User();
        user.setUsername("allen");
        user.setPassword("123456");
        UserVo userVo=CommonConverter.convertObject(user, UserVo.class);
        Assert.assertNotNull(userVo);
        Assert.assertEquals(userVo.getPassword(),user.getPassword());
    }
    public void testConvertList()
    {
        List<User> userList=new ArrayList<>();
        User user=new User();
        user.setUsername("allen");
        user.setPassword("1234567");
        userList.add(user);
        User user2=new User();
        user2.setUsername("Paul");
        user2.setPassword("123457");
        userList.add(user2);

    }
}
