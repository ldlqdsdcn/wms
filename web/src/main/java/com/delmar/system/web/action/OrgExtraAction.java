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
import com.delmar.sys.model.OrgExtra;
import com.delmar.sys.service.OrgExtraService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月28日 下午4:48:47 
 * 类说明 
 */
public class OrgExtraAction extends CoreEditPrivAction {
	private OrgExtra  orgExtra;
	
	@Autowired
	private OrgExtraService orgExtraService;
	@Autowired
	private DatadictService datadictService;		
	
	private List<OrgExtra> orgExtraList;	
	private List<DatadictTrl> extraPropList;
	
	private Integer sysOrgId;
	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "组织附加属性管理");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "orgExtra";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		orgExtraService.deleteByPrimaryKey(orgExtra.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		orgExtraService.deleteOrgExtraList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return orgExtra.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		orgExtra= orgExtraService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		extraPropList=datadictService.getDatadictTrlByValue(DatadictType.ORG_EXTRAPROP,ur.getLocale().toString(),up.getLoginOrgId());		

	
		String sysOrgId=request.getParameter("orgId");
		
		request.setAttribute("sysOrgId", sysOrgId);
		if (sysOrgId!=null)
		{
			//得到附加信息配置
			Map paramExtra=new HashMap();
			paramExtra.put("orgId", sysOrgId);
			orgExtraList=this.orgExtraService.selectByExample(paramExtra);
			
			
		
			for(OrgExtra utp:orgExtraList)
			{
				DatadictTrl propTrl=datadictService.getTrlByDataId(utp.getPropId(), ur.getLocale().toString());
				if (propTrl!=null)
					utp.setPropName(propTrl.getName());
	
		
			}
		} else
		{
			orgExtraList=new ArrayList<OrgExtra>();
		}
		
		
		
		
		return orgExtraList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		orgExtra=new OrgExtra();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		orgExtraService.save(orgExtra);
		return "edit";
	}

	public OrgExtra getOrgExtra() {
		return orgExtra;
	}

	public void setOrgExtra(OrgExtra orgExtra) {
		this.orgExtra = orgExtra;
	}

	public List<OrgExtra> getOrgExtraList() {
		return orgExtraList;
	}

	public void setOrgExtraList(List<OrgExtra> orgExtraList) {
		this.orgExtraList = orgExtraList;
	}

	public List<DatadictTrl> getExtraPropList() {
		return extraPropList;
	}

	public void setExtraPropList(List<DatadictTrl> extraPropList) {
		this.extraPropList = extraPropList;
	}

	public Integer getSysOrgId() {
		return sysOrgId;
	}

	public void setSysOrgId(Integer sysOrgId) {
		this.sysOrgId = sysOrgId;
	}
	
	
	
	
	

}
