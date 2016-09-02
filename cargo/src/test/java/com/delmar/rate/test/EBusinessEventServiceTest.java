/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.rate.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.corebus.service.EBusinessEventService;

/**
 * @author 刘大磊 2014年12月31日 上午9:59:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EBusinessEventServiceTest {
	@Autowired
	private EBusinessEventService eBusinessEventService;
	
	@Test
	public void testSearch()
	{
		List list=eBusinessEventService.selectByExample(null);
		System.out.println(list.size());
	}
}
