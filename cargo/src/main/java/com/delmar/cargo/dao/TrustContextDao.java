package com.delmar.cargo.dao;

import java.util.List;

import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.TrustContext;
import com.delmar.core.dao.CoreHibernateDao;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:11:27 
 * 类说明 
 */
public interface TrustContextDao extends CoreHibernateDao<TrustContext> {
	
	public List<BusinessForwarder> getBusForwarder(String flightDate);
	public String getTrustFileCodeByHawb(String hawb);
	public String getTrustFileCodeByMawb(String mawb);

}
