/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.PortWebserviceDao;
import com.delmar.base.model.PortWebservice;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-03-19 11:21:06
 */
@Repository("portWebserviceDao") 
public class PortWebserviceDaoMybatis extends CoreDaoMyBatis<PortWebservice> implements PortWebserviceDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.PortWebserviceMapper";
	}



}
