/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.RealtransEventDao;
import com.delmar.corebus.model.RealtransEvent;

/**
 * @author 刘大磊 2015-03-25 15:47:49
 */
@Repository("realtransEventDao") 
public class RealtransEventDaoMybatis extends CoreDaoMyBatis<RealtransEvent> implements RealtransEventDao {

	/** (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.RealtransEventMapper";
	}

	/** (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealtransEventDao#selectOneRealtransEvent(Integer)
	 */
	public RealtransEvent selectOneRealtransEvent(Integer realtransId,Integer eventTypeStatus) {
		Map param=new HashMap();
		param.put("realtransId", realtransId);
		param.put("accessString", " event_id in(	select id from B_Event where eventtypeid in( select ID from Base_EventType where eventtypestatus="+eventTypeStatus+")) ");
		List<RealtransEvent> list=this.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	
	/** (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealtransEventDao#selectCurrentRealtransEvent(Integer)
	 */
	public RealtransEvent selectCurrentRealtransEvent(Integer realtransId) {
		Map param=new HashMap();
		param.put("realtransId", realtransId);
		param.put("orderByClause", " created desc ");
		List<RealtransEvent> list=this.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	/** (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealtransEventDao#getRealtransEventListByTransId(Integer)
	 */
	public List<RealtransEvent> getRealtransEventListByTransId(Integer transId) {
		Map param=new HashMap();
		param.put("realtransId", transId);
		
		return selectByExample(param);
	}



}
