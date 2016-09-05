<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/>"></script>
	<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
  <script type='text/javascript' src='../js/ea.validate.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">

</script>

</head>

<body >

<s:form action="usersub_listsub" namespace="/system"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.userList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="100" class="table" export="false">
		<display:column titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
	     <display:column  titleKey="user.column.loginname" sortable="true" media="html">
    	      <a href="javascript:becomeToThis('<c:out value="${list.id}"/>')">
   				 <c:out value="${list.username}"/></a>
	     </display:column>
	     <display:column property="name"  titleKey="user.column.name"/>
		 <display:column property="org.name"  escapeXml="true"  titleKey="user.column.org" sortable="true" />
		</display:table>

</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function becomeToThis(sid) {
            window.parent.location.href='<c:url value="/system/userLogsub.action"/>?sid='+sid;
    }
   
</script>

</body>
</html>
