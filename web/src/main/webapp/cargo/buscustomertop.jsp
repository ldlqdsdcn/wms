<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>



<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="buscustomer.top.title" /></title>

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
	
 	var monthBegin=document.getElementById("monthBegin");
	if(isEmpty(monthBegin.value))
		{
			alert('<delmar:warn errorKey="errors.required" key="buscustomer.column.month"/>');
			return false;
		}


	var monthEnd=document.getElementById("monthEnd");
	if(isEmpty(monthEnd.value))
		{
			alert('<delmar:warn errorKey="errors.required" key="buscustomer.column.month"/>');
			return false;
		} 

	return true;	
	
/* 	if ($("#sortType:checked").val()=="0")
	{
		
		alert("sssss");
		if (($("#busMode").val()=="")||($("#busMode").val().indexOf("Ocean")>0&&$("#busMode").val().indexOf("Air")>0))
			{
			alert("aaaaa");
			return false;
			}
			
	} */
	



	
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
   
		        
		        if (validateFilter()==false)
		        {
			        waitingdialog.close();
			        return false;
		        	
		        }
		        	
		        
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
	  
   	   
       
    	jQuery("#busMode").multiselect(
    	{
    		initValue:$("#busModeId").val(),
    		minWidth:130
    	});
    	
     
  		
   		var modeArray=$("#busModeId").val().split(",");
   		for (var i in modeArray)
   		{
   	      $("#busMode").find("option[value='"+modeArray[i]+"']").attr("selected",true);
   		}
   		
    	jQuery("#trustType").multiselect(
    	    	{
    	    		initValue:$("#trustTypeId").val(),
    	    		minWidth:130
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
	
	
	


$(document).ready(function() {	 
	
		
				
			   	jQuery('#monthBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
			   		dateFormat:"yy-mm",    		
		            buttonImage: "../images/DatePicker.GIF",
		            buttonImageOnly: true,
		            buttonText: "Select date"
		    	    });
		    	
		    	jQuery('#monthEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
		    		dateFormat:"yy-mm",
		            buttonImage: "../images/DatePicker.GIF",
		            buttonImageOnly: true,
		            buttonText: "Select date"
		     	    });	
				


});



 

 
</script>
</head>

<body >


<s:form action="cquery_searchtop" namespace="/cargo"  theme="simple" name="cargocustomer">
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="buscustomer.top.location" /></td>
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
			      	<delmar:message key="cargobusiness.column.trusttype" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="trustTypeList" listKey="value" listValue="name"  name="trustType"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.trustTypeId}"  id="trustType" ></s:select>
			    	<s:hidden name="trustTypeId" id="trustTypeId" value="%{#session.MAP_KEY_OF_SESSION.trustTypeId}" />       
			      </td>	
			                       

			   <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.busmode" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="busMode"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.busModeId}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>
			      



			      
			   <td class="d-tdlabel">
			      	<delmar:message key="buscustomer.column.topnumber" />
			   </td>
			      <td class="d-tdinput"> 	
                  <s:textfield name="topNumber" id="topNumber" value="%{#session.MAP_KEY_OF_SESSION.topNumber}"  blurvalidate="yes" blurtype="integer"  title="%{#session.resource.get('public.inputformat.integer')}"  cssClass="d-searchtext"></s:textfield>			     
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
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
			    	<delmar:message key="public.accessprivilege.org"/>
		       	</c:if>
		       	
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='group'}">
			    	<delmar:message key="public.accessprivilege.usergroup"/>
			    	<s:checkbox name="beGroup" id="beGroup" fieldValue="true" value="%{#session.MAP_KEY_OF_SESSION.beGroup}" />
                    <s:textfield name="userName" id="userName"  value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>			    	
		       	</c:if>		
		       			       	
		       	<s:hidden name="orgIds" id="orgIds" value="%{#session.MAP_KEY_OF_SESSION.orgIds}"></s:hidden>
		      </td>						      
		      
			    </tr>
			   <tr>
			   
		       <td class="d-tdlabel">      	
			     <delmar:message key="buscustomer.column.yesoutin" />
			   </td>		      
  		        <td   class="d-tdinput"> 
			      <s:checkbox name="outInOut" id="outInOut" fieldValue="0" />
			      <delmar:message key="buscustomer.column.outinout" /> 
			      <s:checkbox name="outInIn" id="outInIn" fieldValue="1"   />
			      <delmar:message key="buscustomer.column.outinin" /> 
			   </td>	 
			   
			    <td class="d-tdlabel">      	
			     <delmar:message key="buscustomer.column.clienttype" />
			   </td>		      
  		        <td   class="d-tdinput"> 
                    <s:radio name="clientType" id="clientType" cssClass="clientType" list="clientTypeList" listKey="value" listValue="name" value="%{#session.MAP_KEY_OF_SESSION.clientType}"/>			  
                 </td>
                 			   
			   <td class="d-tdlabel">
			      	<delmar:message key="buscustomer.column.sorttype" />
			   </td>
			      <td class="d-tdinput" > 	
			        <s:radio name="sortType" id="sortType" cssClass="sortType" list="sortTypeList" listKey="value" listValue="name" value="%{#session.MAP_KEY_OF_SESSION.sortType}"/>			     
                 </td>
                 
		       <td class="d-tdlabel">      	
			     <delmar:message key="buscustomer.column.month" />
			   </td>			   		      		       
			   <td   class="d-tdinput" > 
                  <s:textfield name="monthBegin" id="monthBegin" value="%{#session.MAP_KEY_OF_SESSION.monthBegin}"  cssClass="d-searchtext"></s:textfield>
                  <delmar:message key="public.column.to" /> 
			      <s:textfield name="monthEnd" id="monthEnd" value="%{#session.MAP_KEY_OF_SESSION.monthEnd}"  cssClass="d-searchtext"></s:textfield>
			   </td>
                  
                 			   
			   </tr>
			
			              
			       <tr>
			       <td colspan=2 style="text-align:left;padding:5px;">
                      <input type="button" class="input_submit" id="btnLinkRecord" value="<delmar:message key='buscustomer.button.searchlinkrecord'/>" />			       
			       </td>			       
			       <td colspan=8  class="d-searchbutton">     
			       <s:submit method="searchtop" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
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
			value="<c:out value='${list.clientCode}'/>" style='border: none' />
			
	</display:column>
				    
	<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;" >
		   <c:out value="${list_rowNum}"/>
    </display:column>   
    

	  <display:column property="clientCode"  escapeXml="true"  titleKey="buscustomer.column.cuscode"  sortable="true" />	  
	  <display:column property="clientName"  escapeXml="true"  titleKey="buscustomer.column.cusname"  sortable="true" />	
      <display:column property="inumber"  escapeXml="true"  titleKey="buscustomer.column.inumber"  sortable="true" />      
      <display:column property="cargoVolume"  escapeXml="true"  titleKey="buscustomer.column.cargovolume"  sortable="true" />
      <display:column  property="profit"  escapeXml="true"  titleKey="buscustomer.column.profit" sortable="true"/>
	  </display:table>

<div style="clear:both;display:block;margin-top:20px;padding-top:20px;margin-left:20px;text-align:center;">
<c:if test="${session.MAP_KEY_OF_SESSION.totalNumber>0}">
<span style="font-size:16"><delmar:message key="buscustomer.column.totalclientnumber"/>:<span style="color:#ff0000;font-size:18"><s:property value="%{#session.MAP_KEY_OF_SESSION.totalNumber}"/></span>
<delmar:message key="buscustomer.column.profitmargin"/>:<span style="color:#ff0000;font-size:18"><s:property value="%{#session.MAP_KEY_OF_SESSION.profitMargin}"/>%</span></span>
</c:if>
</div>

</div>


</s:form>







<script type="text/javascript">

	
$(document).ready(function() {

	    $(document).D_Validate();
	    
	      
        if ("<delmar:session key='outInOut'/>"=="0")
        {
       	 $("#outInOut").attr("checked","true");
        }
        

	      
        if ("<delmar:session key='outInIn'/>"=="1")
        {
       	  $("#outInIn").attr("checked","true");
        }
	      

});  
 


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
