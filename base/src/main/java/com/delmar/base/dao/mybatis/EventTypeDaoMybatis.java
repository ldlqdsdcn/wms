/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.EventTypeDao;
import com.delmar.base.model.EventType;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-31 09:57:16
 */
@Repository("eventTypeDao") 
public class EventTypeDaoMybatis extends CoreDaoMyBatis<EventType> implements EventTypeDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.EventTypeMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.dao.EventTypeDao#getEventTypeListByMode(java.lang.String)
	 */
	public List<EventType> getEventTypeListByMode(String mode) {
	Map<String,Object>param=new HashMap<String,Object>();
	param.put("accessString", " id in(select eventtype_id from base_eventtype_mode where mode='"+mode+"')");
		return this.selectByExample(param);
	}



}
