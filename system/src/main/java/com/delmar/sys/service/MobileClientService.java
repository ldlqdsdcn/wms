
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service;

import java.util.List;

import com.delmar.common.model.FileRelation;
import com.delmar.core.service.CoreService;
import com.delmar.sys.model.MobileClient;

/**
 * @author 刘大磊 2015-07-21 18:27:53
 */
public interface MobileClientService extends CoreService<MobileClient> {
	/**
	 * @param ids
	 */
	public void deleteMobileClientList(Integer[] ids);

	/**
	 * @param mobileClient
	 * @param fileRelationList
	 */
	public void save(MobileClient mobileClient,
			List<FileRelation> fileRelationList);
	
	public MobileClient getMobileClientByMaxVersion();

}