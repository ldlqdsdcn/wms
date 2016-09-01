/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.system.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.system.dao.UserFootmarkDao;
import com.delmar.system.model.UserFootmark;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-01 17:30:01
 */
@Repository("userFootmarkDao") 
public class UserFootmarkDaoMybatis extends CoreDaoMyBatis<UserFootmark> implements UserFootmarkDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.system.mybatis.sql.UserFootmarkMapper";
	}



}
