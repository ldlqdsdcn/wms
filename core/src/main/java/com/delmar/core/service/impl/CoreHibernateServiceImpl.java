package com.delmar.core.service.impl;

import java.io.Serializable;
import java.util.List;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.model.CriteriaH;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.service.CoreHibernateService;

/**
 *@ClassName:   CoreHibernateServiceImpl.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月18日 下午6:01:06
 * @version V2.0
 * @param <T>
 */
public abstract class CoreHibernateServiceImpl<T> implements CoreHibernateService<T>  {
	
	protected abstract CoreHibernateDao<T> getCoreDao();
	
	/**
	 * Hibernate 保存方法
	 */
	public T save(T obj) {
		return getCoreDao().save(obj);
	}
	
	
	/**
	 * 通过对象删除对象 用户Hibernate
	 * @param obj
	 */
	public void remove(T obj) {
		getCoreDao().remove(obj);	
	}	
	
	/**
	 * 根据model id值更新数据库中的记录
	 * @param obj
	 */
	public void updateObject(T obj){
		getCoreDao().updateObject(obj);		
	}

	
	/**
	 * 通过主键值来获得 model对象
	 * @param id
	 * @return 如果model对象存在，则返回该model对象，否则返回null
	 */
	public T getObject(Serializable obj){
		return getCoreDao().getObject(obj);		
	}

	
	/**
	 * 通过主键值来获得 model对象
	 * @param id
	 * @return 如果model对象存在，则返回该model对象，否则返回null
	 */
	public T loadObject(Serializable obj){
		return getCoreDao().loadObject(obj);		
	}
	

	/**
	 * 统计符合一定条件的记录数
	 * @param obj
	 * @return 返回记录个数
	 */
	public Integer countObjects(Object obj){
		return getCoreDao().countObjects(obj);		
	}
	
	/**
	 * Hibernate 公共查询
	 * @param criteriaH
	 * @return
	 */

	
	public List<T> searchHSQL(HbnHsql hbnWhere){
		return getCoreDao().searchHSQL(hbnWhere);		
	}
	
	public List<T> search(CriteriaH criteriaH){
		return getCoreDao().search(criteriaH);		
	}
	
	public T get(CriteriaH criteriaH){
		return getCoreDao().get(criteriaH);		
	}

}
