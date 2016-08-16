/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.corebus.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Port;
import com.delmar.base.service.PortService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.EBusinessAssign;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.Transaction;
import com.delmar.corebus.service.EBusinessService;
import com.delmar.corebus.service.RealTransService;
import com.delmar.corebus.service.TransactionService;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatemasterService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;

/**
 * @author 刘大磊 2015年3月23日 上午9:57:30
 */
public class EbusinessAction extends CoreEditPrivAction {
	private EBusiness ebusiness;
	private RealTrans realTrans;
	private Transaction transaction;
	private Ratemaster ratemaster;
	private Port poaPort;
	private EBusinessAssign eBusinessAssign;

	private Port polPort;
	private Port podPort;
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

	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "EBusiness数据");
	}
	
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		
		return "eBusiness";
	}
	
	
	
	private void init()
	{
		
		
		if(ebusiness.getId()!=null)
		{
			transaction=this.transactionService.selectByPrimaryKey(ebusiness.getbTransactionId());
			ratemaster=this.ratemasterService.selectByPrimaryKey(transaction.getrRateMasterId());
			poaPort=this.portService.selectByPrimaryKey(ratemaster.getPoa());
			polPort=this.portService.selectByPrimaryKey(this.ratemaster.getPol());
			if(ratemaster.getPod()!=null)
			{
				podPort=this.portService.selectByPrimaryKey(this.ratemaster.getPod());
			}
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("accessString", " b_ebusiness_id="+ebusiness.getId());
			List<RealTrans> realTransList=realTransService.selectByExample(param);
			if(realTransList!=null&&realTransList.size()>0)
			{
				
				realTrans=realTransList.get(0);
			}
		}
		
		
	
		
		
	}
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {

		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {


	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {
		
		return ebusiness.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		ebusiness=this.eBusinessService.selectByPrimaryKey(id);
		init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		PrivilegesDataFilter  up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			Map<String,Object> param=new HashMap<String,Object>();
			
		
		String orgAccessString=up.getAccessStringByStruts2();
		if (!orgAccessString.equals(""))
		  param.put("accessString",orgAccessString);
		else
		  param.put("accessString"," 1=1 ");			
		
		List<EBusiness> list=eBusinessService.selectByExample(param);
		if(list!=null)
		for(EBusiness ebus:list)
		{
			Transaction tr=transactionService.selectByPrimaryKey(ebus.getbTransactionId());
			Ratemaster mr=ratemasterService.selectByPrimaryKey( tr.getrRateMasterId());
			tr.setRatemaster(mr);
			ebus.setTransaction(tr);
		}
		
		
		return list;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		
		ebusiness=new EBusiness();
		init();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		return null;
	}
	public String generateRealTrans()
	{
		Date now=new Date();
		ebusiness.setStatus(101);
		id=ebusiness.getId();
		PrivilegesDataFilter	 up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		realTrans.setClientId(up.getLoginClientId());
		realTrans.setOrgId(up.getLoginOrgId());
		realTrans.setUserId(up.getUserId());
		realTrans.setPoa(this.poaPort.getId());
		realTrans.setPod(this.podPort.getId());
		realTrans.setPol(this.polPort.getId());
		realTrans.setCreated(now);
		realTrans.setCreatedBy(up.getLoginUserId());
		realTrans.setUpdated(now);
		realTrans.setUpdatedBy(up.getLoginUserId());
		realTrans.setCreatedByName(up.getLoginUser().getUsername());
		realTrans.setUpdatedByName(up.getLoginUser().getUsername());
		eBusinessService.saveGenerateRealTrans(id,realTrans);
		edit();
	
		return "edit";
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
	 * @return the realTrans
	 */
	public RealTrans getRealTrans() {
		return realTrans;
	}

	/**
	 * @param realTrans the realTrans to set
	 */
	public void setRealTrans(RealTrans realTrans) {
		this.realTrans = realTrans;
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
	 * @return the eBusinessAssign
	 */
	public EBusinessAssign geteBusinessAssign() {
		return eBusinessAssign;
	}

	/**
	 * @param eBusinessAssign the eBusinessAssign to set
	 */
	public void seteBusinessAssign(EBusinessAssign eBusinessAssign) {
		this.eBusinessAssign = eBusinessAssign;
	}
	
}
