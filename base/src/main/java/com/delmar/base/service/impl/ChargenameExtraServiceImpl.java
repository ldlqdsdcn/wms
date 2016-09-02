/******************************************************************************
 * 刘大磊  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：13336390671                                                          * 
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.base.dao.ChargenameExtraDao;
import com.delmar.base.model.ChargenameExtra;
import com.delmar.base.service.ChargenameExtraService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22015-03-24 11:04:26
 */
@Service("chargenameExtraService")
public class ChargenameExtraServiceImpl extends CoreServiceImpl<ChargenameExtra> implements
		ChargenameExtraService {
	@Autowired
	private ChargenameExtraDao chargenameExtraDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<ChargenameExtra> getCoreDao() {
		return chargenameExtraDao;
	}
	public void deleteChargenameExtraList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			chargenameExtraDao.deleteByPrimaryKey(id);
		}
	}
	
}
