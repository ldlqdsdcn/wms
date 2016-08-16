/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                      		  *
 * 电话：0532-66701118                                                                * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/

package com.delmar.corebus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.delmar.base.enumdef.ModeType;
import com.delmar.base.model.City;
import com.delmar.base.model.CityCompany;
import com.delmar.base.model.Companys;
import com.delmar.base.model.CompanysSop;
import com.delmar.base.model.Port;
import com.delmar.base.service.CityCompanyService;
import com.delmar.base.service.CityService;
import com.delmar.base.service.CompanysService;
import com.delmar.base.service.CompanysSopService;
import com.delmar.base.service.PortService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.model.MailInfo;
import com.delmar.core.service.MailInfoService;
import com.delmar.core.service.impl.CoreServiceImpl;
import com.delmar.corebus.dao.EBusinessDao;
import com.delmar.corebus.dao.TransactionDao;
import com.delmar.corebus.model.EBusiness;
import com.delmar.corebus.model.EBusinessAssign;
import com.delmar.corebus.model.EBusinessStatus;
import com.delmar.corebus.model.RealTrans;
import com.delmar.corebus.model.Transaction;
import com.delmar.corebus.service.EBusinessAssignService;
import com.delmar.corebus.service.EBusinessService;
import com.delmar.corebus.service.RealTransService;
import com.delmar.quota.service.impl.DataNotFondException;
import com.delmar.quota.service.impl.QuotaParamException;
import com.delmar.rate.model.Ratemaster;
import com.delmar.rate.service.RatemasterService;
import com.delmar.sys.model.Org;
import com.delmar.sys.model.User;
import com.delmar.sys.service.OrgService;
import com.delmar.sys.service.UserService;
import com.delmar.utils.DateTimeDecorator;
import com.delmar.utils.StringUtil;

/**
 * @author 刘大磊 22014-12-26 10:54:30
 */
@Service("eBusinessService")
public class EBusinessServiceImpl extends CoreServiceImpl<EBusiness> implements
		EBusinessService {
	
	@Autowired
	private EBusinessDao eBusinessDao;
	@Autowired
	private TransactionDao transactionDao;
	@Autowired
	private RatemasterService ratemasterService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrgService orgService;
	@Autowired
	private PortService portService;
	@Autowired
	private CityService cityService;
	@Autowired
	private CityCompanyService cityCompanyService;
	@Autowired
	private CompanysSopService companysSopService;
	@Autowired
	private RealTransService realTransService;
	@Autowired
	private EBusinessAssignService eBusinessAssignService;
	@Autowired
	private CompanysService companysService;

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailInfoService mailInfoService;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<EBusiness> getCoreDao() {
		return eBusinessDao;
	}
	/** (non-Javadoc)
	 * @see com.delmar.rate.service.EBusinessService#saveEBusiness(com.delmar.rate.model.EBusiness)
	 * 
	 * 
	 */
	public EBusiness saveEBusiness(EBusinessParam eparam) throws QuotaParamException,DataNotFondException{
		Date now=new Date();
		String transno=eparam.getQuoteNo();
				
		
		if(StringUtil.isEmpty(transno))
		{
			throw new QuotaParamException("报价单号不允许为空");
		}
		Map param=new HashMap<String,Object>();
		param.put("accessString", "transno ='"+transno+"' and provider_id="+String.valueOf(eparam.getProviderId()));
		Transaction trans=transactionDao.getByExample(param);


		if(trans==null)
		{
			throw new DataNotFondException("找不到该报价单");
		}
		
		if (((now.getTime()-trans.getCreated().getTime())/1000/60)>30)
		{
			throw new DataNotFondException("当前报价单已经生成超过30分钟，已经失效，请重新进行询价，谢谢。");			
		}
		
		param=new HashMap<String,Object>();
		param.put("accessString", " b_transaction_id="+String.valueOf(trans.getId()));
		EBusiness existEBusiness= this.getByExample(param);		
		if(existEBusiness!=null)
		{
			throw new DataNotFondException("该报价单已经生成相应的订单，不能够再次生成订单！");
		}		
		
		Ratemaster ratemaster= ratemasterService.selectByPrimaryKey(trans.getrRateMasterId());
		if (ratemaster==null)
		{
			throw new DataNotFondException("找不到相应的报价信息");	
		}
		//TODO
		//如果此时这个价格已经没有了，怎么办？
	
		
		 EBusiness eb=new EBusiness();
		 eb.setTotalAmount(trans.getTotalAmount());
		 eb.setMode(ratemaster.getMode());
		 eb.setbTransactionId(trans.getId());
		 String modeID="";
		 if( ModeType.Ocean.toString().equalsIgnoreCase(ratemaster.getMode()))
		 {
			 modeID="02";
		
		 }
		 else if(ModeType.Air.toString().equalsIgnoreCase(ratemaster.getMode()))
		 {
			 modeID="01";
			
		 }
		eb.setBusinessno(generateDoccode(modeID));
		eb.setCargodesc(eparam.getCargoDesc());
		eb.setConsigneeaddress(eparam.getConsigneeAddress());
		eb.setConsigneecity(eparam.getConsigneeCity());
		eb.setConsigneecontact(eparam.getConsigneeContact());
		eb.setConsigneedistrict(eparam.getConsigneeDistrict());
		eb.setConsigneemobile(eparam.getConsigneeMobile());
		eb.setConsigneename(eparam.getConsigneeName());
		eb.setConsigneeprovince(eparam.getConsigneeProvince());
		eb.setGoodsdimension(eparam.getGoodsDimension());
		eb.setGoodsnumber(eparam.getGoodsNumber());
		eb.setGoodssize(eparam.getGoodsSize());
		eb.setGoodsweight(eparam.getGoodsWeight());
		eb.setRemark(eparam.getRemark());
		eb.setShipperaddress(eparam.getShipperAddress());
		eb.setShippercity(eparam.getShipperCity());
		eb.setShippercontact(eparam.getShipperContact());
		eb.setShipperdistrict(eparam.getShipperDistrict());
		eb.setShippermobile(eparam.getShipperMobile());
		eb.setShippername(eparam.getShipperName());
		eb.setShipperprovince(eparam.getShipperProvince()); 
		
		eb.setUserId(eparam.getUserId());
		eb.setUserName(eparam.getUserName());
		eb.setCreated(now); 		
		eb.setCreatedBy(eparam.getUserId());
		eb.setCreatedByName(eparam.getUserName());
		eb.setUpdated(now);
		eb.setUpdatedBy(eparam.getUserId());
		eb.setUpdatedByName(eparam.getUserName());

		
		eb.setRemark("");
		 

		 
		eb.setStatus(EBusinessStatus.DRAFT.getState());
		User user=userService.selectByPrimaryKey(eparam.getUserId());
		
		Org org=orgService.selectByPrimaryKey(user.getOrgId());
		eb.setOrgId(user.getOrgId());
		eb.setClientId(org.getClientId());
		
		
		
		
		 eBusinessDao.save(eb);
		 
		 Port polPort=portService.selectByPrimaryKey(ratemaster.getPol());
		
		 if(polPort.getCityId()!=null)
		 {	List<String> mailList=new ArrayList<String>();
			 Map ccparam=new HashMap();
		 	ccparam.put("baseCityId", polPort.getCityId());
			 List<CityCompany> ccList=cityCompanyService.selectByExample(ccparam);
			 if(ccList!=null)
			 {
				 for(CityCompany cc:ccList)
				 {
					 Companys cp=companysService.selectByPrimaryKey(cc.getCompanyId());
					 mailList.add(cp.getEmail());
					 if(cp!=null)
					 {
						 EBusinessAssign ea=new EBusinessAssign();
						 ea.setClientId(cp.getRelateclientId());
						 ea.setOrgId(cp.getRelateorgId());
						 ea.setStatus(100);
						 ea.setUserId(cp.getUserId());
						 ea.setEbusinessId(eb.getId());
						 this.eBusinessAssignService.save(ea);
					 }
				 }
			 }
			 //邮件发送通知
			if(mailList.size()>0)
			{
				 String[] mails=new String[mailList.size()]; 
				 mailList.toArray(mails);
				 /*
				 MailInfo mailInfo=new MailInfo();
				 mailInfo.setFromaddress("crm@mail.delmarchina.com");
				 mailInfo.setSubject("");
				 mailInfo.setMailcontent("");
				 */
				 StringBuilder sb=new StringBuilder();
				 for(String m:mails)
				 {
					 sb.append(m).append(";");
				 }
				 
				 NotifactionEmail ne=new NotifactionEmail();
				 ne.ebusinessno=eb.getBusinessno();
				 ne.emails=mails;
				 ne.subject="有新的业务单据";
				 ne.content="<html><body><h1>收到新的富士康处理单据请及时处理</h1><h2> 单据号为："+ne.ebusinessno+"</h2></body></html>";
				 ne.start();
				 MailInfo mi=new MailInfo();
				 mi.setSubject(ne.subject);
				 mi.setMailcontent(ne.content);
				 mi.setMailtype("WS/ALERT");
				 mi.setCreated(new Date());
				 mi.setCreatedby(-1);
				 mi.setUpdated(new Date());
				 mi.setUpdatedby(-1);
				 mi.setOrgId(-1);
				 mi.setClientId(-1);
				 mi.setSendtime(new Date());
				 mi.setFromaddress("crm@delmarchina.com");
				 mi.setToaddress(sb.toString());
				 mailInfoService.save(mi);
			}
			
			
		 }
		 
		return eb;
	}

	public String generateDoccode(String modeKey)
	{
		String prefix="DM2"+modeKey;
		//modeKey
		String datemonth=DateTimeDecorator.getDateMonth(new Date());
		prefix=prefix+datemonth;
		String maxvalue=eBusinessDao.selectMaxBusinessNo(prefix);
		if ((StringUtil.isNotEmpty(maxvalue))&&(maxvalue.indexOf(prefix)>-1))
		{
			String value=maxvalue.replace(prefix, "");			
			int nextvalue=Integer.parseInt(value)+1;
			 String  append= String.format("%04d", nextvalue);    
			 return prefix+append;
		}
		else
		{
			 String  append= String.format("%04d", 1);    
			 return prefix+append;
		}

	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.EBusinessService#getSopModelList(java.lang.String)
	 */
	public List<SopBusModel> getSopModelList(String transno) {
		 List<SopBusModel> sopbusModelList=new ArrayList<SopBusModel>();
		Map param=new HashMap<String,Object>();
		param.put("accessString", "transno ='"+transno+"'");
		Transaction trans=transactionDao.getByExample(param);
		Ratemaster ratemaster= ratemasterService.selectByPrimaryKey(trans.getrRateMasterId());
		
		Port port=portService.selectByPrimaryKey(ratemaster.getPol());
		
		City city=cityService.selectByPrimaryKey(port.getCityId());
		Map ccMap=new HashMap();
		ccMap.put("baseCityId", city.getId());
		List<CityCompany> ccList=cityCompanyService.selectByExample(ccMap);
		List<String> mailList=new ArrayList<String>();
		for(CityCompany cc:ccList)
		{
			Map<String,Object> cparam=new HashMap<String,Object>();
			cparam.put("companyId", cc.getCompanyId());
			List<CompanysSop> csList=companysSopService.selectByExample(cparam);

			if(csList!=null&&csList.size()>0)
			{
				for(CompanysSop cs:csList)
				{
					SopBusModel sbm=new SopBusModel();
					sbm.setOperateDate(null);
					sbm.setOperateDesc(cs.getOperdesc());
					sbm.setOperator(cs.getOperator());
					sbm.setRemark(cs.getRemark());
					sopbusModelList.add(sbm);
				}
			}
		}
		
		return sopbusModelList;
	}
	/* (non-Javadoc)
	 * @see com.delmar.rate.service.EBusinessService#saveGenerateRealTrans(java.lang.Integer)
	 */
	public void saveGenerateRealTrans(Integer ebusinessId,RealTrans realTrans) {

		EBusiness ebusiness=eBusinessDao.selectByPrimaryKey(ebusinessId);
		
		ebusiness.setStatus(101);
		
		this.eBusinessDao.save(ebusiness);
		
		realTrans.setbEbusinessId(ebusiness.getId());
		realTrans.setHawbbillno(this.realTransService.generateHawbBillNo());
		realTrans.setMasterbillno(this.realTransService.generateMasterBillNo());
		realTrans.setBusinessno(ebusiness.getBusinessno());
		realTransService.save(realTrans);

	
	}
	class NotifactionEmail extends Thread 
	{
		
		private String subject="有新的业务单据";
		private String[] emails;
		private String ebusinessno;
		private String content;
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			try
			{
				MimeMessage message=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message,true);
				helper.setFrom("crm@delmarchina.com");
				helper.setTo(emails);
				helper.setSubject(subject);
				helper.setText(content,true);
				mailSender.send(message);
			}
			catch(Exception e)
			{
				logger.error(e);
			}

		}
	}
}
