/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.corebus.service.RealtransEventService;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-03-25 15:57:17
 */
public class RealtransEventAction extends CoreEditPrivAction {
	private RealtransEvent  realtransEvent;
	
	@Autowired
	private RealtransEventService realtransEventService;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "实际业务时间记录");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "realtransEvent";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		realtransEventService.deleteByPrimaryKey(realtransEvent.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		realtransEventService.deleteRealtransEventList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return realtransEvent.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 realtransEvent= realtransEventService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return realtransEventService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		realtransEvent=new RealtransEvent();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		realtransEventService.save(realtransEvent);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public RealtransEvent getRealtransEvent() {
		return realtransEvent;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setRealtransEvent(RealtransEvent realtransEvent) {
		this.realtransEvent = realtransEvent;
	}

}
