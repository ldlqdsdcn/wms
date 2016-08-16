package com.delmar.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *@ClassName:   HbnConnection.java 
 *@Description:  TODO
 *
 * @author Charles Luo 
 * @Date: 2015年5月19日 上午9:32:38
 * @version V2.0
 */
public class HbnConnection {
	private static SessionFactory cargoH;  
	private static SessionFactory coreH;   	
	private static Configuration configurationCargo = null;
	private static Configuration configurationCore = null; 
	
	static {  

		configurationCargo = new Configuration().configure("/hbn.cargo.cfg.xml");
		configurationCore = new Configuration().configure("/hbn.core.cfg.xml");
		
		cargoH = configurationCargo.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configurationCargo.getProperties()).buildServiceRegistry());
		coreH = configurationCore.buildSessionFactory(new ServiceRegistryBuilder().applySettings(configurationCore.getProperties()).buildServiceRegistry());		
		}   	
	
	/**根据DBName判断调用哪个sessionFactory的openSession（）方法*/  
	public static Session getSessionByDB(String DBName) {  
	   Session s = null;  
	  
	   if (DBName == "cargo") {  
	    if (!cargoH.isClosed())  
	     s = cargoH.openSession();  
	   } else if (DBName == "core") {  
	    if (!coreH.isClosed())  
	     s = coreH.openSession();  
	   } else {  
	     System.out.println("错误的 DBName！");  
	   }  
	   
	   return s;  
	}   
	
	
	/**根据DBName判断调用哪个sessionFactory的close()方法*/  
	public static void closeSessionFactoryByDB(String DBName) {  
	   if (DBName == "cargo") {  
	    if (!cargoH.isClosed()) {  
	    	cargoH.close();  
	    }  
	   } else if (DBName == "core") {  
	    if (!coreH.isClosed()) {  
	    	coreH.close();  
	    }  
	   } else {  
	    System.out.println("错误的 DBName！");  
	   }  
	}  


	

}
