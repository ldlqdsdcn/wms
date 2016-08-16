<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>考试系统</title>

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
		 });
	jQuery("#bankCategory").multiselect({
		
		initValue:$("#bankCategoryId").val(),
		minWidth:180
	});
	
	jQuery("#questionType").multiselect({
		
		initValue:$("#questionTypeId").val(),
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


<s:form action="officeTestExamDetail_list" namespace="/officeTest"  theme="simple" >
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
					   		<delmar:message key="officeTestExam.select.selectTypeList" />：
					   </td>
					   <td   class="d-tdinput"> 
					   		<s:select list="selectTypeList" listKey="value" listValue="name"  name="selectTypeId"  
					   			id="selectTypeId" style="width:180px;"></s:select>
					   </td>
					   <td  class="d-tdlabel">
					       	<delmar:message key="officeTestExam.column.peopleJob" />:
					   </td>
					   <td class="d-tdinput">
			    			<s:textfield name="peopleJob" id="peopleJob" cssClass="d-searchtext"/> 
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
	 <display:table name="officeTestExamDetails" cellspacing="0" cellpadding="0"  requestURI=""
			    id="list" pagesize="20" class="table" export="true" >
		    
		<display:column  titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
    	<c:if test="${list.id*1 == 0}">
    		<display:column titleKey="officeTestExam.button.view" >  
				<a href="#" class="atest" onclick="javascript:viewRecord('<c:out value="${list.testQuestionId}"/>','edit')"><delmar:message key="officeTestExam.button.view" /></a>
	    	</display:column>
    	</c:if>
    	<display:column  titleKey="officeTestExam.column.post"></display:column>
    	<display:column  property="examUserName" titleKey="officeTestExam.column.examUserName"  sortable="true" > </display:column>
    	<display:column property="questionType" escapeXml="true" titleKey="officeTestExam.column.questionType"  sortable="true"></display:column>
    	<display:column  property="content" escapeXml="true" titleKey="officeTestExam.column.content"  sortable="true" ></display:column>
    	<display:column  property="aAcount" escapeXml="true" titleKey="officeTestExam.column.aAcount"  sortable="true" ></display:column>
    	<display:column  property="bAcount" escapeXml="true" titleKey="officeTestExam.column.bAcount"  sortable="true" ></display:column>
    	<display:column  property="cAcount" escapeXml="true" titleKey="officeTestExam.column.cAcount"  sortable="true" ></display:column>
    	<display:column  property="dAcount" escapeXml="true" titleKey="officeTestExam.column.dAcount"  sortable="true" ></display:column>
    	<display:column  property="eAcount" escapeXml="true" titleKey="officeTestExam.column.eAcount"  sortable="true" ></display:column>
    	<display:column  property="optionOne" escapeXml="true" titleKey="officeTestExam.column.optionOne"  sortable="true" ></display:column>
    	<display:column  property="optionTwo" escapeXml="true" titleKey="officeTestExam.column.optionTwo"  sortable="true" ></display:column>
    	<display:column  property="optionThree" escapeXml="true" titleKey="officeTestExam.column.optionThree"  sortable="true" ></display:column>
    	<display:column  property="optionFour" escapeXml="true" titleKey="officeTestExam.column.optionFour"  sortable="true" ></display:column>
    	<display:column  property="optionFive" escapeXml="true" titleKey="officeTestExam.column.optionFive"  sortable="true" ></display:column>
    	<display:column  property="answer" escapeXml="true" titleKey="officeTestExam.column.answer"  sortable="true" ></display:column>
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

if ($("#queryStatus").val()=="true") {
	$('#filterForm').removeClass("d-hidden");
	$('#filterForm').addClass("d-visible");
    $('#filterpagetoggleimage').attr('src','../images/core/icon_expand_l.gif');
}



function viewRecord(questionId){
	window.open("<%=request.getContextPath()%>/officeTest/officeTestExamDetail_list.action?id=" + questionId + "&doAction=viewDetail");
}


</script>

</body>
</html>
