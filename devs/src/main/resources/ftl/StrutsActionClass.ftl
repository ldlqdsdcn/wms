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

import ${modelpackage}.${modelname};

import ${servicepackage}.${modelname}Service;
<#if lineList?exists>
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
</#if>
<#list lineList as item>
import com.delmar.${item.module}.model.${item.model};
</#list>

/**
 * @author 刘大磊 ${datetime}
 */
public class ${modelname}Action extends CoreEditPrivAction {
	private ${modelname}  ${modelObjname};

<#list lineList as item>
	private List<${item.model}> ${item.model?uncap_first}List=new ArrayList<${item.model}>();;
</#list>

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
		<#list lineList as item>
		${item.model?uncap_first}List=${modelObjname}Service.get${item.model}ListBy${modelname}Id(id);
		</#list>

	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#search()
	 */
	@Override
	public List search() {
		return ${modelObjname}Service.selectByExample(null);
	}

	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#createForm()
	 */
	@Override
	public void createForm() {
		${modelObjname}=new ${modelname}();
		<#list lineList as item>
		${item.model?uncap_first}List=new ArrayList<${item.model}>();
		</#list>
	}
	<#list lineList as item>
    public String add${item.model}()
    {
    ${item.model}  ${item.model?uncap_first}=new ${item.model}();
	${item.model?uncap_first}List.add(${item.model?uncap_first});
    return "edit";
    }
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
	/* (non-Javadoc)
	 * @see com.delmar.core.web.action.CoreEditPrivAction#saveForm()
	 */
	@Override
	public String saveForm() {

		${modelObjname}Service.save${modelname}(${modelObjname}<#list lineList as item>,${item.model?uncap_first}List</#list>);
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
}
