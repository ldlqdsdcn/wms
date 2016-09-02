/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
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
