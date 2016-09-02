
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import java.util.List;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface DatadictService extends CoreService<Datadict> {

	/**
	 * @param ids
	 */
	void deleteDatadictList(Integer[] ids);
	List<Datadict> getDatadictListByTypeId(Integer typeId, Integer clientId);
	List<Datadict> getDatadictListByTypeIdAndDate(Integer typeId, String requestDate, Integer clientId);
	List<Datadict> getDatadictListByTypeValue(String value);
	List<Datadict> getDatadictListByTypeValue(String value, Integer clientId);
	/**
	 * @param datadict
	 * @param datadictTrlList
	 */
	void saveDatadict(Datadict datadict, List<DatadictTrl> datadictTrlList);	
	List<DatadictTrl> getDatadictTrlByTypeId(Integer typeId, String language, Integer clientId);
	List<DatadictTrl> getDatadictTrlByTypeIdAndDate(Integer typeId, String requetDate, String language, Integer clientId);
	List<DatadictTrl> getDatadictTrlByValue(String value, String language);
	List<DatadictTrl> getDatadictTrlByValue(String value, String language, Integer clientId);
	DatadictTrl getTrlByDataId(Integer dataId, String language);
	String getTransName(Integer dataId, String language);
	void saveIndexOrder(Integer newIndexOrder, Integer newId, Integer otherIndexOrder, Integer otherId);

}