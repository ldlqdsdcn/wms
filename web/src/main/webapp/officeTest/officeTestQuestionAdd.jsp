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
	 
	 uf_getCagegorys();
});

function validate(type) {
	var scoreType=document.getElementById("officeTestQuestion.testBankId").value;
	  var value1=$("#typeId").find("option:selected").text();
	  var value2=document.getElementById("officeTestQuestion.content").value;
	  var value3=document.getElementById("officeTestQuestion.optionOne").value;
	  var value4=document.getElementById("officeTestQuestion.optionTwo").value;
	  var value5=document.getElementById("officeTestQuestion.optionThree").value;
	  var value6=document.getElementById("officeTestQuestion.optionFour").value;
	  var value7=document.getElementById("officeTestQuestion.answer").value;
	  var value8=document.getElementById("officeTestQuestion.categoryId").value;
	  var value9=document.getElementById("officeTestQuestion.point").value;

	  if(scoreType==""){
	    alert("<delmar:message key="officeTestExam.message.examBankCanNotEmpty" />");
	    return;
	  }
	  if(value1==""){
	    alert("<delmar:message key="officeTestExam.message.typeCanNotEmpty" />");
	    return;
	  }
	  if(value2==""){
	    alert("<delmar:message key="officeTestExam.message.contentCanNotEmpty" />");
	    return;
	  }
	  if(value8==""){
	    alert("<delmar:message key="officeTestExam.message.categoryCanNotEmpty" />");
	    return;
	  }
	  if(value9==""){
	    alert("<delmar:message key="officeTestExam.message.pointCanNotEmpty" />");
	    return;
	  }
	  
    	if(!(/\d+(\.\d+)?/g.test(value9))) {
			//alert('<delmar:message key="wfDetail.message.NumberEnterNumber" />');
			alert("<delmar:message key="officeTestBank.message.pointShouldBeNum" />");
			return false;
		}
		// 考试时间数字判断整数
	    if(!(/^[-]?\d+$/.test(value9))) {
			//alert('<delmar:message key="wfDetail.message.goodsNumber" />');
			alert("<delmar:message key="officeTestBank.message.pointShouldBeInteger" />");
			return false;
		} 
	  if(value1=="简答题"){
		  document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestQuestion_saveOrUpdate.action?saveType=" + type;
		  document.forms["jcddetailform"].submit();
		 return;
	    
	  }

	  if(value3==""){
	    alert("<delmar:message key="officeTestExam.message.optionAcanNotEmpty" />");
	    return;
	  }
	  if(value4==""){
	    alert("<delmar:message key="officeTestExam.message.optionBcanNotEmpty" />");
	    return;
	  }
	  if(value1!="判断题"){
	  	if(value5==""){
		    alert("<delmar:message key="officeTestExam.message.optionCcanNotEmpty" />");
		    return;
		  }
		  if(value6==""){
		    alert("<delmar:message key="officeTestExam.message.optionDcanNotEmpty" />");
		    return;
		  }
	  }else{
	  	if((value5!="") || (value6!="")){
		    alert("<delmar:message key="officeTestExam.message.judQuestionShouleAorB" />");
		    document.QuestionForm.optionThree.value="";
		    document.QuestionForm.optionFour.value="";
		    return;
		  }
	  }
	  if(value7==""){
	  	alert("<delmar:message key="officeTestExam.message.rightAnswerCanNotEmpty" />");
	  	return;
	  }else{
	  	if(value1!="判断题"){
	  		if(value7.length > 4){
			  	alert("<delmar:message key="officeTestExam.message.shouldBeFourAnswer" />");
			  	document.QuestionForm.answer.value="";
			  	return;
			}else{
			  	if(!(/^[A-D]+$/.test(value7))){
				    alert("<delmar:message key="officeTestExam.message.shouleA-D" />");
				    document.QuestionForm.answer.value="";
				    return;
		  		}
		  	}
	  	}else{
	  		if(value7.length > 1){
			  	alert("<delmar:message key="officeTestExam.message.mostOneAnser" />");
			  	document.QuestionForm.answer.value="";
			  	return;
			}else{
			  	if(!(/^[A-B]+$/.test(value7))){
				    alert("<delmar:message key="" />officeTestExam.message.shouleA-B");
				    document.QuestionForm.answer.value="";
				    return;
		  		}
		  	}
	  	}
	  	
	  }

	  document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestQuestion_saveOrUpdate.action?saveType=" + type;
	  document.forms["jcddetailform"].submit();
    
}

function uf_getCagegorys() {
	
	var testBankId = document.getElementById("officeTestQuestion.testBankId").value;
	$.ajax({
        type: "post",
        url: "<%=request.getContextPath()%>/officeTest/officeTestQuestion_ajaxGetCategorys.action?testBankId=" + testBankId,
        dataType: "json",
        success: function (data) {
        	
        	var categoryId = document.getElementById("officeTestQuestion.categoryId");
        	for (var i = 0; i < data.length; i++) {
        		categoryId.add(new Option(data[i].name, data[i].id));
        	}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
   });
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
	<table border="1" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
	         <td class="navig" align="right"> 
	          
          </td>
	       </tr>
	 	</table>
	<div style="margin:5px">
		
		<table width="100%" border="0" id="tableinputtable">
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestExam.select.bankCategoryList" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:select  list="bankCategoryList" listKey="value" listValue="name" name="officeTestQuestion.testBankId" id="officeTestQuestion.testBankId" 
			 					value="%{officeTestQuestion.testBankId}" style="height:24px;width:120px" onchange="uf_getCagegorys();" ></s:select>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestExam.select.questionTypeList" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:select  list="questionTypeList" listKey="datadictId" listValue="name" name="officeTestQuestion.typeId" id="typeId" 
			 					value="%{officeTestQuestion.typeId}"	style="height:24px;width:120px" ></s:select>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestExam.column.content" />：
		  	</td>
		  	<td  class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.content" id="officeTestQuestion.content" cssClass="d-inputtext d-widthb" style="width:50%"  ></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.column.optionOne" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.optionOne" id="officeTestQuestion.optionOne" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.column.optionTwo" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.optionTwo" id="officeTestQuestion.optionTwo" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.column.optionThree" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.optionThree" id="officeTestQuestion.optionThree" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.column.optionFour" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.optionFour" id="officeTestQuestion.optionFour" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.column.optionFive" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.optionFive" id="officeTestQuestion.optionFive" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestExam.message.rightAnswer" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.answer" id="officeTestQuestion.answer" cssClass="d-inputtext d-widthb" style="width:50%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestQuestion.column.point" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.point" id="officeTestQuestion.point" cssClass="d-inputtext d-widthb" width="100%"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestQuestion.column.knowledgePoint" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:select  list="categoryList" listKey="value" listValue="name" name="officeTestQuestion.categoryId" id="officeTestQuestion.categoryId" 
			 					value="%{officeTestQuestion.categoryId}"	style="height:24px;width:120px" ></s:select>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="officeTestBank.column.remark" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="officeTestQuestion.remark" id="officeTestQuestion.remark" cssClass="d-inputtext d-widthb" width="100%"></s:textfield>
		  	</td>
		  </tr>
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
                			<delmar:operatePriv operator="create" >
                			<input type="button" name="btnsubmit" id="btnsubmitNew" value="保存" 
                				Class="input_submit" style="border:0;" onclick="validate(0)" >
                		</delmar:operatePriv>	
                		
                			<delmar:operatePriv operator="create">
							<input onclick="validate(1);"  type="button" value="<delmar:message key="officeTestQuestion.button.saveAndNew" />"  class="input_submit" >
						</delmar:operatePriv>
                			
             			<input onclick="window.location='<c:url value="/officeTest/officeTestQuestion_list.action"/>'"  type="button" 
         					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                	</td>
                	<td>&nbsp;
					</td>
                </tr>
            </table>	
<input id="officeTestQuestion.id" name="officeTestQuestion.id" type="hidden" value="<s:property value="officeTestQuestion.id"/>"/>
<input id="officeTestQuestion.clientId" name="officeTestQuestion.clientId" type="hidden" value="<s:property value="officeTestQuestion.clientId"/>"/>
<input id="officeTestQuestion.orgId" name="officeTestQuestion.orgId" type="hidden" value="<s:property value="officeTestQuestion.orgId"/>"/>
<input id="officeTestQuestion.userId" name="officeTestQuestion.userId" type="hidden" value="<s:property value="officeTestQuestion.userId"/>"/>
<input id="officeTestQuestion.userName" name="officeTestQuestion.userName" type="hidden" value="<s:property value="officeTestQuestion.userName"/>"/>
<input id="officeTestQuestion.created" name="officeTestQuestion.created" type="hidden" value="<s:property value="officeTestQuestion.created"/>"/>
<input id="officeTestQuestion.createdBy" name="officeTestQuestion.createdBy" type="hidden" value="<s:property value="officeTestQuestion.createdBy"/>"/>
<input id="officeTestQuestion.createdByName" name="officeTestQuestion.createdByName" type="hidden" value="<s:property value="officeTestQuestion.createdByName"/>"/>
<input id="officeTestQuestion.created" name="officeTestQuestion.created" type="hidden" value="<s:property value="officeTestQuestion.created"/>"/>
<input id="officeTestQuestion.updatedBy" name="officeTestQuestion.updatedBy" type="hidden" value="<s:property value="officeTestQuestion.updatedBy"/>"/>
<input id="officeTestQuestion.updatedByName" name="officeTestQuestion.updatedByName" type="hidden" value="<s:property value="officeTestQuestion.updatedByName"/>"/>
</s:form>

<script language="javascript">
 
</script>







</body>
</html>
