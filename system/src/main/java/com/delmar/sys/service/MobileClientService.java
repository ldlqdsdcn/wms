
/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
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