<%@page import="java.util.Locale"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
  <script type="text/javascript" src="<c:url value="/js/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.5.custom.min.js"/>"></script>
<link rel="Stylesheet" href="../css/smoothness/jquery-ui-1.8.5.custom.css" type="text/css" />

<script type="text/javascript">
 $(document).ready(function() {
	$("#selectDiv").dialog({
			autoOpen: false,
			height: 500,
			width: 700,
			modal: true,
			title:'<fmt:message key="common.search.title"></fmt:message>',
			resizable:false});
			highlightTableRows("list");
			$('#search_but').click(function()
		{
			openDialog('/system/module_list.action');
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body >

<s:form action="module_list" namespace="/system"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><fmt:message key="module.title"></fmt:message></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                
                  <input type="button"  value='<delmar:message key="common.button.search" />'  id="search_but"   class="input_submit"/>
                  <input type="button" value='<delmar:message key="common.button.create" />'  class="input_submit"  onclick="viewExport(0)"/>
				  <s:submit method="delete" cssClass="input_submit"   value=" %{#session.resource.get('common.button.delete')}"/>

                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.moduleList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</display:column>    
		<display:column titleKey="common.label.sequence"  media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="module.column.name" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
     				 <c:out value="${list.name}"/></a>
		     </display:column>
			<display:column property="dataFilter"  escapeXml="true"  titleKey="module.column.datafilter" sortable="true"/>
		    <display:column property="remark"  escapeXml="true" titleKey="module.column.remark" sortable="true"/>
		    

		</display:table>




</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
    if(id==0)
    window.location='<c:url value="/system/module_edit.action"/>';
    else
   
       window.location='<c:url value="/system/module_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
    
   
</script>
  <div id="selectDiv">
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>
</body>
</html>
