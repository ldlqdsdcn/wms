/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.base.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.base.dao.CarrierTrlDao;
import com.delmar.base.model.CarrierTrl;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-02-05 16:23:46
 */
@Repository("carrierTrlDao") 
public class CarrierTrlDaoMybatis extends CoreDaoMyBatis<CarrierTrl> implements CarrierTrlDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.base.mybatis.sql.CarrierTrlMapper";
	}



}
