package com.delmar.station.web.action;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import com.delmar.station.model.EDIResponseInfo;
import com.delmar.station.model.NameTypeInfo;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WFReality;
import com.delmar.station.model.WarehouseForwarder;
import com.delmar.station.service.EDIResponseInfoService;
import com.delmar.station.service.RecordActionInfoService;
import com.delmar.station.service.WFDetailService;
import com.delmar.station.service.WFRealityService;
import com.delmar.station.service.WarehouseForwarderService;
import com.delmar.station.service.impl.WFDetailServiceImpl;
import com.delmar.sys.model.UserorgAccess;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.delmar.utils.StringUtil;
import com.delmar.web.model.ObjSelect;

public class WFDetailAction extends CoreEditPrivAction {

	private WFDetail wfDetail;
	private WFDetail lastWFDetail;
	private WarehouseForwarder warehouseForwarder = null;
	
	@Autowired
	private WFDetailService wfDetailService;
	
	@Autowired
	private WarehouseForwarderService warehouseForwarderService;
	
	@Autowired
	private DatadictService datadictService;
	
	@Autowired
	private WFRealityService wfRealityService;
	
	@Autowired
	private RecordActionInfoService recordActionInfoService;
	
	@Autowired
	private EDIResponseInfoService ediResponseInfoService;
	
	protected PrivilegesDataFilter up;
	
	private List<DatadictTrl> packageList;
	private List<NameTypeInfo> nameTypeList ;
	
	protected UserResource ur;
	
	private String printFlag;
	private String updateFlag;
	private String warehouseNo;
	private String searchFlag;
	private String scanSearch;
	protected List<ObjSelect> userOrgAccessList;   //能够访问的公司列表
	private List<DatadictTrl> stationStatusList;  //进仓单状态
	private List<ObjWFDetail> stationList;
	private final String CARGO_PRO_WAREHOUSE = "CargoProWarehouse";
	private String scanMsg;
	private boolean stationEmpty;
	
	private static final Logger logger = Logger.getLogger(WFDetailServiceImpl.class);
	
	@Override
	public String getModuleName() {
		return "wfDetail";
	}

	@Override
	public String delete() {
		
		WFDetail wfd = wfDetailService.getWFDetailById(id);
		Integer masterId = wfd.getMasterID();
		wfDetailService.deleteByPrimaryKey(id);
		wfDetailService.refreshWFRealitySum(masterId);
		return "delete";
	}

	@Override
	public void deleteList(Integer[] ids) {
		wfDetailService.getWFDetailById(id);
	}

	@Override
	public Integer getModelId() {
		return null;
	}

	
	public String print() {
		
		wfDetail = wfDetailService.getWFDetailById(id);
		if (wfDetail != null) {
			warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
		}
		
		return "print";
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
		
		String warehouseNo=request.getParameter("warehouseNo");
		FacesUtils.setValueInHashtableOfSession("warehouseNo", warehouseNo);
		
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
	    	 hbmwhere.addWhereCell("b.warehouseNo", StringUtil.fullYhStr(warehouseNo.trim()), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		
	    }
		
		if (StringUtils.isNotEmpty(statusId)){
			hbmwhere.addWhereCell("b.status", statusId.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(flightDateStart)){
	    	hbmwhere.addWhereCell("b.FlightDate", flightDateStart.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_MORE_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
	    if (StringUtils.isNotEmpty(flightDateEnd)){
	    	hbmwhere.addWhereCell("b.FlightDate", flightDateEnd.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LESS_EQ,HbnHsql.REL_TYPE_AND);
	    }
	    
        if (StringUtils.isNotEmpty(orgIds)) {
      		hbmwhere.addWhereCell("a.companyID", convertCargoCompanyId(orgIds), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
        }  		
        
		if (hbmwhere.hasWhereCell()){
			
			//设置当前为查询状态
			FacesUtils.setValueInHashtableOfSession("queryStatus", "true");	
		}else{
			
			FacesUtils.setValueInHashtableOfSession("queryStatus", "false");	
			hbmwhere.addWhereCell("b.status", "1", HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
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
		stationList = wfDetailService.searchWFDetailList(hbmwhere);
		
		FacesUtils.setValueInHashtableOfSession("stationList", stationList);
		
	    return stationList;
	}
	
	@Override
	public void editForm() {
		wfDetail = wfDetailService.getWFDetailById(id);
		if (wfDetail != null) {
			warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
		}
		
		FacesUtils.setValueInHashtableOfSession("updateFlag", "update");
		
		init();
	}
	
	public String editDetail() {
		
		wfDetail = wfDetailService.getWFDetailById(id);
		if (wfDetail != null) {
			warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
		}
		
		FacesUtils.setValueInHashtableOfSession("updateFlag", "update");
		
		init();
		return "editDetail";
	}
	
	public String queryDetail() {
		
		wfDetail = wfDetailService.getWFDetailById(id);
		if (wfDetail != null) {
			warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
		}
		
		init();
		return "queryDetail";
	}
	
	/**
	 * 新增、编辑或查看进仓明细
	 * @return
	 */
	public String initDetail() {
			
			//编辑、查看的情况
			if (id != null && id > 0) {
				wfDetail = wfDetailService.getWFDetailById(id);
				if (wfDetail != null) {
					warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
				}
				
				init();
				
			//新建进仓明细的情况
			} else {
				warehouseForwarder = warehouseForwarderService.getWareHouseForwarderByNo(warehouseNo);
				lastWFDetail = null;
				List<String> referenceNoList = null;
				wfDetail = new WFDetail();
				if (warehouseForwarder != null) {
					WFReality wfReality = wfRealityService.getWFRealityByMasterId(warehouseForwarder.getId());
					if (wfReality != null) {
						int totalGoodsNumber = 0;
			    		double totalGoodsWeight =0;
			    		double totalGoodsSize = 0;
			    		
			    		if (warehouseForwarder.getGoodsNumber() - wfReality.getGoodsNumber() > 0){
			    			totalGoodsNumber = warehouseForwarder.getGoodsNumber() - wfReality.getGoodsNumber();
			    		}
			    		
			    		if (warehouseForwarder.getGoodsWeight() - wfReality.getGoodsWeight() > 0){
			    			totalGoodsWeight = warehouseForwarder.getGoodsWeight() - wfReality.getGoodsWeight();
			    		}
			    		
			    		if (warehouseForwarder.getGoodsSize() - wfReality.getGoodsSize() > 0){
			    			totalGoodsSize = warehouseForwarder.getGoodsSize() - wfReality.getGoodsSize();
			    		}
			    		
			    		//初始默认数据
			    		BigDecimal  goodsWeight = new BigDecimal(totalGoodsWeight);
			    		BigDecimal  goodsSize = new BigDecimal(totalGoodsSize);
			    		wfDetail.setGoodsNumber(totalGoodsNumber);
			    		wfDetail.setGoodsWeight(goodsWeight.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		wfDetail.setGoodsSize(goodsSize.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
			    		
					} else {
						
						//初始默认数据
						BigDecimal  goodsWeight = new BigDecimal(warehouseForwarder.getGoodsWeight());
			    		BigDecimal  goodsSize = new BigDecimal(warehouseForwarder.getGoodsSize());
						wfDetail.setGoodsNumber(warehouseForwarder.getGoodsNumber());
			    		wfDetail.setGoodsWeight(goodsWeight.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
			    		wfDetail.setGoodsSize(goodsSize.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
					}
					
					wfDetail.setGoodsDesc(warehouseForwarder.getGoodsDesc());
					wfDetail.setMaiTou(warehouseForwarder.getMaiTou());
					wfDetail.setChargeData(warehouseForwarder.getChargeData());
					wfDetail.setMasterID(warehouseForwarder.getId());
					wfDetail.setTrustFileCode(warehouseForwarder.getTrustFileCode());
					wfDetail.setCompanyID(warehouseForwarder.getCompanyID());
					
					if(warehouseForwarder.getToWarehouse().equals(getCurrentUserThird(CARGO_PRO_WAREHOUSE))){
						List<WFDetail> list = wfDetailService.getWFDetailByMasterId(warehouseForwarder.getId());
						
						//在sql中拼接了order by inDate desc 所以取第一个就是最新的一个
						if (list != null && list.size() > 0) {
							lastWFDetail = list.get(0);
						}
						
						if(StringUtils.isNotEmpty(warehouseForwarder.getReferenceNo())){
							String referenceNo = warehouseForwarder.getReferenceNo();
			    			
			    			String[] tempRreferenceNo = referenceNo.split(";");
			    			referenceNoList = new ArrayList<String>();
			    			for(int i=0;i<tempRreferenceNo.length;i++){
			    				referenceNoList.add(tempRreferenceNo[i]);
			    			}
			    		}
					} 
					
					//初始默认数据，在新增进货记录时，如果有最新一笔进货记录，那默认数据从最新一笔进货记录取，否则从进仓单取
					wfDetail.setInDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					if (referenceNoList != null && lastWFDetail != null ) {
						wfDetail.setCarDriver(lastWFDetail.getCarDriver());
						wfDetail.setCarLicenseNo(lastWFDetail.getCarLicenseNo());
					} 
					
					if (lastWFDetail != null) {
						
						wfDetail.setReceiptPerson(lastWFDetail.getReceiptPerson());
						wfDetail.setShippingSpace(lastWFDetail.getShippingSpace());
					}
				
					UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
					packageList = datadictService.getDatadictTrlByValue(DatadictType.PACKAGELIST, ur.getLocale().toString());
					FacesUtils.setValueInHashtableOfSession("packageList", packageList);
				}
			}
			
		return "initDetail";
	}
	
	public String addSave() {
		updateFlag = "addSuccess";
		try {
			warehouseForwarder = warehouseForwarderService.getWareHouseForwarderByNo(warehouseForwarder.getWarehouseNo());
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			
			wfDetail.setZfbz(0);
			
			//companyId 取自DtssUserDetailsHelper.getUser().getStation()
			wfDetail.setCompanyID(getCurrentUserThird(CARGO_PRO_WAREHOUSE));
			wfDetail.setOperator(up.getUser().getName());
			wfDetail.setOperatorName(up.getUser().getName());
			wfDetail.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
			
			wfDetailService.saveOrUpdate(wfDetail);
			
			WFReality wfReality = wfRealityService.getWFRealityByMasterId(warehouseForwarder.getId());
			if (wfReality == null) {
				// 插入
				wfReality = new WFReality();
				wfReality.setTrustFileCode(wfDetail.getTrustFileCode());
				wfReality.setGoodsDesc(wfDetail.getGoodsDesc());
				wfReality.setMaiTou(wfDetail.getMaiTou());
				wfReality.setGoodsNumber(wfDetail.getGoodsNumber());
				wfReality.setNumberPackage(wfDetail.getNumberPackage());
				wfReality.setGoodsWeight(wfDetail.getGoodsWeight());
				wfReality.setGoodsSize(wfDetail.getGoodsSize());
				wfReality.setRemark(wfDetail.getRemark());
				wfReality.setMasterID(wfDetail.getMasterID());
				wfReality.setCompanyID(wfDetail.getCompanyID());
				wfReality.setOperator(wfDetail.getOperator());
				wfReality.setOperatorName(wfDetail.getOperatorName());
				
				wfRealityService.saveOrUpdate(wfReality);
			} else {
				wfDetailService.refreshWFRealitySum(warehouseForwarder.getId());
			}
			
			//172.20.7.250:1433;DatabaseName=EBusiness
			/*RecordActionInfo inf = new RecordActionInfo();
			inf.setOriginCode(String.valueOf(warehouseForwarder.getId()));
			inf.setActionName("进仓记录:" + warehouseForwarder.getId());
			inf.setActionType("message-sys");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			inf.setCreateDate(sdf.format(new Date()));
			inf.setActionCode("in-warehousinadd");
			inf.setToBranch(wfDetail.getCompanyID());
			inf.setZfbz(0);
			
			recordActionInfoService.saveOrUpdate(inf);*/
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			warehouseForwarder = warehouseForwarderService.getWareHouseForwarderByNo(warehouseForwarder.getWarehouseNo());
			
			// 使CargoPro知道做过进仓单了
			EDIResponseInfo edirInfo = new EDIResponseInfo();
			edirInfo.setTrustFileCode(wfDetail.getTrustFileCode());
			edirInfo.setCompanyID(wfDetail.getCompanyID());
			edirInfo.setOperator(wfDetail.getOperator());
			edirInfo.setOperatorName(wfDetail.getOperatorName());
			edirInfo.setBookingNumber("WHEDI" + wfDetail.getMasterID());
			edirInfo.setBatchNo("WHEDI" + wfDetail.getMasterID());
			edirInfo.setInDate(sdf.format(new Date()));
			edirInfo.setEdiType("WH_R");
			edirInfo.setEdiAction("NEW");
			edirInfo.setEdiStatus("1");
			edirInfo.setIsSend(0);
			edirInfo.setUpdateDate(sdf.format(new Date()));
			edirInfo.setBeUse(0);
			
			ediResponseInfoService.saveOrUpdate(edirInfo);
			EDIResponseInfo edirInfo2 = new EDIResponseInfo();
			edirInfo2.setTrustFileCode("WHEDI" + warehouseForwarder.getId());
			edirInfo2.setCompanyID(wfDetail.getCompanyID());
			edirInfo2.setOperator(wfDetail.getOperator());
			edirInfo2.setOperatorName(wfDetail.getOperatorName());
			edirInfo2.setBookingNumber("WHEDI" + wfDetail.getMasterID());
			edirInfo2.setBatchNo("WHEDI" + wfDetail.getMasterID());
			edirInfo2.setInDate(sdf.format(new Date()));
			edirInfo2.setEdiType("WH_R");
			edirInfo2.setEdiAction("NEW");
			edirInfo2.setEdiStatus("1");
			edirInfo2.setIsSend(0);
			edirInfo2.setUpdateDate(sdf.format(new Date()));
			edirInfo2.setBeUse(0);
			ediResponseInfoService.saveOrUpdate(edirInfo2);
			
			//调用DCMS接口 修改FCRDate
			WFReality wfRealityNew = wfRealityService.getWFRealityByMasterId(warehouseForwarder.getId());
			WarehouseForwarder warehouseForwarderVo = warehouseForwarderService.getWareHouseForwarderByNo(warehouseForwarder.getWarehouseNo());
			if(wfRealityNew!=null && warehouseForwarderVo != null){
	    		if(warehouseForwarderVo.getGoodsNumber() == wfRealityNew.getGoodsNumber()){
	    			
	    			edirInfo.setResponseDesc("Package Quantity：" + wfDetail.getGoodsNumber());   	
	    			//调用DCMS接口，修改FCRDate
	    			String resultMessage = wfDetailService.updateDcmsFcrDate(edirInfo, wfDetail.getTrustFileCode());
	    			wfDetail.setResultMessage(resultMessage);
	        	}
	    	}
			
			String createById = String.valueOf(up.getLoginUserId());
			String orgId = String.valueOf(up.getLoginOrgId());
			String clientId = String.valueOf(up.getLoginClientId());
			wfDetailService.createFobMail("WebCore",
    				new String[] {
					"warehouse-detail-new",
					warehouseForwarder.getOperatorEMail(),
					"",
					"1",
					warehouseForwarder.getWarehouseNo(),
					createById,
					orgId,
					clientId},wfDetail);
			
			UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
			packageList = datadictService.getDatadictTrlByValue(DatadictType.PACKAGELIST, ur.getLocale().toString());
			FacesUtils.setValueInHashtableOfSession("packageList", packageList);
		} catch (Exception e) {
			updateFlag = "insertFail";
			e.printStackTrace();
		}
		
		return "initDetail";
	}
	
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
		packageList = datadictService.getDatadictTrlByValue(DatadictType.PACKAGELIST, ur.getLocale().toString());
		FacesUtils.setValueInHashtableOfSession("packageList", packageList);
	}
    
	@Override
	public void createForm() {
		
		
	}

	@Override
	public String saveForm() {
		updateFlag = "updateSuccsess";
		try {
			Integer id = wfDetail.getId();
			String beforeMaiTou = "";
			String beforeCargoRemark = "";
			if (id != null && id >= 0) {
				WFDetail oldWFDetail = wfDetailService.getWFDetailById(id);
				beforeMaiTou = oldWFDetail.getMaiTou();
				beforeCargoRemark = oldWFDetail.getCargoRemark();
			}
			wfDetail = wfDetailService.saveOrUpdate(wfDetail);
			wfDetailService.refreshWFRealitySum(wfDetail.getMasterID());
			// 如果对唛头及货损（备注）进行了修改，则发邮件提醒
			int mailFlag = 0;
			if (beforeMaiTou.length() > 0) {
				if (!beforeMaiTou.equals(wfDetail.getMaiTou())) {
					mailFlag=1;
				}
			}
			
			if (beforeCargoRemark.length() > 0) {
				if (!beforeCargoRemark.equals(wfDetail.getCargoRemark())) {
					if (mailFlag == 1) {
	        			mailFlag = 3;
	        		} else {
	        			mailFlag = 2;
	        		}
				}
			}
			
			up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			String createById = String.valueOf(up.getLoginUserId());
			String orgId = String.valueOf(up.getLoginOrgId());
			String clientId = String.valueOf(up.getLoginClientId());
			
			if (mailFlag != 0) {
				wfDetailService.createUpdateFobMail("WebCore",
        				new String[] {
						"warehouse-detail-new",
						warehouseForwarder.getOperatorEMail(),
						"",
						"1",
						warehouseForwarder.getWarehouseNo(),
						createById,
						orgId,
						clientId},beforeMaiTou,beforeCargoRemark,wfDetail,mailFlag);
			}
			
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		packageList = datadictService.getDatadictTrlByValue(DatadictType.PACKAGELIST, ur.getLocale().toString());
		FacesUtils.setValueInHashtableOfSession("packageList", packageList);
		
		if (wfDetail != null) {
			warehouseForwarder = warehouseForwarderService.selectByPrimaryKey(wfDetail.getMasterID());
		}
		} catch (Throwable e) {
			updateFlag = "updateFailed";
		}
		return "initDetail";
	}
	
	private String convertCargoCompanyId(String orgIds){
		
		if (orgIds == null || orgIds.length() <= 0) {
			return "";
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserorgAccess> userorgListStr = (List<UserorgAccess>)request.getSession().getAttribute("userorgaccessList");
		
		if (orgIds.equals(""))
			return "";
		
		StringBuffer cargoCompanyIds = new StringBuffer();
		orgIds=","+orgIds+",";
		for (UserorgAccess userorgaccess:userorgListStr){
			if (orgIds.indexOf(","+userorgaccess.getOrgId().toString()+",")>-1)
				cargoCompanyIds.append(userorgaccess.getOrg().getValue()+",");
		}
		
		if (cargoCompanyIds.length()>0)
		   cargoCompanyIds.deleteCharAt(cargoCompanyIds.length()-1);
		
		return cargoCompanyIds.toString();
	}
	
	public String scan () {
		
		String toHouseWare = getCurrentUserThird(CARGO_PRO_WAREHOUSE);
		HbnHsql hbmwhere = new HbnHsql();
		hbmwhere.setClassName(WarehouseForwarder.class);
		List<WarehouseForwarder> list = null;
		
		if (warehouseNo != null && warehouseNo.trim().length() > 0) {
			if (toHouseWare.trim().length() == 0) {
				up=(PrivilegesDataFilter)FacesUtils.getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
				
				String orgIds = "";
				
				int accessLevel=up.getAccessDataLevelByStruts2().intValue();
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
				} else if (accessLevel==DelmarConst.ACCESS_LEVEL_ORG) {
					orgIds = String.valueOf(up.getLoginOrgId());
					if (StringUtil.isNotEmpty(orgIds)){
						hbmwhere.addWhereCell("a.companyID", convertCargoCompanyId(String.valueOf(up.getLoginOrgId())), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_IN,HbnHsql.REL_TYPE_AND);
			        }  
				} 
		        	
		        if (StringUtils.isNotEmpty(warehouseNo)){
			    	 hbmwhere.addWhereCell("a.warehouseNo", StringUtil.fullYhStr(warehouseNo.trim()), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);		
			    }
			} else {
				
				if (StringUtils.isNotEmpty(warehouseNo)){
			    	 hbmwhere.addWhereCell("a.warehouseNo", StringUtil.fullYhStr(warehouseNo.trim()), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_LIKE,HbnHsql.REL_TYPE_AND);	
			    	 hbmwhere.addWhereCell("a.toWarehouse", toHouseWare.trim(), HbnHsql.VALUE_TYPE_STR, HbnHsql.LK_TYPE_EQ,HbnHsql.REL_TYPE_AND);
			    }
			}
			
			list = warehouseForwarderService.searchWarehouseForwarders(hbmwhere);
			if (list != null && list.size() == 1) {
				warehouseForwarder = list.get(0);
			}
		}
		
		
		//如果有查询条件 并且查询结果为null时，表示没有查询到，否则是初始进入页面
		if (warehouseForwarder == null) {
			if (warehouseNo != null) {
				scanMsg = "scanFailed";
			}
			
			return "initDetail";
		}
		
		initDetail();
		
		UserResource ur=(UserResource)ServletActionContext.getRequest().getSession().getAttribute("resource");
		packageList = datadictService.getDatadictTrlByValue(DatadictType.PACKAGELIST, ur.getLocale().toString());
		FacesUtils.setValueInHashtableOfSession("packageList", packageList);
		return "initDetail";
	}
	
	public WFDetail getWfDetail() {
		return wfDetail;
	}

	public void setWfDetail(WFDetail wfDetail) {
		this.wfDetail = wfDetail;
	}

	public WarehouseForwarder getWarehouseForwarder() {
		return warehouseForwarder;
	}

	public void setWarehouseForwarder(WarehouseForwarder warehouseForwarder) {
		this.warehouseForwarder = warehouseForwarder;
	}

	public List<DatadictTrl> getPackageList() {
		return packageList;
	}

	public void setPackageList(List<DatadictTrl> packageList) {
		this.packageList = packageList;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getWarehouseNo() {
		return warehouseNo;
	}

	public void setWarehouseNo(String warehouseNo) {
		this.warehouseNo = warehouseNo;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public WFDetail getLastWFDetail() {
		return lastWFDetail;
	}

	public void setLastWFDetail(WFDetail lastWFDetail) {
		this.lastWFDetail = lastWFDetail;
	}

	public List<DatadictTrl> getStationStatusList() {
		return stationStatusList;
	}

	public void setStationStatusList(List<DatadictTrl> stationStatusList) {
		this.stationStatusList = stationStatusList;
	}
	
	public List<ObjWFDetail> getStationList() {
		return stationList;
	}

	public void setStationList(List<ObjWFDetail> stationList) {
		this.stationList = stationList;
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

	public List<NameTypeInfo> getNameTypeList() {
		return nameTypeList;
	}

	public void setNameTypeList(List<NameTypeInfo> nameTypeList) {
		this.nameTypeList = nameTypeList;
	}

	public String getScanMsg() {
		return scanMsg;
	}

	public void setScanMsg(String scanMsg) {
		this.scanMsg = scanMsg;
	}

	public boolean isStationEmpty() {
		return stationEmpty;
	}

	public void setStationEmpty(boolean stationEmpty) {
		this.stationEmpty = stationEmpty;
	}

	public List<ObjSelect> getUserOrgAccessList() {
		return userOrgAccessList;
	}

	public void setUserOrgAccessList(List<ObjSelect> userOrgAccessList) {
		this.userOrgAccessList = userOrgAccessList;
	}

}
