/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package com.delmar.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delmar.core.dao.WindowDao;
import com.delmar.core.model.Window;
import com.delmar.core.service.WindowService;
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * @author 刘大磊 2016-08-27 16:44:29
 */
@Service("windowService")
public class WindowServiceImpl extends CoreServiceImpl<Window> implements
		WindowService {
	@Autowired
	private WindowDao windowDao;
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<Window> getCoreDao() {
		return windowDao;
	}
	public void deleteWindowList(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer saveWindow(Window window) {
	Integer id=save(window);
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    return super.deleteByPrimaryKey(id);
    }

}
