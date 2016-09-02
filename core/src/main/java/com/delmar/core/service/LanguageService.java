
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
	 void deleteLanguageList(Integer[] ids);
	
	 void saveLanguage(Language language,List<LanguageTrl> trlList);
}