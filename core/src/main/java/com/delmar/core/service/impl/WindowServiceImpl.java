/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
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
import java.util.Date;
import com.delmar.core.model.WindowTrl;
import com.delmar.core.dao.WindowTrlDao;
/**
 * @author 刘大磊 2016-09-12 15:10:29
 */
@Service("windowService")
public class WindowServiceImpl extends CoreServiceImpl<Window> implements
		WindowService {
	@Autowired
	private WindowDao windowDao;
    @Autowired
    private WindowTrlDao windowTrlDao;
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


public Integer saveWindow(Window window,List<WindowTrl> windowTrlList) {
	Integer id=save(window);
	Date now=new Date();
		for(WindowTrl windowTrl: windowTrlList)
		{
			windowTrl.setWindowId(id);
			windowTrlDao.save(windowTrl);
		}
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
    Map<String,Object> windowTrlParam=new HashMap<String,Object>();
windowTrlParam.put("windowId",id);
windowTrlDao.deleteByExample(windowTrlParam);
    return super.deleteByPrimaryKey(id);
    }

	public List<WindowTrl> getWindowTrlListByWindowId(Integer windowId)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("windowId",windowId);
		if(windowId==null)
		{
			return new java.util.ArrayList<WindowTrl>();
		}
		return windowTrlDao.selectByExample(param);
	}
}
