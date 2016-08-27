        $.datetimepicker.setLocale('en');
<#list propertyList as prop>
    <#if  prop.date&& prop.edit >
        <#if (prop.prop?upper_case)?index_of("TIME")!=-1>

        $('#${prop.prop}').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        format:"Y-m-d H:i:s"
        });
        <#else>
        $('#${prop.prop}').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        timepicker:false,
        format:"Y-m-d",
        step:10
        });
    </#if>
</#if>
</#list>