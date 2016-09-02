/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.rate.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.rate.dao.RatedetailDao;
import com.delmar.rate.model.Ratedetail;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2014-12-22 17:09:41
 */
@Repository("ratedetailDao") 
public class RatedetailDaoMybatis extends CoreDaoMyBatis<Ratedetail> implements RatedetailDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.rate.mybatis.sql.RatedetailMapper";
	}



}
