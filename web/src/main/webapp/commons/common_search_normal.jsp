<%@ page import="com.delmar.common.vo.SearchFormRow" %>
<%@ page import="java.util.List" %>
<%@ page import="com.delmar.common.vo.SearchColumnVo" %>
<%@ page import="com.delmar.core.def.SearchShowTypeDef" %>
<%@ page import="com.delmar.core.model.CommonSearchResult" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/10/8
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
    <title>公共查询-普通查询</title>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/jquery/jquery-1.11.1.min.js'></script>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
    <script type="text/javascript" src="../js/jquery/plugin/jquery.datetimepicker.full.min.js"></script>
    <link rel="Stylesheet" href="../js/jquery/plugin/jquery.datetimepicker.min.css" type="text/css" >
    <script type='text/javascript' src='../js/ea.validate.js'></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body class="container">
    <form  name="myform" class="form-horizontal col-box">

<%List<SearchFormRow> searchFormRowList=(List<SearchFormRow>)request.getAttribute("searchFormRows");
    for(SearchFormRow searchFormRow:searchFormRowList)
    {
        List<SearchColumnVo> searchColumnVoList= searchFormRow.getSearchColumnVoList();
        int columnSize=searchColumnVoList.size();
        int smLength=4;
        if(columnSize==1)
        {
            smLength=10;
        }
        else if(columnSize==2)
        {
            smLength=4;
        }
        else if(columnSize==3)
        {
            smLength=2;
        }
%>        <div class="form-group" >
           <%  for(SearchColumnVo searchColumnVo:searchColumnVoList)
           {%>
        <label for="<%=searchColumnVo.getColumnId()%>" class="col-sm-2 control-label"><%=searchColumnVo.getColumnLabel()%></label>
        <div class="col-sm-<%=smLength%>">
            <%if(searchColumnVo.getShowType()== SearchShowTypeDef.INPUT.getKey()||searchColumnVo.getShowType()== SearchShowTypeDef.DATEPICKER.getKey()||searchColumnVo.getShowType()== SearchShowTypeDef.DATETIMEPICKER.getKey()){%>
            <input class="form-control" type="text" id="<%=searchColumnVo.getColumnId()%>" name="<%=searchColumnVo.getColumnName()%>" value="<%=searchColumnVo.getValue()==null?"":searchColumnVo.getValue()%>"/>
             <%} else if(searchColumnVo.getShowType()== SearchShowTypeDef.SELECT.getKey()){%>
            <select id="<%=searchColumnVo.getColumnId()%>" name="<%=searchColumnVo.getColumnId()%>">
                <option value="">-------</option>
                <%
                    List<CommonSearchResult> commonSearchResultList=  searchColumnVo.getCommonSearchResultList();
                    for(CommonSearchResult commonSearchResult:commonSearchResultList)
                    {
                %>
                     <option value="<%=commonSearchResult.getKey()%>"><%=commonSearchResult.getLabel()%></option>
                <%}%>
            </select>
            <%}
            %>
        </div>
        <%}%>
            </div>
        <%}%>
        <div class="form-group">
            <button  type="button" class="btn btn-primary" onclick="return false" id="searchBtn">查找</button>
        </div>
    </form>
</body>
<script type="text/javascript">
    $.datetimepicker.setLocale('en');
    <%
        for(SearchFormRow searchFormRow:searchFormRowList)
        {
            List<SearchColumnVo> searchColumnVoList= searchFormRow.getSearchColumnVoList();
            for(SearchColumnVo searchColumnVo:searchColumnVoList)
        {
          if(searchColumnVo.getShowType()== SearchShowTypeDef.DATEPICKER.getKey()){
        %>
        $('#<%=searchColumnVo.getColumnId()%>').datetimepicker({
            dayOfWeekStart : 1,
            lang:'en',
            timepicker:false,
            format:"Y-m-d"
        });

        <%} else if(searchColumnVo.getShowType()== SearchShowTypeDef.DATETIMEPICKER.getKey()){%>

        $('#<%=searchColumnVo.getColumnId()%>').datetimepicker({
            dayOfWeekStart : 1,
            lang:'en',
            format:"Y-m-d H:i:s",step:10
        });
        <%}
        }
        }%>
        $("#searchBtn").click(function()
        {
            var array=<%=request.getAttribute("searchColumnJson")%>;
            for(var i=0;i<array.length;i++)
            {
                var oneColumn=array[i];
                delete array[i].relOpearList;
                delete array[i].commonSearchResultList;
                oneColumn.value=$("#"+oneColumn.columnId).val();
                if(oneColumn.dataType==2)
                {
                    if(!isInt( oneColumn.value))
                    {
                        bootbox.alert(oneColumn.columnName+"必须为整数");
                        return false;
                    }
                }
                else if(oneColumn.dataType==3)
                {
                    if(!isFloat( oneColumn.value))
                    {
                        bootbox.alert(oneColumn.columnName+"必须为数字");
                        return false;
                    }
                }
             }

            console.log(JSON.stringify(array));
            $.ajax( {
                type:"POST",
                dataType: "json",
                contentType:"application/json",
                data:JSON.stringify(array),
                url : "<c:url value="/commons/search2.do"/>",
                success : function(result) {
                    if(result.success)
                    {
                        window.parent.search();
                    }
                    else {
                        bootbox.alert(result.message);
                    }

                }
            });
        });
</script>
</html>
