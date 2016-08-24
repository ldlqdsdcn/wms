<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/8/24
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>生成窗口</title>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
    <script type='text/javascript' src="../js/angular/angular.min.js"></script>
</head>
<body class="container" ng-app="myApp" ng-controller="windowCtrl">
<div class="page-header">
    <h3>创建窗体</h3>
    <form role="form" name="myform">
    <div class="form-group">
        <div class="form-group row" >
            <label for="name" class="col-sm-2 control-label">窗体名</label>
            <div class="col-md-4">
                <input type="text" id="name" placeholder="请输入窗体名" ng-model="window.name" class="form-control" required >
            </div>
            <label for="tableTrlName" class="col-sm-2 control-label">表注释</label>
            <div class="col-md-4">
                <input type="text" id="tableTrlName" placeholder="请输入表注释" ng-model="tableInfo.tableTrlName"
                       class="form-control " required>
            </div>
        </div>

    </div>

    </form>
</div>
</body>
</html>
