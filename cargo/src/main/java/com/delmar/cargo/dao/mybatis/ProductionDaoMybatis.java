/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.ProductionDao;
import com.delmar.cargo.model.Production;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-13 11:28:19
 */
@Repository("productionDao") 
public class ProductionDaoMybatis extends CoreDaoMyBatis<Production> implements ProductionDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.cargo.mybatis.sql.ProductionMapper";
	}



}
