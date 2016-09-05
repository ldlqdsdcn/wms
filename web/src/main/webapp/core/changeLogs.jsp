<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/commons/header.jsp"%>
	<jsp:include page="/commons/list_js.jsp">
		<jsp:param name="search_name" value="core_changelog"/>
		<jsp:param name="edit_url" value="/core/changeLog_view.action"/>
	</jsp:include>
</head>

<body >

<s:form action="changeLog_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
          <delmar:message key="changelog.location" />
          </td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <input type="button"  value="<delmar:message key="common.button.search" />" id="search_but"  class="input_submit"/>
                </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.changeLogList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="changelog.column.tablename" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
     				 <c:out value="${list.table.name}"/></a>
		     </display:column>
		     <display:column property="table.tableNameTr"  escapeXml="true"  titleKey="changelog.column.busname"  sortable="true" />
		     
		     <display:column property="pk"  escapeXml="true"   titleKey="changelog.column.key"  sortable="true" />
		     <display:column property="buPk" escapeXml="true"  titleKey="changelog.column.buskey" />
		     <display:column property="operateType" escapeXml="true"  titleKey="changelog.column.action" 
		      decorator="com.delmar.core.web.displaytag.decorator.ChangeLogOperateTypeDecorator"/>
			 <display:column property="context"  escapeXml="true"  titleKey="changelog.column.content" />
			 <display:column property="username" sortable="true"  titleKey="changelog.column.username"/>				
			<display:column property="created"  escapeXml="true"  titleKey="changelog.column.operatedate" sortable="true"
		     decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		</display:table>




</td>
</tr>
</table>


</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
