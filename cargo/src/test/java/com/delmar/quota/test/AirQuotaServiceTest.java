/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.quota.service.QuotaService;
import com.delmar.quota.service.busmodel.QuotaBusModel;
import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2014年12月25日 下午6:33:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AirQuotaServiceTest {
	@Autowired
	private QuotaService airQuotaService;
	@Autowired
	private UserService userService; 
	@Test
	public void testAirQuota()
	{
		User user=userService.getUserByUsername("fsk");
		QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", new BigDecimal(1000),new BigDecimal(0.15), null,null, null, "Air", 0);
		
		try {
			QuotaBusModelResult qbmr=airQuotaService.quote(quotaBusParam);
			Gson gson=new Gson();
			List<QuotaBusModel>  list=qbmr.getQuotaList();
			for(QuotaBusModel qbm:list)
			{
				System.out.println(gson.toJson(qbm));
			}
			
		} catch (QuotaParamException e) {
		
			e.printStackTrace();
		} catch (DataNotFondException e) {
			
			e.printStackTrace();
		}
	}
}
