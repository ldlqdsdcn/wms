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
	       $("#questionTypeId").val($("#questionType").val());
	       $("#categoryId").val($("#category").val());

		 });
	jQuery("#bankCategory").multiselect({
		
		initValue:$("#bankCategoryId").val(),
		minWidth:180
	});
	
	jQuery("#questionType").multiselect({
		
		initValue:$("#questionTypeId").val(),
		minWidth:180
	});
	
	jQuery("#category").multiselect({
		
		initValue:$("#categoryId").val(),
		minWidth:180
	});
	
		var varray=$("#bankCategoryId").val().split(",");
		for (var i in varray){
	      $("#bankCategory").find("option[value='"+varray[i]+"']").attr("selected",true);
		}
		
		 varray=$("#questionTypeId").val().split(",");
		for (var i in varray){
	      $("#questionType").find("option[value='"+varray[i]+"']").attr("selected",true);
		}
		
		 varray=$("#categoryId").val().split(",");
		for (var i in varray){
	      $("#category").find("option[value='"+varray[i]+"']").attr("selected",true);
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

function edit(id) {
	window.location='<c:url value="/officeTest/officeTestQuestion_edit.action"/>?id='+id;
}
	
</script>
</head>

<body >


<s:form action="officeTestQuestion_list" namespace="/officeTest"  theme="simple" >
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
					   		<delmar:message key="officeTestExam.select.bankCategoryList" />:
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="bankCategoryList" listKey="value" listValue="name"  name="bankCategory"  multiple="true" id="bankCategory" ></s:select>
					   		<s:hidden name="bankCategoryId" id="bankCategoryId" />
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="officeTestExam.select.questionTypeList" />:
					   </td>
					   <td class="d-tdinput"> 
					       	<s:select list="questionTypeList" listKey="datadictId" listValue="name"  name="questionType"  multiple="true" id="questionType" ></s:select>
					   		<s:hidden name="questionTypeId" id="questionTypeId" />
					   </td>
					</tr>
					<tr>
				       <td class="d-tdlabel">      	
					   		<delmar:message key="officeTestQuestion.column.contentContain" />：
					   </td>
					   <td   class="d-tdinput"> 
					       	<s:textfield name="content" id="content" cssClass="d-searchtext" style="width:180px;"></s:textfield>
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
	 <display:table name="officeTestQuestionList" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		<delmar:operatePriv operator="delete">
			<display:column style="width:30px;text-align:center;vertical-align:text-top" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
				<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
			</display:column>
		</delmar:operatePriv>
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<display:column  titleKey="officeTestExam.button.operation" media="html csv excel xml pdf rtf">
			  <a href='javascript:void(0)' onclick="edit('<c:out value="${list.id}"/>')"><delmar:message key="common.label.edit" /></a>
    	</display:column>
    	
    	<display:column  property="testBankId" decorator="com.delmar.officeTest.web.displaytag.decorator.BankCategoryDecorator" 
    		escapeXml="true" titleKey="officeTestExam.select.bankCategoryList"  sortable="true" >
    		
    		</display:column>
    		
    	<display:column  property="typeId" decorator="com.delmar.officeTest.web.displaytag.decorator.TypeDecorator" 
    		escapeXml="true" titleKey="officeTestExam.select.questionTypeList"  sortable="true" ></display:column>
    		
    	<display:column property="content" titleKey="officeTestExam.column.content"  sortable="true" ></display:column>
    	<display:column  property="answer" escapeXml="true" titleKey="officeTestExam.message.rightAnswer"  sortable="true" ></display:column>
    	<display:column  property="categoryId" decorator="com.delmar.officeTest.web.displaytag.decorator.CategoryDecorator" 
    		escapeXml="true" titleKey="officeTestQuestion.column.knowledgePoint"  sortable="true" ></display:column>
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
</script>

</body>
</html>
