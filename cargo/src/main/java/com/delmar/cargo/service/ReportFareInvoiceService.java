package com.delmar.cargo.service;

import java.math.BigDecimal;

import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.core.service.CoreHibernateService;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:03:02 
 * 类说明 
 */
public interface ReportFareInvoiceService extends CoreHibernateService<ReportFareInvoice> {

	
	public BigDecimal getDebitBalance(String trustFileCode);
}
