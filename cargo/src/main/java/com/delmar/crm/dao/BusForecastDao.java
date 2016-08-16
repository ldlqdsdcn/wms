/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.dao;

import java.util.List;

import com.delmar.crm.model.BusForecast;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-04-21 08:33:00
 */
public interface BusForecastDao extends CoreDao<BusForecast> {

	public List<BusForecast> selectByLinkRecordId(Integer linkRecordId);
}
