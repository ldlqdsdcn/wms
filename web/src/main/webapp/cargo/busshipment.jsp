<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="busshipment.title" /></title>

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


<s:form action="squery_searchs" namespace="/cargo"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="busshipment.location" /></td>
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
			     <delmar:message key="cargobusiness.column.fileno" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="fileNo" value="%{#session.MAP_KEY_OF_SESSION.fileNo}"  cssClass="d-searchtext"></s:textfield>
			   </td>
			   <td class="d-tdlabel">
			      	<delmar:message key="cargobusiness.column.busmode" />
			   </td>
			      <td class="d-tdinput"> 	
			    	<s:select list="busModeList" listKey="value" listValue="name"  name="busMode"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="busMode" ></s:select>
			    	<s:hidden name="busModeId" id="busModeId" value="%{#session.MAP_KEY_OF_SESSION.busModeId}" />       
			      </td>
			      
		       <td class="d-tdlabel">      	
			     <delmar:message key="cargobusiness.column.shippername" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="shipperName" value="%{#session.MAP_KEY_OF_SESSION.shipperName}"  cssClass="d-searchtext"></s:textfield>
			   </td>
			   
		       <td class="d-tdlabel">      	
			     <delmar:message key="cargobusiness.column.consignname" />
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="consignName" value="%{#session.MAP_KEY_OF_SESSION.consignName}"  cssClass="d-searchtext"></s:textfield>
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
			      <td class="d-tdinput">
			       	
			      </td>
			      <td class="d-tdinput">
			       	
			      </td>			      			      

			       </tr>
			              
			       <tr>
			       <td colspan=10  class="d-searchbutton">     
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
		
		<delmar:operatePriv operator="delete">
		<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.trustFileCode}'/>" style='border: none' />
				
		</display:column>
		</delmar:operatePriv>
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	 
	     <display:column  escapeXml="true"  titleKey="cargobusiness.column.fileno" sortable="true">
	      <c:out value="${list.fileNo}"/>
	    </display:column>

		    <display:column   titleKey="cargobusiness.column.maincode" sortable="true" media="html">
		      <c:if test="${list.beZhiDan=='1'}">
		      <a id='shipment<c:out value="${list.trustFileCode}"/>' href="javascript:viewShipment('<c:out value="${list.trustFileCode}"/>')">		      
		      <c:out value="${list.mawbCode}"/>
		      </a>
		      <br>
				<div id="operate<c:out value="${list.trustFileCode}"/>" class="d-rowactions">
				 <a  href="#"  onclick="javascript:viewInvoice('<c:out value="${list.trustFileCode}"/>','<c:out value="${list.planeOcean}"/>')"><delmar:message key="cargobusiness.operate.viewinvoice"/></a>|
				</div>     		  
		      </c:if>
		      <c:if test="${list.beZhiDan=='2'}">
	           <c:out value="${list.mawbCode}"/>
		      </c:if>		    
		    
		    </display:column>
  		    <display:column    titleKey="cargobusiness.column.hawb" sortable="true" media="html">
		      <c:if test="${list.beZhiDan=='1'}">
		      <c:out value="${list.hawb}"/>
		      </c:if>
		      
		      <c:if test="${list.beZhiDan=='2'}">
			      <a id='shipment<c:out value="${list.trustFileCode}"/>' href="javascript:viewShipment('<c:out value="${list.trustFileCode}"/>')">		      
		           <c:out value="${list.hawb}"/>
			      </a>	           
		      <br>
				<div id="operate<c:out value="${list.trustFileCode}"/>" class="d-rowactions">
				 <a  href="#"  onclick="javascript:viewInvoice('<c:out value="${list.trustFileCode}"/>','<c:out value="${list.planeOcean}"/>')"><delmar:message key="cargobusiness.operate.viewinvoice"/></a>|
				</div>     		  
			      
		      </c:if>		    
  		    
  		    </display:column>
		    <display:column property="shipperName"  escapeXml="true"  titleKey="cargobusiness.column.shippername" sortable="true" >
		     [<c:out value="${list.shipperCode}"/>]
		    </display:column>	
            <display:column property="consignName"  escapeXml="true"  titleKey="cargobusiness.column.consignname" sortable="true" >
             [<c:out value="${list.consignCode}"/>]
            </display:column>
		    <display:column property="flightDate"  escapeXml="true"  titleKey="cargobusiness.column.flightdate"  sortable="true"  />
		    <display:column property="airPortFrom"  escapeXml="true"  titleKey="cargobusiness.column.airportfrom"  sortable="true" >
		    </display:column>
		    <display:column property="airPortTo"  escapeXml="true"  titleKey="cargobusiness.column.airportto"  sortable="true"  />
   		    <display:column property="arrivePort"  escapeXml="true"  titleKey="cargobusiness.column.arriveport"  sortable="true"  />		    
  		    <display:column property="cwConfirmDate"  escapeXml="true"  titleKey="cargobusiness.column.cwconfirmdate"  sortable="true" />
  		    <display:column property="goodsWeight"  escapeXml="true"  titleKey="cargobusiness.column.goodsweight"  sortable="true" />
  		    <display:column property="goodsSize"  escapeXml="true"  titleKey="cargobusiness.column.goodssize"  sortable="true" />
  		    <display:column property="profit"  escapeXml="true"  titleKey="cargobusiness.column.profit"  sortable="true" />
            <display:column property="businessName"  escapeXml="true"  titleKey="cargobusiness.column.business"  sortable="true"/>
            <display:column property="docOpName"  escapeXml="true"  titleKey="cargobusiness.column.docop"  sortable="true"/>
  		    <display:column property="csCodeName"  escapeXml="true"  titleKey="cargobusiness.column.cscode"  sortable="true"  />
		    <display:column property="operatorName"  escapeXml="true"  titleKey="cargobusiness.column.operator" sortable="true"></display:column>
            <display:column property="operateDate"  escapeXml="true"  titleKey="cargobusiness.column.operatedate" sortable="true"></display:column>		    
		    <display:column property="companyId"  escapeXml="true"  titleKey="cargobusiness.column.companyid" sortable="true"></display:column>
		</display:table>

</div>
</s:form>

<div id="invoiceList" >
  <div id="closebtn"><a href="#" onclick="return $('#invoiceList').attr('style','display:none');">Close</a></div>
  <div id="invoicegrid" style="margin: 5px; padding: 0">
  </div>
</div>






<script type="text/javascript">

	
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
    
    
    
    function viewInvoice(id,planeOcean)
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
    	
    	if ($("#invoiceList").attr("currentId")!=id)
    		{
        
        	
				reportfareinvoiceDwr.getInvoiceGson(id,planeOcean, function(data) {
					eval("var gsondata={Rows:"+data+"};");
					if  (gsondata.Rows.length>0)
					{
						var grid=$("#invoicegrid").ligerGrid({
							columns: [ 
							{display: '<delmar:message key="cargoinvoice.column.invoiceno"/>', name: 'invoiceNo',width:80} ,				          
			                { display: '<delmar:message key="cargoinvoice.column.cusname"/>', name: 'cusCodeName', minWidth: 80 },                
							{ display: '<delmar:message key="cargoinvoice.column.creditdebit"/>', name: 'creditDebit', minWidth: 80},
							{ display: '<delmar:message key="cargoinvoice.column.currencytype"/>', name: 'currencyType', minWidth: 80 },
							{ display: '<delmar:message key="cargoinvoice.column.transprofit"/>', minWidth: 80,render:function (rowdata,rowindex,value){
								   var h="";
				    			   h+=rowdata.transProfit;
				    			   if (rowdata.balance!=0)
				    				   h+="<font color='#ff0000'>("+rowdata.balance+")</font>";
				         		   return h;
								} },
							{ display: '<delmar:message key="cargoinvoice.column.billno"/>', name: 'operatorName', minWidth: 80,render:function (rowdata,rowindex,value){
								   var h="";
								   if (rowdata.billNo!=undefined)
				    			     h+=rowdata.billNo+"("+rowdata.billDate+")";
				         		   return h;
								} },
							{ display: '<delmar:message key="cargoinvoice.column.operatorname"/>', name: 'operatorName', minWidth: 80 },
							{ display: '<delmar:message key="cargoinvoice.column.createdate"/>', name: 'operateDate', minWidth: 80 }
							], data: gsondata, usePager:false, sortName: 'invoiceNo', frozen:false,
							width: 780,rownumbers:true,headerRowHeight:26,isScroll: false,
							detail: { onShowDetail: f_showfareinfo },detailHeight:180
						});
					}
					
				});    	
    		}
		
		document.all.invoiceList.style.left=(selObjPos.left+30)+"px";
		document.all.invoiceList.style.top=(selObjPos.top+20)+"px";
		document.all.invoiceList.style.display="inline";

		
		$("#invoiceList").attr("currentId",id);

    }
    
  
    function f_showfareinfo(row, detailPanel,callback)
    {
    	
		var grid = document.createElement('div'); 
	    $(detailPanel).append(grid);      
        
	    var gsondata;

	    fareinfoDwr.getFareInfoGson(row.invoiceNo, function(data) {
		   eval("gsondata={Rows:"+data+"};");
		   
	        $(grid).css('margin',10).ligerGrid({
				columns: [ 
	            { display: '<delmar:message key="cargofareinfo.column.farename"/>', name: 'fareName', minWidth: 80},                
				{ display: '<delmar:message key="cargofareinfo.column.operacount"/>', minWidth: 80,render:function (rowdata,rowindex,value){
				   var h="";
				   
	 			   if (rowdata.recedeal==row.receDeal)
	 				  h=rowdata.operAcount; 				
	 			   else
	 				  h="<font color='#ff0000'>("+rowdata.operAcount+")</font>"; 				   
	 				   
	      		   return h;
					} },
				
				{ display: '<delmar:message key="cargofareinfo.column.operatorname"/>', name: 'operatorName', minWidth: 80 }				
				], data: gsondata, usePager:false, sortName: 'fareName',
				width: 700,rownumbers:true,headerRowHeight:26,isScroll: false
			});
		        
			});

        

		
    }
   
    
    

   
    
</script>

</body>
</html>
