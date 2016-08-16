package com.delmar.cargo.service;

import com.delmar.cargo.model.TrustContext;
import com.delmar.core.service.CoreHibernateService;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:02:27 
 * 类说明 
 */
public interface TrustContextService  extends CoreHibernateService<TrustContext>  {

	public String getTrustFileCodeByHawb(String hawb);
	public String getTrustFileCodeByMawb(String mawb);

	
}
