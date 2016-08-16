/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.dao;

import java.io.Serializable;
import java.util.List;

import com.delmar.core.model.CriteriaH;
import com.delmar.core.model.HbnHsql;

/**
 * @author 刘大磊 2015年5月6日 上午9:45:47
 */
public interface  CoreHibernateDao<T> {

	
	/**
	 * Hibernate 保存方法
	 */
	public T save(T obj);
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
