<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store"/>
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache"/>
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="0"/>

<%@ page import="javax.servlet.*,
	 javax.servlet.http.*,
	 java.io.*,
	 java.util.zip.*,
	 java.lang.*,   
	 com.powere2e.reporttool.ReportProcessor"
%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0); 
%>

<%ReportProcessor processor = (ReportProcessor)session.getAttribute("Pro");

if(processor.isAlive())
{%>
	<html>
	<head>
	<META http-equiv="refresh" content="2;url=processbar2.jsp">
	</head>
	    <body>
		<div style="margin-top:40">
	    <table width="50%" border="0"  align="center"> 
	        <tr>
		        <a href="processbar2.jsp" ><font color=#576CC0>Report - <%= session.getAttribute("ReportName").toString()%> Loading... 
		        </font></a>
		    </tr>
	    </table>
		</div>
	    </body>
	</html>
<%}
else
{%>
	<html>
	<head>
	<META http-equiv="refresh" content="2;url=showReport">
	<script type="text/javascript">
	function sonpage()
	{
		 //alert("111111111");
		parent.document.all.status.src="#";
		
	}
	</script>
	</head>
	    <body onload="sonpage();">
		<div style="margin-top:40">
	    <table width="50%" border="0" cellspacing="0" align="center"> 
	        <tr>
		        <font color=#576CC0>Report - <%= session.getAttribute("ReportName").toString()%> Finished... </font>
				<!--<%= processor.getProcess()%>%-->
		    </tr>
	    </table>
		</div>
	    </body>
	    
	</html>
<%}%>