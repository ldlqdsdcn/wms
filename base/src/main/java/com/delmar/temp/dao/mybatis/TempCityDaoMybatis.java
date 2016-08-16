/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.temp.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.temp.dao.TempCityDao;
import com.delmar.temp.model.TempCity;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-06 02:59:26
 */
@Repository("tempCityDao") 
public class TempCityDaoMybatis extends CoreDaoMyBatis<TempCity> implements TempCityDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.temp.mybatis.sql.TempCityMapper";
	}



}
