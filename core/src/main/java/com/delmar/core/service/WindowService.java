/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/package com.delmar.core.service;
import com.delmar.core.model.Window;
import com.delmar.core.service.CoreService;
import com.delmar.core.model.WindowTrl;
import java.util.List;
/**
 * @author 刘大磊 2016-09-10 14:38:00
 */
public interface WindowService extends CoreService<Window> {
	void deleteWindowList(Integer[] ids);
	List<WindowTrl> getWindowTrlListByWindowId(Integer windowId);
	Integer saveWindow(Window window,List<WindowTrl> windowTrlList);
}