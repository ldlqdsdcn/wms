/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.corebus.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.corebus.dao.RealTransDao;
import com.delmar.corebus.model.RealTrans;

/**
 * @author 刘大磊 2015-03-18 16:04:12
 */
@Repository("realTransDao") 
public class RealTransDaoMybatis extends CoreDaoMyBatis<RealTrans> implements RealTransDao {

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.corebus.mybatis.sql.RealTransMapper";
	}

	/* (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealTransDao#selectMaxHawbBillNo()
	 */
	public String selectMaxHawbBillNo() {
		return this.sqlSessionTemplate.selectOne(getSqlName() +".selectMaxHawbBillNo");
	}

	/* (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealTransDao#selectMaxMasterBillNo()
	 */
	public String selectMaxMasterBillNo() {
		return this.sqlSessionTemplate.selectOne(getSqlName() +".selectMaxMasterBillNo");
	}

	/** (non-Javadoc)
	 * @see com.delmar.corebus.dao.RealTransDao#getRealTransByHawbBillNo(String)
	 */
	public List<RealTrans> getRealTransByHawbBillNo(String orderno,String hawbBillNo,Integer clientId) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("hawbbillno", hawbBillNo);
		param.put("businessno", orderno);
		param.put("accessString", " b_ebusiness_id in (select id from B_EBusiness where client_id="+clientId+") ");
		param.put("orderByClause", " hawbbillNo ");
		return this.selectByExample(param);
	}



}
