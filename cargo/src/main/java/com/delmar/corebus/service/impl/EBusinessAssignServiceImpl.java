/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.EBusinessAssignDao;
import com.delmar.corebus.model.EBusinessAssign;
import com.delmar.corebus.service.EBusinessAssignService;

/**
 * @author 刘大磊 22015-03-18 16:04:12
 */
@Service("eBusinessAssignService")
public class EBusinessAssignServiceImpl extends CoreServiceImpl<EBusinessAssign> implements
		EBusinessAssignService {
	@Autowired
	private EBusinessAssignDao eBusinessAssignDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<EBusinessAssign> getCoreDao() {
		return eBusinessAssignDao;
	}
	public void deleteEBusinessAssignList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			eBusinessAssignDao.deleteByPrimaryKey(id);
		}
	}
	
}
