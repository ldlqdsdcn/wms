package com.delmar.cargo.service;

import java.util.List;

import com.delmar.cargo.model.BusinessClient;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.cargo.model.ObjPerformancePK;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午9:57:06 
 * 类说明 
 */
public interface BusinessQueryService {
	
	public List<BusinessInvoice> searchBusinessInvoiceList(HbnHsql hbnhsql,String planeOcean);
	public List<BusinessForwarder> searchBusinessList(HbnHsql hbnhsql,String planeOcean);
	public List<CustomerBusiness> searchCustomerBusiness(HbnHsql hbnhsql);
	public List<BusinessPerformance> searchSalesPerformance(HbnHsql hbnhsql);
	public BusinessPerformance getSalesPerformance(HbnHsql hbnhsql);
	public ObjPerformancePK getSalesPerformancePK(HbnHsql hbnhsql,String currentUserCode);
	public List<BusinessInvoice> searchBusinessInvoiceState(String salesCode,String flightDateBegin,String flightDateEnd);
	public List<BusinessClient> searchClientState(HbnHsql hbnhsql,String exportImport,String planeOcean,String chinaForeign);	
	public List<BusinessClient> searchChinaClientState(HbnHsql hbnhsql,String exportImport,String planeOcean);
	public List<BusinessClient> searchForeignClientState(HbnHsql hbnhsql,String exportImport,String planeOcean);
	public boolean getDebitStatus(String hblNo,String mawbNo);
}
