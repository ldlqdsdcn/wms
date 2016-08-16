/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.core.dao.CodemapinfoDao;

/**
 * @author 刘大磊 2014年12月18日 下午6:55:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CodemapinfoTest {
	@Autowired
	private CodemapinfoDao codemapinfoDao;
	@Test
	public void testSaveCodemapinfo()
	{
		List list=codemapinfoDao.selectByExample(null);
		System.out.println("list.size="+list.size());
	}
}
