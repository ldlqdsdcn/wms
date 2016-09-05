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
 * @author 刘大磊 2016-09-05 14:01:13
 */
@Validations(requiredStrings = {@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "label.value", message = "不允许为空") ,@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "label.msgtext", message = "不允许为空") })
public class LabelAction extends CoreEditPrivAction {
	private Label  label;
	private List<LabelTrl> labelTrlList=new ArrayList<LabelTrl>();;
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
    }
    @SkipValidation
    public String addLabelTrl()
    {
    LabelTrl  labelTrl=new LabelTrl();
	labelTrlList.add(labelTrl);
    return "edit";
    }
    @SkipValidation
    public String deleteLabelTrls()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("LabelTrl_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   LabelTrl column=labelTrlList.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	labelTrlList.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
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
