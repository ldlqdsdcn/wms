/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.MessageDao;
import com.delmar.core.model.Message;
import com.delmar.core.service.MessageService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22016-08-26 17:30:05
 */
@Service("messageService")
public class MessageServiceImpl extends CoreServiceImpl<Message> implements
		MessageService {
	@Autowired
	private MessageDao messageDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Message> getCoreDao() {
		return messageDao;
	}
	public void deleteMessageList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			messageDao.deleteByPrimaryKey(id);
		}
	}
	
}
