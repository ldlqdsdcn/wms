package com.delmar.cargo.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.BusinessDao;
import com.delmar.cargo.model.BusinessForwarder;
import com.delmar.cargo.model.BusinessInvoice;
import com.delmar.cargo.model.BussinessMan;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;
import com.delmar.core.model.HbnHsql;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月25日 下午3:09:16 
 * 类说明 
 */
@Repository("businessDaoHibernate") 
public class BusinessDaoHibernate  extends CoreHibernateDaoImpl<BusinessForwarder> implements BusinessDao {
	public BusinessDaoHibernate()
	{
		
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	private static final String SQL_OCEANHAWBINFO = "Select 'Ocean' planeOcean,A.TrustCode trustFileCode,a.companyId,"
			+"a.FareFinishDate,a.ShMarkDate,"
			+" a.CwConfirmDate,a.Hawb,"
			+"dbo.IsEmptyNull(b.MainCode,a.MainCode) MawbCode,dbo.IsEmptyNull(b.FileNo,a.FileNo) FileNo,a.MainCode,a.BeZhiDan,"
			+"a.BeSpelling,a.BeOweSpell,a.OutIn,"
			+"a.TrustType,a.ShipCompany CarrierCode,c.PlaneCompanyCName CarrierName,"
			+" a.AirPortFrom,a.AirPortFromDesc,a.AirPortTo,a.AirPortToDesc,a.ArrivePort,"
			+" a.ArrivePortDesc,a.DestPlace,a.FlightDate,a.ShipName,a.Flight,a.ArrivePortDate,"
			+" g.CountryCode AirPortFromCountry,gg.CountryCode AirPortToCountry,ggg.CountryCode ArrivePortCountry,"
			+ " a.ShipperCode,a.ShipperName,a.ConsignCode,a.ConsignName, a.Shipper,a.Consignee,"
			+ " a.TrustSource,f.CusCName as TrustSourceName,a.ColoadCompany,d.CusCName as ColoadCompanyName, "
			+" a.GoodsNumber,a.GoodsWeight,a.GoodsSize,a.GoodsChargeWeight,a.TeuNum,a.GoodsDesc,a.ContainerType,a.ContainerLable,"
			+" a.Bussiness business,e.Name BusinessName,a.csCode,ee.Name as CsCodeName,ee.EMail as CsCodeEMail,a.DocOpCode,eee.Name as DocOpName,"
			+" eee.EMail as DocOpEMail,a.Debit,a.Credit,a.Profit, "
			+" a.ExecDate operateDate,a.Operator ,a.OperatorName "
			+ " From  TrustContext a"
			+ " LEFT OUTER JOIN TrustContext b ON A.MainCode=b.TrustCode and a.CompanyID=b.CompanyID"
			+ " LEFT OUTER JOIN PlaneCompanyInfo c ON A.ShipCompany=c.PlaneCompanyCode"
			+ " LEFT OUTER JOIN CustomerInfo d ON A.COLoadCompany=d.CusCode"
			+ " LEFT OUTER JOIN BussinessMan e ON A.Bussiness=e.Code"
			+ " LEFT OUTER JOIN BussinessMan ee ON A.CSCode=ee.Code"
			+ " LEFT OUTER JOIN BussinessMan eee ON A.DocOpCode=eee.Code"			
			+ " LEFT OUTER JOIN CustomerInfo f ON A.TrustSource=f.CusCode"
			+ " LEFT OUTER JOIN TrustFileInfo ff ON a.TrustCode=ff.TrustFileCode  and a.CompanyID=ff.CompanyID"
			+ " LEFT OUTER JOIN TrustFileAddition fff ON a.TrustCode=fff.TrustFileCode  and a.CompanyID=fff.CompanyID "
			+ " LEFT OUTER JOIN PortInfo g ON A.AirPortFrom=g.PortCode "
			+ " LEFT OUTER JOIN PortInfo gg ON A.AirPortTo=gg.PortCode "
			+ " LEFT OUTER JOIN PortInfo ggg ON A.ArrivePort=ggg.PortCode "			
			+ " Where a.TrustCode<>'' ";

	/** 查询空运分单信息 */
	private static final String SQL_AIRHAWBINFO = "Select 'Air' planeOcean,A.FileCode trustFileCode,a.companyId,a.FareFinishDate,a.ShMarkDate,"
			+" a.CwConfirmDate,a.Hawb,"
			+"dbo.IsEmptyNull(b.MainCode,a.MainCode) MawbCode,dbo.IsEmptyNull(b.FileNo,a.FileNo) FileNo,a.MainCode,a.BeZhiDan,"
			+"0 BeSpelling,0 BeOweSpell,a.OutIn,"			
			+"a.TrustType,a.PlaneCompany CarrierCode,c.PlaneCompanyCName CarrierName,"
			+" a.AirPortFrom,a.AirPortFromDesc,a.AirPortTo,a.AirPortToDesc,a.ArrivePort,"
			+" a.ArrivePortDesc,a.DestPlace,a.FlightDate,'' ShipName,a.Flight,a.ArrivePortDate,"
			+" g.CountryCode AirPortFromCountry,gg.CountryCode AirPortToCountry,ggg.CountryCode ArrivePortCountry,"			
			+ " a.ShipperCode,a.ShipperName,a.ConsignCode,a.ConsignName, a.Shipper,a.Consignee,"
			+ " a.TrustSource,f.CusCName as TrustSourceName,a.ColoadCompany,d.CusCName as ColoadCompanyName, "
			+" a.GoodsNumber,a.GoodsWeight,a.GoodsSize,a.GoodsChargeWeight,0.0 TeuNum,a.GoodsDesc,'' ContainerType,'' ContainerLable,"
			+" a.Bussiness business,e.Name BusinessName,a.csCode,ee.Name as CsCodeName,ee.EMail as CsCodeEMail,a.DocOpCode,eee.Name as DocOpName,"
			+" eee.EMail as DocOpEMail,a.Debit,a.Credit,a.Profit, "
			+" a.ExecDate operateDate,a.Operator ,a.OperatorName "			
			+ " From  FileTable a"
			+ " LEFT OUTER JOIN FileTable b ON A.MainCode=b.FileCode and a.CompanyID=b.CompanyID"
			+ " LEFT OUTER JOIN PlaneCompanyInfo c ON A.PlaneCompany=c.PlaneCompanyCode"
			+ " LEFT OUTER JOIN CustomerInfo d ON A.COLoadCompany=d.CusCode"
			+ " LEFT OUTER JOIN BussinessMan e ON A.Bussiness=e.Code"
			+ " LEFT OUTER JOIN BussinessMan ee ON A.CSCode=ee.Code"
			+ " LEFT OUTER JOIN BussinessMan eee ON A.DocOpCode=eee.Code"			
			+ " LEFT OUTER JOIN CustomerInfo f ON A.TrustSource=f.CusCode"
			+ " LEFT OUTER JOIN TrustFileInfo ff ON a.FileCode=ff.TrustFileCode  and a.CompanyID=ff.CompanyID"
			+ " LEFT OUTER JOIN TrustFileAddition fff ON a.FileCode=fff.TrustFileCode  and a.CompanyID=fff.CompanyID "
			+ " LEFT OUTER JOIN PortInfo g ON A.AirPortFrom=g.PortCode "
			+ " LEFT OUTER JOIN PortInfo gg ON A.AirPortTo=gg.PortCode "
			+ " LEFT OUTER JOIN PortInfo ggg ON A.ArrivePort=ggg.PortCode "					
			+ " Where a.FileCode<>''   ";
	
	
	/** 查询其他业务信息 */
	private static final String SQL_OTHERHAWBINFO = "Select 'Other' planeOcean,A.FileCode trustFileCode,a.companyId,a.FareFinishDate,a.ShMarkDate,"
			+" a.CwConfirmDate,a.Hawb,"
			+"a.MainCode MawbCode,a.FileNo,a.MainCode,1 BeZhiDan,"
			+"0 BeSpelling,0 BeOweSpell,a.OutIn,"			
			+"a.TrustType,a.PlaneCompany CarrierCode,c.PlaneCompanyCName CarrierName,"
			+" a.AirPortFrom,a.AirPortFromDesc,a.AirPortTo,a.AirPortToDesc,'' ArrivePort,"
			+" '' ArrivePortDesc,a.DestPlace,a.FlightDate,a.ShipName,a.Flight,'' ArrivePortDate,"
			+" g.CountryCode AirPortFromCountry,gg.CountryCode AirPortToCountry,'' ArrivePortCountry,"			
			+ " a.ShipperCode,a.ShipperName,a.ConsignCode,a.ConsignName, '' Shipper,'' Consignee,"
			+ " a.TrustSource,f.CusCName as TrustSourceName,a.ColoadCompany,d.CusCName as ColoadCompanyName, "
			+" a.GoodsNumber,a.GoodsWeight,a.GoodsSize,a.GoodsChargeWeight,0.0 TeuNum,a.GoodsDesc,'' ContainerType,'' ContainerLable,"
			+" a.Bussiness business,e.Name BusinessName,'' csCode,'' CsCodeName,'' CsCodeEMail,'' DocOpCode,''  DocOpName,"
			+" '' DocOpEMail,a.Debit,a.Credit,a.Profit, "
			+" a.ExecDate operateDate,a.Operator ,a.OperatorName "			
			+ " From  OtherFileTable a"
			+ " LEFT OUTER JOIN PlaneCompanyInfo c ON A.PlaneCompany=c.PlaneCompanyCode"
			+ " LEFT OUTER JOIN CustomerInfo d ON A.COLoadCompany=d.CusCode"
			+ " LEFT OUTER JOIN BussinessMan e ON A.Bussiness=e.Code"
			+ " LEFT OUTER JOIN CustomerInfo f ON A.TrustSource=f.CusCode"
			+ " LEFT OUTER JOIN PortInfo g ON A.AirPortFrom=g.PortCode "
			+ " LEFT OUTER JOIN PortInfo gg ON A.AirPortTo=gg.PortCode "
			+ " Where a.FileCode<>''  ";	
	
	
	/** 查询数据账单sql语句 */

	
	private final static String SQL_SEL_OCEANINVOICE = "Select 'Ocean' PlaneOcean,"
			+ "e.Rate as CurrencyRate "
			+ ",b.OutIn,dbo.IsEmptyNull(c.FileNo,b.FileNo) FileNo,dbo.IsEmptyNull(c.mainCode,b.mainCode) mawbCode,"
			+ "b.Hawb,b.TrustType,b.ShipperCode,b.ShipperName,"
			+ "b.ConsignCode,b.ConsignName,b.TrustSource,"
			+ "c.ShipperCode as MawbShipperCode,c.ConsignCode as MawbConsignCode,"
			+ "b.ShipCompany as CarrierCode,b.AirPortFrom,"
			+ "b.AirPortTo,b.DestPlace,"
			+ "b.ShipName,b.Flight,b.FlightDate,"
			+ "b.GoodsDesc,b.GoodsNumber,b.GoodsWeight,b.GoodsChargeWeight,b.GoodsSize,"
			+ "b.TeuNum,b.ContainerType,b.ContainerLable,b.Profit,b.Bussiness business,q.Name as BusinessName,b.CwConfirmDate, "
			+" a.companyId,a.InvoiceNo,a.ReceDeal,a.CusCode,a.CusCodeName,"
			+" a.currencyType,a.TransDebit,a.TransCredit,a.TransProfit,a.CwFinishDate,a.ConfirmDate,"
			+" a.BillDate,a.BillNo,a.ReceiveDate,a.cwAcount,a.Balance,f.BalanceType,a.OperatorName,a.OperateDate "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN TrustContext b ON a.TrustCode=b.TrustCode "
			+ " LEFT OUTER JOIN TrustContext c ON B.MainCode=c.TrustCode "
			+ " LEFT OUTER JOIN CustomerInfo d ON a.cusCode=d.CusCode "					
			+ " LEFT OUTER JOIN BussinessMan q ON b.Bussiness=q.Code "
			+ " LEFT OUTER JOIN CurrencyHis e ON a.CurrencyType=e.Code and "
			+ " (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End)>=e.StartDate "
			+ " and (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End) <=e.EndDate "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.TrustCode,'')<>'' and f.CusType='Customer'  ";

	private final static String SQL_SEL_AIRINVOICE = "Select 'Air' PlaneOcean,"
			+ "e.Rate as CurrencyRate "
			+ ",b.OutIn,dbo.IsEmptyNull(c.FileNo,b.FileNo) FileNo,dbo.IsEmptyNull(c.mainCode,b.mainCode) mawbCode,"
			+ "b.Hawb,b.TrustType,b.ShipperCode,b.ShipperName,"
			+ "b.ConsignCode,b.ConsignName,b.TrustSource,"
			+ "c.ShipperCode as MawbShipperCode,c.ConsignCode as MawbConsignCode,"
			+ "b.PlaneCompany as CarrierCode,b.AirPortFrom,"
			+ "b.AirPortTo,b.DestPlace,"
			+ "'' ShipName,b.Flight,b.FlightDate,"
			+ "b.GoodsDesc,b.GoodsNumber,b.GoodsWeight,b.GoodsChargeWeight,b.GoodsSize,"
			+ "0.0 TeuNum,'' ContainerType,'' ContainerLable,b.Profit,b.Bussiness business,q.Name as BusinessName,b.CwConfirmDate, "
			+" a.companyId,a.InvoiceNo,a.ReceDeal,a.CusCode,a.CusCodeName,"
			+" a.currencyType,a.TransDebit,a.TransCredit,a.TransProfit,a.CwFinishDate,a.ConfirmDate,"
			+" a.BillDate,a.BillNo,a.ReceiveDate,a.cwAcount,a.Balance,f.BalanceType,a.OperatorName,a.OperateDate "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN FileTable b ON a.FileCode=b.FileCode "
			+ " LEFT OUTER JOIN FileTable c ON B.MainCode=c.FileCode "
			+ " LEFT OUTER JOIN CustomerInfo d ON a.cusCode=d.CusCode "					
			+ " LEFT OUTER JOIN BussinessMan q ON b.Bussiness=q.Code "
			+ " LEFT OUTER JOIN CurrencyHis e ON a.CurrencyType=e.Code and "
			+ " (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End)>=e.StartDate "
			+ " and (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End) <=e.EndDate "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.FileCode,'')<>''  and f.CusType='Customer'   ";

	private final static String SQL_SEL_OTHERINVOICE =  "Select 'Other' PlaneOcean,"
			+ "e.Rate as CurrencyRate "
			+ ",b.OutIn,b.FileNo,b.mainCode mawbCode,"
			+ "b.Hawb,b.TrustType,b.ShipperCode,b.ShipperName,"
			+ "b.ConsignCode,b.ConsignName,b.TrustSource,"
			+ " ''  MawbShipperCode,'' MawbConsignCode,"
			+ "b.PlaneCompany as CarrierCode,b.AirPortFrom,"
			+ "b.AirPortTo,b.DestPlace,"
			+ "b.ShipName,b.Flight,b.FlightDate,"
			+ "b.GoodsDesc,b.GoodsNumber,b.GoodsWeight,b.GoodsChargeWeight,b.GoodsSize,"
			+ "0.0 TeuNum,'' ContainerType,'' ContainerLable,b.Profit,b.Bussiness business,q.Name as BusinessName,b.CwConfirmDate, "
			+" a.companyId,a.InvoiceNo,a.ReceDeal,a.CusCode,a.CusCodeName,"
			+" a.currencyType,a.TransDebit,a.TransCredit,a.TransProfit,a.CwFinishDate,a.ConfirmDate,"
			+" a.BillDate,a.BillNo,a.ReceiveDate,a.cwAcount,a.Balance,f.BalanceType,a.OperatorName,a.OperateDate "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN OtherFileTable b ON a.FileCode=b.FileCode "
			+ " LEFT OUTER JOIN CustomerInfo d ON a.cusCode=d.CusCode "					
			+ " LEFT OUTER JOIN BussinessMan q ON b.Bussiness=q.Code "
			+ " LEFT OUTER JOIN CurrencyHis e ON a.CurrencyType=e.Code and "
			+ " (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End)>=e.StartDate "
			+ " and (Case When IsNull(a.ConfirmDate,'')='' THEN Convert(Varchar(20),GetDate(),120) ELSE a.ConfirmDate End) <=e.EndDate "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.FileCode,'')<>''  and f.CusType='Customer'   ";
	
	

	
	private final static String SQL_SEL_INVOICESTATE ="Select aa.CusCode,bb.CusCName cusCodeName,aa.CurrencyType,sum(aa.Balance) Balance from ("
			+"Select a.CusCode,a.CurrencyType,a.Balance "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN TrustContext b ON a.TrustCode=b.TrustCode "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.TrustCode,'')<>'' and f.CusType='Customer'  and a.Recedeal=1 and a.Balance<>0  "
			+" and b.Bussiness=? and b.FlightDate>=? and b.FlightDate<=?  "
			+" union all "
			+ "Select a.CusCode,a.CurrencyType,a.Balance "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN FileTable b ON a.FileCode=b.FileCode "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.FileCode,'')<>'' and f.CusType='Customer'  and a.Recedeal=1 and a.Balance<>0  "
			+" and b.Bussiness=?  and b.FlightDate>=? and b.FlightDate<=? "
			+" union all "
			+ "Select a.CusCode,a.CurrencyType,a.Balance "
			+ " From ReportFareInvoice a "
			+ " INNER JOIN OtherFileTable b ON a.FileCode=b.FileCode "
			+" LEFT OUTER JOIN CustomerInfo f ON A.CusCode=f.CusCode "			
			+ " where IsNull(a.FileCode,'')<>'' and f.CusType='Customer'  and a.Recedeal=1 and a.Balance<>0  "
			+" and b.Bussiness=?  and b.FlightDate>=? and b.FlightDate<=? "
			+") aa "
			+" LEFT OUTER JOIN CustomerInfo bb ON aa.CusCode=bb.CusCode "
			+" group by aa.CusCode,bb.CusCName,aa.CurrencyType";
	
	
	
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return BusinessForwarder.class;
	}
	
	
	public List<BusinessForwarder> searchBusinessList(HbnHsql hbnhsql,String planeOcean)
	{
		List<BusinessForwarder> list=new ArrayList<BusinessForwarder>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
		if (planeOcean.equals("Ocean"))
		   sqlBuf.append(SQL_OCEANHAWBINFO);
		else if (planeOcean.equals("Air"))
    	   sqlBuf.append(SQL_AIRHAWBINFO);
		else if (planeOcean.equals("Other"))
		   sqlBuf.append(SQL_OTHERHAWBINFO);			
		
		sqlBuf.append(hbnhsql.getSqlWhere());		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessForwarder.class)).list();
	
		return list;		
	}
	
	
	public List<BusinessInvoice> searchBusinessInvoiceList(HbnHsql hbnhsql,String planeOcean)
	{
		List<BusinessInvoice> list=new ArrayList<BusinessInvoice>(); 
		
		StringBuffer sqlBuf=new StringBuffer();
		if (planeOcean.equals("Ocean"))
		   sqlBuf.append(SQL_SEL_OCEANINVOICE);
		else if (planeOcean.equals("Air"))
    	   sqlBuf.append(SQL_SEL_AIRINVOICE);
		else if (planeOcean.equals("Other"))
		   sqlBuf.append(SQL_SEL_OTHERINVOICE);			
		
		sqlBuf.append(hbnhsql.getSqlWhere());		
		SQLQuery query=getCurrentSession().createSQLQuery(sqlBuf.toString());
		hbnhsql.setQueryValue(query);
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessInvoice.class)).list();
	
		return list;			
	}
		
	
	
	 /**
	 * <p>Title: searchBusinessInvoiceState</p>
	 * <p>查询应收账款 </p>
	 * @param salesCode
	 * @param flightDateBegin
	 * @param flightDateEnd
	 * @return
	 * @see com.delmar.cargo.dao.BusinessDao#searchBusinessInvoiceState(String, String, String)
	 */
	public List<BusinessInvoice> searchBusinessInvoiceState(String salesCode,String flightDateBegin,String flightDateEnd)
	{
		List<BusinessInvoice> list=new ArrayList<BusinessInvoice>(); 
		
		
		SQLQuery query=getCurrentSession().createSQLQuery(SQL_SEL_INVOICESTATE);
		query.setString(0, salesCode);
		query.setString(1, flightDateBegin);
		query.setString(2, flightDateEnd);
		query.setString(3, salesCode);
		query.setString(4, flightDateBegin);
		query.setString(5, flightDateEnd);
		query.setString(6, salesCode);
		query.setString(7, flightDateBegin);
		query.setString(8, flightDateEnd);		
		list=query.setResultTransformer(Transformers.aliasToBean(BusinessInvoice.class)).list();
	
		return list;			
	}
			
	
	
	
	
	
	

}
