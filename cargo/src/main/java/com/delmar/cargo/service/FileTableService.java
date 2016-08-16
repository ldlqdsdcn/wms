package com.delmar.cargo.service;

import com.delmar.cargo.model.FileTable;
import com.delmar.core.service.CoreHibernateService;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:02:16 
 * 类说明 
 */
public interface FileTableService  extends CoreHibernateService<FileTable>  {

	public String getTrustFileCodeByHawb(String hawb);
	public String getTrustFileCodeByMawb(String mawb);

}
