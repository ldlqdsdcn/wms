/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                               *
 *****************************************************************************/

package com.delmar.sys.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.sys.dao.JavabeanDao;
import com.delmar.sys.model.Javabean;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
@Repository("javabeanDao") 
public class JavabeanDaoMybatis extends CoreDaoMyBatis<Javabean> implements JavabeanDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.sys.mybatis.sql.JavabeanMapper";
	}

	/** (non-Javadoc)
	 * @see com.delmar.sys.dao.JavabeanDao#selectJavabeanByPrivileges(Integer, String)
	 */
	@Override
	public List<Javabean> selectJavabeanByPrivileges(Integer userId, String operator) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("operator",operator);
		return this.sqlSessionTemplate.selectList(getSqlName() +".selectJavabeanByPrivileges", param);
	}



}
