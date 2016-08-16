/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.dao;

import java.util.List;
import java.util.Map;

import com.delmar.base.model.Port;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface PortDao extends CoreDao<Port> {
	public List<Port> selectPortByMode(Map<String,Object> param) ;
}
