/******************************************************************************
 * 刘大磊  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.MeetingDao;
import com.delmar.cargo.model.Meeting;
import com.delmar.cargo.service.MeetingService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.delmar.cargo.model.MeetingParticipant;
import com.delmar.cargo.model.MeetingTopic;
import com.delmar.cargo.dao.MeetingParticipantDao;
import com.delmar.cargo.dao.MeetingTopicDao;
/**
 * @author 刘大磊 2016-08-31 15:25:16
 */
@Service("meetingService")
public class MeetingServiceImpl extends CoreServiceImpl<Meeting> implements
		MeetingService {
	@Autowired
	private MeetingDao meetingDao;
    @Autowired
    private MeetingParticipantDao meetingParticipantDao;
    @Autowired
    private MeetingTopicDao meetingTopicDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Meeting> getCoreDao() {
		return meetingDao;
	}
	public void deleteMeetingList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveMeeting(Meeting meeting,List<MeetingParticipant> meetingParticipantList,List<MeetingTopic> meetingTopicList) {
	Integer id=save(meeting);
	Date now=new Date();
		for(MeetingParticipant meetingParticipant: meetingParticipantList)
		{
			if(meetingParticipant.isnew())
			{
				meetingParticipant.setCreated(now);
				meetingParticipant.setCreatedby(meeting.getUpdatedby());
			}
		meetingParticipant.setUpdated(now);
		meetingParticipant.setUpdatedby(meeting.getUpdatedby());
		meetingParticipant.setUserId(meeting.getUserId());
		meetingParticipant.setOrgId(meeting.getOrgId());
		meetingParticipant.setClientId(meeting.getClientId());
			meetingParticipant.setMeetingId(id);
			meetingParticipantDao.save(meetingParticipant);
		}
		for(MeetingTopic meetingTopic: meetingTopicList)
		{
			if(meetingTopic.isnew())
			{
				meetingTopic.setCreated(now);
				meetingTopic.setCreatedby(meeting.getUpdatedby());
			}
		meetingTopic.setUpdated(now);
		meetingTopic.setUpdatedby(meeting.getUpdatedby());
		meetingTopic.setUserId(meeting.getUserId());
		meetingTopic.setClientId(meeting.getClientId());
			meetingTopic.setMeetingId(id);
			meetingTopicDao.save(meetingTopic);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> meetingParticipantParam=new HashMap<String,Object>();
meetingParticipantParam.put("meetingId",id);
meetingParticipantDao.deleteByExample(meetingParticipantParam);
    Map<String,Object> meetingTopicParam=new HashMap<String,Object>();
meetingTopicParam.put("meetingId",id);
meetingTopicDao.deleteByExample(meetingTopicParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<MeetingParticipant> getMeetingParticipantListByMeetingId(Integer meetingId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("meetingId",meetingId);
		if(meetingId==null)
		{
			return new java.util.ArrayList<MeetingParticipant>();
		}
		return meetingParticipantDao.selectByExample(param);
	}
	public List<MeetingTopic> getMeetingTopicListByMeetingId(Integer meetingId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("meetingId",meetingId);
		if(meetingId==null)
		{
			return new java.util.ArrayList<MeetingTopic>();
		}
		return meetingTopicDao.selectByExample(param);
	}
}
