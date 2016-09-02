/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.TableColumnDao;
import com.delmar.core.model.TableColumn;
import com.delmar.core.service.TableColumnService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-09 10:48:25
 */
@Service("tableColumnService")
public class TableColumnServiceImpl extends CoreServiceImpl<TableColumn> implements
		TableColumnService {
	@Autowired
	private TableColumnDao tableColumnDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<TableColumn> getCoreDao() {
		return tableColumnDao;
	}

	
}
