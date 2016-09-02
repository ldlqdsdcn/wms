/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.common.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.common.dao.DelmarFileDao;
import com.delmar.common.model.DelmarFile;
import com.delmar.common.service.DelmarFileService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-16 08:53:59
 */
@Service("delmarFileService")
public class DelmarFileServiceImpl extends CoreServiceImpl<DelmarFile> implements
		DelmarFileService {
	@Autowired
	private DelmarFileDao delmarFileDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<DelmarFile> getCoreDao() {
		return delmarFileDao;
	}
	public void deleteDelmarFileList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		DelmarFile mf=	delmarFileDao.selectByPrimaryKey(id);
		File file=new File(mf.getPath());
		if(file.exists())
		{
			file.delete();
		}
		return super.deleteByPrimaryKey(id);
	}
}
