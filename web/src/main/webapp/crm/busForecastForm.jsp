<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<%@page import="com.delmar.utils.ProDateUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="busforecast.title"/>
</title>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>


<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-timepicker-addon.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-sliderAccess.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/i18n/jquery-ui-timepicker-addon-i18n.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/TimepickerAddon/dist/i18n/jquery-ui-timepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<link rel="Stylesheet" href="../js/jquery/plugin/TimepickerAddon/dist/jquery-ui-timepicker-addon.min.css" type="text/css" />


<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/linkrecordDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/portDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>


<script type="text/javascript">

function validateForm()
{

	var customerId=document.getElementById("customerId");
	if(isEmpty(customerId.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="busforecast.column.customerid"/>');
		document.getElementById('customer').focus();
		return false;
		}
	
	var polId=document.getElementById("polId");
	if(isEmpty(polId.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="busforecast.column.polid"/>');
		document.getElementById('pol').focus();
		return false;
		}
	
	
	var poaId=document.getElementById("poaId");
	if(isEmpty(poaId.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="busforecast.column.poaid"/>');
		document.getElementById('poa').focus();
		return false;
		}
	
	var fromDate=document.getElementById("fromDate");
	if(isEmpty(fromDate.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="busforecast.column.fromdate"/>');
		document.getElementById('fromDate').focus();
		return false;
		}
	

	var toDate=document.getElementById("toDate");
	if(isEmpty(toDate.value))
		{
		alert('<delmar:warn errorKey="errors.required" key="busforecast.column.todate"/>');
		document.getElementById('toDate').focus();
		return false;
		}	
	
	
		
	return true;
}
jQuery.datepicker.setDefaults(jQuery.datepicker.regional['zh_CN']);
$(document).ready(function() {

	 
	 DWREngine.setAsync(false); 

		$('#linkRecordCode').autocomplete(
				{
					delay: 1000,
					source : function(request, response) {
						var availableTags = [];
						var value = $("#linkRecordCode").val();
						var customerId=$("#customerId").val();
						linkrecordDwr.getLinkmanList(value,customerId,"linkrecordid", function(data) {
							//alert(data+"data.length="+data.length);
							if(data!=null)
							for (var i = 0; i < data.length; i++) {
								var vendor = data[i];
								
								vendor.label=data[i].linkRecordId;
								vendor.value=data[i].contractTitle;
								availableTags.push(vendor);
							}

						});
						
						response(availableTags);
					},
					select : function(event, ui) {
						$("#linkrecordId").val(ui.item.id);
						$("#linkrecordCode").val(ui.item.linkRecordId);

					}
				});

				
		$('#customer').autocomplete(
				{
					delay: 1000,
					source : function(request, response) {
						var availableTags = [];
						var value = $("#customer").val();
						$("#linkRecordCode").val('');
						$("#linkrecordId").val('');
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
		
		
		$('#pol').autocomplete(
				{
					source : function(request, response) {
						var availableTags = [];
						var value = $("#pol").val();
						portDwr.getPortList(value, function(data) {

							for (var i = 0; i < data.length; i++) {
								var vendor = data[i];
								vendor.label=data[i].name+(data[i].remark==null ? "":" "+data[i].remark);
								vendor.value=data[i].name+(data[i].remark==null ? "":" "+data[i].remark);
								availableTags.push(vendor);
							}

						});
						
						response(availableTags);
					},
					select : function(event, ui) {
						$("#polId").val(ui.item.portId);
						$("#pol").val(ui.item.name+(ui.item.remark==null ? "":" "+ui.item.remark));
					}
				});

		
		$('#poa').autocomplete(
				{
					source : function(request, response) {
						var availableTags = [];
						var value = $("#poa").val();
						portDwr.getPortList(value, function(data) {

							for (var i = 0; i < data.length; i++) {
								var vendor = data[i];
								vendor.label=data[i].name+(data[i].remark==null ? "":" "+data[i].remark);
								vendor.value=data[i].name+(data[i].remark==null ? "":" "+data[i].remark);
								availableTags.push(vendor);
							}

						});
						
						response(availableTags);
					},
					select : function(event, ui) {
						$("#poaId").val(ui.item.portId);
						$("#poa").val(ui.item.name+(ui.item.remark==null ? "":" "+ui.item.remark));
					}
				});

		
	             
       	jQuery('#fromDate').datepicker({changeMonth: true,changeYear: true,showOn: "button",
             buttonImage: "../images/DatePicker.GIF",
             buttonImageOnly: true,
             buttonText: "Select date",
             dateFormat:"yy-mm-dd",             
             minDate: -7,
             
             onClose:function(selectedDate) {
                 $("#toDate").datepicker("option","minDate",selectedDate);	
              }             
             });
     	jQuery('#toDate').datepicker({changeMonth: true,changeYear: true,showOn: "button",
             buttonImage: "../images/DatePicker.GIF",
             buttonImageOnly: true,
             buttonText: "Select date",
             dateFormat:"yy-mm-dd"
           });

});

</script>
</head>



<body>

<s:form id="editForm" action="busForecast_edit" namespace='/crm' theme="simple">
<s:hidden id="id" name="busForecast.id"></s:hidden>
<s:hidden id="from" name="from" value="%{#session.MAP_KEY_OF_SESSION.from}"></s:hidden>


<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	<delmar:message key="busforecast.location"/>
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
               			<s:label for="busForecastId" value="%{#session.resource.get('busforecast.column.busforecastid')}" ></s:label>
               		</td>
               		<td colspan=3>
               			<s:textfield name="busForecast.busForecastId" id="busForecastId" style="readonly:true;" cssClass="required d-inputtext d-widthb"></s:textfield>
               		</td>
               	</tr>

               <tr>
               		<td style="width:20%;">
               			<s:label for="customerId" value="%{#session.resource.get('busforecast.column.customerid')}" ></s:label>
               		</td>
               		<td style="width:30%">
               			<s:textfield name="busForecast.customer.name" id="customer" cssClass="required d-inputtext d-widthb" ></s:textfield>
               			<s:hidden name="busForecast.customerId" id="customerId"></s:hidden>
               		</td>
               		
             		<td style="width:20%;">
               			<s:label for="linkRecordId" value="%{#session.resource.get('busforecast.column.linkrecordid')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="busForecast.linkRecordCode" id="linkRecordCode" cssClass="d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="busForecast.linkRecordId" id="linkRecordId"></s:hidden>
               		</td>               		
               </tr>
               	<tr>
               		<td style="width:20%;">
               			<s:label for="planeOcean" value="%{#session.resource.get('busforecast.column.planeocean')}" ></s:label>
               		</td>
               		<td >
               			<s:select list="modeList" listKey="value" listValue="name"  name="busForecast.planeOcean" id="planeOcean" cssClass="d-inputtext d-widthb"></s:select>
               		</td>   
               		
               		<td style="width:20%;">
               			<s:label for="outIn" value="%{#session.resource.get('busforecast.column.outin')}" ></s:label>
               		</td>
               		<td >
					     <s:radio name="busForecast.outIn" id="outIn" list="outinList" listKey="id" listValue="value"  />
               		</td> 
               		               		            	
               	</tr>               
               <tr>
               		<td style="width:20%;">
               			<s:label for="polId" value="%{#session.resource.get('busforecast.column.polid')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="busForecast.pol.name" id="pol"  cssClass="d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="busForecast.polId" id="polId"></s:hidden>
               		</td>
               		
             		<td style="width:20%;">
               			<s:label for="poaId" value="%{#session.resource.get('busforecast.column.poaid')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="busForecast.poa.name" id="poa"  cssClass="d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="busForecast.poaId" id="poaId"></s:hidden>
               		</td>               		
               </tr>
                              
    
               <tr>
               	<td >
               	<s:label for="fromDate" value="%{#session.resource.get('busforecast.column.fromdate')}" ></s:label>

               	</td>
               	<td>
               	<s:textfield  id="fromDate"  readonly="true"  name="busForecast.fromDate" cssClass="required d-inputtext d-widthb"><s:param name="value"><s:date name="busForecast.fromDate" format="yyyy-MM-dd HH:mm"/></s:param></s:textfield>
               	</td>
               	
               	<td >
               	<s:label for="toDate" value="%{#session.resource.get('busforecast.column.todate')}" ></s:label>

               	</td>
               	<td>
               	<s:textfield  id="toDate"  readonly="true"  name="busForecast.toDate" cssClass="required d-inputtext d-widthb"><s:param name="value"><s:date name="busForecast.toDate" format="yyyy-MM-dd HH:mm"/></s:param></s:textfield>
               	</td>



				</tr>
								
				<tr >
					<td rowspan=3> <delmar:message key="busforecast.column.goodsdesc"/> </td>
					<td rowspan=3>
						<s:textarea id="GoodsDesc" name="busForecast.GoodsDesc"  cssStyle="width:260px;height:60px;"></s:textarea>
					</td>
				<td>
				  <s:label for="goodsWeight" value="%{#session.resource.get('busforecast.column.goodsweight')}" ></s:label>
				</td>
				<td>
				   <s:textfield name="busForecast.goodsWeight" id="goodsWeight"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="numeric"  title="%{#session.resource.get('public.inputformat.numeric')}"></s:textfield>
				</td>
				</tr>
				
				<tr>
				<td>
				  <s:label for="goodsSize" value="%{#session.resource.get('busforecast.column.goodssize')}" ></s:label>
				</td>
				<td>
    				<s:textfield name="busForecast.goodsSize" id="goodsSize"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="numeric"  title="%{#session.resource.get('public.inputformat.numeric')}"></s:textfield>
				</td>
				</tr>
				
				<tr>
				<td>
				<s:label for="teuNum" value="%{#session.resource.get('busforecast.column.teunum')}" ></s:label>
				</td>
				<td>
				   <s:textfield name="busForecast.teuNum" id="teuNum"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="integer"  title="%{#session.resource.get('public.inputformat.integer')}"></s:textfield>
				</td>
				</tr>
				
				<tr>
          		<td>
               	<s:label for="currencyId" value="%{#session.resource.get('busforecast.column.currencyid')}" ></s:label>
               	</td>
               	<td>
               	<s:select list="currencyList" listKey="id" listValue="cname"  name="busForecast.currencyId" id="currencyId"  cssClass="d-inputtext d-widthb" ></s:select>
               	</td>				
				<td>
					<s:label for="profit" value="%{#session.resource.get('busforecast.column.profit')}" ></s:label>
				</td>
				<td>
				   <s:textfield name="busForecast.profit" id="profit"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="numeric"  title="%{#session.resource.get('public.inputformat.numeric')}"></s:textfield>
				</td>
				</tr>				
				
		 	    <tr>
						<td>
							<s:label for="remark"  value="%{#session.resource.get('common.label.remark')}" ></s:label>
						</td>
						<td colspan="3">
						<s:textarea id="remark" name="busForecast.remark" cssStyle="width:700px;height:60px;"></s:textarea>
						</td>
					</tr>
					
			     <tr >
               		<td style="width:20%;">
               			<s:label for="userName"  value="%{#session.resource.get('customer.column.user')}" ></s:label>
               			</td>
               			<td style="width: 30%">
               			<s:textfield name="busForecast.user.name" id="userName" disabled="true" cssClass="d-inputtext d-widthb" ></s:textfield>
               			</td>
               			<td style="width:20%;">
               			<s:label for="orgId"  value="%{#session.resource.get('customer.column.org')}" ></s:label>
               			</td>
               			<td style="width: 30%">
                        <s:textfield name="busForecast.org.name" id="orgName" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>               			
               			</td>
                </tr>					
				<tr>
               		<td style="width:20%;">
               			<s:label for="createdBy"   value="%{#session.resource.get('customer.column.createdby')}" ></s:label>
               		</td>
               		<td style="width: 30%">
               			<s:textfield name="busForecast.createdByUser.name" id="createdBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               		<td style="width:20%;">
               			<s:label for="created"  value="%{#session.resource.get('customer.column.created')}" ></s:label>
               		</td>
               		<td style="width: 30%"><s:textfield  id="created" disabled="true"  name="busForecast.created" cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="busForecast.created" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               </tr>
               	<tr>
               		<td style="width:20%;">
               			<s:label for="updatedBy"   value="%{#session.resource.get('customer.column.updatedby')}" ></s:label>
               			</td>
               			<td style="width: 30%">
               			<s:textfield name="busForecast.updatedByUser.name" id="updatedBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               			</td>
               			<td style="width:20%;">
               			<s:label for="updated"  value="%{#session.resource.get('customer.column.updated')}" ></s:label>
               			</td>
               			<td style="width: 30%"><s:textfield  name="busForecast.updated" id="updated" disabled="true"  cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="busForecast.updated" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>   
               			
               			</td>
               </tr>

				

			<tr>
                <td colspan="4" class="td_page_right">
                    <c:if test="${MAP_KEY_OF_SESSION.from!='linkrecord'}">
                	<delmar:operatePriv operator="create" >
                		<s:submit method="create"   value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"></s:submit>
                	</delmar:operatePriv>
                	</c:if>
               		<delmar:operatePriv operator="create" idpk="${linkrecord.id }">
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="update" idpk="${linkrecord.id }">
						<s:submit method="save"   value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="delete" idpk="${linkrecord.id }">
						<s:submit method="delete"   value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
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
	 $("#tableinputtable").find("img").addClass("d-hidden");
	 $("#nextComments").removeAttr("readonly");
	 </c:if>
	 
	 

});  
  
   
  

  

</script>





</body>
</html>
