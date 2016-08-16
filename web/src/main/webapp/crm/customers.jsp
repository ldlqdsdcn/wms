<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>



<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>


<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/multiselect/src/jquery.multiselect.js"></script>
<link rel="Stylesheet" href="../js/jquery/plugin/multiselect/jquery.multiselect.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/plugin/multiselect/i18n/jquery.multiselect.<s:property value='#session.currentlanguagelowercase'/>.js"></script>

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>



<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkmanDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkrecordDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>



<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}

#personList{
	position:absolute;
	width:800px;
	top:15px;
	background-color:lightyellow;
	border:2px solid #7C96E2;
	padding:0px;
	display:none;
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
	  $("#btnQuery").click(function(event)
	 		 {
	   	       $("#statusId").val($("#status").val());
		        if (document.all.userOrgs)
		        {
		        	$("#orgIds").val($("#userOrgs").val());		        	
		        }
		        
		        var waitingdialog=showWaiting("<delmar:message key='public.dialg.waitingsearch' cn='数据正在进行查询，请稍后'/>",false);		        
	
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
    	
    	jQuery('#visitDateBegin').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",    		
            buttonImage: "../images/DatePicker.GIF",
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,
            onClose:function(selectedDate) {
               $("#visitDateEnd").datepicker("option","minDate",selectedDate);	
            }
    	    });
    	
    	jQuery('#visitDateEnd').datepicker({changeMonth: true,changeYear: true,showOn: "button",
    		dateFormat:"yy-mm-dd",    		
            buttonImage: "../images/DatePicker.GIF",            
            buttonImageOnly: true,
            buttonText: "Select date",
            numberOfMonths:2,
            onClose:function(selectedDate) {
               $("#visitDateBegin").datepicker("option","maxDate",selectedDate);	
            }
    	    });
    	
       
    	jQuery("#status").multiselect(
    	{
    		initValue:$("#statusId").val(),
    		minWidth:200
    	});
    	
  	
   		var varray=$("#statusId").val().split(",");
   		for (var i in varray)
   		{
   	      $("#status").find("option[value='"+varray[i]+"']").attr("selected",true);
   		}
   		
  	
        
  	  $("#overDays").keypress(function(event)
		{
  	       return keypressNumber(event);
		});
  	  
      $("#overDays").blur(function(event){
    	  
    	     if ($("#overDays").val()=="")
    	    	 return;
    	    
   		     validateIntegerObj($("#overDays"));

		});	
      
 		if (document.all.userOrgs)
		{
	    	jQuery("#userOrgs").multiselect(
	    	    	{
	    	    		initValue:$("#orgIds").val(),
	    	    		minWidth:200
	    	    	});
	    	    	
	    	      	
	 	    	var varray=$("#orgIds").val().split(",");
	 	    	for (var i in varray)
	 	    	{
	 	          $("#userOrgs").find("option[value='"+varray[i]+"']").attr("selected",true);
	 	    	}
		}	
 		
 		
 		
 		$("#userName").attr("placeholder",$("#userName").attr("title"));
    	      
      

       
	});
	
	
	



 

 
</script>
</head>

<body >


<s:form action="customer_list" namespace="/crm"  theme="simple" >
<div style="margin:5px">

<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="customer.location" /></td>
          <td class="navig" align="right"> 
          <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                <delmar:operatePriv operator="create">
                  <s:submit method="create" cssClass="input_submit"  value="%{#session.resource.get('common.button.create')}" ></s:submit>
                 </delmar:operatePriv>
                  <delmar:operatePriv operator="delete">
                  <s:submit method="deletes" cssClass="input_submit"  value="%{#session.resource.get('common.button.delete')}"   onclick="return confirmListDelete('ids')"></s:submit>
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
			     <delmar:message key="customer.column.cuscode" /> 
			   </td>
			   <td   class="d-tdinput"> 
			       	<s:textfield name="cusCode" value="%{#session.MAP_KEY_OF_SESSION.cusCode}" cssClass="d-searchtext"></s:textfield>
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="customer.column.opscode"/>
			   </td>
			   <td class="d-tdinput"> 
			       	<s:textfield name="opsCode" value="%{#session.MAP_KEY_OF_SESSION.opsCode}" cssClass="d-searchtext"></s:textfield>
			    </td>
		      			     
			   
			     <td class="d-tdlabel"> 	
			      <delmar:message key="customerext.column.proplabel"/>
			      </td>
			      <td class="d-tdinput" >
			      	<s:textfield name="propLabel" value="%{#session.MAP_KEY_OF_SESSION.propLabel}" cssClass="d-searchtext"></s:textfield>
			      </td>				     
			    <td class="d-tdlabel"> 	
		       	<!--To check whether the user have the group privilege  -->
		       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
		    	<delmar:message key="customer.column.org" />
		    	</c:if>
		       </td>
		      <td class="d-tdinput" > 
		       
		       
		        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
			    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs"  cssClass="d-searchtext"></s:select>
			    	
			    	<s:textfield name="userName" id="userName" value="%{#session.MAP_KEY_OF_SESSION.userName}" cssClass="d-searchtext"  title="%{#session.resource.get('customer.inputtitle.username')}"></s:textfield>
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
                  <td  class="d-tdlabel">
			       	<delmar:message key="customer.column.name" />
			     </td>
			     <td class="d-tdinput">  	
			       	<s:textfield name="cusName" value="%{#session.MAP_KEY_OF_SESSION.cusName}" cssClass="d-searchtext"></s:textfield>
			      </td>
			     <td  class="d-tdlabel">
			       	<delmar:message key="customer.column.address" />
			      </td>
			      <td class="d-tdinput"> 	
			       	<s:textfield name="address" value="%{#session.MAP_KEY_OF_SESSION.address}" cssClass="d-searchtext"></s:textfield>	&nbsp;&nbsp;
			      </td>	
			      
			      <td class="d-tdlabel"> 	
			         <delmar:message key="customer.column.overdays" />
			      </td>
			      <td class="d-tdinput"> 	
			         <s:textfield  id="overDays"   name="overDays" value="%{#session.MAP_KEY_OF_SESSION.overDays}" cssClass="d-searchtext d-inputnumber"  title="%{#session.resource.get('public.input.numberonly')}"></s:textfield>
			       </td>
			       			      
			        <td class="d-tdlabel">
			       	<delmar:message key="customer.column.statusid" />
			      </td>
			      <td class="d-tdinput"> 	

			    	<s:select list="statusList" listKey="datadictId" listValue="name"  name="status"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="status"  cssClass="d-searchtext" ></s:select>
			    	<s:hidden name="statusId" id="statusId" value="%{#session.MAP_KEY_OF_SESSION.statusId}" />       
			      </td>
			      

			       			     			      
			      
			      </tr>
			       <tr>
			      <td class="d-tdlabel"> 	
			       	<delmar:message key="public.column.created" />
			      </td>
			      <td class="d-tdinput" colspan=3> 				       	
			   		<s:textfield  id="createDateBegin"  readonly="true"  name="createDateBegin" value="%{#session.MAP_KEY_OF_SESSION.createDateBegin}" cssClass="d-searchtext" ></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="createDateEnd"  readonly="true"  name="createDateEnd" value="%{#session.MAP_KEY_OF_SESSION.createDateEnd}" cssClass="d-searchtext"></s:textfield>   		
			       </td>

			       <td class="d-tdlabel">
			       	<delmar:message key="customer.column.visitdate" />
			      </td>
			      <td class="d-tdinput" colspan=3> 	
			       	
			   		<s:textfield  id="visitDateBegin"  readonly="true"  name="visitDateBegin" value="%{#session.MAP_KEY_OF_SESSION.visitDateBegin}" cssClass="d-searchtext"></s:textfield>
			   		<delmar:message key="public.column.to" />
			        <s:textfield  id="visitDateEnd"  readonly="true"  name="visitDateEnd" value="%{#session.MAP_KEY_OF_SESSION.visitDateEnd}" cssClass="d-searchtext"></s:textfield>
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
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.customerList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true"  decorator="com.delmar.crm.web.displaytag.decorator.CustomerTableDecorator">
		
		<delmar:operatePriv operator="delete">
		<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
				
		</display:column>
		</delmar:operatePriv>
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf" style="width:40px;">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="customer.column.cuscode" sortable="true" media="html" style="width:160px;">
		      <a id='customer<c:out value="${list.id}"/>' href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.cusCode}"/>
     		  </a>
     		  <br>
			<div id="operate<c:out value="${list.id}"/>" class="d-rowactions">
			 <a  href="#"  onclick="javascript:viewLinkman('<c:out value="${list.id}"/>')"><delmar:message key="customer.operate.viewlinkman"/></a>|
			 <a href="#" onclick="javascript:viewLinkrecord('<c:out value="${list.id}"/>')"><delmar:message key="customer.operate.viewlinkrecord"/></a>|
			</div>     		  
		    </display:column>
		    <display:column property="opsCode"  escapeXml="true"  titleKey="customer.column.opscode" sortable="true"  style="width:80px;"></display:column>	
		    <display:column   titleKey="customer.column.name" sortable="true" media="html"  style="width:160px;">
		    <c:out value="${list.name}"/>
			<div id="operatetwo<c:out value="${list.id}"/>" class="d-rowactions">
             <a href="#" onclick="javascript:window.location='<c:url value="/crm/linkman_create.action"/>?from=customer&fromid=${list.id}'"><delmar:message key="customer.button.createlinkman"/></a> |			 
			 <a href="#" onclick="javascript:window.location='<c:url value="/crm/linkrecord_create.action"/>?from=customer&fromid=${list.id}'"><delmar:message key="customer.button.createlinkrecord"/></a>
			</div>		    
		    </display:column>	
            <display:column property="statusId"  escapeXml="true"  titleKey="customer.column.statusid" sortable="true"  style="width:60px;"  decorator="com.delmar.base.web.displaytag.decorator.DatadictDecorator"></display:column>
            <display:column property="legalPerson"  escapeXml="true"  titleKey="customer.column.legalperson" sortable="true" style="width:60px;"></display:column>
			<display:column property="telephone"  escapeXml="true"  titleKey="customer.column.telephone" sortable="true" style="width:60px;"></display:column>            						
			<display:column property="customerExtra.firstLinkTime"  escapeXml="true"  titleKey="customerext.column.firstlinktime" sortable="true" style="width:100px;" decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"></display:column>
			<display:column property="customerExtra.lastLinkTime"  escapeXml="true"  titleKey="customerext.column.lastlinktime" sortable="true" style="width:100px;" decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"></display:column>			
			<display:column property="customerExtra.nextLinkTime"  escapeXml="true"  titleKey="customerext.column.nextlinktime" sortable="true" style="width:100px;" decorator="com.delmar.core.web.displaytag.decorator.DateHourDecorator"></display:column>
		    <display:column property="userName"  escapeXml="true"  titleKey="public.column.user"  sortable="true" style="width:60px;"/>
		    <display:column property="created"  escapeXml="true"  titleKey="public.column.created"  sortable="true"   style="width:60px;" decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		</display:table>

</div>
</s:form>

<div id="personList" >
  <div id="closebtn"><a href="#" onclick="return $('#personList').attr('style','display:none');">Close</a></div>
  <div id="persongrid" style="margin: 5px; padding: 0">
  </div>
</div>
<div id="recordList">
  <div id="closebtn"><a href="#" onclick="return $('#recordList').attr('style','display:none');">Close</a></div>  
  <div id="recordgrid" style="margin: 5px; padding: 0">
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
    
    
    
    function viewLinkman(id)
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
    	
    	if ($("#personList").attr("currentId")!=id)
    		{
				linkmanDwr.getLinkmanGson(id, function(data) {
					eval("var gsondata={Rows:"+data+"};");
					if  (gsondata.Rows.length>0)
					{
						var grid=$("#persongrid").ligerGrid({
							columns: [ 
							{display: '<delmar:message key="linkman.column.linkcode"/>', name: 'linkCode',width:80} ,				          
			                { display: '<delmar:message key="linkman.column.name"/>', name: 'name', minWidth: 80 },                
							{ display: '<delmar:message key="linkman.column.busphone"/>', name: 'busPhone', minWidth: 80},
							{ display: '<delmar:message key="linkman.column.phoneno"/>', name: 'phoneNo', minWidth: 80 },
							{ display: '<delmar:message key="linkman.column.email"/>', name: 'email', minWidth: 80 },				
							{ display: '<delmar:message key="public.button.operate"/>', minWidth: 80,render:function (rowdata,rowindex,value){
								   var h="";
				    			   h+="<a href='#' onclick=\"javascript:window.location='<c:url value="/crm/linkrecord_create.action"/>?from=linkman&fromid="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key='linkman.button.createlinkrecord'/></a>";
				         		   return h;
								} }
							], data: gsondata, usePager:false, sortName: 'linkCode', frozen:false,
							width: 780,rownumbers:true,headerRowHeight:26,isScroll: false,
							detail: { onShowDetail: f_showlinkrecord },detailHeight:160
						});
					}
					
				});    	
    		}
		
		document.all.personList.style.left=(selObjPos.left+30)+"px";
		document.all.personList.style.top=(selObjPos.top+20)+"px";
		document.all.personList.style.display="inline";
		document.all.recordList.style.display="none";	
		
		$("#personList").attr("currentId",id);

    }
    

    function f_showlinkrecord(row, detailPanel,callback)
    {
    	
        var grid = document.createElement('div'); 
        $(detailPanel).append(grid);
        
    	var gsondata;
    	linkrecordDwr.getLinkrecordGson(row.id,'linkman', function(data) {    		
			eval("gsondata={Rows:"+data+"};");
			
	        $(grid).css('margin',10).ligerGrid({
				columns: [ 
	            { display: '<delmar:message key="linkrecord.column.contactdate"/>', name: 'contactDate', minWidth: 80},                
				{ display: '<delmar:message key="linkrecord.column.contacttitle"/>', name: 'contactTitle', minWidth: 80 },
				{ display: '<delmar:message key="linkrecord.column.nexttime"/>', name: 'nextTime', minWidth: 80 },
				{ display: '<delmar:message key="linkrecord.column.createdby"/>', name: 'createdByName', minWidth: 80 }				
				], data: gsondata, usePager:false, sortName: 'contactDate',
				width: 700,height:200,rownumbers:true,headerRowHeight:26,isScroll: false, 
				onAfterShowData: callback,frozen:false
			});			
			
			
		});
    	
        
        

		
    }
   
    
    
    function viewLinkrecord(customerid)
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
    	var selObjPos = getXY(theSrcElement);
    	if ($("#recordList").attr("currentId")!=customerid) 
    		{
    	
				linkrecordDwr.getLinkrecordGson(customerid,'customer', function(data) {
				
					eval("var gsondata={Rows:"+data+"};");
					
					if  (gsondata.Rows.length>0)
						{
							var grid=$("#recordgrid").ligerGrid({
								columns: [ 
				                { display: '<delmar:message key="linkrecord.column.contactdate"/>', name: 'contactDate', minWidth: 80,format:'yyyy-MM-dd',frozen: true },                
								{ display: '<delmar:message key="linkrecord.column.contacttitle"/>', name: 'contactTitle', minWidth: 80,frozen: true },
								{ display: '<delmar:message key="linkrecord.column.nexttime"/>', name: 'nextTime', minWidth: 80,format:'yyyy-MM-dd',frozen: true },
								{ display: '<delmar:message key="linkrecord.column.createdby"/>', name: 'createdByName', minWidth: 80,frozen: true }				
								], data: gsondata, usePager:false, sortName: 'contactDate',
								width: 780,height:200,rownumbers:true,headerRowHeight:26
							});
							
						}
					
				});    	
    		}
		
		document.all.recordList.style.left=(selObjPos.left+30)+"px";
		document.all.recordList.style.top=(selObjPos.top+20)+"px";
		document.all.recordList.style.display="inline";
		document.all.personList.style.display="none";
		
		$("#recordList").attr("currentId",customerid);
    }
        
   
    
</script>

</body>
</html>
