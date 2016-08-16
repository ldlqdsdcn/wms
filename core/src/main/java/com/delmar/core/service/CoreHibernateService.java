package com.delmar.core.service;

import java.io.Serializable;
import java.util.List;

import com.delmar.core.model.CoreModel;
import com.delmar.core.model.CriteriaH;
import com.delmar.core.model.HbnHsql;

/**
 *@ClassName:   CoreHibernateService.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月15日 下午3:25:36
 * @version V2.0
 */
@Deprecated
public interface CoreHibernateService<T> {
	
	/**
	 * Hibernate 保存方法
	 */
	public T save(T obj);
	
	/**
	 * 通过对象删除对象 用户Hibernate
	 * @param obj
	 */
	public void remove(T obj) ;
	
	/**
	 * 根据model id值更新数据库中的记录
	 * @param obj
	 */
	public void updateObject(T obj);
	
	
		
	/**
	 * 通过主键值来获得 model对象
	 * @param id
	 * @return 如果model对象存在，则返回该model对象，否则返回null
	 */
	public T getObject(Serializable obj);
	
	/**
	 * 通过主键值来获得 model对象
	 * @param id
	 * @return 如果model对象存在，则返回该model对象，否则返回null
	 */
	public T loadObject(Serializable obj);	

	

	/**
	 * 统计符合一定条件的记录数
	 * @param obj
	 * @return 返回记录个数
	 */
	public Integer countObjects(Object obj);
	
	/**
	 * Hibernate 公共查询
	 * @param criteriaH
	 * @return
	 */
	public List<T> searchHSQL(HbnHsql hbnWhere);
	public List<T> search(CriteriaH criteriaH);
	
	public T get(CriteriaH criteriaH);
	
}
