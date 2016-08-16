/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.base;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.model.City;
import com.delmar.base.service.CityService;

/**
 * @author 刘大磊 2014年12月29日 上午9:55:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CityTest {
@Autowired
CityService cityService;
@Test
public void testSelectList()
{
	List<City> list=cityService.selectByExample(null);
	for(City city:list)
	{
		System.out.println(city.getCode()+" "+city.getCname()+"  "+city.getEname());
	}
}
}
