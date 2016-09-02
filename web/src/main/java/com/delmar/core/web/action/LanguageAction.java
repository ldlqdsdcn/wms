/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.model.Language;
import com.delmar.core.model.LanguageTrl;
import com.delmar.core.service.LanguageService;
import com.delmar.core.service.LanguageTrlService;
import com.delmar.core.web.bean.UserResource;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-02-05 14:01:11
 */
public class LanguageAction extends CoreEditPrivAction {
	private Language  language;
	private List<LanguageTrl> languageTrlList;
	
	@Autowired
	private LanguageService languageService;
	@Autowired
	private LanguageTrlService languageTrlService;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "多语言编辑");
	}
	
	private void init()
	{
		if(language.getId()!=null)
		{
			Map  param=new HashMap();
			param.put("languageCode", language.getCode());
			languageTrlList=	languageTrlService.selectByExample(param);
			
			List<Language> list=languageService.selectByExample(null);
			List<Language> noList=new ArrayList<Language>();
			if(languageTrlList==null||languageTrlList.size()==0)
			{
				languageTrlList=new ArrayList<LanguageTrl>();
				noList=list;
			}
			else
			{
				
				for(Language lang:list)
				{
					boolean has=false;
					for(LanguageTrl trl:languageTrlList)
					{
						if(trl.getLanguage().equals(lang.getCode()))
						{
							has=true;
							break;
						}
					}
					if(!has)
					{
						noList.add(lang);
					}
				}
			}
			for(Language lang:noList)
			{
				LanguageTrl trl=new LanguageTrl();
				trl.setLanguage(lang.getCode());
				trl.setLanguageCode(language.getCode());
				languageTrlList.add(trl);
			}
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "language";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		languageService.deleteByPrimaryKey(language.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		languageService.deleteLanguageList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return language.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 language= languageService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return languageService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		language=new Language();
		languageTrlList=null;
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		if(!language.isnew())
		{
			language.setCode(null);
		}
		languageService.saveLanguage(language,this.languageTrlList);
		this.id=language.getId();
		editForm();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * @return the languageTrlList
	 */
	public List<LanguageTrl> getLanguageTrlList() {
		return languageTrlList;
	}

	/**
	 * @param languageTrlList the languageTrlList to set
	 */
	public void setLanguageTrlList(List<LanguageTrl> languageTrlList) {
		this.languageTrlList = languageTrlList;
	}

}
