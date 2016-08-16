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
});

function validate() {
	var code = document.getElementById("testCategory.code").value;
	var name = document.getElementById("testCategory.name").value;
	
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
    
   	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/officeTest/officeTestBankCategory_saveOrUpdate.action";
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

<s:form id="jcddetailform" name="jcddetailform" action="officeTestCategory_create" namespace='/officeTest' theme="simple">
	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
	         <td class="navig" align="right"> 
	          
          </td>
	       </tr>
	 	</table>
	<div style="margin:5px">
		<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" 
			style="padding-top:5px;padding-bottom:5px" id="tableinputtable">
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.code" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="testCategory.code" id="testCategory.code" cssClass="d-inputtext d-widthb"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.name" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="testCategory.name" id="testCategory.name" cssClass="d-inputtext d-widthb"></s:textfield>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="officeTestBank.column.remark" />：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="testCategory.remark" id="testCategory.remark" cssClass="d-inputtext d-widthb"></s:textfield>
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
                		<c:if test="${testCategory.id == null}">
                			<delmar:operatePriv operator="create" >
	                			<input type="button" name="btnsubmit" id="btnsubmitNew" value="<delmar:message key="wfDetail.button.add" />" 
	                				Class="input_submit" style="border:0;" onclick="validate()" >
	                		</delmar:operatePriv>	
                		</c:if>
                		
                		<c:if test="${testCategory.id != null}">
                			<delmar:operatePriv operator="update">
								<input onclick="validate();"  type="button" value="<delmar:message key="wfDetail.button.update" />"  class="input_submit" >
							</delmar:operatePriv>
                		</c:if>
                			
             			<input onclick="window.location='<c:url value="/officeTest/officeTestBank_list.action"/>'"  type="button" 
         					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                	</td>
                	<td>&nbsp;
					</td>
                </tr>
            </table>	
<input id="testCategory.id" name="testCategory.id" type="hidden" value="<s:property value="testCategory.id"/>"/>
<input id="testCategory.testBankId" name="testCategory.testBankId" type="hidden" value="<s:property value="testCategory.testBankId"/>"/>
</s:form>

<script language="javascript">
 
</script>







</body>
</html>
