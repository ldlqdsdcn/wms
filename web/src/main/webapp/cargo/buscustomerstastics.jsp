<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="buscustomerstastics.title" /></title>

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
<script type='text/javascript' src='../dwr/interface/reportfareinvoiceDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/fareinfoDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>



<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}

#invoiceList{
	position:absolute;
	width:800px;
	top:15px;
	background-color:lightyellow;
	border:2px solid #7C96E2;
	padding:0px;
	display:none;
}


</style>



<script type="text/javascript">

function validateFilter()
{
	var flightDateBegin=$("#flightDateBegin").val();
	var flightDateEnd=$("#flightDateEnd").val();
	var cwConfirmDateBegin=$("#cwConfirmDateBegin").val();	
	var cwConfirmDateEnd=$("#cwConfirmDateEnd").val();
	
	if (flightDateBegin==""&&cwConfirmDateBegin=="")
	{
		alert('<delmar:message key="cargobusiness.dialog.flightconfirmdate.required"/>');
		return false;
	}
	
	var BeflightDate=true;
	var becwConfirmDate=true;
	if (flightDateBegin!="")
	{
	   if (flightDateEnd!="")	
		 {
		    //两者日期之间不能够相差半年
		    if (GetDateDiff(flightDateBegin,flightDateEnd,"day")>180)
		    {
		    	BeflightDate=false;
		    }
		  }
	   else
		   {
			    if (GetDateDiff(flightDateBegin,getCurrDateTimeStr(),"day")>180)
			    {
			    	BeflightDate=false;
			    }		   
		   }
	}
	
	
	if (cwConfirmDateBegin!="")
	{
	   if (cwConfirmDateEnd!="")	
		 {
		    //两者日期之间不能够相差半年
		    if (GetDateDiff(cwConfirmDateBegin,cwConfirmDateEnd,"day")>180)
		    {
		    	BecwConfirmDate=false;
		    }
		  }
	   else
		   {
			    if (GetDateDiff(cwConfirmDateBegin,getCurrDateTimeStr(),"day")>180)
			    {
			    	BecwConfirmDate=false;
			    }		   
		   }
	}
	
	
	if (BecwConfirmDate||beflightDate)
	{
 	
	}
	else
	{
      if (BecwConfirmDate==false)
    	{
    	  alert('<delmar:message key="cargobusiness.dialog.cwconfirmdatecompare"/>');
    	}
      
	      if (beflightDate==false)
	  	{
	  	   alert('<delmar:message key="cargobusiness.dialog.flightdatecompare"/>');
	  	}   
	      
	   return false;
	}
			
	
	
	return true;
}

$(document).ready(function() {	
	     $("#btnQuery").click(function(event)
	 		 {
		           
		  
		        $("#busModeId").val($("#busMode").val());
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
	  
	  

	 
    	jQuery('#flightDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
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
                $("#flightDateBegin").datepicker("option","maxDate",selectedDate);	
             }
     	    });
    	
    	jQuery('#cwConfirmDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",    		
            buttonImage: "../images/DatePicker.GIF",
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,
            onClose:function(selectedDate) {
               $("#cwConfirmDateEnd").datepicker("option","minDate",selectedDate);	
            }
    	    });
    	
    	jQuery('#cwConfirmDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",    		
            buttonImage: "../images/DatePicker.GIF",            
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,
            onClose:function(selectedDate) {
               $("#cwConfirmDateBegin").datepicker("option","maxDate",selectedDate);	
            }
    	    });
    	
       
    	jQuery("#busMode").multiselect(
    	{
    		initValue:$("#busModeId").val(),
    		minWidth:130
    	});
    	
      	
    	var varray=$("#busModeId").val().split(",");
    	for (var i in varray)
    	{
          $("#busMode").find("option[value='"+varray[i]+"']").attr("selected",true);
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


<s:form action="cstastics_searchs" namespace="/cargo"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="buscustomerstastics.location" /></td>
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

			        <td class="d-tdinput" colspan=8 style="text-align:center">
			              <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}"> 	
					      <s:checkbox name="orgGroup" id="orgGroup" fieldValue="yes" />
					      <delmar:message key="buscustomerstastics.column.orggroup" />
					      </c:if> 			       	
			              <c:if test="${MAP_KEY_OF_SESSION.orgVisible!='false'}">					       
					      <s:checkbox name="userGroup" id="userGroup" fieldValue="yes"   />
					      <delmar:message key="buscustomerstastics.column.usergroup" />
					      </c:if>
  				           <s:checkbox name="customerGroup" id="customerGroup" fieldValue="yes"  value="%{#session.MAP_KEY_OF_SESSION.customerGroup}" />
					      <delmar:message key="buscustomerstastics.column.customergroup" />
  				           <s:checkbox name="countryGroup" id="countryGroup" fieldValue="yes"  value="%{#session.MAP_KEY_OF_SESSION.countryGroup}" />
					      <delmar:message key="buscustomerstastics.column.countrygroup" />
  				           <s:checkbox name="portGroup" id="portGroup" fieldValue="yes"  value="%{#session.MAP_KEY_OF_SESSION.portGroup}" />
					      <delmar:message key="buscustomerstastics.column.portgroup" />					      					      
					       			       	
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
			      	<delmar:message key="cargobusiness.column.busmode" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="busMode"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>	
			      			      
			    <td class="d-tdlabel"> 	
		       	<!--To check whether the user have the group privilege  -->
		       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
		    	<delmar:message key="cargobusiness.column.org" />
		    	</c:if>
		       </td>
		      <td class="d-tdinput" colspan=3> 
	       
		       
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
			       	<delmar:message key="cargobusiness.column.flightdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 		
			      		       	
			   		<s:textfield  id="flightDateBegin"  readonly="true"  name="flightDateBegin" value="%{#session.MAP_KEY_OF_SESSION.flightDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="flightDateEnd"  readonly="true"  name="flightDateEnd"  value="%{#session.MAP_KEY_OF_SESSION.flightDateEnd}"  cssClass="d-searchtext"></s:textfield>   		
			       </td>

			      <td class="d-tdlabel">
			       	<delmar:message key="cargobusiness.column.cwconfirmdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="cwConfirmDateBegin"  readonly="true"  name="cwConfirmDateBegin"  value="%{#session.MAP_KEY_OF_SESSION.cwConfirmDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" /> 
			        <s:textfield  id="cwConfirmDateEnd"  readonly="true"  name="cwConfirmDateEnd"  value="%{#session.MAP_KEY_OF_SESSION.cwConfirmDateEnd}"  cssClass="d-searchtext"></s:textfield>
			      </td>
      			      

			       </tr>
			              
			       <tr>
			       <td colspan=8  class="d-searchbutton">     
			       <s:submit method="searchs" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
    


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.businessList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	
	    <c:if test="${list.groupList[0]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomerstastics.column.group1"  sortable="true" >
	          <c:out value="${list.groupList[0]}"/>
	   	</display:column>
	    </c:if>>
	    
	    <c:if test="${list.groupList[1]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomerstastics.column.group2"  sortable="true" >
	          <c:out value="${list.groupList[1]}"/>
	   	</display:column>
	    </c:if>  
	    
	      	    <c:if test="${list.groupList[2]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomerstastics.column.group3"  sortable="true" >
	          <c:out value="${list.groupList[2]}"/>
	   	</display:column>
	    </c:if>    
	    
	    <c:if test="${list.groupList[3]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomerstastics.column.group4"  sortable="true" >
	          <c:out value="${list.groupList[3]}"/>
	   	</display:column>
	    </c:if>    
	    
	    <c:if test="${list.groupList[4]!=null}">
	   	<display:column escapeXml="true"  titleKey="buscustomerstastics.column.group5"  sortable="true" >
	          <c:out value="${list.groupList[4]}"/>
	   	</display:column>
	    </c:if>    	    	    
	    <display:column property="count"  escapeXml="true"  titleKey="buscustomerstastics.column.count" sortable="true"></display:column>	
	    <display:column property="profit"  escapeXml="true"  titleKey="buscustomerstastics.column.profit" sortable="true"></display:column>	    
	    <display:column escapeXml="false"  titleKey="buscustomerstastics.column.volume" sortable="true">
	     Ocean:<c:out value="${list.oceanTeu}"/> Teu &nbsp;&nbsp;Air:<c:out value="${list.airVolume}"/>
	    </display:column>
	</display:table>

</div>
</s:form>





<script type="text/javascript">


$(document).ready(function() {

    
    
    if ("<delmar:session key='userGroup'/>"=="yes")
    {
   	 $("#userGroup").attr("checked","true");
    }
      
    if ("<delmar:session key='orgGroup'/>"=="yes")
    {
   	 $("#orgGroup").attr("checked","true");
    }
      
    if ("<delmar:session key='customerGroup'/>"=="yes")
    {
   	 $("#customerGroup").attr("checked","true");
    }
    
    if ("<delmar:session key='countryGroup'/>"=="yes")
    {
   	 $("#countryGroup").attr("checked","true");
    }
    
    if ("<delmar:session key='portGroup'/>"=="yes")
    {
   	 $("#portGroup").attr("checked","true");
    } 
    
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
