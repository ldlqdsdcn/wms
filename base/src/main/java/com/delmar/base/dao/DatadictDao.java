/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import java.util.List;

import com.delmar.base.model.Datadict;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface DatadictDao extends CoreDao<Datadict> {

	/**
	 * @param typeId
	 * @return
	 */
	List<Datadict> getDatadictListByTypeId(Integer typeId,Integer clientId);
	public void updateIndexOrder(Integer indexOrder,Integer id);
}
