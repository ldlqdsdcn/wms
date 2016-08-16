package com.delmar.cargo.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.TrustContextDao;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.TrustContext;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:16:27 
 * 类说明 
 */
@Repository("trustContextDao") 
public class TrustContextDaoHibernate extends CoreHibernateDaoImpl<TrustContext> implements TrustContextDao {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String SQL_OCEANHAWBINFO = "Select A.TrustCode TrustFileCode,a.CompanyId "
			+ " From  TrustContext a"
			+ " LEFT OUTER JOIN TrustContext b ON A.MainCode=b.TrustCode and a.CompanyID=b.CompanyID"
			+ " LEFT OUTER JOIN PlaneCompanyInfo c ON A.ShipCompany=c.PlaneCompanyCode"
			+ " LEFT OUTER JOIN CustomerInfo d ON A.COLoadCompany=d.CusCode"
			+ " LEFT OUTER JOIN BussinessMan e ON A.Bussiness=e.Code"
			+ " LEFT OUTER JOIN BussinessMan ee ON A.CSCode=ee.Code"
			+ " LEFT OUTER JOIN BussinessMan eee ON A.DocOpCode=eee.Code"			
			+ " LEFT OUTER JOIN CustomerInfo f ON A.TrustSource=f.CusCode"
			+ " LEFT OUTER JOIN Station j ON A.StationCode=j.Code"
			+ " LEFT OUTER JOIN TrustFileInfo ff ON a.TrustCode=ff.TrustFileCode  and a.CompanyID=ff.CompanyID"
			+ " LEFT OUTER JOIN TrustFileAddition fff ON a.TrustCode=fff.TrustFileCode  and a.CompanyID=fff.CompanyID "
			+ " Where a.TrustCode<>'' and a.flightDate>= ? ";
	
	private static final String SQL_OCEANTRUSTFILECODEBYHAWB="Select TrustCode TrustFileCode From TrustContext where bezhidan=2 and hawb=? ";
	private static final String SQL_OCEANTRUSTFILECODEBYMAWB="Select TrustCode TrustFileCode From TrustContext where bezhidan=1 and maincode=? ";

	

	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return TrustContext.class;
	}
	
	
	public List<BusinessForwarder> getBusForwarder(String flightDate)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		list=getCurrentSession().createSQLQuery(SQL_OCEANHAWBINFO).setString(0,flightDate).setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
		
		return list;
	}
	
	
	public String getTrustFileCodeByHawb(String hawb)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		list=getCurrentSession().createSQLQuery(SQL_OCEANTRUSTFILECODEBYHAWB).setString(0,hawb).setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
		if (list.size()>0)
			return list.get(0).getTrustFileCode();
		
		return "";
	}

	
	public String getTrustFileCodeByMawb(String mawb)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		list=getCurrentSession().createSQLQuery(SQL_OCEANTRUSTFILECODEBYMAWB).setString(0,mawb).setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
		if (list.size()>0)
			return list.get(0).getTrustFileCode();
		
		return "";
	}	

}
