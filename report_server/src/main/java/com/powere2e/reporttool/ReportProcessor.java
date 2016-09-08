package com.powere2e.reporttool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.mail.MessagingException;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.config.Email;

/**
 * @author peter
 *
 */
public abstract class ReportProcessor extends Thread 
{
    private static final Logger log = Logger.getLogger(ReportProcessor.class.getName());
	protected String reportname;
	protected String reporttype;
	protected HashMap params;
	protected byte[] reportData;
	
	//***add by Axular****//
	private Email email;
	
	//***modify end*******//

	public void setReportName (String reportname)
    {
        this.reportname = reportname;
    }
    
    public void setReporttype (String reporttype)
    {
        this.reporttype = reporttype;
    }
    
    public void setParams (HashMap params )
    {
        this.params = params;
    }
    
    public byte[] getReportData ()
    {
        return this.reportData;
    }
    
    abstract public void doIt ();
    
    abstract public int getProcess();

	public void run() 
	{
		doIt ();
	}
    
	public JasperPrint getJasperPrint ()
	{		
		return null;
	}
	//       modify by axular              //

	
	/**
	 * 订阅报表
	 */
	public void sendMail(File filename,String sign,String mailaddress) throws IOException
	{
		log.info("send report to mail has begin");
		// ****判断是否符合订阅报表的条件***//
		if (("0").equals(sign)&&mailaddress!=null&&!mailaddress.equals("")) {
			Mail mail = new Mail();
			try {
				// ** 设置邮件目的地址***/
				mail.setAddress(mailaddress, Mail.TO);
				 // ****设置邮件发送地址*****//
				mail.setFromAddress(email.getMailfromaddress());
				 // **** 设置 SMTP 服务器 ****/
				mail.setSMTPHost(email.getEmailserver(), email.getMailuser(),email.getMailpassword());
				 // **** 设置邮件主题 ******/
				mail.setSubject(email.getMailsubject());
				// System.out.println("mailsubject=================="+getMailSubject());
				 // **** 设置邮件超文本正文*****/
				mail.setHtmlBody(email.getMailbody());
				 // ***** 设置邮件附件 ***/
				mail.setFileAttachment(filename.toString());
				// **** 邮件发送(一次发送多个地址, 优点速度快, 但是有非法邮件地址时将中断发送操作)***//
				mail.sendBatch();
			} catch (MessagingException e) {
				log.error("发送报表邮件有误", e);
				e.printStackTrace();
			}
		}
	}
	public void setEmail(Email email) {
		this.email = email;
	}


}
