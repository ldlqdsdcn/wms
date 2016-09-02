/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.crm.dao.BusForecastDao;
import com.delmar.crm.model.BusForecast;

/**
 * @author 刘大磊 2015-04-21 08:33:00
 */
@Repository("busForecastDao") 
public class BusForecastDaoMybatis extends CoreDaoMyBatis<BusForecast> implements BusForecastDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.BusForecastMapper";
	}
	
	
	public List<BusForecast> selectByLinkRecordId(Integer linkRecordId)
	{
		return sqlSessionTemplate.selectList(this.getSqlName()+".selectByLinkRecordId", linkRecordId);
	}



}
