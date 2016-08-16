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

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.sys.dao.ClientDao;
import com.delmar.sys.model.Client;
import com.delmar.sys.service.ClientService;

/**
 * @author 刘大磊 22014-12-23 14:47:25
 */
@Service("clientService")
public class ClientServiceImpl extends CoreServiceImpl<Client> implements
		ClientService {
	@Autowired
	private ClientDao clientDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Client> getCoreDao() {
		return clientDao;
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ClientService#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		for(Integer id:ids)
		{
			clientDao.deleteByPrimaryKey(id);
		}
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.sys.service.ClientService#getClientByValue(java.lang.String)
	 */
	@Override
	public Client getClientByValue(String value) {
		Map<String,Object> param=new HashMap<String,Object>();
		
		List<Client> list=clientDao.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	
}
