/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                                * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.base.web.action;

import com.delmar.base.model.City;
import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CityTrlService;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.utils.ResourceMessage;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 刘大磊 2015-02-09 20:06:08
 */
public class CityAction extends CoreEditPrivAction {
	private City  city;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CityTrlService cityTrlService;
	@Autowired
	private LanguageService languageService;
	
	private List<CityTrl> cityTrlList;
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "城市代码维护");
	}
	
	private void init()
	{

		
			if(city.getId()!=null)
				{
					Map  param=new HashMap();
					param.put("cityId", city.getId());
					cityTrlList=	cityTrlService.selectByExample(param);
					
					List<Language> list=languageService.selectByExample(null);
					List<Language> noList=new ArrayList<Language>();
					if(cityTrlList==null||cityTrlList.size()==0)
					{
						cityTrlList=new ArrayList<CityTrl>();
						noList=list;
					}
					else
					{
						for(Language lang:list)
						{
							boolean has=false;
							for(CityTrl trl:cityTrlList)
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
						CityTrl trl=new CityTrl();
						trl.setLanguage(lang.getCode());
						trl.setCityId(city.getId());
						cityTrlList.add(trl);
					}
					
				}
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "city";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		cityService.deleteByPrimaryKey(city.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		cityService.deleteCityList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return city.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 city= cityService.selectByPrimaryKey(id);
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		 city.setParent(cityService.getCityTrl(ur.getLocale().toString(), city.getParentId()));
		 city.setRelateCity(cityService.getCityTrl(ur.getLocale().toString(), city.getRelateCityId()));
		 init();

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return cityService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		city=new City();
		init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		cityService.saveCity(city, cityTrlList);
		this.id=city.getId();
		edit();
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * @return the cityTrlList
	 */
	public List<CityTrl> getCityTrlList() {
		return cityTrlList;
	}

	/**
	 * @param cityTrlList the cityTrlList to set
	 */
	public void setCityTrlList(List<CityTrl> cityTrlList) {
		this.cityTrlList = cityTrlList;
	}



}
