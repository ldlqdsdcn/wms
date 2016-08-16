<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<%@page import="com.delmar.utils.ProDateUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="linkrecord.title"/>
</title>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>

<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>


<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/dm/uploadfile.js'></script>


<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-timepicker-addon.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-sliderAccess.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/i18n/jquery-ui-timepicker-addon-i18n.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/i18n/jquery-ui-timepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<link rel="Stylesheet" href="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-timepicker-addon.min.css" type="text/css" />


<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkmanDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>


<script type="text/javascript">

function validateForm()
{

	var customerId=document.getElementById("customerId");
	if(isEmpty(customerId.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.customerid"/>');
		document.getElementById('customer').focus();
		return false;
		}

	var linkmanId=document.getElementById("linkmanId");
	if(isEmpty(linkmanId.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.linkmanid"/>');
		document.getElementById('linkmanname').focus();
		return false;
	}
	
	var contactDate=document.getElementById("contactDate");
	if(isEmpty(contactDate.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.contactdate"/>');
		document.getElementById('contactDate').focus();
		return false;
	}
	var contactTitle=document.getElementById("contactTitle");
	if(isEmpty(contactTitle.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.contacttitle"/>');
		document.getElementById('contactTitle').focus();
		return false;
	}	
	var resultId=document.getElementById("resultId");
	if(isEmpty(resultId.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.resultid"/>');
		document.getElementById('resultId').focus();
		return false;
	}	
	var contactRecord=document.getElementById("contactRecord");
	if(isEmpty(contactRecord.value))
	{
		alert('<delmar:warn errorKey="errors.required" key="linkrecord.column.contactrecord"/>');
		document.getElementById('contactRecord').focus();
		return false;
	}		
	return true;
}
jQuery.datepicker.setDefaults(jQuery.datepicker.regional['zh_CN']);
$(document).ready(function() {

	 
	 DWREngine.setAsync(false); 

		$('#linkmanname').autocomplete(
				{
					delay: 1000,
					source : function(request, response) {
						var availableTags = [];
						var value = $("#linkmanname").val();
						var customerId=$("#customerId").val();
						linkmanDwr.getLinkmanList(value,customerId, function(data) {
							//alert(data+"data.length="+data.length);
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
						$("#linkmanId").val(ui.item.id);
						$("#linkmanname").val(ui.item.name);

					}
				});

				
		$('#customer').autocomplete(
				{
					delay: 1000,
					source : function(request, response) {
						var availableTags = [];
						var value = $("#customer").val();
						$("#linkmanId").val('');
						$("#linkmanname").val('');
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
						
						linkmanDwr.getLinkmanList("",ui.item.id, function(data) {
							if(data!=null)
							{
								var vendor = data[0];
								$("#linkmanId").val(vendor.id);
								$("#linkmanname").val(vendor.name);								
							}
						

						});						

					}
				});

	             
       	jQuery('#contactDate').datetimepicker({changeMonth: true,changeYear: true,showOn: "button",
             buttonImage: "../images/DatePicker.GIF",
             buttonImageOnly: true,
             buttonText: "Select date",
             dateFormat:"yy-mm-dd",             
             minDate: -7,
             maxDate: new Date('<%=ProDateUtil.getShortHisDateStr(0)%>'),
             onClose:function(selectedDate) {
                 $("#nextTime").datepicker("option","minDate",selectedDate);	
              }             
             });
     	jQuery('#nextTime').datetimepicker({changeMonth: true,changeYear: true,showOn: "button",
             buttonImage: "../images/DatePicker.GIF",
             buttonImageOnly: true,
             buttonText: "Select date",
             dateFormat:"yy-mm-dd"
           });

});

</script>
</head>



<body>
 
<s:form id="editForm" action="linkrecord_edit" namespace='/crm' theme="simple">
<s:hidden id="id" name="linkrecord.id"></s:hidden>
<s:hidden id="from" name="from" value="%{#session.MAP_KEY_OF_SESSION.from}"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	       <delmar:message key="linkrecord.location"/>
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
               		<td style="width:30%;">               		 
               			<s:label for="linkRecordId" value="%{#session.resource.get('linkrecord.column.linkrecordid')}" ></s:label>
               		</td>
               		<td colspan=3>
               			<s:textfield name="linkrecord.linkRecordId" id="linkRecordId" style="readonly:true;" cssClass="required d-inputtext d-widthb"></s:textfield>
               		</td>
               	   </tr>
                   <tr>
               		<td style="width:20%;">
               			<s:label for="customerId" value="%{#session.resource.get('linkrecord.column.customerid')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="linkrecord.customer.name" id="customer" cssClass="required d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="linkrecord.customerId" id="customerId"></s:hidden>
               		</td>
               		<td style="width:20%;">
               			<s:label for="linkmanId"  value="%{#session.resource.get('linkrecord.column.linkmanid')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="linkrecord.linkman.name" id="linkmanname" cssClass="required d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="linkrecord.linkmanId" id="linkmanId"></s:hidden>
               		</td>
               </tr>
                <tr>
               		<td style="width:20%;">
               			<s:label for="contactModeId" value="%{#session.resource.get('linkrecord.column.contactmodeid')}" ></s:label>
               		</td>
               		<td >
               			<s:select list="contactModeList" listKey="datadictId" listValue="name"  name="linkrecord.contactModeId" id="contactModeId"  cssClass="d-inputtext d-widthb"></s:select>
               		</td>
               		<td>
               			<s:label for="forceOnId" value="%{#session.resource.get('linkrecord.column.forceonid')}" ></s:label>
               		</td>
               		<td>
               		<s:select list="forceOnList" listKey="datadictId" listValue="name"  name="linkrecord.forceOnId" id="forceOnId"  cssClass="d-inputtext d-widthb"></s:select>
               		</td>
               			
               </tr>
               <tr>
               	<td >
               	<s:label for="contactDate" value="%{#session.resource.get('linkrecord.column.contactdate')}" ></s:label>

               	</td>
               	<td>
               	<s:textfield  id="contactDate"  readonly="true"  name="linkrecord.contactDate"  cssClass="required d-inputtext d-widthb"><s:param name="value"><s:date name="linkrecord.contactDate" format="yyyy-MM-dd HH:mm"/></s:param></s:textfield>
               	</td>
				<td>
				<s:label for="contactTitle" value="%{#session.resource.get('linkrecord.column.contacttitle')}" ></s:label>
				</td>
				<td>
					<s:textfield name="linkrecord.contactTitle" id="contactTitle" cssClass="required d-inputtext d-widthb"></s:textfield>
				</td>

				</tr>
				
               <tr>
               	<td >
               	<s:label for="resultId" value="%{#session.resource.get('linkrecord.column.resultid')}"></s:label>

               	</td>
               	<td colspan=3>
               	<s:select list="resultList" listKey="datadictId" listValue="name"  name="linkrecord.resultId" id="resultId"  cssClass="required d-inputtext d-widthb" ></s:select>               	
               	</td>


				</tr>
								
				<tr >
					<td> <delmar:message key="linkrecord.column.contactrecord"/> </td>
					<td colspan="3">
						<s:textarea id="contactRecord" name="linkrecord.contactRecord" cssStyle="width:700px;height:60px;"></s:textarea>
					</td>
				
				</tr>
				<tr >
               <td>
               	<s:label for="nextTime" value="%{#session.resource.get('linkrecord.column.nexttime')}" ></s:label>

               	</td>
               	<td>
               	<s:textfield  id="nextTime"  readonly="true"  name="linkrecord.nextTime"  cssClass="required d-inputtext d-widthb" ><s:param name="value"><s:date name="linkrecord.nextTime" format="yyyy-MM-dd HH:mm"/></s:param></s:textfield>
               	</td>
				<td>
						 	<s:label for="nextGoal" value="%{#session.resource.get('linkrecord.column.nextgoal')}" ></s:label>
				</td>
				<td>
							<s:textfield name="linkrecord.nextGoal" id="nextGoal"></s:textfield>
				</td>
				</tr>
				
			<tr>
						<td>
						 
							<s:label for="remark"  value="%{#session.resource.get('common.label.remark')}" ></s:label>
							 
						</td>
						<td colspan="3">
						 
						<s:textarea id="remark" name="linkrecord.remark" cssStyle="width:700px;height:60px;"></s:textarea>
						
						</td>
					</tr>
                <tr>
                <td colspan=4>
                <delmar:file tableName="linkrecord"  tableId="${linkrecord.id }" />
                </td>
                
                </tr>					
			     <tr >
               		<td style="width:20%;">
               			<s:label for="userName"  value="%{#session.resource.get('customer.column.user')}" ></s:label>
               			</td>
               			<td style="width: 30%">
               			<s:textfield name="linkrecord.user.name" id="userName" disabled="true"  cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
               			<td style="width:20%;">
               			<s:label for="orgId"  value="%{#session.resource.get('customer.column.org')}" ></s:label>
               			</td>
               			<td style="width: 30%">
                        <s:textfield name="linkrecord.org.name" id="orgName" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>               			
               			</td>
                </tr>					
				<tr>
               		<td style="width:20%;">
               			<s:label for="createdBy"   value="%{#session.resource.get('customer.column.createdby')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="linkrecord.createdByUser.name" id="createdBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               		<td style="width:20%;">
               			<s:label for="created"  value="%{#session.resource.get('customer.column.created')}" ></s:label>
               		</td>
               		<td style="width: 30%"><s:textfield  id="created" disabled="true"  name="linkrecord.created" cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="linkrecord.created" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               </tr>
               	<tr>
               		<td style="width:20%;">
               			<s:label for="updatedBy"   value="%{#session.resource.get('customer.column.updatedby')}" ></s:label>
               			</td>
               			<td style="width: 30%">
               			<s:textfield name="linkrecord.updatedByUser.name" id="updatedBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               			</td>
               			<td style="width:20%;">
               			<s:label for="updated"  value="%{#session.resource.get('customer.column.updated')}" ></s:label>
               			</td>
               			<td style="width: 30%"><s:textfield  name="linkrecord.updated" id="updated" disabled="true"  cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="linkrecord.updated" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>   
               			
               			</td>
               </tr>
               
               <tr>
               	<td>
				<s:label for="nextComments" value="%{#session.resource.get('linkrecord.column.nextcomments')}" ></s:label>
				</td>
				<td colspan=3>
                       <s:textarea id="nextComments" name="linkrecord.nextComments" cssStyle="width:700px;height:60px;"></s:textarea>				
				</td>
               	
               	
               	
				</tr>
				
               <c:if test="${linkrecord.id>0}">
               <tr>
               	<td colspan=4 style="padding-top:20px;padding-bottom:20px;padding-left:10px;padding-right:10px" align="center">
               	   
                	<delmar:operatePriv operator="create" >
            
                	   <input type="button" name="addBf" id="addBf" value="<delmar:message key='common.button.addnew'/>" class="input_submit" >
                	</delmar:operatePriv>
           	       
				  <table  width="100%" border="0"  cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue" style="margin-top:10px;">
				  <thead>
				    <th><delmar:message key="busforecast.column.busforecastid"/></th>
				    <th><delmar:message key="busforecast.column.planeocean"/></th>
				    <th><delmar:message key="busforecast.column.outin"/></th>
				    <th><delmar:message key="busforecast.column.polid"/></th>
				    <th><delmar:message key="busforecast.column.poaid"/></th>
				    <th><delmar:message key="busforecast.column.fromdate"/></th>
				    <th><delmar:message key="busforecast.column.todate"/></th>
				    <th><delmar:message key="busforecast.column.goodsweight"/></th>				    
				    <th><delmar:message key="busforecast.column.goodssize"/></th>
				    <th><delmar:message key="busforecast.column.teunum"/></th>
				    <th><delmar:message key="busforecast.column.currencyid"/></th>
				    <th><delmar:message key="busforecast.column.profit"/></th>
				  </thead>
				   <tbody>
						<s:iterator value="lrbfList" id="lrbfobj" status="st">
						    <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
						      <td>
							    <a href="javascript:viewExport('<c:out value="${lrbfobj.id}"/>')"><c:out value="${lrbfobj.busForecastId}"/></a>
						      </td>
						      <td>
     					        <c:out value="${lrbfobj.planeOcean}"/>
						      </td>
						      <td>
                                <c:out value="${lrbfobj.outIn}"/>						      
						      </td>
						      <td>						      						   
                                   <c:out value="${lrbfobj.pol.name}"/>
						      </td>						      
						      <td>		
                                  <c:out value="${lrbfobj.poa.name}"/>
						      </td>		
						      <td>	
						         <s:date name="%{#lrbfobj.fromDate}" format="yyyy-MM-dd HH:mm:ss"/>	
						      </td>			
						      <td>		
						        <s:date name="%{#lrbfobj.toDate}" format="yyyy-MM-dd HH:mm:ss"/>
			      			
						      </td>	
						      <td>	
   			      			    <c:out value="${lrbfobj.goodsWeight}"/>
						      </td>								      							      				      				      
						      <td>	
   			      			    <c:out value="${lrbfobj.goodsSize}"/>
						      </td>								      							      				      				      
						      <td>	
   			      			    <c:out value="${lrbfobj.teuNum}"/>
						      </td>	
						      <td>
						       <c:out value="${lrbfobj.currency.code}"/>
						      </td>							      							      				      				      
						      <td>	
   			      			     <c:out value="${lrbfobj.profit}"/>
						      </td>								      							      				      				      
						      						      
						      						      
				            </tr>
				       </s:iterator>
				 </tbody>
				  
				  
				  </table>
				
				</td>

               	
               	
               	
				</tr>
								
				</c:if>

			<tr>
                <td colspan="4" class="td_page_right">
                	<delmar:operatePriv operator="create" >
                		<s:submit method="create"   value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"></s:submit>
                	</delmar:operatePriv>
               		<delmar:operatePriv operator="create" idpk="${linkrecord.id }">
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="update" idpk="${linkrecord.id }">
						<s:submit method="save"   value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="delete" idpk="${linkrecord.id }">
						<s:submit method="delete"   value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
					</delmar:operatePriv>
               		<delmar:operatePriv operator="update" idpk="${linkrecord.id }">
						<input type="button"  id="uploadFile" value="<delmar:message key='public.uploadfile.file'/>"   class="input_submit" >
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

     <c:if test="${!beAmend}">	 
	 $("#tableinputtable").find("input[type=text]").attr("readonly","true");
	 $("#tableinputtable").find("textarea").attr("readonly","true");
	 $("#tableinputtable").find("select").attr("disabled","true");
	 //$("#tableinputtable").find("img").find("#ui-datepicker-trigger").addClass("d-hidden");
	 $(".ui-datepicker-trigger").addClass("d-hidden");
	 $("#nextComments").removeAttr("readonly");
	 </c:if>
	 
	 
	 
	 $("#addBf").bind("click",function() {addNewBF()});
	 $("#uploadFile").bind("click",function() {uploadFileDelmar("linkrecord",$("#id").val(),null,'<delmar:message key="public.uploadfile.title"/>')});

});  
  
  
  function addNewBF()
  {
	  window.location.href="busForecast_create.action?from=linkrecord&fromid="+$("#id").val();
  }
   
  
  function viewExport(id) {
      window.location='<c:url value="/crm/busForecast_edit.action"/>?from=linkrecord&id='+id;
   }
  

</script>





</body>
</html>
