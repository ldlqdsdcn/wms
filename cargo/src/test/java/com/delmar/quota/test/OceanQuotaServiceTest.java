/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.quota.service.QuotaService;
import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2014年12月25日 下午8:35:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OceanQuotaServiceTest {
@Autowired
private	QuotaService oceanQuotaService;
@Autowired
private UserService userService; 
	
	public void testFCLQuota()
	{
		User user=userService.getUserByUsername("fsk");
		//海运FCL
		//QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", new BigDecimal(1000),new BigDecimal(0.15), null, null, "Air", 0);
		QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", null,null, "40'",null,"FCL", "Ocean", 30);
		try {
			Gson gson=new Gson();
			QuotaBusModelResult qbmr=oceanQuotaService.quote(quotaBusParam);
			System.out.println(gson.toJson(qbmr));
		} catch (QuotaParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotFondException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testLCLQuota()
	{
		User user=userService.getUserByUsername("fsk");
		//海运FCL
		//QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", new BigDecimal(1000),new BigDecimal(0.15), null, null, "Air", 0);
		QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", new BigDecimal(5000),new BigDecimal(5),null, "LCL", "LCL", "Ocean", 0);
		try {
			Gson gson=new Gson();
			QuotaBusModelResult qbmr=oceanQuotaService.quote(quotaBusParam);
			System.out.println(gson.toJson(qbmr));
		} catch (QuotaParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotFondException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
