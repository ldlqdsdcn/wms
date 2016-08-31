/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/
package com.delmar.cargo.web.action;

import java.util.List;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.cargo.model.Meeting;
import com.delmar.cargo.service.MeetingService;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.cargo.model.MeetingParticipant;
import com.delmar.cargo.model.MeetingTopic;
/**
 * @author 刘大磊 2016-08-31 15:25:16
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "meeting.title", message = "不允许为空") },requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "meeting.bgnTime",message = "不允许为空"),@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "meeting.endTime",message = "不允许为空")})
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
		Map<String,Object> param=new HashMap();
		param.put("searchString",getSearchWhere());
		return meetingService.selectByExample(param);
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
    @SkipValidation
    public String addMeetingParticipant()
    {
    MeetingParticipant  meetingParticipant=new MeetingParticipant();
	meetingParticipantList.add(meetingParticipant);
    return "edit";
    }
    @SkipValidation
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
    @SkipValidation
    public String addMeetingTopic()
    {
    MeetingTopic  meetingTopic=new MeetingTopic();
	meetingTopicList.add(meetingTopic);
    return "edit";
    }
    @SkipValidation
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
Date date=new Date();
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
if(meeting.isnew())
{
meeting.setCreated(date);
meeting.setCreatedby(currentUserId);

    meeting.setClientId(user.getClientId());
    meeting.setOrgId(user.getOrgId());
    meeting.setUserId(user.getId());
}
meeting.setUpdated(date);
meeting.setUpdatedby(currentUserId);
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
