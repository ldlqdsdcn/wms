/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.MobileClientDao;
import com.delmar.sys.model.MobileClient;

/**
 * @author 刘大磊 2015-07-21 18:27:53
 */
@Repository("mobileClientDao") 
public class MobileClientDaoMybatis extends CoreDaoMyBatis<MobileClient> implements MobileClientDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.MobileClientMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.sys.dao.MobileClientDao#getMobileClientByMaxVersion()
	 */
	@Override
	public MobileClient getMobileClientByMaxVersion() {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("orderByClause", "version_code desc");
		List<MobileClient> mc=this.selectByExample(param);
		for(MobileClient m:mc)
		{
			return m;
		}
		return null;
	}



}
