/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                               *
 *****************************************************************************/

package com.delmar.crm.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.delmar.core.dao.mybatis.CoreDaoMyBatis;
import com.delmar.core.model.CoreModel;
import com.delmar.crm.dao.CustomerExterDao;
import com.delmar.crm.model.CustomerExtra;

/**
 * @author 刘大磊 2015-03-04 16:06:05
 */
@Repository("customerExterDao") 
public class CustomerExterDaoMybatis extends CoreDaoMyBatis<CustomerExtra> implements CustomerExterDao {
	
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#save(java.lang.Object)
	 */
	@Override
	public Integer save(CustomerExtra model) {
		
		CoreModel cm=(CoreModel)model;
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("id", model.getId());
		int count=this.countObjects(param);
		Integer id=model.getId();
		if(count==0)
		{
			id=this.insert(model);
		}
		else
		{
			update(model);
		}
		id=cm.getId();
		return id;
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreDaoMyBatis#getSqlName()
	 */
	@Override
	protected String getSqlName() {
		
		return "com.delmar.crm.mybatis.sql.CustomerExtraMapper";
	}
	
	public void updateTimesData(Integer id){
		this.sqlSessionTemplate.selectList(this.getSqlName()+".updateTimesData", id);
	}



}
