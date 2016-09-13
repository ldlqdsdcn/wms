/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.MeeingDao;
import com.delmar.cargo.model.Meeing;
import com.delmar.cargo.service.MeeingService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.delmar.cargo.model.MeeingParticipant;
import com.delmar.cargo.model.MeeingTopic;
import com.delmar.cargo.dao.MeeingParticipantDao;
import com.delmar.cargo.dao.MeeingTopicDao;
/**
 * @author 刘大磊 2016-09-12 14:56:11
 */
@Service("meeingService")
public class MeeingServiceImpl extends CoreServiceImpl<Meeing> implements
		MeeingService {
	@Autowired
	private MeeingDao meeingDao;
    @Autowired
    private MeeingParticipantDao meeingParticipantDao;
    @Autowired
    private MeeingTopicDao meeingTopicDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Meeing> getCoreDao() {
		return meeingDao;
	}
	public void deleteMeeingList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveMeeing(Meeing meeing,List<MeeingParticipant> meeingParticipantList,List<MeeingTopic> meeingTopicList) {
	Integer id=save(meeing);
	Date now=new Date();
		for(MeeingParticipant meeingParticipant: meeingParticipantList)
		{
			if(meeingParticipant.isnew())
			{
				meeingParticipant.setCreated(now);
				meeingParticipant.setCreatedby(meeing.getUpdatedby());
			}
		meeingParticipant.setUpdated(now);
		meeingParticipant.setUpdatedby(meeing.getUpdatedby());
		meeingParticipant.setUserId(meeing.getUserId());
		meeingParticipant.setOrgId(meeing.getOrgId());
		meeingParticipant.setClientId(meeing.getClientId());
		//	meeingParticipant.setMeeingId(id);
			meeingParticipantDao.save(meeingParticipant);
		}
		for(MeeingTopic meeingTopic: meeingTopicList)
		{
			if(meeingTopic.isnew())
			{
				meeingTopic.setCreated(now);
				meeingTopic.setCreatedby(meeing.getUpdatedby());
			}
		meeingTopic.setUpdated(now);
		meeingTopic.setUpdatedby(meeing.getUpdatedby());
		meeingTopic.setUserId(meeing.getUserId());
		meeingTopic.setOrgId(meeing.getOrgId());
		meeingTopic.setClientId(meeing.getClientId());
		//	meeingTopic.setMeeingId(id);
			meeingTopicDao.save(meeingTopic);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> meeingParticipantParam=new HashMap<String,Object>();
meeingParticipantParam.put("meeingId",id);
meeingParticipantDao.deleteByExample(meeingParticipantParam);
    Map<String,Object> meeingTopicParam=new HashMap<String,Object>();
meeingTopicParam.put("meeingId",id);
meeingTopicDao.deleteByExample(meeingTopicParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<MeeingParticipant> getMeeingParticipantListByMeeingId(Integer meeingId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("meeingId",meeingId);
		if(meeingId==null)
		{
			return new java.util.ArrayList<MeeingParticipant>();
		}
		return meeingParticipantDao.selectByExample(param);
	}
	public List<MeeingTopic> getMeeingTopicListByMeeingId(Integer meeingId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("meeingId",meeingId);
		if(meeingId==null)
		{
			return new java.util.ArrayList<MeeingTopic>();
		}
		return meeingTopicDao.selectByExample(param);
	}
}
