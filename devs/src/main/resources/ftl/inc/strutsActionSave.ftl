<#if hasCreated||hasUpdated>
Date date=new Date();
</#if>
Integer currentUserId=getCurrentUser();
<#if hasCreated>
if(${modelObjname}.isnew())
{
${modelObjname}.setCreated(date);
${modelObjname}.setCreatedby(currentUserId);
}
</#if>
<#if hasUpdated>
${modelObjname}.setUpdated(date);
${modelObjname}.setUpdatedby(currentUserId);
</#if>