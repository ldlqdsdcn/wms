<%@ include file="/commons/taglib.jsp" %>
<%
	if(session.getAttribute("n") == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<c:choose>
	<c:when test="${param.locale=='en_US' }">
	<fmt:setLocale value="en_US" scope="session"/>
	</c:when>
	<c:when test="${param.locale=='zh_CN' }">
	<fmt:setLocale value="zh_CN" scope="session"/>
	</c:when>
</c:choose>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0); 
		%>
		<meta http-equiv="content-type" content="text/html;charset=utf-8" />
		<meta name="generator" content="Adobe GoLive" />
		<title>Reporttool index</title>
		<link href="css/contentpage.css" rel="stylesheet" type="text/css" media="all">
		<style type="text/css" media="screen"><!--

.for_foot { background-image: url(images/foot_bgimages.gif); text-align: right; }
.for_head { background-image: url(images/head_bgimages.gif); }
.for_headimg { float: left; margin: 0; padding: 0; }
body { font-size: 12px; line-height: 14px; text-decoration:none; margin: 0; padding: 0; }
.forlanguage_ch{ color: white; font-size: 12px; font-family: Tahoma; float: right; margin-top: 29px; margin-right: 10px;font-weight: bold; }
.forlanguage_en{ color: white; font-size: 12px; font-family: Tahoma; float: right; margin-top: 29px; margin-right: 20px;font-weight: bold; }
.forlanguage_faq{ color:white; font-size: 12px; font-family: Tahoma; float: right; margin-top: 29px; margin-right: 18px;font-weight: bold; }
--></style>
<script type="text/javascript">
    function page_redir(_url)
    {
    		parent.location=_url;
    		return;
    }
</script>     
	</head>

	<body>
		<table width="100%" cellpadding="0" align="center" height="100%">
			<tr>
				<td colspan="2"  class="for_head">
				<div class="for_headimg">
				<img  src="images/title_01.gif" alt="" height="53" width="621" border="0" />
				</div>
	<a  class="forlanguage_ch" href="main.jsp?locale=zh_CN" style="color: white;"><fmt:message key="lan.Chinese"/></a>
    <a class="forlanguage_ch" href="main.jsp?locale=en_US" style="color: white;"><fmt:message key="lan.English"/></a></td>
		
			</tr>
			<tr valign="top" >
				<td class="C_leftmenu_printline"  width="22%"  valign="top" height="100%"   >
				<iframe id="leftmenu" name="leftmenu" src="leftmenu.jsp" frameborder="0" align="top" height="100%" width="220" style="margin-top:0px; margin-left:0px; border:0px; padding: 0px ;">
				</iframe>
				
				</td>
				<td  width="auto" class="C_content" valign="top" height="100%"   >
				<iframe id="content" name="content" src="<c:url value='${contextpath }'/>" frameborder="0" align="top" height="100%" width="100%" style="margin-top:0px; margin-left:0px; border:0px; padding: 0px;">
				</iframe>				
				</td>
			</tr>

		</table>
	</body>

</html>