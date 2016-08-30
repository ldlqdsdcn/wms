<#if hasCreated||hasUpdated>
Date date=new Date();
</#if>
Integer currentUserId=getCurrentUser();
User user=getUserInSession();
<#if hasCreated>
if(${modelObjname}.isnew())
{
${modelObjname}.setCreated(date);
${modelObjname}.setCreatedby(currentUserId);

    <#if hasClientId>
    ${modelObjname}.setClientId(user.getClientId());
    </#if>
    <#if hasOrgId>
    ${modelObjname}.setOrgId(user.getOrgId());
    </#if>
    <#if hasUserId>
    ${modelObjname}.setUserId(user.getId());
    </#if>
}
</#if>
<#if hasUpdated>
${modelObjname}.setUpdated(date);
${modelObjname}.setUpdatedby(currentUserId);
</#if>
