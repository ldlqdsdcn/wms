package com.delmar.cargo.web.comparator;

import java.math.BigDecimal;
import java.util.Comparator;

import com.delmar.cargo.model.BusinessClient;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年6月29日 下午5:55:35 
 * 类说明 
 */
public class BusinessClientVolumeComparator   implements Comparator {
	public int compare(Object element1, Object element2) {

		BigDecimal lower1 =  ((BusinessClient) element1).getCargoVolume();
		BigDecimal lower2 =  ((BusinessClient) element2).getCargoVolume();
	
		return lower2.compareTo(lower1);
	}
}
