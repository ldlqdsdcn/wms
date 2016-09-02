
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import com.delmar.sys.model.Usergroup;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-01-20 16:14:21
 */
public interface UsergroupService extends CoreService<Usergroup> {
	public void deleteUsergroups(Integer[] ids);
}