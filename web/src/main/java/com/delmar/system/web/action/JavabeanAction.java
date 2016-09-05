/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delmar.core.dao.util.SqlUtil;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.action.PrivilegeOperator;
import com.delmar.sys.model.Javabean;
import com.delmar.sys.service.JavabeanService;
import com.delmar.utils.ResourceMessage;
import org.apache.commons.collections.map.HashedMap;

/**
 * @author 刘大磊 2015年1月16日 上午10:43:12
 */
public class JavabeanAction extends CoreEditPrivAction {
	private JavabeanService javabeanService;
	private Javabean javabean=new Javabean();
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "JAVA Beam信息");
	}
	
	@Override
	public void createForm() {
		javabean=new Javabean();

	}

	@Override
	public String delete() {
		if(!PrivilegeOperator.isDelete())
		{
			return NO_PRIVILEGE;
		}
		javabeanService.deleteByPrimaryKey(javabean.getId());
		return list();
	}

	@Override
	public void deleteList(Integer[] ids) {
		Map  param=new HashMap();
		param.put("accessString", SqlUtil.arrayToInSQL("id",ids) );
		javabeanService.deleteByExample(param);
		addActionMessage("删除 java 类信息成功！");
	}

	@Override
	public void editForm() {
		javabean=javabeanService.selectByPrimaryKey(id);

	}

	@Override
	public Integer getModelId() {
		
		return javabean.getId();
	}

	@Override
	public String getModuleName() {
		
		return "javabean";
	}

	@Override
	public String saveForm() {
		javabeanService.save(this.javabean);
		this.id=javabean.getId();
		return "edit";
	}

	@Override
	public List search() {
		Map<String,Object> param=new HashedMap();
		param.put("searchString",getSearchWhere());
		return javabeanService.selectByExample(param);
	}

	public void setJavabeanService(JavabeanService javabeanService) 
	{
		this.javabeanService = javabeanService;
	}

	public Javabean getJavabean() 
	{
		return javabean;
	}

	public void setJavabean(Javabean javabean) 
	{
		this.javabean = javabean;
	}

}
