/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.model.EventTypeStatus;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.RealTransDao;
import com.delmar.corebus.dao.RealtransEventDao;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.corebus.service.EBusinessService;
import com.delmar.corebus.service.RealtransEventService;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.rate.service.exp.OutNumberException;
import com.delmar.sys.model.User;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22015-03-25 15:47:49
 */
@Service("realtransEventService")
public class RealtransEventServiceImpl extends CoreServiceImpl<RealtransEvent> implements
		RealtransEventService {
	@Autowired
	private RealtransEventDao realtransEventDao;
	@Autowired
	private RealTransDao realTransDao;
	@Autowired
	private EBusinessService ebusinessService;	
	
	
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<RealtransEvent> getCoreDao() {
		return realtransEventDao;
	}
	public void deleteRealtransEventList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			realtransEventDao.deleteByPrimaryKey(id);
		}
	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RealtransEventService#selectCurrentRealtransEvent(java.lang.Integer)
	 */
	public RealtransEvent selectCurrentRealtransEvent(Integer realtransId) {
		// TODO Auto-generated method stub
		return realtransEventDao.selectCurrentRealtransEvent(realtransId);
	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RealtransEventService#getRealtransEventListByTransId(java.lang.Integer)
	 */
	public List<RealtransEvent> getRealtransEventListByTransId(Integer transId) {
		
		return realtransEventDao.getRealtransEventListByTransId(transId);
	}

	
	
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RealtransEventService#getRealtransEventListByOrderNoAndHawbBillNo(java.lang.String, java.lang.String)
	 */
	public List<RealtransEvent> getRealtransEventListByOrderNoAndHawbBillNo(
			String orderNo, String hawbBillNo,User user) throws DataNotFondException {
		if(StringUtil.isEmpty(hawbBillNo))
		{
			hawbBillNo=null;
		}
		
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
		
		List<RealTrans> realTransList=null;
		if (hawbBillNo!=null)
		{
			realTransList=realTransDao.getRealTransByHawbBillNo(orderNo, hawbBillNo,user.getClientId());
			//此处检查是否
			if ((realTransList==null)||(realTransList.size()==0))
			{
				throw new DataNotFondException("具有相应的订单，但根据提单号，查询不到相应的提单信息！");
			}
		} else
		{
			realTransList=realTransBusList;
		}
			
		
	
		
		List<RealtransEvent> list=new ArrayList<RealtransEvent>();
		
		for(RealTrans rt:realTransList)
		{
			List<RealtransEvent> reList=realtransEventDao.getRealtransEventListByTransId(rt.getId());
			if(reList!=null&&reList.size()>0)
			{
				list.addAll(reList);
			}
		}
		// realtransEventDao.getRealtransEventListByTransId(transId)
		return list;
	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.RealtransEventService#getFinalRealtransEventByOrderNoAndHawbBillNo(java.lang.String, java.lang.String)
	 */
	public RealtransEvent getFinalRealtransEventByOrderNoAndHawbBillNo(
			String orderNo, String hawbBillNo,User user) throws OutNumberException,DataNotFondException{
		if(StringUtil.isEmpty(hawbBillNo))
		{
			hawbBillNo=null;
		}
		
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
		
		List<RealTrans> realTransList=null;
		if (hawbBillNo!=null)
		{
			realTransList=realTransDao.getRealTransByHawbBillNo(orderNo, hawbBillNo,user.getClientId());
			//此处检查是否
			if ((realTransList==null)||(realTransList.size()==0))
			{
				throw new DataNotFondException("具有相应的订单，但根据提单号，查询不到相应的提单信息！");
			}
		} else
		{
			realTransList=realTransBusList;
		}
		
				
		
		if(realTransList!=null&&realTransList.size()>0)
		{
			if(realTransList.size()>1)
			{
				throw new OutNumberException("有多条分单，获取最终事件失败");
			}
			
			//List<RealtransEvent> list=new ArrayList<RealtransEvent>();
			RealTrans rt=realTransList.get(0);
			//EventTypeStatus
			RealtransEvent re=realtransEventDao.selectOneRealtransEvent(rt.getId(),EventTypeStatus.END_STATUS.getState());
			return re;
			
		}
		return null;
	}
	
}
