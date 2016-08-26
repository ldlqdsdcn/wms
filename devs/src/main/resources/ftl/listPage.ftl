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
    <link rel="Stylesheet" href="../css/displaytag.css" type="text/css"/>
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8"/>
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
                            <input type="button" value="查询" class="input_submit">
                            <s:submit method="create" cssClass="input_submit" value="新建"></s:submit>
                            <s:submit method="deletes" cssClass="input_submit" value="删除"></s:submit>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.${mode? uncap_first}List" cellspacing="0" cellpadding="0"  requestURI=""
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
        <display:column property="${prop.prop}"  escapeXml="true" title="${prop.label}" sortable="true" <#if  prop.date >decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"</#if>/>
        </#if>
        </#list>
    </display:table>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="${namespace}/${mode? uncap_first}_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
</script>
</body>
</html>
