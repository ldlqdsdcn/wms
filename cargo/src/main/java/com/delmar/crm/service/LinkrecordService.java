
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service;

import com.delmar.core.service.CoreService;
import com.delmar.crm.model.Linkrecord;

/**
 * @author 刘大磊 2015-03-11 13:53:09
 */
public interface LinkrecordService extends CoreService<Linkrecord> {
	/**
	 * @param ids
	 */
	public void deleteLinkrecordList(Integer[] ids);
	public void deleteByLinkmanId(Integer linkmanId);
	public void deleteById(Integer id);
}