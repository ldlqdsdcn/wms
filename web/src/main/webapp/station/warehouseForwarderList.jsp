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
	
//新增进仓明细
function addNewJCRecord(status,lockDate,warehouseNo){
	if (status!=1){
	  alert('<delmar:message key="wfDetail.message.statusLocked" />');
	  return;
	}
	//if (lockDate<cdate){
	//  alert("记录已经锁定，不能够进行增加");
	//  return;
	//}
	
	window.location='<c:url value="/station/wfDetail_initDetail.action"/>?warehouseNo='+warehouseNo;
}
	
</script>
</head>

<body >


<s:form action="warehouseForwarder_list" namespace="/station"  theme="simple" >
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
			   <td  class="d-tdlabel">
			       	<delmar:message key="warehouseForwarder.column.airPortTo" />
			   </td>
			   <td class="d-tdinput"> 
			       	<s:textfield name="airPortTo" value="%{#session.MAP_KEY_OF_SESSION.airPortTo}"></s:textfield>
			    </td>
			     
			    <td class="d-tdlabel"> 	
		       	<delmar:message key="warehouseForwarder.column.status" />
		       </td>
		      <td class="d-tdinput" > 
			    	<s:select list="stationStatusList" listKey="value" listValue="name"  name="status"  multiple="true" value="%{#session.MAP_KEY_OF_SESSION.status}"  id="status" ></s:select>
			    	<s:hidden name="statusId" id="statusId" value="%{#session.MAP_KEY_OF_SESSION.statusId}" />
		      </td>					      
			      <td class="d-tdinput"> 	
			      </td>
			      <td class="d-tdinput" >
			      </td>				      
			      		   
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
		
    	<display:column titleKey="warehouseForwarder.column.open" >  
    		<!-- 如果状态为1且锁定时间大于当前时间，那么展开的进仓明细可编辑及删除，否则只能查看 -->
    		<c:if test="${list.status*1 == 1 && list.lockDate >= list.nowDate}">
				<a href="#" class="atest" onclick="javascript:viewRecord('<c:out value="${list.id}"/>','edit')"><delmar:message key="warehouseForwarder.column.open" /></a>
			</c:if>
			<c:if test="${!(list.status*1 == 1 && list.lockDate >= list.nowDate)}">
				<a href="#"  onclick="javascript:viewRecord('<c:out value="${list.id}"/>','search')"><delmar:message key="warehouseForwarder.column.open" /></a>
     		  </a>
			</c:if>
    	</display:column>
    	<display:column  titleKey="warehouseForwarder.column.relatedOperation">
    	
    		<c:if test="${list.realID*1 != 0}">
				<img src='<%=request.getContextPath()%>/images/warehousein.png'
					style='border: 0px; margin-right: 5px' title='具有相应的进仓记录' />&nbsp;
			</c:if>
			<c:if test="${list.realID*1 == 0}">
				<span style='width: 16px; margin-right: 5px'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</c:if>
			<c:if test="${list.status*1 == 1 && list.lockDate >= list.nowDate}">
				<a href='javascript:void(0)'
					onclick="addNewJCRecord('<c:out value="${list.status}"/>','<c:out value="${list.lockDate}"/>','<c:out value="${list.warehouseNo}"/>')"><delmar:message key="wfDetail.message.add" /></a>
			</c:if>
			<c:if test="${list.status*1 == 2 || list.lockDate < list.nowDate}">
				<font color='#ff0000' style='margin-left: 5px'>Locked</font>
			</c:if>
    	</display:column>
    	<display:column  property="warehouseNo"  escapeXml="true" titleKey="warehouseForwarder.column.warehouseNo"  sortable="true" ></display:column>
    	<display:column  property="flightDate" escapeXml="true" titleKey="warehouseForwarder.column.flightDate" sortable="true"></display:column>
    	<display:column  property="lockDate" escapeXml="true" titleKey="warehouseForwarder.column.lockDate" sortable="true" ></display:column>
    	
    	<display:column  titleKey="warehouseForwarder.column.goodsNumber" sortable="true" >
	    	<c:if test="${list.realID*1 != 0}">
	    		<c:if test="${list.goodsNumber*1 == list.realGoodsNumber*1}">
	    			<c:out value="${list.goodsNumber}"></c:out>
				</c:if>
				<c:if test="${list.goodsNumber*1 != list.realGoodsNumber*1}">
						<a href='javascript:void(0)' title="<delmar:message key="wfDetail.message.realGoodsSize" />:${list.realGoodsNumber}">
							<font color='#ff0000'>
								<c:out value="${list.goodsNumber}"></c:out>
							</font> 
						</a>
				</c:if>
	    	</c:if>
	    	<c:if test="${list.realID*1 == 0}">
	    		<c:out value="${list.goodsNumber}"></c:out>
	    	</c:if>
    	</display:column>
    	<display:column  titleKey="warehouseForwarder.column.goodsWeight" sortable="true">
	    	<c:if test="${list.realID*1 != 0}">
	    		<c:if test="${list.goodsWeight*1 == list.realGoodsWeight*1}">
	    			<c:out value="${list.goodsWeight}"></c:out>
				</c:if>
				<c:if test="${list.goodsWeight*1 != list.realGoodsWeight*1}">
						<a href='javascript:void(0)' title="<delmar:message key="wfDetail.message.realGoodsSize" />:${list.realGoodsWeight}">
							<font color='#ff0000'>
								<c:out value="${list.goodsWeight}"></c:out>
							</font> 
						</a>
				</c:if>
	    	</c:if>
	    	<c:if test="${list.realID*1 == 0}">
	    		<c:out value="${list.goodsWeight}"></c:out>
	    	</c:if>
    	</display:column>
    	<display:column  titleKey="warehouseForwarder.column.goodsSize" sortable="true" >
	    	<c:if test="${list.realID*1 != 0}">
	    		<c:if test="${list.goodsSize*1 == list.realGoodsSize*1}">
	    			<c:out value="${list.goodsSize}"></c:out>
				</c:if>
				<c:if test="${list.goodsSize*1 != list.realGoodsSize*1}">
						<a href='javascript:void(0)' title="<delmar:message key="wfDetail.message.realGoodsSize" />:${list.realGoodsSize}">
							<font color='#ff0000'>
								<c:out value="${list.goodsSize}"></c:out>
							</font> 
						</a>
				</c:if>
	    	</c:if>
	    	<c:if test="${list.realID*1 == 0}">
	    		<c:out value="${list.goodsSize}"></c:out>
	    	</c:if>
    	</display:column>
    	<display:column  property="numberPackage" titleKey="warehouseForwarder.column.numberPackage" sortable="true" ></display:column>
    	<display:column  property="goodsDesc" titleKey="warehouseForwarder.column.goodsDesc" sortable="true" style="width:200px;"></display:column>
    	<display:column  escapeXml="true" titleKey="warehouseForwarder.column.status" sortable="true">
	    	<c:if test="${list.status*1==1}">
				 <delmar:message key="warehouseForwarder.column.issued" />
			</c:if>
			<c:if test="${list.status*1!=1}">
				<delmar:message key="warehouseForwarder.column.complete" />
			</c:if>
		</display:column>
    	
    	<display:column  property="createDate" titleKey="warehouseForwarder.column.createDate" sortable="true"></display:column>
    	
		</display:table>

</div>
</s:form>

<div id="recordList" >
  <div id="closebtn"><a href="#" onclick="btn_close();">Close</a></div>
  <div id="persongrid" style="margin: 5px; padding: 0">
  </div>
</div>





<script type="text/javascript">
var grid;
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
    
    
    
    function viewRecord(id, flag){
    	var theSrcElement;
    	if (getBrowserInfo()=="FF"){
    		var theEvent = window.event || arguments.callee.caller.arguments[0];
    		theSrcElement=theEvent.target;
    	}else{
    		theSrcElement=event.srcElement;
        }
       
    	var pubTrElement=findRowTR(theSrcElement);    	  	
    	var selObjPos = getXY(theSrcElement); 
    	
    	//将div删掉重新增加，否则grid的数据没有刷新
    	var recordList = document.getElementById("recordList");
   	 	recordList.removeChild(document.getElementById("persongrid"));
	 	var newdiv = document.createElement("div");
	 	newdiv.id ="persongrid";
		recordList.appendChild(newdiv);
   	
    	if ($("#recordList").attr("currentId")!=id){
    		linkStationRecordDwr.getLinkStationRecordGson(id, function(data) {
    			
					eval("var gsondata={Rows:"+data+"};");
					if  (gsondata.Rows.length>0){
						 grid=$("#persongrid").ligerGrid({
							columns: [ 
							{display: '<delmar:message key="warehouseForwarder.column.operation" />', name: 'id',width:120,render:function (rowdata,rowindex,value){
								
								   var h = "";
								   if (flag == 'edit') {
					    			   h += "<a href='#' onclick=\"javascript:window.location='<c:url value="/station/wfDetail_initDetail.action"/>";
					    			   h += "?updateFlag=wareHouseForwardUpdate&id="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key="common.label.edit" /></a>";
					    			   
					    			   h += "<a href='#' onclick=\"javascript:window.location='<c:url value="/station/wfDetail_delete.action"/>";
					    			   h += "?id="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key="common.button.delete" /></a>";
					    			   
					    			   
				    			   } else {
				    				   h += "<a href='#' onclick=\"javascript:window.location='<c:url value="/station/wfDetail_initDetail.action"/>";
					    			   h += "?id="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key="common.button.view" /></a>";
				    			   }
								   
								   h += "<a href='#' onclick=\"javascript:print(" + rowdata.id + ")\" style='padding-left:10px'><delmar:message key="common.button.print" /></a>";
				    			  
				         		   return h;
								} },
							{display: '<delmar:message key="wfDetail.column.referenceNo" />', name: 'referenceNo',width:100},
							{display: '<delmar:message key="wfDetail.column.inDate" />', name: 'inDate',width:150},
							{display: '<delmar:message key="warehouseForwarder.column.goodsNumber" />', name: 'goodsNumber',width:70},
							{display: '<delmar:message key="warehouseForwarder.column.goodsWeight" />', name: 'goodsWeight',width:70},
							{display: '<delmar:message key="warehouseForwarder.column.goodsSize" />', name: 'goodsSize',width:70},
							{display: '<delmar:message key="wfDetail.column.receiptPerson" />', name: 'receiptPerson',width:80},
							{display: '<delmar:message key="wfDetail.column.carDriver" />', name: 'carDriver',width:85}
							], data: gsondata, usePager:false, sortName: 'inDate', frozen:false,
							width: 780,rownumbers:true,headerRowHeight:26,isScroll: false,
							detailHeight:160
						});
					}
				});    	
    		}
		document.all.recordList.style.left=(selObjPos.left+30)+"px";
		document.all.recordList.style.top=(selObjPos.top+20)+"px";
		document.all.recordList.style.display="inline";
		//document.all.recordList.style.display="none";	
		$("#recordList").attr("currentId",id);
    }
    
    function print(id) {
    	window.open("<%=request.getContextPath()%>/station/wfDetail_print.action?id="+id,"newwindow","height=500px,width=800px,top=100px,left=300%,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no") 
    }
    
 function btn_close() {
	 //$("#persongrid").ligerGrid({});
	 //$("#persongrid").find("tbody").children().remove();
	 //$("#persongrid").attr('innerHTML','');
	 
	 
	 return $('#recordList').attr('style','display:none');
 }

    
    
    
    
    
        
   
    
</script>

</body>
</html>
