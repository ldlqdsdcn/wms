/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.ScheduledDao;
import com.delmar.sys.model.Scheduled;

/**
 * @author 刘大磊 2015-08-24 16:48:41
 */
@Repository("scheduledDao") 
public class ScheduledDaoMybatis extends CoreDaoMyBatis<Scheduled> implements ScheduledDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.ScheduledMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.ScheduledDao#getScheduledByClassName(java.lang.String)
	 */
	@Override
	public Scheduled getScheduledByClassName(String className) {
		Map param=new HashMap<String,Object>();
		param.put("className", className);
		return this.getByExample(param);
	}



}
