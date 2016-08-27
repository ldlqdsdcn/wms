<#list modelList as model>
	<#if model?exists>
	<action name="${model? uncap_first}_*" class="com.delmar.${modulename}.web.action.${model}Action" method="{1}">
		<result name="list">/core/${model? uncap_first}List.jsp</result>
		<result name="edit">/core/${model? uncap_first}Form.jsp</result>
	</action>
	</#if>
</#list>