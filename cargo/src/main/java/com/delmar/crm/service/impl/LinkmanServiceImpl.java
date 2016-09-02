/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.LinkmanDao;
import com.delmar.crm.model.Linkman;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.service.LinkrecordService;

/**
 * @author 刘大磊 22015-03-04 16:06:05
 */
@Service("linkmanService")
public class LinkmanServiceImpl extends CoreServiceImpl<Linkman> implements
		LinkmanService {
	@Autowired
	private LinkmanDao linkmanDao;
	@Autowired
	private LinkrecordService linkrecordService;	
	@Autowired
	private CustomerExterService customerExterService;	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Linkman> getCoreDao() {
		return linkmanDao;
	}
	public void deleteLinkmanList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			this.deleteById(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.crm.service.LinkmanService#getLinkman(java.lang.Integer, java.lang.String)
	 */
	public Linkman getLinkman(Integer customerId, String ismain) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("customerId",customerId);
		param.put("ismain", ismain);
		List<Linkman> list=this.selectByExample(param);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	
	public void updateTimesData(Integer id)
	{
		linkmanDao.updateTimesData(id);	
	}
	

	
	public void deleteByCustomerId(Integer customerId)
	{
		
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("customerId", customerId);
		
		List<Linkman> linkmanList=this.selectByExample(param);
		
		for (Linkman obj:linkmanList)
		{
			this.deleteById(obj.getId());
		}

	}
	
	
	public void deleteById(Integer id)
	{
		Linkman linkmanObj=linkmanDao.selectByPrimaryKey(id);
		//之前首先要删除和这个联系人有关的拜访记录

		linkrecordService.deleteByLinkmanId(id);
		//删除联系人
		linkmanDao.deleteByPrimaryKey(id);
		//更新客户档案
		customerExterService.updateTimesData(linkmanObj.getCustomerId());
	}
		
	public String getNamebyId(Integer id)
	{
		return linkmanDao.getNamebyId(id);	
	}	
}
