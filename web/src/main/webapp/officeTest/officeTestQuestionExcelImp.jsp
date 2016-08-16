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
	 
	 var impMsg = document.getElementById("impMsg").value;
	 if (!(impMsg == "" || impMsg == null)) {
		 alert(impMsg);
	 } 
});

function impExcel(bankId){
	
	var textfield = document.getElementById("textfield").value;
	if (textfield == "" || textfield == null) {
		alert("<delmar:message key="officeTestQuestion.message.selectFile"/>");
		return;
	}
	
	var index = textfield.lastIndexOf(".");
	if(index < 0) {
		alert("<delmar:message key="officeTestQuestion.message.selectXLSEFile"/>");
		return;
	} else {
		var ext = textfield.substring(index + 1, textfield.length);
		if(ext != "xlsx") {
			alert("<delmar:message key="officeTestQuestion.message.selectXLSEFile"/>");
			return;
		}
	} 
	
	document.getElementById("imps").disabled = true;
	
	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestQuestion_impExcel.action";
    document.forms["jcddetailform"].submit();
}

</script>


<style type="text/css">
.fieldError ul {
display:inline-block;
position: relative;
}

body{ font-size:14px;}
input{ vertical-align:middle; margin:0; padding:0}
.file-box{ position:relative;width:340px}
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:50px;}
.file{ position:absolute; top:100; right:360px; height:24px; filter:alpha(opacity:0);opacity: 0;width:250px }
</style>
</head>



<body>

<s:form id="jcddetailform" name="jcddetailform" action="" namespace='/officeTest' enctype="multipart/form-data" theme="simple" >
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
		   	<td align="center" colspan="2" ><delmar:message key="officeTestQuestion.message.download"/> <a href="<%=request.getContextPath()%>/officeTest/question.xlsx"><delmar:message key="officeTestQuestion.message.template"/></a> ，<delmar:message key="officeTestQuestion.message.write"/></td>
		   	<td width="30%"></td>
		</tr>
		<tr>
			<td width="40%"></td>
		   	<td align="right" width="10%"><delmar:message key="officeTestQuestion.message.selectPath"/>:</td>
			<td align="left" width="20%">
				<input type='text' name='textfield' id='textfield' class='txt' />  
 				<input type='button' class='btn' value='<delmar:message key="officeTestQuestion.message.browse"/>' />
    			<input type="file" name="excelFile" class="file" id="excelFile" size="28" onchange="document.getElementById('textfield').value=this.value" />
    			
			</td>
			<td width="30%"></td>
		</tr>
		
		<tr>
		   	<td align="right" colspan="4"></td>
		</tr>
		<tr>
		   	<td align="center" colspan="4">
				<input type="button" id="imps" name="imps" onclick="impExcel('<s:property value="officeTestBank.id"/>')" value="<delmar:message key="officeTestQuestion.message.importExam"/>"/>
			</td>
		</tr>
	</table>
	</div>
	<input id="officeTestBank.id" name="officeTestBank.id" type="hidden" value="<s:property value="officeTestBank.id"/>"/>
	<input id="impMsg" name="impMsg" type="hidden" value="<s:property value="impMsg"/>"/>
</s:form>
<script language="javascript">




</script>

</body>
</html>
