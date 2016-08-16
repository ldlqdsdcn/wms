/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.model.Carrier;
import com.delmar.base.service.CarrierService;

/**
 * @author 刘大磊 2014年12月22日 下午1:28:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CarrierServiceTest {
	@Autowired
	private CarrierService carrierService;

	public void saveCarrier()
	{
		Carrier carrier=new Carrier();
		carrier.setCname("马士基");
		carrier.setCode("msj");
		carrier.setEname("msj");
		carrier.setPlaneocean("ocean");
		carrier.setRemark("test");
		//carrier.setScacode("msjtest");
		carrierService.save(carrier);
	}
	@Test
	public void testSearch()
	{
	List<Carrier> list=carrierService.selectByExample(null);
	for(Carrier carrier :list)
	{
		System.out.println(carrier.getCode()+" "+carrier.getCname()+" "+carrier.getEname());
	}
	System.out.println(list.size());
	}
}
