
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.base.service;

import java.util.List;

import com.delmar.base.model.Carrier;
import com.delmar.base.model.CarrierTrl;
import com.delmar.common.model.FileRelation;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
public interface CarrierService extends CoreService<Carrier> {
	
	public Integer GetIdByCode(String code) throws Exception;

	/**
	 * @param ids
	 */
	public void deleteCarrierList(Integer[] ids);
	
	/**
	 * 保存运输公司，和国际化信息
	 * @param carrier
	 * @param trlList
	 */
	public void saveCarrier(Carrier carrier,List<CarrierTrl> trlList);

	/**
	 * @param carrier
	 * @param carrierTrlList
	 * @param fileRelationList
	 */
	public void saveCarrier(Carrier carrier, List<CarrierTrl> carrierTrlList,
			List<FileRelation> fileRelationList);
	

}