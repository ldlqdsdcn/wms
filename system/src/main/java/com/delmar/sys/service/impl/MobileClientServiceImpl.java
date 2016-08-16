/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.common.dao.FileRelationDao;
import com.delmar.common.model.FileRelation;
import com.delmar.common.service.DelmarFileService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.MobileClientDao;
import com.delmar.sys.model.MobileClient;
import com.delmar.sys.service.MobileClientService;

/**
 * @author 刘大磊 22015-07-21 18:27:53
 */
@Service("mobileClientService")
public class MobileClientServiceImpl extends CoreServiceImpl<MobileClient> implements
		MobileClientService {
	@Autowired
	private MobileClientDao mobileClientDao;
	@Autowired
	private FileRelationDao fileRelationDao;
	@Autowired
	private DelmarFileService delmarFileService;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<MobileClient> getCoreDao() {
		return mobileClientDao;
	}
	public void deleteMobileClientList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			mobileClientDao.deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.MobileClientService#save(com.delmar.sys.model.MobileClient, java.util.List)
	 */
	@Override
	public void save(MobileClient mobileClient,
			List<FileRelation> fileRelationList) {
		save(mobileClient);
		if(fileRelationList!=null)
		{
			for(FileRelation fr:fileRelationList)
			{
				delmarFileService.save(fr.getDelmarFile());
			}
		}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.MobileClientService#getLastVersionCode()
	 */
	@Override
	public MobileClient getMobileClientByMaxVersion() {

		return mobileClientDao.getMobileClientByMaxVersion();
	}
	
}
