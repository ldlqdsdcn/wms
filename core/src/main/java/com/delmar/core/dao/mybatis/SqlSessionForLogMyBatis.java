/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.core.dao.mybatis;



import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.content.SessionContent;
import com.delmar.core.dao.SqlSessionForLog;
import com.delmar.core.dao.UserInterface;
import com.delmar.core.model.Changelog;
import com.delmar.core.model.CoreModel;
import com.delmar.core.model.Table;
import com.delmar.core.model.TableColumn;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年1月9日 上午11:57:00
 */
@Repository("sqlSessionForLog") 
public class SqlSessionForLogMyBatis implements SqlSessionForLog {
	private Logger logger=Logger.getLogger(SqlSessionForLogMyBatis.class);
	@Autowired(required=true)
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired(required=false)
	private HttpServletRequest request; 
	private Gson gson=new Gson();
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.SqlSessionForLog#update(java.lang.String, java.lang.Object)
	 */
	public int update(String statement, CoreModel model) {
		this.writeLog(model, SessionContent.OPERATE_UPDATE);
		return sqlSessionTemplate.update(statement, model);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.SqlSessionForLog#delete(java.lang.String, java.lang.Object)
	 */
	public int delete(String statement, CoreModel model) {
		Integer id=null;
		id=model.getId();
		writeLog(model, SessionContent.OPERATE_DELETE);
		return sqlSessionTemplate.delete(statement, model);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.SqlSessionForLog#insert(java.lang.String, java.lang.Object)
	 */
	public int insert(String statement, CoreModel model) {
		int returnvalue=sqlSessionTemplate.insert(statement, model);
		this.writeLog(model, SessionContent.OPERATE_INSERT);
		return returnvalue;
	}
	   public void writeLog(CoreModel model,String operateType)
	   {
		 String name=  model.getClass().getName();
		 Map<String,Object> param=new HashMap<String,Object>();
		 param.put("className", name);
		 param.put("outLog", "Y");
		List<Table> tableList= sqlSessionTemplate.selectList("com.delmar.core.mybatis.sql.TableMapper.selectByExample", param);
		 
		 Table table=null;
		 if(tableList!=null&&tableList.size()>0)
		 {
			 table=tableList.get(0);
		 }
		 try
		 {
		 if(table!=null)
		 {
			 param=new HashMap<String,Object>();
			 param.put("tableId", table.getId());
			 List<TableColumn> tableColumnList=sqlSessionTemplate.selectList("com.delmar.core.mybatis.sql.TableColumnMapper.selectByExample", param);
			 Map<String,Object> obj=new HashMap<String,Object>();
			 if(tableColumnList!=null)
			 {
				 for(TableColumn column:tableColumnList)
				 {
					obj.put(column.getAttributeName(), PropertyUtils.getProperty(model, column.getAttributeName()));
					
				 }
			 }
			 Changelog log=new Changelog();
			 log.setOperateType(operateType);
			 log.setCreated(new Date());
			 log.setContext(gson.toJson(obj));
			 
			 if(request!=null)
			 {
				 UserInterface user=(UserInterface)request.getSession().getAttribute("loginUser"); 
				if(user!=null)
				{
					log.setUserId(user.getId());
					log.setUsername(user.getUsername());
				}
				else
				{
					 String userName=SessionContent.getUserId(Thread.currentThread().getId());
					 log.setUsername(userName);
				}
			 }
			 else
			 {
				 String userName=SessionContent.getUserId(Thread.currentThread().getId());
				 log.setUsername(userName);
			 }
		
			 Object pk=PropertyUtils.getProperty(model, "id");
			 if(pk!=null)
			 log.setPk((Integer)pk);
			 if(StringUtil.isNotEmpty(table.getBuPk()))
			 {
				 Object buPk=PropertyUtils.getProperty(model, table.getBuPk());
				 if(buPk!=null)
				 log.setBuPk(buPk.toString()); 
			 
			 }
			
			 log.setTableId(table.getId());
			 sqlSessionTemplate.insert("com.delmar.core.mybatis.sql.ChangelogMapper.insert", log);	
		 }
		 
		 }
		 catch(Exception e)
		 {
			 logger.error("插入操作日志异常",e);
		 }
	   }
}
