/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：0532-66701118                                                               * 
 * email:liua@delmarchina.com						                              *
 *****************************************************************************/
package com.delmar.station.web.dwr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.delmar.core.web.dwr.DwrPrivilegeFilter;
import com.delmar.station.model.ObjWFDetail;
import com.delmar.station.service.WFDetailService;
import com.delmar.utils.StringUtil;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年3月12日 下午3:50:09
 */
@Repository("linkStationRecordDwr") 
public class LinkStationRecordDwr {
	@Autowired
	private WFDetailService wfDetailService;
	
	public ObjWFDetail[] getLinkStationRecordList (String name,Integer id){
		
		if(!DwrPrivilegeFilter.isView(this.getClass().getName())){
			return null;
		}
		List<ObjWFDetail> detailList = wfDetailService.viewRecordByMasterId(id);
		ObjWFDetail[] detailArray = new ObjWFDetail[detailList.size()];
		detailList.toArray(detailArray);
		return detailArray;
	}
	
	public String getLinkStationRecordGson(Integer id){
		
		ObjWFDetail[] linkStationRecordArray = getLinkStationRecordList("",id);
		return (new Gson()).toJson(linkStationRecordArray);
	}
}
