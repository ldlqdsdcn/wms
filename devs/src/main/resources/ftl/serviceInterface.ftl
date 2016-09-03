<#include "inc/class_header.ftl"/>

package ${packagename};

import ${modelpackage}.${modelname};
import com.delmar.core.service.CoreService;
<#list lineList as item>
import com.delmar.${item.module}.model.${item.model};
</#list>
<#if lineList?exists>
import java.util.List;
</#if>
/**
 * @author 刘大磊 ${datetime}
 */
public interface ${modelname}Service extends CoreService<${modelname}> {
	/**
	 * @param ids
	 */
	public void delete${modelname}List(Integer[] ids);
<#list lineList as line>
    public List<${line.model}> get${line.model}ListBy${modelname}Id(Integer ${modelname?uncap_first}Id);
</#list>

public Integer save${modelname}(${modelname} ${modelname?uncap_first}<#list lineList as item>,List<${item.model}> ${item.model?uncap_first}List</#list>);

}