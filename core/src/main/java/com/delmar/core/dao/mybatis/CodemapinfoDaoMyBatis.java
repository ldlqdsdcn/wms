/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.CodemapinfoDao;
import com.delmar.core.model.Codemapinfo;

/**
 * @author 刘大磊 2014年12月18日 下午6:49:58
 */
@Repository("codemapinfoDao") 
public class CodemapinfoDaoMyBatis extends CoreDaoMyBatis<Codemapinfo> implements
		CodemapinfoDao {
private static final String SQLNAME="com.delmar.core.dao.CodemapinfoMapper";
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		return SQLNAME;
	}





}
