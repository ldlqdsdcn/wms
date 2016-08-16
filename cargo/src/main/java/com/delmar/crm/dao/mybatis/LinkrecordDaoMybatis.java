/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.crm.dao.LinkrecordDao;
import com.delmar.crm.model.Linkrecord;

/**
 * @author 刘大磊 2015-03-11 13:53:09
 */
@Repository("linkrecordDao") 
public class LinkrecordDaoMybatis extends CoreDaoMyBatis<Linkrecord> implements LinkrecordDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.LinkrecordMapper";
	}



}
