package com.delmar.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 下午4:01:56 
 * 类说明 
 */
@Repository("hbnSessionManager") 
public class HbnSessionManager {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static Session session;
	private static HbnSessionManager unique=null;
	
	public static HbnSessionManager getInstance()
	{
		if (unique==null)
			unique=new HbnSessionManager();

		return unique;
	}
	

	
	public Session getCurrentSession()
	{
		if (session==null)
			session=sessionFactory.openSession();

		return session;
	}
	
	

}
