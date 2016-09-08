<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<%@ page import="java.util.Properties" %>
<%@ page import="com.delmar.sys.model.UserContent" %>
<html>
<head>

<style type="text/css">
A:visited{TEXT-DECORATION:none}
A:link{text-decoration:none}
A:hover{TEXT-DECORATION:underline}

DIV,FORM,P,TD,BODY{FONT-SIZE:9pt;}

BODY {
  margin: 0px;
  padding: 0px;
	SCROLLBAR-FACE-COLOR: #0650d2; FONT-SIZE: 9pt; BACKGROUND: #ffffff; MARGIN: 0px; SCROLLBAR-HIGHLIGHT-COLOR: #0650d2; SCROLLBAR-SHADOW-COLOR: #449ae8; SCROLLBAR-3DLIGHT-COLOR: #449ae8; LINE-HEIGHT: 150%; SCROLLBAR-ARROW-COLOR: #02338a; SCROLLBAR-TRACK-COLOR: #0650d2; FONT-FAMILY: "宋体"; SCROLLBAR-DARKSHADOW-COLOR: #0650d2; TEXT-DECORATION: none
}


#TableTitleLink A:link,#TableTitleLink A:visited{COLOR:000000;}
.a1{BACKGROUND-IMAGE:url('images/title_bg.gif');COLOR:000000;}
.a2{BACKGROUND-COLOR: A4B6D7;}
.a3{BACKGROUND-COLOR: F2F8FF;}
.a4{BACKGROUND-COLOR: ECF5FF;}

.menuskin {
	BORDER-RIGHT: #0A2999 1px solid ;
	BORDER-TOP: #0A2999 1px solid;
	BORDER-LEFT: #0A2999 1px solid;
	BORDER-BOTTOM: #0A2999 1px solid;
	BACKGROUND-IMAGE: url(menubg.gif);
	POSITION: absolute;
	VISIBILITY: hidden;
}

.menuitems {
	PADDING-RIGHT: 1px;
	PADDING-TOP: 1px;
	PADDING-LEFT: 1px;
	PADDING-BOTTOM: 1px;
	MARGIN: 2px;
	font-size:9pt;
	line-height:14pt;
}
#mouseoverstyle {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	PADDING-TOP: 0px;
	BORDER-RIGHT: #0B008B 1px solid;
	BORDER-TOP: #0B008B 1px solid;
	BORDER-LEFT: #0B008B 1px solid;
	BORDER-BOTTOM: #0B008B 1px solid;
	BACKGROUND-COLOR: #FFEEC2 
}
.menuskin A {PADDING-RIGHT:10px;PADDING-LEFT:30px;}

.a1{BACKGROUND: #6B97DA; COLOR: #ffffff}
.a2{BORDER-RIGHT: #449ae8 1px solid; BORDER-TOP: #449ae8 1px solid; BORDER-LEFT: #449ae8 1px solid; BORDER-BOTTOM: #449ae8 1px solid}
.a3{BACKGROUND-COLOR: F2F8FF;}
.a4{.tdbg {
	BACKGROUND: #ffffff; LINE-HEIGHT: 120%
}

.t1 {font:12px 宋体;color:#000000} 
.t2 {font:12px 宋体;color:#ffffff} 
.t3 {font:12px 宋体;color:#ffff00} 
.t4 {font:12px 宋体;color:#800000} 
.t5 {font:12px 宋体;color:#191970} 

.aaa:hover{
	Font-unline:yes;
	font-family: "宋体";
	color: #FFFFFF;
	text-decoration: underline;
	background-color: #CCCCCC;
}

.tdbg {
	BACKGROUND: #ffffff; LINE-HEIGHT: 120%
}

</style>

	<%
	
	   String ip=request.getRemoteAddr();
	     
	 %>
		 
		 
</head>
<body topmargin="0" leftmargin="0"><br>
<table cellpadding="2" cellspacing="1" border="0" width="90%" align="center" class="a2">	
  <tr>
		<td class="a1" colspan="2" height="25" align="center"><b><delmar:message key="system.information" cn="系统信息"/></b></td>
  </tr>	
	<tr class="a4">
		<td width="50%" height="23" bgcolor="#f0f0f0"><delmar:message key="system.infor.ipaddress" cn="本机IP"/>：<font class="t4"><%=ip%></font><font class="t4"></font></td>
		<%
		
		Properties props=System.getProperties(); //获得系统属性集    
		String osName = props.getProperty("os.name"); //操作系统名称    
		String osArch = props.getProperty("os.arch"); //操作系统构架    
		String osVersion = props.getProperty("os.version"); //操作系统版本   
       
		 %>
		
		<td style="width:'50%' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.opsystem" cn="操作系统"/>：<span class="t4"><%=osName%>(<%=osVersion%>)</span></td>
	</tr>
	<tr class="a4">
		<td style="width:'50%' height:'23' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.browsertype" cn="浏览器类型"/>：<span class="t4"><script>document.write(navigator.appName)</script></span></td>
		<td style="width:'50%' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.browserversion" cn="浏览器版本"/>：<span class="t4"><script>document.write(navigator.appVersion)</script></span></td>		
	</tr>
	<tr class="a4">
		<td style="width:'50%' height:'23' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.syslanguage" cn="系统语言"/>：<span class="t4"><script>document.write(navigator.language)</script></span></td>
		<td style="width:'50%' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.browserlanguage" cn="浏览器语言"/>：<span class="t4"><script>document.write(navigator.language)</script></span></td>		
	</tr>
	<tr class="a4">
		<td style="width:'50%' height:'23' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.online" cn="在线情况"/>：<span class="t4"><script>document.write(navigator.onLine) </script></span></td>
		<td style="width:'50%' bgcolor:'#f0f0f0'"><delmar:message key="system.infor.screenresolution" cn="屏幕分辨率"/>：<span class="t4"><script>document.write(window.screen.width+"x"+window.screen.height)</script></span></td>		
	</tr>

	
	
</table>
<br>


</body>
</html>