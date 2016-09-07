<%@ page contentType="text/html; charset=gb2312"%>
<%@ page language="java" pageEncoding="GB2312" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title> Report Parameter </title>
<style type="text/css">
<!--
.style7 {color: #FFFFFF; font-weight: bold; }
.style8 {color: #FFFFFF}
.style9 {color: #000000}
-->
</style>

</head>
<body>

<form name="form1" method="post" action="ReportProviderServlet">
  <p>&nbsp;</p>
  <table width="50%" height="246" border="1" align="center"> 
 
  <input type="hidden" name="ReportName" value="ArticleAllocation">
  
    <tr bordercolor="#FFFFFF">
      <td height="35" colspan="2"> <div align="left"><strong>Reports - Article Allocation </strong></div></td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td width="50%" height="35" bgcolor="#CC3366"><div align="right"><span class="style7">MM Number </span></div></td>
      <td width="50%" height="35"><input name="textfield1" type="text" id="textfield12" size="10">Search </td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td height="35" bgcolor="#CC3366"> <div align="right"><span class="style7">Store No. </span></div></td>
      <td height="35">01</td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td height="35" bgcolor="#CC3366"> <div align="right"><span class="style7">Floor No.</span></div></td>
      <td height="35"><select name="select1" id="select">
        <option selected>All</option>
      </select></td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td height="35" bgcolor="#CC3366"> <div align="right"><span class="style7">Area. </span></div></td>
      <td height="35"><select name="select2">
        <option selected>All</option>
      </select></td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td height="35" bgcolor="#CC3366"> <div align="right"><span class="style7">Supplier Number</span></div></td>
      <td height="35"><input name="textfield2" type="text" size="10">
      Search </td>
    </tr>
    <tr bordercolor="#FFFFFF">
      <td height="37" bgcolor="#CC3366"> <div align="right"><span class="style8"><span class="style7">Supplier Name </span> </span></div></td>
      <td height="37">&nbsp;</td>
    </tr>
    
    
    
    <tr bordercolor="#CCCCCC">
      <td height="35" bordercolor="#CCCCCC">
          <span class="style9">PDF</span>
          <input type=radio name=DisplayType value="PDF" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          
          <span class="style9">Excel</span>
	  <input type=radio name=DisplayType value="Excel" >
      </td>
      <td height="35" bordercolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr bordercolor="#CCCCCC">
      <td height="35" colspan="2"><input type="submit" name="s" value="View" onclick="form1.submit();form1.nextlink.value="pdfList.jsp""></td>
    </tr>
   
  </table>
  <p>&nbsp;</p>
</form>
</body>
</html>
