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
	       $("#bankCategoryId").val($("#bankCategory").val());
	       $("#beCancel").val($("#beCancelSelect").val());
	       if (document.all.userOrgs) {
	        	$("#orgIds").val($("#userOrgs").val());		        	
	        }	
		 });
	jQuery("#bankCategory").multiselect({
		
		initValue:$("#bankCategoryId").val(),
		minWidth:180
	});
	
	jQuery("#beCancelSelect").multiselect({
		
		initValue:$("#beCancel").val(),
		minWidth:180
	});
	
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
	
	var varray=$("#bankCategoryId").val().split(",");
	for (var i in varray){
      $("#bankCategory").find("option[value='"+varray[i]+"']").attr("selected",true);
	}
	
	varray=$("#beCancelSelect").val().split(",");
	for (var i in varray){
      $("#beCancel").find("option[value='"+varray[i]+"']").attr("selected",true);
	}
	  
});

function validate(ids) {
	
	if (confirmListDelete(ids)) {
		$.ajax({  
	        url:'officeTestCategory_validateAndDelete.action?ids='+ids,  
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


<s:form action="officeTestExam_list" namespace="/officeTest"  theme="simple" >
	<div style="margin:5px">
	
		<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
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
					       			       	
					       	<s:hidden name="orgIds" id="orgIds" type="hidden"/>
					      </td>	
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestExam.select.bankCategoryList" />:
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="bankCategoryList" listKey="value" listValue="name"  name="bankCategory"  multiple="true" id="bankCategory" ></s:select>
					   		<s:hidden name="bankCategoryId" id="bankCategoryId" />
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="officeTestExam.message.employeeName" />:
					   </td>
					   <td class="d-tdinput"> 
					       	<s:textfield name="examUserName" id="examUserName" cssClass="d-searchtext"></s:textfield>
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestExam.message.containCancel" />：
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="booleanList" listKey="value" listValue="name"  name="beCancelSelect"  multiple="true"  id="beCancelSelect" ></s:select>
			    			<s:hidden name="beCancel" id="beCancel" />
					   </td>
					   <td class="d-tdinput" colspan="4">      	
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
	 <display:table name="officeTestExamList" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<display:column titleKey="officeTestExam.button.operation" >  
			<a href="#"  onclick="javascript:viewExam('<c:out value="${list.id}"/>')"><delmar:message key="officeTestExam.button.view" /></a>
    	</display:column>
    	<display:column  property="testBankId" decorator="com.delmar.officeTest.web.displaytag.decorator.BankCategoryDecorator" 
    		escapeXml="true" titleKey="officeTestExam.select.bankCategoryList"  sortable="true" ></display:column>
    	<display:column  property="examUserName" escapeXml="true" titleKey="officeTestExam.message.employeeName"  sortable="true"></display:column>
    	<display:column  property="created" escapeXml="true" titleKey="officeTestBank.column.timeLimit"  sortable="true" 
    	decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"></display:column>
    	<display:column  property="timeUsed" escapeXml="true" titleKey="officeTestExam.column.usedTime"  sortable="true" ></display:column>
    	<display:column  property="examScore" escapeXml="true" titleKey="officeTestExam.column.examScore"  sortable="true" ></display:column>
    	<display:column  escapeXml="true" titleKey="officeTestExam.column.beCancel" sortable="true">
	    	<c:if test="${list.beCancel*1==1}">
				 <delmar:message key="officeTestExam.status.beCancel" />
			</c:if>
			<c:if test="${list.beCancel*1!=1}">
				<delmar:message key="officeTestExam.status.effect" />
			</c:if>
		</display:column>
    	<display:column titleKey="officeTestExam.button.operation" >  
			<a href="#"  onclick="javascript:cancelExam('<c:out value="${list.id}"/>','<c:out value="${list.beCancel}"/>')"><delmar:message key="officeTestExam.status.beCancel" /></a>
    	</display:column>
	  </display:table>
	</div>
</s:form>


<script type="text/javascript">


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
if ($("#queryStatus").val()=="true")  {
	$('#filterForm').removeClass("d-hidden");
	$('#filterForm').addClass("d-visible");
    $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
}

function viewExam(id) {
	window.open("<%=request.getContextPath()%>/officeTest/officeTestExamDetail_viewTest.action?testId=" + id);
}

function cancelExam(id, status) {
	if (status == '1') {
		alert("<delmar:message key="officeTestExam.message.alreadyCancel" />");
		return;
	}
	
	if (confirm("<delmar:message key="officeTestExam.message.confrimCancel" />")) {
		$.ajax({
	        type: "post",
	        url: "<%=request.getContextPath()%>/officeTest/officeTestExam_ajaxCancelExam.action?id=" + id,
	        dataType: "json",
	        success: function (data) {
	        	alert("<delmar:message key="officeTestExam.message.cancelSuccess" />");
	        	window.location.reload();
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	                alert(errorThrown);
	        }
	   });
	}
}
</script>

</body>
</html>
