/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.TransactionDao;
import com.delmar.corebus.model.Transaction;

/**
 * @author 刘大磊 2014-12-26 11:48:40
 */
@Repository("transactionDao") 
public class TransactionDaoMybatis extends CoreDaoMyBatis<Transaction> implements TransactionDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.TransactionMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.corebus.dao.TransactionDao#selectByExampleWithBLOBs(java.util.Map)
	 */
	public List<Transaction> selectByExampleWithBLOBs(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList(getSqlName()+".selectByExampleWithBLOBs", param);
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.corebus.dao.TransactionDao#maxTranscode(java.lang.String)
	 */
	public String maxTranscode(String prefix) {

		return sqlSessionTemplate.selectOne(getSqlName()+".selectMaxTransno", prefix);
	}



}
