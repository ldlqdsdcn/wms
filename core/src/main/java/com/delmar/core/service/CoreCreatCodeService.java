
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import com.delmar.core.model.CoreCreatCode;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-07-31 10:10:10
 */
public interface CoreCreatCodeService extends CoreService<CoreCreatCode> {
	/**
	 * @param ids
	 */
	 void deleteCoreCreatCodeList(Integer[] ids);
	 Integer getMaxValue(String code, Integer clientId);
	 void updateMaxValue(CoreCreatCode coreCreatCode);
}