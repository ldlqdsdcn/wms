/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.test;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 刘大磊 2015年2月12日 下午4:41:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MailHtmlTest {
	@Autowired
	private JavaMailSender mailSender;
	@Test
	public void testSendEmail() throws Exception
	{
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom("crm@delmarchina.com");
		helper.setTo(new String[]{"liua@delmarchina.com","luos@delmarchina.com"});
		helper.setSubject("刘大磊测试");
		helper.setText("<html><body><h1>test H1</h1><i>i 测试</i><h2 style=\"color:red\"> H2 Test</h2></body></html>",true);
		mailSender.send(message);
	}
}
