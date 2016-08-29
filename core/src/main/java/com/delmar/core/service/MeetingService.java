
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Meeting;
import com.delmar.core.service.CoreService;
import com.delmar.core.model.MeetingParticipant;
import com.delmar.core.model.MeetingTopic;
import java.util.List;
/**
 * @author 刘大磊 2016-08-27 16:28:14
 */
public interface MeetingService extends CoreService<Meeting> {
	/**
	 * @param ids
	 */
	public void deleteMeetingList(Integer[] ids);
    public List<MeetingParticipant> getMeetingParticipantListByMeetingId(Integer meetingId);
    public List<MeetingTopic> getMeetingTopicListByMeetingId(Integer meetingId);

public Integer saveMeeting(Meeting meeting,List<MeetingParticipant> meetingParticipantList,List<MeetingTopic> meetingTopicList);

}