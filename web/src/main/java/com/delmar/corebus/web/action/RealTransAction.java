/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.web.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.EventType;
import com.delmar.base.model.Port;
import com.delmar.base.service.EventTypeService;
import com.delmar.base.service.PortService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.Event;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.RealtransEvent;
import com.delmar.corebus.model.Transaction;
import com.delmar.corebus.service.EBusinessService;
import com.delmar.corebus.service.RealTransService;
import com.delmar.corebus.service.RealtransEventService;
import com.delmar.corebus.service.TransactionService;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatemasterService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;


/**
 * @author 刘大磊 2015-03-25 17:19:06
 */
public class RealTransAction extends CoreEditPrivAction {
	private RealTrans  realTrans;
	private EBusiness ebusiness;
	private Transaction transaction;
	private Ratemaster ratemaster;
	private Port poaPort;
	private Port polPort;
	private Port podPort;
	private List<RealtransEvent> realtransEventList;
	private RealtransEvent currentRealtransEvent;
	private Integer seventtype;
	@Autowired
	private PortService portService;
	@Autowired
	private EBusinessService eBusinessService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private RatemasterService ratemasterService;
	@Autowired
	private RealTransService realTransService;
	@Autowired
	private EventTypeService eventTypeService;
	@ Autowired
	private RealtransEventService realtransEventService;
	
	private List<EventType> eventTypeList;
	
	private Event event;
	
	private Integer cityId;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "实际业务维护");
	}

	
	private void init()
	{
		if(realTrans.getId()!=null)
		{
			ebusiness=this.eBusinessService.selectByPrimaryKey(this.realTrans.getbEbusinessId());
			transaction=this.transactionService.selectByPrimaryKey(ebusiness.getbTransactionId());
			ratemaster=this.ratemasterService.selectByPrimaryKey(transaction.getrRateMasterId());
			poaPort=this.portService.selectByPrimaryKey(ratemaster.getPoa());
			polPort=this.portService.selectByPrimaryKey(this.ratemaster.getPol());
			realtransEventList=this.realtransEventService.getRealtransEventListByTransId(realTrans.getId());
			currentRealtransEvent=realtransEventService.selectCurrentRealtransEvent(realTrans.getId());
			
			if(ratemaster.getPod()!=null)
			{
				podPort=portService.selectByPrimaryKey(this.ratemaster.getPod());
			}
		
		}
		eventTypeList=eventTypeService.getEventTypeListByMode("Land");
	}
	public String doevent()
	{
		Date now=new Date();
		PrivilegesDataFilter	up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		RealtransEvent re=new RealtransEvent();
		
		re.setClientId(up.getLoginClientId());
		re.setOrgId(up.getLoginOrgId());
		re.setUserId(up.getUserId());
		re.setCreated(now);
		re.setCreatedby(up.getLoginUserId());
		re.setUpdated(now);
		re.setUpdatedby(up.getLoginUserId());
		re.setRealtransId(realTrans.getId());
		event.setEventdate(now);
		realTransService.saveEvent(event,re);
		this.id=this.realTrans.getId();
		
		edit();
		
		return "edit";
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "realTrans";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		realTransService.deleteByPrimaryKey(realTrans.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		realTransService.deleteRealTransList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return realTrans.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 realTrans= realTransService.selectByPrimaryKey(id);
		 init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return realTransService.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		realTrans=new RealTrans();
		 init();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		realTransService.save(realTrans);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public RealTrans getRealTrans() {
		return realTrans;
	}

	/**
	 * @param usergroup the usergroup to set
	 */
	public void setRealTrans(RealTrans realTrans) {
		this.realTrans = realTrans;
	}

	/**
	 * @return the polPort
	 */
	public Port getPolPort() {
		return polPort;
	}

	/**
	 * @param polPort the polPort to set
	 */
	public void setPolPort(Port polPort) {
		this.polPort = polPort;
	}

	/**
	 * @return the podPort
	 */
	public Port getPodPort() {
		return podPort;
	}

	/**
	 * @param podPort the podPort to set
	 */
	public void setPodPort(Port podPort) {
		this.podPort = podPort;
	}

	/**
	 * @return the ebusiness
	 */
	public EBusiness getEbusiness() {
		return ebusiness;
	}

	/**
	 * @param ebusiness the ebusiness to set
	 */
	public void setEbusiness(EBusiness ebusiness) {
		this.ebusiness = ebusiness;
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the ratemaster
	 */
	public Ratemaster getRatemaster() {
		return ratemaster;
	}

	/**
	 * @param ratemaster the ratemaster to set
	 */
	public void setRatemaster(Ratemaster ratemaster) {
		this.ratemaster = ratemaster;
	}

	/**
	 * @return the poaPort
	 */
	public Port getPoaPort() {
		return poaPort;
	}

	/**
	 * @param poaPort the poaPort to set
	 */
	public void setPoaPort(Port poaPort) {
		this.poaPort = poaPort;
	}

	/**
	 * @return the currentRealtransEvent
	 */
	public RealtransEvent getCurrentRealtransEvent() {
		return currentRealtransEvent;
	}

	/**
	 * @param currentRealtransEvent the currentRealtransEvent to set
	 */
	public void setCurrentRealtransEvent(RealtransEvent currentRealtransEvent) {
		this.currentRealtransEvent = currentRealtransEvent;
	}

	/**
	 * @return the realtransEventList
	 */
	public List<RealtransEvent> getRealtransEventList() {
		return realtransEventList;
	}

	/**
	 * @param realtransEventList the realtransEventList to set
	 */
	public void setRealtransEventList(List<RealtransEvent> realtransEventList) {
		this.realtransEventList = realtransEventList;
	}

	/**
	 * @return the eventTypeList
	 */
	public List<EventType> getEventTypeList() {
		return eventTypeList;
	}

	/**
	 * @param eventTypeList the eventTypeList to set
	 */
	public void setEventTypeList(List<EventType> eventTypeList) {
		this.eventTypeList = eventTypeList;
	}

	/**
	 * @return the seventtype
	 */
	public Integer getSeventtype() {
		return seventtype;
	}

	/**
	 * @param seventtype the seventtype to set
	 */
	public void setSeventtype(Integer seventtype) {
		this.seventtype = seventtype;
	}
	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}
	/**
	 * @param event the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

}
