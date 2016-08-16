/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.EBusinessEventDao;
import com.delmar.corebus.model.EBusinessEvent;

/**
 * @author 刘大磊 2014-12-31 09:58:50
 */
@Repository("eBusinessEventDao") 
public class EBusinessEventDaoMybatis extends CoreDaoMyBatis<EBusinessEvent> implements EBusinessEventDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.EBusinessEventMapper";
	}



}
