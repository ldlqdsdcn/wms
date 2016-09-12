/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.MessageTrlDao;
import com.delmar.core.model.MessageTrl;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-12 10:38:53
 */
@Repository("messageTrlDao") 
public class MessageTrlDaoMybatis extends CoreDaoMyBatis<MessageTrl> implements MessageTrlDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.MessageTrlMapper";
	}



}
