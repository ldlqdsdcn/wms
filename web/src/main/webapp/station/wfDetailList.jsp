<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>

<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>


<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/multiselect/src/jquery.multiselect.js"></script>
<link rel="Stylesheet" href="../js/jquery/plugin/multiselect/jquery.multiselect.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/plugin/multiselect/i18n/jquery.multiselect.<s:property value='#session.currentlanguagelowercase'/>.js"></script>

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>



<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkStationRecordDwr.js'></script>
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
	   	       $("#statusId").val($("#status").val());
		        if (document.all.userOrgs) {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }	   	       
	
	 		 });	
	  
    	jQuery('#flightDateStart').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",    		
            buttonImage: "../images/DatePicker.GIF",
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,
            onClose:function(selectedDate) {
               $("#flightDateEnd").datepicker("option","minDate",selectedDate);	
            }
    	    });
    	
    	jQuery('#flightDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",
            buttonImage: "../images/DatePicker.GIF",
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,            
            onClose:function(selectedDate) {
                $("#flightDateStart").datepicker("option","maxDate",selectedDate);	
             }
     	    });
    	
    	jQuery("#status").multiselect({
    		
    		initValue:$("#statusId").val(),
    		minWidth:180
    	});
    	
   		var varray=$("#statusId").val().split(",");
   		for (var i in varray){
   	      $("#status").find("option[value='"+varray[i]+"']").attr("selected",true);
   		}
        
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
	});
	
function detailUpdate (id) {
	window.location='<c:url value="/station/wfDetail_initDetail.action"/>?id=' + id + "&updateFlag=wfDetailUpdate";
}

function detailQuery (id) {
	window.location='<c:url value="/station/wfDetail_initDetail.action"/>?id='+id;
}
	
</script>
</head>

<body >


<s:form action="wfDetail_list" namespace="/station"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
       <tr> 
         <td align="left" class="navig"><delmar:message key="warehouseForwarder.location" /></td>
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
			<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px">
		       <tr>
		       		<td class="d-tdlabel">      	
			     		<delmar:message key="warehouseForwarder.column.warehouseNo" /> 
			   		</td>
			   		<td   class="d-tdinput"> 
			       		<s:textfield name="warehouseNo" value="%{#session.MAP_KEY_OF_SESSION.warehouseNo}" class="d-inputtext"></s:textfield>
			   		</td>
				   	<td  class="d-tdlabel" >
				       	<delmar:message key="warehouseForwarder.column.airPortTo" />
				   </td>
				   <td class="d-tdinput" > 
				       	<s:textfield name="airPortTo" value="%{#session.MAP_KEY_OF_SESSION.airPortTo}"></s:textfield>
				   </td>
			     
			       <td class="d-tdlabel" > 	
		       			<delmar:message key="warehouseForwarder.column.status" />
		      		</td>
		     		<td class="d-tdinput" > 
			    		<s:select list="stationStatusList" listKey="value" listValue="name"  name="status"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="status" ></s:select>
			    		<s:hidden name="statusId" id="statusId" value="%{#session.MAP_KEY_OF_SESSION.statusId}" />
		      		</td>	
		       					      
			      	<td class="d-tdinput"> 	</td>
			    </tr>
			    <tr>
		       		
		       		<td  class="d-tdlabel">
			       		<delmar:message key="warehouseForwarder.column.flightDate" /> 
			    	</td>
			     	<td class="d-tdinput">  	
			       		<s:textfield  id="flightDateStart"  readonly="true"  name="flightDateStart" value="%{#session.MAP_KEY_OF_SESSION.flightDateStart}" ></s:textfield>
			   			<delmar:message key="warehouseForwarder.message.to" /> 
			        	<s:textfield  id="flightDateEnd"  readonly="true"  name="flightDateEnd" value="%{#session.MAP_KEY_OF_SESSION.flightDateEnd}" ></s:textfield> 
			      	</td>	
			      	<s:if test="stationEmpty">
				      	 <td class="d-tdlabel"> 	
					       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
					    		<delmar:message key="customer.column.org" />
					    	</c:if>
					      </td>
					      <td class="d-tdinput" > 
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
						    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs" ></s:select>
					       	</c:if>
					       	
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
						    	<delmar:message key="public.accessprivilege.org"/>
					       	</c:if>
					       			       	
					       	<input name="orgIds" id="orgIds" value="<s:property value="%{#session.MAP_KEY_OF_SESSION.orgIds}"/>" type="hidden"/>
					      </td>		
				      </s:if>			      
			      	<td class="d-tdinput"> 	</td>
			    </tr>
		       <tr>
			       <td colspan=8 style="text-align:left; padding:5px;">     
			       		<s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
	                  <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
		       </tr>
			</table>        
        </div>

 </div>
    


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.stationList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >
		    
	    <display:column   titleKey="warehouseForwarder.column.warehouseNo" sortable="true" media="html">
    	 	<c:if test="${list.lockDate >= list.nowDate}">
    	 		<a id='customers<c:out value="${list.id}"/>' href="javascript:detailUpdate('<c:out value="${list.id}"/>')"><c:out value="${list.warehouseNo}"/></a>
    	 	</c:if>
	      	<c:if test="${!(list.lockDate >= list.nowDate)}">
	      		 <a id='customers<c:out value="${list.id}"/>' href="javascript:detailQuery('<c:out value="${list.id}"/>')"><c:out value="${list.warehouseNo}"/></a>
	      	</c:if>
		</display:column>
   		<display:column  property="referenceNo" escapeXml="true" titleKey="wfDetail.column.referenceNo" sortable="true" ></display:column>
    	<display:column  property="inDate" escapeXml="true" titleKey="wfDetail.column.inDate" sortable="true" ></display:column>
    	<display:column  property="goodsNumber" escapeXml="true" titleKey="warehouseForwarder.column.goodsNumber" sortable="true"></display:column>
    	<display:column  property="goodsWeight" escapeXml="true" titleKey="warehouseForwarder.column.goodsWeight" sortable="true"></display:column>
    	<display:column  property="goodsSize" escapeXml="true" titleKey="warehouseForwarder.column.goodsSize" sortable="true"></display:column>
    	<display:column  property="sizeDescription" escapeXml="true" titleKey="wfDetail.column.sizeDescription" sortable="true" style="width:200px;"></display:column>
    	<display:column  property="cargoRemark" escapeXml="true" titleKey="wfDetail.column.cargoRemark" sortable="true" style="width:150px;"></display:column>
    	<display:column  property="receiptPerson" escapeXml="true" titleKey="wfDetail.column.receiptPerson" sortable="true" ></display:column>
    	<display:column  property="carDriver" escapeXml="true" titleKey="wfDetail.column.carDriver" sortable="true" ></display:column>
    	<display:column  property="totalAMount" escapeXml="true" titleKey="wfDetail.column.totalAMount" sortable="true"></display:column>
    	
    	 	<display:column   titleKey="warehouseForwarder.column.operation" sortable="true" media="html">
	    	 	<c:if test="${list.lockDate >= list.nowDate}">
	    	 		<a id='customer<c:out value="${list.id}"/>' href="javascript:detailUpdate('<c:out value="${list.id}"/>')"><delmar:message key="common.label.edit" /></a>
	    	 	</c:if>
		      	<c:if test="${!(list.lockDate >= list.nowDate)}">
		      		 <a id='customer<c:out value="${list.id}"/>' href="javascript:detailQuery('<c:out value="${list.id}"/>')"><delmar:message key="common.button.view" /></a>
		      	</c:if>
			</display:column>
		</display:table>

</div>
</s:form>

<div id="recordList" >
  <div id="closebtn"><a href="#" onclick="return $('#recordList').attr('style','display:none');">Close</a></div>
  <div id="persongrid" style="margin: 5px; padding: 0">
  </div>
</div>





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
    
    if ($("#queryStatus").val()=="true")  
    {
    	$('#filterForm').removeClass("d-hidden");
    	$('#filterForm').addClass("d-visible");
        $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
    }
    
</script>

</body>
</html>
