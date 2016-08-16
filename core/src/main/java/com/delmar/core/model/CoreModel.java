/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
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
		if (id == null || id == 0)
			return true;
		else
			return false;
	}
}
