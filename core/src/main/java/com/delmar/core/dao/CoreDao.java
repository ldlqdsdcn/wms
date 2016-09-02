/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                           *
 *****************************************************************************/
package com.delmar.core.dao;

import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 2014年12月18日 下午5:23:46
 */
 public interface CoreDao<T> {

	/**
	 * 
	 * @param example
	 * @return
	 */
	 T getByExample(Map example)  ;
	/**
	 * 插入数据库model类
	 * @param model
	 * @return
	 */
	 Integer insert(T model)  ;
	
	/**
	 * 更新数据库model类
	 * @param model
	 * 更新非空字段
	 */
	 void update(T model)  ;
	
	
	/**
	 * 全部字段更新
	 * @param model
	 */
	 void updateAll(T model)  ;
	

	/**
	 * 保存model 返回最新更新的model对象
	 * @param model
	 * @return
	 */
	
	
	 Integer save(T model)  ;
	/**
	 * 根据主键删除对象
	 * @param example
	 */
	 Integer deleteByExample(Map example);
	/**
	 * 
	 * @param example
	 * @return
	 */
	 Integer countObjects(Map example)  ;
	/**
	 * 只插入非空字段
	 * @param model
	 * @return
	 */
	 Integer insertSelective(T model)  ;
	/**
	 * 用于替代 delete
	 */
	 Integer deleteByPrimaryKey(Integer id)  ;
	/**
	 *  用于替代 getObject
	 */
	 T selectByPrimaryKey(Integer id)  ;
	
	/**
	 *  用于获取值取数据库当中一部分字段的
	 */
	 T selectFieldsByPrimaryKey(String fieldColumns,Integer id)  ;
	
	/**
	 * 用于代替 getByExample
	 * @param example
	 * @return
	 */
	 List<T> selectByExample(Map example)  ;

	/**
	 * 分页查询
	 * @param example
	 * @return
	 */
	public List<T> selectByPaging(Map example);


}
