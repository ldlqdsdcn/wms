package com.delmar.station.service;

import java.util.List;
import com.delmar.core.model.HbnHsql;
import com.delmar.station.model.NameTypeInfo;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WarehouseForwarder;

public interface WarehouseForwarderService {

	
	public List<ObjWarehouseForwarder> searchWarehouseForwarderList(HbnHsql hbnhsql);

	public WarehouseForwarder selectByPrimaryKey(Integer id);

	public WarehouseForwarder getWareHouseForwarderByNo(String warehouseNo);

	public WarehouseForwarder initWareHouseForwarderByNo(String warehouseNo);

	public List<NameTypeInfo> getPackList();

	public List<WarehouseForwarder> searchWarehouseForwarders(HbnHsql hbmwhere);
		
		
}
