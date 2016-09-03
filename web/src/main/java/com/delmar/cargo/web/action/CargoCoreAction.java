package com.delmar.cargo.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.Datadict;
import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.sys.model.User;
import com.delmar.sys.model.UserThirdParty;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.sys.service.UserService;
import com.delmar.sys.service.UserThirdPartyService;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月29日 上午11:40:29 
 * 类说明 
 */
public abstract class CargoCoreAction extends CoreEditPrivAction {

	
	protected List<DatadictTrl> busModeList;   //业务类型
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	public  PrivilegesDataFilter up;
	public UserResource ur;
	
	@Autowired
	private DatadictService datadictService;	
	@Autowired
	private UserThirdPartyService userThirdPartyService;
	@Autowired
	private UserService userService;
	
	
	{

	}
	
	@Override
	public String delete() {
	    return this.list();
	}
	
	@Override
	public void deleteList(Integer[] ids) {
		
	}
	
	@Override
	public String deletes() {
		return super.deletes();
	}
	
	@Override
	public Integer getModelId() {
		return 0;
	}	
	
	 @Override
	 public void editForm() {
		 
	 }
	 
	 @Override
	 public List search() {
		 return null;
	 }
	
	@Override
	public void createForm() {
		
	}
	

	@Override
	public String saveForm() {
		return "edit";	
	}
	
	
	
	

	protected void init()
	{
		ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		}else if (accessLevel==DelmarConst.ACCESS_LEVEL_USERGROUP)
		{
			FacesUtils.setValueInHashtableOfSession("orgVisible","group");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());
			FacesUtils.setValueInHashtableOfSession("beGroup","false");
		}
		else
		{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		
		busModeList=datadictService.getDatadictTrlByValue(DatadictType.MODE,ur.getLocale().toString());		

	}
	
	protected String convertCargoCompanyId(String orgIds)
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		List<UserorgAccess> userorgListStr=(List<UserorgAccess>)request.getSession().getAttribute("userorgaccessList");
		
		if (orgIds.equals(""))
			return "";
		
		StringBuffer cargoCompanyIds=new StringBuffer();
		orgIds=","+orgIds+",";
		for (UserorgAccess userorgaccess:userorgListStr)
		{
			if (orgIds.indexOf(","+userorgaccess.getOrgId().toString()+",")>-1)
				cargoCompanyIds.append(userorgaccess.getOrg().getValue()+",");
		}
		
		if (cargoCompanyIds.length()>0)
		   cargoCompanyIds.deleteCharAt(cargoCompanyIds.length()-1);
		
		return cargoCompanyIds.toString();
	}

	protected String getCargoSalesCode()
	{    
		if (getCurrentUserThird().equals(""))
		  return "0";
	     else
		   return getCurrentUserThird();
	}
	
	
	protected String getCargoProSalesGroup()
	{
		
		String cargoSalesCodes=(String)FacesUtils.getValueInHashtableOfSession("cargoSalesCode");
		
		if (cargoSalesCodes!=null)
		{
			return cargoSalesCodes;
		}
		
		String groupUserIds=(String)ServletActionContext.getRequest().getSession().getAttribute("usergroupaccessListStr");
		
		String cargoUserIds=getCargoProSalesBySysUserId(groupUserIds);
		
		FacesUtils.setValueInHashtableOfSession("cargoSalesCode",cargoUserIds);
		return cargoUserIds;
		

	}
	
	protected String getCargoProSalesBySysUserId(String ids)
	{
		//这里再选择出用户所关联的第三方账号
		Map<String,Object> paramThird=new HashMap<>();
		paramThird.put("accessString"," sys_user_id in ("+ids+")");		
		List<UserThirdParty> thirdPartyList=userThirdPartyService.selectByExample(paramThird);
		
		StringBuffer cargoSalesBuf=new StringBuffer();
		for (UserThirdParty oneObj:thirdPartyList)
		{
			Datadict systemDataDict=datadictService.selectByPrimaryKey(oneObj.getSystemId());
			Datadict partyTypeDataDict=datadictService.selectByPrimaryKey(oneObj.getPartyTypeId());
			
			String key=systemDataDict.getValue()+partyTypeDataDict.getValue();
			
			if (key.equalsIgnoreCase("CargoProSales"))
			{
				cargoSalesBuf.append(oneObj.getPartyUser()).append(",");	
			}
	 	}
		
		if (cargoSalesBuf.length()>0)
		{
			cargoSalesBuf.deleteCharAt(cargoSalesBuf.length()-1);
			return cargoSalesBuf.toString();
		}
		
		
		
		
		return "0";
	}
	
	
	protected String getCargoProSalesBySysUserName(String userName)
	{
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("accessString"," ((name in ("+StringUtil.convertToSqlParam(userName)+")) or (username in ("+StringUtil.convertToSqlParam(userName)+"))) ");
		
		List<User> userList=userService.selectByExample(param);
		StringBuffer sysUserIds=new StringBuffer();
		for (User obj:userList)
		{
			sysUserIds.append(obj.getId()).append(",");
		}
		
		if (sysUserIds.length()!=0)
		{
			sysUserIds.deleteCharAt(sysUserIds.length()-1);
			return getCargoProSalesBySysUserId(sysUserIds.toString());
		}
		
		return "0";
		

		
	}

	public List<DatadictTrl> getBusModeList() {
		return busModeList;
	}


	public void setBusModeList(List<DatadictTrl> busModeList) {
		this.busModeList = busModeList;
	}


	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}


	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	public PrivilegesDataFilter getUp() {
		return up;
	}

	public void setUp(PrivilegesDataFilter up) {
		this.up = up;
	}

	public UserResource getUr() {
		return ur;
	}

	public void setUr(UserResource ur) {
		this.ur = ur;
	}
	
	
	
}
