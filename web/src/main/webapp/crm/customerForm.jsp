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

<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>

<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/interface/customerExtraPropDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>

<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>

<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>
<script type='text/javascript' src='../js/dm/uploadfile.js'></script>

<script type='text/javascript' src='../dwr/interface/cityDwr.js'></script>


<script type="text/javascript">
	
	function validateForm()
	{
		var cuscode=document.getElementById("cusCode");
		if(isEmpty(cuscode.value))
			{
			    showWarn('<delmar:warn errorKey="errors.required" key="customer.column.cuscode"/>');
				return false;
			}
		var name=document.getElementById("name");
		if(isEmpty(name.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="customer.column.name"/>');
			return false;
			}
		//customer.column.address
		
		var countryId=document.getElementById("countryId");
		if(isEmpty(countryId.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="customer.column.country"/>');
			return false;
			}
		
		
		var provinceId=document.getElementById("provinceId");
		if(isEmpty(provinceId.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="customer.column.province"/>');
			return false;
			}
		
		var cityId=document.getElementById("cityId");
		if(isEmpty(cityId.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="customer.column.city"/>');
			return false;
			}	
		
		
		var address=document.getElementById("address");
		if(isEmpty(address.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="customer.column.address"/>');
			return false;
			}
		
		
		var linkCode=document.getElementById("linkCode");
		if(isEmpty(linkCode.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="linkman.column.linkcode"/>');
			return false;
			}
		
		var linkmanname=document.getElementById("linkmanName");
		if(isEmpty(linkmanname.value))
			{
			showWarn('<delmar:warn errorKey="errors.required" key="linkman.column.name"/>');
			return false;
			}
		return true;
	}
	 $(document).ready(function() {		 
		 DWREngine.setAsync(false); 
		   $('#province').autocomplete(
								{
									source : function(request, response) {
										var availableTags = [];
										var value = $("#province").val();
										var countryId=$('#countryId').val();
										cityDwr.getCityProvinceList(value, countryId,function(data) {
				
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
										$("#provinceId").val(ui.item.cityId);
										$("#province").val(ui.item.name+(ui.item.remark==null ? "":" "+ui.item.remark));
										
										$("#cityId").val('');
										$("#city").val('');

									}
								});
		 
				$('#country').autocomplete(
								{
									source : function(request, response) {
										var availableTags = [];
										var value = $("#country").val();
										cityDwr.getCityCountryList(value, function(data) {
				
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
										$("#countryId").val(ui.item.cityId);
										$("#country").val(ui.item.name+(ui.item.remark==null ? "":" "+ui.item.remark));
										$("#provinceId").val('');
										$("#province").val('');
										
										$("#cityId").val('');
										$("#city").val('');
									}
								});

					$('#city').autocomplete(
							{
								source : function(request, response) {
									var availableTags = [];
									var value = $("#city").val();
									var countryId=$('#countryId').val();
									var provinceId=$("provinceId").val();
									cityDwr.getCityCityList(value,countryId,provinceId, function(data) {
			
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
									$("#cityId").val(ui.item.cityId);
									$("#city").val(ui.item.name+(ui.item.remark==null ? "":" "+ui.item.remark));

								}
							});
					
		                  $("input[type='text']").each(
		                          function(){
		                              $(this).keypress( function(e) {
		                                      var key = window.event ? e.keyCode : e.which;
		                                      if(key.toString() == "13"){
		                                                  return false;
		              
		                                     }
		                              });
		                          });
		             
	 
	 });
	 
	 
	 $(document).ready(function() {  
		 
		 

		$('#cusCode').blur(function(event)
				{
			      if ($("#id").val()!="")
			    	  return;
			      
	 			  var value = $('#cusCode').val();
				  if (value=="")
				  {
						$('#cuscodetip').addClass("d-hidden");
					    $('#cuscodetip').removeClass("d-visible");
						return;					    
     			  }
	
	
				  
				  customerDwr.getCustomer(value, function(data) {
							if(data!=null)
							{
							  $('#cusCode').focus();
							  $('#cuscodetip').removeClass("d-hidden");
							  $('#cuscodetip').addClass("d-visible");
							}
							else
							{
   							  $('#cuscodetip').addClass("d-hidden");
   							  $('#cuscodetip').removeClass("d-visible");
							}

						});
						

			  });
	 });     		
	   
</script>


<style type="text/css">
.fieldError ul {
display:inline-block;
position: relative;
}


</style>
</head>



<body>

<s:form id="editForm" action="customer_edit" namespace='/crm' theme="simple">
<s:hidden id="id" name="customer.id"></s:hidden>
<div style="margin:5px">
<table width="100%" border="0" cellspacing="0" cellpadding="5" class="d-formHeaderTable" >
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	<delmar:message key="customer.location"/>
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
      
      
      
    	<table width="100%" border="0"  cellpadding="0" cellspacing="0"  class="table_blue" bgcolor="#FFFFFF"  >
        <tr align="center" valign="top"> 
          <td >

                
                <table width="100%" border="0" cellpadding="0" cellspacing="1" id="tableinputtable">
               
                <tr>
               		<td class="d-label">
               			<s:label for="cuscode" value="%{#session.resource.get('customer.column.cuscode')}" ></s:label>
               		</td>
               		<td colspan="3" class="d-input">
               			<s:textfield name="customer.cusCode" id="cusCode" readonly="customer.id!=null" cssClass="required d-inputtext d-widthb" maxlength="10" ></s:textfield>
                        <span id="cuscodetip" class="d-hidden"><span class=" d-tiperror" ></span><span class="d-tipcontent d-error" id="cuscodedup"><delmar:message key="customer.cuscode.duplicate" cn="客户代码已经存在"/></span></span>               			
               		</td>
                 </tr>
                 <tr>
               		
               		<td  class="d-label">
               			<s:label for="opsCode" value="%{#session.resource.get('customer.column.opscode')}" ></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customer.opsCode" id="opsCode" cssClass="d-inputtext d-widthb"></s:textfield>
               			<s:hidden name="oldOpsCode" id="oldOpsCode"></s:hidden>
               			<span class="fieldError">
               			<s:fielderror  cssStyle="color:red;">
						    <s:param>opsCode</s:param>
						</s:fielderror>
						</span>
               		</td>
               		<td  class="d-label">
               			<s:label for="statusId" value="%{#session.resource.get('customer.column.statusid')}" ></s:label>
               		</td>
               		<td class="d-input">
               		  
                 		<s:select list="statusList" listKey="datadictId" listValue="name"  name="customer.statusId" id="statusId"  cssClass="d-inputtext d-widthb"></s:select>
                 		<s:hidden name="oldStatusId" id="oldStatusId"></s:hidden>
                 	</td>
               </tr>
                <tr>
                
         	    	<td class="d-label" >
               			<s:label for="name"  value="%{#session.resource.get('customer.column.name')}"></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customer.name" id="name"  cssClass="required d-inputtext d-widthb"></s:textfield>
               		</td>
               		
         	    	<td class="d-label" >
               			<s:label for="legalPerson"  value="%{#session.resource.get('customer.column.legalperson')}" ></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customer.legalPerson" id="legalPerson"  cssClass="d-inputtext d-widthb optional"  ></s:textfield>
               		</td>
               </tr>
               
                <tr >                
         	    	<td  class="d-label">
               			<s:label for="telephone"  value="%{#session.resource.get('customer.column.telephone')}" ></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customer.telephone" id="telephone"  cssClass="required d-inputtext d-widthb" blurvalidate="yes" blurtype="telephone"  title="%{#session.resource.get('public.inputformat.telephone')}" ></s:textfield>
               		</td>
               		
         	    	<td class="d-label">
               			<s:label for="email"  value="%{#session.resource.get('customer.column.email')}"></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customer.email" id="email"   cssClass="d-inputtext d-widthb optional" blurvalidate="yes" blurtype="email"  title="%{#session.resource.get('public.inputformat.email')}"></s:textfield>
               		</td>
               </tr>
               <tr >
                   <td class="d-label">	<s:label for="country"  value="%{#session.resource.get('customer.column.country')}" ></s:label></td>
                   <td class="d-input">
                   	<s:textfield name="customer.country.name" id="country"  cssClass="required d-inputtext d-widthb"></s:textfield>
                   	<s:hidden name="customer.countryId" id="countryId"></s:hidden>
                   </td>
                   <td rowspan=3  class="d-label"><delmar:message key="customer.column.address"/> </td>
					<td rowspan=3 class="d-input">
						<s:textarea name="customer.address" id="address" cols="30" rows="5"  > </s:textarea>
				   </td>                   
                 </tr>
                
                <tr >
                   
					<td class="d-label"><s:label for="province"  value="%{#session.resource.get('customer.column.province')}"  ></s:label></td>
					<td class="d-input">	<s:textfield name="customer.province.name" id="province"  cssClass="required d-inputtext d-widthb"></s:textfield>
						<s:hidden name="customer.provinceId" id="provinceId"></s:hidden>
					</td>
				</tr>
               <tr >					
					<td class="d-label"><s:label for="city"  value="%{#session.resource.get('customer.column.city')}"  ></s:label></td>
					<td class="d-input">	<s:textfield name="customer.city.name" id="city"  cssClass="required d-inputtext d-widthb"></s:textfield>
						<s:hidden name="customer.cityId" id="cityId"></s:hidden>
					</td>
               </tr>
               
                 <tr>
					<td class="d-label"> <delmar:message key="customerext.column.proplabel" />
					
					 </td>
					<td class="d-input">
					   <s:textarea name="customerExter.propLabel" id="propLabel"  cols="50" rows="5"> </s:textarea>
					   
					    <input type="button" name="buttonblue" class="d-btn-white" onclick="choosePropLabel()" value="<delmar:message key='customer.button.chooseproplabel'/>">
					   <br><span class="d-highlight-red"><delmar:message key="customerext.column.proplabel.title" /></span>
					</td>
					<td class="d-label"> <delmar:message key="customer.column.remark" /> </td>
					<td  class="d-input">
						<s:textarea name="customer.remark" id="remark"  cols="50" rows="6"> </s:textarea>
					</td>
				</tr>
                <tr>
                <td colspan=4 >
                <delmar:file tableName="customer"  tableId="${customer.id}"/>
                </td>
                
                </tr>					
               <tr >
					<td colspan=4> 		
					<s:hidden id="linkmanid" name="linkman.id"></s:hidden>			
					<div id="linkman" style="margin:20px;">
					   <div id="linkmanpanel" class="d-panel" style="height:80px">
					      <div id="panelheader" class="d-panel-header" style="width:30px"><delmar:message key="customer.column.majorlinkman" cn="主联系人记录"/></div>
					      <div id="panelcontent" style="padding:0px 0px 10px 10px">
						  <delmar:message key="linkman.column.linkcode" cn="代码"/>&nbsp;<s:textfield name="linkman.linkCode" id="linkCode"  cssClass="required d-inputtext d-widthb"></s:textfield>
					      <delmar:message key="linkman.column.name" cn="名称"/>&nbsp;<s:textfield name="linkman.name" id="linkmanName"  cssClass="required d-inputtext d-widthb"></s:textfield>
                          <delmar:message key="linkman.column.position" cn="职位"/>&nbsp;<s:textfield name="linkman.position" id="linkmanPosition"  cssClass="d-inputtext d-widthb"></s:textfield>
                          <br><br>					      
					      <delmar:message key="linkman.column.phoneno" cn="电话"/>&nbsp;<s:textfield name="linkman.phoneNo" id="linkmanPhoneNo"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="mobile"  title="%{#session.resource.get('public.inputformat.mobile')}" > </s:textfield>					      
					      <delmar:message key="linkman.column.email" cn="邮件"/>&nbsp;<s:textfield name="linkman.email" id="linkmanEmail"  cssClass="optional d-inputtext d-widthb" blurvalidate="yes" blurtype="email"  title="%{#session.resource.get('public.inputformat.email')}"></s:textfield>
					      
					      <c:if test="${linkman.firstLinkTime!=null}">
					      <br><br>
                			<s:label for="firstLinkTime"  value="%{#session.resource.get('linkman.column.firstlinktime')}"  ></s:label>
                			<s:date name="linkman.firstLinkTime" format="yyyy-MM-dd HH:mm:ss"/>
                			
                			<s:label for="nextLinkTime"  value="%{#session.resource.get('linkman.column.nextlinktime')}"  ></s:label>
                			<s:date name="linkman.nextLinkTime" format="yyyy-MM-dd HH:mm:ss"/>
                			<s:label for="lastLinkTime"  value="%{#session.resource.get('linkman.column.lastlinktime')}"  ></s:label>
                			<s:date name="linkman.lastLinkTime" format="yyyy-MM-dd HH:mm:ss"/>
                			</c:if>
					      
					      </div>					      
					   </div>
					</div>					
					
  				   </td>
				</tr>	
				
				<s:if test="customer.id>0">
				   
				   <s:hidden name="customerExter.id"></s:hidden>
				   <tr>
				   <td colspan=4  class="d-panel-split" >
					<div style="margin-left:10px;font-weight:bold" class="d-formExtendHeader"><delmar:message key="customer.column.extendinfo" cn="扩展信息"/></div>						
				   </td>
				   </tr>
				   
				   <tr>
				   <td colspan=4 style="padding-top:10px;padding-bottom:10px">
				     <table id="customerextend" width="80%" align="center"  border="0" cellpadding="0" cellspacing="0"  class="table_blue" >
                     <tr >
         	    	<td  class="d-label">
               			<s:label for="ename"  value="%{#session.resource.get('customerext.column.ename')}" ></s:label>
               		</td>
               		<td  class="d-input">
               			<s:textfield name="customerExter.ename" id="ename"  cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               						   
				      	<td class="d-label">
						<s:label for="source"  value="%{#session.resource.get('customerext.column.custype')}"></s:label>
						</td>
						<td class="d-input">
							<s:select list="custypeList" listKey="datadictId" listValue="name"  name="customerExter.cusTypeId" id="cusType"  cssClass="d-inputtext d-widthb"></s:select>
						</td>
				    
				    

				   </tr>
				   
				   <tr >
				   
				      	<td class="d-label">
						<s:label for="source"  value="%{#session.resource.get('customerext.column.source')}"  ></s:label>
						</td>
						<td class="d-input">
							<s:select list="cussourceList" listKey="datadictId" listValue="name"  name="customerExter.cusSourceId" id="cusSource"  cssClass="d-inputtext d-widthb"></s:select>
						</td>				   
				      	<td class="d-label">
						<s:label for="industry"  value="%{#session.resource.get('customerext.column.industry')}"  ></s:label>
						</td>
						<td class="d-input">
							<s:select list="industryList" listKey="datadictId" listValue="name"  name="customerExter.industryId" id="industry"  cssClass="d-inputtext d-widthb"></s:select>
						</td>				 

				   </tr>
				   
				   <tr >
				   
         	    	<td class="d-label" >
               			<s:label for="majorBusiness"  value="%{#session.resource.get('customerext.column.majorbusiness')}"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textarea name="customerExter.majorBusiness" id="majorBusiness"   cols="30" rows="5"></s:textarea>
               		</td>
               						   
         	    	<td class="d-label" >
               			<s:label for="busDirection"  value="%{#session.resource.get('customerext.column.busdirection')}"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textarea name="customerExter.busDirection" id="busDirection"    cols="30" rows="5"></s:textarea>
               		</td>
				    
				    

				   </tr>
				   
				   <tr >
				   
         	    	<td class="d-label" >
               			<s:label for="busVolume"  key="customerext.column.busvolume"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textfield name="customerExter.busVolume" id="busVolume"  cssClass="d-inputtext d-widthb d-inputnumber" blurvalidate="yes" blurtype="integer" title="%{#session.resource.get('public.input.numberonly')}"></s:textfield>
               		</td>
               						   
         	    	<td rowspan=2 class="d-label">
               			<s:label for="busVolumeDesc"  value="%{#session.resource.get('customerext.column.busvolumedesc')}"  ></s:label>
               		</td>
               		<td rowspan=2 class="d-input">

               			<s:textarea name="customerExter.busVolumeDesc" id="busVolumeDesc"  cssClass="d-inputtext"  cols="30" rows="5"></s:textarea>
               		</td>
				    
				    

				   </tr>
				   
				   <tr >
				   
         	    	<td  class="d-label">
               			<s:label for="volumeUnit"  value="%{#session.resource.get('customerext.column.volumeunit')}"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textfield name="customerExter.volumeUnit" id="volumeUnit"   cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               						   

				   </tr>
				   				   
				   						   
				    <tr >
				   
         	    	<td  class="d-label">
               			<s:label for="postCode"  value="%{#session.resource.get('customerext.column.postcode')}"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textfield name="customerExter.postCode" id="postCode"  cssClass="d-inputtext d-widthb" ></s:textfield>
               		</td>
               		
         	    	<td  class="d-label">
               			<s:label for="websiteAddress"  value="%{#session.resource.get('customerext.column.websiteaddress')}"  ></s:label>
               		</td>
               		<td class="d-input">

               			<s:textfield name="customerExter.websiteAddress" id="websiteAddress"  cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               		               		
               						   

				   </tr>
				   
			
				   
				    <tr >
				   
         	    	<td class="d-label" >
               			<s:label for="firstLinkTime"  value="%{#session.resource.get('customerext.column.firstlinktime')}"  ></s:label>
               		</td>
               		<td class="d-input">
               			<s:textfield  id="firstLinkTime" disabled="true"  cssClass="d-inputtext d-widthb" ><s:param name="value"><s:date name="customerExter.firstLinkTime" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               		
         	    	<td class="d-label" >
               			<s:label for="nextLinkTime"  value="%{#session.resource.get('customerext.column.nextlinktime')}"  ></s:label>
               		</td>
               		<td class="d-input">
               			<s:textfield  id="nextLinkTime" disabled="true"  cssClass="d-inputtext d-widthb" ><s:param name="value"><s:date name="customerExter.nextLinkTime" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               		               		
               						   

				   </tr>
				   
				    <tr >
				   
         	    	<td  class="d-label">
               			<s:label for="lastLinkTime"  value="%{#session.resource.get('customerext.column.lastlinktime')}"  ></s:label>
               		</td>
               		<td colspan=3 class="d-input">
               			<s:textfield  id="lastLinkTime" disabled="true"  cssClass="d-inputtext d-widthb" ><s:param name="value"><s:date name="customerExter.lastLinkTime" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
       		
               						   

				   </tr>	
				   
				    <tr >
				   
         	    	<td  class="d-label">
               			<s:label for="longtitude"  value="%{#session.resource.get('customerext.column.longtitude')}"  ></s:label>
               		</td>
               		<td class="d-input">
               			<s:textfield  id="longtitude" name="customerExter.longtitude"  cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               		
         	    	<td class="d-label" >
               			<s:label for="latitude"  value="%{#session.resource.get('customerext.column.latitude')}"  ></s:label>
               		</td>
               		<td class="d-input">
               		   <s:textfield  id="latitude" name="customerExter.latitude"  cssClass="d-inputtext d-widthb"></s:textfield>
               		   
               		   <a  href="#"  onclick="javascript:openMap()"><delmar:message key="customerext.operator.maplocation"/></a>|
               		</td>
               		               		
               						   


				   </tr>
				   				   
				   						     
				     </table>
				   </td>
				   </tr>
				   
				
				   						   
				   			

				   
				</s:if>			

			     <tr >
               		<td class="d-label">
               			<s:label for="userName"  value="%{#session.resource.get('customer.column.user')}"  ></s:label>
               			</td>
               			<td  class="d-input" >
               			<s:textfield name="customer.user.name" id="userName" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               			</td>
               			<td   class="d-label">
               			<s:label for="orgId"  value="%{#session.resource.get('customer.column.org')}"  ></s:label>
               			</td>
               			<td class="d-input" >
                        <s:textfield name="customer.org.name" id="orgName" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>               			
               			</td>
                </tr>
						
				<tr >
               		<td  class="d-label">
               			<s:label for="createdBy"   value="%{#session.resource.get('customer.column.createdby')}"  ></s:label>
               		</td>
               		<td class="d-input" >
               			<s:textfield name="customer.createdByUser.name" id="createdBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               		</td>
               		<td  class="d-label">
               			<s:label for="created"  value="%{#session.resource.get('customer.column.created')}"  ></s:label>
               		</td>
               		<td  class="d-input"><s:textfield  id="created" disabled="true"  cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="customer.created" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>
               		</td>
               </tr>
               	<tr >
               		<td  class="d-label">
               			<s:label for="updateBby"   value="%{#session.resource.get('customer.column.updatedby')}"  ></s:label>
               			</td>
               			<td  class="d-input">
               			<s:textfield name="customer.updatedByUser.name" id="updatedBy" disabled="true" cssClass="d-inputtext d-widthb"></s:textfield>
               			</td>
               			<td  class="d-label">
               			<s:label for="updated"  value="%{#session.resource.get('customer.column.updated')}"  ></s:label>
               			</td>
               			<td class="d-input" ><s:textfield  name="customer.updated" id="updated" disabled="true"  cssClass="d-inputtext d-widthb"><s:param name="value"><s:date name="customer.updated" format="yyyy-MM-dd HH:mm:ss"/></s:param></s:textfield>   
               			
               			</td>
               </tr>	

			
                <tr>
                <td colspan="4" class="td_page_right">
                	<delmar:operatePriv operator="create" >
                		<s:submit method="create"   value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit" ></s:submit>
                	</delmar:operatePriv>
               		<delmar:operatePriv operator="create" idpk="${customer.id }">
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="update" idpk="${customer.id }">
						<s:submit method="save"   value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"  onclick="return validateForm();"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="delete" idpk="${customer.id }">
						<s:submit method="delete"   value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
					</delmar:operatePriv>
					<delmar:operatePriv operator="create" path="/crm/linkman" idpk="${customer.id>0  ? 0 :1 }" >
						<input onclick="window.location='<c:url value="/crm/linkman_create.action"/>?from=customer&fromid=${customer.id}'"  type="button" value="<delmar:message key="customer.button.createlinkman"/>"  class="input_submit" >
					</delmar:operatePriv>					
					<delmar:operatePriv operator="create" path="/crm/linkrecord" idpk="${customer.id>0  ? 0 :1 }" >
						<input onclick="window.location='<c:url value="/crm/linkrecord_create.action"/>?from=customer&fromid=${customer.id}'"  type="button" value="<delmar:message key="customer.button.createlinkrecord"/>"  class="input_submit" >
					</delmar:operatePriv>					
					<delmar:operatePriv operator="view" >
						<input onclick="window.location='<c:url value="/crm/customer_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
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
</div>
</s:form>


<div id="gislocationcontainer" title="<delmar:message key='customer.dialog.getlocation'/>" >
  <jsp:include page="gislocationbaidu.jsp"/>
</div>

<div id="propLabelDiv">
</div>


<script type="text/javascript">
  //进行一些验证的绑定
var dialog;
var proplabeldialog;
  
dialog = $( "#gislocationcontainer" ).dialog({
    autoOpen: false,
    height: 600,
    width: 600,
    modal: true,
    buttons: {
      "<delmar:message key='common.button.enter'/>": getLocation,
      "<delmar:message key='common.button.close'/>": function() {
         dialog.dialog( "close" );
      }
    },
    close: function() {      
     
    }
  });
  
  
proplabeldialog = $( "#propLabelDiv" ).dialog({
    autoOpen: false,
    height: 300,
    width: 400,
    modal: true,
    buttons: {
      "<delmar:message key='common.button.enter'/>": getPropLabel,
      "<delmar:message key='common.button.close'/>": function() {
    	  proplabeldialog.dialog( "close" );
      }
    },
    close: function() {      
     
    }
  });
  
    
  
 function getLocation()
 {
	 $("#longtitude").val($("#pointlng").val());
	 $("#latitude").val($("#pointlat").val());
	 dialog.dialog( "close" );	 
 }
 
 function openMap()
 {
	 searchByLocation($("#longtitude").val(),$("#latitude").val(),$("#address").val());
	 dialog.dialog( "open" );		 
 }
  
 $(document).ready(function() {
	 
	 $(document).D_Validate();
	 
	 $("#tableinputtable tr:even").addClass("query_two");
	 $("#tableinputtable tr:odd").addClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_two");	 	
	 
	 var haveError=false;
	 $(".fieldError").each(function(i){
		 
		 if (trim($(this).text())!="")
		 {
		   haveError=true;
		 }
	 });
	 
	 
	 if (haveError==true)
	 {
		 showWarn("<delmar:message key='public.message.errorwarn'/>");
	 }

});  
 
 
 function choosePropLabel()
 {
	 customerExtraPropDwr.getCustomerExtraPropDefine($("#propLabel").val(),3,function(data)
	  {
  		$("#propLabelDiv").html(data);
	  });
	 
	 proplabeldialog.dialog("open" );	
	 
	 
 }
 
 function getPropLabel()
 {
	 var newPropLabel="";
	 $("#propLabels:checked").each(function(i) {
		 newPropLabel=newPropLabel+","+$(this).val(); 
	 });
	 
	 if (newPropLabel!="")
		$("#propLabel").val($("#propLabel").val()+newPropLabel);
	 
	
	 proplabeldialog.dialog( "close" );	
 }
 
 
 function deletePropLabel(id)
 {
	 customerExtraPropDwr.deleteCustomerExtraPropDefine($("#propLabel").val(),id,3,function(data)
	  {
		 $("#propLabelDiv").html(data);
	  });
 }
  
   
  

  

</script>







</body>
</html>
