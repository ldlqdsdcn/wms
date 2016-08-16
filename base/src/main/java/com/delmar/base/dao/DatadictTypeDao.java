/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import com.delmar.base.model.DatadictType;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-01-21 10:29:53
 */
public interface DatadictTypeDao extends CoreDao<DatadictType> {

	/**
	 * @param value
	 * @return
	 */
	DatadictType datadictTypeDao(String value);

}
