package com.delmar.station.service;

import java.util.List;

import com.delmar.core.model.HbnHsql;
import com.delmar.station.model.EDIResponseInfo;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WFDetail;

public interface WFDetailService {

	public WFDetail getWFDetailById(Integer id);

	public WFDetail saveOrUpdate(WFDetail wfDetail);

	public void deleteByPrimaryKey(Integer id);

	public void refreshWFRealitySum(Integer masterID);
	
	public List<WFDetail> getWFDetailByMasterId(Integer masterID);

	public void createUpdateFobMail(String string, String[] strings,
			String beforeMaiTou, String beforeCargoRemark, WFDetail wfDetail,
			int mailFlag);

	public List<ObjWFDetail> searchWFDetailList(HbnHsql hbmwhere);

	public void createFobMail(String string, String[] strings, WFDetail wfDetail);

	public String updateDcmsFcrDate(EDIResponseInfo edirInfo, String string);
	
	public List<ObjWFDetail> viewRecordByMasterId(Integer masterId);
	public ObjWFDetail sumRecordByMasterId(Integer masterId);


}
