
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
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
	public List<Datadict> getDatadictListByTypeId(Integer typeId,Integer clientId);
	public List<Datadict> getDatadictListByTypeIdAndDate(Integer typeId,String requestDate,Integer clientId);
	public List<Datadict> getDatadictListByTypeValue(String value);
	public List<Datadict> getDatadictListByTypeValue(String value,Integer clientId);
	/**
	 * @param datadict
	 * @param datadictTrlList
	 */
	void saveDatadict(Datadict datadict, List<DatadictTrl> datadictTrlList);	
	public List<DatadictTrl> getDatadictTrlByTypeId(Integer typeId,String language,Integer clientId);	
	public List<DatadictTrl> getDatadictTrlByTypeIdAndDate(Integer typeId,String requetDate,String language,Integer clientId);
	public List<DatadictTrl> getDatadictTrlByValue(String value,String language);
	public List<DatadictTrl> getDatadictTrlByValue(String value,String language,Integer clientId);
	public DatadictTrl getTrlByDataId(Integer dataId,String language);
	public String getTransName(Integer dataId,String language);
	public void saveIndexOrder(Integer newIndexOrder,Integer newId,Integer otherIndexOrder,Integer otherId);

}