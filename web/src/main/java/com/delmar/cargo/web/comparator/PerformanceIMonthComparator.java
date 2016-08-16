package com.delmar.cargo.web.comparator;

import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

import com.delmar.cargo.web.pub.ObjPerformance;
import com.delmar.crm.model.Customer;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月27日 下午4:36:12 
 * 类说明 
 */
public class PerformanceIMonthComparator implements Comparator {
	
	public int compare(Object element1, Object element2) {

		String lower1 =  ((ObjPerformance) element1).getCode()+((ObjPerformance) element1).getImonth()+((ObjPerformance) element1).getPlaneOcean()+((ObjPerformance) element1).getOutIn();
		String lower2 =  ((ObjPerformance) element2).getCode()+((ObjPerformance) element2).getImonth()+((ObjPerformance) element2).getPlaneOcean()+((ObjPerformance) element2).getOutIn();
	
		return lower1.compareTo(lower2);
	}
}
