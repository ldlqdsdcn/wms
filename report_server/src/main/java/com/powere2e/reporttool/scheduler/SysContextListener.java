package com.powere2e.reporttool.scheduler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.config.DB;
import com.powere2e.reporttool.model.Scheduler;
public class SysContextListener implements ServletContextListener {
  //定期任务
	
	private Logger log = Logger.getLogger(SysContextListener.class.getName());
	public static  Timer timer = null;
  public void contextInitialized(ServletContextEvent event) {//在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
      timer = new Timer(true);
      //防止配置文件没有加载完
      try {
		Thread.sleep(10*1000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 

      DB.database_Home=event.getServletContext().getRealPath("WEB-INF/db");
      log.warn("定时器已启动");//添加日志，可在tomcat日志中查看到
      log.info("INfo");
      log.debug("Debug");
      List list2=new ArrayList();
      try{
     List list= DB.getInstance().query("select * from SCHEDULER where active='Y' ");
     
     for(int i=0;i<list.size();i++)
     {
    	 list2.add(new Scheduler((java.util.Map)list.get(i)));
     }
     }
     catch(Exception e)
     {
    	 e.printStackTrace(); 
     }
     Field bgndate=null;
     Field enddate=null;
	  

     for(int i=0;i<list2.size();i++)
     {
    	 Scheduler scheduler=(Scheduler)list2.get(i);


    	 try
    	 {
    	
    	  Object obj=Class.forName(scheduler.getClassname()).newInstance();
  			bgndate=obj.getClass().getField("bgndate");

  			enddate=obj.getClass().getField("enddate");
    	  
    	   	bgndate.setLong(obj, scheduler.getBgndate().getTime());
    	   	enddate.setLong(obj, scheduler.getEnddate().getTime());
    	   	 timer.schedule((TimerTask)obj , 0,60*60*1000*scheduler.getInterval().intValue());
    	 }
    	 catch(Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    	 //timer.schedule(new CleanCache(event.getServletContext()),0,10*60*1000);
    	 //调用exportHistoryBean，0表示任务无延迟，5*1000表示每隔5秒执行任务，60*60*1000表示一个小时。
     }
      
      
      
     log.warn("已经添加任务");
  }
  public void contextDestroyed(ServletContextEvent event) {//在这里关闭监听器，所以在这里销毁定时器。
      timer.cancel();
      log.warn("定时器销毁");
  }
public static void main(String[] args)
{
	try
	{
		CleanCache cle=new CleanCache();
		Field[] fis=cle.getClass().getFields();
		
		for(int i=0;i<fis.length;i++)
		{
			System.out.println(fis[i].getName());
		}
		//Field bgndate=CleanCache.class.getField("bgndate");
	}
	catch (SecurityException e)
	{
		e.printStackTrace();
	} 
}
} 
