/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.corebus.service.EBusinessService;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;

/**
 * @author 刘大磊 2014年12月26日 上午10:59:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class EBusinessServiceTest {
	@Autowired
	private EBusinessService eBusinessService;
	@Test
	public void testEBusinessSave() throws QuotaParamException, DataNotFondException
	{
	//	eBusinessService.saveEBusiness("DM022014120001");
	}
}
