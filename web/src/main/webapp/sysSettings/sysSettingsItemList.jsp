<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/plugin/multiselect/jquery.multiselect.css" type="text/css" />
<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript" src="../js/jquery/plugin/multiselect/src/jquery.multiselect.js"></script>
<script type="text/javascript" src="../js/jquery/plugin/multiselect/i18n/jquery.multiselect.<s:property value='#session.currentlanguagelowercase'/>.js"></script>
<script  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></script>
<script type='text/javascript' src='../dwr/interface/linkStationRecordDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkBankCategoryDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>
<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}


#recordList{
	position:absolute;
	width:800px;
	top:15px;
	background-color:lightyellow;
	border:3px solid #7C96E2;
	padding:0px;
	display:none;
}

</style>



<script type="text/javascript">

$(document).ready(function() {	
	
	$("#btnQuery").click(function(event) {
	       $("#setLevelId").val($("#setLevel").val());
	       $("#setTypeId").val($("#setType").val());
		 });
	jQuery("#setLevel").multiselect({
		
		initValue:$("#setLevelId").val(),
		minWidth:180
	});
	
	jQuery("#setType").multiselect({
		
		initValue:$("#setTypeId").val(),
		minWidth:180
	});
	
	var varray=$("#setLevelId").val().split(",");
	for (var i in varray) {
      $("#setLevel").find("option[value='" + varray[i] + "']").attr("selected",true);
	}
	
	varray = $("#setTypeId").val().split(",");
	for (var i in varray) {
      $("#setType").find("option[value='" + varray[i] + "']").attr("selected",true);
	}
	  
});


function edit(id) {
	window.location='<c:url value="/sysSettings/sysSettingsItemAction_edit.action"/>?id='+id;
}

function validate(ids) {
	
	if (confirmListDelete(ids)) {
	    window.location.reload();	        
	}
	
}
	
</script>
</head>

<body >


<s:form action="sysSettingsItemAction_list" namespace="/sysSettings"  theme="simple" >
	<div style="margin:5px">
	
		<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="sysSettingsItem.title.lacation"/></td>
	         <td class="navig" align="right"> 
	         	<table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
	              <tr> 
	                <td >
	                <delmar:operatePriv operator="create">
	                  <s:submit method="create" cssClass="input_submit"  value="%{#session.resource.get('common.button.create')}" />
	                 </delmar:operatePriv>
	                  <delmar:operatePriv operator="delete">
	                  <s:submit method="deletes" cssClass="input_submit"  value="%{#session.resource.get('common.button.delete')}"   onclick="return confirmListDelete('ids')"/>
	                  </delmar:operatePriv>
	                 </td>
	              </tr>
	            </table>
          	</td>
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
	        
	        <div id="filterForm" class="d-hidden">
	            <s:hidden id="queryStatus" value="%{#session.MAP_KEY_OF_SESSION.queryStatus}"/>
				<table border="0" cellpadding="0" cellspacing="0"  class="d-filterTable" style="padding-top:5px;padding-bottom:5px">
			       <tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="sysSettingsItem.column.value"/>:
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:textfield name="value" id="value" cssClass="d-searchtext"/>
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="sysSettingsItem.column.name"/>:
					   </td>
					   <td class="d-tdinput"> 
					       	<s:textfield name="name" id="name" cssClass="d-searchtext"/>
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="sysSettingsItem.column.level"/>：
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select  list="accessList" listKey="datadictId" listValue="name" name="setLevel" id="setLevel" 
			 					value="%{setLevel}"	style="height:24px;width:120px" multiple="true"></s:select>
			 				<s:hidden name="setLevelId" id="setLevelId" />	
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="sysSettingsItem.column.inputType"/>:
					   </td>
					   <td class="d-tdinput">
					   		<s:select  list="inputTypeList" listKey="value" listValue="name" name="setType" id="setType" 
			 					value="%{setType}"	style="height:24px;width:120px" multiple="true"></s:select>
			 				<s:hidden name="setTypeId" id="setTypeId" />
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestBank.column.remark" />：
					   </td>
					   <td   class="d-tdinput" colspan="3"> 
					   		<s:textfield name="remark" id="remark" cssClass="d-searchtext"/>
					   </td>
					   
					</tr>
					
					<tr>
				       <td colspan=8  class="d-searchbutton">  
				       		<s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" />
		                  	<s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
				       </td>    
			       </tr>
				</table>        
			</div>
		</div>
	    
	 <display:table name="sysSettingsItemList" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		<delmar:operatePriv operator="delete">
			<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
				<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
			</display:column>
		</delmar:operatePriv>    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	
    	<display:column  titleKey="sysSettingsItem.column.value"  sortable="true" >
    		<a href='javascript:void(0)' onclick="edit('<c:out value="${list.id}"/>')"><c:out value="${list.value}"/></a>
    	</display:column>
    	<display:column  property="name" escapeXml="true" titleKey="sysSettingsItem.column.name"  sortable="true"/>
    	<display:column  property="setLevel" escapeXml="true" titleKey="sysSettingsItem.column.level"  sortable="true" 
    	decorator="com.delmar.sysSettings.web.displaytag.decorator.SettingsLevelDecorator"/>
    	<display:column  property="setType" escapeXml="true" titleKey="sysSettingsItem.column.inputType"  sortable="true" 
    	decorator="com.delmar.sysSettings.web.displaytag.decorator.SettingsInputDecorator"/>
    	<display:column  property="indexOrder" escapeXml="true" titleKey="sysSettingsItem.column.indexOrder" sortable="true"/>
    	<display:column  property="remark" escapeXml="true" titleKey="sysSettingsItem.column.remark" sortable="true"/>
    	
	  </display:table>
	</div>
</s:form>


<script type="text/javascript">


highlightTableRows("list");


if ($('#filterpageheader')) {
    if (typeof(Event) == "undefined") {
    } else {
    	$('#filterpageheader').bind('click', function() {
            $('#filterForm').toggle();
            if ($('#filterForm').hasClass("d-visible")) {
            	$('#filterForm').removeClass("d-visible");
            	$('#filterForm').addClass("d-hidden");
                $('#filterpagetoggleimage').attr('src','../images/core/icon_collapse_l.gif');
            } else {
            	
            	$('#filterForm').removeClass("d-hidden");
            	$('#filterForm').addClass("d-visible");
                $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
            }
        }) ;
    }
}
if ($("#queryStatus").val()=="true")  {
	$('#filterForm').removeClass("d-hidden");
	$('#filterForm').addClass("d-visible");
    $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
}


</script>

</body>
</html>
