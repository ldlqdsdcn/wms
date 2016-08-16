package com.delmar.cargo.dao;

import com.delmar.cargo.model.FileTable;
import com.delmar.core.dao.CoreHibernateDao;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:10:58 
 * 类说明 
 */
public interface FileTableDao  extends CoreHibernateDao<FileTable>  {

	public String getTrustFileCodeByHawb(String hawb);
	public String getTrustFileCodeByMawb(String mawb);

	
}
