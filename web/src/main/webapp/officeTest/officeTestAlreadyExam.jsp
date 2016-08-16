<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="officeTestExam.message.alreadyExam" /></title>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
<link rel="stylesheet" href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>
<script  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<script type="text/javascript">

$(document).ready(function() {
	
	$(document).D_Validate();
	 
	 $("#tableinputtable tr:even").addClass("query_two");
	 $("#tableinputtable tr:odd").addClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_two");
});

</script>


<style type="text/css">
.fieldError ul {
display:inline-block;
position: relative;
}
</style>
</head>



<body>

<s:form id="jcddetailform" name="jcddetailform" action="" namespace='/officeTest' theme="simple">
	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
       <tr> 
         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
         <td class="navig" align="right"> 
          
         </td>
       </tr>
 	</table>
<div style="margin:5px">
	<table width="100%" id="tableinputtable" style="border-collapse:collapse" border="0">
		<tr>
		   	<td align="right" colspan="4"></td>
		</tr>
		<tr>
			<td width="40%"></td>
		   	<td align="center" colspan="2" ><delmar:message key="officeTestExam.message.alreadyExams" /></td>
		   	<td width="30%"></td>
		</tr>
		<tr>
			<td width="40%"></td>
		   	<td align="right" width="10%"><delmar:message key="officeTestExam.column.examScore" />:</td>
			<td align="left" width="20%"><font size='6' color="#ff0000"><s:property value="officeTestExam.examScore"/></font></td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td width="40%"></td>
		   	<td align="right"><delmar:message key="officeTestExam.column.created" />:</td>
			<td align="left"><s:property value="officeTestExam.created"/></td>
			<td width="30%"></td>
		</tr>
		<tr>
		   	<td align="right" colspan="4"></td>
		</tr>
		<tr>
		   	<td align="center" colspan="4">
				<input type="button" onclick="lookTestPaper('<s:property value="officeTestExam.id"/>')" value="<delmar:message key="officeTestExam.column.viewExam" />"/>
			</td>
		</tr>
	</table>
	</div>
</s:form>

<script language="javascript">
function lookTestPaper(testId){
	$.ajax({
        type: "post",
        url: "<%=request.getContextPath()%>/officeTest/officeTestExamDetail_ajaxCanViewTest.action?testId=" + testId,
        dataType: "json",
        success: function (data) {
        	if (data) {
        		window.open("<%=request.getContextPath()%>/officeTest/officeTestExamDetail_viewTest.action?testId=" + testId);
        	} else {
        		alert("<delmar:message key="officeTestExam.message.canNotViewExam" />");
        	}

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
   });
	
}
</script>







</body>
</html>
