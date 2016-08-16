/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.common.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.common.dao.FtpConnectionDao;
import com.delmar.common.model.FtpConnection;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-04-16 08:53:59
 */
@Repository("ftpConnectionDao") 
public class FtpConnectionDaoMybatis extends CoreDaoMyBatis<FtpConnection> implements FtpConnectionDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.common.mybatis.sql.FtpConnectionMapper";
	}



}
