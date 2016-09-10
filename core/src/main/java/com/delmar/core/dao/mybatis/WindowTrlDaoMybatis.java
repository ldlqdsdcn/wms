/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.dao.mybatis;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.WindowTrlDao;
import com.delmar.core.model.WindowTrl;
import com.delmar.core.dao.mybatis.CoreDaoMyBatis;

/**
 * @author 刘大磊 2016-09-10 14:38:01
 */
@Repository("windowTrlDao") 
public class WindowTrlDaoMybatis extends CoreDaoMyBatis<WindowTrl> implements WindowTrlDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.core.mybatis.sql.WindowTrlMapper";
	}



}
