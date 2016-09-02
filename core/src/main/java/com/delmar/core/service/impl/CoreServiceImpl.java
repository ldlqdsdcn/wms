/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.CoreService;
import com.delmar.utils.DmLog;
import com.delmar.utils.ResourceMessage;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author 刘大磊 2014年12月22日 上午11:56:58
 */
public abstract  class CoreServiceImpl<T> implements CoreService<T> {

	
    protected DmLog logger =DmLog.getLogger("InfoLogger."+getClass().getName()); 
    
	protected abstract CoreDao<T> getCoreDao();
	protected String getString()
	{
		return getClass().getName();
	}
	
	
	public String getText(String keyName,String defaultValue) {
		
		ResourceBundle bundle=ResourceBundle.getBundle(ResourceMessage.BUNDLE_NAME,Locale.getDefault());
		String keyValue=bundle.getString(keyName);
		return keyValue;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#getByExample(java.util.Map)
	 */
	public T getByExample(Map example)   {
		   return getCoreDao().getByExample(example);


	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#insert(java.lang.Object)
	 */
	public Integer insert(T model)   {
		

		return getCoreDao().insert(model);
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#update(java.lang.Object)
	 */
	public void update(T model)   {
		getCoreDao().update(model);
	
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#updateAll(java.lang.Object)
	 */
	public void updateAll(T model)   {
		getCoreDao().updateAll(model);
	
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#save(java.lang.Object)
	 */
	public Integer save(T model)   {
		return getCoreDao().save(model);
	
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#deleteByExample(java.util.Map)
	 */
	public Integer deleteByExample(Map example)   {
		return getCoreDao().deleteByExample(example);
			
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#countObjects(java.util.Map)
	 */
	public Integer countObjects(Map example)   {
		return getCoreDao().countObjects(example);
	
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#insertSelective(java.lang.Object)
	 */
	public Integer insertSelective(T model)   {
		return getCoreDao().insertSelective(model);
			
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#deleteByPrimaryKey(java.lang.Integer)
	 */
	public Integer deleteByPrimaryKey(Integer id)   {
		return getCoreDao().deleteByPrimaryKey(id);
		
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#selectByPrimaryKey(java.lang.Integer)
	 */
	public T selectByPrimaryKey(Integer id)   {
		return getCoreDao().selectByPrimaryKey(id);
	
	}
	
	public T selectFieldsByPrimaryKey(String fieldColumns,Integer id)   {
		return getCoreDao().selectFieldsByPrimaryKey(fieldColumns,id);
	
	}	

	/* (non-Javadoc)
	 * @see com.delmar.core.service.CoreService#selectByExample(java.util.Map)
	 */
	public List<T> selectByExample(Map example)   {
		return getCoreDao().selectByExample(example);			
	}



}
