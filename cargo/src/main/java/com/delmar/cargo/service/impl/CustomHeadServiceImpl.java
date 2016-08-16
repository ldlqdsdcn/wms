package com.delmar.cargo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.cargo.dao.CustomHeadDao;
import com.delmar.cargo.dao.CustomListDao;
import com.delmar.cargo.model.CustomHead;
import com.delmar.cargo.model.CustomList;
import com.delmar.cargo.service.CustomHeadService;

@Service("CustomHeadService")
public class CustomHeadServiceImpl implements CustomHeadService{
	
	@Autowired
	private CustomHeadDao customHeadDao;
	
	@Autowired
	private CustomListDao customListDao;

	public List<CustomHead> getCustomHeadByMap(Map<String, Object> map) {
		
		if (map != null) {
			
			List<CustomHead> returnList = null;
			List<CustomHead> headList = customHeadDao.getCustomHeadByMap(map);
			if (headList != null && headList.size() > 0) {
				returnList = new ArrayList<CustomHead>();
				for (CustomHead head : headList) {
					
					List<CustomList> customList = customListDao.getCustomListByPreEntryId(head.getPre_entry_id());
					head.setCustomList(customList);
					returnList.add(head);
				}
			}
			
			return returnList;
		}
		
		return null;
	}

	public String getBusinessBillCode(Map<String, Object> map) {
		return null;
	}

	public boolean checkBusinessBalance(String trustFileCode) {
		return false;
	}

}
