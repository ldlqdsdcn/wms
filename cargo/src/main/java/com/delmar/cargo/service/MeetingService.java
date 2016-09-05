
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.cargo.service;

import com.delmar.cargo.model.Meeting;
import com.delmar.core.service.CoreService;
import com.delmar.cargo.model.MeetingParticipant;
import com.delmar.cargo.model.MeetingTopic;
import java.util.List;
/**
 * @author 刘大磊 2016-08-31 15:25:16
 */
public interface MeetingService extends CoreService<Meeting> {
	/**
	 * @param ids
	 */
	 void deleteMeetingList(Integer[] ids);
     List<MeetingParticipant> getMeetingParticipantListByMeetingId(Integer meetingId);
     List<MeetingTopic> getMeetingTopicListByMeetingId(Integer meetingId);

 Integer saveMeeting(Meeting meeting,List<MeetingParticipant> meetingParticipantList,List<MeetingTopic> meetingTopicList);

}