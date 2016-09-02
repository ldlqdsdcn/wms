/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.dao;

import java.util.List;

import com.delmar.core.dao.CoreDao;
import com.delmar.corebus.model.RealTrans;

/**
 * @author 刘大磊 2015-03-18 16:04:12
 */
public interface RealTransDao extends CoreDao<RealTrans> {
	public String selectMaxHawbBillNo();
	public String selectMaxMasterBillNo();
	
	public List<RealTrans>  getRealTransByHawbBillNo(String orderno,String hawbBillNo,Integer clientId);
}
