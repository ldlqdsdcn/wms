package com.delmar.crm.proxy.pub;

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName:   CustomerProxyManage.java 
 *@Description:  利用这个代理进行第三方数据的查询是否存在
 *
 * @author Charles Luo 
 * @Date: 2015年7月2日 上午11:05:32
 * @version V2.0
 */
public class CustomerProxyManage {
	
	private static CustomerProxyManage instance=null;
	
	private List<Object> customerProxyList=new ArrayList<Object>();
	
	public CustomerProxyManage()
	{
		
	}
	
	public static synchronized CustomerProxyManage getInstance()
	{
		if (instance == null)
			instance = new CustomerProxyManage();
		return instance;	
	}
	
	
	/**
	 * 注册相应的任务到管理器
	 * 
	 * @param 
	 */
	public synchronized void registProxy(Object taskProxy) {
		if (taskProxy != null)
			customerProxyList.add(taskProxy);
	}	
	
	
	
	/**
	 * 通过代理获得任务集合
	 * 
	 * @return Vector:ProFlowTask
	 */
	public boolean processByProxy(String opsCode) {
		
		for (Object taskProxy:customerProxyList)
		{
		  if (taskProxy instanceof CustomerProxy)
		  {
			  boolean beExist=((CustomerProxy) taskProxy).checkOpsCode(opsCode);
			  if (beExist==true)
				  return beExist;
		  }
			
		}
		
		return false;
		
	}
	
	

}
