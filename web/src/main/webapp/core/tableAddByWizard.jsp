<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>

    <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/common.js'></script>
    <script type="text/javascript">
        function validateForm()
        {
            var tableName=document.getElementById('tableName');
            if(isEmpty(tableName.value))
            {
                alert('<delmar:message key="table.message.tablenotnull" />');
                return false;
            }

            var className=document.getElementById('className');
            if(isEmpty(className.value))
            {
                alert('<delmar:message key="table.message.classnotnull" />');
                return false;
            }

            return true;
        }
        function addColumn()
        {
            var editForm=document.getElementById('editForm');
            editForm.action='<c:url value="/core/table_addColumn.action"/>';
            editForm.submit();
        }
        function deleteColumns()
        {
            var editForm=document.getElementById('editForm');
            editForm.action='<c:url value="/core/table_deleteColumns.action"/>';
            editForm.submit();
        }
        $(document).ready(function(){

            $("#next").click(function () {
                   $.ajax({
                    type : 'get',
                    url : '/core/getTableInfo.do?tableName='+$("#tableName").val(),
                    dataType : 'json',
                    success : function(result) {
                        //COLUMN_NAME
                        var pkObj=$.parseJSON(result.primaryKey);

                          $("#pkTd").html(pkObj['COLUMN_NAME']);
                        var indexArray=$.parseJSON(result.uniqueIndex);
                        var indexString="";
                        for(var i=0;i<indexArray.length;i++)
                        {
                            if(i==0)
                            {
                                indexString="<table><tr><td>字段</td><td>索引名</td></tr>";
                            }
                            var indexObj=indexArray[i];
                            var INDEX_NAME=indexObj['INDEX_NAME'];
                            var COLUMN_NAME=indexObj['COLUMN_NAME'];
                            indexString+="<tr><td>"+COLUMN_NAME+" </td><td>"+INDEX_NAME+"</td></tr>";
                        }
                        $("#indexTd").html(indexString);
                        var exportedArray=$.parseJSON(result.exportedFK);
                        var exportedString="";
                        for(var i=0;i<exportedArray.length;i++)
                        {
                            if(i==0)
                            {
                                exportedString="<table><tr><td>主键值</td><td>表</td><td>字段</td></tr>";
                            }
                            var exportedObj=exportedArray[i];
                            exportedString=exportedString+" <tr><td>"+exportedObj['PKCOLUMN_NAME']+" </td><td>"+exportedObj['FKTABLE_NAME']+" </td><td>"+exportedObj['FKCOLUMN_NAME']+"</td></tr>";
                        }
                        exportedString+="</table>";
                        $("#expTd").html(exportedString);
                        input = $.parseJSON(result.importedFK);

                       // $.jsontotable(input, { id: "#impTd"});
                        var innerHtml="";
                        for(var i=0;i<input.length;i++)
                        {
                            if(i==0)
                            {
                                innerHtml="<table><tr><td>字段</td><td>引用表</td><td>引用字段</td></tr>";
                            }
                            var lineObj=input[i];
                            var line="<tr>";
                            line+="<td>"+lineObj['FKCOLUMN_NAME']+"</td>";
                            line+="<td>"+lineObj['PKTABLE_NAME']+"</td>";
                            line+="<td>"+lineObj['PKCOLUMN_NAME']+"</td>";
                            line+="</tr>";
                            innerHtml+=line;
                        }
                        innerHtml=innerHtml+"</table>";
                      $("#impTd").html(innerHtml);
                        var columnTabel="";
                        var columnListJson = $.parseJSON(result.columnList);
                        for(var i=0;i<columnListJson.length;i++)
                        {
                            var lineObj=columnListJson[i];
                            if(i==0)
                            {
                                columnTabel="<table class='table'><thead><th>字段名</th><th>字段类型</th><th>数据类型</th><th>大小</th><th>小数位</th><th>是否为空</th></thread><tbody>";
                            }
                            columnTabel+="<tr class='"+(i%2==0?'odd':'even')+"'><td >"+lineObj['COLUMN_NAME']+"</td>"
                                        +"<td>"+lineObj['TYPE_NAME']+"</td>"
                                    +"<td>"+lineObj['DATA_TYPE']+"</td>"
                                    +"<td>"+lineObj['COLUMN_SIZE']+"</td>"
                                    +"<td>"+lineObj['DECIMAL_DIGITS']+"</td>"
                                    +"<td>"+lineObj['NULLABLE']+"</td>"
                            +"</tr>";
                        }

                        columnTabel=columnTabel+"</tbody></table>";
                        $("#columnList").html(columnTabel);
                    },
                    error : function(xhr, type) {
                        console.log('获取表信息失败');
                        myAlert.alert("获取表信息失败");

                    }
                });
            });
        });
    </script>
</head>

<body>
<s:form id="editForm" action="table_edit" namespace='/core' theme="simple">
    <s:hidden id="id" name="table.id"></s:hidden>

    <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr>
            <td valign="top">
                <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
                    <tr>
                        <td align="left" class="navig">
                            从数据库导入表

                        </td>
                        <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">

                            </td>
                    </tr>
                </table>
                <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
                    <tr align="center" valign="top">
                        <td>


                            <table width="100%" cellpadding="10" cellspacing="0" >
                                <tr>
                                    <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                                        <tr>
                                            <td>

                                                <table width="100%" border="0" cellpadding="0" cellspacing="1">

                                                    <tr  class="query_one">
                                                        <td  width="20%">请输入表名</td>
                                                        <td  width="30%" colspan="3">
                                                            <s:textfield name="table.name" id="tableName" cssStyle="width:260px;"></s:textfield>
                                                        </td>

                                                    </tr>
                                                    <tr class="query_one">
                                                        <td>主键</td>
                                                        <td colspan="3" id="pkTd"></td>
                                                    </tr>
                                                    <tr class="query_two">
                                                        <td>唯一索引</td>
                                                        <td colspan="3" id="indexTd"></td>
                                                    </tr>
                                                    <tr class="query_one">
                                                        <td>关联表</td>
                                                        <td colspan="3" id="expTd"></td>
                                                    </tr>
                                                    <tr class="query_two">
                                                        <td>引用表</td>
                                                        <td colspan="3" id="impTd"></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" class="td_page_right">
                                                            <input type="button" value="下一步" id="next" class="input_submit">

                                                            <s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" onclick="return validateForm()"></s:submit>





                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" id="columnList">


                                                        </td>
                                                    </tr>
                                                </table>


                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <label style="color: red">
                                                    <%out.println(session.getAttribute("msg")==null?"":session.getAttribute("msg"));
                                                        session.removeAttribute("msg");
                                                    %>
                                                </label>
                                                <s:actionmessage cssStyle="color:red"/>

                                            </td>
                                        </tr>
                                    </table></td></tr></table>
                        </td></tr></table></td></tr>
    </table>
</s:form>







</body>
</html>
