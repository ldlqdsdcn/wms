<%@ page import="com.delmar.sys.model.UserContent" %>
<%@ include file="/commons/taglib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/9/8
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Info</title>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
</head>
<body class="container">
<h2>Name</h2>
<p class="lead">
    ${loginUser.name}
</p>
<h2>token</h2>
<p class="lead" style="word-break: break-all">

	<% UserContent userContent=(UserContent)session.getAttribute("userContent");
        out.println(userContent.token);
    %>

</p>
</body>
</html>
