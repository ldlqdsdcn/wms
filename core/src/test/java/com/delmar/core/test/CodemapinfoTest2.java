/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.delmar.core.dao.CodemapinfoDao;
import com.delmar.core.model.Codemapinfo;

/**
 * @author 刘大磊 2014年12月18日 下午7:10:48
 */
public class CodemapinfoTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		CodemapinfoDao codemapinfoDao=(CodemapinfoDao)ac.getBean("codemapinfoDao");
		
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
