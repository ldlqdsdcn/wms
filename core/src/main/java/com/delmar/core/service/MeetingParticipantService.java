
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.MeetingParticipant;
import com.delmar.core.service.CoreService;
import java.util.List;
/**
 * @author 刘大磊 2016-08-27 13:57:01
 */
public interface MeetingParticipantService extends CoreService<MeetingParticipant> {
	/**
	 * @param ids
	 */
	public void deleteMeetingParticipantList(Integer[] ids);
}