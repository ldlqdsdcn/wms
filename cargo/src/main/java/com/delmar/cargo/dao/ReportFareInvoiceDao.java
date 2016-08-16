package com.delmar.cargo.dao;

import java.math.BigDecimal;

import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.core.dao.CoreHibernateDao;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:11:19 
 * 类说明 
 */
public interface ReportFareInvoiceDao  extends CoreHibernateDao<ReportFareInvoice>  {

	
	public BigDecimal getDebitBalance(String trustFileCode);
}
