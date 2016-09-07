package com.powere2e.reporttool.scheduler;

import java.util.Calendar;
import java.util.TimerTask;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.powere2e.reporttool.ReportMonitor;
import com.powere2e.reporttool.jasperprocessor.Cache;
public class CleanCache extends TimerTask {
  //private static final int C_SCHEDULE_HOUR = 0;
  private static boolean isRunning = true;
  public long bgndate;
  public long enddate;
  private Logger log = Logger.getLogger(ReportMonitor.class.getName());
  private int i=0;
  /*public CleanCache(ServletContext context) {
      this.context = context;
  }
  public CleanCache()
  {
	  
  }*/
  public void run() {
   // Calendar c = Calendar.getInstance();
	  if(System.currentTimeMillis()>enddate)this.cancel();
	 if(System.currentTimeMillis()>bgndate)
    if(isRunning) {
    	
        isRunning = false;
        log.warn("开始执行指定任务");
        System.out.println("开始执行指定任务"+(++i));
        //-------------------开始保存当日历史记录
        //在这里编写自己的功能，例：
        //File file = new File("temp");
        //file.mkdir();
        //启动tomcat，可以发现在tomcat根目录下，会自动创建temp文件夹
        Cache.getInstance().removeAllReports();//清理临时文件
        //-------------------结束
        isRunning = true;
        log.warn("指定任务执行结束");
     
        
    }
    else
      {
    	log.warn("上一次任务执行还未结束");
      }
  }

}
