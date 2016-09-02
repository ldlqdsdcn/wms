/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.core.web.listener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.delmar.core.model.EaModelContent;
import com.delmar.core.model.Table;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.TableColumnService;
import com.delmar.core.service.TableService;
import com.delmar.core.web.bean.EaContext;

/**
 * @author 刘大磊 2015年1月12日 下午1:52:46
 */
public class SystemInitListener implements ServletContextListener{
	static
	{
		//Log4j 配置
		File classesDir=new File(SystemInitListener.class.getResource("/").getFile());
		//String file = prefix+"/web-inf/classes/log4j.properties";
		File logFile=new File(classesDir,"log4j.properties");
        PropertyConfigurator.configure(logFile.getPath()); 
        
       // Constants.servletContext="";
	}
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		EaContext.ServletContext=servletContextEvent.getServletContext();
		EaContext.ApplicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(EaContext.ServletContext);
		EaContext.CONTEXTPATH=EaContext.ServletContext.getContextPath();
		
				//操作日志初始化
				TableService tableService=(TableService)EaContext.ApplicationContext.getBean("tableService");
				TableColumnService tableColumnService=(TableColumnService)EaContext.ApplicationContext.getBean("tableColumnService");
				Map example=new HashMap();
				example.put("accessString", " out_log='Y' ");
				List<Table> tableList=tableService.selectByExample(example);
				Map<String,Table> logTableMap=new HashMap<String,Table>();
				if(tableList!=null)
					
				for(Table table:tableList)
				{
					example.put("accessString", " out_log='Y' and table_id='"+table.getId()+"'");
					List<TableColumn> columnList=tableColumnService.selectByExample(example);
					table.setTableColumnList(columnList);
					logTableMap.put(table.getClassName(), table);
				}
				//EaModelContent.logTableList=tableList;
				EaModelContent.logTableMap=logTableMap;
		
		Logger logger=Logger.getLogger("SystemInitListener");
		logger.info("系统已经启动");
	}

}
