/******************************************************************************
 * 刘大磊  2013-07-01											  *
 *	作者：刘大磊								                              *
 * 电话：13336390671                                                        *
 * email:ldlqdsd@126.com						                          *
 *****************************************************************************/

package com.delmar.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.system.dao.UserFootmarkDao;
import com.delmar.system.model.UserFootmark;
import com.delmar.system.service.UserFootmarkService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
/**
 * @author 刘大磊 2016-09-02 10:18:25
 */
@Service("userFootmarkService")
public class UserFootmarkServiceImpl extends CoreServiceImpl<UserFootmark> implements
		UserFootmarkService {
	@Autowired
	private UserFootmarkDao userFootmarkDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<UserFootmark> getCoreDao() {
		return userFootmarkDao;
	}
	public void deleteUserFootmarkList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveUserFootmark(UserFootmark userFootmark) {
	Integer id=save(userFootmark);
	Date now=new Date();
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    return super.deleteByPrimaryKey(id);
    }

}
