
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Message;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2016-08-26 17:30:05
 */
public interface MessageService extends CoreService<Message> {
	/**
	 * @param ids
	 */
	 void deleteMessageList(Integer[] ids);
}