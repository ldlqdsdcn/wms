/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.core.dao.mybatis;

import com.delmar.core.model.CommonSearchParam;
import com.delmar.core.model.CommonSearchResult;
import org.springframework.stereotype.Repository;

import com.delmar.core.dao.SearchDao;
import com.delmar.core.model.Search;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

import java.util.List;

/**
 * @author 刘大磊 2016-08-29 16:03:22
 */
@Repository("searchDao") 
public class SearchDaoMybatis extends CoreDaoMyBatis<Search> implements SearchDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.SearchMapper";
	}


	@Override
	public List<CommonSearchResult> selectCommonList(CommonSearchParam commonSearchParam) {
		return sqlSessionTemplate.selectList(getSqlName()+".selectCommonList",commonSearchParam);
	}
}
