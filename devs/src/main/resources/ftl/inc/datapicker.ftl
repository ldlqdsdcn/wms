
<#list propertyList as prop>
    <#if  prop.date&&!prop.edit >
jQuery('#${prop.prop}').datepicker({changeMonth: true,changeYear: true,showOn: "button",
dateFormat:"yy-mm-dd",
buttonImage: "../images/DatePicker.GIF",
buttonImageOnly: true,
buttonText: "Select date"
});
    </#if>
</#list>