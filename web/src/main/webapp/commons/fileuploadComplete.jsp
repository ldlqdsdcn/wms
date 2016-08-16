<%@page import="com.delmar.core.web.util.FacesUtils"%>
<%@ page language="java" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.delmar.common.web.bean.FileUpLoad"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传文件完成</title>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body><script type="text/javascript">
  
   var fileNames='<%=FileUpLoad.doFileUpload(application,request)%>';
  
   if(fileNames=='0')
   {
   	
   }
   else if(fileNames=='1')
   {
   		document.writeln('<h1>文件大于10M</h1>');
   }
   else
   {
   	 	var files=fileNames.split(":fileName:");
   	 	alert(<%=session.getAttribute("rowIndex")%>);
		window.opener.document.closeDialog(<%=session.getAttribute("rowIndex")%>,files[0],files[1]);
   }
   </script>
  <h1>上传文件失败！！</h1>
  
  </body>
</html>
