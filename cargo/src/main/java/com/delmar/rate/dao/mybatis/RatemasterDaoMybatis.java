/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.rate.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.rate.dao.RatemasterDao;
import com.delmar.rate.model.Ratemaster;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-22 17:09:41
 */
@Repository("ratemasterDao") 
public class RatemasterDaoMybatis extends CoreDaoMyBatis<Ratemaster> implements RatemasterDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		return "com.delmar.rate.mybatis.sql.RatemasterMapper";
	}



}
