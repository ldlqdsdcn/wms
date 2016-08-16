<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
<title><delmar:message key="datadictType.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body >

<s:form action="datadicttype_list" namespace="/base"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="datadictType.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                
                  <input type="button"  value="<delmar:message key="common.button.search" />"  class="input_submit">
                  
                  <s:submit method="create" cssClass="input_submit" value="%{#session.resource.get('common.button.create')}" ></s:submit>
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}" ></s:submit>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.datadictTypeList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
				
		</display:column>    
		<display:column titleKey="common.label.sequence"  media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="datadictType.column.value" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.value}"/>
     		  </a>
		     </display:column>
		    <display:column property="value" titleKey="datadictType.column.value"  media="csv excel xml pdf rtf"	></display:column>
		    <display:column property="name" titleKey="datadictType.column.name"   escapeXml="true"  sortable="true"/>
		    <display:column titleKey="datadictType.column.bepublic"   escapeXml="true"  sortable="true">
		    <c:if test="${list.bePublic==0}">
		       <delmar:message key="datadictType.column.bepublic"/>
		    </c:if>
		    <c:if test="${list.bePublic==1}">
		       <delmar:message key="datadictType.column.bepublicnot"/>
		    </c:if>
		    </display:column>

			<display:column property="remark"  escapeXml="true"  titleKey="datadictType.column.remark" sortable="true"/>
		</display:table>
</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/base/datadicttype_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
    
   
</script>

</body>
</html>
