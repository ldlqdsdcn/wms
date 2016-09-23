<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/9/7
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/JxlsReportProviderServlet" method="post">
    <input name="ReportName" placeholder="reportName" width="300px" value="users"><br>
    <input name="token" placeholder="token" width="500px" ><br>
    <input name="DisplayType" placeholder="DisplayType" width="500px" value="Excel"><br>
    <input type="submit" value="提交">

</form>
</body>
</html>
