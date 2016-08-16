/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.sys.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.delmar.sys.model.Scheduled;
import com.delmar.sys.model.ScheduledLog;
import com.delmar.sys.service.ScheduledLogService;
import com.delmar.sys.service.ScheduledService;

/**
 * @author 刘大磊 2015年8月25日 上午11:05:38
 *  Server status
 */
@Component
public class ServerStatus implements Runnable {
	@Autowired
	private ScheduledService scheduledService;
	@Autowired
	private ScheduledLogService scheduledLogService;
	@Autowired
	private JavaMailSender mailSender;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom("crm@delmarchina.com");
	//	smm.setTo("liua@delmarchina.com");
		smm.setTo(new String[]{"liua@delmarchina.com","luos@delmarchina.com"});
		smm.setSubject("计划任务测试");
		
		smm.setText("<html><body><h1></h1><i>i计划任务测试</i></body></html>");
		//smm.setText("Hello 这是测测试邮件！！！");
		mailSender.send(smm);
		Scheduled scheduled=scheduledService.getScheduledByClassName(this.getClass().getName());
		
		Long runCount=scheduled.getRunCount();
		if(runCount==null)
		{
			runCount=0l;
		}
		runCount=runCount+1;
		/**
		 * 运行次数
		 */
		scheduled.setRunCount(runCount);
		/**
		 * 设置下次运行时间
		 */
		scheduled.setNextRunTime(System.currentTimeMillis()+scheduled.getDelay()*1000);
		scheduledService.save(scheduled);
		ScheduledLog scheduledLog=new ScheduledLog();
		scheduledLog.setMsg("Sent mail Success");
		scheduledLog.setResult(1);
		scheduledLog.setRunTime(new Date());
		scheduledLog.setScheduledId(scheduled.getId());
		scheduledLogService.save(scheduledLog);
	}

}
