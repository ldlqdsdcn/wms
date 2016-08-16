
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import java.util.List;

import com.delmar.core.model.Language;
import com.delmar.core.model.LanguageTrl;

/**
 * @author 刘大磊 2015-02-05 13:52:38
 */
public interface LanguageService extends CoreService<Language> {
	/**
	 * @param ids
	 */
	public void deleteLanguageList(Integer[] ids);
	
	public void saveLanguage(Language language,List<LanguageTrl> trlList);
}