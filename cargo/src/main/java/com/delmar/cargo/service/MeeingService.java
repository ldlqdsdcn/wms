/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/package com.delmar.cargo.service;
import com.delmar.cargo.model.Meeing;
import com.delmar.core.service.CoreService;
import com.delmar.cargo.model.MeeingParticipant;
import com.delmar.cargo.model.MeeingTopic;
import java.util.List;
/**
 * @author 刘大磊 2016-09-12 14:56:11
 */
public interface MeeingService extends CoreService<Meeing> {
	void deleteMeeingList(Integer[] ids);
	List<MeeingParticipant> getMeeingParticipantListByMeeingId(Integer meeingId);
	List<MeeingTopic> getMeeingTopicListByMeeingId(Integer meeingId);
	Integer saveMeeing(Meeing meeing,List<MeeingParticipant> meeingParticipantList,List<MeeingTopic> meeingTopicList);
}