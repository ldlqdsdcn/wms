/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.dao;

import com.delmar.core.model.MailInfo;
import com.delmar.core.dao.CoreDao;

/**
 * @author 刘大磊 2015-03-25 09:20:22
 */
public interface MailInfoDao extends CoreDao<MailInfo> {

	
	 void updateMailFinishStatus(Integer id);
}
