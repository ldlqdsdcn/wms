package com.delmar.cargo.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.cargo.model.CustomHead;
import com.delmar.cargo.service.BusinessQueryService;
import com.delmar.cargo.service.CustomHeadService;
import com.delmar.core.model.HbnHsql;
import com.delmar.core.web.action.CoreAction;
import com.delmar.station.model.ObjWarehouseForwarder;
import com.delmar.utils.StringUtil;

public class CustomAction extends CoreAction{
	
	private String customsHblNoHid; //分单号、运单号、提单号
	private String customsTradeNameHid;// 经营单位
	private String customsBillNoHid;// 关单号 、主单号
	private List<CustomHead> headList;
	private boolean beSettled;
	private Integer headListSize;
	
	@Autowired
	private CustomHeadService customHeadService;
	
	@Autowired
	private BusinessQueryService businessQueryService;

	public String select () {
		
		 beSettled = false;
		 headList = null;
		 headListSize = null;
		//若想要查询，那么经营单位是必须的，提单号、主单号中选一个或多个
		if ((StringUtils.isNotEmpty(customsTradeNameHid) && StringUtils.isNotEmpty(customsHblNoHid))
				|| (StringUtils.isNotEmpty(customsTradeNameHid) && StringUtils.isNotEmpty(customsBillNoHid))) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customsTradeNameHid", customsTradeNameHid);
			map.put("customsHblNoHid", customsHblNoHid);
			map.put("customsBillNoHid", customsBillNoHid);
			
			headList = customHeadService.getCustomHeadByMap(map);
			if (headList != null) {
				headListSize = headList.size();
			} else {
				headListSize = 0;
			}
			
			//首先根据是否能够找到相应的核销单对象，这里只能够按照分单号或者关单号去查询。
			if (headList != null && headList.size() == 1) {
        		beSettled = businessQueryService.getDebitStatus(customsHblNoHid, customsBillNoHid);
			}
		}
		
		return "searchCustom";
	}

	public String getCustomsHblNoHid() {
		return customsHblNoHid;
	}

	public void setCustomsHblNoHid(String customsHblNoHid) {
		this.customsHblNoHid = customsHblNoHid;
	}

	public String getCustomsTradeNameHid() {
		return customsTradeNameHid;
	}

	public void setCustomsTradeNameHid(String customsTradeNameHid) {
		this.customsTradeNameHid = customsTradeNameHid;
	}

	public String getCustomsBillNoHid() {
		return customsBillNoHid;
	}

	public void setCustomsBillNoHid(String customsBillNoHid) {
		this.customsBillNoHid = customsBillNoHid;
	}

	public List<CustomHead> getHeadList() {
		return headList;
	}

	public void setHeadList(List<CustomHead> headList) {
		this.headList = headList;
	}

	public boolean isBeSettled() {
		return beSettled;
	}

	public void setBeSettled(boolean beSettled) {
		this.beSettled = beSettled;
	}

	public int getHeadListSize() {
		return headListSize;
	}

	public void setHeadListSize(int headListSize) {
		this.headListSize = headListSize;
	}
	
	
}
