/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.PortDao;
import com.delmar.base.dao.PortWebserviceDao;
import com.delmar.base.model.Port;
import com.delmar.base.model.PortWebservice;
import com.delmar.base.service.PortWebserviceService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-19 11:21:06
 */
@Service("portWebserviceService")
public class PortWebserviceServiceImpl extends CoreServiceImpl<PortWebservice> implements
		PortWebserviceService {
	@Autowired
	private PortWebserviceDao portWebserviceDao;
	@Autowired
	private PortDao portDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<PortWebservice> getCoreDao() {
		return portWebserviceDao;
	}
	public void deletePortWebserviceList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			portWebserviceDao.deleteByPrimaryKey(id);
		}
	}
	/**
	 *  根据 webservice下发的 港口名称，关联系统中的港口名称
	 * @see com.delmar.base.service.PortWebserviceService#getPortByPortWebserviceName(String,String,Integer)
	 */
	public Port getPortByPortWebserviceName(String name, String serviceType, Integer provider) {
		Map<String,Object> param=new HashMap<>();
		param.put("queryname", name);
		param.put("servicetype", serviceType);
		param.put("clientId", provider);
		List<PortWebservice>list=portWebserviceDao.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			PortWebservice pw= list.get(0);
		if(pw.getPortId()==null)
		{
			return null;
		}
			return portDao.selectByPrimaryKey(pw.getPortId());
		
		}
		return null;
	}
	
}
