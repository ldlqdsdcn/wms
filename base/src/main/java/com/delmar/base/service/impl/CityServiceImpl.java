/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import com.delmar.base.dao.CityDao;
import com.delmar.base.dao.CityTrlDao;
import com.delmar.base.model.City;
import com.delmar.base.model.CityTrl;
import com.delmar.base.service.CityService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 22014-12-29 09:54:05
 */
@Service("cityService")
public class CityServiceImpl extends CoreServiceImpl<City> implements
		CityService {
	@Autowired
	private CityDao cityDao;
	@Autowired
	private CityTrlDao cityTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<City> getCoreDao() {
		return cityDao;
	}
	/** (non-Javadoc)
	 */
	public City getRelateCity(Integer relateCityId) {
		
		return cityDao.selectByPrimaryKey(relateCityId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.CityService#deleteCityList(java.lang.Integer[])
	 */
	public void deleteCityList(Integer[] ids) {
	if(ids!=null)
	{
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}
		
	}
/* (non-Javadoc)
 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
 */
@Override
public Integer deleteByPrimaryKey(Integer id) {
	Map<String,Object> param=new HashMap<>();
	param.put("cityId", id);
	cityTrlDao.deleteByExample(param);
	return super.deleteByPrimaryKey(id);
}
/* (non-Javadoc)
 * @see com.delmar.base.service.CityService#saveCity(com.delmar.base.model.City, java.util.List)
 */
public void saveCity(City city, List<CityTrl> cityTrlList) {

	this.save(city);

	if(cityTrlList!=null)
	{
		for(CityTrl trl:cityTrlList)
		{
			trl.setCityId(city.getId());
			cityTrlDao.save(trl);
		}
	}
}
/* (non-Javadoc)
 * @see com.delmar.base.service.CityService#getCityTrl(java.lang.String, java.lang.String)
 */
public CityTrl getCityTrl(String language, Integer cityId) {
	if(cityId==null)
	{
		return null;
	}
	Map<String,Object> param=new HashMap<>();
   param.put("cityId",cityId);
   param.put("language",language);
   CityTrl cityTrl=null;
   try
   {
   cityTrl=cityTrlDao.getByExample(param);
   }
   catch(Exception e)
   {
	   logger.error("获取CityTrl发送错误",e);
   }
   if(cityTrl==null)
   {
	   City city=cityDao.selectByPrimaryKey(cityId);
	   if(city==null)
	   {
		   return null;
	   }
	   cityTrl=new CityTrl();
	   cityTrl.setCityId(city.getId());
	   cityTrl.setLanguage(language);
	   cityTrl.setName(city.getCname());
   }
   
   return cityTrl;
}
	
}
