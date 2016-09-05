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
		 $("#itemId").val($("#item").val());
	       if (document.all.userOrgs) {
	        	$("#orgIds").val($("#userOrgs").val());		        	
	        }	
		 });
	jQuery("#item").multiselect({
		
		initValue:$("#itemId").val(),
		minWidth:180
	});
	
	if (document.all.userOrgs){
    	jQuery("#userOrgs").multiselect({
    	    		initValue:$("#orgIds").val(),
    	    		minWidth:180
    	    	});
    	    	
    	      	
 	    	var varray=$("#orgIds").val().split(",");
 	    	for (var i in varray){
 	          $("#userOrgs").find("option[value='"+varray[i]+"']").attr("selected",true);
 	    	}
	}	
	
	var varray=$("#itemId").val().split(",");
	for (var i in varray){
   		$("#item").find("option[value='"+varray[i]+"']").attr("selected",true);
	}
	  
});


function edit(id) {
	window.location='<c:url value="/sysSettings/sysSettingsAction_edit.action"/>?id='+id;
}

function save(id, setType) {
	
	if (setType == 0 || setType == 1) {
		var setValueString = document.getElementById("setValueString_" + id).value;
		if (setType == 0) {
			if(!(/\d+(\.\d+)?/g.test(setValueString))) {
				alert("<delmar:message key="sysSettingsItem.message.setValueString"/>");
				return;
			}
		}
		
		if (setValueString == "" || setValueString == null) {
			alert("<delmar:message key="sysSettingsItem.message.setValueStringEmpty"/>");
			return;
		}
		
		var url = "<%=request.getContextPath()%>/sysSettings/sysSettingsAction_ajaxUpdate.action?id=" + id 
				+ "&setValueString=" + setValueString
				+ "&setType=" + setType;
		
		ajaxSave(url);
	} 
	if (setType == 2 || setType == 3) {
		var itemValueIds = $("#sysSettingsValuesList_" + id).val();
		
		if (itemValueIds == "" || itemValueIds == null) {
			alert("<delmar:message key="sysSettingsItem.message.setValueStringEmpty"/>");
			return;
		}
		var url = "<%=request.getContextPath()%>/sysSettings/sysSettingsAction_ajaxUpdateSelect.action?itemValueIds=" + itemValueIds
				+ "&settingsId=" + id;
		ajaxSave(url);
	}
}

function ajaxSave(url) {
	$.ajax({
        type: "post",
        url: url,
        dataType: "json",
        success: function (data) {
        	alert("<delmar:message key="sysSettingsItem.message.successed"/>");
        	window.location.reload();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
   });
}

function changeMultiselect(id) {
	
	document.getElementById("itemValueIds_" + id).value = $("#sysSettingsValuesList_" + id).val();
}

function changeSingelSelect(id) {
	
	document.getElementById("itemValueIds_" + id).value = $("#sysSettingsValuesList_" + id).val();
}


	
</script>
</head>

<body >


<s:form action="sysSettingsAction_list" namespace="/sysSettings"  theme="simple" >
	<div style="margin:5px">
	
		<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="sysSettingsItem.title.lacation"/></td>
	         <td class="navig" align="right"> 
	         	<table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
	              <tr> 
	                <td >
	               
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
					   		<delmar:message key="sysSettingsItem.column.sysSettings"/>:
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:select  list="itemList" listKey="datadictId" listValue="name" name="item" id="item" 
			 					value="%{itemId}"	style="height:24px;width:120px" multiple="true"></s:select>
			 				<s:hidden name="itemId" id="itemId" />	
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="sysSettingsItem.column.setValueString"/>:
					   </td>
					   <td class="d-tdinput"> 
					       	<s:textfield name="setValueString" id="setValueString" cssClass="d-searchtext"/>
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="sysSettingsItem.column.customerName"/>:
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:textfield name="userName" id="userName" cssClass="d-searchtext"/>
					   </td>
					  <td class="d-tdlabel"> 	
					       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
					    		<delmar:message key="customer.column.org" />:
					    	</c:if>
				      	</td>
				      	<td class="d-tdinput" > 
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
						    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs" ></s:select>
					       	</c:if>
					       	
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
						    	<delmar:message key="public.accessprivilege.org"/>
					       	</c:if>
					       			       	
					       	<s:hidden name="orgIds" id="orgIds"/>
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
	    
	  
	 <display:table name="sysSettingsList" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		<delmar:operatePriv operator="delete">
			<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
				<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
			</display:column>
		</delmar:operatePriv>    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" >
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	
    	<display:column  property="sysSettingsItem.setType" escapeXml="true" titleKey="sysSettingsItem.column.inputType" 
    		decorator="com.delmar.sysSettings.web.displaytag.decorator.SettingsInputDecorator"sortable="true"/>
    	<display:column property="id" titleKey="sysSettingsItem.column.setValueString"  sortable="true" style="width:200px;"
    	decorator="com.delmar.sysSettings.web.displaytag.decorator.SetValueStringDecorator">
    		
    	</display:column>
    	<display:column  property="itemId" escapeXml="true" titleKey="sysSettingsItem.column.sysSettingsSelect"  sortable="true"
    		decorator="com.delmar.sysSettings.web.displaytag.decorator.SettingsItemDecorator"/>
    	<display:column  property="userName" escapeXml="true" titleKey="sysSettingsItem.column.person"  sortable="true" />
    	<display:column  property="indexOrder" escapeXml="true" titleKey="sysSettingsItem.column.indexOrder" sortable="true"/>
    	<display:column  titleKey="sysSettingsItem.button.option"  sortable="true" >
    		<a href='javascript:void(0)' onclick="save('<c:out value="${list.id}"/>', <c:out value="${list.sysSettingsItem.setType}"/>)"><delmar:message key="sysSettingsItem.button.save"/></a>
    	</display:column>
    	
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
