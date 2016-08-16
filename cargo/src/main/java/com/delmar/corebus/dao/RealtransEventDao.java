/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.dao;

import java.util.List;

import com.delmar.core.dao.CoreDao;
import com.delmar.corebus.model.RealtransEvent;

/**
 * @author 刘大磊 2015-03-25 15:47:49
 */
public interface RealtransEventDao extends CoreDao<RealtransEvent> {

	/**
	 * @param realtransId
	 * @return
	 */
	RealtransEvent selectOneRealtransEvent(Integer realtransId,Integer eventTypeStatus);
	RealtransEvent selectCurrentRealtransEvent(Integer realtransId);

	/**
	 * @param transId
	 * @return
	 */
	List<RealtransEvent> getRealtransEventListByTransId(Integer transId);

}
