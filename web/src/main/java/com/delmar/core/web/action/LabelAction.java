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
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
import org.springframework.beans.factory.annotation.Autowired;
import com.delmar.core.model.Label;
import com.delmar.core.service.LabelService;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import com.delmar.core.model.LabelTrl;
/**
 * @author 刘大磊 2016-09-03 13:51:47
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "label.value", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "label.msgtext", message = "不允许为空") })
public class LabelAction extends CoreEditPrivAction {
	private Label  label;
	private List<LabelTrl> labelTrlList=new ArrayList<LabelTrl>();;
@Autowired
private LanguageService languageService;
	@Autowired
	private LabelService labelService;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "label";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		labelService.deleteByPrimaryKey(label.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		labelService.deleteLabelList(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return label.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
public void editForm() {
label= labelService.selectByPrimaryKey(id);

		labelTrlList=labelService.getLabelTrlListByLabelId(id);
            List<Language> list=languageService.selectByExample(null);
            List<Language> noList=new ArrayList<Language>();
                for(Language lang:list)
                {
					boolean has=false;
					for(LabelTrl trl:labelTrlList)
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
					LabelTrl trl=new LabelTrl();
					trl.setLanguage(lang.getCode());
					trl.setLabelId(id);
					labelTrlList.add(trl);
                }
}
/* (non-Javadoc)
* @see com.delmar.core.web.action.CoreEditPrivAction#search()
*/
@Override
public List search() {
Map<String,Object> param=new HashMap();
param.put("searchString",getSearchWhere());
return labelService.selectByExample(param);
}


	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
public void createForm() {
label=new Label();
	labelTrlList=new ArrayList<LabelTrl>();
        List
        <Language> languageList=languageService.selectByExample(null);
            for(Language lang:languageList)
            {
		LabelTrl trl=new LabelTrl();
            trl.setLanguage(lang.getCode());
		labelTrlList.add(trl);
            }
    }
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
		labelService.saveLabel(label,labelTrlList);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
public List<LabelTrl> getLabelTrlList()
{
	return labelTrlList;
}
public void setLabelTrlList(List<LabelTrl> labelTrlList)
{
	this.labelTrlList=labelTrlList;
}
}
