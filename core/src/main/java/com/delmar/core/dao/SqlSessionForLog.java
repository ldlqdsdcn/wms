/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                           *
 *****************************************************************************/
package com.delmar.core.dao;

import com.delmar.core.model.CoreModel;


/**
 * @author 刘大磊 2014年12月18日 下午5:28:52
 */
 public interface SqlSessionForLog {
	 int update(String statement, CoreModel model);
	 int delete(String statement, CoreModel model);
	 int insert(String statement, CoreModel model);
	
/*	*//**
	 *  用于替代 getObject
	 *//*
	 Changelog selectByPrimaryKey(Integer id);
	*//**
	 * 用于代替 getByExample
	 * @param example
	 * @return
	 *//*
	 List<Changelog> selectByExample(Map<String,Object> example);*/
}
