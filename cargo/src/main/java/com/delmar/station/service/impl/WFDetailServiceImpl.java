package com.delmar.station.service.impl;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.MimeMessage;

import com.delmar.utils.StringUtil;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import com.delmar.core.model.HbnHsql;
import com.delmar.core.model.MailInfo;
import com.delmar.core.service.MailInfoService;
import com.delmar.station.dao.WFDetailDao;
import com.delmar.station.dao.WFRealityDao;
import com.delmar.station.model.EDIResponseInfo;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WFReality;
import com.delmar.station.service.EDIResponseInfoService;
import com.delmar.station.service.WFDetailService;


@Service("WFDetailService")
public class WFDetailServiceImpl implements WFDetailService{

	@Autowired
	private WFDetailDao wfDetailDao;
	
	@Autowired
	private WFRealityDao wfRealityDao;
	
	@Autowired
	private MailInfoService mailInfoService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EDIResponseInfoService ediResponseInfoService;
	
	private static final Logger logger = Logger.getLogger(WFDetailServiceImpl.class);
	
	
	public WFDetail getWFDetailById(Integer id) {
		
		return wfDetailDao.selectByPrimaryKey(id);
	}

	public WFDetail saveOrUpdate(WFDetail wfDetail) {
		
		return wfDetailDao.saveOrUpdate(wfDetail);
	}

	public void deleteByPrimaryKey(Integer id) {
		wfDetailDao.deleteByPrimaryKey(id);
	}

	public void refreshWFRealitySum(Integer masterID) {
		
		if (masterID == null || masterID < 0) {
			return;
		}
		
		List<WFDetail> wfDetailList = getWFDetailByMasterId(masterID);
		WFReality wfReality = wfRealityDao.getWFRealityByMasterId(masterID);
		
		// 如果进仓单明细的记录数大于0，则对件重尺的汇总表更新
		if (wfDetailList.size() > 0) {
			
			int goodsNumber = 0;
			double goodsWeight = 0;
			double goodsSize = 0;
			for (WFDetail wfDetail : wfDetailList) {
				
				if (wfDetail.getZfbz() == 0) {
					goodsNumber += wfDetail.getGoodsNumber();
					goodsWeight += wfDetail.getGoodsWeight();
					goodsSize += wfDetail.getGoodsSize();
				}
			}
			
			wfReality.setGoodsNumber(goodsNumber);
			wfReality.setGoodsWeight(goodsWeight);
			wfReality.setGoodsSize(goodsSize);
			wfRealityDao.saveOrUpdate(wfReality);
			
			// 如果进仓单明细的记录数等于0，则对件重尺的汇总表更新
		} else {
			wfRealityDao.delete(wfReality);
		}
	}

	public List<WFDetail> getWFDetailByMasterId(Integer masterID) {
		return wfDetailDao.getWFDetailByMasterId(masterID);
	}

	public void createUpdateFobMail(String mailImplName, String[] paramArray,
			String beforeMaiTou, String beforeCargoRemark, WFDetail wfDetail,
			int mailFlag) {
		
		if (paramArray == null){
			return;
		}
		if (paramArray.length == 0){
			return;
		}
			
		if (paramArray[0] == null){
			return;
		}
		if (paramArray[0].trim().equals("")){
			return;
		}

		if (paramArray.length < 3){
			return;
		}
		if (paramArray[1] == null){
			return;
		}
		if (paramArray[1].trim().equals("")){
			return;
		}
		
		if (paramArray[0].equals("warehouse-detail-new")) {
			sendWarehouseUpdateDealMail(mailImplName, paramArray,beforeMaiTou,beforeCargoRemark,wfDetail,mailFlag);
		}
	}
	
	private void sendWarehouseUpdateDealMail(String mailImplName,
			String[] paramArray, String beforeMaiTou,
			String beforeCargoRemark, WFDetail wfDetail, int mailFlag) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("Dear " + wfDetail.getOperatorName() + ":<br/>");
		sb.append("     进仓单号:" + paramArray[4] +" 的进仓明细已更新<br/> <br/> 具体信息如下： <br/>");
		sb.append("-----------------------------------------------------------------");
		sb.append("<br/>");
		
		String subject = "";
		if(mailFlag == 1) {
			subject = "德玛电子商务:场站-进仓单明细的唛头被更新";
			sb.append("唛头被更新：" + beforeMaiTou + " 变更为： " + wfDetail.getMaiTou() + "<br/>");
		}
		
		if(mailFlag == 2) {
			subject = "德玛电子商务:场站-进仓单明细的货损情况被更新";
			sb.append("货损情况：" + wfDetail.getCargoRemark() + "<br/>");
		}
		
		if(mailFlag == 3) {
			subject = "德玛电子商务:场站-进仓单明细的的唛头及货损情况被更新";
			sb.append("唛头被更新：" + beforeMaiTou + " 变更为： " + wfDetail.getMaiTou() + "<br/>");
			sb.append("货损情况：" + wfDetail.getCargoRemark() + "<br/>");
		} 
		sb.append("-----------------------------------------------------------------");
		sb.append("<br/>");
		sb.append("烦请处理！");
		
		String[] toAddress = new String[1];
		toAddress[0] = paramArray[1];
		//toAddress[0] = "zhangja@delmarchina.com";
		
		NotifactionEmail ne=new NotifactionEmail();
		 ne.ebusinessno = paramArray[4];
		 ne.emails = toAddress;
		 ne.subject = subject;
		 ne.content = sb.toString();
		 ne.start();
		 
		 MailInfo mi=new MailInfo();
		 mi.setSubject(ne.subject);
		 mi.setMailcontent(ne.content);
		 //mi.setMailtype("WS/ALERT");
		 mi.setCreated(new Date());
		 mi.setCreatedby(Integer.parseInt(paramArray[5]));
		 mi.setUpdated(new Date());
		 mi.setUpdatedby(Integer.parseInt(paramArray[5]));
		 mi.setOrgId(Integer.parseInt(paramArray[6]));
		 mi.setClientId(Integer.parseInt(paramArray[6]));
		 mi.setSendtime(new Date());
		 mi.setFromaddress("crm@delmarchina.com");
		 mi.setToaddress(paramArray[1]);
		 mi.setBefinish(0);
		 mi.setMailtype("EBusiness-Warehouse");
			
		 mailInfoService.save(mi);
	}

	public boolean isNumber(String   str) {
		if (str.equals(""))
			return false;
		
        return   java.util.regex.Pattern.matches("^[-+0-9.]*$",   str.trim());   
	}

	public List<ObjWFDetail> searchWFDetailList(HbnHsql hbmwhere) {
		return wfDetailDao.searchWFDetailList(hbmwhere);
	}
	
	class NotifactionEmail extends Thread {
		
		private String subject="有新的业务单据";
		private String[] emails;
		private String ebusinessno;
		private String content;
		
		@Override
		public void run() {
			try{
				MimeMessage message=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message,true);
				helper.setFrom("crm@delmarchina.com");
				helper.setTo(emails);
				helper.setSubject(subject);
				helper.setText(content,true);
				mailSender.send(message);
			}catch(Throwable e){
				logger.error(e);
				e.printStackTrace();
			}

		}
	}

	public void createFobMail(String mailImplName, String[] paramArray, WFDetail wfDetail) {
		if (paramArray == null){
			return;
		}
		if (paramArray.length == 0){
			return;
		}
			
		if (paramArray[0] == null){
			return;
		}
		if (paramArray[0].trim().equals("")){
			return;
		}

		if (paramArray.length < 3){
			return;
		}
		if (paramArray[1] == null){
			return;
		}
		if (paramArray[1].trim().equals("")){
			return;
		}
		
		if (paramArray[0].equals("warehouse-detail-new")) {
			sendWarehouseUpdateDealMail(mailImplName, paramArray,wfDetail);
		}
	}

	private void sendWarehouseUpdateDealMail(String mailImplName,
			String[] paramArray, WFDetail wfDetail) {
		
		StringBuffer contentSB = new StringBuffer();
		contentSB.append("Dear " + wfDetail.getOperatorName() + ":<br/>");
		contentSB.append("     进仓单号:" + paramArray[4] +" 已新增进仓明细<br/> <br/> 具体信息如下： <br/>");
		contentSB.append("-----------------------------------------------------------------");
		contentSB.append("<br/>");
		contentSB.append("件：" + wfDetail.getGoodsNumber() + "&nbsp;&nbsp;&nbsp;&nbsp;重：" + wfDetail.getGoodsWeight() + "&nbsp;&nbsp;&nbsp;&nbsp;尺：" + wfDetail.getGoodsSize() + "<br/>");
		contentSB.append("司机：" + wfDetail.getCarDriver() + "<br/>");
		contentSB.append("车牌：" + wfDetail.getCarLicenseNo() + "<br/>");
		contentSB.append("货损情况：" + wfDetail.getCargoRemark() + "<br/>");
		contentSB.append("费用金额：" + wfDetail.getTotalAMount() + "<br/>");
		contentSB.append("唛头：" + wfDetail.getMaiTou() + "<br/>");
		if(StringUtil.isNotEmpty(wfDetail.getResultMessage())){
			if("success".equals(wfDetail.getResultMessage())){
				contentSB.append("DCMS中FcrDate已更新，请知晓！<br/>");
			} else {
				contentSB.append("DCMS中FcrDate更新失败，请手动更新。提示信息："+wfDetail.getResultMessage()+"<br/>");
			}
		}
		
		contentSB.append("-----------------------------------------------------------------");
		contentSB.append("<br/>");
		
		contentSB.append("烦请处理！");	
		
		String[] toAddress = new String[1];
		toAddress[0] = paramArray[1];
		//toAddress[0] = "zhangja@delmarchina.com";
		NotifactionEmail ne=new NotifactionEmail();
		 ne.ebusinessno = paramArray[4];
		 ne.emails = toAddress;
		 ne.subject="德玛电子商务:场站-进仓信息";
		 ne.content=contentSB.toString();
		 ne.start();
		 
		 MailInfo mainInfo=new MailInfo();
		 mainInfo.setSubject(ne.subject);
		 mainInfo.setMailcontent(ne.content);
		// mainInfo.setMailtype("WS/ALERT");
		 mainInfo.setCreated(new Date());
		 mainInfo.setCreatedby(Integer.parseInt(paramArray[5]));
		 mainInfo.setUpdated(new Date());
		 mainInfo.setUpdatedby(Integer.parseInt(paramArray[5]));
		 mainInfo.setOrgId(Integer.parseInt(paramArray[6]));
		 mainInfo.setClientId(Integer.parseInt(paramArray[6]));
		 mainInfo.setSendtime(new Date());
		 mainInfo.setFromaddress("crm@delmarchina.com");
		 mainInfo.setToaddress(paramArray[1]);
		 mainInfo.setMailtype("EBusiness-Warehouse");
		 
		 mailInfoService.save(mainInfo);
		 
	}

	public String updateDcmsFcrDate(EDIResponseInfo edirInfo, String trustFileCode) {
		
		String resultMessage = "success";
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sf.format(date);
		try {
			date = sf.parse(edirInfo.getInDate());
			currentDate = sf.format(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
    	try {
    		
    	    EDIResponseInfo resultEDIResponseInfo = ediResponseInfoService.getEDIRByTrustFileCode(trustFileCode);
    		
    	    // 如果Booking ID在CargoPro与Dcms建立关联还能调用接口，否则找不到bookingID
    		if(StringUtil.isNotEmpty(resultEDIResponseInfo.getCsReferenceNo())){
    			Map<String, String> params = new HashMap<String, String>();
    			params.put("id", resultEDIResponseInfo.getCsReferenceNo());
    			params.put("fcrDate", currentDate);
    			params.put("remark",edirInfo.getResponseDesc());
    			HttpClient httpClient = null;
    			httpClient.getParams().setAuthenticationPreemptive(true);
    			
    			// 验证
    			Credentials credentials = new UsernamePasswordCredentials("wsuserchina", "ws1sGreat");
    	        httpClient.getState().setCredentials(AuthScope.ANY,credentials);
    	        
    	        HttpMethod method = buildPostMethod("https://www.delmarcargo.com/cms/api/bookingservice/updateBookingFcrDate", params);
    			int statusCode = httpClient.executeMethod(method);
    			if(statusCode != HttpStatus.SC_OK){
    				throw new HttpException(method.getStatusText());
    			}
    			String xmlResult = method.getResponseBodyAsString(); 
    			method.releaseConnection();
    			
    			// 创建一个新的字符串  
    	        StringReader xmlReader = new StringReader(xmlResult);  
    	        // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
    	        InputSource xmlSource = new InputSource(xmlReader);  
    	        // 创建一个SAXBuilder  
    	        SAXReader builder = new SAXReader();  
    	        // 通过输入源SAX构造一个Document
    			Document doc = builder.read(xmlSource);
    			// 获得根节点
    	        Element root = doc.getRootElement();  
    	        // 获得BODY节点 
    	        Element resultStatusCode = root.element("ServiceResponse").element("statusCode");
    	        
    	        // 判断调用接口成不成功
    	        if ("200".equals(resultStatusCode.getText())){
    	        	// add
    	        	edirInfo.setEdiType("DCMS_FCRDATE");
    	        	edirInfo.setEdiStatus("1");
    	        	edirInfo.setCsReferenceNo(resultEDIResponseInfo.getCsReferenceNo());// set Booking Id
    	        	edirInfo.setEdiAction("NEW");
    	        	edirInfo.setEdiStatus("1");
    	        	edirInfo.setBatchNo("0");
    	        	edirInfo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	        	edirInfo.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	        	edirInfo.setBeUse(0);
    	        	ediResponseInfoService.saveOrUpdate(edirInfo);
        	        
    	        	ediResponseInfoService.updateTrustFileInfoFCRDate(currentDate,edirInfo.getTrustFileCode());
    	        } else if("405".equals(resultStatusCode.getText())){
    	        	Element resultText = root.element("ServiceResponse").element("description");
    	        	resultMessage = resultText.getText();
    	        	
    	        	// add
    	        	edirInfo.setEdiType("DCMS_FCRDATE");
    	        	edirInfo.setEdiStatus("11");
    	        	edirInfo.setCsReferenceNo(resultEDIResponseInfo.getCsReferenceNo());// set Booking Id
    	        	edirInfo.setEdiAction("NEW");
    	        	edirInfo.setEdiStatus("1");
    	        	edirInfo.setBatchNo("0");
    	        	edirInfo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	        	edirInfo.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	        	edirInfo.setBeUse(0);
        	        ediResponseInfoService.saveOrUpdate(edirInfo);
    	        }else {
    	        	Element resultText = root.element("ServiceResponse").element("description");
    	        	resultMessage = resultText.getText();
    	        }
    		} else {
    			resultMessage = "Booking ID在CargoPro与Dcms还未建立关联";
    		}
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = "Modify Dcms Fcr Date have Exception";
			return resultMessage;
		}  
        
        return resultMessage;
	}
	
	 /**
		 * 构建POST方法
		 * @param url
		 * @param parms
		 * @return
		 * @throws java.io.UnsupportedEncodingException
		 */
		private static HttpMethod buildPostMethod(String url,Map<String, String> parms) throws UnsupportedEncodingException{
			//填入各个表单域的值 
			NameValuePair[] data = new NameValuePair[parms.keySet().size()];
			Iterator<Entry<String, String>> it = parms.entrySet().iterator();
			int i=0;
			while(it.hasNext()){
				Entry<String, String> entry = (Entry)it.next();
				String key = entry.getKey();
				String value = entry.getValue();
				//System.out.println(key+":"+value);
				data[i] = new NameValuePair(key,value);
				i++;
			}
			PutMethod method = new PutMethod(url);
			method.setQueryString(data);
			//.setRequestBody(data);

			return method;
		}
		
		public List<ObjWFDetail> viewRecordByMasterId(Integer masterId) {
			
			return wfDetailDao.searchWFDetailList(masterId);
		}

		public ObjWFDetail sumRecordByMasterId(Integer masterId) {
			
			return wfDetailDao.sumRecordByMasterId(masterId);
		}

}
