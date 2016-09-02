/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.common.dao.FtpConnectionDao;
import com.delmar.common.model.FtpConnection;
import com.delmar.common.service.FtpConnectionService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-16 08:53:59
 */
@Service("ftpConnectionService")
public class FtpConnectionServiceImpl extends CoreServiceImpl<FtpConnection> implements
		FtpConnectionService {
	@Autowired
	private FtpConnectionDao ftpConnectionDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<FtpConnection> getCoreDao() {
		return ftpConnectionDao;
	}
	public void deleteFtpConnectionList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			ftpConnectionDao.deleteByPrimaryKey(id);
		}
	}
	
}
