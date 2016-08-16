/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.dao;

import com.delmar.core.model.CoreCreatCode;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-07-31 10:10:09
 */
public interface CoreCreatCodeDao extends CoreDao<CoreCreatCode> {

	
	public void updateMaxValue(CoreCreatCode creatCode);
	public Integer getMaxValue(String code, Integer clientId);
}
