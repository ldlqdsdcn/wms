<#macro decorator type>
    <#if type==1>
        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
    </#if>
<#if type==1> decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
</#if>
    <#if type==3>decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
        </#if>
    <#if type==4>decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"
        </#if>
    <#if type==5>decorator="com.delmar.system.web.displaytag.decorator.ClientDecorator"
     </#if>

</#macro>