/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.dao.LanguageDao;
import com.delmar.core.dao.LanguageTrlDao;
import com.delmar.core.model.Language;
import com.delmar.core.model.LanguageTrl;
import com.delmar.core.service.LanguageService;

/**
 * @author 刘大磊 22015-02-05 13:52:38
 */
@Service("languageService")
public class LanguageServiceImpl extends CoreServiceImpl<Language> implements
		LanguageService {
	@Autowired
	private LanguageDao languageDao;
	@Autowired
	private LanguageTrlDao languageTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Language> getCoreDao() {
		return languageDao;
	}
	public void deleteLanguageList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		Map map=new HashMap();
		map.put("accessString", " languageCode in(select code from core_language where id="+id+")");
		languageTrlDao.deleteByExample(map);
		return super.deleteByPrimaryKey(id);
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.service.LanguageService#saveLanguage(com.delmar.core.model.Language, java.util.List)
	 */
	public void saveLanguage(Language language, List<LanguageTrl> trlList) {
		languageDao.save(language);
		for(LanguageTrl trl:trlList)
		{
			trl.setLanguageCode(language.getCode());
			languageTrlDao.save(trl);
		}
		
	}
	
}
