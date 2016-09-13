/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
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
import com.delmar.cargo.model.MeetingTopics;
import com.delmar.cargo.model.MeetingParticipant;
import com.delmar.cargo.dao.MeetingTopicsDao;
import com.delmar.cargo.dao.MeetingParticipantDao;
/**
 * @author 刘大磊 2016-09-13 13:40:33
 */
@Service("meetingService")
public class MeetingServiceImpl extends CoreServiceImpl<Meeting> implements
		MeetingService {
	@Autowired
	private MeetingDao meetingDao;
    @Autowired
    private MeetingTopicsDao meetingTopicsDao;
    @Autowired
    private MeetingParticipantDao meetingParticipantDao;
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


public Integer saveMeeting(Meeting meeting,List<MeetingTopics> meetingTopicsList,List<MeetingParticipant> meetingParticipantList) {
	boolean isNew=false;
	if(meeting.getId()==null||meeting.getId()==0)
	{
	isNew=true;
	}
	if(!isNew)
	{	
			List<MeetingTopics> oldMeetingTopicsList=getMeetingTopicsListByMeetingId(meeting.getId());
    		for(MeetingTopics meetingTopics: meetingTopicsList)
			{
				for(int i=oldMeetingTopicsList.size()-1;i>=0;i--)
				{
					if(oldMeetingTopicsList.get(i).getId().equals(meetingTopics.getId()))
					{
						oldMeetingTopicsList.remove(i);
					}
				}
			}
			for(MeetingTopics meetingTopics:oldMeetingTopicsList)
			{
				meetingTopicsDao.deleteByPrimaryKey(meetingTopics.getId());
			}

			List<MeetingParticipant> oldMeetingParticipantList=getMeetingParticipantListByMeetingId(meeting.getId());
    		for(MeetingParticipant meetingParticipant: meetingParticipantList)
			{
				for(int i=oldMeetingParticipantList.size()-1;i>=0;i--)
				{
					if(oldMeetingParticipantList.get(i).getId().equals(meetingParticipant.getId()))
					{
						oldMeetingParticipantList.remove(i);
					}
				}
			}
			for(MeetingParticipant meetingParticipant:oldMeetingParticipantList)
			{
				meetingParticipantDao.deleteByPrimaryKey(meetingParticipant.getId());
			}
    }
	Integer id=save(meeting);
	Date now=new Date();
		for(MeetingTopics meetingTopics: meetingTopicsList)
		{
			meetingTopics.setMeetingId(id);
			meetingTopicsDao.save(meetingTopics);
		}
		for(MeetingParticipant meetingParticipant: meetingParticipantList)
		{
			meetingParticipant.setMeetingId(id);
			meetingParticipantDao.save(meetingParticipant);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> meetingTopicsParam=new HashMap<String,Object>();
meetingTopicsParam.put("meetingId",id);
meetingTopicsDao.deleteByExample(meetingTopicsParam);
    Map<String,Object> meetingParticipantParam=new HashMap<String,Object>();
meetingParticipantParam.put("meetingId",id);
meetingParticipantDao.deleteByExample(meetingParticipantParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<MeetingTopics> getMeetingTopicsListByMeetingId(Integer meetingId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("meetingId",meetingId);
		if(meetingId==null)
		{
			return new java.util.ArrayList<MeetingTopics>();
		}
		return meetingTopicsDao.selectByExample(param);
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
}
