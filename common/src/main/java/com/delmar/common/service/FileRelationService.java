
/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.common.service;

import com.delmar.common.model.FileRelation;
import com.delmar.core.service.CoreService;

/**
 * @author 刘大磊 2015-04-16 08:53:59
 */
public interface FileRelationService extends CoreService<FileRelation> {
	/**
	 * @param ids
	 */
	public void deleteFileRelationList(Integer[] ids);
	
	public void deleteFileRelation(FileRelation fileRelation);
}