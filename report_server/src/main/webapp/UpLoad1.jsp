<%@ include file="/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>UpLoad File</title>
<link href="css/styleIE.css" type=text/css rel='stylesheet'/>
</head>
<body>
<jsp:useBean id="upBean" scope="page" class="com.powere2e.reporttool.UpLoadBean"/>
<fmt:message key="upload1.complete"> </fmt:message>
<%

upBean.upLoad(request);

out.println(upBean.getErrorMessage());
%>
<table id=Table3 cellspacing=0 cellpadding=0 width=500 bgcolor=white border=0>
  <tbody>
  <tr style="height: 10px;"><td></td></tr>
  <tr>
    <td width=5%>
	</td>  
    <td width=5% align="center" valign="middle"></td>
	<td width=65% valign="middle"></td>
	<td width="25%"><input type="button" class="button" value="OK" onclick="javascript:window.close();"/></td>
	<td width=5%></td>
   </tr>
   </tbody>
</table>
</body>
</html>
