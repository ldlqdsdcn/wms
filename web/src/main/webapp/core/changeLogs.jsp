<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.5.custom.min.js"/>"></script>
<link rel="Stylesheet" href="../css/smoothness/jquery-ui-1.8.5.custom.css" type="text/css" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
  <script type='text/javascript' src='../js/ea.validate.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
			openDialog('/core/changeLog_list.action');
		});
	});
	
	function openDialog(url)
	{
		document.getElementById('selectIframe').src='<c:url value='/commons/commonSearch_open.action'/>?pageUrl='+url;
		$('#selectDiv').dialog('open');
		
		
	}
	function closeDialog()
 	{
 	
 		
 		$("#selectDiv").dialog('close');
 
 	}
</script>

</head>

<body >

<s:form action="changeLog_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
          <delmar:message key="changelog.location" />
          </td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <input type="button"  value="<delmar:message key="common.button.search" />" id="search_but"  class="input_submit"/>
                </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.changeLogList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="changelog.column.tablename" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
     				 <c:out value="${list.table.name}"/></a>
		     </display:column>
		     <display:column property="table.tableNameTr"  escapeXml="true"  titleKey="changelog.column.busname"  sortable="true" />
		     
		     <display:column property="pk"  escapeXml="true"   titleKey="changelog.column.key"  sortable="true" />
		     <display:column property="buPk" escapeXml="true"  titleKey="changelog.column.buskey" />
		     <display:column property="operateType" escapeXml="true"  titleKey="changelog.column.action" 
		      decorator="com.delmar.core.web.displaytag.decorator.ChangeLogOperateTypeDecorator"/>
			 <display:column property="context"  escapeXml="true"  titleKey="changelog.column.content" />
			 <display:column property="username" sortable="true"  titleKey="changelog.column.username"/>				
			<display:column property="created"  escapeXml="true"  titleKey="changelog.column.operatedate" sortable="true"
		     decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		</display:table>




</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
     window.location='<c:url value="/core/changeLog_view.action"/>?id='+id;
     
    }
   
</script>
  <div id="selectDiv">
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0; border:0; padding: 0;" id="selectIframe"></iframe>
</div>
</body>
</html>
