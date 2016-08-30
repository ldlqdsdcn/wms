/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.cargo.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.MeetingParticipantDao;
import com.delmar.cargo.model.MeetingParticipant;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-08-30 22:15:07
 */
@Repository("meetingParticipantDao") 
public class MeetingParticipantDaoMybatis extends CoreDaoMyBatis<MeetingParticipant> implements MeetingParticipantDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.cargo.mybatis.sql.MeetingParticipantMapper";
	}



}
