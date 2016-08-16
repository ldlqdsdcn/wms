<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="linkrecord.title" /></title>


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

function validateFilter()
{
	
	var createDateBegin=document.getElementById("createDateBegin");
	if(isEmpty(createDateBegin.value))
		{
			alert('<delmar:warn errorKey="errors.required" key="buscustomer.column.created"/>');
			return false;
		}

	var createDateEnd=document.getElementById("createDateEnd");
	if(isEmpty(createDateEnd.value))
		{
			alert('<delmar:warn errorKey="errors.required" key="buscustomer.column.created"/>');
			return false;
		}

		
	return true;	
	
}



$(document).ready(function() {	
	  $("#btnQuery").click(function(event)
	 		 {
		        $("#contactModeId").val($("#contactMode").val());
		        $("#resultId").val($("#result").val());
		        
		        if (document.all.userOrgs)
		        {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }	
		        
		        var waitingdialog=showWaiting("<delmar:message key='public.dialg.waitingsearch' cn='数据正在进行查询，请稍后'/>",false);
		        
		        if(validateFilter()==false);
		        {
    		        waitingdialog.close();
    		        return false;
		        }
		        
		        return true;
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
  	
  	jQuery('#contactDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#contactDateEnd").datepicker("option","minDate",selectedDate);	
          }
  	    });
  	
  	jQuery('#contactDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
  		dateFormat:"yy-mm-dd",    		
          buttonImage: "../images/DatePicker.GIF",            
          buttonImageOnly: true,
          buttonText: "Select date",
          numberOfMonths:2,
          onClose:function(selectedDate) {
             $("#contactDateBegin").datepicker("option","maxDate",selectedDate);	
          }
  	    });
  	
     
  	jQuery("#contactMode").multiselect(
  	{
  		initValue:$("#contactModeId").val(),
  		minWidth:180
  	});
  	
  	
	var varray=$("#contactModeId").val().split(",");
	for (var i in varray)
	{
      $("#contactMode").find("option[value='"+varray[i]+"']").attr("selected",true);
	}
		
		
  	
  	jQuery("#result").multiselect(
  		  	{
  		  		initValue:$("#resultId").val(),
  		  		minWidth:180
  		  	});
  	
	varray=$("#resultId").val().split(",");
	for (var i in varray)
	{
      $("#result").find("option[value='"+varray[i]+"']").attr("selected",true);
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
    		

		$("#userName").attr("placeholder",$("#userName").attr("title"));
     
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

<s:form action="linkrecord_list" namespace="/crm"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="linkrecord.location" /></td>
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
			      <delmar:message key="linkrecord.column.linkrecordid" /> 
			   </td>
			   <td   class="d-tdinput"> 
  		       	  <s:textfield name="linkRecordId" id="linkRecordId" value="%{#session.MAP_KEY_OF_SESSION.linkRecordId}" cssClass="d-searchtext"></s:textfield>
			   </td>
		       <td class="d-tdlabel">      	
			      <delmar:message key="linkrecord.column.customerid" /> 
			   </td>
			   <td   class="d-tdinput"> 
  		       	  <s:textfield name="customerName" id="customerName" value="%{#session.MAP_KEY_OF_SESSION.customerName}" cssClass="d-searchtext"></s:textfield>
		       	  <s:hidden name="customerId" id="customerId" value="%{#session.MAP_KEY_OF_SESSION.customerId}"></s:hidden>
		       	  
		       	  <s:hidden name="linkCode" value="%{#session.MAP_KEY_OF_SESSION.linkCode}" cssClass="d-searchtext"></s:hidden>
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="linkman.column.name" />
			   </td>
			   <td class="d-tdinput"> 
                    <s:textfield name="linkManName" value="%{#session.MAP_KEY_OF_SESSION.linkManName}" cssClass="d-searchtext"></s:textfield>
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
			    	<br>
	                <s:textfield name="userName" id="userName" value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>			    	
		       	</c:if>
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
			    	<delmar:message key="public.accessprivilege.org"/>
		       	</c:if>
		       	
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='group'}">
			    	<delmar:message key="public.accessprivilege.usergroup"/>
			    	<s:checkbox name="beGroup" id="beGroup" fieldValue="true" value="%{#session.MAP_KEY_OF_SESSION.beGroup}" />
                 	<s:textfield name="userName" id="userName" value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>			    	
		       	</c:if>		
		       			       	
		       			       	
		       	<s:hidden name="orgIds" id="orgIds" value="%{#session.MAP_KEY_OF_SESSION.orgIds}"></s:hidden>
		      </td>					      
		      		   
			      </tr>
			      <tr>
			      <td class="d-tdlabel"> 	
			      <delmar:message key="linkrecord.column.contacttitle" />
			      </td>
                   <td class="d-tdinput">
                   <s:textfield name="contactTitle" id="contactTitle" value="%{#session.MAP_KEY_OF_SESSION.contactTitle}" cssClass="d-searchtext"></s:textfield>                   
                   </td>
                   <td class="d-tdlabel"> 	
			      <delmar:message key="linkrecord.column.contactmodeid" />
			      </td>
                   <td class="d-tdinput">
                   <s:select list="contactModeList" listKey="datadictId" listValue="name"  name="contactMode" value="%{#session.MAP_KEY_OF_SESSION.contactModeId}" multiple="true" id="contactMode"></s:select>
			    	<s:hidden name="contactModeId" id="contactModeId" value="%{#session.MAP_KEY_OF_SESSION.contactModeId}" />                                      
                   </td> 	
                  <td class="d-tdlabel"> 	
			      <delmar:message key="linkrecord.column.resultid" />
			      </td>
                   <td class="d-tdinput">
                   <s:select list="resultList" listKey="datadictId" listValue="name"  name="result" multiple="true" id="result"></s:select>
                    <s:hidden name="resultId" id="resultId" value="%{#session.MAP_KEY_OF_SESSION.resultId}" />
                   </td>  
                   
                   <td class="d-tdinput">
                    </td>
                    <td class="d-tdinput">
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
			       	<delmar:message key="linkrecord.column.contactdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="contactDateBegin"  readonly="true"  name="contactDateBegin" value="%{#session.MAP_KEY_OF_SESSION.contactDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="contactDateEnd"  readonly="true"  name="contactDateEnd" value="%{#session.MAP_KEY_OF_SESSION.contactDateEnd}" cssClass="d-searchtext"></s:textfield>
			      </td>

			       </tr>
			       <tr>
			       <td colspan=8 class="d-searchbutton">     
			       <s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
 


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.linkrecordList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true"  decorator="com.delmar.crm.web.displaytag.decorator.LinkrecordDecorator">
		
		<delmar:operatePriv operator="delete">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</display:column>
		</delmar:operatePriv>
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf"  style="width:40px;">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
	     <display:column   titleKey="linkrecord.column.linkrecordid" sortable="true" media="html" style="width:80px;">
	         <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.linkRecordId}"/>
    		  </a>
	     </display:column>
    	<display:column property="customerId"  escapeXml="true"  titleKey="customer.column.name" sortable="true" decorator="com.delmar.crm.web.displaytag.decorator.CustomerDecorator"></display:column>    	
        <display:column property="linkmanId"  escapeXml="true"  titleKey="linkman.column.name" sortable="true" decorator="com.delmar.crm.web.displaytag.decorator.LinkmanDecorator"></display:column>        
	     <display:column   titleKey="linkrecord.column.contacttitle" sortable="true" media="html">
	         <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.contactTitle}"/>
    		  </a>
	     </display:column>
		<display:column property="contactDate"  escapeXml="true"  titleKey="linkrecord.column.contactdate"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"/>	     
        <display:column property="contactModeId"  escapeXml="true"  titleKey="linkrecord.column.contactmodeid"  sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.DatadictDecorator"/>
        <display:column property="forceOnId"  escapeXml="true"  titleKey="linkrecord.column.forceonid"  sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.DatadictDecorator"/>        
        <display:column property="nextTime"  escapeXml="true"  titleKey="linkrecord.column.nexttime"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"/>
		<display:column property="userName"  escapeXml="true"  titleKey="public.column.user"  sortable="true" />        
		<display:column property="createdBy"  escapeXml="true"  titleKey="public.column.createdby" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"></display:column>	
		<display:column property="created"  escapeXml="true"  titleKey="public.column.created"  sortable="true"  decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		<display:column property="orgId" escapeXml="true" titleKey="public.column.org" sortable="true" decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"></display:column>		    
		</display:table>
</div>


</s:form>




<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="/crm/linkrecord_edit.action"/>?id='+id;
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
