
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
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
	public void deleteMailInfoList(Integer[] ids);
	
	public void updateMailFinishStatus(Integer id);
	public List<MailInfo> getPendingMail();
}