/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.dao;

import com.delmar.core.dao.CoreDao;
import com.delmar.crm.model.Linkman;

/**
 * @author 刘大磊 2015-03-04 16:06:05
 */
public interface LinkmanDao extends CoreDao<Linkman> {
	
	public void updateTimesData(Integer id);
	public String getNamebyId(Integer id);

}
