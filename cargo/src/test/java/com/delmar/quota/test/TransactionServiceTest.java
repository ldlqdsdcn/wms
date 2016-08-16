/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.corebus.model.TransactionWithBLOBs;
import com.delmar.corebus.service.TransactionService;

/**
 * @author 刘大磊 2014年12月26日 下午1:16:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TransactionServiceTest {
	@Autowired
	TransactionService transactionService;
	@Test
	public void testTransaction()
	{
		
		
		System.out.println(transactionService.generateDoccode("01"));
	}
	public void testsaveTransaction()
	{
		TransactionWithBLOBs tran=new TransactionWithBLOBs();
		tran.setCreated(new Date());
		tran.setCreatedBy(-1);
		tran.setrRateMasterId(80);
		tran.setTransno(transactionService.generateDoccode("01"));
		tran.setUpdated(new Date());
		tran.setUpdatedBy(-1);
		tran.setTranparam("11111111111111");
		tran.setTransresult("22222222222");
		transactionService.save(tran);
		
		System.out.println(tran.getId());
	}
}
