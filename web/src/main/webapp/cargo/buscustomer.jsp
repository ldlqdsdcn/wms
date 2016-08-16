<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="buscustomer.title" /></title>

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

<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>



<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}



</style>



<script type="text/javascript">

function validateFilter()
{
	
	var daysEnd=document.getElementById("daysEnd");
	if(isEmpty(daysEnd.value))
		{
			alert('<delmar:warn errorKey="errors.required" key="buscustomer.column.daysend"/>');
			return false;
		}



		
	return true;	
	
}

$(document).ready(function() {	
	

	
	
	  $("#btnQuery").click(function(event)
	 		 {
		        $("#busModeId").val($("#busMode").val());
		        $("#trustTypeId").val($("#trustType").val());
		        
		        if (document.all.userOrgs)
		        {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }
		        

		        
		        var waitingdialog=showWaiting("<delmar:message key='public.dialg.waitingsearch' cn='数据正在进行查询，请稍后'/>",false);
		        
		       // alert(validateFilter());

		        //if(validateFilter()==false);
		       // {
    		   //     waitingdialog.close();
    		    //    return false;
		       // }
		        // alert("1");
		        return true;
	
	 		 });	
	  
	  $("#btnLinkRecord").click(function(event)
		 		 {
		              
			          if ($("#ids:checked").length==0)
			          {
			          	showWarn("<delmar:message key='public.message.chooseids'/>");
			          	return false;
			          }
			          
		            $.ligerDialog.prompt('<delmar:message key="buscustomer.message.inputdays"/>',"30", function (yes,value) { 
		            	
		              if(yes)
		            	 {
		            	  if (validateInteger(value)==false)
		            		  {
		            	        showWarn("<delmar:message key='public.inputformat.integer'/>");
		            	        return false;
		            		  }
		            	  else
		            		  {

		            		     document.cargocustomer.action="cquery_searchcargo.action?days="+value;
		            		     document.cargocustomer.submit();
		            		     
		            		  }
		            		  return true;
		            	 }
		            	  
		              else
		            	  return false;
		              
		            });
			       
		
		 		 });	
	  	  
	  

   	   $(".haveBusiness").click(function(event) {
   		   if ($(this).val()=="yes")
   			   {
   			    $("#nobusiness").removeClass("d-hidden"); 
   			     
   			   }
   		   else
   			  $("#nobusiness").addClass("d-hidden");   			    			   
 		 });	
		  
		 
   	   
       
    	jQuery("#busMode").multiselect(
    	{
    		initValue:$("#busModeId").val(),
    		minWidth:140
    	});
    	
     
  		
   		var modeArray=$("#busModeId").val().split(",");
   		for (var i in modeArray)
   		{
   	      $("#busMode").find("option[value='"+modeArray[i]+"']").attr("selected",true);
   		}
   		
   		
    	jQuery("#trustType").multiselect(
    	    	{
    	    		initValue:$("#trustTypeId").val(),
    	    		minWidth:140
    	    	});
    	    	
    	     
    	  		
 	   		var trustTypeArray=$("#trustTypeId").val().split(",");
 	   		for (var i in trustTypeArray)
 	   		{
 	   	      $("#trustType").find("option[value='"+trustTypeArray[i]+"']").attr("selected",true);
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
	
	
	



 

 
</script>
</head>

<body >


<s:form action="cquery_searchc" namespace="/cargo"  theme="simple" name="cargocustomer" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="buscustomer.location" /></td>
          <td class="navig" align="right"> 
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
			     <delmar:message key="buscustomer.column.yesoutin" />
			     <s:checkbox name="soutIn" id="soutIn" fieldValue="1"  />
			       	<s:hidden name="cusName" value="%{#session.MAP_KEY_OF_SESSION.cusName}"  cssClass="d-searchtext"></s:hidden>			      
			   </td>		      
  		        <td   class="d-tdinput"> 
			      <s:checkbox name="outInOut" id="outInOut" fieldValue="0" />
			      <delmar:message key="buscustomer.column.outinout" /> 
			      <s:checkbox name="outInIn" id="outInIn" fieldValue="1"   />
			      <delmar:message key="buscustomer.column.outinin" /> 
			   </td>		      		       
			   <td   class="d-tdlabel"> 
			      <s:radio name="haveBusiness" id="haveBusiness" cssClass="haveBusiness" list="haveBusinessList" listKey="value" listValue="name" value="%{#session.MAP_KEY_OF_SESSION.haveBusiness}"/>
			   </td>
			   <td  class="d-tdinput">
			      <span id="nobusiness" class="d-hidden">
                  <s:textfield name="daysBegin" id="daysBegin" value="%{#session.MAP_KEY_OF_SESSION.daysBegin}" cssClass="d-searchtext d-width-small" blurvalidate="yes" blurtype="integer" title="%{#session.resource.get('public.input.numberonly')}" ></s:textfield>
                  <delmar:message key="public.column.to" /></span> 
			      <s:textfield name="daysEnd" id="daysEnd" value="%{#session.MAP_KEY_OF_SESSION.daysEnd}" cssClass="d-searchtext d-width-small" blurvalidate="yes" blurtype="integer"    title="%{#session.resource.get('public.input.numberonly')}" ></s:textfield>
			   </td>
 
			   <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.busmode" />
                    <s:checkbox name="smode" id="smode" fieldValue="1" />		      	
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="busMode"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.busModeId}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>
			      
			   <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.trusttype" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="trustTypeList" listKey="value" listValue="name"  name="trustType"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.trustTypeId}"  id="trustType" ></s:select>
			    	<s:hidden name="trustTypeId" id="trustTypeId" value="%{#session.MAP_KEY_OF_SESSION.trustTypeId}" />       
			      </td>			      
			    <td class="d-tdlabel"> 	
		       	<!--To check whether the user have the group privilege  -->
		       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
		    	<delmar:message key="cargobusiness.column.org" />
		    	</c:if>
		       </td>
		      <td class="d-tdinput" > 
		       
		       
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
			    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs" ></s:select>
			    	<s:textfield name="userName" id="userName"  value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>			    	
		       	</c:if>
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='group'}">
			    	<delmar:message key="public.accessprivilege.org"/>
			    	<s:checkbox name="beGroup" id="beGroup" fieldValue="true" value="%{#session.MAP_KEY_OF_SESSION.beGroup}" />
			    				    	<s:textfield name="userName" id="userName"  value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>
		       	</c:if>
		       	
		       	 <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
			    	<delmar:message key="public.accessprivilege.org"/>
		       	</c:if>
		       			       	
		       	<s:hidden name="orgIds" id="orgIds" value="%{#session.MAP_KEY_OF_SESSION.orgIds}"></s:hidden>
		      </td>					      
		      
			    </tr>
			
			              
			       <tr>
	               <td colspan=2 style="text-align:left;padding:5px;">
                      <input type="button" class="input_submit" id="btnLinkRecord" value="<delmar:message key='buscustomer.button.searchlinkrecord'/>" />			       
			       </td>			       
			       <td colspan=6  class="d-searchbutton">     
			       <s:submit method="searchc" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>   
		
			       </tr>
			       </table>        
        </div>

 </div>
    


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
  <display:table name="sessionScope.MAP_KEY_OF_SESSION.businessList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >

	<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
	
	<input type="checkbox" name="ids" id="ids"
			value="<c:out value='${list.cusCode}'/>" style='border: none' />
			
	</display:column>
			    
	<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;">
		   <c:out value="${list_rowNum}"/>
    </display:column>   
    

	  <display:column property="cusCode"  escapeXml="true"  titleKey="buscustomer.column.cuscode"  sortable="true" />	  
	  <display:column property="cusName"  escapeXml="true"  titleKey="buscustomer.column.cusname"  sortable="true" />	
	    
	    <c:if test="${list.groupList[2]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomer.column.group1"  sortable="true" >
	          <c:out value="${list.groupList[2]}"/>
	   	</display:column>
	    </c:if>>
	    
	    <c:if test="${list.groupList[3]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomer.column.group2"  sortable="true" >
	          <c:out value="${list.groupList[3]}"/>
	   	</display:column>
	    </c:if>>    
            
      <display:column property="firstEtd"  escapeXml="true"  titleKey="buscustomer.column.firstetd"  sortable="true" />
      <display:column  property="lastEtd"  escapeXml="true"  titleKey="buscustomer.column.lastetd" sortable="true"/>
      <display:column property="dayToNow"  escapeXml="true"  titleKey="buscustomer.column.daytonow"  sortable="true" />
      <display:column property="salesName"  escapeXml="true"  titleKey="buscustomer.column.salesname"  sortable="true" />
	  <display:column   titleKey="buscustomer.column.remark" sortable="true">
	  [<c:out value="${list.planeOceanTrl}"/>][<c:out value="${list.outInTrl}"/>][<c:out value="${list.companyId}"/>]
	  </display:column>
	  </display:table>

</div>
</s:form>







<script type="text/javascript">

	
$(document).ready(function() {

	    $(document).D_Validate();
	    
        if ("<delmar:session key='soutIn'/>"=="1")
         {
        	 $("#soutIn").attr("checked","true");
         }
	      
        if ("<delmar:session key='outInOut'/>"=="0")
        {
       	 $("#outInOut").attr("checked","true");
        }
	      
        if ("<delmar:session key='outInIn'/>"=="1")
        {
       	 $("#outInIn").attr("checked","true");
        }
	      
        if ("<delmar:session key='smode'/>"=="1")
        {
       	 $("#smode").attr("checked","true");
        }
	      
	    if ($('input[name="haveBusiness"]:checked').val()=="yes")
    	{
	    	$("#nobusiness").removeClass("d-hidden");	
	    	
	    }
	    else
	    	$("#nobusiness").addClass("d-hidden");   	
	    	


});  
 

    function viewExport(id) {
       window.location='<c:url value="/crm/customer_edit.action"/>?id='+id;
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
    
    
 
    
    
    

   
    
</script>

</body>
</html>
