/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/package com.delmar.cargo.service;
import com.delmar.cargo.model.Meeting;
import com.delmar.core.service.CoreService;
import com.delmar.cargo.model.MeetingTopics;
import com.delmar.cargo.model.MeetingParticipant;
import java.util.List;
/**
 * @author 刘大磊 2016-09-13 13:40:33
 */
public interface MeetingService extends CoreService<Meeting> {
	void deleteMeetingList(Integer[] ids);
	List<MeetingTopics> getMeetingTopicsListByMeetingId(Integer meetingId);
	List<MeetingParticipant> getMeetingParticipantListByMeetingId(Integer meetingId);
	Integer saveMeeting(Meeting meeting,List<MeetingTopics> meetingTopicsList,List<MeetingParticipant> meetingParticipantList);
}