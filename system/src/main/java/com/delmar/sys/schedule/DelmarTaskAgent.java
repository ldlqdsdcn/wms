package com.delmar.sys.schedule;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.delmar.sys.model.Scheduled;
import com.delmar.sys.service.ScheduledService;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月26日 下午1:01:01 
 * 类说明 
 */
@Component
public class DelmarTaskAgent {
	
	@Autowired
	private ScheduledService scheduledService;
	
	private static DelmarTaskAgent instance=null;
	
	public List<Scheduled> scheduleList=new ArrayList<Scheduled>();
	public List<String> scheduleCodeList=new ArrayList<String>();
	
	
	public DelmarTaskAgent()
	{
		
	}
	
	public static  synchronized DelmarTaskAgent getInstance()
	{
		if (instance==null)
			instance=new DelmarTaskAgent();
		
		return instance;
	}
	
	public synchronized void registTaskAgent(Scheduled taskSchedule) {
		if (taskSchedule != null)
		{
			if (scheduleCodeList.indexOf(taskSchedule.getCode())==-1)
			{
			   scheduleList.add(taskSchedule);
			   scheduleCodeList.add(taskSchedule.getCode());
			}
		}
	}
	
	
	@PostConstruct 
	public void init()
	{
/*		Map<String,Object> param=new HashMap<String,Object>();
		param.put("accessString", "isactive='Y'");
		
		List<com.delmar.sys.model.Scheduled> list=scheduledService.selectByExample(param);	 
		
		for(com.delmar.sys.model.Scheduled s:list)
		{
			this.instance.registTaskAgent(s);
		}*/
		

	}

}
