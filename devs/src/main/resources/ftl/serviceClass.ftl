<#include "inc/class_header.ftl"/>

package ${packagename};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${interfacefulldaoname};
import ${modelpackage}.${modelname};
import ${interfacefullservicename};
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
<#if lineList??>
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
</#if>
<#list lineList as item>
import com.delmar.${item.module}.model.${item.model};
</#list>
<#list lineList as item>
import com.delmar.${item.module}.dao.${item.model}Dao;
</#list>
/**
 * @author 刘大磊 ${datetime}
 */
${serviceName}
public class ${modelname}ServiceImpl extends CoreServiceImpl<${modelname}> implements
		${modelname}Service {
	@Autowired
	private ${modelname}Dao ${repositoryname};
	<#list lineList as item>
    @Autowired
    private ${item.model}Dao ${item.model?uncap_first}Dao;
	</#list>
	/* (non-Javadoc)
	 * @see CoreService.CoreServiceImpl#getCoreDao()
	 */
	protected CoreDao<${modelname}> getCoreDao() {
		return ${repositoryname};
	}
	public void delete${modelname}List(Integer[] ids)
	{
		if(ids!=null)
		for(Integer id:ids)
		{
			deleteByPrimaryKey(id);
		}
	}


public Integer save${modelname}(${modelname} ${modelname?uncap_first}<#list lineList as item>,List<${item.model}> ${item.model?uncap_first}List</#list>) {
	Integer id=save(${modelname?uncap_first});
	Date now=new Date();
	<#list lineList as item>
		for(${item.model} ${item.model?uncap_first}: ${item.model?uncap_first}List)
		{
		<#if item.hasCreated>
			if(${item.model?uncap_first}.isnew())
			{
				${item.model?uncap_first}.setCreated(now);
				${item.model?uncap_first}.setCreatedby(${modelname?uncap_first}.getUpdatedby());
			}
		${item.model?uncap_first}.setUpdated(now);
		${item.model?uncap_first}.setUpdatedby(${modelname?uncap_first}.getUpdatedby());
		</#if>
		<#if item.hasUserId>
		${item.model?uncap_first}.setUserId(${modelname?uncap_first}.getUserId());
		</#if>
		<#if item.hasOrgId>
		${item.model?uncap_first}.setOrgId(${modelname?uncap_first}.getOrgId());
		</#if>
		<#if item.hasClientId>
		${item.model?uncap_first}.setClientId(${modelname?uncap_first}.getClientId());
		</#if>
			${item.model?uncap_first}.set${modelname}Id(id);
			${item.model?uncap_first}Dao.save(${item.model?uncap_first});
		}
	</#list>
	return id;
}


    public Integer deleteByPrimaryKey(Integer id) {
<#list lineList as line>
    Map<String,Object> ${line.model?uncap_first}Param=new HashMap<String,Object>();
${line.model?uncap_first}Param.put("${modelname?uncap_first}Id",id);
${line.model?uncap_first}Dao.deleteByExample(${line.model?uncap_first}Param);
</#list>
    return super.deleteByPrimaryKey(id);
    }

	<#list lineList as line>
	public List<${line.model}> get${line.model}ListBy${modelname}Id(Integer ${modelname?uncap_first}Id)
	{
		Map<String,Object> param=new HashMap<String,Object>();
        param.put("${modelname?uncap_first}Id",${modelname?uncap_first}Id);
		if(${modelname?uncap_first}Id==null)
		{
			return new java.util.ArrayList<${line.model}>();
		}
		return ${line.model?uncap_first}Dao.selectByExample(param);
	}
	</#list>
}
