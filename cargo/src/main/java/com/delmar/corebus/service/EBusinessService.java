
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service;

import java.util.List;

import com.delmar.core.service.CoreService;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.service.impl.EBusinessParam;
import com.delmar.corebus.service.impl.SopBusModel;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;

/**
 * @author 刘大磊 2014-12-26 10:54:30
 */
public interface EBusinessService extends CoreService<EBusiness> {
	public EBusiness saveEBusiness(EBusinessParam param)throws QuotaParamException,DataNotFondException;
	
	public List<SopBusModel> getSopModelList(String transno);
	
	public void saveGenerateRealTrans(Integer ebusinessId, RealTrans realTrans);
}