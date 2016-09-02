
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service;

import java.util.List;

import com.delmar.core.model.MailInfo;

/**
 * @author 刘大磊 2015-03-25 09:20:22
 */
public interface MailInfoService extends CoreService<MailInfo> {
	/**
	 * @param ids
	 */
	 void deleteMailInfoList(Integer[] ids);
	
	 void updateMailFinishStatus(Integer id);
	 List<MailInfo> getPendingMail();
}