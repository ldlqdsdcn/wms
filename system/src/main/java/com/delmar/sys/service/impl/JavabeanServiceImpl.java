/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.sys.dao.JavabeanDao;
import com.delmar.sys.model.Javabean;
import com.delmar.sys.service.JavabeanService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-01-13 09:38:52
 */
@Service("javabeanService")
public class JavabeanServiceImpl extends CoreServiceImpl<Javabean> implements
		JavabeanService {
	@Autowired
	private JavabeanDao javabeanDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Javabean> getCoreDao() {
		return javabeanDao;
	}

	
}
