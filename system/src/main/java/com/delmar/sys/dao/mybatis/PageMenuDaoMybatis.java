/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.delmar.sys.dao.PageMenuDao;
import com.delmar.sys.model.PageMenu;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
@Repository("pageMenuDao") 
public class PageMenuDaoMybatis extends CoreDaoMyBatis<PageMenu> implements PageMenuDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.PageMenuMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.PageMenuDao#getPageMenusbyUserId(java.lang.Integer)
	 */
	@Override
	public List<PageMenu> getPageMenusbyUserId(Integer userId) {
		
		return this.sqlSessionTemplate.selectList(getSqlName()+".getPageMenuByUserId", userId);
	}



}
