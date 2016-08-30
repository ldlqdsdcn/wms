<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/29
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>公共查询</title>
  <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
  <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
  <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
  <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
  <script type='text/javascript' src="../js/angular/angular.min.js"></script>
  <script type="text/javascript" src="../js/jquery/plugin/jquery.datetimepicker.full.min.js"></script>
  <link rel="Stylesheet" href="../js/jquery/plugin/jquery.datetimepicker.min.css" type="text/css" >
</head>
<body class="container" ng-app="myApp" ng-controller="tableCtrl">
<form role="form" name="myform" class="form-inline">
    <div class="form-group">
    <select  class="form-control" id="columnName" ng-model="currentColumn" ng-options="option.columnLabel for option in columnList">
    </select>
      </div>
  <div class="form-group">
    <select  class="form-control" id="opearType" ng-model="currentColumn.opearType" ng-options="m for m in  currentColumn.relOpearList">
    </select>
  </div>
  <div class="form-group">
    <input type="text" class="form-control" ng-model="currentColumn.value"  ng-show="currentColumn.inputType==1">
    <select  class="form-control"  ng-model="currentColumn.value" ng-options="option.key as option.label for option in currentColumn.commonSearchResultList" ng-show="currentColumn.inputType==2"></select>
    <input type="text"  ng-show="currentColumn.inputType==3" class="form-control" id="value-date"  ng-model="currentColumn.value">
    <input type="text" class="form-control" id="value-date-time" ng-show="currentColumn.inputType==4"  ng-model="currentColumn.value">

  </div>
  <div class="form-group">
    <button  type="submit" class="btn btn-primary" ng-click="submit()"  ng-disabled="currentColumn.value==null">添加</button> <button  type="submit" class="btn btn-primary" ng-click="search()">查找</button>
  </div>
  <div class="form-group" ng-show="searchColumnList!=null">
    <label for="column_list" class="control-label">字段列表</label>
    <table id="column_list"  class="table table-hover table-bordered">
      <thead>
      <th>字段</th>
      <th>比较符</th>
      <th>值</th>
      <th></th>
      </thead>
      <tbody>
      <tr ng-repeat="x in searchColumnList track by $index">
        <td>{{x.columnLabel}}</td>
        <td>{{x.opearType}}</td>
        <td>
          <div ng-switch="x.inputType">
            <div ng-switch-when="2">
              <select   ng-model="x.value" ng-options="option.key as option.label for option in x.commonSearchResultList" disabled="disabled"></select>
            </div>
            <div ng-switch-default>
              {{x.value}}
            </div>
          </div>
        </td>
        <td><button  type="button" class="btn btn-default" ng-click="delete(x)"  >删除</button></td>
      </tr>

      </tbody>
    </table>
  </div>
</form>
</body>
<script type="text/javascript">
  $.datetimepicker.setLocale('en');

  var app = angular.module('myApp', []);
  app.controller('tableCtrl', function ($scope, $http)  {

    $('#value-date').datetimepicker({
      dayOfWeekStart : 1,
      lang:'en',
      timepicker:false,
      format:"Y-m-d"
    });

    $('#value-date-time').datetimepicker({
      dayOfWeekStart : 1,
      lang:'en',

      format:"Y-m-d H:i:s",step:10
    });

    $scope.columnList=${searchColumnJson};
    $scope.searchColumnList=${addedSearchColumnVoListJson};
    $scope.currentColumn=$scope.columnList[0];
    $scope.submit = function () {

      if($scope.myform.$valid)
      {
          var addColumn={};
          angular.copy( $scope.currentColumn,addColumn);
          console.log("addColumn="+JSON.stringify(addColumn));
          console.log("currentColumn="+$scope.currentColumn.columnname);
        $scope.searchColumnList.push(addColumn);
      }
    };
    $scope.delete=function(x)
    {
      var index = $scope.searchColumnList.indexOf(x);
      $scope.searchColumnList.splice(index, 1);
    };

    $scope.search=function()
    {
      console.log(JSON.stringify($scope.searchColumnList));
      $http.post("<c:url value="/commons/search.do"/>",$scope.searchColumnList).success(function(data)
      {
        if(data.success)
        {
          window.parent.search();
        }
        else {
          bootbox.alert(data.message);
        }
      });
    };
  });

</script>
</html>
