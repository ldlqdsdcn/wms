/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.quota.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.enumdef.ModeType;
import com.delmar.base.model.Datadict;
import com.delmar.quota.service.QuotaService;
import com.delmar.quota.service.busmodel.QuotaBusModelResult;
import com.delmar.quota.service.busmodel.QuotaBusParam;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;
import com.delmar.sys.model.User;
import com.delmar.sys.service.UserService;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2014年12月30日 上午10:00:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class LandQuotaServiceTest {
	@Autowired
	private QuotaService landQuotaService;
	@Autowired
	private UserService userService;
	@Test
	public void testLandQuota()
	{
		Gson gson=new Gson();
		User user=userService.getUserByUsername("fsk");
		//海运FCL
		//QuotaBusParam quotaBusParam=new QuotaBusParam(user, "DKAAL", "DKAAR", "", new BigDecimal(1000),new BigDecimal(0.15), null, null, "Air", 0);
		QuotaBusParam quotaBusParam=new QuotaBusParam(user, "CNPEK", "CNTAO", "", null,null, null,"5TON",null, ModeType.Land.toString(), 3);
		try {
			QuotaBusModelResult qbmr=landQuotaService.quote(quotaBusParam);
			System.out.println(gson.toJson(qbmr));
		} catch (QuotaParamException e) {
			e.printStackTrace();
		} catch (DataNotFondException e) {
			e.printStackTrace();
		}
	}
}
