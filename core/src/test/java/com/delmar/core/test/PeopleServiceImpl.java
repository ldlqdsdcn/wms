/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.delmar.core.model.Language;
import com.delmar.core.model.LanguageTrl;
import com.delmar.core.service.LanguageService;

/**
 * @author 刘大磊 2015年7月28日 上午11:21:21
 */
@Service("peopleService")
public class PeopleServiceImpl implements LanguageService{

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#getByExample(java.util.Map)
	 */
	public Language getByExample(Map example) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#insert(java.lang.Object)
	 */
	public Integer insert(Language model) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#update(java.lang.Object)
	 */
	public void update(Language model) {
		List list=new ArrayList();
		
		list.add(1);
		list.add(2);
		list.add(3);
		
		System.out.println(list.get(10));
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#updateAll(java.lang.Object)
	 */
	public void updateAll(Language model) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#save(java.lang.Object)
	 */
	public Integer save(Language model) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#deleteByExample(java.util.Map)
	 */
	public Integer deleteByExample(Map example) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#countObjects(java.util.Map)
	 */
	public Integer countObjects(Map example) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#insertSelective(java.lang.Object)
	 */
	public Integer insertSelective(Language model) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#deleteByPrimaryKey(java.lang.Integer)
	 */
	public Integer deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#selectByPrimaryKey(java.lang.Integer)
	 */
	public Language selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#selectFieldsByPrimaryKey(java.lang.String, java.lang.Integer)
	 */
	public Language selectFieldsByPrimaryKey(String fieldColumns, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#selectByExample(java.util.Map)
	 */
	public List<Language> selectByExample(Map example) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.LanguageService#deleteLanguageList(java.lang.Integer[])
	 */
	public void deleteLanguageList(Integer[] ids) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.LanguageService#saveLanguage(com.delmar.core.model.Language, java.util.List)
	 */
	public void saveLanguage(Language language, List<LanguageTrl> trlList) {
		// TODO Auto-generated method stub
		
	}

}
