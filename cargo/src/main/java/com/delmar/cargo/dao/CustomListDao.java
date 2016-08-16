package com.delmar.cargo.dao;

import java.util.List;
import java.util.Map;

import com.delmar.cargo.model.CustomHead;
import com.delmar.cargo.model.CustomList;
import com.delmar.core.dao.CoreHibernateDao;

public interface CustomListDao extends CoreHibernateDao<CustomList>{

	public List<CustomList> getCustomListByPreEntryId(String pre_entry_id);

}
