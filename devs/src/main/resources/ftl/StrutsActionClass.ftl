/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01										      *
 *	作者：刘大磊								                              *
 * 电话：0532-66701118                                                        *
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/
package ${packagename};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.delmar.core.web.action.CoreEditPrivAction;
<#if (requiredStrings?size>0)>
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
</#if>
<#if (requiredFields?size>0)>
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
</#if>
<#if needValidate>
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import ${modelpackage}.${modelname};
import ${servicepackage}.${modelname}Service;
<#include "inc/strutsActionInclude.ftl"/>
<#if lineList?exists>
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
<#list lineList as item>
import com.delmar.${item.module}.model.${item.model};
</#list>
</#if>
/**
 * @author 刘大磊 ${datetime}
 */
<#if needValidate>
<#--TODO 需要完善-->
@Validations(<#if (requiredStrings?size>0)>requiredStrings = {<#list requiredStrings as prop>@RequiredStringValidator(type = ValidatorType.FIELD,
trim=true, fieldName = "${modelObjname}.${prop}", message = "不允许为空") <#if prop_has_next>,</#if></#list>}<#if (requiredFields?size>0)>,</#if></#if><#if (requiredFields?size>0)>requiredFields = {<#list requiredFields as prop>@RequiredFieldValidator(type =ValidatorType.FIELD,fieldName = "${modelObjname}.${prop}",message = "不允许为空")<#if prop_has_next>,</#if></#list>}</#if>)
</#if>
public class ${modelname}Action extends CoreEditPrivAction {
	private ${modelname}  ${modelObjname};
<#if lineList?exists>
<#list lineList as item>
	private List<${item.model}> ${item.model?uncap_first}List=new ArrayList<${item.model}>();;
</#list>
</#if>
	@Autowired
	private ${modelname}Service ${modelObjname}Service;
	
	private void init()
	{

	}
	
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return "${modelObjname}";
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#delete()
	 */
	@Override
	public String delete() {
		${modelObjname}Service.deleteByPrimaryKey(${modelObjname}.getId());
		return list();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#deleteList(java.lang.Integer[])
	 */
	@Override
	public void deleteList(Integer[] ids) {
		
		${modelObjname}Service.delete${modelname}List(ids);

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#getModelId()
	 */
	@Override
	public Integer getModelId() {

		return ${modelObjname}.getId();
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#editForm()
	 */
	@Override
	public void editForm() {
		 ${modelObjname}= ${modelObjname}Service.selectByPrimaryKey(id);
		<#if lineList?exists>
		<#list lineList as item>
		${item.model?uncap_first}List=${modelObjname}Service.get${item.model}ListBy${modelname}Id(id);
		</#list></#if>

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		Map<String,Object> param=new HashedMap();
		param.put("searchString",getSearchWhere());
		return ${modelObjname}Service.selectByExample(param);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		${modelObjname}=new ${modelname}();
<#if lineList?exists>
		<#list lineList as item>
		${item.model?uncap_first}List=new ArrayList<${item.model}>();
		</#list>
</#if>
	}
<#if lineList?exists>
	<#list lineList as item>
    @SkipValidation
    public String add${item.model}()
    {
    ${item.model}  ${item.model?uncap_first}=new ${item.model}();
	${item.model?uncap_first}List.add(${item.model?uncap_first});
    return "edit";
    }
    @SkipValidation
    public String delete${item.model}s()
    {
    String[] ids=ServletActionContext.getRequest().getParameterValues("${item.model}_ids");
    List<String> idList=new ArrayList<String>();

        //
        Integer[] intids=new Integer[ids.length];

        for(int i=0;i<ids.length;i++)
        {
        idList.add(ids[i]);
        Integer index=Integer.parseInt(ids[i]);
	   ${item.model} column=${item.model?uncap_first}List.get(index);
        if(column.getId()!=null&&column.getId()!=0)
        {
        intids[i]=column.getId();
        }
        }
        java.util.Collections.sort(idList);
        for(int i=idList.size()-1;i>=0;i--)
        {
        	${item.model?uncap_first}List.remove(Integer.parseInt(idList.get(i)));
        }
        return "edit";
	}
	</#list>
</#if>
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {
		<#include "inc/strutsActionSave.ftl"/>
		${modelObjname}Service.save${modelname}(${modelObjname}<#if lineList?exists><#list lineList as item>,${item.model?uncap_first}List</#list></#if>);
		return "edit";
	}
	/**
	 * @return the usergroup
	 */
	public ${modelname} get${modelname}() {
		return ${modelObjname};
	}

	/**
	 * @param ${modelObjname} the ${modelObjname} to set
	 */
	public void set${modelname}(${modelname} ${modelObjname}) {
		this.${modelObjname} = ${modelObjname};
	}
<#if lineList?exists>
<#list lineList as item>
public List<${item.model}> get${item.model}List()
{
	return ${item.model?uncap_first}List;
}
public void set${item.model}List(List<${item.model}> ${item.model?uncap_first}List)
{
	this.${item.model?uncap_first}List=${item.model?uncap_first}List;
}
</#list>
</#if>
}
