package com.powere2e.reporttool;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Mail {
	/** 发件方式 - 普通发送 */
	final public static int TO = 0;

	/** 发件方式 - 抄送 */
	final public static int CC = 1;

	/** 发件方式 - 密件抄送 */
	final public static int BCC = 2;

	/** 邮件相关信息 - SMTP 服务器 */
	private String mailSMTPHost = null;

	/** 邮件相关信息 - 邮件用户名 */
	private String mailUser = null;

	/** 邮件相关信息 - 密码 */
	private String mailPassword = null;

	/** 邮件相关信息 - 发件人邮件地址 */
	private String mailFromAddress = null;

	/** 邮件相关信息 - 邮件主题 */
	private String mailSubject = "";

	/** 邮件相关信息 - 邮件发送地址 */
	private Address[] mailTOAddress = null;

	/** 邮件相关信息 - 邮件抄送地址 */
	private Address[] mailCCAddress = null;

	/** 邮件相关信息 - 邮件密件抄送地址 */
	private Address[] mailBCCAddress = null;

	/** 邮件相关信息 - 邮件正文(复合结构) */
	private MimeMultipart mailBody = null;

	public Mail() {
		mailBody = new MimeMultipart();
	}

	/**
	 * 设置 SMTP 服务器
	 * 
	 * @param strSMTPHost
	 *            邮件服务器名称或 IP
	 * @param strUser
	 *            邮件用户名
	 * @param strPassword
	 *            密码
	 */
	public void setSMTPHost(String strSMTPHost, String strUser,
			String strPassword) {
		this.mailSMTPHost = strSMTPHost;
		this.mailUser = strUser;
		this.mailPassword = strPassword;
	}

	/**
	 * 设置邮件发送地址
	 * 
	 * @param strFromAddress
	 *            邮件发送地址
	 */
	public void setFromAddress(String strFromAddress) {
		this.mailFromAddress = strFromAddress;
	}

	/**
	 * 设置邮件目的地址
	 * 
	 * @param strAddress
	 *            邮件目的地址列表, 不同的地址可用;号分隔
	 * @param iAddressType
	 *            邮件发送方式 (TO 0, CC 1, BCC 2) 常量已在本类定义
	 * @throws AddressException
	 */
	public void setAddress(String strAddress, int iAddressType)
			throws AddressException {
		switch (iAddressType) {
		case Mail.TO: {
			String []address =strAddress.split(";");
//			ArrayList alAddress = StringHelper.split(strAddress, ';');
			mailTOAddress = new Address[address.length];
			for (int i = 0; i < address.length; i++) {
				mailTOAddress[i] = new InternetAddress((String) address[i]);
			}
			break;
		}
		case Mail.CC: {
			String []address =strAddress.split(";");
//			ArrayList alAddress = StringHelper.split(strAddress, ';');
//			mailCCAddress = new Address[alAddress.size()];
//			for (int i = 0; i < alAddress.size(); i++) {
//				mailCCAddress[i] = new InternetAddress((String) alAddress
//						.get(i));
//			}
			mailTOAddress = new Address[address.length];
			for (int i = 0; i < address.length; i++) {
				mailTOAddress[i] = new InternetAddress((String) address[i]);
			}
			break;
		}
		case Mail.BCC: {
			String []address =strAddress.split(";");
//			ArrayList alAddress = StringHelper.split(strAddress, ';');
			mailTOAddress = new Address[address.length];
			for (int i = 0; i < address.length; i++) {
				mailTOAddress[i] = new InternetAddress((String) address[i]);
			}
			break;
		}
		}
	}

	/**
	 * 设置邮件主题
	 * 
	 * @param strSubject
	 *            邮件主题
	 */
	public void setSubject(String strSubject) {
		this.mailSubject = strSubject;
	}

	/**
	 * 设置邮件文本正文
	 * 
	 * @param strTextBody
	 *            邮件文本正文
	 * @throws MessagingException
	 */
	public void setTextBody(String strTextBody) throws MessagingException {
		MimeBodyPart mimebodypart = new MimeBodyPart();
		mimebodypart.setText(strTextBody, "GBK");
		mailBody.addBodyPart(mimebodypart);
	}

	/**
	 * 设置邮件超文本正文
	 * 
	 * @param strHtmlBody
	 *            邮件超文本正文
	 * @throws MessagingException
	 */
	public void setHtmlBody(String strHtmlBody) throws MessagingException {
		MimeBodyPart mimebodypart = new MimeBodyPart();
		mimebodypart.setDataHandler(new DataHandler(strHtmlBody,
				"text/html;charset=GBK"));
		mailBody.addBodyPart(mimebodypart);
	}

	/**
	 * 设置邮件正文外部链接 URL, 信体中将包含链接所指向的内容
	 * 
	 * @param strURLAttachment
	 *            邮件正文外部链接 URL
	 * @throws MessagingException
	 * @throws MalformedURLException
	 */
	public void setURLAttachment(String strURLAttachment)
			throws MessagingException, MalformedURLException {
		MimeBodyPart mimebodypart = new MimeBodyPart();
		mimebodypart.setDataHandler(new DataHandler(new URL(strURLAttachment)));
		mailBody.addBodyPart(mimebodypart);
	}

	/**
	 * 设置邮件附件
	 * 
	 * @param strFileAttachment
	 *            文件的全路径
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void setFileAttachment(String strFileAttachment)
			throws MessagingException, UnsupportedEncodingException {
		File path = new File(strFileAttachment);
		if (!path.exists() || path.isDirectory()) {
			return;
		}
		String strFileName = path.getName();
		MimeBodyPart mimebodypart = new MimeBodyPart();
		mimebodypart.setDataHandler(new DataHandler(new FileDataSource(
				strFileAttachment)));		
		/*
		 *  modified by zord @ 2003/6/16 to support Chinese File Name
		 */
		mimebodypart.setFileName(MimeUtility.encodeText(strFileName));
		// end of modify
		mailBody.addBodyPart(mimebodypart);
	}

	/**
	 * 邮件发送(一次发送多个地址, 优点速度快, 但是有非法邮件地址时将中断发送操作)
	 * 
	 * @throws MessagingException
	 */
	public void sendBatch() throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", this.mailSMTPHost);
		
		Session session = Session.getInstance(properties,null);
		MimeMessage mimemessage = new MimeMessage(session);
		mimemessage.setFrom(new InternetAddress(this.mailFromAddress));
		if (mailTOAddress != null) {
			mimemessage.addRecipients(javax.mail.Message.RecipientType.TO,
					this.mailTOAddress);
		}
		if (mailCCAddress != null) {
			mimemessage.addRecipients(javax.mail.Message.RecipientType.CC,
					this.mailCCAddress);
		}
		if (mailBCCAddress != null) {
			mimemessage.addRecipients(javax.mail.Message.RecipientType.BCC,
					this.mailBCCAddress);
		}
		mimemessage.setSubject(this.mailSubject);
		mimemessage.setContent(this.mailBody);
		mimemessage.setSentDate(new Date());
		Transport transport = session.getTransport("smtp");
		transport.connect(this.mailSMTPHost, this.mailUser, this.mailPassword);
	
		
		Transport.send(mimemessage);
		
		System.out.println("已向下列邮箱发送了邮件");
		if (mailTOAddress != null) {
			for (int i = 0; i < mailTOAddress.length; i++) {
				System.out.println(mailTOAddress[i]);
			}
		}
		if (mailCCAddress != null) {
			for (int i = 0; i < mailTOAddress.length; i++) {
				System.out.println(mailCCAddress[i]);
			}
		}
		if (mailBCCAddress != null) {
			for (int i = 0; i < mailTOAddress.length; i++) {
				System.out.println(mailBCCAddress[i]);
			}
		}
	}


//	static public void main(String str[]) throws MessagingException, UnsupportedEncodingException {
//		Mail mail = new Mail();
//		/*
//		 * 设置邮件目的地址
//		 */
//		mail.setAddress("Axular.huang@powere2e.com", Mail.TO);
//		/*
//		 * 设置邮件发送地址
//		 */
//		mail.setFromAddress("hjya@163.com");
//		/*
//		 * 设置 SMTP 服务器
//		 */
////		mail.setSMTPHost("mail.21cn.com", "", "");
//		mail.setSMTPHost("smtp.163.com", "hjya", "198246833");
//		/*
//		 * 设置邮件主题
//		 */
//		mail.setSubject("测试一下");
//		/*
//		 *  设置邮件超文本正文
//		 */
//		mail.setHtmlBody("testtesttesttesttesttesttest");
//		/*
//		 * 设置邮件附件
//		 */
//		mail.setFileAttachment(mailFromAddress,);
//		/*
//		 * 邮件发送(一次发送多个地址, 优点速度快, 但是有非法邮件地址时将中断发送操作)
//		 */
//		mail.sendBatch();
//	}
	

}

















