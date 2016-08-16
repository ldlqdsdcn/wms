/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.crm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.crm.dao.LinkrecordDao;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.BusForecastService;
import com.delmar.crm.service.CustomerExterService;
import com.delmar.crm.service.LinkmanService;
import com.delmar.crm.service.LinkrecordService;

/**
 * @author 刘大磊 22015-03-11 13:53:09
 */
@Service("linkrecordService")
public class LinkrecordServiceImpl extends CoreServiceImpl<Linkrecord> implements
		LinkrecordService {
	@Autowired
	private LinkrecordDao linkrecordDao;
	@Autowired
	private CustomerExterService customerExterService;	
	@Autowired
	private LinkmanService linkmanService;
	@Autowired
	private BusForecastService  busForecastService;		
	

	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Linkrecord> getCoreDao() {
		return linkrecordDao;
	}
	public void deleteLinkrecordList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			//此处需要进行检查
			this.deleteById(id);
		}
	}
	
	
    public void deleteByLinkmanId(Integer linkmanId)
    {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("linkmanId", linkmanId);
		List<Linkrecord> linkrecordList=this.selectByExample(param);
		
		for (Linkrecord obj:linkrecordList)
		{
			this.deleteById(obj.getId());
		}
		
    }
    
    

    public void deleteById(Integer id)
    {
    	Linkrecord linkrecordObj=this.selectByPrimaryKey(id);
    	
    	
    	
    	busForecastService.deleteBusForecastByRecordId(id);
    	linkrecordDao.deleteByPrimaryKey(id);
    	
		//执行日期的更新
		customerExterService.updateTimesData(linkrecordObj.getCustomerId());
		linkmanService.updateTimesData(linkrecordObj.getLinkmanId());
		
		
		
    }
	

}
