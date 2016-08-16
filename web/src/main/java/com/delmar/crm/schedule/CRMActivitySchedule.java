package com.delmar.crm.schedule;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delmar.common.model.ObjMailInfo;
import com.delmar.common.web.bean.MailFormatDeal;
import com.delmar.core.service.MailInfoService;
import com.delmar.crm.model.Customer;
import com.delmar.crm.model.Linkrecord;
import com.delmar.crm.service.CustomerService;
import com.delmar.crm.service.LinkrecordService;
import com.delmar.sys.model.User;
import com.delmar.sys.schedule.CoreSchedule;
import com.delmar.sys.service.UserService;
import com.delmar.utils.ProDateUtil;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月27日 下午3:12:22 
 * 类说明 
 */

@Component
public class CRMActivitySchedule extends CoreSchedule implements Runnable {

	@Autowired
	private MailInfoService mailInfoService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LinkrecordService linkrecordService;	
	
	@Autowired
	private UserService userService;	
	
	
	private MailFormatDeal mailHtml = MailFormatDeal.getInstance();
	
	@Override
	public void run() {
		
		try
		{
		customerVisitRemind();
		customerStateNew();
		
		RecordScheduleLog();
		} catch (Exception ex)
		{
			
		}
		
	}
	
	
	/** 
	 * @Title:        customerVisitRemind 
	 * @Description:  TODO
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年8月27日 下午4:33:17 
	 */
	private void customerVisitRemind() throws Exception {

		StringBuffer contentBuf = new StringBuffer();
		mailHtml.writeMailHeader(contentBuf, "客户联系提醒", "以下是明天需要联系的客户",
				"/portal/common/web/images/mail/remind.png");

		String headerTitle = "序号40,客户状态80,客户名称150,联系人80,联系电话100,邮件地址";
		mailHtml.writeMainDataTableHeader(contentBuf, headerTitle);

		try {
			
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("accessString", " id in (select id from b_crm_customer_extra where nextLinkTime>=convert(datatime,'"+ProDateUtil.getShortHisDateStr(-1)+"') and nextLinkTime<convert(datatime,'"+ProDateUtil.getShortHisDateStr(-2)+"')) ");
			
			List<Customer> customerList=customerService.selectByExample(param);
			
			ObjMailInfo objMail = null;
			Hashtable operBussTable = new Hashtable();
			HashMap userMail = new HashMap();
			int i=0;
			for (Customer oneDetail:customerList) {
				i++;
				// 定义onecontent 用来储存email文件信息。
				StringBuffer oneContent = new StringBuffer();
				// 获得邮件地址
				String mailAddress = "";
				if (userMail.containsKey("USER"+oneDetail.getUserId())) {
					mailAddress = (String) userMail
							.get("USER"+oneDetail.getUserId());
					objMail = (ObjMailInfo) operBussTable.get(mailAddress);
				} else {
					User objUser =userService.selectByPrimaryKey(oneDetail.getUserId());
					mailAddress = objUser.getEmail();
					objMail = new ObjMailInfo();
					objMail.setFromaddress("notices@delmarchina.com");
					objMail.setToaddress(mailAddress);
					objMail.setSubject("提醒：明天你需要联系的客户");
					objMail.getContentBuf().append(contentBuf.toString());
					objMail.setBefinish(0);
					objMail.setMailtype("CUSTOMER");
					operBussTable.put(mailAddress, objMail);
					userMail.put("USER"+oneDetail.getUserId(), mailAddress);
				}

				if (i % 2 == 0)
					mailHtml.fullDataTD(oneContent, "XHXHXHXH", "", "first",
							"trdata1");
				else
					mailHtml.fullDataTD(oneContent, "XHXHXHXH", "", "first",
							"trdata2");

				mailHtml.fullDataTD(oneContent, oneDetail.getStatusName());
				mailHtml.fullDataTD(oneContent, oneDetail.getName());
				mailHtml.fullDataTD(oneContent, ProDateUtil.getLongDateStr(oneDetail.getCustomerExtra().getLastLinkTime()));
				mailHtml.fullDataTD(oneContent, oneDetail.getTelephone());
				mailHtml.fullDataTD(oneContent, oneDetail.getEmail());
				objMail.getCurrentContentBuf().append(
						oneContent.toString().replaceAll("XHXHXHXH",
								String.valueOf(objMail.getXhNo())));
				objMail.addXhNo();
			}

			// 进行加入到邮件表中的操作
			Iterator iter = operBussTable.keySet().iterator();
			while (iter.hasNext()) {
				String oneOperator = (String) iter.next();
				ObjMailInfo oneMail = (ObjMailInfo) operBussTable
						.get(oneOperator);
				// 获取详细记录信息
				if (oneMail.getCurrentContentBuf().length() != 0) {
					oneMail.getContentBuf().append(
							oneMail.getCurrentContentBuf().toString());
				}

				// Table结束
				oneMail.getContentBuf()
						.append(mailHtml.writeMainDataTableEnd());
				//
				String noticeText = "★★★注:<br>1、此封邮件是由系统根据相应的规则自动生成的<br>"
						+ "2、系统会在每天的固定时间把次日需要联系的客户发送给你，以便你查询<br>"
						+ "3、如果有问题，请及时联系总部IT<br>";
				oneMail.getContentBuf()
						.append(mailHtml
								.writeMailBottom(noticeText,
										"/portal/common/web/images/mail/right_remind.png"));

				String tempStr = oneMail.getContentBuf().toString()
						.replaceAll("SUMMARYCONTENT", "");
				oneMail.getContentBuf().delete(0,
						oneMail.getContentBuf().length());
				oneMail.getContentBuf().append(tempStr);
				oneMail.setMailcontent(oneMail.getContentBuf().toString());

				mailInfoService.insert(oneMail);
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}

	}

	/** 
	 * @Title:        customerStateNew 
	 * @Description:  系统根据客户的创建时间，把前一天增加的拜访记录 发送给主管、分公司经理、销售总监和公司总经理
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年8月27日 下午4:33:04 
	 */
	private void customerStateNew() throws Exception {

		StringBuffer contentBuf = new StringBuffer();
		mailHtml.writeMailHeader(contentBuf, "新增客户拜访记录提醒", "以下是昨天新增的客户拜访记录列表",
				"/portal/common/web/images/mail/remind.png");

		String headerTitle = "序号40,客户名称150,联系人名称80,联系电话100,联系时间80,联系主题80,联系内容80,用户80,创建人80,创建时间100";
		mailHtml.writeMainDataTableHeader(contentBuf, headerTitle);

		// 得到需要生成的记录，如果当前时间是星期一(即isMonday是否等于2)，则生成记录，否则推出。
		try {
			
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("accessString", "  created>=convert(datatime,'"+ProDateUtil.getShortHisDateStr(0)+"') ");
			
			List<Linkrecord> linkrecordList=linkrecordService.selectByExample(param);
			
			ObjMailInfo objMail = null;
			Hashtable operBussTable = new Hashtable();
			Hashtable managerTable = new Hashtable();

			
			int i=0;
			for (Linkrecord oneDetail:linkrecordList) {
				// 定义onecontent 用来储存email文件信息。
				i++;
				StringBuffer oneContent = new StringBuffer();
				if (i % 2 == 0)
					mailHtml.fullDataTD(oneContent, "XHXHXHXH", "", "first",
							"trdata1");
				else
					mailHtml.fullDataTD(oneContent, "XHXHXHXH", "", "first",
							"trdata2");

				mailHtml.fullDataTD(oneContent, oneDetail.getCustomer().getName());
				mailHtml.fullDataTD(oneContent, oneDetail.getLinkman().getName());
				mailHtml.fullDataTD(oneContent, oneDetail.getLinkman().getPhoneNo());
				mailHtml.fullDataTD(oneContent, ProDateUtil.getLongDateStr(oneDetail.getContactDate()));
				mailHtml.fullDataTD(oneContent, oneDetail.getContactTitle());
				mailHtml.fullDataTD(oneContent, oneDetail.getContactRecord());
				mailHtml.fullDataTD(oneContent, oneDetail.getUserName());
				mailHtml.fullDataTD(oneContent, oneDetail.getCreatedByName());
				mailHtml.fullDataTD(oneContent, ProDateUtil.getLongDateStr(oneDetail.getCreated()));


				// 获得邮件地址
				String mailAddress = "";
				mailAddress = (String) managerTable.get(oneDetail.getOrgId());
				// 首先以分公司经理为单位把相应公司新添加的客户发送给分公司经理
				if (operBussTable.containsKey(mailAddress)) {
					objMail = (ObjMailInfo) operBussTable.get(mailAddress);
				} else {
					objMail = new ObjMailInfo();
					objMail.setSubject("提醒：昨天公司新添加的客户");
					objMail.getContentBuf().append(contentBuf.toString());
					objMail.setBefinish(0);
					objMail.setMailtype("Visit Report");
					objMail.setFromaddress("notices@delmarchina.com");
					objMail.setToaddress(mailAddress);
					operBussTable.put(mailAddress, objMail);
				}
				
				objMail.getCurrentContentBuf().append(
						oneContent.toString().replaceAll("XHXHXHXH",
								String.valueOf(objMail.getXhNo())));
				objMail.addXhNo();
				// 最后以整个集团为单位，把所有新添加的客户发送给销售总监和总经理
				String headSalesEMail ="";

				if (operBussTable.containsKey(headSalesEMail)) {
					objMail = (ObjMailInfo) operBussTable.get(headSalesEMail);
				} else {
					objMail = new ObjMailInfo();
					objMail.setSubject("提醒：昨天各分公司新添加的客户");
					objMail.getContentBuf().append(contentBuf.toString());
					objMail.setBefinish(0);
					objMail.setMailtype("Visit Report");
					objMail.setFromaddress("notices@delmarchina.com");
					objMail.setToaddress(headSalesEMail);
					objMail.setCcaddress("wanga@delmarchina.com");
					operBussTable.put(headSalesEMail, objMail);
				}
				objMail.getCurrentContentBuf().append(
						oneContent.toString().replaceAll("XHXHXHXH",
								String.valueOf(objMail.getXhNo())));
				objMail.addXhNo();
			}

			// 进行加入到邮件表中的操作
			Iterator iter = operBussTable.keySet().iterator();
			while (iter.hasNext()) {
				String oneOperator = (String) iter.next();
				ObjMailInfo oneMail = (ObjMailInfo) operBussTable
						.get(oneOperator);
				// 获取详细记录信息
				if (oneMail.getCurrentContentBuf().length() != 0) {
					oneMail.getContentBuf().append(
							oneMail.getCurrentContentBuf().toString());
				}
				// 这里进行对应统计数据的填充

				// Table结束
				oneMail.getContentBuf()
						.append(mailHtml.writeMainDataTableEnd());
				//
				String noticeText = "★★★注:<br>1、此封邮件是由系统根据相应的规则自动生成的<br>"
						+ "2、系统会在每天的固定时间把前一天新添加的客户发送给你，以便你查询<br>"
						+ "3、如果有问题，请及时联系总部IT<br>";
				oneMail.getContentBuf()
						.append(mailHtml
								.writeMailBottom(noticeText,
										"/portal/common/web/images/mail/right_remind.png"));

				String tempStr = oneMail.getContentBuf().toString()
						.replaceAll("SUMMARYCONTENT", "");
				oneMail.getContentBuf().delete(0,
						oneMail.getContentBuf().length());
				oneMail.getContentBuf().append(tempStr);
				mailInfoService.insert(oneMail);
			}
		} catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}	
	
	
}
