/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.core.dao.CodemapinfoDao;
import com.delmar.core.model.Codemapinfo;

/**
 * @author 刘大磊 2015年1月9日 上午9:51:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AopTest {
	@Autowired
	private CodemapinfoDao codemapinfoDao;
	@Test
	public void testAop()
	{
		System.out.println("执行方法所在线程："+Thread.currentThread().getId());
		Codemapinfo cmi=new Codemapinfo();
		cmi.setCode("ldltest");
		cmi.setMappedcode("ldltestmapped");
		cmi.setMappedname("刘大磊测试");
		cmi.setName("测试");
		cmi.setMaptype("test");
		codemapinfoDao.save(cmi);
		System.out.println("cmi.getId()="+cmi.getId());
	}
}
