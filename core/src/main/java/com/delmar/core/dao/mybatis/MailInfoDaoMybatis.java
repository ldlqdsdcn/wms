/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.MailInfoDao;
import com.delmar.core.model.MailInfo;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-03-25 09:20:22
 */
@Repository("mailInfoDao") 
public class MailInfoDaoMybatis extends CoreDaoMyBatis<MailInfo> implements MailInfoDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.MailInfoMapper";
	}
	
	
	public void updateMailFinishStatus(Integer id)
	{
		sqlSessionTemplate.update(getSqlName()+".updateFinishStatus", id);
	}



}
