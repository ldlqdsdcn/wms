/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.rate.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.rate.dao.RatemarkupDao;
import com.delmar.rate.model.Ratemarkup;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-22 17:09:41
 */
@Repository("ratemarkupDao") 
public class RatemarkupDaoMybatis extends CoreDaoMyBatis<Ratemarkup> implements RatemarkupDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.rate.mybatis.sql.RatemarkupMapper";
	}



}
