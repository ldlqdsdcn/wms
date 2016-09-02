/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.core.service.LanguageService;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2015年7月28日 上午11:20:06
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AdviceExceptionTest {
	final DmLog log=DmLog.getLogger(AdviceExceptionTest.class);
	@Autowired
	LanguageService peopleService;
	@Test
	public void testAdviceException()
	{
		log.debug("test begin");
		try
		{
		peopleService.update(null);
		}
		catch(Exception e)
		{
			
		//	log.error(e.getMessage(), e);
		}
		log.debug("test end");
	}
}
