<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/plugin/multiselect/jquery.multiselect.css" type="text/css" />
<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript" src="../js/jquery/plugin/multiselect/src/jquery.multiselect.js"></script>
<script type="text/javascript" src="../js/jquery/plugin/multiselect/i18n/jquery.multiselect.<s:property value='#session.currentlanguagelowercase'/>.js"></script>
<script  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></script>
<script type='text/javascript' src='../dwr/interface/linkStationRecordDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkBankCategoryDwr.js'></script>
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
	       $("#beCalcScoreId").val($("#beCalcScore").val());
	       $("#beOpenId").val($("#beOpen").val());
	       $("#beStateId").val($("#beState").val());
	        if (document.all.userOrgs) {
	        	$("#orgIds").val($("#userOrgs").val());		        	
	        }	   	       

		 });
	jQuery("#beCalcScore").multiselect({
		
		initValue:$("#beCalcScoreId").val(),
		minWidth:180
	});
	
	jQuery("#beOpen").multiselect({
		
		initValue:$("#beOpenId").val(),
		minWidth:180
	});
	
	jQuery("#beState").multiselect({
		
		initValue:$("#beStateId").val(),
		minWidth:180
	});
	
		var varray=$("#beCalcScoreId").val().split(",");
		for (var i in varray){
	      $("#beCalcScore").find("option[value='"+varray[i]+"']").attr("selected",true);
		}
		
		 varray=$("#beOpenId").val().split(",");
		for (var i in varray){
	      $("#beOpen").find("option[value='"+varray[i]+"']").attr("selected",true);
		}
		
		 varray=$("#beStateId").val().split(",");
		for (var i in varray){
	      $("#beState").find("option[value='"+varray[i]+"']").attr("selected",true);
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

function validate(ids) {
	
	if (confirmListDelete(ids)) {
		$.ajax({  
	        url:'officeTestBank_validateCanDelete.action?ids='+ids,  
	        type:'post',  
	        success:function (data) {  	        	 
	      	 window.location.reload();	        
	        },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }  
	    });  
	}
	
}

	
</script>
</head>

<body >


<s:form action="officeTestBank_list" namespace="/officeTest"  theme="simple" >
	<div style="margin:5px">
	
		<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
	         <td class="navig" align="right"> 
	          <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
	              <tr> 
	                <td >
	                <delmar:operatePriv operator="create">
	                  <s:submit method="create" cssClass="input_submit"  value="%{#session.resource.get('common.button.create')}" ></s:submit>
	                 </delmar:operatePriv>
	                  <delmar:operatePriv operator="delete">
	                  <s:submit method="deletes" cssClass="input_submit"  value="%{#session.resource.get('common.button.delete')}"   onclick="return validate('ids')"></s:submit>
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
					   		<delmar:message key="officeTestBank.column.code" />:
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:textfield name="code" id="code" cssClass="d-searchtext"></s:textfield>
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="officeTestBank.column.name" />:
					   </td>
					   <td class="d-tdinput"> 
					       	<s:textfield name="name" id="name" cssClass="d-searchtext"></s:textfield>
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestBank.column.beCalcScore" />：
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="booleanList" listKey="value" listValue="name"  name="status"  multiple="true"  id="beCalcScore" ></s:select>
			    			<s:hidden name="beCalcScoreId" id="beCalcScoreId" />
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="officeTestBank.column.beOpen" />:
					   </td>
					   <td class="d-tdinput">
					   		<s:select list="booleanList" listKey="value" listValue="name"  name="status"  multiple="true" id="beOpen" ></s:select>
			    			<s:hidden name="beOpenId" id="beOpenId" /> 
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestBank.column.beState" />：
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="booleanList" listKey="value" listValue="name"  name="status"  multiple="true"  id="beState" ></s:select>
			    			<s:hidden name="beStateId" id="beStateId" />
					   </td>
					   <td class="d-tdlabel"> 	
					       	<c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
					    		<delmar:message key="customer.column.org" />:
					    	</c:if>
				      	</td>
				      	<td class="d-tdinput" > 
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='true'}">
						    	<s:select list="userOrgAccessList" listKey="id" listValue="name"  name="userOrgs"  multiple="true"   id="userOrgs" ></s:select>
					       	</c:if>
					       	
					        <c:if test="${MAP_KEY_OF_SESSION.orgVisible=='org'}">
						    	<delmar:message key="public.accessprivilege.org"/>
					       	</c:if>
					       			       	
					       	<s:hidden name="orgIds" id="orgIds"/>
				      	</td>		
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestBank.column.remark" />：
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:textfield name="remark" id="remark" cssClass="d-searchtext"></s:textfield>
					   </td>
					   <td class="d-tdinput" colspan="2">       	
					   		&nbsp;
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
	 <display:table name="officeTestBankList" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		<delmar:operatePriv operator="delete">
			<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
				<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
			</display:column>
		</delmar:operatePriv>
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<display:column titleKey="warehouseForwarder.column.open" >  
				<a href="#" class="atest" onclick="javascript:viewRecord('<c:out value="${list.id}"/>','edit')"><delmar:message key="warehouseForwarder.column.open" /></a>
    	</display:column>
    	<display:column  titleKey="warehouseForwarder.column.relatedOperation">
    		<c:if test="${list.hasBankCategorys}">
				<img src='<%=request.getContextPath()%>/images/warehousein.png'
					style='border: 0px; margin-right: 5px' title='<delmar:message key="officeTestBank.message.hasCategorys" />' />&nbsp;
			</c:if>
			<c:if test="${!list.hasBankCategorys}">
				<span style='width: 16px; margin-right: 5px'>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</c:if>
			
			<a href='javascript:void(0)' onclick="addNewJCRecord('<c:out value="${list.id}"/>')"><delmar:message key="officeTestBank.button.addCategory" /></a>
    	</display:column>
    	<display:column  titleKey="officeTestBank.column.code"  sortable="true" >
    		<a href='javascript:void(0)' onclick="edit('<c:out value="${list.id}"/>')"><c:out value="${list.code}"/></a>
    	</display:column>
    	<display:column property="name" escapeXml="true" titleKey="officeTestBank.column.name"  sortable="true"></display:column>
    	<display:column  property="timeLimit" escapeXml="true" titleKey="officeTestBank.column.timeLimit"  sortable="true" ></display:column>
    	<display:column  property="questionCount" escapeXml="true" titleKey="officeTestBank.column.questionCount"  sortable="true" ></display:column>
    	<display:column  property="beCalcScore" decorator="com.delmar.officeTest.web.displaytag.decorator.BooleanDecorator" 
    		escapeXml="true" titleKey="officeTestBank.column.beCalcScore"  sortable="true" ></display:column>
    	<display:column  property="beOpen" decorator="com.delmar.officeTest.web.displaytag.decorator.BooleanDecorator" 
    		escapeXml="true" titleKey="officeTestBank.column.beOpen"  sortable="true" ></display:column>
    	<display:column  property="beState" decorator="com.delmar.officeTest.web.displaytag.decorator.BooleanDecorator" 
    		escapeXml="true" titleKey="officeTestBank.column.beState"  sortable="true" ></display:column>
    	<display:column  property="created" escapeXml="true" titleKey="officeTestBank.column.timeLimit"  sortable="true" 
    	decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"></display:column>
	  </display:table>
	</div>
</s:form>
<div id="recordList" >
  <div id="closebtn"><a href="#" onclick="btn_close();">Close</a></div>
  <div id="persongrid" style="margin: 5px; padding: 0">
  </div>
</div>

<script type="text/javascript">
highlightTableRows("list");
function btn_close() {
	 return $('#recordList').attr('style','display:none');
}


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
	linkBankCategoryDwr.getLinkBankCategoryGson(id, function(data) {
			eval("var gsondata={Rows:"+data+"};");
			if  (gsondata.Rows.length>0){
				 grid=$("#persongrid").ligerGrid({
					columns: [ 
					{display: '<delmar:message key="warehouseForwarder.column.operation" />', name: 'id',width:120,render:function (rowdata,rowindex,value){
						
						   var h = "";
						
			    			   h += "<a href='#' onclick=\"javascript:window.location='<c:url value="/officeTest/officeTestBankCategory_initBankCagegory.action"/>";
			    			   h += "?buttonType=edit&id="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key="common.label.edit" /></a>";
			    			   
			    			   h += "<a href='#' onclick=\"javascript:window.location='<c:url value="/officeTest/officeTestBankCategory_delete.action"/>";
			    			   h += "?id="+rowdata.id+"'\" style='padding-left:10px'><delmar:message key="common.button.delete" /></a>";
			    			   
		         		   return h;
						} },
					{display: '<delmar:message key="officeTestBank.column.code" />', name: 'code',width:150},
					{display: '<delmar:message key="officeTestBank.column.name" />', name: 'name',width:200},
					{display: '<delmar:message key="officeTestBank.column.remark" />', name: 'remark',width:250}
					], data: gsondata, usePager:false, sortName: 'inDate', frozen:false,
					width: 780,rownumbers:true,headerRowHeight:26,isScroll: false,
					detailHeight:160
				});
			}
		});    	
	document.all.recordList.style.left=(selObjPos.left+30)+"px";
	document.all.recordList.style.top=(selObjPos.top+20)+"px";
	document.all.recordList.style.display="inline";
	//document.all.recordList.style.display="none";	
}

function addNewJCRecord(id){
	window.location='<c:url value="/officeTest/officeTestBankCategory_create.action"/>?id='+id;
}

function edit(id) {
	window.location='<c:url value="/officeTest/officeTestBank_edit.action"/>?id='+id;
}

</script>

</body>
</html>
