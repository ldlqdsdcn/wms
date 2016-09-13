/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.cargo.model.Meeing;
import com.delmar.cargo.service.MeeingService;
import java.util.Date;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.cargo.model.MeeingParticipant;
import com.delmar.cargo.model.MeeingTopic;
/**
 * @author 刘大磊 2016-09-12 14:56:11
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "meeing.title", message = "不允许为空") },requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "meeing.bgnTime",message = "不允许为空"),@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "meeing.endTime",message = "不允许为空")})
public class MeeingAction extends CoreEditPrivAction {
	private Meeing  meeing;
	private List<MeeingParticipant> meeingParticipantList=new ArrayList<MeeingParticipant>();;
	private List<MeeingTopic> meeingTopicList=new ArrayList<MeeingTopic>();;
	@Autowired
	private MeeingService meeingService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "meeing";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		meeingService.deleteByPrimaryKey(meeing.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		meeingService.deleteMeeingList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return meeing.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
public void editForm() {
meeing= meeingService.selectByPrimaryKey(id);

		meeingParticipantList=meeingService.getMeeingParticipantListByMeeingId(id);

		meeingTopicList=meeingService.getMeeingTopicListByMeeingId(id);
}
/* (non-Javadoc)
* @see com.delmar.core.web.action.CoreEditPrivAction#search()
*/
@Override
public List search() {
Map<String,Object> param=new HashMap();
param.put("searchString",getSearchWhere());
return meeingService.selectByExample(param);
}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
public void createForm() {
meeing=new Meeing();
	meeingParticipantList=new ArrayList<MeeingParticipant>();
	meeingTopicList=new ArrayList<MeeingTopic>();
    }
    @SkipValidation
    public String addMeeingParticipant()
    {
    MeeingParticipant  meeingParticipant=new MeeingParticipant();
	meeingParticipantList.add(meeingParticipant);
    return "edit";
    }
    @SkipValidation
    public String deleteMeeingParticipants()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("MeeingParticipant_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   MeeingParticipant column=meeingParticipantList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	meeingParticipantList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
    @SkipValidation
    public String addMeeingTopic()
    {
    MeeingTopic  meeingTopic=new MeeingTopic();
	meeingTopicList.add(meeingTopic);
    return "edit";
    }
    @SkipValidation
    public String deleteMeeingTopics()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("MeeingTopic_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   MeeingTopic column=meeingTopicList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	meeingTopicList.remove(Integer.parseInt(idList.get(i)));
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
if(meeing.isnew())
{
meeing.setCreated(date);
meeing.setCreatedby(currentUserId);

    meeing.setClientId(user.getClientId());
    meeing.setOrgId(user.getOrgId());
    meeing.setUserId(user.getId());
}
meeing.setUpdated(date);
meeing.setUpdatedby(currentUserId);
		meeingService.saveMeeing(meeing,meeingParticipantList,meeingTopicList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Meeing getMeeing() {
		return meeing;
	}

	/**
	 * @param meeing the meeing to set
	 */
	public void setMeeing(Meeing meeing) {
		this.meeing = meeing;
	}
public List<MeeingParticipant> getMeeingParticipantList()
{
	return meeingParticipantList;
}
public void setMeeingParticipantList(List<MeeingParticipant> meeingParticipantList)
{
	this.meeingParticipantList=meeingParticipantList;
}
public List<MeeingTopic> getMeeingTopicList()
{
	return meeingTopicList;
}
public void setMeeingTopicList(List<MeeingTopic> meeingTopicList)
{
	this.meeingTopicList=meeingTopicList;
}
}
