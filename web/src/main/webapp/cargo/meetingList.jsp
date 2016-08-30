<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-30 17:40:14
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="Stylesheet" href="../css/displaytag.css" type="text/css"/>
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/>"></script>
    <link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
    <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8"/>
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
                openDialog('cargo_meeting');
            });
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
    </script>
</head>
<body >
<s:form action="meeting_list" namespace="/cargo"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：会议纪要</td>
            <td class="navig" align="right">
                <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td>
                            <input type="button" value="查询" class="input_submit" id="search_but">
                            <s:submit method="create" cssClass="input_submit" value="新建"></s:submit>
                            <s:submit method="deletes" cssClass="input_submit" value="删除" onclick="return confirmListDelete()"></s:submit>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.meetingList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html">
          		<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${list_rowNum}"/>
        </display:column>
        <display:column   title="标题" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value=" ${list.title}"/></a>
        </display:column>
        <display:column property="title" media="csv excel xml pdf rtf"	title="标题" />


        <display:column property="bgnTime"  escapeXml="true" title="开始时间" sortable="true"
        
                 decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                                                                                    />


        <display:column property="endTime"  escapeXml="true" title="介绍时间" sortable="true"
        
                 decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                                                                                    />


        <display:column property="descr"  escapeXml="true" title="描述" sortable="true"
        
                                                                                                    />


        <display:column property="created"  escapeXml="true" title="创建时间" sortable="true"
        
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                                                                                                    />


        <display:column property="createdby"  escapeXml="true" title="创建人" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="updated"  escapeXml="true" title="修改时间" sortable="true"
        
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                                                                                                    />


        <display:column property="updatedby"  escapeXml="true" title="修改人" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="mainContent"  escapeXml="true" title="mainContent" sortable="true"
        
                                                                                                    />


        <display:column property="userId"  escapeXml="true" title="userId" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="orgId"  escapeXml="true" title="组织" sortable="true"
        
                                                                decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"
                                    />


        <display:column property="clientId"  escapeXml="true" title="实体" sortable="true"
        
                                                                                        decorator="com.delmar.system.web.displaytag.decorator.ClientDecorator"
            />


    </display:table>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/cargo/meeting_edit.action"/>?id='+id;
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
    highlightTableRows("list");
</script>
<div id="selectDiv">
    <iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>
</body>
</html>
