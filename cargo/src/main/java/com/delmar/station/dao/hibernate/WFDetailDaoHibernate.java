package com.delmar.station.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.delmar.common.dao.BaseHibernateDao;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.core.model.CriteriaH;
import com.delmar.core.model.HbnHsql;
import com.delmar.station.dao.WFDetailDao;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.station.model.WFDetail;
import com.delmar.station.model.WarehouseForwarder;

@Repository("WFDetailDaoHibernate")
public class WFDetailDaoHibernate extends BaseHibernateDao<WFDetail> implements WFDetailDao{

	public WFDetail selectByPrimaryKey(Integer id) {
		if (id == null || String.valueOf(id).length() <= 0) {
			return null;
		}
		List<WFDetail> list = new ArrayList<WFDetail>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WFDetail a where a.id = " + id);
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

	@Override
	public Class getModelClass() {
		return WFDetail.class;
	}

	public WFDetail saveOrUpdate(WFDetail wfDetail) {
		if (wfDetail.isnew()) {
			wfDetail.setId(getMaxId("WFDetail"));
			getCurrentSession().save(wfDetail);
			getCurrentSession().flush();
		} else {
			getCurrentSession().merge(wfDetail);
		}
		getCurrentSession().clear();
		return wfDetail;
	}

	public void deleteByPrimaryKey(Integer id) {
		
		if (id == null || String.valueOf(id).length() <= 0) {
			return;
		}
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("delete from WFDetail a where a.id = " + id);
		getCurrentSession().createQuery(fromHql.toString()).executeUpdate();
		getCurrentSession().flush();
		getCurrentSession().clear();
	}

	public List<WFDetail> getWFDetailByMasterId(Integer masterID) {
		if (masterID == null || String.valueOf(masterID).length() <= 0) {
			return null;
		}
		List<WFDetail> list = new ArrayList<WFDetail>(); 
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("from WFDetail a where a.masterID = " + masterID + " order by a.inDate desc");
		Query query = getCurrentSession().createQuery(fromHql.toString());
		if (query == null) {
			return list;
		}
		
		return query.list();
	}
	
	public List<ObjWFDetail> searchWFDetailList(HbnHsql hbnhsql) {
		
		List<ObjWFDetail> list = new ArrayList<ObjWFDetail>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
		
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("select a.id,a.referenceNo,a.goodsNumber,a.goodsWeight,a.goodsSize,a.numberPackage,a.goodsDesc," + 
				"a.createDate,a.inDate,a.sizeDescription,a.cargoRemark,a.receiptPerson,a.carDriver,a.totalAMount, b.warehouseNo,b.FlightDate,b.LockDate,b.status masterstatus from WFDetail  A " +
			" LEFT OUTER JOIN WarehouseForwarder b ON a.MasterID=b.ID where a.zfbz='0' and b.status in ('1','2')");

		sqlBuf.append(fromHql);
		sqlBuf.append(hbnhsql.getSqlWhere());	
		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		
		hbnhsql.setQueryValue(query);
		list = query.setResultTransformer(Transformers.aliasToBean(ObjWFDetail.class)).list();
		getCurrentSession().clear();
		return list;		
	}
	
	public List<ObjWFDetail> searchWFDetailList(Integer masterId) {
		List<ObjWFDetail> list = new ArrayList<ObjWFDetail>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
		
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("select b.warehouseNo,b.FlightDate,b.LockDate,b.status,a.referenceNo"
				+ ", a.id, a.inDate,a.goodsNumber,a.goodsWeight,a.goodsSize,a.receiptPerson,a.carDriver "+
	    "from WFDetail a  INNER JOIN WareHouseForwarder b ON a.masterID=b.ID  where a.zfbz=0 and a.masterID = " + masterId);

		sqlBuf.append(fromHql);
		
		SQLQuery query=getCurrentSession().createSQLQuery(fromHql.toString());
		list = query.setResultTransformer(Transformers.aliasToBean(ObjWFDetail.class)).list();
		return list;		
	}

	public ObjWFDetail sumRecordByMasterId(Integer masterId) {
		
		List<ObjWFDetail> list = new ArrayList<ObjWFDetail>(); 
		StringBuffer sqlBuf=new StringBuffer();
		
		StringBuffer fromHql = new StringBuffer();
	    
		// 初始化时用,初始化时为升序，普通查询为降序
		fromHql.append("select sum(a.goodsNumber) as sumGoodsNumber,sum(a.goodsWeight) as sumGoodsWeight,sum(a.goodsSize) as sumGoodsSize "+
	    "from WFDetail a where a.zfbz=0 and a.masterID = " + masterId);

		sqlBuf.append(fromHql);
		
		SQLQuery query=getCurrentSession().createSQLQuery(fromHql.toString());
		list = query.setResultTransformer(Transformers.aliasToBean(ObjWFDetail.class)).list();
		
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		
		return null;
	}


}
