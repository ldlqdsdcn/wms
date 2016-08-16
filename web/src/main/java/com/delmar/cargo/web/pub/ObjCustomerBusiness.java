package com.delmar.cargo.web.pub;

import java.util.ArrayList;

import com.delmar.cargo.model.CustomerBusiness;
import com.delmar.utils.ProDateUtil;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月28日 下午1:44:11 
 * 类说明 
 */
public class ObjCustomerBusiness extends CustomerBusiness {
	
	private ArrayList groupList;
	

	
	public ObjCustomerBusiness()
	{
		groupList=new ArrayList();
	}

	public ArrayList getGroupList() {
		return groupList;
	}

	public void setGroupList(ArrayList groupList) {
		this.groupList = groupList;
	}

	
	
	
	

}
