<#if hasCreated||hasUpdated>
    Date date=new Date();
</#if>
    User user=getUserInSession();
<#if hasCreated>
    if(${modelObjname}.isnew())
    {
        ${modelObjname}.setCreated(date);
        ${modelObjname}.setCreatedby(user.getId());
    }
</#if>
<#if hasUpdated>
    ${modelObjname}.setUpdated(date);
    ${modelObjname}.setUpdatedby(user.getId());
</#if>
<#if hasClientId>
    ${modelObjname}.setClientId(user.getClientId());
</#if>
<#if hasOrgId>
    ${modelObjname}.setOrgId(user.getOrgId());
</#if>
<#if hasUserId>
    ${modelObjname}.setUserId(user.getId());
</#if>

