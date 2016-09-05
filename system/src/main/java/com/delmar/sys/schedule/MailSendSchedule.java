package com.delmar.sys.schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delmar.core.model.MailInfo;
import com.delmar.core.service.MailInfoService;
import com.delmar.sys.model.Scheduled;
import com.delmar.sys.model.ScheduledLog;
import com.delmar.sys.service.ScheduledLogService;
import com.delmar.sys.service.ScheduledService;
import com.delmar.utils.StringUtil;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月26日 下午3:40:22 
 * 类说明 
 */
@Component
public class MailSendSchedule  extends CoreSchedule implements Runnable {
	
	@Autowired
	private MailInfoService mailinfoService; 
	@Autowired
	private ScheduledService scheduledService;
	@Autowired
	private ScheduledLogService scheduledLogService;	
	
	@Override
	public void run() {
		
		
		Session session = Session.getInstance(getProperties(),
				new Authentic());		
		List<MailInfo> mailinfoList=mailinfoService.getPendingMail();
		
		for (MailInfo mailInfo:mailinfoList)
		{
			sendMail(session,mailInfo.getFromaddress(),mailInfo.getToaddress(),mailInfo.getCcaddress(),mailInfo.getBccaddress(),mailInfo.getSubject(),mailInfo.getMailcontent(),null);
			mailinfoService.updateMailFinishStatus(mailInfo.getId());
		}
		
	   recordScheduleLog();
		
	}
	
	
	private boolean sendMail(Session session, String fromAddress,String toAddress,
			String ccAddress, String bccAddress, String subject,
			String content, String[] files) {

		try {
			
			if (fromAddress==null||fromAddress.equals(""))
				return false;
			
			if (toAddress==null)
				toAddress="";
			
			if (ccAddress==null)
				ccAddress="";
			
			if (bccAddress==null)
				bccAddress="";			
			
			if (toAddress.equals("")&&ccAddress.equals("")&&bccAddress.equals(""))
				return false;
			
			
			Message rs = new MimeMessage(session);
			Address from = new InternetAddress(fromAddress);
			// 发送地址
			rs.setFrom(from);
			
			String[] toAddressA = toAddress.split(";");
			for (int i = 0; i < toAddressA.length; i++) {
				if (toAddressA[i].equals(""))
					continue;
				
				if (StringUtil.isEmail(toAddressA[i])==false)
					continue;

				rs.addRecipient(RecipientType.TO, new InternetAddress(
						toAddressA[i])); // 接收地址
			}
			
			String[] ccAddressA = ccAddress.split(";");
			for (int i = 0; i < ccAddressA.length; i++) {
				if (ccAddressA[i].equals(""))
					continue;
				
				if (StringUtil.isEmail(ccAddressA[i])==false)
					continue;				

				rs.addRecipient(RecipientType.CC, new InternetAddress(
						ccAddressA[i]));

			}

			String[] bccAddressA = bccAddress.split(";");
			for (int i = 0; i < bccAddressA.length; i++) {
				if (bccAddressA[i].equals(""))
					continue;
				
				if (StringUtil.isEmail(bccAddressA[i])==false)
					continue;				

				rs.addRecipient(RecipientType.BCC, new InternetAddress(
						bccAddressA[i]));
			}

			rs.setSubject(subject); // 邮件主题
			rs.setSentDate(new Date());// 发送日期

			Multipart mp = new MimeMultipart();// related意味着可以发送html格式的邮件

			BodyPart html = new MimeBodyPart();
			// 为了能够发送图片  先把Img标签替换为<input type='image'
			content = content.replaceAll("<input type=\"image\"", "<img");

			Document htmlDoc = Jsoup.parse(content);
			Elements imgList = htmlDoc.getElementsByTag("img");
			// 同样的图片只处理一次
			Map<String,String> imageMap = new HashMap<String,String>();			
			for (int i = 0; i < imgList.size(); i++) {
				Element img = (Element) imgList.get(i); // 遍历img标签
				String imgUrl = img.attr("src");
				if (imgUrl != null) {
					int imgHash = imgUrl.hashCode();
					String imgCID = String.valueOf(imgHash);
					if (imageMap.containsKey(imgCID)) {
						content = content.replaceAll(imgUrl,
								"cid:" + String.valueOf(imgCID));
						continue;
					}

					imageMap.put(imgCID, "exist");
					content = content.replaceAll(imgUrl,
							"cid:" + String.valueOf(imgCID));
					MimeBodyPart htmlimg = new MimeBodyPart();
					// 得到绝对路径
					String rootPath = System.getProperty("user.dir").replace("bin", "webapps").replace("\\","/");
					String fileName = rootPath + imgUrl;
					FileDataSource fds = new FileDataSource(fileName);
					htmlimg.setDataHandler(new DataHandler(fds));
					htmlimg.setHeader("Content-ID", imgCID);
					mp.addBodyPart(htmlimg);
				}
			}

			Elements linkList = htmlDoc.getElementsByTag("link");
			// 同样的图片只处理一次
			Map<String,String> linkMaps = new HashMap<String,String>();
			for (int i = 0; i < linkList.size(); i++) {
				Element link = (Element) linkList.get(i); // 遍历img标签

				String HUrl = link.attr("href");
				if (HUrl != null) {
					int imgHashs = HUrl.hashCode();
					String HCID = String.valueOf(imgHashs);
					if (linkMaps.containsKey(HCID)) {
						content = content.replaceAll(HUrl,
								"cid:" + String.valueOf(HCID));
						continue;
					}

					linkMaps.put(HCID, "exist");
					content = content.replaceAll(HUrl,
							"cid:" + String.valueOf(HCID));

					MimeBodyPart htmlimgs = new MimeBodyPart();
					// 得到绝对路径
					String rootPath = System.getProperty("user.dir").replace("bin", "webapps").replace("\\","/");
					String fileName = rootPath  + HUrl;

					FileDataSource fdss = new FileDataSource(fileName);
					htmlimgs.setDataHandler(new DataHandler(fdss));
					htmlimgs.setHeader("Content-ID", HCID);
					mp.addBodyPart(htmlimgs);
				}
			}

			html.setContent(content, "text/html; charset=GBK"); // 邮件HTML内容
			mp.addBodyPart(html);
			if (files != null && files.length > 0) { // 邮件附件
				for (int i = 0; i < files.length; i++) {
					MimeBodyPart mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(files[i]);
					mbp.setDataHandler(new DataHandler(fds));
					mp.addBodyPart(mbp);
				}
			}

			rs.setContent(mp); // 邮件全部内容
			rs.setSentDate(new Date()); // 发送时间
			Transport.send(rs); // 发送
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	
	/**
	 * 邮件会话属性
	 * 
	 * @return 会话属性
	 */
	private Properties getProperties() {
		Properties rs = new Properties();
		rs.put("mail.smtp.host", "mail.delmarchina.com");
		rs.put("mail.smtp.port", "25");
		rs.put("mail.smtp.auth", false ? "true" : "false");
		return rs;
	}

	static class Authentic extends Authenticator { // 验证密码

		public Authentic() {
		}

		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("notices@delmarchina.com", "delmar7006");
		}

	}
	

}
