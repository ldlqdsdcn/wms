<%@ include file="/commons/taglib.jsp" %>
<html>

<!-- $Id: frameLogin.jsp,v 1.1 2008/07/31 04:24:45 solomon Exp $ -->
<head>

<head>

<script type="text/javascript">
	
	if(window.parent)
	{
	window.parent.window.close();
	window.parent.window.open('<%=request.getContextPath()%>/login.jsp', '_top');
    }
    else
    {
    	window.close();
    	window.open('<%=request.getContextPath()%>/login.jsp', '_top');
    }
</script>
</html>
