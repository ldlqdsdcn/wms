package com.delmar.cargo.dao.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.dao.ReportFareInvoiceDao;
import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.core.dao.hibernate.CoreHibernateDaoImpl;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月19日 上午10:16:19 
 * 类说明 
 */
@Repository("reportFareInvoiceDao") 
public class ReportFareInvoiceDaoHibernate  extends CoreHibernateDaoImpl<ReportFareInvoice> implements ReportFareInvoiceDao {
	@Autowired
	private SessionFactory sessionFactory;

	private final String SQL_DEBITBALANCE="Select Sum(aa.Balance) balance From (Select a.InvoiceNo,a.Balance From ReportFareInvoice a "
			+" INNER JOIN CustomerInfo b ON a.CusCode=b.CusCode "
			+" where a.ReceDeal=1 and b.CusType='Customer' and ((a.FileCode=?) or (a.TrustCode=?))) aa ";
	
	/* (non-Javadoc)
	 * @see com.delmar.core.dao.mybatis.CoreHibernateDaoImpl#getModelClass()
	 */
	@Override
	public Class getModelClass() {
		return ReportFareInvoice.class;
	}

	
	public BigDecimal getDebitBalance(String trustFileCode)
	{
		BigDecimal balance=(BigDecimal)getCurrentSession().createSQLQuery(SQL_DEBITBALANCE).setString(0,trustFileCode).setString(1,trustFileCode).uniqueResult();
		return balance;
		
	}
	
}
