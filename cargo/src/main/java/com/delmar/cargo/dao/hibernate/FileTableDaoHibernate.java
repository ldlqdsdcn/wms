package com.delmar.cargo.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.FileTableDao;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.FileTable;
import com.delmar.cargo.model.TrustContext;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:15:52 
 * 类说明 
 */
@Repository("fileTableDao") 
public class FileTableDaoHibernate   extends CoreHibernateDaoImpl<FileTable> implements FileTableDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String SQL_AIRFILECODEBYHAWB="Select FileCode TrustFileCode From FileTable where bezhidan=2 and hawb=? ";
	private static final String SQL_AIRFILECODEBYMAWB="Select FileCode TrustFileCode From FileTable where bezhidan=1 and maincode=? ";
	

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return TrustContext.class;
	}
	
	
	public String getTrustFileCodeByHawb(String hawb)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		list=getCurrentSession().createSQLQuery(SQL_AIRFILECODEBYHAWB).setString(0,hawb).setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
		if (list.size()>0)
			return list.get(0).getTrustFileCode();
		
		return "";
	}

	
	public String getTrustFileCodeByMawb(String mawb)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		list=getCurrentSession().createSQLQuery(SQL_AIRFILECODEBYMAWB).setString(0,mawb).setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
		if (list.size()>0)
			return list.get(0).getTrustFileCode();
		
		return "";
	}	

	

}
