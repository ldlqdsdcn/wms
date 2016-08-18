<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript" src="<c:url value="/js/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.5.custom.min.js"/>"></script>
<link rel="Stylesheet" href="../css/smoothness/jquery-ui-1.8.5.custom.css" type="text/css" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.effect.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 
</head>

<body >

<s:form action="table_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="table.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <s:submit cssClass="input_submit" value="%{#session.resource.get('common.button.search')}"></s:submit>
                  
                  
                  <input type="button" value="<delmar:message key="common.button.create" />" class="input_submit" onclick="viewExport(0)">
					<input type="button" value="创建表向导" class="input_submit" onclick="viewExport(1)">
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}" onclick="return confirmListDelete('ids')"></s:submit>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.tableList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" media="html" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>">
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</display:column>    
		<display:column titleKey="common.label.sequence"  media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<display:column   titleKey="table.column.name"  sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
     				 <c:out value="${list.name}"/></a>
		     </display:column>
		     <display:column property="name" media="excel,rtf,pdf,csv"
							  titleKey="table.column.name"  />
							<display:column property="tableNameTr" media="excel,rtf,pdf,csv"
							titleKey="table.column.desc"/>
		     <display:column property="className"  escapeXml="true"  sortable="true"
							titleKey="table.column.classname" />
			<display:column property="outLog"  escapeXml="true"  sortable="true"
							titleKey="table.lable.islog" />
		</display:table>




</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
    if(id==0)
    {
    	window.location='<c:url value="/core/table_edit.action"/>';
    }
    else if(id==1)
	{
		window.location='<c:url value="/core/table_saveTableByWizard.action"/>';
	}
    else
    {
       window.location='<c:url value="/core/table_edit.action"/>?id='+id;
    }
    }
    highlightTableRows("list");
    
   
</script>

</body>
</html>
