package com.delmar.station.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.model.HbnHsql;
import com.delmar.station.dao.WarehouseForwarderDao;
import com.delmar.station.model.NameTypeInfo;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WFReality;
import com.delmar.station.model.WarehouseForwarder;
import com.delmar.station.service.WFRealityService;
import com.delmar.station.service.WarehouseForwarderService;

@Service("warehouseForwarderService")
public class WarehouseForwarderServiceImpl implements WarehouseForwarderService{

	@Autowired
	private WarehouseForwarderDao warehouseForwarderDao;
	
	@Autowired
	private WFRealityService wfRealityService;
	public List<ObjWarehouseForwarder> searchWarehouseForwarderList(HbnHsql hbnhsql) {
		
		return warehouseForwarderDao.searchWarehouseForwarderList(hbnhsql);
	}
	
	public WarehouseForwarder selectByPrimaryKey(Integer id) {
		
		return warehouseForwarderDao.selectByPrimaryKey(id);
	}

	public WarehouseForwarder getWareHouseForwarderByNo(String warehouseNo) {
		
		return warehouseForwarderDao.getWareHouseForwarderByNo(warehouseNo);
	}

	public WarehouseForwarder initWareHouseForwarderByNo(String warehouseNo) {
		WarehouseForwarder wf = warehouseForwarderDao.getWareHouseForwarderByNo(warehouseNo);
		if (wf != null) {
			WFReality wfReality = wfRealityService.getWFRealityByMasterId(wf.getId());
			if (wfReality != null) {
				int totalGoodsNumber = 0;
	    		double totalGoodsWeight =0;
	    		double totalGoodsSize = 0;
	    		
	    		if (wf.getGoodsNumber() - wfReality.getGoodsNumber() > 0){
	    			
	    			totalGoodsNumber = wf.getGoodsNumber() - wfReality.getGoodsNumber();
	    		}
	    		
	    		if (wf.getGoodsWeight() - wfReality.getGoodsWeight() > 0){
	    			totalGoodsWeight = wf.getGoodsWeight() - wfReality.getGoodsWeight();
	    		}
	    		
	    		if (wf.getGoodsSize() - wfReality.getGoodsSize() > 0){
	    			totalGoodsSize = wf.getGoodsSize() - wfReality.getGoodsSize();
	    		}
	    		
	    		wf.setGoodsNumberTo(totalGoodsNumber);
	    		wf.setGoodsWeightTo(totalGoodsWeight);
	    		wf.setGoodsSizeTo(totalGoodsSize);
			} else {
				wf.setGoodsNumberTo(wf.getGoodsNumber());
	    		wf.setGoodsWeightTo(wf.getGoodsWeight());
	    		wf.setGoodsSizeTo(wf.getGoodsSize());
			}
		}
		return wf;
	}

	public List<NameTypeInfo> getPackList() {
		return warehouseForwarderDao.getPackList();
	}

	public List<WarehouseForwarder> searchWarehouseForwarders(HbnHsql hbmwhere) {
		return warehouseForwarderDao.searchWarehouseForwarders(hbmwhere);
	}
}
