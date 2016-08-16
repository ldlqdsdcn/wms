package com.delmar.station.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.base.model.DatadictTrl;
import com.delmar.base.model.DatadictType;
import com.delmar.base.service.DatadictService;
import com.delmar.core.DelmarConst;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.web.bean.UserResource;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.station.dao.WarehouseForwarderDao;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WarehouseForwarder;
import com.delmar.station.service.WarehouseForwarderService;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

/**
 * <p>功能描述:进仓单Action<p>
 * <p/>
 * 创建日期  2015-5-9 18:02:11<br>
 *
 * @author  Jack Zhang zhangja@delmarchina.com
 * @version 1.0
 */
public class WarehouseForwarderAction extends CoreEditPrivAction {
	
    
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	private WarehouseForwarderService warehouseForwarderService;
	
	@Autowired
	private WarehouseForwarderDao stationDao;
	
    private static final long serialVersionUID = 1L;
    private WarehouseForwarder warehouseForwarder;
    protected PrivilegesDataFilter up;
    protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
    private List<DatadictTrl> stationStatusList;  //进仓单状态
    private List<ObjWarehouseForwarder> stationList;
    private String warehouseNo;
	private List<DatadictTrl> packageList;
	private final String CARGO_PRO_WAREHOUSE = "CargoProWarehouse";
	private String scanSearch;
	private boolean stationEmpty;

	/**
     * 进仓单列表
     *
     * @return
     */
    @Override
	public String list() {
		
    	init();
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL) {
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG) {
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		} else {
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		
		//默认下发
		FacesUtils.setValueInHashtableOfSession("statusId","1");
		return super.list();
	}
    
    private void init(){
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		if (getCurrentUserThird(CARGO_PRO_WAREHOUSE).trim().length() == 0) {
			stationEmpty = true;
		}
		
		stationStatusList = datadictService.getDatadictTrlByValue(DatadictType.STATIONSTATUS,ur.getLocale().toString());
	}

	@Override
	public String getModuleName() {
		return "warehouseForwarder";
	}

	@Override
	public String delete() {
		return "list";
	}

	@Override
	public void deleteList(Integer[] ids) {
	}

	@Override
	public Integer getModelId() {
		//return station.getId();
		
		return null;
	}

	@Override
	public void editForm() {
		//station=this.eBusinessService.selectByPrimaryKey(id);
		init();
	}

	@Override
	public List search() {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
		int accessLevel=up.getAccessDataLevelByStruts2().intValue();
		if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL){
			FacesUtils.setValueInHashtableOfSession("orgVisible","true");
			userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
		} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG){
			FacesUtils.setValueInHashtableOfSession("orgVisible","org");
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId());			
		}else{
			FacesUtils.setValueInHashtableOfSession("orgIds",up.getLoginOrgId()); 	
			FacesUtils.setValueInHashtableOfSession("orgVisible","false");
		}
		String warehouseNo = request.getParameter("warehouseNo");
		FacesUtils.setValueInHashtableOfSession("warehouseNo", warehouseNo);
		
		String airPortTo=request.getParameter("airPortTo");
		FacesUtils.setValueInHashtableOfSession("airPortTo", airPortTo);
		
		String statusId=request.getParameter("statusId");
		FacesUtils.setValueInHashtableOfSession("statusId",statusId);
		
		String flightDateStart=request.getParameter("flightDateStart");
		FacesUtils.setValueInHashtableOfSession("flightDateStart", flightDateStart);
		
		String flightDateEnd=request.getParameter("flightDateEnd");
		FacesUtils.setValueInHashtableOfSession("flightDateEnd", flightDateEnd);
		
		String orgIds=request.getParameter("orgIds");
		FacesUtils.setValueInHashtableOfSession("orgIds",orgIds);
		
		
		HbnHsql hbmwhere=new HbnHsql();
		hbmwhere.setClassName(ObjWarehouseForwarder.class);
		
		if (StringUtils.isNotEmpty(warehouseNo)){
	    	 hbmwhere.addWhereCell("a.warehouseNo", StringUtil.fullYhStr(warehouseNo.trim()), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		
	    }
		
		if (StringUtils.isNotEmpty(airPortTo)){
	    	 hbmwhere.addWhereCell("a.airPortTo", StringUtil.fullYhStr(airPortTo.trim()), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		
	    }
		
		if (StringUtils.isNotEmpty(statusId)){
			hbmwhere.addWhereCell("a.status", statusId.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(flightDateStart)){
	    	hbmwhere.addWhereCell("a.FlightDate", flightDateStart.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(flightDateEnd)){
	    	hbmwhere.addWhereCell("a.FlightDate", flightDateEnd.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(flightDateEnd)){
	    	hbmwhere.addWhereCell("a.FlightDate", flightDateEnd.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(orgIds)) {
      		hbmwhere.addWhereCell("a.companyID", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        } 
	    
		if (hbmwhere.hasWhereCell()){
			
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");			
		}else{
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			
			//默认查询的条件
			hbmwhere.addWhereCell("a.status", "1", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			hbmwhere.addWhereCell("a.LockDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()).trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
		}
		
		String toWareHouse = getCurrentUserThird(CARGO_PRO_WAREHOUSE);
		if (StringUtils.isNotEmpty(toWareHouse)) {
			hbmwhere.addWhereCell("a.ToWareHouse", toWareHouse.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);	
		} else {
			
			//如果是集团权限则能查看所勾选公司的，若是组织权限那就是登陆时选择的部门
			String companyIds = "";
			if (accessLevel==DelmarConst.ACCESS_LEVEL_ALL) {
				userOrgAccessList=(List<ObjSelect>)ServletActionContext.getRequest().getSession().getAttribute("userOrgAccessSelectList");
				int i = 0;
				for (ObjSelect obj : userOrgAccessList) {
					if (i == 0) {
						companyIds += obj.getValue();
					} else {
						companyIds += "," + obj.getValue();
					}
					i++;
				}
				hbmwhere.addWhereCell("a.companyID", companyIds, HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG){
		        hbmwhere.addWhereCell("a.companyID", convertCargoCompanyId(String.valueOf(up.getLoginOrgId())), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			}
		}
		
		stationList = warehouseForwarderService.searchWarehouseForwarderList(hbmwhere);
		
		FacesUtils.setValueInHashtableOfSession("stationList", stationList);
		
	    return stationList;
	}
	
	private String convertCargoCompanyId(String orgIds){
		
		if (orgIds == null || orgIds.length() <= 0) {
			return "";
		}
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
	
	@Override
	public void createForm() {
		
		warehouseForwarder= new WarehouseForwarder();
		init();
	}

	@Override
	public String saveForm() {
		return null;
	}

	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

	public List<DatadictTrl> getStationStatusList() {
		return stationStatusList;
	}

	public void setStationStatusList(List<DatadictTrl> stationStatusList) {
		this.stationStatusList = stationStatusList;
	}
	
	public List<ObjWarehouseForwarder> getStationList() {
		return stationList;
	}

	public void setStationList(List<ObjWarehouseForwarder> stationList) {
		this.stationList = stationList;
	}

	public WarehouseForwarder getWarehouseForwarder() {
		return warehouseForwarder;
	}

	public void setWarehouseForwarder(WarehouseForwarder warehouseForwarder) {
		this.warehouseForwarder = warehouseForwarder;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}
	
	public List<DatadictTrl> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<DatadictTrl> packageList) {
		this.packageList = packageList;
	}

	public String getScanSearch() {
		return scanSearch;
	}

	public void setScanSearch(String scanSearch) {
		this.scanSearch = scanSearch;
	}

	@Override
	public String getPurpose() {
		return null;
	}

	public boolean isStationEmpty() {
		return stationEmpty;
	}

	public void setStationEmpty(boolean stationEmpty) {
		this.stationEmpty = stationEmpty;
	}
	
}
