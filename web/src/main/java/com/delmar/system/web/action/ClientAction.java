/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.system.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.model.SearchColumnList;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.Client;
import com.delmar.sys.model.ClientExtra;
import com.delmar.sys.service.ClientExtraService;
import com.delmar.sys.service.ClientService;
import com.delmar.utils.ResourceMessage;

/**
 * @author 刘大磊 2015年1月15日 下午2:56:40
 * 实体信息
 */
public class ClientAction extends CoreEditPrivAction {
	
	
	private List<DatadictTrl> extraPropList;
	private List<ClientExtra> clientExtraList;	
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientExtraService clientExtraService;
	@Autowired
	private DatadictService datadictService;	
		
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "实体信息");
	}
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	private Client client;
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String getModuleName() {
		
		return "client";
	}

	@Override
	public String delete() {
		clientService.deleteByPrimaryKey(client.getId());
		return this.list();
	}

	@Override
	public void deleteList(Integer[] ids) {
		clientService.deleteList(ids);
		
	}

	@Override
	public Integer getModelId() {
		
		return client.getId();
	}

	@Override
	public void editForm() {
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");

		extraPropList=datadictService.getDatadictTrlByValue(DatadictType.CLIENT_EXTRAPROP,ur.getLocale().toString());
		
		
		this.client=this.clientService.selectByPrimaryKey(id);
		
		
		//得到附加信息配置
		Map paramExtra=new HashMap();
		paramExtra.put("clientId", id);
		clientExtraList=this.clientExtraService.selectByExample(paramExtra);		
		
	}

	@Override
	public List search() {
		Map param=new HashMap();
		SearchColumnList searchColumnList=(SearchColumnList)FacesUtils.getValueInHashtableOfSession("searchColumnList");
		if(searchColumnList!=null)
		{
			//example.set("accessString", searchColumnList.buildSql());
			param.put("accessString", searchColumnList.buildSql());
		}
		return clientService.selectByExample(param);
	}

	public void createForm() {
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		
		this.client=new Client();
		
		extraPropList=datadictService.getDatadictTrlByValue(DatadictType.CLIENT_EXTRAPROP,ur.getLocale().toString());		
	}


	public String saveForm() {
		boolean  isNew=false;
		if(client.getId()==null)
		{
			isNew=true;
		}
		id=this.clientService.save(client);
		
		if(isNew)
		{
			this.getIdList().add(id);
			addActionMessage("添加实体信息成功！");
		}
		else
		{
			addActionMessage("修改实体信息成功！");
		}
		return "edit";
	}

	public List<DatadictTrl> getExtraPropList() {
		return extraPropList;
	}

	public void setExtraPropList(List<DatadictTrl> extraPropList) {
		this.extraPropList = extraPropList;
	}

	public List<ClientExtra> getClientExtraList() {
		return clientExtraList;
	}

	public void setClientExtraList(List<ClientExtra> clientExtraList) {
		this.clientExtraList = clientExtraList;
	}
	
	
	

}
