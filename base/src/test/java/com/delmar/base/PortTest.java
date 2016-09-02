/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.base.dao.PortDao;
import com.delmar.base.enumdef.ModeType;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.Port;
import com.delmar.base.service.PortService;

/**
 * @author 刘大磊 2014年12月23日 下午1:57:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class PortTest {
	@Autowired
	PortService portService;
	@Autowired
	PortDao portDao;
	@Test
	public void testPortSelect()
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("mode", ModeType.Air.toString());
		List<Port> portList=portDao.selectPortByMode(param);
		for(Port p:portList)
		{
			System.out.println(p.getCountryCode()+" "+p.getPortcname());
		}
		System.out.println(portList.size());
	}
}
