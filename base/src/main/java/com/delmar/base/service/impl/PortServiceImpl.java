/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import com.delmar.base.dao.PortDao;
import com.delmar.base.dao.PortModeDao;
import com.delmar.base.dao.PortTrlDao;
import com.delmar.base.model.Datadict;
import com.delmar.base.model.Port;
import com.delmar.base.model.PortMode;
import com.delmar.base.model.PortTrl;
import com.delmar.base.service.DatadictService;
import com.delmar.base.service.PortService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘大磊 2014-12-22 13:26:54
 */
@Service("portService")
public class PortServiceImpl extends CoreServiceImpl<Port> implements
		PortService {
	@Autowired
	private PortDao portDao;
	@Autowired
	private PortTrlDao portTrlDao;
	@Autowired
	private PortModeDao portModeDao;
	@Autowired
	private DatadictService datadictService;
	@Override
	protected CoreDao<Port> getCoreDao() {
		return portDao;
	}
	
	public Integer getIdByCode(String code)
	{
		Map<String,Object> filterMap=new HashMap<String,Object>();
		
		filterMap.put("portcode",code);
		
		List<Port> objList=selectByExample(filterMap);
		if (objList.size()==0)
		{
			return 0;
		}
		else
		{
			return objList.get(0).getId();
		}
		
		
	}
	public Port getPortByportcodeAndMode(String portcode,String mode)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("mode", mode);
		param.put("portcode", portcode);
		 List<Port> list=portDao.selectPortByMode(param);
		 if(list==null||list.size()==0)
		 {
			 return null;
		 }
		return list.get(0);
	}

	/** (non-Javadoc)
	 * @see com.delmar.base.service.PortService#selectPortByMode(java.util.Map)
	 */
	public List<Port> selectPortByMode(Map<String,Object> param) {
		return portDao.selectPortByMode(param);
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.service.impl.CoreServiceImpl#deleteByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public Integer deleteByPrimaryKey(Integer id) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("basePortId", id);
		this.portModeDao.deleteByExample(param);
		param=new HashMap<String,Object>();
		param.put("portId", id);
		portTrlDao.deleteByExample(param);
		
		return super.deleteByPrimaryKey(id);
	}
	/* (non-Javadoc)
	 * @see com.delmar.base.service.PortService#deletePortList(java.lang.Integer[])
	 */
	public void deletePortList(Integer[] ids) {
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.service.PortService#savePort(com.delmar.base.model.Port, java.util.List, java.util.List)
	 */
	public void savePort(Port port, List<PortTrl> trls, List<Integer> modIds) {
			this.portDao.save(port);
			if(trls!=null)
			{
				for(PortTrl trl:trls)
				{
					trl.setPortId(port.getId());
					this.portTrlDao.save(trl);
				}
			}
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("basePortId", port.getId());
			this.portModeDao.deleteByExample(param);
			if(modIds!=null)
			{
				
				for(Integer id:modIds)
				{
					PortMode pm=new PortMode();
					Datadict dict=datadictService.selectByPrimaryKey(id);
					pm.setMode(dict.getValue());
					pm.setDatadictId(id);
					pm.setBasePortId(port.getId());
					this.portModeDao.save(pm);
				}
			}
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.service.PortService#validatePortType(java.lang.Integer, java.lang.String)
	 */
	public boolean validatePortMode(Integer portId, String mode) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("basePortId", portId);
		List<PortMode> portModeList=portModeDao.selectByExample(param);
		if(portModeList!=null)
		{
			for(PortMode pm:portModeList)
			{
				if(pm.getMode().equals(mode))
				{
					return true;
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.delmar.base.service.PortService#selectPortByCityIdAndMode(java.lang.Integer, java.lang.String)
	 */
	public Port selectPortByCityIdAndMode(Integer cityId, String mode) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("cityId", cityId);
		param.put("accessString", "id in(select base_port_id from base_port_mode where mode='"+mode+"' )");
		List<Port> portList=this.portDao.selectByExample(param);
		if(portList!=null&&portList.size()>0)
		{
			return portList.get(0);
		}
		return null;
	}	

	
}
