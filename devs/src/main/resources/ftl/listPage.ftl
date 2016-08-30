<%--
  Created by IntelliJ IDEA.
  User: ${user}
  Date: ${datetime}
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
                openDialog('${module}_${mode}');
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
<s:form action="${mode}_list" namespace="${namespace}"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：${title}</td>
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
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.${mode? uncap_first}List" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html">
          		<input type="checkbox" name="ids" value="<c:out value='${r'${list.id}'}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${r'${list_rowNum}'}"/>
        </display:column>
        <#list propertyList as prop>
        <#if prop_index==0>
        <display:column   title="${prop.label}" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${r'${list.id}'}"/>')"><c:out value=" ${r'${'}list.${prop.prop}${r'}'}"/></a>
        </display:column>
        <display:column property="${prop.prop}" media="csv excel xml pdf rtf"	title="${prop.label}" />
        <#else>
        <display:column property="${prop.prop}"  escapeXml="true" title="${prop.label}" sortable="true"
        
                <#if prop.decoratorType==1>
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                </#if>
                <#if prop.decoratorType==2> decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                </#if>
                        <#if prop.decoratorType==3>decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                        </#if>
                        <#if prop.decoratorType==4>decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"
                        </#if>
                        <#if prop.decoratorType==5>decorator="com.delmar.system.web.displaytag.decorator.ClientDecorator"
                        </#if>
            />
        </#if>


        </#list>
    </display:table>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="${namespace}/${mode? uncap_first}_edit.action"/>?id='+id;
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
