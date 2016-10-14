<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/10/13
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
<script type="text/javascript">
   $(document).ready(function () {
       $("#addList").click(function(){
           var userArr = [];

           userArr.push({"name":"ldl","email":"ldl@126.com"});
           userArr.push({"name":"王二","email":"ldl@126.com"});
           console.log(JSON.stringify(userArr));
           $.ajax({
               // headers必须添加，否则会报415错误
               /*headers: {
                   'Accept': 'application/json',
                   'Content-Type': 'application/json'
               },*/
               contentType :'application/json',
               type: "post",
               data: JSON.stringify(userArr),
               url: "/core/addUsers.do",
               async: true,
               dataType: "json",
               beforeSend: function () {

               },
               success: function (result) {
                   alert(JSON.stringify(result));
               },
               error: function () {

               }
           });
       });

       $("#addStringList").click(function () {
           var stringArr = ["张六","李四","张三"];
           $.ajax({
               // headers必须添加，否则会报415错误
               /*headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                },*/
               contentType :'application/json',
               type: "post",
               data: JSON.stringify(stringArr),
               url: "/core/addStrings.do",
               async: true,
               dataType: "json",
               beforeSend: function () {

               },
               success: function (result) {
                   alert(JSON.stringify(result));
               },
               error: function () {

               }
           });
       });
       $("#addIntegerList").click(function () {
           var idsArr = [11,22,33];
           $.ajax({
               // headers必须添加，否则会报415错误
               /*headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
                },*/
               contentType :'application/json',
               type: "post",
               data: JSON.stringify(idsArr),
               url: "/core/addIds.do",
               async: true,
               dataType: "json",
               beforeSend: function () {

               },
               success: function (result) {
                   alert(JSON.stringify(result));
               },
               error: function () {

               }
           });
       });
   });
</script>
</head>
<body class="container">
    <button id="addList">addList</button>
    <button id="addStringList">addStringList</button>
    <button id="addIntegerList">addIntegerList</button>
    <table class="table table-bordered">


    </table>
</body>
</html>
