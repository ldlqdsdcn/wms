/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                           *
 *****************************************************************************/
package com.delmar.core.model;

import java.io.Serializable;

/**
 * @author 刘大磊 2014年12月18日 下午5:45:20
 */
public class CoreModel implements Serializable  {
	protected Integer id;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isnew() {
        return id == null || id == 0;
	}
}
