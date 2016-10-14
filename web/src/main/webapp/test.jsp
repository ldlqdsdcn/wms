<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/10/12
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jquery Ajax Test</title>
    <script type="text/javascript" src="js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="js/ea.util.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#getUsers").click(function () {
                testHelper.getUserList();
            });
            $("#addUser").click(function () {
                var user={};
                user.name=$("#name").val();
                user.email=$("#email").val();
                user.birthday=$("#birthday").val();
                    testHelper.addUser(user);
            });
            $("#saveUser").click(function()
            {
                var array=[];
                $('#userList tr').each(function(trindex,tritem){
                   tds= tritem.childNodes;
                    var user={};
                    user.name=tds[0].childNodes[0].value;
                    user.email=tds[1].childNodes[0].value;
                    user.birthday=tds[2].childNodes[0].value;
                    array.push(user);

                });
                alert(JSON.stringify(array));
                testHelper.saveUserList({userVoList:array});
            });
        });
        var testHelper={
            getUserList:function()
            {
                $.ajax({url:"test/users.do",success:function (data) {
                    alert(JSON.stringify(data));
                    var sb=new StringBuffer();
                    $.each(data,function(n,item){
                        sb.append("<tr>");
                        sb.append("<td> <input type='text' value='").append(item.name!=null?item.name:"").append("'></td>");
                        sb.append("<td><input type='text' value='").append(item.email!=null?item.email:"").append("'></td>");
                        sb.append("<td><input type='text' value='").append(item.birthday!=null?item.birthday:"").append("'></td>");
                        sb.append("</tr>");
                    });
                    alert(sb.toString());
                    $("#userList").html(sb.toString());
                }});
            },
            saveUserList:function (userList) {
                $.ajax({url:"test/saveUsers.do",
                    type:"POST",
                    contentType:"application/x-www-form-urlencoded; charset=utf-8",
                    dataType: "json",
                    data:userList,
                    success:function (data) {
                    alert(JSON.stringify(data));
                }});
            }
            ,addUser:function (user) {
                alert(JSON.stringify(user));
                $.ajax({url:"test/saveUser.do",
                        type:"POST",
                        contentType:"application/x-www-form-urlencoded; charset=utf-8",
                        dataType: "json",
                        data:user,
                        success:function (data) {
                    if(data.success)
                    {

                        alert(JSON.stringify(data));

                    }
                    else
                    {
                        alert(data.message);
                    }

                }});
            }
        };

    </script>
</head>
<body>
<div>
    <button id="getUsers">获取用户列表</button>
    <button id="saveUser">保存用户列表</button>
    <button id="addUser">添加用户</button>
</div>
<div>
    <input type="text" name="name" id="name" placeholder="name"><br>
    <input type="text" name="email" id="email" placeholder="email"><br>
    <input type="text" name="birthday" id="birthday" placeholder="birthday">

</div>

<table style="width: 1000px;">
    <thead>
    <th>用户名</th>
    <th>email</th>
    <th>生日</th>
    </thead>
    <tbody id="userList">
        <tr><td colspan="3">无数据</td></tr>
    </tbody>
</table>

</body>
</html>
