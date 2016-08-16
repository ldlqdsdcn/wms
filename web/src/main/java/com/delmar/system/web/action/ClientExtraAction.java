package com.delmar.system.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.ClientExtra;
import com.delmar.sys.service.ClientExtraService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月28日 下午4:38:51 
 * 类说明 
 */
public class ClientExtraAction  extends CoreEditPrivAction {
	private ClientExtra  clientExtra;
	
	@Autowired
	private ClientExtraService clientExtraService;
	@Autowired
	private DatadictService datadictService;		
	
	private List<ClientExtra> clientExtraList;	
	private List<DatadictTrl> extraPropList;
	
	private Integer sysClientId;
	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "实体附加属性管理");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "clientExtra";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		clientExtraService.deleteByPrimaryKey(clientExtra.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		clientExtraService.deleteClientExtraList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return clientExtra.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		clientExtra= clientExtraService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		extraPropList=datadictService.getDatadictTrlByValue(DatadictType.CLIENT_EXTRAPROP,ur.getLocale().toString(),up.getLoginClientId());		

	
		if (sysClientId!=null)
		{
			//得到附加信息配置
			Map paramExtra=new HashMap();
			paramExtra.put("clientId", sysClientId);
			clientExtraList=this.clientExtraService.selectByExample(paramExtra);
			
			
		
			for(ClientExtra utp:clientExtraList)
			{
				DatadictTrl propTrl=datadictService.getTrlByDataId(utp.getPropId(), ur.getLocale().toString());
				if (propTrl!=null)
					utp.setPropName(propTrl.getName());
	
		
			}
		} else
		{
			clientExtraList=new ArrayList<ClientExtra>();
		}
		
		
		
		
		return clientExtraList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		clientExtra=new ClientExtra();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		clientExtraService.save(clientExtra);
		return "edit";
	}

	public ClientExtra getClientExtra() {
		return clientExtra;
	}

	public void setClientExtra(ClientExtra clientExtra) {
		this.clientExtra = clientExtra;
	}

	public List<ClientExtra> getClientExtraList() {
		return clientExtraList;
	}

	public void setClientExtraList(List<ClientExtra> clientExtraList) {
		this.clientExtraList = clientExtraList;
	}

	public List<DatadictTrl> getExtraPropList() {
		return extraPropList;
	}

	public void setExtraPropList(List<DatadictTrl> extraPropList) {
		this.extraPropList = extraPropList;
	}

	public Integer getSysClientId() {
		return sysClientId;
	}

	public void setSysClientId(Integer sysClientId) {
		this.sysClientId = sysClientId;
	}
	
	
	
	
	

}
