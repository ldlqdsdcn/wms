
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.Locale;

import com.delmar.core.service.CoreService;
import com.delmar.sys.model.PageMenu;

/**
 * @author 刘大磊 2015-01-13 09:38:52
 */
public interface PageMenuService extends CoreService<PageMenu> {
	public String	getLeftMenus(Integer userId,	Locale local);
}