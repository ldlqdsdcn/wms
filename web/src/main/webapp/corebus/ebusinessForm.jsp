<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="javaclass.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 
 
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
 
 
<script type="text/javascript">
jQuery.datepicker.setDefaults(jQuery.datepicker.regional['zh_CN']);
$(document).ready(function() {

	 
	 //DWREngine.setAsync(false); 



				
/* 		$('#customer').autocomplete(
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
				}); */

	             //,buttonImage: "../images/DatePicker.GIF"
	             
	              	jQuery('#flightdate').datepicker({changeMonth: true,changeYear: true,showOn: "button",
	                    buttonImage: "../images/DatePicker.GIF",
	                    buttonImageOnly: true,
	                    buttonText: "Select date"});
			  	jQuery('#eta').datepicker({changeMonth: true,changeYear: true,showOn: "button",
                    buttonImage: "../images/DatePicker.GIF",
                    buttonImageOnly: true,
                    buttonText: "Select date"});
	              	

});

</script>
</head>



<body>

<s:form id="editForm" action="ebusiness_edit" namespace='/corebus' theme="simple">
<s:hidden id="id" name="ebusiness.id"></s:hidden>
<s:hidden name="poaPort.id"></s:hidden>
<s:hidden name="polPort.id"></s:hidden>
<s:hidden name="podPort.id"></s:hidden>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="ebusiness.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"></s:submit>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"   cssClass="input_submit"></s:submit>
			
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
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="businessno" value="%{#session.resource.get('ebusiness.column.businessno')}"  ></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="ebusiness.businessno" id="businessno" disabled="true"></s:textfield>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><s:label for="transno" value="%{#session.resource.get('ebusiness.column.transcationno')}"  ></s:label></td>
							<td colspan="3">
							<s:textfield name="transaction.transno" id="transno" cssStyle="width:500px;" disabled="true"></s:textfield>
							</td>
							
				</tr>
						<tr  class="query_one">
							<td >
								<s:label for="shippercode"  value="%{#session.resource.get('ebusiness.column.shippercode')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="ebusiness.shippercode" id="shippercode" cssStyle="width:500px;" disabled="true"></s:textfield>
							</td>
								<td >
								<s:label for="shippername"  value="%{#session.resource.get('ebusiness.column.shippername')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="ebusiness.shippername" id="shippername" cssStyle="width:500px;" disabled="true"></s:textfield>
							</td>
						</tr>
					
										<tr  class="query_two">
							<td >
								<s:label for="consigneecode"  value="%{#session.resource.get('ebusiness.column.consigneecode')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="ebusiness.consigneecode" id="consigneecode" cssStyle="width:500px;" disabled="true"></s:textfield>
							</td>
								<td >
								<s:label for="consigneename"  value="%{#session.resource.get('ebusiness.column.consigneename')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="ebusiness.consigneename" id="consigneename" cssStyle="width:500px;"  disabled="true"></s:textfield>
							</td>
						</tr>		
						
					<tr  class="query_one">
							<td >
								<s:label for="pol"  value="%{#session.resource.get('ebusiness.column.pol')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="polPort.portcname" id="pol" cssStyle="width:500px;"  disabled="true"></s:textfield>
							</td>
								<td >
								<s:label for="poa"  value="%{#session.resource.get('ebusiness.column.poa')}"  ></s:label>
							</td>
							<td>
								<s:textfield name="poaPort.portcname" id="poa" cssStyle="width:500px;" disabled="true"></s:textfield>
							</td>
						</tr>							
					<tr class="query_two">
						<td>
						<s:label for="flight"  value="%{#session.resource.get('realtrans.column.flight')}"  ></s:label>
						</td>
						
						<td>
							<s:textfield name="realTrans.flight" id="flight" cssStyle="width:500px;"></s:textfield>
						</td>
						<td><s:label for="flightdate"  value="%{#session.resource.get('realtrans.column.flightDate')}"  ></s:label></td>
						<td >
						<s:textfield  name="realTrans.flightdate" id="flightdate" >
	               			<s:param name="value">
	               				<s:date name="realTrans.flightdate"  format="yyyy-MM-dd"/>
	               			</s:param>
               			</s:textfield>   
						</td>
					</tr>
                    <tr class="query_one">
                    	<td>
                    		<s:label for="reciplace"  value="%{#session.resource.get('realtrans.column.reciplace')}"  ></s:label>
                    	</td>
                    	<td>
                    		<s:textfield name="realTrans.reciplace" id="reciplace" ></s:textfield>
                    	</td>
                    	<td>
                    	<s:label for="destplace"  value="%{#session.resource.get('realtrans.column.destplace')}"  ></s:label>
                    	</td>
                    	<td>
                    		<s:textfield name="realTrans.destplace" id="destplace"></s:textfield>
                    	</td>
                    </tr>
                    <tr class="query_two">
                    	<td>
                    		<s:label for="carriername"  value="%{#session.resource.get('realtrans.column.carriername')}"  ></s:label>
                    	</td>
                    	<td>
                    	<s:textfield name="realTrans.carriername" id="carriername" cssStyle="width:500px;"></s:textfield>
                    	</td>
                    	<td>
                    			<s:label for="carriercontact"  value="%{#session.resource.get('realtrans.column.carrierContact')}"  ></s:label>
                    	</td>
                    	<td>
                    			<s:textfield name="realTrans.carriercontact" id="carriercontact" ></s:textfield>
                    	</td>
                    </tr>
                     <tr class="query_one">
                    	<td>
                    		<s:label for="goodsnumber"  value="%{#session.resource.get('realtrans.column.GoodsNumber')}"  ></s:label>
                    	</td>
                    	<td>
                    		<s:textfield name="realTrans.goodsnumber" id="goodsnumber" ></s:textfield>
                    	</td>
                    	<td>
                    	<s:label for="goodsweight"  value="%{#session.resource.get('realtrans.column.GoodsWeight')}"  ></s:label>
                    	</td>
                    	<td>
                    		<s:textfield name="realTrans.goodsweight" id="goodsweight"></s:textfield>
                    	</td>
                    </tr>
                       <tr class="query_two">
                    	<td>
                    		<s:label for="goodssize"  value="%{#session.resource.get('realtrans.column.GoodsSize')}"  ></s:label>
            		
                    	</td>
                    	<td>
                    		<s:textfield name="realTrans.goodssize" id="goodssize" ></s:textfield>
                    	</td>
                    	<td>
                    	<s:label for="eta"  value="%{#session.resource.get('realtrans.column.eta')}"  ></s:label>
                    	</td>
                    	<td>
	                    	<s:textfield name="realTrans.eta" id="eta">
			               			<s:param name="value">
			               				<s:date name="realTrans.flightdate"  format="yyyy-MM-dd"/>
			               			</s:param>
		               			</s:textfield>
                    	</td>
                    </tr>
                           <tr class="query_one">
                    	<td>
                    			
                    			<s:label for="remark"  value="%{#session.resource.get('realtrans.column.remark')}"  ></s:label>
                    			
                    	</td>
                    	<td colspan="3">
                    		<s:textfield name="realTrans.remark" id="remark"  cssStyle="width:500px"></s:textfield>
                    	</td>
                    	
                    </tr>
                    <tr>
                    <td></td><td></td>
                    <td></td><td></td>
                    </tr>
                    
                <tr>
                <td colspan="4" class="td_page_right">
                <c:if test="${ ebusiness.status==100}">
               			<s:submit method="generateRealTrans" key='ebusiness.button.generateRealTrans'   cssClass="input_submit_H"></s:submit>&nbsp;

				</c:if>
						<input onclick="window.location='<c:url value="/corebus/ebusiness_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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







</body>

</html>