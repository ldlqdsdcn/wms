/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;

/**
 * @author 刘大磊 2014年12月23日 下午2:55:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {
	@Autowired
	UserService userService;
	@Test
	public void testGetuserByusername()
	{
		User user=userService.getUserByUsername("allen");
		System.out.println(user.getName()+" "+user.getPassword());
		/*Assert.assertEquals("刘大磊", user.getName());
		Assert.assertEquals("刘大磊", user.getRemark());*/

	}
	
	public void testUserSelect()
	{
		List<User>list=userService.selectByExample(null);
		for(User u:list)
		{
			System.out.println(u.getName());
		}
	}
}
