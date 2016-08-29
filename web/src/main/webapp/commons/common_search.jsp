<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/29
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公共查询</title>
  <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
  <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
  <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
  <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
  <script type='text/javascript' src="../js/angular/angular.min.js"></script>
</head>
<body class="container" ng-app="myApp" ng-controller="tableCtrl">
<form role="form" name="myform" class="form-inline">
  <div class="form-group">
  <select  class="form-control" id="columnName" ng-model="column.columnName" ng-options="option.key as option.label for option in column.commonSearchResultList">
  </select>
    </div>
  <div class="form-group">
    <select  class="form-control" id="opearType" ng-model="column.opearType" ng-options="option.key as option.label for option in column.relOpearList">
    </select>
  </div>
  <div class="form-group" ng-switch="column.inputType">
    <div ng-switch-when="1">
      <input type="text" class="form-control" ng-model="column.value">
    </div>
    <div ng-switch-when="2">
      <select  class="form-control" id="value" ng-model="column.value" ng-options="option.key as option.label for option in column.commonSearchResultList"></select>
    </div>
    <div ng-switch-when="3">
      <input type="text" class="form-control" id="value">
    </div>
    <div ng-switch-when="4">
      <input type="text" class="form-control" id="value">
    </div>
  </div>
</form>
</body>
</html>
