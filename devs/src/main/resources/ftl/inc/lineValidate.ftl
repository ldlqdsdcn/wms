<#list lineList as line>
$("input[name^=${line.model?uncap_first}List]").each(function(i, item){

    <#list line.propertyList as prop>
        <#if prop.prop!=(mode+'Id')>
        <#if prop.validationList??>
        if(endWith(item.name,'${prop.prop}')){
            <#list prop.validationList as val>
                <#if val==0>
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"${line.label+' '+prop.label}不允许为空";
                $(item).focus();
                validateLine=false;
                }
                <#elseif val==1>
                if (!isInt($(item).val())) {
                lineMsg+="<br>"+"${line.label+' '+prop.label}必须为整数";

                $(item).focus();
                validateLine=false;
                }
                <#elseif val==2>
                if (!isDouble($(item).val())) {
                lineMsg+="<br>"+"${line.label+' '+prop.label}必须为数值";
                $(item).focus();
                validateLine=false;
                }
                <#elseif val==3&&(prop.prop?upper_case)?index_of("TIME")==-1 >
                if (!validateDate($(item).val())) {
                lineMsg+="<br>"+"${line.label+' '+prop.label}必须为日期";
                $(item).focus();
                validateLine=false;
                }
                <#elseif val==3>
                if (!validateDateTime($(item).val())) {
                lineMsg+="<br>"+"${line.label+' '+prop.label}必须为日期时间";
                $(item).focus();
                validateLine=false;
                }

                </#if>


            </#list>
        }</#if>
        </#if>

    </#list>
});
</#list>