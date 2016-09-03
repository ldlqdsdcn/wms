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
	 
	 $("#languageTable tr:even").addClass("query_two");
	 $("#languageTable tr:odd").addClass("query_one");
	 $("#languageTable tr:last").removeClass("query_one");
	 $("#languageTable tr:last").removeClass("query_two");	
	 
	 $("#itemValueTable tr:even").addClass("query_two");
	 $("#itemValueTable tr:odd").addClass("query_one");
	 $("#itemValueTable tr:last").removeClass("query_one");
	 $("#itemValueTable tr:last").removeClass("query_two");	
	 
});

function validate(type) {
	var value = document.getElementById("sysSettingsItem.value").value;
	var name = document.getElementById("sysSettingsItem.name").value;
	var setLevel = document.getElementById("sysSettingsItem.setLevel").value;
	var setType = document.getElementById("sysSettingsItem.setType").value;
	var indexOrder = document.getElementById("sysSettingsItem.indexOrder").value;
	
    if(value == ""){
    	alert("<delmar:message key="sysSettingsItem.message.code"/>");
		return false;
    }
    if(name == ""){
    	alert("<delmar:message key="sysSettingsItem.message.name"/>");
		return false;
    }
    if(setLevel == ""){
    	alert("<delmar:message key="sysSettingsItem.message.level"/>");
		return false;
    }
    if(setType == ""){
    	alert("<delmar:message key="sysSettingsItem.message.inputType"/>");
		return false;
    }
    
    if(!(/\d+(\.\d+)?/g.test(indexOrder))) {
		alert("<delmar:message key="sysSettingsItem.message.indexOrder"/>");
		return false;
	}
    
    var size = "<s:property value="itemValueList.size"/>";
    var selectNum = 0;
   	for (var i = 0; i < size; i++) {
	   var value = document.getElementById("jcddetailform_itemValueList_" + i + "__value").value;
	   var name = document.getElementById("jcddetailform_itemValueList_" + i + "__name").value;
	   var defaulted = document.getElementById("itemValueList[" + i + "].defaulted").value;
	   
	   if (defaulted == 1) {
		   selectNum += 1;
	   }
	   
	   if (name != "" && value == "") {
		   alert("<delmar:message key="sysSettingsItem.message.sysSettingsItemCode"/>");
		   return false;
	   }
	   
	   if ((value != "" && name == "" )) {
		   alert("<delmar:message key="sysSettingsItem.message.sysSettingsItemName"/>");
		   return false;
	   }
   	}
   	
   	//多选
   	if (setType == 3) {
   		if (selectNum < 1) {
   	   		alert("<delmar:message key="sysSettingsItem.message.moreInputType"/>");
   	   		return false;
   	   	}
   	}
   	
   	if (setType != 3 && selectNum != 1) {
   		alert("<delmar:message key="sysSettingsItem.message.defaulted"/>");
   		return false;
   	}
    
   	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/sysSettings/sysSettingsItemAction_saveOrUpdate.action?saveType=" + type;
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

<s:form id="jcddetailform" name="jcddetailform" action="sysSettingsItemAction_create" namespace='/sysSettings' theme="simple">
	<table border="1" cellpadding="0" cellspacing="0" class="cTableBorder">
	       <tr> 
	         <td align="left" class="navig"><delmar:message key="sysSettingsItem.title.lacation"/></td>
	         <td class="navig" align="right"> 
	          
          </td>
	       </tr>
	 	</table>
	<div style="margin:5px">
		
		<table width="100%" border="0" id="tableinputtable">
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.value"/>：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="sysSettingsItem.value" id="sysSettingsItem.value" cssClass="d-inputtext d-widthb" style="width:30%"  />
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.name"/>：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="sysSettingsItem.name" id="sysSettingsItem.name" cssClass="d-inputtext d-widthb" style="width:30%"  />
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.level"/>：
		  	</td>
		  	<td  class="d-tdinput">
		  		<s:select  list="accessList" listKey="datadictId" listValue="name" name="sysSettingsItem.setLevel" id="sysSettingsItem.setLevel" 
			 					value="%{sysSettingsItem.setLevel}"	style="height:24px;width:120px" ></s:select>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="sysSettingsItem.column.inputType"/>：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:select  list="inputTypeList" listKey="value" listValue="name" name="sysSettingsItem.setType" id="sysSettingsItem.setType" 
			 					value="%{sysSettingsItem.setType}"	style="height:24px;width:120px" ></s:select>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="sysSettingsItem.column.indexOrder"/>：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="sysSettingsItem.indexOrder" id="sysSettingsItem.indexOrder" cssClass="d-inputtext d-widthb" style="width:30%"/>
		  	</td>
		  </tr>
		  <tr> 
		  	<td class="d-tdlabel">
		  		<delmar:message key="sysSettingsItem.column.remark"/>：
		  	</td>
		  	<td class="d-tdinput">
		  		<s:textfield name="sysSettingsItem.remark" id="sysSettingsItem.remark" cssClass="d-inputtext d-widthb" style="width:30%"/>
		  	</td>
		  </tr>
		  <tr>
		  	<td  colspan="2">
		  		&nbsp;
		  	</td>
		  </tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
			<tr>
			 	<td width="90" height="26" align="center" background="../images/table_page_1.gif">
	            	<delmar:message key="common.label.internationalization"/>
	            </td>
	           	<td background="../images/table_page_bg.gif" width="*"  height="26">
	           		<div class="C_S_F_L">&nbsp;</div>
	           	</td>
				
			</tr>
		</table>
		
		<table width="100%" border="0" id="languageTable">
			<tr>
		  		<td class="d-tdlabel" >      	
			   		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.language"/>
			   </td>
		       <td class="d-tdlabel" >      	
			   		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.name"/>
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="sysSettingsItem.column.remark"/>
			   </td>
			  
			</tr>
				<s:iterator status="st" value="itemTrlList">
					<tr>
						<td class="d-tdinput">
							<s:hidden name="%{'itemTrlList['+#st.index+'].id'}"/>
							<s:hidden name="%{'itemTrlList['+#st.index+'].itemId'}"/>
							<s:textfield name="%{'itemTrlList['+#st.index+'].language'}" cssClass="d-inputtext d-widthb"  readonly="true"/>
						</td>
						<td class="d-tdinput">
							<s:textfield name="%{'itemTrlList['+#st.index+'].name'}" cssClass="d-inputtext d-widthb" />
						</td>
						<td class="d-tdinput">
							<s:textfield name="%{'itemTrlList['+#st.index+'].remark'}" cssClass="d-inputtext d-widthb" />
						</td>
					</tr>
					
				</s:iterator>
			<tr>
				<td>&nbsp;
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			
			<tr>
			 	<td width="90" height="26" align="center" background="../images/table_page_1.gif">
	            	<delmar:message key="sysSettingsItem.column.sysSettingsInter"/>
	            </td>
	           	<td background="../images/table_page_bg.gif" width="*"  height="26">
	           		<div class="C_S_F_L">&nbsp;</div>
	           	</td>
				
			</tr>
		</table>
		
		
		<table width="100%" border="0" id="itemValueTable" cellspacing="0" cellpadding="0">
			<tr >
				<td class="d-tdlabel" >      	
			   		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.value"/>
			   </td>
		       <td class="d-tdlabel" >      	
			   		<font color="#FC0107">*</font><delmar:message key="sysSettingsItem.column.name"/>
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="sysSettingsItem.column.remark"/>
			   </td>
			   <td  class="d-tdlabel">
			       	<delmar:message key="sysSettingsItem.column.defaulted"/>
			   </td>
			   <s:iterator status="language" value="languageList">
			   		<td  class="d-tdlabel">
				       	<delmar:message key="sysSettingsItem.column.nameLanguage"/>(<s:property value="code" />)
				   </td>
				   <td  class="d-tdlabel">
				       	<delmar:message key="sysSettingsItem.column.remarkValue"/>(<s:property value="code"/>)
				   </td>
			   </s:iterator>
			</tr>
				 <%
				   int th = 0;
				%>
				<s:iterator status="itemValue" value="itemValueList">
					<tr>
						<td class="d-tdinput">
							<s:hidden name="%{'itemValueList['+#itemValue.index+'].id'}"/>
							<s:hidden name="%{'itemValueList['+#itemValue.index+'].itemId'}"/>
							<s:textfield name="%{'itemValueList['+#itemValue.index+'].value'}"  cssClass="d-inputtext"/>
						</td>
						<td class="d-tdinput">
							<s:textfield name="%{'itemValueList['+#itemValue.index+'].name'}" cssClass="d-inputtext"/>
						</td>
						<td class="d-tdinput">
							<s:textfield name="%{'itemValueList['+#itemValue.index+'].remark'}"cssClass="d-inputtext" />
						</td>
						<td class="d-tdinput">
							<s:select  list="defaultedList" listKey="value" listValue="name" 
								name="%{'itemValueList['+#itemValue.index+'].defaulted'}" 
								id="%{'itemValueList['+#itemValue.index+'].defaulted'}" 
			 					value="defaulted"	style="height:24px;width:120px" ></s:select>
						</td>
						<%
						   int language = 0;
						%>
						
						<s:iterator status="language" value="languageList">
							<s:hidden name="%{'itemValueList['+#itemValue.index+'].sysSettingsItemValueTrlList['+#language.index+'].id'}"/>
							<s:hidden name="%{'itemValueList['+#itemValue.index+'].sysSettingsItemValueTrlList['+#language.index+'].itemvalueId'}"/>
							<input name="itemValueList[<%=th %>].sysSettingsItemValueTrlList[<%=language %>].language" value="<s:property value="code"/>" 
								id="jcddetailform_itemValueList_<%=th %>__sysSettingsItemValueTrlList_<%=language %>__language" type="hidden">
							
							<td class="d-tdinput">
								<s:textfield name="%{'itemValueList['+#itemValue.index+'].sysSettingsItemValueTrlList['+#language.index+'].name'}"  cssClass="d-inputtext"/>
							</td>
							<td class="d-tdinput">
								<s:textfield name="%{'itemValueList['+#itemValue.index+'].sysSettingsItemValueTrlList['+#language.index+'].remark'}" cssClass="d-inputtext"/>
							</td>
							<%
							language = language + 1;
							%>
						</s:iterator>
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
                	<td>
                			<delmar:operatePriv operator="create" >
                				<input type="button" name="btnsubmit" id="btnsubmitNew" value="<delmar:message key="sysSettingsItem.button.save"/>" 
                					Class="input_submit" style="border:0;" onclick="validate(0)" >
                			</delmar:operatePriv>	
                		
                			<delmar:operatePriv operator="create">
								<input onclick="validate(1);"  type="button" value="<delmar:message key="sysSettingsItem.button.addAndNew"/>"  class="input_submit" >
							</delmar:operatePriv>
                			
             				<input onclick="window.location='<c:url value="/sysSettings/sysSettingsItemAction_list.action"/>'"  type="button" 
         						value="<delmar:message key="common.button.back" />"  class="input_submit" >
                	</td>
                	<td>&nbsp;
					</td>
                </tr>
            </table>	
<input id="sysSettingsItem.id" name="sysSettingsItem.id" type="hidden" value="<s:property value="sysSettingsItem.id"/>"/>
<input id="sysSettingsItem.clientId" name="sysSettingsItem.clientId" type="hidden" value="<s:property value="sysSettingsItem.clientId"/>"/>
<input id="sysSettingsItem.orgId" name="sysSettingsItem.orgId" type="hidden" value="<s:property value="sysSettingsItem.orgId"/>"/>
<input id="sysSettingsItem.userId" name="sysSettingsItem.userId" type="hidden" value="<s:property value="sysSettingsItem.userId"/>"/>
<input id="sysSettingsItem.userName" name="sysSettingsItem.userName" type="hidden" value="<s:property value="sysSettingsItem.userName"/>"/>
<input id="sysSettingsItem.created" name="sysSettingsItem.created" type="hidden" value="<s:property value="sysSettingsItem.created"/>"/>
<input id="sysSettingsItem.createdBy" name="sysSettingsItem.createdBy" type="hidden" value="<s:property value="sysSettingsItem.createdBy"/>"/>
<input id="sysSettingsItem.createdByName" name="sysSettingsItem.createdByName" type="hidden" value="<s:property value="sysSettingsItem.createdByName"/>"/>
<input id="sysSettingsItem.updated" name="sysSettingsItem.updated" type="hidden" value="<s:property value="sysSettingsItem.updated"/>"/>
<input id="sysSettingsItem.updatedBy" name="sysSettingsItem.updatedBy" type="hidden" value="<s:property value="sysSettingsItem.updatedBy"/>"/>
<input id="sysSettingsItem.updatedByName" name="sysSettingsItem.updatedByName" type="hidden" value="<s:property value="sysSettingsItem.updatedByName"/>"/>
</s:form>

<script language="javascript">
 
</script>







</body>
</html>
