package com.delmar.crm.web.comparator;

import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import com.delmar.crm.model.Customer;

public class CustomerLastLinkTimeComparator implements Comparator {
	
	public int compare(Object element1, Object element2) {

		Date lower1 = ((Customer) element1).getCustomerExtra().getLastLinkTime();
		if (lower1==null)
			lower1=(new GregorianCalendar(1900,1,1)).getTime();
		Date lower2 = ((Customer) element2).getCustomerExtra().getLastLinkTime();
		if (lower2==null)
			lower2=(new GregorianCalendar(1900,1,1)).getTime();
		
		return lower2.compareTo(lower1);
	}
}
