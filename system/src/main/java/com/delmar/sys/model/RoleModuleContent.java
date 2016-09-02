/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.sys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘大磊 2015年1月15日 上午11:17:55
 */
public class RoleModuleContent {
	public RoleModuleContent()
	{
		
	}
	
	public RoleModuleContent( Module module,List<Operator> operatorList,List<Integer> operIds, Integer accessLevel)
	{
		this.module=module;
		this.operatorList=operatorList;
		this.operIds=operIds;
		this.accessLevel=accessLevel;
	}
	
	private Module module;
	private List<Operator> operatorList;
	private Integer accessLevel;
	
	/**
	 * @return the accessLevel
	 */
	public Integer getAccessLevel() {
		return accessLevel;
	}

	/**
	 * @param accessLevel the accessLevel to set
	 */
	public void setAccessLevel(Integer accessLevel) {
		this.accessLevel = accessLevel;
	}

	private List<Integer> operIds=new ArrayList<Integer>();
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public List<Operator> getOperatorList() {
		return operatorList;
	}
	public void setOperatorList(List<Operator> operatorList) {
		this.operatorList = operatorList;
	}
	public List<Integer> getOperIds() {
		return operIds;
	}
	public void setOperIds(List<Integer> operIds) {
		this.operIds = operIds;
	}
}
