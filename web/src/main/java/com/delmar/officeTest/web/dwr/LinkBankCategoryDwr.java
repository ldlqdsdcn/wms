/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.officeTest.web.dwr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.core.web.util.FacesUtils;
import com.delmar.officeTest.model.OfficeTestBankCategory;
import com.delmar.officeTest.service.OfficeTestBankCategoryService;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.service.WFDetailService;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年3月12日 下午3:50:09
 */
@Repository("linkBankCategoryDwr") 
public class LinkBankCategoryDwr {
	
	@Autowired
	private OfficeTestBankCategoryService officeTestBankCategoryService;
	
	public OfficeTestBankCategory[] getLinkBankCategoryList (String name,Integer id){
		
		if(!DwrPrivilegeFilter.isView(this.getClass().getName())){
			return null;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer();
		
	    if (id != null){
	    	sb.append(" and testBank_id = ").append(id).append("");
	    }
	    
		String accessString=" 1=1 " + " " + sb.toString();
		param.put("accessString", accessString);
		
		List<OfficeTestBankCategory> detailList = officeTestBankCategoryService.selectByExample(param);
		OfficeTestBankCategory[] detailArray = new OfficeTestBankCategory[detailList.size()];
		detailList.toArray(detailArray);
		return detailArray;
	}
	
	public String getLinkBankCategoryGson(Integer id){
		
		OfficeTestBankCategory[] linkStationRecordArray = getLinkBankCategoryList("",id);
		return (new Gson()).toJson(linkStationRecordArray);
	}
}
