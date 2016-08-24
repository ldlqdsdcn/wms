<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/8/23
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>第二步</title>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
    <script type='text/javascript' src="../js/angular/angular.min.js"></script>

</head>
<body class="container" ng-app="myApp" ng-controller="tableCtrl">
<div class="page-header">
    <h3>根据表结构生成模块第二步-生成table和table_column
    </h3>
</div>
<form role="form" name="myform">
    <div class="form-group row" >
        <label for="name" class="col-sm-2 control-label">表名</label>
        <div class="col-md-4">
        <input type="text" id="name" placeholder="请输入名称" ng-model="tableInfo.name" ng-keypress="enter($event)"
               class="form-control " required >
            </div>
        <label for="tableTrlName" class="col-sm-2 control-label">表注释</label>
        <div class="col-md-4">
            <input type="text" id="tableTrlName" placeholder="请输入表注释" ng-model="tableInfo.tableTrlName"
                   class="form-control " required>
        </div>
    </div>
    <div class="form-group row" >
        <label for="index" class="col-sm-2 control-label">主键</label>
        <div class="col-md-4">
        <input type="text" id="index" readonly class="form-control" ng-model="tableInfo.pkColumn">
        </div>
        <label for="busPk" class="col-sm-2 control-label">业务主键</label>
        <div class="col-md-4">
            <input type="text" id="busPk"  class="form-control" ng-model="tableInfo.busPk">
        </div>
    </div>
    <div class="form-group row">
        <label for="outLog" class="col-sm-2 control-label">输出日志</label>
        <div class="col-sm-1">

             <input type="checkbox" id="outLog"  class="form-control" ng-model="tableInfo.outLog">

        </div>

    </div>
    <div class="form-group row">
        <label for="className" class="control-label col-sm-2 ">类名</label>
        <div class="col-md-10">
        <input type="text" id="className"  class="form-control" ng-model="tableInfo.className">
            </div>
    </div>


    <div class="form-group row">
        <label for="remark" class="control-label col-sm-2 ">备注</label>
        <div class="col-md-10">
            <input type="text" id="remark"  class="form-control" ng-model="tableInfo.remark">
        </div>
    </div>

        <div class="form-group">
              <button  type="submit" class="btn btn-primary" ng-click="submit()"  ng-disabled="tableInfo.columnList==null">保存</button>
            </div>
    <div class="form-group" ng-show="tableInfo.uniqueKeyList!=null">
        <label for="unique_list" class="control-label">索引</label>
        <table id="unique_list"  class="table table-hover table-bordered">
            <thead>
                <th>字段名</th>
                <th>索引名</th>
            </thead>
            <tbody>
            <tr ng-repeat="x in tableInfo.uniqueKeyList track by $index">
                <td>{{x.indexName}}</td><td>{{x.indexColumnName}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="form-group" ng-show="tableInfo.exportedFK!=null" >
        <label for="exportedFK" class="control-label">外部关联表</label>
        <table id="exportedFK" class="table table-hover table-bordered">
            <thead>
            <td>字段</td><td>引用表</td><td>引用字段</td>
            </thead>
            <tbody>
            <tr ng-repeat="x in tableInfo.exportedFK track by $index">
                <td>{{x.pkColumnName}}</td><td>{{x.fkTableName}}</td><td>{{x.fkColumnName}}</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="form-group" ng-show="tableInfo.importedFK!=null">
        <label for="importedFK" class="control-label">外键</label>
        <table id="importedFK" class="table table-hover table-bordered">
            <thead>
            <td>字段</td><td>引用表</td><td>引用字段</td>
            </thead>
            <tbody>
            <tr ng-repeat="x in tableInfo.importedFK track by $index">
                <td>{{x.fkColumnName}}</td><td>{{x.pkTableName}}</td><td>{{x.pkColumnName}}</td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="form-group" ng-show="tableInfo.columnList!=null">
        <label for="column_list" class="control-label">字段列表</label>
        <table id="column_list"  class="table table-hover table-bordered">
            <thead>
            <th>字段名</th>
            <th>字段类型</th>
            <th>数据类型</th>
            <th>大小</th>
            <th>小数位</th>
            <th>是否为空</th>
            <th>备注</th>
            <th>默认值</th>
            </thead>
            <tbody>
            <tr ng-repeat="x in tableInfo.columnList track by $index">
                <td>{{x.columnName}}</td>
                <td>{{x.type}}</td>
                <td>
                    <%--<select   ng-model="x.dataType"  >
                        <option ng-repeat="option in columnDataTypes" ng-value="option.key">{{option.desc}}</option>
                    </select>--%>
                    <select ng-model="x.dataType"
                            ng-options="option.key as option.desc for option in columnDataTypes"></select>
                    <%--ng-options="option.desc for option in columnDataTypes track by option.key"--%>
                    <%--<select   ng-model="x.dataType" >--%>
                    <%--<option ng-repeat="option in columnDataTypes" ng-value="option.key">{{option.desc}}</option>--%>
                    <%--</select>--%>
                </td>
                <td>{{x.columnSize}}</td>
                <td>{{x.decimalDigits}}</td>
                <td><input type="checkbox" ng-model="x.nullable"></td>
                <td><input type="text" ng-model="x.remarks" required/></td>
                <td><input type="text" ng-model="x.columnDefault"/></td>
            </tr>

            </tbody>
        </table>
    </div>
</form>

</body>

<script>
    var app = angular.module('myApp', []);
    app.controller('tableCtrl', function ($scope, $http) {
        $scope.tableInfo = {};
        $scope.columnDataTypes =${columnDataTypes};
        $scope.enter = function (ev) {
            if (ev.keyCode !== 13) return;
            $http.get("/core/getTableInfo.do?tableName=" + $scope.tableInfo.name)
                    .success(function (response) {
                        if (response.success) {
                            $scope.tableInfo = response.data;

                        }
                        else {
                            bootbox.alert(response.message, function () {
                                $("#name").focus();
                            });


                        }


                    });

        };
        $scope.submit = function () {

            if($scope.myform.$valid)
            {
                $http.post("<c:url value="/core/saveTableInfo.do"/>",$scope.tableInfo).success(function(data)
                {
                    if(data.success)
                    {
                        bootbox.alert("保存表信息成功");
                        $scope.tableInfo=data.data;
                    }
                    else {
                        bootbox.alert(data.message);
                    }

                });
            }


        };
    });
</script>
</html>
