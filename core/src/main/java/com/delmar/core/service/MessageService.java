/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/package com.delmar.core.service;
import com.delmar.core.model.Message;
import com.delmar.core.service.CoreService;
import com.delmar.core.model.MessageTrl;
import java.util.List;
/**
 * @author 刘大磊 2016-09-12 10:38:52
 */
public interface MessageService extends CoreService<Message> {
	void deleteMessageList(Integer[] ids);
	List<MessageTrl> getMessageTrlListByMessageId(Integer messageId);
	Integer saveMessage(Message message,List<MessageTrl> messageTrlList);
}