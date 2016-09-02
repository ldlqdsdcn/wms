/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.PortTrlDao;
import com.delmar.base.model.PortTrl;
import com.delmar.base.service.PortTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-02-06 16:25:41
 */
@Service("portTrlService")
public class PortTrlServiceImpl extends CoreServiceImpl<PortTrl> implements
		PortTrlService {
	@Autowired
	private PortTrlDao portTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<PortTrl> getCoreDao() {
		return portTrlDao;
	}
	public void deletePortTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			portTrlDao.deleteByPrimaryKey(id);
		}
	}

	
	public PortTrl getPortTrl(String language, Integer portId) {
		if(portId==null)
		{
			return null;
		}
	   Map param=new HashMap();
	   param.put("portId",portId);
	   param.put("language",language);
	   PortTrl portTrl=null;
	   try
	   {
		   portTrl=portTrlDao.getByExample(param);
	   }
	   catch(Exception e)
	   {
		   logger.error("获取CityTrl发送错误",e);
	   }

	   
	   return portTrl;
	}	
}
