<#macro validateProperty id prop>
    <#if prop.validationList?exists>
        <#list prop.validationList as val>
            <#if val==0>
            if (isEmpty($("#${id}").val())) {
            alert("${prop.label}不允许为空");
            $("#${prop.prop}").focus();
            return false;
            }
            <#elseif val==1>
            if (!isInt($("#${id}").val())) {
            alert("${prop.label}必须为整数");
            $("#${prop.prop}").focus();
            return false;
            }
            <#elseif val==2>
            if (!isDouble($("#${id}").val())) {
            alert("${prop.label}必须为数值");
            $("#${prop.prop}").focus();
            return false;
            }
            <#elseif val==3&&(prop.prop?upper_case)?index_of("TIME")==-1 >
            if (!validateDate($("#${id}").val())) {
            alert("${prop.label}必须为日期");
            $("#${prop.prop}").focus();
            return false;
            }
            <#elseif val==3>
            if (!validateDateTime($("#${id}").val())) {
            alert("${prop.label}必须为日期时间");
            $("#${prop.prop}").focus();
            return false;
            }

            </#if>


        </#list>
    </#if>
</#macro>