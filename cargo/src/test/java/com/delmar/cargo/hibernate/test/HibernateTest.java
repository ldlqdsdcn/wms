/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.cargo.hibernate.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.delmar.cargo.dao.CityDao;
import com.delmar.cargo.dao.hibernate.BusinessDaoHibernate;
import com.delmar.cargo.dao.hibernate.ReportFareInvoiceDaoHibernate;
import com.delmar.cargo.dao.hibernate.TrustContextDaoHibernate;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.core.model.HbnHsql;

/**
 * @author 刘大磊 2015年4月27日 下午2:50:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class HibernateTest {
	@Autowired
	private CityDao cargoCityDao;
	
	@Autowired
	private TrustContextDaoHibernate trustContextDao;
	
	@Autowired
	private ReportFareInvoiceDaoHibernate reportFareInvoiceDao;	
	
	@Autowired
	private BusinessDaoHibernate businessDaoHibernate;		
	
	/*
	@Test
	public void testGetObject2()
	{
		
		CityInfo obj=cargoCityDao.getObject(new Integer(16));
		
		System.out.println(obj.getName() );
	}	
	
	
	
	@Test
	public void testFind()
	{
		List<CityInfo> cityList=cargoCityDao.getCityList();
		for(CityInfo city:cityList)
		{
			System.out.println(city.getCname());
		}
		System.out.println(cityList.size());
	}
	
	@Test
	public void testGetObject()
	{
		CriteriaH ch=new CriteriaH(CityInfo.class);
		ch.addField("cname", "like", "%CHON%");
		
		List<CityInfo> cityList=cargoCityDao.search(ch);
		for(CityInfo city:cityList)
		{
			System.out.println(city.getCname());
		}
		System.out.println(cityList.size());
		Assert.assertTrue(cityList.size()>0);
	}
	
  */
	
	
	public void testGetObject()
	{
		/*
		CriteriaH ch=new CriteriaH(ReportFareInvoice.class);
		ch.addField("invoiceNo", "=", "B1409221009");
		
		ReportFareInvoice oneInvoice=reportFareInvoiceDao.getObject("B1409221009");
		
		System.out.println(oneInvoice.getCusCodeName());
		
		System.out.println(oneInvoice.getCusCode());
	
			*/
	   List<BusinessForwarder> list=trustContextDao.getBusForwarder("2015-03-21");
	   
	   System.out.println(list.size());
	   
	   System.out.println(list.get(0).getFileNo());
	   
	   

	}	
	
	
	@Test
	public void testTransformer()
	{

		
	   HbnHsql sqlwhere=new HbnHsql();
	   sqlwhere.addWhereCell("b.flightDate", "2015-03-21", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ, HbnHsql.REL_TYPE_AND);
	   
	   List<BusinessInvoice> list=businessDaoHibernate.searchBusinessInvoiceList(sqlwhere,"Other");
	   
	   System.out.println(list.size());
	   
	   System.out.println(list.get(0).getFileNo());
	   
	   

	}	
	
	
	
}
