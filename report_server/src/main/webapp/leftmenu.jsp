<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>left menu</title>


	<style media="screen" type="text/css"><!--
body { background-color: #f8f8f8; margin: 0; padding: 0; }
--></style>
<link rel="stylesheet" type="text/css" href="css/menu.css"/>
<script type="text/javascript">
function page_go(_url)
{

 		parent.content.location = _url;

 
}
</script>
</head>
  
<body>

<div class="usersname">
	 <fmt:message key="login.username"/> :</div>
				<div class="login_info"><c:out value="${username}"></c:out> </div>
		<div class="menutitle"><img src="images/<fmt:message key="lan.code"/>/content_15.gif" alt="" width="198" height="23" border="0"/></div>
<div id="mainMenu">
 	
 	
 	<ul id="menuList">
 	
 	
 	<li class="menubar"><a href="javascript:page_go('Console.jsp');"><fmt:message key="console.title"/></a></li>
 	<li class="menubar"><a href="javascript:page_go('schedulerList.jsp');"><fmt:message key="scheduler.title"/></a></li>
 	<li class="menubar"><a href="javascript:page_go('email/emailList.jsp');"><fmt:message key="emailaddress.title"/></a></li>
	<li class="menubar"><a href="javascript:page_go('<%=request.getContextPath()%>/excel_test.jsp');">test report</a></li>
 	</ul>
  
  </div>
<table width="100%">
  <tbody>
    <tr>
	<td align="left" class="blank">
	<a href="javascript:page_go('Console.jsp');" class="log"><img src="images/<fmt:message key="lan.code"/>/ico_index.gif" alt="" width="62" height="14" border="0"/></a>
	<a href="javascript:window.parent.page_redir('login.jsp');" class="log"><img src="images/<fmt:message key="lan.code"/>/ico_logout.gif" alt="" width="62" height="14" border="0"/></a></td>
  </tr>
  <tr>
	<td align="left" class="blank">
	<font size="1" style="font-weight: bold;">Powered By</font>
	<a target="_blank" href="http://www.powere2e.com" class="log">
	<img width="90" height="20" border="0" alt="" src="images/ico_logo_new.gif"/></a></td>
  </tr>
</tbody>
</table>

</body>
</html>