package com.delmar.station.dao;

import java.util.List;
import java.util.Map;

import com.delmar.core.dao.CoreHibernateDao;
import com.delmar.core.model.HbnHsql;
import com.delmar.station.model.NameTypeInfo;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WarehouseForwarder;

public interface WarehouseForwarderDao extends CoreHibernateDao<WarehouseForwarder>  {

	public List<ObjWarehouseForwarder> searchWarehouseForwarderList(HbnHsql hbnhsql);

	public WarehouseForwarder selectByPrimaryKey(Integer id);

	public WarehouseForwarder getWareHouseForwarderByNo(String warehouseNo);

	public List<NameTypeInfo> getPackList();

	public List<WarehouseForwarder> searchWarehouseForwarders(HbnHsql hbmwhere);
}
