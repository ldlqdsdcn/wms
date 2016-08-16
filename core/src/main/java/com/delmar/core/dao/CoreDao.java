/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.dao;

import java.util.List;
import java.util.Map;

import com.delmar.core.dao.exception.DaoException;

/**
 * @author 刘大磊 2014年12月18日 下午5:23:46
 */
public interface CoreDao<T> {

	/**
	 * 
	 * @param example
	 * @return
	 */
	public T getByExample(Map example)  ;
	/**
	 * 插入数据库model类
	 * @param model
	 * @return
	 */
	public Integer insert(T model)  ;
	
	/**
	 * 更新数据库model类
	 * @param model
	 * 更新非空字段
	 */
	public void update(T model)  ;
	
	
	/**
	 * 全部字段更新
	 * @param model
	 */
	public void updateAll(T model)  ;
	

	/**
	 * 保存model 返回最新更新的model对象
	 * @param model
	 * @return
	 */
	
	
	public Integer save(T model)  ;
	/**
	 * 根据主键删除对象
	 * @param id
	 */
	public Integer deleteByExample(Map example)  ;
	/**
	 * 
	 * @param example
	 * @return
	 */
	public Integer countObjects(Map example)  ;
	/**
	 * 只插入非空字段
	 * @param model
	 * @return
	 */
	public Integer insertSelective(T model)  ;
	/**
	 * 用于替代 delete
	 */
	public Integer deleteByPrimaryKey(Integer id)  ;
	/**
	 *  用于替代 getObject
	 */
	public T selectByPrimaryKey(Integer id)  ;
	
	/**
	 *  用于获取值取数据库当中一部分字段的
	 */
	public T selectFieldsByPrimaryKey(String fieldColumns,Integer id)  ;
	
	/**
	 * 用于代替 getByExample
	 * @param example
	 * @return
	 */
	public List<T> selectByExample(Map example)  ;

	
}
