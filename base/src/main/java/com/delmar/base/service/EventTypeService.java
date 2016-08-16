
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import java.util.List;

import com.delmar.base.model.EventType;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-31 09:57:16
 */
public interface EventTypeService extends CoreService<EventType> {
	/**
	 * 通过mode获取 event type
	 * @param mode
	 * @return
	 */
	public List<EventType> getEventTypeListByMode(String mode);
}