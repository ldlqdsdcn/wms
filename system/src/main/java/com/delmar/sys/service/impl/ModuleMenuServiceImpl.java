/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.ModuleMenuDao;
import com.delmar.sys.model.ModuleMenu;
import com.delmar.sys.service.ModuleMenuService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("moduleMenuService")
public class ModuleMenuServiceImpl extends CoreServiceImpl<ModuleMenu> implements
		ModuleMenuService {
	@Autowired
	private ModuleMenuDao moduleMenuDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<ModuleMenu> getCoreDao() {
		return moduleMenuDao;
	}

	
}
