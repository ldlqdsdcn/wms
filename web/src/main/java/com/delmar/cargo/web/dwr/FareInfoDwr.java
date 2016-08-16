package com.delmar.cargo.web.dwr;

import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.cargo.model.FareInfo;
import com.delmar.cargo.model.ReportFareInvoice;
import com.delmar.cargo.service.FareInfoService;
import com.delmar.core.model.CriteriaH;
import com.delmar.system.web.WebConst;
import com.delmar.system.web.model.PrivilegesDataFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * @author Charles Luo  luos@delmarchina.com
 * @version V2.0 2015年5月20日 下午5:17:58 
 * 类说明 
 */
@Repository("fareinfoDwr") 
public class FareInfoDwr {
	
		@Autowired
		private FareInfoService fareinfoService;
		
		public FareInfo[] getFareInfoList(String invoiceNo)
		{
			/*
			if(!DwrPrivilegeFilter.isView(this.getClass().getName()))
			{
				return null;
			}
			*/

			
			
			PrivilegesDataFilter up=(PrivilegesDataFilter)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute(WebConst.SESSION_USERPRIVILEGES);
			String accessString=up.getDWRAccessStringByStruts2();
			
			CriteriaH ch=new CriteriaH(FareInfo.class);
			ch.addField("invoiceNo", "=",invoiceNo);
			
			List<FareInfo> fareinfoList=fareinfoService.search(ch);
		
			FareInfo[] fareinfoArray=new FareInfo[fareinfoList.size()];
			fareinfoList.toArray(fareinfoArray);
			return fareinfoArray;
		}
		
		public String getFareInfoGson(String invoiceNo)
		{
			FareInfo[] fareinfoArray=getFareInfoList(invoiceNo);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();  
			return gson.toJson(fareinfoArray);
		}
		
		
	   	
	}