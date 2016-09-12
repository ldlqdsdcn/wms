/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.core.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.delmar.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.web.action.CoreEditPrivAction;
import com.delmar.core.model.Language;
import com.delmar.core.service.LanguageService;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.model.Window;
import com.delmar.core.service.WindowService;
import java.util.Date;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.core.model.WindowTrl;
/**
 * @author 刘大磊 2016-09-12 15:10:29
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "window.name", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "window.isactive", message = "不允许为空") },requiredFields = {@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "window.typeId",message = "不允许为空")})
public class WindowAction extends CoreEditPrivAction {
	private Window  window;
	private List<WindowTrl> windowTrlList=new ArrayList<WindowTrl>();;
@Autowired
private LanguageService languageService;
	@Autowired
	private WindowService windowService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "window";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		windowService.deleteByPrimaryKey(window.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		windowService.deleteWindowList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return window.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
public void editForm() {
window= windowService.selectByPrimaryKey(id);

		windowTrlList=windowService.getWindowTrlListByWindowId(id);
            List<Language> list=languageService.selectByExample(null);
            List<Language> noList=new ArrayList<Language>();
                for(Language lang:list)
                {
					boolean has=false;
					for(WindowTrl trl:windowTrlList)
					{
						if(trl.getLanguage().equals(lang.getCode()))
						{
							has=true;
							break;
						}
					}
					if(!has)
					{
					noList.add(lang);
					}
                }
                for(Language lang:noList)
                {
					WindowTrl trl=new WindowTrl();
					trl.setLanguage(lang.getCode());
					trl.setWindowId(id);
					windowTrlList.add(trl);
                }
}
/* (non-Javadoc)
* @see com.delmar.core.web.action.CoreEditPrivAction#search()
*/
@Override
public List search() {
Map<String,Object> param=new HashMap();
param.put("searchString",getSearchWhere());
return windowService.selectByExample(param);
}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
public void createForm() {
window=new Window();
	windowTrlList=new ArrayList<WindowTrl>();
        List
        <Language> languageList=languageService.selectByExample(null);
            for(Language lang:languageList)
            {
		WindowTrl trl=new WindowTrl();
            trl.setLanguage(lang.getCode());
		windowTrlList.add(trl);
            }
    }
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
Date date=new Date();
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
if(window.isnew())
{
window.setCreated(date);
window.setCreatedby(currentUserId);

}
window.setUpdated(date);
window.setUpdatedby(currentUserId);
		windowService.saveWindow(window,windowTrlList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Window getWindow() {
		return window;
	}

	/**
	 * @param window the window to set
	 */
	public void setWindow(Window window) {
		this.window = window;
	}
public List<WindowTrl> getWindowTrlList()
{
	return windowTrlList;
}
public void setWindowTrlList(List<WindowTrl> windowTrlList)
{
	this.windowTrlList=windowTrlList;
}
}
