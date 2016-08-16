package com.delmar.cargo.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.ReportFareInvoiceDao;
import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.cargo.service.ReportFareInvoiceService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:28:29 
 * 类说明 
 */
@Service("reportFareInvoiceService")
public class ReportFareInvoiceServiceImpl extends CoreHibernateServiceImpl<ReportFareInvoice> implements ReportFareInvoiceService {

	@Autowired
	private ReportFareInvoiceDao reportFareInvoiceDao;
	

	
	
	@Override
	public CoreHibernateDao<ReportFareInvoice> getCoreDao()
	{
	   return reportFareInvoiceDao;	
	}
	
	public BigDecimal getDebitBalance(String trustFileCode)
	{
		return reportFareInvoiceDao.getDebitBalance(trustFileCode);
	}

}
