<%@ include file="/commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>UpLoad File</title>
<link href="css/styleIE.css" type='text/css' rel='stylesheet'/>
</head>
<body style="margin-top: 5px;">
<form action="UpLoad1.jsp" enctype="MULTIPART/FORM-DATA" method='post'>
	<table id='Table1' cellspacing='0' cellpadding='0' width='500' bgcolor='white' border='0'>
  <tbody>
  <tr style="height: 20px;">
    <td></td>
  </tr>
  <tr><td>
	     <table cellspacing=0 cellpadding=0 width="100%" align=center border=0>
	        <tbody>
	        <tr style="height: 5px;">
	          <td></td>
	        </tr>
	        <tr style="height: 1px;">
	          <td width="100%" bgcolor=#d1d1d1 colspan=2></td>
	        </tr>
	        <tr style="height: 5px;">
	          <td></td>
	        </tr>
	        </tbody>
	     </table>
  	</td></tr>
  
  <tr><td>
	<table id=Table3 cellspacing=0 cellpadding=0 width=500 bgcolor=white border=0>
	  <tbody>
	  <tr style="height: 10px;"><td></td></tr>
	  <tr>
	    <td width=5%></td>  
	    <td width=30% align="center" valign="middle"><fmt:message key="upload.uploadfile"> </fmt:message></td>
		<td width=65% valign="middle">
			<input type="file" class="text" name="file" />
		</td>
		<td width=25%><input type="submit" class="button" value="<fmt:message key="console.Upload"/>" /></td>
		<td width=5%></td>
	  </tr>
	  </tbody>
	</table>
	</td>
  </tr>
</tbody>
</table>
	<table id=Table0 cellspacing=0 cellpadding=0 width=500 bgcolor=white border=0>
  <tr style="height: 30px;"><td></td></tr>
  <tr bgcolor=#386d9f style="height: 18px;">
    <td colspan=5>&nbsp;</td></tr>
  <tr  style="height: 2px;">
    <td></td></tr>
  <tr>
    <td align=right><font color='#386d9f'>&nbsp;Copyright 2006, All Rights Reserved, Powere2e</font></td>
  </tr>
 </table>
</form>
</body>
</html>
