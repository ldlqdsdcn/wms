<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="busforecast.title" /></title>


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


<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>

<script type="text/javascript">

$(document).ready(function() {	
	  $("#btnQuery").click(function(event)
	 		 {
		        $("#modeId").val($("#mode").val());
		        
		        if (document.all.userOrgs)
		        {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }		        
	 		 });	
	  
	  
	  
	 
  	jQuery('#createDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#createDateEnd").datepicker("option","minDate",selectedDate);	
          }
  	    });
  	
  	jQuery('#createDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",
          buttonImage: "../images/DatePicker.GIF",
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,            
          onClose:function(selectedDate) {
              $("#createDateBegin").datepicker("option","maxDate",selectedDate);	
           }
   	    });
  	
  	jQuery('#fromDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#fromDateEnd").datepicker("option","minDate",selectedDate);	
          }
  	    });
  	
  	jQuery('#fromDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",            
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#fromDateBegin").datepicker("option","maxDate",selectedDate);	
          }
  	    });
  	
  	jQuery('#toDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#toDateEnd").datepicker("option","minDate",selectedDate);	
          }
  	    });
  	
  	jQuery('#toDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",            
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#toDateBegin").datepicker("option","maxDate",selectedDate);	
          }
  	    });  	
  	
     
  	jQuery("#mode").multiselect(
  	{
  		initValue:$("#modeId").val(),
  		minWidth:180
  	});
  	
  	
	var varray=$("#modeId").val().split(",");
	for (var i in varray)
	{
      $("#mode").find("option[value='"+varray[i]+"']").attr("selected",true);
	}
		
		
  	

		  	
	
		if (document.all.userOrgs)
		{
	    	jQuery("#userOrgs").multiselect(
	    	    	{
	    	    		initValue:$("#orgIds").val(),
	    	    		minWidth:180
	    	    	});
	    	    	
	    	      	
	 	    	var varray=$("#orgIds").val().split(",");
	 	    	for (var i in varray)
	 	    	{
	 	          $("#userOrgs").find("option[value='"+varray[i]+"']").attr("selected",true);
	 	    	}
		}	
    		


     
	});
	
	
	
$(document).ready(function() {

	 
	 DWREngine.setAsync(false); 

		

				$('#customerName').autocomplete(
						{
							source : function(request, response) {
								var availableTags = [];
								var value = $("#customerName").val();
	
								customerDwr.getCustomerList(value, function(data) {
									if(data!=null)
									for (var i = 0; i < data.length; i++) {
										var vendor = data[i];
										vendor.label=data[i].name;
										vendor.value=data[i].name;
										availableTags.push(vendor);
									}

								});
								
								response(availableTags);
							},
							select : function(event, ui) {
								$("#customerId").val(ui.item.id);
								$("#customerName").val(ui.item.name);

							}
						});
				
				
});

</script>
</head>

<body >

<s:form action="busForecast_list" namespace="/crm"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="busforecast.location" /></td>
          <td class="navig" align="right"> 
          <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                <c:if test="${MAP_KEY_OF_SESSION.fromOthers!='true'}">                
                <delmar:operatePriv operator="create">

                  <s:submit method="create" cssClass="input_submit"  value="%{#session.resource.get('common.button.create')}" ></s:submit>
                 </delmar:operatePriv>
                  <delmar:operatePriv operator="delete">
                  <s:submit method="deletes" cssClass="input_submit"  value="%{#session.resource.get('common.button.delete')}"   onclick="return confirmListDelete('ids')"></s:submit>
                  </delmar:operatePriv>
                </c:if>
                
                <c:if test="${MAP_KEY_OF_SESSION.fromOthers=='true'}">
                    <input type="button" class="input_submit" id="btnReturn" value="<delmar:message key='common.button.back'/>" onclick="javascript:history.go(-1);"/>                
                </c:if>        
                
                 <s:hidden id="fromOthers" value="%{#session.MAP_KEY_OF_SESSION.fromOthers}"/>                
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
			      <delmar:message key="busforecast.column.busforecastid" /> 
			   </td>
			   <td   class="d-tdinput"> 
  		       	  <s:textfield name="busForecastId" id="busForecastId" value="%{#session.MAP_KEY_OF_SESSION.busForecastId}" cssClass="d-searchtext"></s:textfield>
			   </td>
		       <td class="d-tdlabel">      	
			      <delmar:message key="busforecast.column.customerid" /> 
			   </td>
			   <td   class="d-tdinput"> 
  		       	  <s:textfield name="customerName" id="customerName" value="%{#session.MAP_KEY_OF_SESSION.customerName}" cssClass="d-searchtext"></s:textfield>
		       	  <s:hidden name="customerId" id="customerId" value="%{#session.MAP_KEY_OF_SESSION.customerId}"></s:hidden>
			   </td>			   		       
		       <td class="d-tdlabel">      	
			      <delmar:message key="busforecast.column.linkrecordid" /> 
			   </td>
			   <td   class="d-tdinput"> 
  		       	  <s:textfield name="linkRecordId" id="linkRecordId" value="%{#session.MAP_KEY_OF_SESSION.linkRecordId}" cssClass="d-searchtext"></s:textfield>
			   </td>
		      	<td class="d-tdlabel"> 	
		       	<!--To check whether the user have the group privilege  -->
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
		       	
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='group'}">
			    	<delmar:message key="public.accessprivilege.usergroup"/>
			    	<s:checkbox name="beGroup" id="beGroup" fieldValue="true" value="%{#session.MAP_KEY_OF_SESSION.beGroup}" />
			    	
		       	</c:if>		
		       			       	
		       			       	
		       	<s:hidden name="orgIds" id="orgIds" value="%{#session.MAP_KEY_OF_SESSION.orgIds}"></s:hidden>
		      </td>					      
		      		   
			      </tr>
			      <tr>
			      <td class="d-tdlabel"> 	
			       	<delmar:message key="public.column.created" />
			      </td>
			      <td class="d-tdinput" colspan=3> 				       	
			   		<s:textfield  id="createDateBegin"  readonly="true"  name="createDateBegin" value="%{#session.MAP_KEY_OF_SESSION.createDateBegin}" cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="createDateEnd"  readonly="true"  name="createDateEnd" value="%{#session.MAP_KEY_OF_SESSION.createDateEnd}" cssClass="d-searchtext"></s:textfield>   		
			       </td>                        
                    
                   <td class="d-tdlabel"> 	
			      <delmar:message key="busforecast.column.planeocean" />
			      </td>
                   <td class="d-tdinput">
                   <s:select list="modeList" listKey="value" listValue="name"  name="mode" value="%{#session.MAP_KEY_OF_SESSION.modeId}" multiple="true" id="mode"></s:select>
			    	<s:hidden name="modeId" id="modeId" value="%{#session.MAP_KEY_OF_SESSION.modeId}" />                                      
                   </td>
                   <td class="d-tdinput" colspan=2>
                   </td>
			      </tr>
			       <tr>


			       <td class="d-tdlabel">
			       	<delmar:message key="busforecast.column.fromdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="fromDateBegin"  readonly="true"  name="fromDateBegin" value="%{#session.MAP_KEY_OF_SESSION.fromDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="fromDateEnd"  readonly="true"  name="fromDateEnd" value="%{#session.MAP_KEY_OF_SESSION.fromDateEnd}" cssClass="d-searchtext"></s:textfield>
			      </td>
			      
		           <td class="d-tdlabel">
			       	<delmar:message key="busforecast.column.todate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="toDateBegin"  readonly="true"  name="toDateBegin" value="%{#session.MAP_KEY_OF_SESSION.toDateBegin}" cssClass="d-searchtext" ></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="toDateEnd"  readonly="true"  name="toDateEnd" value="%{#session.MAP_KEY_OF_SESSION.toDateEnd}" cssClass="d-searchtext"></s:textfield>
			      </td>			      

			       </tr>
			       <tr>
			       <td colspan=8  class="d-searchbutton">     
			       <s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
 


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.busForecastList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >
		
		<delmar:operatePriv operator="delete">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</display:column>
		</delmar:operatePriv>
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
	     <display:column   titleKey="busforecast.column.busforecastid" sortable="true" media="html" style="width:80px;">
	         <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.busForecastId}"/>
    		  </a>
	     </display:column>
    	<display:column property="customerId"  escapeXml="true"  titleKey="busforecast.column.customerid" sortable="true" decorator="com.delmar.crm.web.displaytag.decorator.CustomerDecorator"></display:column>    	
        <display:column property="planeOcean"  escapeXml="true"  titleKey="busforecast.column.planeocean"  sortable="true"  />
        <display:column property="polId"  escapeXml="true"  titleKey="busforecast.column.polid" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.PortDecorator"></display:column>
    	<display:column property="poaId"  escapeXml="true"  titleKey="busforecast.column.poaid" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.PortDecorator"></display:column>
		<display:column property="fromDate"  escapeXml="true"  titleKey="busforecast.column.fromdate"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"/>	     
		<display:column property="toDate"  escapeXml="true"  titleKey="busforecast.column.todate"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"/>
		<display:column property="profit"  escapeXml="true"  titleKey="busforecast.column.profit"  sortable="true" />
	    <display:column property="userName"  escapeXml="true"  titleKey="public.column.user"  sortable="true" />		
		<display:column property="createdBy"  escapeXml="true"  titleKey="public.column.createdby" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"></display:column>	
		<display:column property="created"  escapeXml="true"  titleKey="public.column.created"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		<display:column property="orgId" escapeXml="true" titleKey="public.column.org" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"></display:column>		    
		</display:table>
</div>


</s:form>




<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/crm/busForecast_edit.action"/>?id='+id;
    }
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
    
    
    
    if ($("#fromOthers").val()=="true")  
    {
    	$('#filterpage').addClass("d-hidden");
    }    
        
   
</script>

</body>
</html>
