<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/9/7
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/JasperReportProviderServlet" method="post">
    <input name="ReportName" placeholder="reportName" width="300px"><br>
    <input name="token" placeholder="token" width="500px"><br>
    <input name="DisplayType" placeholder="token" width="500px" value="PDF"><br>
    <input type="submit" value="提交">

</form>
</body>
</html>
