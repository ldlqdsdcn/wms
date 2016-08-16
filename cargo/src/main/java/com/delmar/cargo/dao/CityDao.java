/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.cargo.dao;

import java.util.List;

import com.delmar.cargo.model.CityInfo;
import com.delmar.core.dao.CoreHibernateDao;

/**
 * @author 刘大磊 2015年4月27日 下午2:24:04
 */
public interface CityDao extends CoreHibernateDao<CityInfo> {
	public List<CityInfo> getCityList();
	
}
