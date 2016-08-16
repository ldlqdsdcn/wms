<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="customer.title"/>
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
	 
	 $("#categorysTable tr:even").addClass("query_two");
	 $("#categorysTable tr:odd").addClass("query_one");
	 $("#categorysTable tr:last").removeClass("query_one");
	 $("#categorysTable tr:last").removeClass("query_two");
	 
	 var bankId = document.getElementById("officeTestBank.id").value;

	 if (bankId == "" || bankId == null) {
	 	 document.getElementById("imps").disabled = true;
	 }
});

function validate() {
	var code = document.getElementById("officeTestBank.code").value;
	var name = document.getElementById("officeTestBank.name").value;
	var timeLimit = document.getElementById("officeTestBank.timeLimit").value;
	var questionCount = document.getElementById("officeTestBank.questionCount").value;
	
    if(code == ""){
    	//alert('<delmar:message key="wfDetail.message.carDriverCanNotEmpty" />');
    	alert("<delmar:message key="officeTestBank.message.codeIsEmpty" />");
		return false;
    }
    
    if(name == ""){
    	//alert('<delmar:message key="wfDetail.message.carDriverCanNotEmpty" />');
    	alert("<delmar:message key="officeTestBank.message.nameIsEmpty" />");
		return false;
    }
    
 	// 考试时间数字判断 
    if(!(/\d+(\.\d+)?/g.test(timeLimit))) {
		//alert('<delmar:message key="wfDetail.message.NumberEnterNumber" />');
		alert("<delmar:message key="officeTestBank.message.timeLimitShouldBeNum" />");
		return false;
	}
	
	// 考试时间数字判断整数
    if(!(/^[-]?\d+$/.test(timeLimit))) {
		//alert('<delmar:message key="wfDetail.message.goodsNumber" />');
		alert("<delmar:message key="officeTestBank.message.timeLimitShouldBeInteger" />");
		return false;
	} 
	
 	// 考题数量数字判断 
    if(!(/\d+(\.\d+)?/g.test(questionCount))) {
		//alert('<delmar:message key="wfDetail.message.NumberEnterNumber" />');
		alert("<delmar:message key="officeTestBank.message.questionCountShouldBeNum" />");
		return false;
	}
	
	// 考题数量判断整数
    if(!(/^[-]?\d+$/.test(questionCount))) {
		//alert('<delmar:message key="wfDetail.message.goodsNumber" />');
		alert("<delmar:message key="officeTestBank.message.questionCountShouldBeInteger" />");
		return false;
	} 
	
    $("#categoryIds").val($("#categorys").val());
   	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestBank_saveOrUpdate.action";
    document.forms["jcddetailform"].submit();
    
}

function impExcel() {
	
	var id = document.getElementById("officeTestBank.id").value;
	window.location='<c:url value="/officeTest/officeTestQuestion_toImpExcel.action"/>?officeTestBank.id='+id;
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

<s:form id="jcddetailform" name="jcddetailform" action="officeTestCategory_create" namespace='/officeTest' theme="simple">
	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
	         <td class="navig" align="right"> 
	          
          </td>
	       </tr>
	 	</table>
	<div style="margin:5px">
		<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px" id="tableinputtable">
		  <tr>
	       <td class="d-tdlabel" >      	
		   		<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.code" />:
		   </td>
		   <td   class="d-tdinput"> 
		       	<s:textfield name="officeTestBank.code" id="officeTestBank.code" cssClass="d-inputtext d-widthb"></s:textfield>
		   </td>
		   <td  class="d-tdlabel">
		       	<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.name" />:
		   </td>
		   <td class="d-tdinput"> 
		       	<s:textfield name="officeTestBank.name" id="officeTestBank.name" cssClass="d-inputtext d-widthb"></s:textfield>
		   </td>
		</tr>
		<tr>
	       <td class="d-tdlabel">      	
		   		<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.timeLimit" />:
		   </td>
		   <td   class="d-tdinput"> 
		       	<s:textfield name="officeTestBank.timeLimit" id="officeTestBank.timeLimit" cssClass="d-inputtext d-widthb"/>
		   </td>
		   <td  class="d-tdlabel">
		       	<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.questionCount" />:
		   </td>
		   <td class="d-tdinput"> 
		       	<s:textfield name="officeTestBank.questionCount" id="officeTestBank.questionCount" cssClass="d-inputtext d-widthb"></s:textfield>
		   </td>
		</tr>
		<tr>
	       <td class="d-tdlabel">      	
		   		<delmar:message key="officeTestBank.column.beCalcScore" />：
		   </td>
		   <td   class="d-tdinput"> 
		   		<s:select  list="booleanList" listKey="value" listValue="name" name="officeTestBank.beCalcScore" id="officeTestBank.beCalcScore" 
 					value="%{officeTestBank.beCalcScore}"	style="height:24px;width:80px" ></s:select>
		   </td>
		   <td  class="d-tdlabel">
		       	<delmar:message key="officeTestBank.column.beOpen" />:
		   </td>
		   <td class="d-tdinput"> 
		   		<s:select  list="booleanListSort" listKey="value" listValue="name" name="officeTestBank.beOpen" id="officeTestBank.beOpen" 
 					value="%{officeTestBank.beOpen}"	style="height:24px;width:80px" ></s:select>
		   </td>
		</tr>
		<tr>
	       <td class="d-tdlabel">      	
		   		<delmar:message key="officeTestBank.column.beState" />：
		   </td>
		   <td   class="d-tdinput" colspan="3"> 
		   		<s:select  list="booleanListSort" listKey="value" listValue="name" name="officeTestBank.beState" id="officeTestBank.beState" 
 					value="%{officeTestBank.beState}"	style="height:24px;width:80px" ></s:select>
		   </td>
		  
		</tr>
		<tr>
	       <td class="d-tdlabel">      	
		   		<delmar:message key="officeTestBank.column.remark" />：
		   </td>
		   <td   class="d-tdinput" colspan="3"> 
		       	<s:textfield name="officeTestBank.remark" id="officeTestBank.remark" cssClass="d-inputtext d-widthb" style="width:300px"></s:textfield>
		   </td>
		   
		</tr>
		<tr>
			<td>&nbsp;
			</td>
		</tr>
		 
		</table>
	</div>
	
	<div style="margin:5px">
		<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px"
			id="categorysTable">
			<tr >
		  		<td class="d-tdlabel" colspan="4" >      	
			   		<font size="4"><delmar:message key="officeTestBank.column.bankCategory" /></font>
			   </td>
		      
			</tr>
		  <tr>
		  		<td class="d-tdlabel" >      	
			   		<delmar:message key="officeTestBank.column.xuhao" />
			   </td>
		       <td class="d-tdlabel" >      	
			   		<delmar:message key="officeTestBank.column.code" />
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="officeTestBank.column.name" />
			   </td>
			   <td class="d-tdlabel"> 
			       	<delmar:message key="officeTestBank.column.remark" />
			   </td>
			</tr>
				<%
				   int th=1;
				%>
			
				<s:iterator status="st" id="li" value="categories">
					<tr>
						<td class="d-tdlabel"><%=th%></td>
						<td class="d-tdlabel">
							<s:hidden name="%{'categories['+#st.index+'].id'}"/>
							<s:textfield name="%{'categories['+#st.index+'].code'}" cssClass="d-inputtext d-widthb"/>
						</td class="d-tdlabel">
						<td class="d-tdlabel">
							<s:textfield name="%{'categories['+#st.index+'].name'}" cssClass="d-inputtext d-widthb"/>
						</td>
						<td class="d-tdlabel">
							<s:textfield name="%{'categories['+#st.index+'].remark'}" cssClass="d-inputtext d-widthb"/>
						</td>
					</tr>
					<%
					  th = th + 1;
					%>
				</s:iterator>
			<tr>
				<td>&nbsp;
				</td>
			</tr>
		 
		</table>
	</div>
	<table border="0">
		<tr align="center">
					<td>&nbsp;
					</td>
                	<td>
                		<c:if test="${officeTestBank.id == null}">
                			<delmar:operatePriv operator="create" >
	                			<input type="button" name="btnsubmit" id="btnsubmitNew" value="<delmar:message key="wfDetail.button.add" />" 
	                				Class="input_submit" style="border:0;" onclick="validate()" >
	                		</delmar:operatePriv>	
                		</c:if>
                		
                		<c:if test="${officeTestBank.id != null}">
                			<delmar:operatePriv operator="update">
								<input onclick="validate();" id="btnsubmitNew" type="button" value="<delmar:message key="wfDetail.button.update" />"  class="input_submit" >
							</delmar:operatePriv>
                		</c:if>
                		
						<input onclick="impExcel();" id="imps" type="button" value="<delmar:message key="officeTestQuestion.message.importExam"/>"  class="input_submit" >
                			
             			<input onclick="window.location='<c:url value="/officeTest/officeTestBank_list.action"/>'"  type="button" 
         					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                	</td>
                	<td>&nbsp;
					</td>
                </tr>
            </table>	
<input id="officeTestBank.id" name="officeTestBank.id" type="hidden" value="<s:property value="officeTestBank.id"/>"/>
<input id="officeTestBank.clientId" name="officeTestBank.clientId" type="hidden" value="<s:property value="officeTestBank.clientId"/>"/>
<input id="officeTestBank.orgId" name="officeTestBank.orgId" type="hidden" value="<s:property value="officeTestBank.orgId"/>"/>
<input id="officeTestBank.userId" name="officeTestBank.userId" type="hidden" value="<s:property value="officeTestBank.userId"/>"/>
<input id="officeTestBank.userName" name="officeTestBank.userName" type="hidden" value="<s:property value="officeTestBank.userName"/>"/>
<input id="officeTestBank.created" name="officeTestBank.created" type="hidden" value="<s:property value="officeTestBank.created"/>"/>
<input id="officeTestBank.createdBy" name="officeTestBank.createdBy" type="hidden" value="<s:property value="officeTestBank.createdBy"/>"/>
<input id="officeTestBank.createdByName" name="officeTestBank.createdByName" type="hidden" value="<s:property value="officeTestBank.createdByName"/>"/>
<input id="officeTestBank.updated" name="officeTestBank.updated" type="hidden" value="<s:property value="officeTestBank.updated"/>"/>
<input id="officeTestBank.updatedBy" name="officeTestBank.updatedBy" type="hidden" value="<s:property value="officeTestBank.updatedBy"/>"/>
<input id="officeTestBank.updatedByName" name="officeTestBank.updatedByName" type="hidden" value="<s:property value="officeTestBank.updatedByName"/>"/>
</s:form>

<script language="javascript">


</script>


</body>
</html>
