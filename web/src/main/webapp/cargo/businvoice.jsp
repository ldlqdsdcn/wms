<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="businvoice.title" /></title>

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
<script type='text/javascript' src='../dwr/interface/reportfareinvoiceDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/fareinfoDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>



<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}

#fareinfoList{
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
 	  return true;
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
			
	
}

$(document).ready(function() {	


	  $("#btnQuery").click(function(event)
	 		 {
	   	     
		        $("#busModeId").val($("#busMode").val());
		        $("#creditDebitId").val($("#creditDebit").val());
		        $("#invoiceStatusId").val($("#invoiceStatus").val());		        
		        
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
    		    
    	
    	
    	jQuery("#creditDebit").multiselect(
    	    	{
    	    		initValue:$("#creditDebitId").val(),
    	    		minWidth:130
    	    	});
    	
      	
    	varray=$("#creditDebitId").val().split(",");
    	for (var i in varray)
    	{
          $("#creditDebit").find("option[value='"+varray[i]+"']").attr("selected",true);
    	}
    		    
    	
    
    	jQuery("#invoiceStatus").multiselect(
    	    	{
    	    		initValue:$("#invoiceStatusId").val(),
    	    		minWidth:130
    	    	});
    
      	
    	varray=$("#invoiceStatusId").val().split(",");
    	for (var i in varray)
    	{
          $("#invoiceStatus").find("option[value='"+varray[i]+"']").attr("selected",true);
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


<s:form action="iquery_searchi" namespace="/cargo"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="businvoice.location" /></td>
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
			     <delmar:message key="cargoinvoice.column.invoiceobject" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="invoiceObject" value="%{#session.MAP_KEY_OF_SESSION.invoiceObject}" cssClass="d-searchtext"></s:textfield>
			   </td>		      		       
		       <td class="d-tdlabel">      	
			     <delmar:message key="cargobusiness.column.fileno" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="fileNo" value="%{#session.MAP_KEY_OF_SESSION.fileNo}"  cssClass="d-searchtext"></s:textfield>
			   </td>
			   
		       <td class="d-tdlabel">      	
			     <delmar:message key="cargobusiness.column.hawb" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="hawb" value="%{#session.MAP_KEY_OF_SESSION.hawb}"  cssClass="d-searchtext"></s:textfield>
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
			      	<delmar:message key="cargoinvoice.column.paymentday" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:textfield name="paymentDay" value="%{#session.MAP_KEY_OF_SESSION.paymentDay}" id="paymentDay" class="d-inputtext" blurvalidate="yes" blurtype="integer"  title="%{#session.resource.get('public.inputformat.integer')}"  cssClass="d-searchtext"></s:textfield>       
			      </td>			      
			     <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.busmode" />
			    </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="status"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>
			      <td class="d-tdlabel">
			       	<delmar:message key="cargoinvoice.column.invoicestatus" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			    	<s:select list="creditDebitList" listKey="value" listValue="name"  name="creditDebit"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.invoiceStatus}"  id="creditDebit" ></s:select>
			    	<s:hidden name="creditDebitId" id="creditDebitId" value="%{#session.MAP_KEY_OF_SESSION.creditDebitId}" />       

			    	<s:select list="invoiceStatusList" listKey="value" listValue="name"  name="invoiceStatus"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.invoiceStatus}"  id="invoiceStatus"></s:select>
			    	<s:hidden name="invoiceStatusId" id="invoiceStatusId" value="%{#session.MAP_KEY_OF_SESSION.invoiceStatusId}" />       
			      </td>			      
			   
			    </tr>
			    <tr>
	      
	              <td class="d-tdlabel"> 	
			       	<delmar:message key="cargobusiness.column.flightdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 		
			      		       	
			   		<s:textfield  id="flightDateBegin"  readonly="true"  name="flightDateBegin" value="%{#session.MAP_KEY_OF_SESSION.flightDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="flightDateEnd"  readonly="true"  name="flightDateEnd"  value="%{#session.MAP_KEY_OF_SESSION.flightDateEnd}" cssClass="d-searchtext" ></s:textfield>   		
			       </td>

			      <td class="d-tdlabel">
			       	<delmar:message key="cargobusiness.column.cwconfirmdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="cwConfirmDateBegin"  readonly="true"  name="cwConfirmDateBegin"  value="%{#session.MAP_KEY_OF_SESSION.cwConfirmDateBegin}"  cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" /> 
			        <s:textfield  id="cwConfirmDateEnd"  readonly="true"  name="cwConfirmDateEnd"  value="%{#session.MAP_KEY_OF_SESSION.cwConfirmDateEnd}" cssClass="d-searchtext" ></s:textfield>
			      </td>

			      
			       </tr>
			              
			       <tr>
			       <td colspan=12  class="d-searchbutton">     
			       <s:submit method="searchi" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" ></s:submit>
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
    


<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.businessList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true" >
		
		<delmar:operatePriv operator="delete">
		<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.invoiceNo}'/>" style='border: none' />
				
		</display:column>
		</delmar:operatePriv>
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf"  style="width:40px;">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	
		  <display:column    titleKey="cargoinvoice.column.invoiceno" sortable="true" media="html">
		      <a id='invoice<c:out value="${list.invoiceNo}"/>' href="javascript:viewInvoice('<c:out value="${list.invoiceNo}"/>')">		      
	           <c:out value="${list.invoiceNo}"/>
		      </a>	           
	      <br>
			<div id="operate<c:out value="${list.invoiceNo}"/>" class="d-rowactions">
			 <a  href="#"  onclick="javascript:viewFareInfo('<c:out value="${list.invoiceNo}"/>')"><delmar:message key="cargoinvoice.operate.viewfareinfo"/></a>|
			</div>     		  
	
	  </display:column>
	  <display:column property="cusCodeName"  escapeXml="true"  titleKey="cargoinvoice.column.cusname"  sortable="true" />	  
	  <display:column property="currencyType"  escapeXml="true"  titleKey="cargoinvoice.column.currencytype"  sortable="true" />	  
      <display:column property="creditDebit"  escapeXml="true"  titleKey="cargoinvoice.column.creditdebit"  sortable="true" />	  
      <display:column property="transProfit"  escapeXml="true"  titleKey="cargoinvoice.column.transprofit"  sortable="true" />      
      <display:column property="balance"  escapeXml="true"  titleKey="cargoinvoice.column.balance"  sortable="true" />
      <display:column  escapeXml="true"  titleKey="cargoinvoice.column.billno"  sortable="true" >
         <c:if test="${list.billNo!=''}">
           <c:out value="${list.billNo}"/>(<c:out value="${list.billDate}"/>)
         </c:if>
      </display:column>
      <display:column  property="fileNo"  escapeXml="true"  titleKey="cargobusiness.column.fileno" sortable="true">
      </display:column>
      <display:column  property="mawbCode"  titleKey="cargobusiness.column.maincode" sortable="true" media="html">
	  </display:column>
  	  <display:column   property="hawb"  titleKey="cargobusiness.column.hawb" sortable="true" media="html">
	  </display:column>
       <display:column property="businessName"  escapeXml="true"  titleKey="cargobusiness.column.business"  sortable="true"/>	  
	  <display:column property="operatorName"  escapeXml="true"  titleKey="cargobusiness.column.operator" sortable="true"></display:column>
      <display:column property="operateDate"  escapeXml="true"  titleKey="cargobusiness.column.operatedate" sortable="true"></display:column>		    
	  <display:column property="companyId"  escapeXml="true"  titleKey="cargobusiness.column.companyid" sortable="true"></display:column>
	  </display:table>

</div>
</s:form>

<div id="fareinfoList" >
  <div id="closebtn"><a href="#" onclick="return $('#fareinfoList').attr('style','display:none');">Close</a></div>
  <div id="fareinfogrid" style="margin: 5px; padding: 0">
  </div>
</div>






<script type="text/javascript">

	$(document).ready(function() {

	    $(document).D_Validate();
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
    
    
    
    function viewFareInfo(id,receDeal)
    {
    	var theSrcElement;
    	if (getBrowserInfo()=="FF")
    	{
    		var theEvent = window.event || arguments.callee.caller.arguments[0];
    		theSrcElement=theEvent.target;
    	}
    	else
        {
    		theSrcElement=event.srcElement;
        }
       
    	var pubTrElement=findRowTR(theSrcElement);    	  	
    	//$(pubTrElement).attr("style","height:60px;vertical-align:text-top");
    	

    	var selObjPos = getXY(theSrcElement); 
    	
    	if ($("#fareInfoList").attr("currentId")!=id)
    		{     
        	
				fareinfoDwr.getFareInfoGson(id, function(data) {
					eval("var gsondata={Rows:"+data+"};");
					if  (gsondata.Rows.length>0)
					{
						var grid=$("#fareinfogrid").ligerGrid({
						columns: [ 
			               { display: '<delmar:message key="cargofareinfo.column.farename"/>', name: 'fareName', minWidth: 80 } ,                
						   { display: '<delmar:message key="cargofareinfo.column.operacount"/>', minWidth: 80,render:function (rowdata,rowindex,value){
						   var h="";
						   
			 			   if (rowdata.recedeal==receDeal)
			 				  h=rowdata.operAcount; 				
			 			   else
			 				  h="<font color='#ff0000'>("+rowdata.operAcount+")</font>"; 				   
			 				   
			      		   return h;
						 	}},
					      { display: '<delmar:message key="cargofareinfo.column.operatorname"/>', name: 'operatorName', minWidth: 80 }				
							], data: gsondata, usePager:false, sortName: 'fareNo', frozen:false,
							width: 780,rownumbers:true,headerRowHeight:26,isScroll: false
						});
					};
				});    	
    		}
		
		document.all.fareinfoList.style.left=(selObjPos.left+30)+"px";
		document.all.fareinfoList.style.top=(selObjPos.top+20)+"px";
		document.all.fareinfoList.style.display="inline";

		
		$("#fareinfoList").attr("currentId",id);

    }
   

 

   
    
</script>

</body>
</html>
