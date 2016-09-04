/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.LabelTrlDao;
import com.delmar.core.model.LabelTrl;
import com.delmar.core.service.LabelTrlService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
/**
 * @author 刘大磊 2016-09-03 23:33:53
 */
@Service("labelTrlService")
public class LabelTrlServiceImpl extends CoreServiceImpl<LabelTrl> implements
		LabelTrlService {
	@Autowired
	private LabelTrlDao labelTrlDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<LabelTrl> getCoreDao() {
		return labelTrlDao;
	}
	public void deleteLabelTrlList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveLabelTrl(LabelTrl labelTrl) {
	Integer id=save(labelTrl);
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    return super.deleteByPrimaryKey(id);
    }

}
