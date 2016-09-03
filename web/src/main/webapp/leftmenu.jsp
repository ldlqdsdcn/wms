<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@page import="com.delmar.core.web.bean.LeftMenuBean"%>
<%@page import="com.delmar.sys.model.User"%>
<%User user=(User)session.getAttribute("loginUser"); %>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <link rel="stylesheet" type="text/css" href="../css/menu.css"> -->
<style type="text/css" media="all">
	.node{width: 160;}
</style>
<title>left menu</title>
<link  rel="stylesheet" type="text/css" href="js/dtree/dtree.css"/>
<script type="text/javascript" src="js/dtree/dtree.js"></script>
<script type="text/javascript">
function page_go(_url,param)
{
	   /* SessionManager.removeHttpSession(function(data)
	    {
	     if(data=='Y')
	     {
	     parent.content.location = _url;
	     }
	    });*/
	    document.getElementById('url').value=_url;
	    if(param!=null)
	     document.getElementById('menuKey').value=param;
	    document.getElementById('urlForm').submit();

}
//document.oncontextmenu=function(e){return false;};
	
</script>
 <style type="text/css">
        <!--
        body {
           /* background-color: #3dbdf4;*/
        }

        -->
    </style>
</head>
<!-- style="background-color: #DFE8F6" -->
<body   background="images/bg_1.gif" >
<form id="urlForm" action="<c:url value="/redirect.jsp"/>"  target="content">
	<input  type="hidden" name="url" id="url"/>
	<input type="hidden" name="menuKey" id="menuKey"/>
</form>
<div class="dtree">

<a href="javascript: d.openAll();"><fmt:message key="leftmenu.label.openall"></fmt:message></a> |
 <a href="javascript: d.closeAll();"><fmt:message key="leftmenu.label.closeall"></fmt:message></a>

	<script type="text/javascript">
		<!--

		d = new dTree('d');

		d.add(0,-1,'<%=user.getName()+"@"+com.delmar.utils.DateTimeDecorator.dateToShortString(new java.util.Date())%>');
		<%=LeftMenuBean.getLeftMenu(session)%>
		<%-- <%=LeftMenuBean.getLeftMenu(session)%> --%>
		
		document.write(d);
		//-->
	</script>

</div>


</body>
</html>