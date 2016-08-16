<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

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

function validate() {
	var testBankId = document.getElementById("testBankId").value;
    if(testBankId == ""){
    	//alert('<delmar:message key="wfDetail.message.carDriverCanNotEmpty" />');
    	alert("<delmar:message key="officeTestQuestion.message.categoryCanNotEmpty" />");
		return false;
    }
	
   	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestExamDetail_start.action";
    document.forms["jcddetailform"].submit();
    
}



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
         <td class="navig" align="right"> </td>
       </tr>
 	</table>
	<div style="margin:5px">
		<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px" id="tableinputtable">
		  	<tr>
		       <td class="d-tdlabel" style="width:45%">      	
			   		<font color="#FC0107" >*</font>
			   		<font size="2"><delmar:message key="officeTestQuestion.message.pleaseSelectCategory" /></font>:
			   </td>
			   <td   class="d-tdinput" style="width:10%"> 
			       	<s:select  list="bankCategoryList" listKey="value" listValue="name" name="testBankId" id="testBankId" 
	 					value="%{officeTestQuestion.testBankId}" style="height:24px;width:120px" ></s:select>
			   </td>
			   <td class="d-tdlabel" style="width:45%">&nbsp;</td>
			</tr>
		 
		</table>
	</div>
	<table border="0">
		<tr align="center">
					<td width="50%">&nbsp;
					</td>
                	<td>
                			<input type="button" name="btnsubmit" id="btnsubmitNew" value="开始测试" 
                				Class="input_submit" style="border:0;" onclick="validate()" >
                	<td width="50%">&nbsp;
					</td>
                </tr>
            </table>	
</s:form>

<script language="javascript">
 
</script>







</body>
</html>
