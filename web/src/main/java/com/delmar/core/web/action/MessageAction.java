/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.model.Message;
import com.delmar.core.service.MessageService;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.core.model.MessageTrl;
/**
 * @author 刘大磊 2016-09-12 10:38:52
 */
public class MessageAction extends CoreEditPrivAction {
	private Message  message;
	private List<MessageTrl> messageTrlList=new ArrayList<MessageTrl>();;
@Autowired
private LanguageService languageService;
	@Autowired
	private MessageService messageService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "message";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		messageService.deleteByPrimaryKey(message.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		messageService.deleteMessageList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return message.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
public void editForm() {
message= messageService.selectByPrimaryKey(id);

		messageTrlList=messageService.getMessageTrlListByMessageId(id);
            List<Language> list=languageService.selectByExample(null);
            List<Language> noList=new ArrayList<Language>();
                for(Language lang:list)
                {
					boolean has=false;
					for(MessageTrl trl:messageTrlList)
					{
						if(trl.getLanguage().equals(lang.getCode()))
						{
							has=true;
							break;
						}
					}
					if(!has)
					{
					noList.add(lang);
					}
                }
                for(Language lang:noList)
                {
					MessageTrl trl=new MessageTrl();
					trl.setLanguage(lang.getCode());
					trl.setMessageId(id);
					messageTrlList.add(trl);
                }
}
/* (non-Javadoc)
* @see com.delmar.core.web.action.CoreEditPrivAction#search()
*/
@Override
public List search() {
Map<String,Object> param=new HashMap();
param.put("searchString",getSearchWhere());
return messageService.selectByExample(param);
}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
public void createForm() {
message=new Message();
	messageTrlList=new ArrayList<MessageTrl>();
        List
        <Language> languageList=languageService.selectByExample(null);
            for(Language lang:languageList)
            {
		MessageTrl trl=new MessageTrl();
            trl.setLanguage(lang.getCode());
		messageTrlList.add(trl);
            }
    }
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
		messageService.saveMessage(message,messageTrlList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
public List<MessageTrl> getMessageTrlList()
{
	return messageTrlList;
}
public void setMessageTrlList(List<MessageTrl> messageTrlList)
{
	this.messageTrlList=messageTrlList;
}
}
