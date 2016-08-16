package com.delmar.cargo.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.BusinessHisDao;
import com.delmar.cargo.model.BusinessClient;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.model.BusinessPerformance;
import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月26日 下午8:34:18 
 * 类说明 
 */
@Repository("businessHisDaoHibernate") 
public class BusinessHisDaoHibernate  extends CoreHibernateDaoImpl<BusinessForwarder> implements BusinessHisDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	//客户走货和未走货分析
	//CustomerBusiness
	private static final String SQL_CUSTOMERBUSINESS = "Select a.CusCode,b.CusName,a.CusType,a.PlaneOcean,a.Fcllcl,a.OutIn, "
			+" a.TrustType,a.firstEtd,a.lastEtd,a.AirPortFrom,a.AirPortTo,a.SalesCode,c.Name as SalesName,a.CompanyId "
			+ " From  CustomerBusiness a "
			+ " LEFT OUTER JOIN CustomerInfo b ON A.CusCode=b.CusCode "
			+ " LEFT OUTER JOIN BussinessMan c ON c.Code=a.SalesCode "
			+ " Where a.CusCode<>''  ";
	
	private static final String SQL_SALESPERFORMANCE = "Select a.CompanyId,a.PlaneOcean,a.PerforType,a.PersonCode code,"
			+"a.Imonth,a.OutIn,a.Inumber,a.profit,b.name name "
			+ " From  State_Performance a "
			+ " LEFT OUTER JOIN BussinessMan b ON A.PersonCode=b.Code"
			+ " Where a.personCode<>''  ";	
	
	private static final String SQL_SALESPERFORMANCE_SUM = "Select sum(a.Inumber) Inumber,sum(profit) profit "
			+ " From  State_Performance a "
			+ " Where a.personCode<>''  ";		
	
	private static final String SQL_SALESPERFORMANCE_PK = "Select a.PersonCode code,sum(a.Inumber) Inumber,sum(profit) profit "
			+ " From  State_Performance a "
			+ " Where a.personCode<>''    ";		

	
	
	private static final String SQL_CLIENT_CHINA_OCEAN_EXPORT = "Select a.ShipperCode clientCode,a.ShipperCusCName clientName,"
			+" Count(a.TrustCode) inumber,sum(a.TeuNum) cargoVolume,sum(a.profit) profit "
			+ " From  State_Ocean a "
			+ " Where a.TrustCode<>'' and a.OutIn=0 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_CHINA_OCEAN_IMPORT = "Select a.ConsignCode clientCode,a.ConsignCusCName clientName,"
			+" Count(a.TrustCode) inumber,sum(a.TeuNum) cargoVolume,sum(a.profit) profit "
			+ " From  State_Ocean a "
			+ " Where a.TrustCode<>'' and a.OutIn=1 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_CHINA_AIR_EXPORT = "Select a.ShipperCode clientCode,a.ShipperCusCName clientName,"
			+" Count(a.FileCode) inumber,sum(a.GoodsChargeWeight) cargoVolume,sum(a.profit) profit "
			+ " From  State_AIR a "
			+ " Where a.FileCode<>'' and a.OutIn=0 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_CHINA_AIR_IMPORT = "Select a.ConsignCode clientCode,a.ConsignCusCName clientName,"
			+" Count(a.FileCode) inumber,sum(a.GoodsChargeWeight) cargoVolume,sum(a.profit) profit "
			+ " From  State_AIR a "
			+ " Where a.FileCode<>'' and a.OutIn=1 and a.BeZhidan in (1,2) ";	
	
	
	private static final String SQL_CLIENT_FOREIGN_OCEAN_IMPORT = "Select a.ShipperCode clientCode,a.ShipperCusCName clientName,"
			+" Count(a.TrustCode) inumber,sum(a.TeuNum) cargoVolume,sum(a.profit) profit "
			+ " From  State_Ocean a "
			+ " Where a.TrustCode<>'' and a.OutIn=1 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_FOREIGN_OCEAN_EXPORT = "Select a.ConsignCode clientCode,a.ConsignCusCName clientName,"
			+" Count(a.TrustCode) inumber,sum(a.TeuNum) cargoVolume,sum(a.profit) profit "
			+ " From  State_Ocean a "
			+ " Where a.TrustCode<>'' and a.OutIn=0 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_FOREIGN_AIR_IMPORT = "Select a.ShipperCode clientCode,a.ShipperCusCName clientName,"
			+" Count(a.FileCode) inumber,sum(a.GoodsChargeWeight) cargoVolume,sum(a.profit) profit "
			+ " From  State_AIR a "
			+ " Where a.FileCode<>'' and a.OutIn=1 and a.BeZhidan in (1,2) ";	
	
	private static final String SQL_CLIENT_FOREIGN_AIR_EXPORT = "Select a.ConsignCode clientCode,a.ConsignCusCName clientName,"
			+" Count(a.FileCode) inumber,sum(a.GoodsChargeWeight) cargoVolume,sum(a.profit) profit "
			+ " From  State_AIR a "
			+ " Where a.FileCode<>'' and a.OutIn=0 and a.BeZhidan in (1,2) ";	
	
	
	//客户业绩
	//State_Ocean  State_Air  State_Other
	//按照月来进行
	

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return BusinessForwarder.class;
	}
	
	
	public List<CustomerBusiness> searchCustomerBusiness(HbnHsql hbnhsql)
	{
		List<CustomerBusiness> list=new ArrayList<CustomerBusiness>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(SQL_CUSTOMERBUSINESS);		
		sqlBuf.append(hbnhsql.getSqlWhere());		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(CustomerBusiness.class)).list();
	
		return list;			
	}
	
	
	
	public List<BusinessPerformance> searchSalesPerformance(HbnHsql hbnhsql)
	{
		List<BusinessPerformance> list=new ArrayList<BusinessPerformance>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(SQL_SALESPERFORMANCE);		
		sqlBuf.append(hbnhsql.getSqlWhere());		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessPerformance.class)).list();
	
		return list;			
	}
	
	public BusinessPerformance getSalesPerformance(HbnHsql hbnhsql)
	{
		List<BusinessPerformance> list=new ArrayList<BusinessPerformance>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(SQL_SALESPERFORMANCE_SUM);		
		sqlBuf.append(hbnhsql.getSqlWhere());		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessPerformance.class)).list();
		if (list.size()>0)
    		return (BusinessPerformance)list.get(0);
		else
			return null;
    				
	}
	
	
	
	public List<BusinessPerformance> searchSalesPerformancePK(HbnHsql hbnhsql)
	{
		List<BusinessPerformance> list=new ArrayList<BusinessPerformance>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(SQL_SALESPERFORMANCE_PK);		
		sqlBuf.append(hbnhsql.getSqlWhere());
		sqlBuf.append(" group by a.PersonCode ");	
		
		String statSqlStr="SELECT aa.*,bb.name name from ("+sqlBuf.toString()+") aa "
		+ " LEFT OUTER JOIN BussinessMan bb ON Aa.Code=bb.Code "
		+" where bb.RoleType='Sales' "
		+" order by aa.Profit asc";
		
		SQLQuery query=getCurrentSession().createSQLQuery(statSqlStr);
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessPerformance.class)).list();
	
		return list;			
	}
	
	
	
	public List<BusinessClient> searchChinaClientState(HbnHsql hbnhsql,String exportImport,String planeOcean)
	{
		List<BusinessClient> list=new ArrayList<BusinessClient>(); 
		String queryStr="";
		
		if (planeOcean.equals("ocean"))
		{
			if (exportImport.equals("export"))
				queryStr=SQL_CLIENT_CHINA_OCEAN_EXPORT;
			else if (exportImport.equals("import"))
				queryStr=SQL_CLIENT_CHINA_OCEAN_IMPORT;
		}
		else
		{
			if (exportImport.equals("export"))
				queryStr=SQL_CLIENT_CHINA_AIR_EXPORT;
			else if (exportImport.equals("import"))
				queryStr=SQL_CLIENT_CHINA_AIR_IMPORT;
			
		}
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(queryStr);		
		sqlBuf.append(hbnhsql.getSqlWhere());
		
		
		if (planeOcean.equals("ocean"))
		{
			if (exportImport.equals("export"))
				sqlBuf.append(" group by a.ShipperCode,a.ShipperCusCName ");		
			else if (exportImport.equals("import"))
				sqlBuf.append(" group by a.ConsignCode,a.ConsignCusCName ");
		}
		else
		{
			if (exportImport.equals("export"))
				sqlBuf.append(" group by a.ShipperCode,a.ShipperCusCName ");		
			else if (exportImport.equals("import"))
				sqlBuf.append(" group by a.ConsignCode,a.ConsignCusCName ");
			
		}		
		
		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessClient.class)).list();
	
		return list;			
	}
		
	public List<BusinessClient> searchForeignClientState(HbnHsql hbnhsql,String exportImport,String planeOcean)
	{
		List<BusinessClient> list=new ArrayList<BusinessClient>(); 
		String queryStr="";
		
		if (planeOcean.equals("ocean"))
		{
			if (exportImport.equals("export"))
				queryStr=SQL_CLIENT_FOREIGN_OCEAN_EXPORT;
			else if (exportImport.equals("import"))
				queryStr=SQL_CLIENT_FOREIGN_OCEAN_IMPORT;
		}
		else
		{
			if (exportImport.equals("export"))
				queryStr=SQL_CLIENT_FOREIGN_AIR_EXPORT;
			else if (exportImport.equals("import"))
				queryStr=SQL_CLIENT_FOREIGN_AIR_IMPORT;
			
		}
		
		StringBuffer sqlBuf=new StringBuffer();
  	    sqlBuf.append(queryStr);		
		sqlBuf.append(hbnhsql.getSqlWhere());
		
		
		if (planeOcean.equals("ocean"))
		{
			if (exportImport.equals("export"))
				sqlBuf.append(" group by a.ConsignCode,a.ConsignCusCName ");				
			else if (exportImport.equals("import"))
				sqlBuf.append(" group by a.ShipperCode,a.ShipperCusCName ");				
		}
		else
		{
			if (exportImport.equals("export"))
				sqlBuf.append(" group by a.ConsignCode,a.ConsignCusCName ");				
			else if (exportImport.equals("import"))
				sqlBuf.append(" group by a.ShipperCode,a.ShipperCusCName ");				
				
			
		}		
				
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessClient.class)).list();
	
		return list;			
	}
			
	
}
