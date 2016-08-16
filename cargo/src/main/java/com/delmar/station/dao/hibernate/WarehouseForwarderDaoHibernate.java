package com.delmar.station.dao.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.core.model.HbnHsql;
import com.delmar.station.dao.WarehouseForwarderDao;
import com.delmar.station.model.NameTypeInfo;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WarehouseForwarder;

/** 
 * @author Jack Zhang zhangja@delmarchina.com
 * @version V1.0 2015年7月9日 上午17:51:52 
 * 
 */
@Repository("warehouseForwarderDaoHibernate") 
public class WarehouseForwarderDaoHibernate extends CoreHibernateDaoImpl<WarehouseForwarder> implements WarehouseForwarderDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Class<WarehouseForwarder> getModelClass() {
		return WarehouseForwarder.class;
	}
	
	public List<ObjWarehouseForwarder> searchWarehouseForwarderList(HbnHsql hbnhsql) {
		
		List<ObjWarehouseForwarder> list = new ArrayList<ObjWarehouseForwarder>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
		
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("select a.id,a.warehouseNo,a.flightDate,a.lockDate ,a.goodsNumber,a.goodsWeight,a.goodsSize,a.numberPackage,a.goodsDesc,a.createDate,a.status," +
			"b.id as RealID, b.goodsNumber as RealGoodsNumber,b.goodsWeight as RealGoodsWeight,b.goodsSize as RealGoodsSize from WarehouseForwarder A " +
			" LEFT OUTER JOIN WFReality b ON a.id=b.MasterID where a.zfbz='0' and a.status in ('1','2')");

		sqlBuf.append(fromHql);
		sqlBuf.append(hbnhsql.getSqlWhere());	
		sqlBuf.append(" order by a.FlightDate desc");	
		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		
		hbnhsql.setQueryValue(query);
		list = query.setResultTransformer(Transformers.aliasToBean(ObjWarehouseForwarder.class)).list();
		
		return list;		
	}

	public WarehouseForwarder selectByPrimaryKey(Integer id) {
		
		if (id == null || String.valueOf(id).length() <= 0) {
			return null;
		}
		List<WarehouseForwarder> list = new ArrayList<WarehouseForwarder>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WarehouseForwarder a where a.id = " + id);
		Query query = getCurrentSession().createQuery(fromHql.toString());
		if (query == null) {
			return null;
		}
		
		list = query.list();
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}

	public WarehouseForwarder getWareHouseForwarderByNo(String warehouseNo) {
		
		if (warehouseNo == null || String.valueOf(warehouseNo).length() <= 0) {
			return null;
		}
		List<WarehouseForwarder> list = new ArrayList<WarehouseForwarder>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WarehouseForwarder a where a.warehouseNo = '" + warehouseNo + "'");
		Query query = getCurrentSession().createQuery(fromHql.toString());
		if (query == null) {
			return null;
		}
		
		list = query.list();
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}

	public List<NameTypeInfo> getPackList() {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("From NameTypeInfo a where a.nameType='PackageType'");
		
		return getCurrentSession().createQuery(sqlStr.toString()).list();
	}

	public List<WarehouseForwarder> searchWarehouseForwarders(HbnHsql hbmwhere) {
		
		StringBuffer fromHql = new StringBuffer();
		fromHql.append("from WarehouseForwarder a where 1=1 ");
		fromHql.append(hbmwhere.getSqlWhere());	
		Query query=getCurrentSession().createQuery(fromHql.toString());
		
		hbmwhere.setQueryValue(query);
		return query.list();
	}
	
}
