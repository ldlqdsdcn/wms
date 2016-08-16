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
import com.delmar.sys.model.UserExtra;
import com.delmar.sys.service.UserExtraService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年8月28日 下午4:31:06 
 * 类说明 
 */
public class UserExtraAction  extends CoreEditPrivAction {
	private UserExtra  userExtra;
	
	@Autowired
	private UserExtraService userExtraService;
	@Autowired
	private DatadictService datadictService;		
	
	private List<UserExtra> userExtraList;	
	private List<DatadictTrl> extraPropList;
	
	private Integer sysUserId;
	
	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "用户附加属性管理");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "userExtra";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		userExtraService.deleteByPrimaryKey(userExtra.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		userExtraService.deleteUserExtraList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return userExtra.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		userExtra= userExtraService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		extraPropList=datadictService.getDatadictTrlByValue(DatadictType.USER_EXTRAPROP,ur.getLocale().toString(),up.getLoginClientId());		

	
		if (sysUserId!=null)
		{
			//得到附加信息配置
			Map paramExtra=new HashMap();
			paramExtra.put("sysUserId", sysUserId);
			userExtraList=this.userExtraService.selectByExample(paramExtra);
			
			
		
			for(UserExtra utp:userExtraList)
			{
				DatadictTrl propTrl=datadictService.getTrlByDataId(utp.getPropId(), ur.getLocale().toString());
				if (propTrl!=null)
					utp.setPropName(propTrl.getName());
	
		
			}
		} else
		{
			userExtraList=new ArrayList<UserExtra>();
		}
		
		
		
		
		return userExtraList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		userExtra=new UserExtra();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		userExtraService.save(userExtra);
		return "edit";
	}

	public UserExtra getUserExtra() {
		return userExtra;
	}

	public void setUserExtra(UserExtra userExtra) {
		this.userExtra = userExtra;
	}

	public List<UserExtra> getUserExtraList() {
		return userExtraList;
	}

	public void setUserExtraList(List<UserExtra> userExtraList) {
		this.userExtraList = userExtraList;
	}

	public List<DatadictTrl> getExtraPropList() {
		return extraPropList;
	}

	public void setExtraPropList(List<DatadictTrl> extraPropList) {
		this.extraPropList = extraPropList;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	
	
	
	

}
