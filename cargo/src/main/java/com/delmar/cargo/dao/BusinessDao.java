package com.delmar.cargo.dao;

import java.util.List;

import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月25日 下午3:11:33 
 * 类说明 
 */
public interface BusinessDao   extends CoreHibernateDao<BusinessForwarder> {

	
	public List<BusinessInvoice> searchBusinessInvoiceList(HbnHsql hbnhsql,String planeOcean);
	public List<BusinessForwarder> searchBusinessList(HbnHsql hbnhsql,String planeOcean);
	public List<BusinessInvoice> searchBusinessInvoiceState(String salesCode,String flightDateBegin,String flightDateEnd);
}
