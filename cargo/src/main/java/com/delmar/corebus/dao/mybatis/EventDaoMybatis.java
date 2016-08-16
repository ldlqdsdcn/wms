/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.EventDao;
import com.delmar.corebus.model.Event;

/**
 * @author 刘大磊 2014-12-31 09:58:50
 */
@Repository("eventDao") 
public class EventDaoMybatis extends CoreDaoMyBatis<Event> implements EventDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.EventMapper";
	}



}
