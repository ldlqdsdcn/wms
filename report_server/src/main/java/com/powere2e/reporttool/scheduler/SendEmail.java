package com.powere2e.reporttool.scheduler;


import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimerTask;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.config.Config;
import com.powere2e.reporttool.config.DB;
import com.powere2e.reporttool.config.Email;


public class SendEmail extends TimerTask{

	  private static boolean isRunning = false;
	  public long bgndate;
	  public long enddate;
	
	public SendEmail()
	  {
		  
	  }
	  private Logger log = Logger.getLogger(SendEmail.class.getName());
	public void run() 
	{
		if(System.currentTimeMillis()>enddate)this.cancel();
		if(System.currentTimeMillis()>bgndate)
		if(!isRunning)
		{
			isRunning=true;
			try{
				Email email=Config.getEmail();
				if(email==null)Config.initialize();
				 email=Config.getEmail();
			List list=DB.getInstance().query(" select * from emailAddress");
			String[] s=new String[list.size()];
			for(int i=0;i<list.size();i++)
			{
				Map map=(Map)list.get(i);
				s[i]=new String(map.get("EMAIL").toString());
			}
			
			SendMail(email.getEmailserver(), email.getMailfromaddress(), s, email.getMailpassword(), email.getMailsubject(), email.getMailbody(),null);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.error(e.fillInStackTrace());
			}
			isRunning=false;
		}
		// TODO Auto-generated method stub
		
	}
	public static void main(String[]  args)
	{
		SendEmail se=new SendEmail();
		se.SendMail("mail.sh.powere2e.com", "solomon.liu@powere2e.com", new String[]{"tcldl@126.com","solomon.liu@powere2e.com"}, "romantic", "你好啊", "中国人民", 
				new String[]{"D:\\b5ce9254c067db0c3a2935b9.jpg","D:\\report.xls"});
		
	/*	String s="D:\\b5ce9254c067db0c3a2935b9.jpg";
		String s2="D:/b5ce9254c067db0c3a2935b9.jpg";
		System.out.println(s.lastIndexOf("\\"));
		System.out.println(s.substring(s.lastIndexOf("\\")+1));
		System.out.println(s2.lastIndexOf("\\"));*/
	}
	public boolean SendMail(String server,String fromEmail,String[] toEmails,String fromEmailPassword,String title,String content,String[] files)
	{

		boolean check = false;

		System.setProperty("mail.smtp.auth", "true");

		Properties props = System.getProperties();
		props.put("mail.smtp.host", server);

		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		
		try {
			message.setFrom(new InternetAddress(fromEmail));
			InternetAddress[] addresses=new InternetAddress[toEmails.length];
			for(int i=0;i<toEmails.length;i++)
			{
				addresses[i]=new InternetAddress(toEmails[i]);
			
			}
			
			
			/*message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toEmail));*/
			message.addRecipients(Message.RecipientType.TO, addresses);
			message.setSubject(title);
			if(files!=null)
			for(int i=0;i<files.length;i++)
			{
				MimeBodyPart file=new MimeBodyPart();
				int last=files[i].lastIndexOf("\\");
				if(last==-1)
					last=files[i].lastIndexOf("/");
				String filename=files[i].substring(last+1);
				
				
				//File f=new File(files[i]);
				file.setFileName(filename);
				FileDataSource source =new FileDataSource(files[i]);
				file.setDataHandler(new DataHandler(source));
				multipart.addBodyPart(file);
			}
			messageBodyPart.setText(content);
			
			multipart.addBodyPart(messageBodyPart);	
			message.setContent(multipart);
			message.saveChanges();

			Transport transport = session.getTransport("smtp");

			transport.connect(server, fromEmail, fromEmailPassword);//发信服务器，自己信箱的用户名，密码
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			check = true;

		} catch (AddressException e) {

			e.printStackTrace();
			check = false;
		} catch (MessagingException e) {

			e.printStackTrace();
			check = false;
		}

		return check;

	}
	}
	
	


