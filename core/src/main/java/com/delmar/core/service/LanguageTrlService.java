
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.LanguageTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-02-05 11:57:46
 */
public interface LanguageTrlService extends CoreService<LanguageTrl> {
	/**
	 * @param ids
	 */
	public void deleteLanguageTrlList(Integer[] ids);
	
	//public List< LanguageTrl> selectLanguageTrlList(String by)
}