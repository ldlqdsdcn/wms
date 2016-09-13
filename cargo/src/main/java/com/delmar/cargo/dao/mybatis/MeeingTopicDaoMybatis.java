/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.MeeingTopicDao;
import com.delmar.cargo.model.MeeingTopic;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-12 14:56:11
 */
@Repository("meeingTopicDao") 
public class MeeingTopicDaoMybatis extends CoreDaoMyBatis<MeeingTopic> implements MeeingTopicDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.cargo.mybatis.sql.MeeingTopicMapper";
	}



}
