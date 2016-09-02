/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.common.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.common.dao.FileRelationDao;
import com.delmar.common.model.FileRelation;
import com.delmar.common.service.DelmarFileService;
import com.delmar.common.service.FileRelationService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-16 08:53:59
 */
@Service("fileRelationService")
public class FileRelationServiceImpl extends CoreServiceImpl<FileRelation> implements
		FileRelationService {
	@Autowired
	private FileRelationDao fileRelationDao;
	@Autowired
	private DelmarFileService delmarFileService;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<FileRelation> getCoreDao() {
		return fileRelationDao;
	}
	public void deleteFileRelationList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.common.service.FileRelationService#deleteFileRelation(com.delmar.common.model.FileRelation)
	 */
	public void deleteFileRelation(FileRelation fileRelation) {
		this.deleteByPrimaryKey(fileRelation.getId());
		delmarFileService.deleteByPrimaryKey(fileRelation.getDelmarFile().getId());
		
	}
	
}
