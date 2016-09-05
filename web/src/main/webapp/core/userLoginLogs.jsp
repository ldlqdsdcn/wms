<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/commons/header.jsp"%>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#selectDiv").dialog({
				autoOpen: false,
				height: 500,
				width: 700,
				modal: true,
				title:'位置：查询条件',
				resizable:false});
			highlightTableRows("list");
			$('#search_but').click(function()
			{
				openDialog('system_user_login_logs');
			});
		});
		function openDialog(url)
		{
			document.getElementById('selectIframe').src='<c:url value='/commons/searchPage.do'/>?action_value='+url;
			$('#selectDiv').dialog('open');
		}
		function closeDialog()
		{
			$("#selectDiv").dialog('close');
		}
		function search()
		{
			closeDialog();
			window.location="<c:url value="/core/userLoginLog.action"/> ";
		}

	</script>
</head>

<body >

<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
          	<delmar:message key="loginlog.location" />
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





</td>
</tr>
<tr>
<td>
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.userSessionList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">

		<display:column titleKey="common.label.sequence"  media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<display:column property="sessionId"  escapeXml="true" titleKey="loginlog.label.sessionId"  sortable="true"/>
		<display:column property="userId" sortable="true"  titleKey="loginlog.label.loginname"  decorator="com.delmar.system.web.displaytag.decorator.UserLoginnameDecorator"/>
		<display:column property="loginDate"  escapeXml="true" titleKey="loginlog.label.logindate"  sortable="true"
		      decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		<display:column property="logoutDate"  escapeXml="true" titleKey="loginlog.label.logoutdate" sortable="true"
		      decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		<display:column property="remoteAddr"  escapeXml="true"  titleKey="loginlog.label.ip" sortable="true"/>
		<display:column property="remoteHost"  escapeXml="true"  titleKey="loginlog.label.loginhost"  sortable="true"/>
		</display:table>
</td>
</tr>
</table>


<%@include file="/commons/list_footer.jsp"%>


</body>
</html>
