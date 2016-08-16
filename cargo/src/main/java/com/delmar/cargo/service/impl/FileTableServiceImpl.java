package com.delmar.cargo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.FileTableDao;
import com.delmar.cargo.model.FileTable;
import com.delmar.cargo.service.FileTableService;
import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.service.impl.CoreHibernateServiceImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:28:07 
 * 类说明 
 */
@Service("fileTableService")
public class FileTableServiceImpl extends CoreHibernateServiceImpl<FileTable> implements FileTableService {

	@Autowired
	private FileTableDao fileTableDao;
	
	
	@Override
	public CoreHibernateDao<FileTable> getCoreDao()
	{
	   return fileTableDao;	
	}
	
	
	public String getTrustFileCodeByHawb(String hawb)
	{
		return fileTableDao.getTrustFileCodeByHawb(hawb);	
	}
	
	public String getTrustFileCodeByMawb(String mawb)
	{
		return fileTableDao.getTrustFileCodeByMawb(mawb);
	}
	
}
