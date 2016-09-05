<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="delmar" uri="http://www.delmarcargo.cn" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2016/9/5
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(document).ready(function() {
        $("#selectDiv").dialog({
            autoOpen: false,
            height: 500,
            width: 700,
            modal: true,
            title:'位置：查询条件',
            resizable:false});
        highlightTableRows("list");

        $('#search_but').click(function()
        {
            openDialog('${param.search_name}');
        });
        highlightTableRows("list");
    });
    function openDialog(url)
    {
        document.getElementById('selectIframe').src='<c:url value='/commons/searchPage.do'/>?action_value='+url;
        $('#selectDiv').dialog('open');
    }
    function closeDialog()
    {
        $("#selectDiv").dialog('close');
    }
    function search()
    {
        closeDialog();
        document.forms[0].submit();
    }
    function viewExport(id) {
        window.location='<c:url value="${param.edit_url}"/>?id='+id;
    }
    function confirmListDelete()
    {
        if(isEmptyCheckBox('ids'))
        {
            alert('请先选择记录再删除');
            return false;
        }
        return confirm("<delmar:message key="org.message.confirmdelete" />");
    }

</script>