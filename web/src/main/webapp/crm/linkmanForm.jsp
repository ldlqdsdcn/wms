<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="linkman.title"/>
</title>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>

<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>

<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>


<script type="text/javascript">

function validateForm()
{

	var name=document.getElementById("name");
	if(isEmpty(name.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="linkman.column.name"/>');
		return false;
		}

	var customerId=document.getElementById("customerId");
	if(isEmpty(customerId.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkman.column.customerid"/>');
		return false;
	}
	
	var phoneNo=document.getElementById("phoneNo");
	var busPhone=document.getElementById("busPhone");
	var email=document.getElementById("email");
	if(isEmpty(phoneNo.value)&&isEmpty(busPhone.value)&&isEmpty(email.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkman.column.phonenobusphoneno"/>');
		return false;
	}
		
	

	return true;
}


$(document).ready(function() {

	 
	 DWREngine.setAsync(false); 				
   	 $('#customer').autocomplete(
				{
					source : function(request, response) {
						var availableTags = [];
						var value = $("#customer").val();

						customerDwr.getCustomerList(value, function(data) {
							if(data!=null)
							for (var i = 0; i < data.length; i++) {
								var vendor = data[i];
								vendor.label=data[i].name;
								vendor.value=data[i].name;
								availableTags.push(vendor);
							}

						});
						
						response(availableTags);
					},
					select : function(event, ui) {
						$("#customerId").val(ui.item.id);
						$("#customer").val(ui.item.name);

					}
				});

     
       	jQuery('#birthday').datepicker({changeMonth: true,changeYear: true,showOn: "button",
             buttonImage: "../images/DatePicker.GIF",
             buttonImageOnly: true,
             dateFormat:"yy-mm-dd",    		             
             buttonText: "Select date"});

});

</script>
</head>



<body>

<s:form id="editForm" action="linkman_edit" namespace='/crm' theme="simple">
<s:hidden id="id" name="linkman.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	<delmar:message key="linkman.location"/>
</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
				<s:submit method="getPrevionsOne"  value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"></s:submit>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"></s:submit>
			
			</c:if>
		</div></td>
        </tr>
      </table>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
           <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1" id="tableinputtable">
               
				<tr>
					<td> <delmar:message key="linkman.column.customerid"/> </td>
					<td colspan="3">
						<s:textfield name="linkman.customer.name" id="customer"   cssClass="required d-inputtext d-widthb" > </s:textfield>
						<s:hidden id="customerId" name="linkman.customerId"></s:hidden>
					</td>
				
				</tr>               
               <tr >
               		<td style="width:20%;">
               			<s:label for="linkcode" value="%{#session.resource.get('linkman.column.linkcode')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="linkman.linkCode" id="linkCode"  cssClass="required d-inputtext d-widthb" ></s:textfield>
               		</td>
               		<td style="width:20%;">
               			<s:label for="name"  value="%{#session.resource.get('linkman.column.name')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="linkman.name" id="name" cssClass="required d-inputtext d-widthb" ></s:textfield> 
               			<s:select list="callList" listKey="datadictId" listValue="name"  name="linkman.callId"></s:select>
               		</td>
               </tr>
               <tr >
            

               	<td>
               	<s:label for="ismain"  value="%{#session.resource.get('linkman.column.ismain')}" ></s:label>
               	</td>
               	<td>
                  <s:radio id="isMain" name="linkman.isMain" list="#{'Y':'Y','N':'N' }"></s:radio>
               	</td>   
              	<td >  	
              	  <s:label for="birthday" value="%{#session.resource.get('linkman.column.birthday')}" ></s:label>

               	</td>
               	<td >
               	<s:textfield  id="birthday"  readonly="true"  name="linkman.birthday"  cssClass="optional d-inputtext d-widthb" ><s:param name="value"><s:date name="linkman.birthday" format="yyyy-MM-dd "/></s:param></s:textfield>
               	</td>
				</tr>


				<tr>
					<td> <delmar:message key="linkman.column.department"/> </td>
					<td >
						<s:textfield name="linkman.department" id="department"   cssClass="d-inputtext d-widthb" > </s:textfield>
					</td>
					<td >
               	<s:label for="position"  value="%{#session.resource.get('linkman.column.position')}" ></s:label>
               	</td>
               	<td >
               	<s:textfield name="linkman.position" id="position" cssClass="d-inputtext d-widthb" ></s:textfield>
               	</td>
				</tr>

				 <tr>
               	<td>
               	<s:label for="busphone"  value="%{#session.resource.get('linkman.column.busphone')}" ></s:label>
               	</td>
               	<td>
               	<s:textfield name="linkman.busPhone" id="busPhone"   blurvalidate="yes" blurtype="telephone"  title="%{#session.resource.get('public.inputformat.telephone')}"  cssClass="required d-inputtext d-widthb" ></s:textfield>
               	</td>
               	
               	<td >
               	<s:label for="phoneNo"  value="%{#session.resource.get('linkman.column.phoneno')}" ></s:label>
               	</td>
               	<td>
               	<s:textfield name="linkman.phoneNo" id="phoneNo"   blurvalidate="yes" blurtype="mobile"  title="%{#session.resource.get('public.inputformat.mobile')}"  cssClass="required d-inputtext d-widthb" ></s:textfield>
               	</td>
               	               	
				</tr>	

				<tr>
               		<td>
               			<s:label for="email"  value="%{#session.resource.get('linkman.column.email')}" ></s:label>
               		</td>
               		<td >
               			<s:textfield name="linkman.email" id="email"  blurvalidate="yes" blurtype="email"  title="%{#session.resource.get('public.inputformat.email')}" cssClass="required d-inputtext d-widthb" ></s:textfield>
               		</td>
               			<td>
               			<s:label for="qq"  value="%{#session.resource.get('linkman.column.qq')}" ></s:label>
               			</td>
               			<td >
               					<s:textfield name="linkman.qq" id="qq"  cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
               </tr>
					<tr>
					
					<td>
               			<s:label for="skyper"  value="%{#session.resource.get('linkman.column.skyper')}" ></s:label>
               			</td>
               			<td  colspan="3">
               					<s:textfield name="linkman.skyper" id="skyper"  cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
					</tr>	
					<tr>
						<td>
							<s:label for="other"  value="%{#session.resource.get('linkman.column.other')}" ></s:label>
						</td>
						<td colspan="3">
						<s:textarea id="other" name="linkman.other" cssStyle="width:700px;height:60px;"></s:textarea>
						</td>
					</tr>
					<tr>
						<td>
							<s:label for="hobby"  value="%{#session.resource.get('linkman.column.hobby')}" ></s:label>
						</td>
						<td colspan="3">
						<s:textarea id="hobby" name="linkman.hobby" cssStyle="width:700px;height:60px;"></s:textarea>
						</td>
					</tr>
					<tr >
						<td>
							<s:label for="taboo"  value="%{#session.resource.get('linkman.column.taboo')}" ></s:label>
						</td>
						<td colspan="3">
						<s:textarea id="taboo" name="linkman.taboo" cssStyle="width:700px;height:60px;"></s:textarea>
						</td>
					</tr>
					<tr >
						<td>
							<s:label for="remark"  value="%{#session.resource.get('common.label.remark')}" ></s:label>
						</td>
						<td colspan="3">
						<s:textarea id="remark" name="linkman.remark" cssStyle="width:700px;height:60px;"></s:textarea>
						</td>
					</tr>
					
			     <tr >
               		<td style="width:20%;">
               			<s:label for="userName"  value="%{#session.resource.get('customer.column.user')}" ></s:label>
               			</td>
               			<td style="width: 30%">
               			<s:textfield name="linkman.user.name" id="userName" disabled="true" cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
               			<td style="width:20%;">
               			<s:label for="orgId"  value="%{#session.resource.get('customer.column.org')}" ></s:label>
               			</td>
               			<td style="width: 30%">
                        <s:textfield name="linkman.org.name" id="orgName" disabled="true" cssClass="d-inputtext d-widthb" ></s:textfield>               			
               			</td>
                </tr>
                					
				<tr >
               		<td >
               			<s:label for="createdby"   value="%{#session.resource.get('customer.column.createdby')}" ></s:label>
               		</td>
               		<td >
               			<s:textfield name="linkman.createdByUser.name" id="createdBy" disabled="true" cssClass="d-inputtext d-widthb" ></s:textfield>
               		</td>
               		<td >
               			<s:label for="created"  value="%{#session.resource.get('customer.column.created')}" ></s:label>
               		</td>
               		<td ><s:textfield  id="created" disabled="true"  cssClass="d-inputtext d-widthb" ><s:param name="value"><s:date name="linkman.created" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               </tr>
               	<tr>
               		<td >
               			<s:label for="updatedby"   value="%{#session.resource.get('customer.column.updatedby')}" ></s:label>
               			</td>
               			<td >
               			<s:textfield name="linkman.updatedByUser.name" id="updatedBy" disabled="true" cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
               			<td>
               			<s:label for="updated"  value="%{#session.resource.get('customer.column.updated')}" ></s:label>
               			</td>
               			<td>
               			<s:textfield  name="linkman.updated" id="updated" disabled="true"  cssClass="d-inputtext d-widthb" >
               			<s:param name="value">
               			<s:date name="linkman.updated" format="yyyy-MM-dd HH:mm:ss"/>
               			</s:param>
               			</s:textfield>   
               			
               			</td>
               </tr>	

		  	  <tr>
                <td colspan="4" class="td_page_right">
                	<delmar:operatePriv operator="create" >
                		<s:submit method="create"   value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"></s:submit>
                	</delmar:operatePriv>
               		<delmar:operatePriv operator="create" idpk="${linkman.id }">
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="update" idpk="${linkman.id }">
						<s:submit method="save"   value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="delete" idpk="${linkman.id }">
						<s:submit method="delete"   value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="create" path="/crm/linkrecord" idpk="${linkman.id>0  ? 0 :1 }" >
						<input onclick="window.location='<c:url value="/crm/linkrecord_create.action"/>?from=linkman&fromid=${linkman.id}'"  type="button" value="<delmar:message key="linkman.button.createlinkrecord"/>"  class="input_submit" >
					</delmar:operatePriv>					
					<delmar:operatePriv operator="view" >
						<input onclick="window.location='<s:property value="%{#session.MAP_KEY_OF_SESSION.returnaction}"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
					</delmar:operatePriv>
				 </td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>

</s:form>





<script language="javascript">
  //进行一些验证的绑定
    
 $(document).ready(function() {
	 
	 $(document).D_Validate();
	 
	 $("#tableinputtable tr:even").addClass("query_two");
	 $("#tableinputtable tr:odd").addClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_two");	
	 

	 
	 
	 if ($("#isMainY").attr("checked")=="checked")
	 {
//		 $("#isMainY").attr("disabled","true");
		 $("#isMainN").attr("disabled","true");		 
	 }
	    
	

});  
  
   
  

  

</script>



</body>
</html>
