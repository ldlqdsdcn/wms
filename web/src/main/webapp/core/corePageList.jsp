<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-26 17:08:24
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
<s:form action="corePage_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：页面</td>
            <td class="navig" align="right">
                <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td>
                            <input type="button" value="查询" class="input_submit">
                            <s:submit method="create" cssClass="input_submit" value="新建"/>
                            <s:submit method="deletes" cssClass="input_submit" value="删除"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.corePageList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>">
          		<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${list_rowNum}"/>
        </display:column>
        <display:column   title="名称" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value=" ${list.name}"/></a>
        </display:column>
        <display:column property="name" media="csv excel xml pdf rtf"	title="名称" />
        <display:column property="descr"  escapeXml="true" title="描述" sortable="true" />
        <display:column property="help"  escapeXml="true" title="帮助" sortable="true" />
        <display:column property="windowId"  escapeXml="true" title="windowId" sortable="true" />
        <display:column property="tableId"  escapeXml="true" title="tableId" sortable="true" />
        <display:column property="filterColumnId"  escapeXml="true" title="filterColumnId" sortable="true" />
        <display:column property="isactive"  escapeXml="true" title="是否有效" sortable="true" />
        <display:column property="showInTab"  escapeXml="true" title="showInTab" sortable="true" />
        <display:column property="seqNo"  escapeXml="true" title="seqNo" sortable="true" />
        <display:column property="filterSql"  escapeXml="true" title="filterSql" sortable="true" />
    </display:table>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/core/page_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
</script>
</body>
</html>
