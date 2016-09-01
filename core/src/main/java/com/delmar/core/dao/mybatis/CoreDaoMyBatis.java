/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                   *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                           *
 *****************************************************************************/
package com.delmar.core.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.dao.CoreDao;
import com.delmar.core.dao.SqlSessionForLog;
import com.delmar.core.model.CoreModel;
import com.delmar.utils.DmLog;
import com.delmar.utils.ResourceMessage;

/**
 * @author 刘大磊 2014年12月18日 下午5:33:31
 */
public abstract class CoreDaoMyBatis<T>    implements CoreDao<T>  {
	
    protected DmLog logger =DmLog.getLogger("SQLLogger."+getClass().getName()); 
    
	@Autowired(required=true)
	protected SqlSessionTemplate sqlSessionTemplate;
	//TODO 日志操作
	@Autowired
	protected SqlSessionForLog  sqlSessionForLog;

	//TODO 日志操作
	/*public void setSqlSessionForLog(SqlSessionForLog sqlSessionForLog) {
		this.sqlSessionForLog = sqlSessionForLog;
	}*/
	protected static final String selectList_SQL=".selectByExample";

	protected static final String selectByPaging_SQL=".selectByPaging";
	//private static final String delete_SQL=".delete";
	protected static final String updateByPrimaryKey=".updateByPrimaryKey";
	

	protected static final String updateByPrimaryKeySelective=".updateByPrimaryKeySelective";
	protected static final String countByExample=".countByExample";
	protected static final String deleteByExample=".deleteByExample";
	protected static final String insert=".insert";
	
	protected static final String selectByExample=".selectByExample";
	protected static final String selectByPrimaryKey=".selectByPrimaryKey";
	protected static final String selectFieldsByPrimaryKey=".selectFieldsByPrimaryKey";	
	protected static final String deleteByPrimaryKey=".deleteByPrimaryKey";
	protected static final String insertSelective=".insertSelective";
	
	
	
	
	

	private String insertSQL()
	{
		return getSqlName()+insert;
	}
	private String updateSQL()
	{
		return getSqlName()+updateByPrimaryKey;
	}
	private String updateSQLSelective()
	{
		return getSqlName()+updateByPrimaryKeySelective;
	}
	private String countSQL()
	{
		return getSqlName()+countByExample;
		
	}
	private String selectListSQL()
	{

		return getSqlName()+selectList_SQL;
	}
	
	
	
	protected abstract String getSqlName();
	protected String getString()
	{
		return getClass().getName();
	}
	
	
	public String getText(String keyName,String defaultValue) {
		
		ResourceBundle bundle=ResourceBundle.getBundle(ResourceMessage.BUNDLE_NAME,Locale.getDefault());
		String keyValue=bundle.getString(keyName);
		return keyValue;
		
	}
	
	public T getByExample(Map example)  {


		return (T) sqlSessionTemplate.selectOne(this.selectListSQL(),example);
	}

	public Integer insert(T model)   {
		  return sqlSessionForLog.insert(insertSQL(), (CoreModel) model);
	}

	public void update(T model)   {

	  sqlSessionForLog.update(updateSQLSelective(), (CoreModel) model);
	
		
		
	}

	public Integer save(T model)   {

			CoreModel cm=(CoreModel)model;
			Integer id=cm.getId();
			if(id==null||id==0)
			{
				id=this.insert(model);
			}
			else
			{
				update(model);
			}
			id=cm.getId();
			return id;
	
				
	}
	public Integer deleteByExample(Map example)   {
		//TODO 日志操作
		List<T> list=this.selectByExample(example);
		if(list!=null)
		{
			for(T obj:list)
			{
				sqlSessionForLog.delete(this.getSqlName()+deleteByPrimaryKey, (CoreModel)obj);
			}
			
			return list.size();
		}
		return 0;
		
	}
	
	public void updateAll(T model)    {
      
		sqlSessionForLog.update(updateSQL(),(CoreModel)model);
		
	}
	public Integer countObjects(Map example)   
	{
		return sqlSessionTemplate.selectOne(countSQL(), example);
	}
	public Integer insertSelective(T model)   
	{
		return sqlSessionForLog.insert(this.getSqlName()+insertSelective,(CoreModel)model);
	}
	public Integer deleteByPrimaryKey(Integer id)   
	{
		//TODO 日志操作
		/*T obj=selectByPrimaryKey(id);
		return sqlSessionForLog.delete(this.getSqlName()+deleteByPrimaryKey, obj);*/
		CoreModel coreModel=(CoreModel)this.selectByPrimaryKey(id);
	    return	 sqlSessionForLog.delete(this.getSqlName()+deleteByPrimaryKey, coreModel);
		    
	}
	@SuppressWarnings("unchecked")
	public T selectByPrimaryKey(Integer id)   
	{
		return (T)sqlSessionTemplate.selectOne(this.getSqlName()+selectByPrimaryKey, id);
	}
	
	@SuppressWarnings("unchecked")
	public T selectFieldsByPrimaryKey(String fieldColumns,Integer id)   
	{
	
		Map<String, Object> newMap = new HashMap<>(2);
		newMap.put("columns", fieldColumns);
		newMap.put("id", id);
		return (T)sqlSessionTemplate.selectOne(this.getSqlName()+selectFieldsByPrimaryKey,newMap);
		
	}
	
	public List<T> selectByExample(Map example)  	{
		if(example!=null)
		{
			Integer pageNo=(Integer)example.get("pageNo");
			Integer pageSize=(Integer)example.get("pageSize");
			if(pageNo!=null&&pageSize!=null)
			{
				int offset = (pageNo - 1) * pageSize;
				int limit = pageSize;
				return  sqlSessionTemplate.selectList(this.selectListSQL(),example,new RowBounds(offset,limit));
			}

		}
		return sqlSessionTemplate.selectList(this.getSqlName()+selectByExample, example);
		
	}
	public List<T> selectByPaging(Map example)  	{

		return sqlSessionTemplate.selectList(this.getSqlName()+selectByExample, example);

	}
	

}
