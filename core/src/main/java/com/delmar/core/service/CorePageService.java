
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.CorePage;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2016-08-26 17:08:24
 */
public interface CorePageService extends CoreService<CorePage> {
	/**
	 * @param ids
	 */
	 void deleteCorePageList(Integer[] ids);
}