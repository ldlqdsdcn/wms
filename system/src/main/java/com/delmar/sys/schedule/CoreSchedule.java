package com.delmar.sys.schedule;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.sys.model.Scheduled;
import com.delmar.sys.model.ScheduledLog;
import com.delmar.sys.service.ScheduledLogService;
import com.delmar.sys.service.ScheduledService;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月27日 下午3:15:56 
 * 类说明 
 */
public class CoreSchedule  {
	
	@Autowired
	private ScheduledService scheduledService;
	@Autowired
	private ScheduledLogService scheduledLogService;		
	
	
	public void recordScheduleLog() {
		
	
		Scheduled scheduled=scheduledService.getScheduledByClassName(getClass().getName());
		Long runCount=scheduled.getRunCount();
		if(runCount==null)
		{
			runCount=0l;
		}
		runCount=runCount+1;
		scheduled.setRunCount(runCount);
		scheduled.setNextRunTime(System.currentTimeMillis());
		
		scheduledService.save(scheduled);
		ScheduledLog scheduledLog=new ScheduledLog();
		scheduledLog.setMsg("Sent mail Success");
		scheduledLog.setResult(1);
		scheduledLog.setRunTime(new Date());
		scheduledLog.setScheduledId(scheduled.getId());
		scheduledLogService.save(scheduledLog);
		
	}	

}
