/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;

import com.delmar.core.model.Meeting;

import com.delmar.core.service.MeetingService;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import com.delmar.core.model.MeetingParticipant;
import com.delmar.core.model.MeetingTopic;

/**
 * @author 刘大磊 2016-08-27 16:28:14
 */
public class MeetingAction extends CoreEditPrivAction {
	private Meeting  meeting;

	private List<MeetingParticipant> meetingParticipantList=new ArrayList<MeetingParticipant>();;
	private List<MeetingTopic> meetingTopicList=new ArrayList<MeetingTopic>();;

	@Autowired
	private MeetingService meetingService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "meeting";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		meetingService.deleteByPrimaryKey(meeting.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		meetingService.deleteMeetingList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return meeting.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 meeting= meetingService.selectByPrimaryKey(id);
		meetingParticipantList=meetingService.getMeetingParticipantListByMeetingId(id);
		meetingTopicList=meetingService.getMeetingTopicListByMeetingId(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return meetingService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		meeting=new Meeting();
		meetingParticipantList=new ArrayList<MeetingParticipant>();
		meetingTopicList=new ArrayList<MeetingTopic>();
	}
    public String addMeetingParticipant()
    {
    MeetingParticipant  meetingParticipant=new MeetingParticipant();
	meetingParticipantList.add(meetingParticipant);
    return "edit";
    }
    public String deleteMeetingParticipants()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("MeetingParticipant_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   MeetingParticipant column=meetingParticipantList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	meetingParticipantList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
    public String addMeetingTopic()
    {
    MeetingTopic  meetingTopic=new MeetingTopic();
	meetingTopicList.add(meetingTopic);
    return "edit";
    }

    public String deleteMeetingTopics()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("MeetingTopic_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   MeetingTopic column=meetingTopicList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	meetingTopicList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		meetingService.saveMeeting(meeting,meetingParticipantList,meetingTopicList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Meeting getMeeting() {
		return meeting;
	}

	/**
	 * @param meeting the meeting to set
	 */
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
public List<MeetingParticipant> getMeetingParticipantList()
{
	return meetingParticipantList;
}
public void setMeetingParticipantList(List<MeetingParticipant> meetingParticipantList)
{
	this.meetingParticipantList=meetingParticipantList;
}
public List<MeetingTopic> getMeetingTopicList()
{
	return meetingTopicList;
}
public void setMeetingTopicList(List<MeetingTopic> meetingTopicList)
{
	this.meetingTopicList=meetingTopicList;
}
}
