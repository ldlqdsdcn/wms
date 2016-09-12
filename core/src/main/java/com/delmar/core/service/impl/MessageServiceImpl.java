/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.MessageDao;
import com.delmar.core.model.Message;
import com.delmar.core.service.MessageService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import com.delmar.core.model.MessageTrl;
import com.delmar.core.dao.MessageTrlDao;
/**
 * @author 刘大磊 2016-09-12 10:38:52
 */
@Service("messageService")
public class MessageServiceImpl extends CoreServiceImpl<Message> implements
		MessageService {
	@Autowired
	private MessageDao messageDao;
    @Autowired
    private MessageTrlDao messageTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Message> getCoreDao() {
		return messageDao;
	}
	public void deleteMessageList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveMessage(Message message,List<MessageTrl> messageTrlList) {
	Integer id=save(message);
	Date now=new Date();
		for(MessageTrl messageTrl: messageTrlList)
		{
			messageTrl.setMessageId(id);
			messageTrlDao.save(messageTrl);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> messageTrlParam=new HashMap<String,Object>();
messageTrlParam.put("messageId",id);
messageTrlDao.deleteByExample(messageTrlParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<MessageTrl> getMessageTrlListByMessageId(Integer messageId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("messageId",messageId);
		if(messageId==null)
		{
			return new java.util.ArrayList<MessageTrl>();
		}
		return messageTrlDao.selectByExample(param);
	}
}
