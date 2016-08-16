/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.dao;

import com.delmar.core.dao.CoreDao;
import com.delmar.corebus.model.EBusiness;

/**
 * @author 刘大磊 2014-12-26 10:54:30
 */
public interface EBusinessDao extends CoreDao<EBusiness> {
	/**
	 * @param prefix
	 * @return
	 */
	public String selectMaxBusinessNo(String prefix);
}
