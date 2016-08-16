package com.delmar.cargo.dao;

import java.util.List;

import com.delmar.cargo.model.BusinessClient;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月26日 下午8:34:41 
 * 类说明 
 */
public interface BusinessHisDao extends CoreHibernateDao<BusinessForwarder> {

	
	public List<CustomerBusiness> searchCustomerBusiness(HbnHsql hbnhsql);
	public List<BusinessPerformance> searchSalesPerformance(HbnHsql hbnhsql);
	public BusinessPerformance getSalesPerformance(HbnHsql hbnhsql);
	public List<BusinessPerformance> searchSalesPerformancePK(HbnHsql hbnhsql);
	public List<BusinessClient> searchChinaClientState(HbnHsql hbnhsql,String exportImport,String planeOcean);
	public List<BusinessClient> searchForeignClientState(HbnHsql hbnhsql,String exportImport,String planeOcean);
}
