/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.EventDao;
import com.delmar.corebus.dao.RealTransDao;
import com.delmar.corebus.dao.RealtransEventDao;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.Event;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.corebus.service.EBusinessService;
import com.delmar.corebus.service.RealTransService;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.sys.model.User;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22015-03-18 16:04:12
 */
@Service("realTransService")
public class RealTransServiceImpl extends CoreServiceImpl<RealTrans> implements
		RealTransService {
	@Autowired
	private RealTransDao realTransDao;
	@Autowired
	private RealtransEventDao realtransEventDao;
	@Autowired
	private EventDao eventDao;
	@Autowired
	private EBusinessService ebusinessService;		
	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<RealTrans> getCoreDao() {
		return realTransDao;
	}
	public void deleteRealTransList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			realTransDao.deleteByPrimaryKey(id);
		}
	}
	public String generateMasterBillNo()
	{
		String prefix="MASTER";
		//modeKey
		String datemonth=DateTimeDecorator.getDateMonth(new Date());
		prefix=prefix+datemonth;
		String maxvalue=realTransDao.selectMaxMasterBillNo();
		if ((StringUtil.isNotEmpty(maxvalue))&&(maxvalue.indexOf(prefix)>-1))
		{
			String value=maxvalue.replace(prefix, "");
			int nextvalue=Integer.parseInt(value)+1;
			 String  append= String.format("%010d", nextvalue);    
			 return prefix+append;
		}
		else
		{
			 String  append= String.format("%010d", 1);    
			 return prefix+append;
		}
		
	}
	public String generateHawbBillNo()
	{
		String prefix="HAWB";
		//modeKey
		String datemonth=DateTimeDecorator.getDateMonth(new Date());
		prefix=prefix+datemonth;
		String maxvalue=realTransDao.selectMaxHawbBillNo();
		if ((StringUtil.isNotEmpty(maxvalue))&&(maxvalue.indexOf(prefix)>-1))
		{
			String value=maxvalue.replace(prefix, "");
			int nextvalue=Integer.parseInt(value)+1;
			String  append= String.format("%010d", nextvalue);    
			return prefix+append;
		}
		else
		{
			 String  append= String.format("%010d", 1);    
			 return prefix+append;
		}
	}
	/** (non-Javadoc)
	 * @see com.delmar.rate.service.RealTransService#saveEvent(Integer, Integer)
	 */
	public void saveEvent(Event event,RealtransEvent realtransEvent) {		
		
		this.eventDao.save(event);
		realtransEvent.setEventId(event.getId());
		this.realtransEventDao.save(realtransEvent);

		
	}
	
	
	public List<RealTrans> getRealtransList(String orderNo,User user) throws DataNotFondException
	{
		//此处首先要检查EBusiness是否具有记录
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("businessno", orderNo);
		param.put("clientId", user.getClientId());
		EBusiness ebusiness=ebusinessService.getByExample(param);
		if (ebusiness==null)
		{
			throw new DataNotFondException("根据订单号以及提单号，查询不到相应的提单信息");			
		}
		
		List<RealTrans> realTransBusList=realTransDao.getRealTransByHawbBillNo(orderNo, null,user.getClientId());		
		//此处检查是否
		if ((realTransBusList==null)||(realTransBusList.size()==0))
		{
			throw new DataNotFondException("此订单号已经进入队列，正在处理当中，请等待！");
		}
		
		return realTransBusList;
		
	}

}
