/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.common.dao.FileSettingDao;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.FileSettingService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-04-16 08:53:59
 */
@Service("fileSettingService")
public class FileSettingServiceImpl extends CoreServiceImpl<FileSetting> implements
		FileSettingService {
	@Autowired
	private FileSettingDao fileSettingDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<FileSetting> getCoreDao() {
		return fileSettingDao;
	}
	public void deleteFileSettingList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			fileSettingDao.deleteByPrimaryKey(id);
		}
	}
	
}
