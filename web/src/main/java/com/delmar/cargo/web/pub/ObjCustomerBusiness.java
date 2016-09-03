package com.delmar.cargo.web.pub;

import com.delmar.cargo.model.CustomerBusiness;

import java.util.ArrayList;

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
