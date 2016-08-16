<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.effect.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
 
<title>
	<delmar:message key="datadict.title" />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body >

<s:form action="datadict_list" namespace="/base"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="datadict.location" /></td>
          <td class="navig" align="right"> 
          <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <s:submit method="create" cssClass="input_submit" value="%{#session.resource.get('common.button.create')}" ></s:submit>
                  <s:submit method="deletes" cssClass="input_submit"   value="%{#session.resource.get('common.button.delete')}"  onclick="return confirmListDelete('ids')"></s:submit>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 
      
<div id="filterpage">
        <div class="d-filterheader">
            <div id="filterpageheader" style="cursor:pointer; width:90%">
                <img id="filterpagetoggleimage" class="toggle" alt="toggler"
                     src="../images/core/icon_expand_l.gif"/>
                <delmar:message key="public.list.filter"/>
            </div>
        </div>
        
        <div id="filterForm" >
            <s:hidden id="queryStatus" value="%{#session.MAP_KEY_OF_SESSION.queryStatus}"/>
			<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px">
		       <tr>
		       <td class="d-tdlabel">      	
			     <delmar:message key="datadictType.column.name" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       <s:select list="datadictTypeList" listKey="id" listValue="name"  value="%{#session.MAP_KEY_OF_SESSION.datadictTypeId}" name="datadictTypeId" onchange="javascript:changeDataType()"></s:select>
			   </td>

			       <td  style="text-align:right; padding:5px;">     
			       <s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
             

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.datadictList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
				
		</display:column>    
		<display:column titleKey="common.label.sequence" style="width:20px" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="datadict.column.value" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.value}"/>
     		  </a>
		     </display:column>
		     <display:column property="value"  escapeXml="true"  titleKey="datadict.column.value" media="csv excel xml pdf rtf"	></display:column>	
		     <display:column property="id"  titleKey="datadict.column.name" sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.DatadictDecorator" />
			 <display:column property="datadictTypeId"  titleKey="datadict.column.type" sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.DatadictTypeDecorator" />		
			 <display:column  escapeXml="false" titleKey="datadict.column.indexorder"  sortable="true">
			   <c:out value="${list.indexOrder}"/>
			   <img id="upimage" class="toggle" alt="toggler"
                     src="../images/arrow_up_16.png" onclick="moveclick('<c:out value='${list.id}'/>','up')"/>
			   <img id="downimage" class="toggle" alt="toggler"
                     src="../images/arrow_down_16.png" onclick="moveclick('<c:out value='${list.id}'/>','down')"/>
                     
			 </display:column>
		     <display:column property="remark"  escapeXml="true" titleKey="datadict.column.remark"  sortable="true"/>
		</display:table>
</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/base/datadict_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
    
    
    
    function changeDataType()
    {
    	document.forms[0].submit();
    }
    
    function moveclick(id,mode)
    {

    
  	  $.ajax({  
            url:'datadict_indexOrder.action?id='+id+"&mode="+mode,  
            type:'post',  
            success:function (data) {  	        	 
          	  window.location.reload();	        
            }  
        });  
    }
   
</script>

</body>
</html>
