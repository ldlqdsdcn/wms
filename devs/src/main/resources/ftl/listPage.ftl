<%--
  Created by IntelliJ IDEA.
  User: ${user}
  Date: ${datetime}
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/commons/header.jsp"%>
    <jsp:include page="/commons/list_js.jsp">
        <jsp:param name="search_name" value="${module}_${mode}"/>
        <jsp:param name="edit_url" value="${namespace}/${mode? uncap_first}_edit.action"/>
    </jsp:include>
</head>
<body >
<s:form action="${mode}_list" namespace="${namespace}"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：${title}</td>
            <td class="navig" align="right">
                <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td>
                            <input type="button" value="查询" class="input_submit" id="search_but">
                            <s:submit method="create" cssClass="input_submit" value="新建"/>
                            <s:submit method="deletes" cssClass="input_submit" value="删除" onclick="return confirmListDelete()"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.${mode? uncap_first}List" cellspacing="0" cellpadding="0"  requestURI="<#if pagingByDb>${r'${pageContext.request.contextPath}'}${namespace}/${mode}_list.action</#if>"
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html">
          		<input type="checkbox" name="ids" value="<c:out value='${r'${list.id}'}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${r'${list_rowNum}'}"/>
        </display:column>
        <#list propertyList as prop>
        <#if prop_index==0>
        <display:column   title="${prop.label}" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${r'${list.id}'}"/>')"><c:out value=" ${r'${'}list.${prop.prop}${r'}'}"/></a>
        </display:column>
        <display:column property="${prop.prop}" media="csv excel xml pdf rtf"	title="${prop.label}" />
        <#else>
        <display:column property="${prop.prop}"  escapeXml="true" title="${prop.label}" <#if !pagingByDb>sortable="true"</#if>
               <#if prop.decoratorType==1>
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                </#if>
                <#if prop.decoratorType==2> decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                </#if>
                        <#if prop.decoratorType==3>decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                        </#if>
                        <#if prop.decoratorType==4>decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"
                        </#if>
                        <#if prop.decoratorType==5>decorator="com.delmar.system.web.displaytag.decorator.ClientDecorator"
                        </#if>
            />
        </#if>


        </#list>
    </display:table>
</td>
</tr>
</table>
</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
