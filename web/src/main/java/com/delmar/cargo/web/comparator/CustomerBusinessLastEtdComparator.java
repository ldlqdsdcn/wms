package com.delmar.cargo.web.comparator;

import java.util.Comparator;

import com.delmar.cargo.web.pub.ObjCustomerBusiness;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月28日 下午5:22:53 
 * 类说明 
 */
public class CustomerBusinessLastEtdComparator  implements Comparator {
	
	public int compare(Object element1, Object element2) {

		String lower1 =  ((ObjCustomerBusiness) element1).getLastEtd();
		String lower2 =  ((ObjCustomerBusiness) element2).getLastEtd();
	
		return lower2.compareTo(lower1);
	}
}
