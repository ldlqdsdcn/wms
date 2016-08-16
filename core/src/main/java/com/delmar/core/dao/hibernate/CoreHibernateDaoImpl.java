/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.dao.HbnSessionManager;
import com.delmar.core.model.CriteriaH;
import com.delmar.core.model.FieldH;
import com.delmar.core.model.HbnHsql;
import com.delmar.utils.DmLog;

/**
 * @author 刘大磊 2015年5月6日 上午10:03:40
 */
@Deprecated
public  abstract class CoreHibernateDaoImpl<T> implements  CoreHibernateDao<T> {

    protected DmLog logger =DmLog.getLogger("SQLLogger."+getClass().getName()); 
    
	@Autowired
	private HbnSessionManager hbnSessionManager;
	
	public abstract  Class getModelClass();	
	
	protected Session getCurrentSession()
	{
		return hbnSessionManager.getCurrentSession();
	}

	public Integer countObjects(Object obj) {
		Query query = getCurrentSession().createQuery(" select count(*) from  "
				+ this.getModelClass().getSimpleName());
		List list = query.list();
		if (list.size() > 0) {
			return ((Long) list.get(0)).intValue();
		}
		return 0;

	}

	public List<T> getList(DetachedCriteria  obj) {
	    	

	   	if(obj!=null)
		{
	    	
		    return 	obj.getExecutableCriteria(getCurrentSession()).list(); 
		}
		else
		{
			 Query query=getCurrentSession().createQuery("from "+getModelClass().getSimpleName());
			 return query.list();
		}
   	
	    }

	    public T getObject(Serializable obj) {

	    	T t=(T) getCurrentSession().get(getModelClass(), obj);
	    	

	    	return t; 

	    }
	    
	    public T loadObject(Serializable obj) {

	    	T t=(T) getCurrentSession().load(getModelClass(), obj);
	    	

	    	return t; 

	    }	    

       
	    public List<T> searchHSQL(HbnHsql hbnWhere)
	    {

	    	StringBuilder sb=new StringBuilder(" ");
   	        sb.append(" from ");

			sb.append(hbnWhere.getClassName().getSimpleName()).append(" ");
			sb.append(" where 1=1 ");
			sb.append(hbnWhere.getSqlWhere());
   	

	    	List list=null;

    		Query query=getCurrentSession().createQuery(sb.toString());
    		hbnWhere.setQueryValue(query);

	    		try
	    		{
	    	 	  list=query.list();	 
	    		}
	    		catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}
			
			return list;
		
	    }
	    
	    
	   
	    public List<T> search(CriteriaH criteriaH)
	    {
	    	if(criteriaH.getClassName()==null)
	    	{
	    		criteriaH.setClassName(this.getModelClass());
	    	}
	    	DetachedCriteria dc=DetachedCriteria.forClass(criteriaH.getClassName());
	    	List<FieldH> fields=criteriaH.getFields();
	    	for(FieldH f:fields)
	    	{
	    		if("=".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.eq(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if(">".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.gt(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if(">=".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.ge(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if("<".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.lt(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if("<=".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.le(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else	if("in".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.in(f.getFieldName(), (List)f.getValue()));
	    		
	    		}
	    		else if("like".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.like(f.getFieldName(), f.getValue()));
	    		}
	    		else if("notin".equals(f.getOperator()))
	    		{
	    			dc.add(Restrictions.not(Restrictions.in(f.getFieldName(), (List)f.getValue())));
	    		}else if("order".equals(f.getOperator()))
	    		{
	    			if("desc".equals(f.getValue()))
	    			{
	    				dc.addOrder(Order.desc(f.getFieldName()));
	    			}
	    			else
	    			{
	    				dc.addOrder(Order.asc(f.getFieldName()));
	    			}
	    			
	    		}
	    	}
	    	List list=null;
			if(criteriaH.getRows()==-1)
			{
				list=	dc.getExecutableCriteria(getCurrentSession()).list(); 
			}
			else
			{
				list=dc.getExecutableCriteria(getCurrentSession()).setFirstResult(criteriaH.getStrrow()).setMaxResults(criteriaH.getRows()).list();
			}
			
			return list;
	    }
	    
	    public T get(CriteriaH criteriaH)
	    {

	    	if(criteriaH.getClassName()==null)
	    	{
	    		criteriaH.setClassName(this.getModelClass());
	    	}
	    	
	    	Criteria criter=getCurrentSession().createCriteria(criteriaH.getClassName());
			//Query q=session.createQuery("from Test");
	    	List<FieldH> fields=criteriaH.getFields();
	    	for(FieldH f:fields)
	    	{
	    		if("=".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.eq(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if(">".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.gt(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if(">=".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.ge(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if("<".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.lt(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else if("<=".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.le(f.getFieldName(), f.getValue()));
	    		
	    		}
	    		else	if("in".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.in(f.getFieldName(), (List)f.getValue()));
	    		
	    		}
	    		else if("notin".equals(f.getOperator()))
	    		{
	    			criter.add(Restrictions.not(Restrictions.in(f.getFieldName(), (List)f.getValue())));
	    		}
	    		
	    	}
	    	
			return (T)criter.uniqueResult();
		
	    }

	    public T save(T obj) {
	     	return (T) getCurrentSession().merge(obj);
		//return hibernateLog.merge(obj);
	    }




		/* (non-Javadoc)
		 * @see com.delmar.core.dao.CoreDaoHibernate#remove(java.lang.Object)
		 */
		public void remove(T obj) {

			getCurrentSession().delete(obj);
	
		}

		/* (non-Javadoc)
		 * @see com.delmar.core.dao.CoreDaoHibernate#updateObject(java.lang.Object)
		 */
		public void updateObject(T obj) {
			getCurrentSession().update(obj);
		}

}
