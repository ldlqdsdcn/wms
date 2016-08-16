package com.delmar.crm.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.crm.model.CustomerExtraPropDefine;
import com.delmar.crm.service.CustomerExtraPropDefineService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.ResourceMessage;
import com.delmar.web.model.ObjSelect;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年9月8日 下午4:54:25 
 * 类说明 
 */
public class CustomerExtraPropDefineAction  extends CoreEditPrivAction {
	private CustomerExtraPropDefine  customerExtraPropDefine;
	
	@Autowired
	private DatadictService datadictService;
	@Autowired
	private CustomerExtraPropDefineService customerExtraPropDefineService;		
	
	
	private List<ObjSelect> propList;	
	
	public String getPurpose()
	{
		return  ResourceMessage.getMessageWithDefault("public.javaaction."+this.getClass().getName(), "客户档案个人标签管理");
	}
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "propDefine";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		customerExtraPropDefineService.deleteByPrimaryKey(customerExtraPropDefine.getId());
		return "list";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		customerExtraPropDefineService.deleteCustomerExtraPropDefineList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return customerExtraPropDefine.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		customerExtraPropDefine= customerExtraPropDefineService.selectByPrimaryKey(id);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		PrivilegesDataFilter up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		List<DatadictTrl> extraPropList=datadictService.getDatadictTrlByValue(DatadictType.CUSTOMER_EXTRAPROPLABEL,ur.getLocale().toString(),up.getLoginClientId());
		List<CustomerExtraPropDefine> userPropList=customerExtraPropDefineService.selectPropByUserId(up.getLoginUserId());
		propList=new ArrayList<ObjSelect>();
		
		for (DatadictTrl obj:extraPropList)
		{
			ObjSelect objSelect=new ObjSelect();
			objSelect.setId(obj.getDatadictId());
			objSelect.setName(obj.getName());
			
			propList.add(objSelect);
		}
		
		

		for (CustomerExtraPropDefine obj:userPropList)
		{
			ObjSelect objSelect=new ObjSelect();
			objSelect.setId(obj.getId());
			objSelect.setName(obj.getPropValue());
			
			propList.add(objSelect);
		}	

		
		
		return propList;
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		customerExtraPropDefine=new CustomerExtraPropDefine();
	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		customerExtraPropDefineService.save(customerExtraPropDefine);
		return "edit";
	}

	public CustomerExtraPropDefine getCustomerExtraPropDefine() {
		return customerExtraPropDefine;
	}

	public void setCustomerExtraPropDefine(
			CustomerExtraPropDefine customerExtraPropDefine) {
		this.customerExtraPropDefine = customerExtraPropDefine;
	}

	public List<ObjSelect> getPropList() {
		return propList;
	}

	public void setPropList(List<ObjSelect> propList) {
		this.propList = propList;
	}


	
	
	
	

}
