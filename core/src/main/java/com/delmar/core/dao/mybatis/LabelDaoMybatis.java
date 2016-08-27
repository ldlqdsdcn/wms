/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.LabelDao;
import com.delmar.core.model.Label;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-08-27 08:23:58
 */
@Repository("labelDao") 
public class LabelDaoMybatis extends CoreDaoMyBatis<Label> implements LabelDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.LabelMapper";
	}



}
