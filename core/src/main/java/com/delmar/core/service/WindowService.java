
/******************************************************************************
 * 刘大磊  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.Window;
/**
 * @author 刘大磊 2016-08-28 17:16:34
 */
public interface WindowService extends CoreService<Window> {
	/**
	 * @param ids
	 */
	 void deleteWindowList(Integer[] ids);

	 Integer saveWindow(Window window);

}