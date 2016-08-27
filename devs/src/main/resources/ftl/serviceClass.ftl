/******************************************************************************
 * 德玛国际物流有限公司  2013-07-01												      *
 *	作者：刘大磊								                                      *
 * 电话：0532-66701118                                                          * 
 * email:liua@delmarchina.com						                          *
 *****************************************************************************/

package ${packagename};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${interfacefulldaoname};
import ${modelpackage}.${modelname};
import ${interfacefullservicename};
import com.delmar.core.dao.CoreDao;
import com.delmar.core.service.impl.CoreServiceImpl;
<#if lineList?exists>
import java.util.List;
import java.util.Map;
import java.util.HashMap;
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
	<#list lineList as item>
		for(${item.model} ${item.model?uncap_first}: ${item.model?uncap_first}List)
		{
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
		return ${line.model?uncap_first}Dao.selectByExample(param);
	}
	</#list>
}
