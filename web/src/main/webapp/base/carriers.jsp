<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/>"></script>
	<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
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
				openDialog('base_carrier');
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

<s:form action="carrier_list" namespace="/base"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="carrier.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                
                  <input type="button"  value="<delmar:message key="common.button.search" />"  class="input_submit" id="search_but">
                  
                  <s:submit method="create" cssClass="input_submit" value="%{#session.resource.get('common.button.create')}" />
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}"/>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.carrierList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
				
		</display:column>    
		<display:column 
			titleKey="common.label.sequence"
		media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   	titleKey="carrier.column.code" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.code}"/>
     		  </a>
		     </display:column>
		     <display:column property="code"    media="csv excel xml pdf rtf"	titleKey="carrier.column.code"/>
			<display:column property="scaccode"  escapeXml="true" titleKey="carrier.column.scaccode" sortable="true"/>
			<display:column property="id"  escapeXml="true" titleKey="carrier.column.cname" sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.CarrierDecorator"/>
		    <display:column property="remark"  escapeXml="true"  titleKey="common.label.remark" sortable="true"/>
		</display:table>
</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
       window.location='<s:url namespace="/base" action="carrier_edit"/>?id='+id;
    }
    highlightTableRows("list");
    
   
</script>
<div id="selectDiv">
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0; border:0; padding: 0;" id="selectIframe"></iframe>
</div>
</body>
</html>
