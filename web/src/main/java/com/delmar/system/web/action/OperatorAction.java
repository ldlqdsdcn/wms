/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.sys.model.Module;
import com.delmar.sys.model.Operator;
import com.delmar.sys.model.OperatorModule;
import com.delmar.sys.service.ModuleService;
import com.delmar.sys.service.OperatorModuleService;
import com.delmar.sys.service.OperatorService;
import com.delmar.utils.ResourceMessage;

/**
 * @author 刘大磊 2015年1月16日 下午2:59:41
 */
public class OperatorAction extends CoreEditPrivAction{
	private  Operator  operator=new Operator();
	private List<Integer> modIds=new ArrayList<Integer>();
	private List<Module> moduleList=new ArrayList<Module>();
	private OperatorService operatorService;
	private ModuleService moduleService;
	@Autowired
	private OperatorModuleService  operatorModuleService;
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "操作日志");
	}

	
	@Override
	public String delete() {
		
		return null;
	}

	@Override
	public void deleteList(Integer[] ids) {
		operatorService.deleteOperators(ids);
		
	}

	@Override
	public void editForm() {
	
		operator=operatorService.selectByPrimaryKey(id);
		Map param=new HashMap();
		param.put("operatorId", id);
		List<OperatorModule> omList=operatorModuleService.selectByExample(param);
		for(OperatorModule om:omList)
		{
			modIds.add(om.getModuleId());
		}
		//modIds=
	}

	@Override
	public Integer getModelId() {
		
		return operator.getId();
	}

	@Override
	public String getModuleName() {
	
		return "operator";
	}

	@Override
	public List search() {
		
		return this.operatorService.selectByExample(null);
	}

	@Override
	public void createForm() {
		operator=new Operator();
		operator.setInit("N");
		
	}

	public void setOperatorService(OperatorService operatorService) {
		this.operatorService = operatorService;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public List<Integer> getModIds() {
		return modIds;
	}

	public void setModIds(List<Integer> modIds) {
		this.modIds = modIds;
	}

	public List<Module> getModuleList() {
		
		moduleList=this.moduleService.selectByExample(null);
		return moduleList;
	}

	

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Override
	public String saveForm() {
		operatorService.save(operator);
		this.id=operator.getId();
		editForm();
		return EDIT;
	}
}
