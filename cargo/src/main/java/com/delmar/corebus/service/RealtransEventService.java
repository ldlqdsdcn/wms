
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.rate.service.exp.OutNumberException;
import com.delmar.sys.model.User;

/**
 * @author 刘大磊 2015-03-25 15:47:49
 */
public interface RealtransEventService extends CoreService<RealtransEvent> {
	/**
	 * @param ids
	 */
	public void deleteRealtransEventList(Integer[] ids);
	
	public RealtransEvent selectCurrentRealtransEvent(Integer realtransId);
	
	public List<RealtransEvent> getRealtransEventListByTransId(Integer transId);

	
	public List<RealtransEvent> getRealtransEventListByOrderNoAndHawbBillNo(String orderNo,String hawbBillNo,User user) throws DataNotFondException;
	
	
	/**
	 * 获取最终单据状态
	 * @param orderNo
	 * @param hawbBillNo
	 * @return
	 */
	public RealtransEvent getFinalRealtransEventByOrderNoAndHawbBillNo(String orderNo,String hawbBillNo,User user) throws OutNumberException,DataNotFondException;
}