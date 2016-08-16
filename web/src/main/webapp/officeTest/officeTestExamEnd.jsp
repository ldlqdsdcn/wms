<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="officeTestExam.title.placeViewExam" />
</title>

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
	 
	 $("#listTable tr:even").addClass("query_two");
	 $("#listTable tr:odd").addClass("query_one");
	 $("#listTable tr:last").removeClass("query_one");
	 $("#listTable tr:last").removeClass("query_two");	
	 
});

</script>


<style type="text/css">
.fieldError ul {
display:inline-block;
position: relative;
}
</style>
</head>

<body  scroll="yes">
<form id="Form1" method="post">
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
     <tr> 
       <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
       <td class="navig" align="right"> 
        
       </td>
     </tr>
</table>
<p align="center">
<div style="margin:5px">
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder" id="tableinputtable">
	<tr>
	   	<td align="right" colspan="3"></td>
	</tr>
	<tr align="center" >
		<td width="30%">
			&nbsp;
		</td>
	 	<td align="center" >
	 		<delmar:message key="officeTestExam.message.examinessPapers" />(<s:property value="officeTestExam.created"/>)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		<input type="button" onclick="javascript:window.close();" value="关闭" class="input_submit">
	 	</td>
	 	<td width="30%">
			&nbsp;
		</td>
	</tr>
	<s:if test="officeTestBank.beCalcScore == 1">
		<tr class="tr2">
			<td width="30%">
				&nbsp;
			</td>
			<td align="center"><delmar:message key="officeTestExam.column.examScore" />:<font size='6' color="#ff0000">&nbsp;&nbsp;&nbsp;<s:property value="officeTestExam.examScore"/></font></td>
			<td width="30%">
				&nbsp;
			</td>
		</tr>
	</s:if>
	
	<tr>
		<td align="center" colspan="3">
			
		</td>
	</tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder" id="listTable">
				<tr >
					<td width="2%"><delmar:message key="officeTestBank.column.xuhao" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.content" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.optionOne" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.optionTwo" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.optionThree" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.optionFour" /></td>
					<td  width="13%"><delmar:message key="officeTestExam.column.optionFive" /></td>
					<s:if test="officeTestBank.beCalcScore == 1">
						<td  width="7%"><delmar:message key="officeTestExam.message.rightAnswer" /></td>
					</s:if>
					<td  width="7%"><delmar:message key="officeTestExam.message.examinessAnswer" /></td>
					<s:if test="officeTestBank.beCalcScore == 1">
						<td  width="7%"><delmar:message key="officeTestExam.message.answerMsg" /></td>
					</s:if>
					<td width="2%">&nbsp;</td>
				</tr>
				<%
				   int th=1;
				%>
			
				<s:iterator status="detail" id="li" value="officeTestExamDetailList">
					<tr>
						<td><%=th%></td>
						<td>
							<s:property value="%{#userOrgAccess.org.name}"/>
							<s:property value="#li.officeTestQuestion.content" />
						</td>
						<td>
							<s:property value="#li.officeTestQuestion.optionOne" />&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<s:property value="#li.officeTestQuestion.optionTwo" />&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<s:property value="#li.officeTestQuestion.optionThree" />&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<s:property value="#li.officeTestQuestion.optionFour" />&nbsp;&nbsp;&nbsp;
						</td>
						<td>
							<s:property value="#li.officeTestQuestion.optionFive" />&nbsp;&nbsp;&nbsp;
						</td>
						<s:if test="officeTestBank.beCalcScore == 1">
							<td><s:property value="#li.rightAnswer" /></td>
						</s:if>
						<td><s:property value="#li.examAnswer" /></td>
						<s:if test="officeTestBank.beCalcScore == 1">
							<td>
								<s:if test="#li.examScore == 0 ">
									<img  src="images/error1.jpg" >
								</s:if>
								<s:else>
									<img  src="images/right1.jpg" >
								</s:else>
							</td>
						</s:if>
						<td width="2%">&nbsp;</td>
					</tr>
					<%
					  th = th + 1;
					%>
				</s:iterator>
			<tr>
				<td align="center" colspan="3">
					
				</td>
			</tr>
			</table>
</p>
</div>
</form>
</body>

</HTML>
