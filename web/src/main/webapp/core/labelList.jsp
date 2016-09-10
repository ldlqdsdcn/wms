<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-09-10 10:28:27
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/commons/header.jsp"%>
    <jsp:include page="/commons/list_js.jsp">
        <jsp:param name="search_name" value="core_label"/>
        <jsp:param name="edit_url" value="/core/label_edit.action"/>
    </jsp:include>
</head>
<body >
<s:form action="label_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：标签</td>
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
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.labelList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html">
          		<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${list_rowNum}"/>
        </display:column>
        <display:column   title="键值" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value=" ${list.value}"/></a>
        </display:column>
        <display:column property="value" media="csv excel xml pdf rtf"	title="键值" />


        <display:column property="msgtext"  escapeXml="true" title="信息" sortable="true"
                                                                                                    />


        <display:column property="compDate"  escapeXml="true" title="compDate" sortable="true"
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                                                                                                    />


        <display:column property="bgnTime"  escapeXml="true" title="开始时间" sortable="true"
                 decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                                                                                    />


    </display:table>
</td>
</tr>
</table>
</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
