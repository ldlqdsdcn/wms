/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.OrgDao;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("orgService")
public class OrgServiceImpl extends CoreServiceImpl<Org> implements
		OrgService {
	@Autowired
	private OrgDao orgDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Org> getCoreDao() {
		return orgDao;
	}
	
	/** (non-Javadoc)
	 * @see com.delmar.sys.service.OrgService#getOrgByValue(String)
	 */
	@Override
	public Org getOrgByValue(String orgValue) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("value", orgValue);
		List<Org> orgs=orgDao.getOrgs(map);
		if(orgs==null||orgs.size()==0)
		{
			return null;
		}
		return 	orgs.get(0);
	}
	

	
}
