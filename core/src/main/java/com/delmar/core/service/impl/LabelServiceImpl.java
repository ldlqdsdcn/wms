/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.LabelDao;
import com.delmar.core.model.Label;
import com.delmar.core.service.LabelService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;

/**
 * @author 刘大磊 22016-08-26 23:24:10
 */
@Service("labelService")
public class LabelServiceImpl extends CoreServiceImpl<Label> implements
		LabelService {
	@Autowired
	private LabelDao labelDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	@Override
	protected CoreDao<Label> getCoreDao() {
		return labelDao;
	}
	public void deleteLabelList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			labelDao.deleteByPrimaryKey(id);
		}
	}
	
}
