<#include "inc/class_header.ftl"/>

package com.delmar.${module}.model;

<#if hasDate>
import java.util.Date;
</#if>
<#if hasDecimal>
import java.math.BigDecimal;
</#if>
<#if module!='core'>
import com.delmar.core.model.CoreModel;
</#if>
import lombok.Data;
 /**
  * table name ${tableName}
  * Date:${datetime}
  **/
@Data
public class ${model} extends CoreModel {

<#list propertyList as prop>
private ${prop.propertyType} ${prop.propertyName};
</#list>

}