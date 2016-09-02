
/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.corebus.model.Event;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2015-03-18 16:04:12
 */
public interface RealTransService extends CoreService<RealTrans> {
	/**
	 * @param ids
	 */
	public void deleteRealTransList(Integer[] ids);
	public String generateHawbBillNo();
	public String generateMasterBillNo();
	/**
	 * Add realtrans event
	 * @param id
	 * @param seventtype
	 */
	public void saveEvent(Event event,RealtransEvent realtransEvent);
	
	/**
	 * */
	public List<RealTrans> getRealtransList(String orderNo,User user) throws DataNotFondException ;
}