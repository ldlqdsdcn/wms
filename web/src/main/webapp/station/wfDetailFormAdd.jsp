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
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>

<script type='text/javascript' src='../dwr/interface/customerDwr.js'></script>
<script type='text/javascript' src='../dwr/engine.js'></script>

<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>


<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<script type='text/javascript' src='../dwr/interface/cityDwr.js'></script>



<script type="text/javascript">

$(document).ready(function() {
	 $(document).D_Validate();
	 
	 $("#tableinputtable tr:even").addClass("query_two");
	 $("#tableinputtable tr:odd").addClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_one");
	 $("#tableinputtable tr:last").removeClass("query_two");	 	
});

//进仓明细打印
function printWhrecore(id){
	window.open("<%=request.getContextPath()%>/station/wfDetail_print.action?id="+id,"newwindow","height=500px,width=800px,top=100px,left=300%,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no") 
}


//业务编号设置
$(function(){
	
	var scanMsg = document.getElementById("scanMsg");
	if (scanMsg && scanMsg.value == "scanFailed") {
		alert('<delmar:message key="wfDetail.message.scanFailed" />');
	}
	
	var datetime = new Date(); 
   //datetime.setTime(time); 
   var year = datetime.getFullYear().toString(); 
   
   var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1; 
   var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate(); 
   var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours(); 
   var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes(); 
   var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds(); 
  
   $("#inDate").datepicker({changeMonth: true,changeYear: true,showOn: "button",
		dateFormat:"yy-mm-dd",
       buttonImage: "../images/DatePicker.GIF",
       buttonImageOnly: true,
       buttonText: "Select date",
       numberOfMonths:2           
      
	    });
   <s:if test="referenceNoList == null">
		if ($("#referenceNo").val() == "") {
			$("#referenceNo").val(year+ month+date+hour+minute+second);
		}
   </s:if>
   <s:else>
       // 可编辑的Select
		$('#referenceNo').editableSelect(  {  
	       bg_iframe: true, 
	       onSelect: function(list_item) {  
	       },  
	       case_sensitive: false, // If set to true, the user has to type in an exact  
	                              // match for the item to get highlighted  
	       items_then_scroll: 10 // If there are more than 10 items, display a scrollbar  
	     }  
	   );
   </s:else>
		
	var flag = document.getElementById("updateFlag").value;
	if(flag=="updateSuccsess"){
		alert('<delmar:message key="wfDetail.message.updateSuccsess" />');
	}else if(flag=="addSuccess"){
		alert('<delmar:message key="wfDetail.message.addSuccess" />');
	}else if (flag=="updateFailed"){
		alert('<delmar:message key="wfDetail.message.updateFailed" />');
	}else if (flag=="addFailed"){
		alert('<delmar:message key="wfDetail.message.addFailed" />');
	}
	var printFlag = document.getElementById("printFlag").value;
	if(printFlag=="1"){
		var id = '<s:property value='wfDetail.id'/>';
		printWhrecore(id)
	}
});

function whrecordsubmit(printFlag){
	
	var carDriver = document.getElementById("wfDetail.carDriver").value;
	var carLicenseNo = document.getElementById("wfDetail.carLicenseNo").value;
	var goodsNumber = document.getElementById("wfDetail.goodsNumber").value;
	var goodsWeight = document.getElementById("wfDetail.goodsWeight").value;
	var goodsSize = document.getElementById("wfDetail.goodsSize").value;
	var cargoRemark = document.getElementById("wfDetail.cargoRemark").value;
	var totalAMount = document.getElementById("wfDetail.totalAMount").value;
	
	// 卡车司机
    if(carDriver == ""){
    	alert('<delmar:message key="wfDetail.message.carDriverCanNotEmpty" />');
		return false;
    }
    
    // 车牌
    if(carLicenseNo == ""){
    	alert('<delmar:message key="wfDetail.message.carLicenseNoCanNotEmpty" />');
		return false;
    }
    
    // 件数
    if(goodsNumber == ""||goodsNumber==0){
    	alert('<delmar:message key="wfDetail.message.goodsNumberCanNotEmpty" />');
		return false;
    }
    
    // 重量
    if(goodsWeight == ""||goodsWeight==0){
    	alert('<delmar:message key="wfDetail.message.goodsWeightCanNotEmpty" />');
		return false;
    }
    
    // 体积/尺寸
    if(goodsSize == ""||goodsSize==0){
    	alert('<delmar:message key="wfDetail.message.goodsSizeCanNotEmpty" />');
		return false;
    }
    
    // 件数数字判断 
    if(!(/\d+(\.\d+)?/g.test(goodsNumber))) {
		alert('<delmar:message key="wfDetail.message.NumberEnterNumber" />');
		return false;
	}
	
	// 件数数字判断整数
    if(!(/^[-]?\d+$/.test(goodsNumber))) {
		alert('<delmar:message key="wfDetail.message.goodsNumber" />');
		return false;
	} 
	
	// 重量数字判断 
    if(!(/\d+(\.\d+)?/g.test(goodsWeight))) {
		alert('<delmar:message key="wfDetail.message.goodsWeight" />');
		return false;
	}
	
	// 尺寸数字判断 
    if(!(/\d+(\.\d+)?/g.test(goodsSize))) {
		alert('<delmar:message key="wfDetail.message.goodsSize" />');
		return false;
	}
    
    // 费用数字
    if(!(/\d+(\.\d+)?/g.test(totalAMount))) {
		alert('<delmar:message key="wfDetail.message.totalAMount" />');
		return false;
	}
    
	var wfDetailId = document.getElementById("wfDetail.id").value;
	document.getElementById("printFlag").value = printFlag;
    if (wfDetailId == '') {
    	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/station/wfDetail_addSave.action";
        document.forms["jcddetailform"].submit();
    } else {
    	document.forms["jcddetailform"].action = "<%=request.getContextPath()%>/station/wfDetail_save.action";
        document.forms["jcddetailform"].submit();
    }
	
    
    $("#btnsubmitNew").attr("disabled", true);
    $("#btnsubmitNewPrint").attr("disabled", true);
    $("#btnsubmitNew").val('<delmar:message key="wfDetail.message.submitting" />');
    $("#btnsubmitNewPrint").val('<delmar:message key="wfDetail.message.submitting" />');
    //$("#btnzhuce").removeAttr("disabled");//将按钮可用
}

//扫描进仓明细新增
function addNewJCRecord(){
	var warehouseNo = document.getElementById("warehouseNo").value;
	if (warehouseNo==""||warehouseNo==null){
	  alert('<delmar:message key="wfDetail.message.warehouseNo" />');
	  return;
	}
	document.forms["scanSearchForm"].action = "<%=request.getContextPath()%>/station/wfDetail_scan.action";
	document.forms["scanSearchForm"].submit();
	
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
<s:if test="scanSearch != null ">
	<div class="condition" style="margin-left:10px">
		<form name="scanSearchForm" id="" action="scanSearchForm" onkeydown="change(event);" method="post">
			<input id="scanSearch" name="scanSearch" value="<s:property value="scanSearch"/>" type="hidden">
			<input id="scanMsg" name="scanMsg" value="<s:property value="scanMsg"/>" type="hidden">
			
			<table border="0" class="bmsEditTable" width="100%" style="margin-top: 0px" cellpadding="3" cellspacing="1">
				<tr>
					<br />
					<td class="bmsInputCaption">
						<delmar:message key="warehouseForwarder.column.warehouseNo" />
					</td>
					<td class="bmsInputContent">
						<input id="warehouseNo" name="warehouseNo" value="<s:property value="warehouseNo"/>"/>
						<input type="button" name="button2" id="button2" value="<delmar:message key='common.button.select'/>"
							Class="input_submit" onclick="addNewJCRecord()" style="border:0;"/>
					</td>
					
				</tr>
				</table>
		</form>
	</div>
</s:if>
<s:if test="warehouseForwarder != null ">
<%--dateLockDate进仓单未超时：0--%>
<s:if test="warehouseForwarder.status != 1">
		<s:if test="''!=wfDetail.status">
		<%-- 初始化的时候不显示 --%>
		<div style="margin-left:8px;margin-top:5px" >
			<font style="color:red"><delmar:message key="wfDetail.message.statusLocked" /></font>
		</div>
		</s:if>
</s:if>
<%--dateLockDate进仓单未超时：0--%>
<s:if test="1==warehouseForwarder.status">
<s:form id="jcddetailform" name="jcddetailform" action="" namespace='/station' theme="simple">

<div style="margin:5px">
<table width="100%" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	<delmar:message key="warehouseForwarder.location" />
</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         </td>
        </tr>
      </table>
    	<table width="100%" border="0"  cellpadding="0" cellspacing="0"  class="table_blue" bgcolor="#FFFFFF"  >
        <tr align="center" valign="top"> 
          <td >
              <table width="100%" border="0" cellpadding="0" cellspacing="1" id="tableinputtable">
                <tr height="20px">
               		<td >
               			<delmar:message key="wfDetail.column.warehouseNo" />:
               			<a style="color:#0000ff"> 
               				<s:property value="warehouseForwarder.warehouseNo"></s:property>
               				<input id="warehouseForwarder.warehouseNo" name="warehouseForwarder.warehouseNo" value="<s:property value="warehouseForwarder.warehouseNo"/>" type="hidden"/>
               			</a><br>
               		</td>
               		<td >
               			&nbsp;
               		</td>
               </tr>
               <tr height="20px">
               		<td>
               			<delmar:message key="wfDetail.column.gdNo" /> : 
               		</td>
               		<td style="width: 30%">
               			<a style="color:#0000ff"> <s:property value="#request.wfDetail.gdNo"/> </a><br>
               		</td>
               </tr>
               <tr height="20px">
               		<td>
               			<delmar:message key="wfDetail.message.details" /> :
               			<!-- 新增时 -->
               			<c:if test="${wfDetail.id == null}">
               				<a style="color:#0000ff"> [<delmar:message key="wfDetail.message.adding" />...] </a> <br>
               			</c:if>
               			
               			<!-- 编辑时 -->
               			<c:if test="${wfDetail.id != null}">
	               			<a style="color:#0000ff"><s:property value="#request.wfDetail.id"/></a>
							 <s:if test="updateFlag != null">
							  	<span id="notedit" class="hide">
							 	<font color='#FF0000'>[<delmar:message key="wfDetail.message.updateing" />]</FONT></span>
							 </s:if>
							 <s:else>
							  	 <span id="notedit" class="hide">
								 <font color='#FF0000'>[<delmar:message key="wfDetail.message.dataLocked" />]</FONT></span>
							 </s:else>
							 <br>
               			</c:if>
               		</td>
               		<td >
               			&nbsp;
               		</td>
               </tr>
                <tr height="20px">
               		<td>
               			<delmar:message key="wfDetail.column.referenceNo" /> :
               			<!-- 新增时 -->
               			<c:if test="${wfDetail.id == null}">
               				<s:if test="referenceNoList == null">
						   		<input type="text" id="referenceNo" name="wfDetail.referenceNo"  class="base blue bigheight textstyle" style="width:30%;"  title="<delmar:message key="wfDetail.message.enterNumber" />"/>
					       </s:if>
					       <s:else>
					        	<s:select name="wfDetail.referenceNo" id="referenceNo" list="referenceNoList" 
					        		cssStyle="width:20%;height: 25px;font-size:14px;" onChange="editable(this);" />
					       </s:else>
               		   </c:if>
               			<!-- 编辑时 -->
               			<c:if test="${wfDetail.id != null}">
               				<input type="text" id="wfDetail.referenceNo" name="wfDetail.referenceNo"  class="base blue bigheight textstyle" style="width:50%" 
               				value="<s:property value="wfDetail.referenceNo"/>" title="<delmar:message key="wfDetail.message.enterNumber" />"/>
               				<input onclick="printWhrecore(<s:property value="wfDetail.id"/>)"  type="button" value="<delmar:message key="common.button.print" />"  class="input_submit" >
               			</c:if>
               			
               		</td>
               		<td style="width: 30%">
               			&nbsp;
               		</td>
               </tr>
				
               <tr  >
					<td colspan=4> 		
					<div id="linkman" style="margin:20px">
					   <div id="linkmanpanel" class="d-panel" style="height:400px">
					      <div id="panelheader" class="d-panel-header" style="width:30px"><delmar:message key="wfDetail.message.information" /></div>
					      <div id="panelcontent" style="padding:0px 0px 10px 10px">
					      	<table width="100%" border="0" cellspacing="0" >
					      		<tr>
					      			<td>
					      				<delmar:message key="wfDetail.column.inDate" />：<input name="wfDetail.inDate" id="inDate" value="<s:property value="wfDetail.inDate"/>"/>
					      			</td>
					      			<td>
					      				<table>
					      					<tr>
					      						<td>
					      							<delmar:message key="wfDetail.column.receiptPerson" />：
												    <input type="text" id="wfDetail.receiptPerson" name="wfDetail.receiptPerson"  class="base blue bigheight textstyle" 
												    	style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.receiptPerson"/>"><br/>
												   
					      						</td>
					      					</tr>
					      					<tr>
					      						<td>
					      							<delmar:message key="wfDetail.column.shippingSpace" />&nbsp;：
												    <input type="text" id="wfDetail.shippingSpace" name="wfDetail.shippingSpace"  class="base blue bigheight textstyle" 
												    	style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.shippingSpace"/>"><br/>
					      						</td>
					      					</tr>
					      				</table>
					      			</td>
					      			<td>
					      				<table>
					      					<tr>
					      						<td>
					      							<delmar:message key="wfDetail.column.carDriver" />：
											   		<input type="text" id="wfDetail.carDriver" name="wfDetail.carDriver"  class="base blue bigheight textstyle" 
											   			style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.carDriver"/>"><font color="#FC0107">*</font><br/>
					      						</td>
					      					</tr>
					      					<tr>
					      						<td>
					      							<delmar:message key="wfDetail.column.carLicenseNo" />：&nbsp;
											   		<input type="text" id="wfDetail.carLicenseNo" name="wfDetail.carLicenseNo"  class="base blue bigheight textstyle" 
											   		style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.carLicenseNo"/>"><font color="#FC0107">*</font>	
					      						</td>
					      					</tr>
					      				</table>
					      			</td>
					      		</tr>
					      	</table>
					      <table width="100%">
		      					<tr height="50px">
		      						<td width="20%">
		      							Pieces and Packing Method<br>
		      							<delmar:message key="wfDetail.column.numbersAndPackage" /><font color="#FC0107">*</font>
		      						</td >
		      						<td width="15%">
		      							Gross Wt.<br>
										<delmar:message key="wfDetail.column.goodsWeight" /><font color="#FC0107">*</font>
		      						</td>
		      						<td width="15%">
		      							Measurement.<br>
										<delmar:message key="wfDetail.column.goodsSize" /><font color="#FC0107">*</font>
		      						</td>
		      						<td width="25%">
		      							Nature of Goods.<br>
										<delmar:message key="wfDetail.column.goodsDesc" /> 
		      						</td>
		      						<td width="25%">
		      							Shipping marks.<br>
										<delmar:message key="wfDetail.column.maiTou" />
		      						</td>
		      					</tr>
		      					<tr>
		      						<td >
		      							<table width="100%">
			      							<tr>
				      							<td >
				      								<input type="text" id="wfDetail.goodsNumber" name="wfDetail.goodsNumber"  class="base blue bigheight textstyle" 
															style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.goodsNumber"/>">
				      							</td>
				      							<td align="left">
				      								<s:select  list="packageList" listKey="value" listValue="name" name="wfDetail.numberPackage" id="wfDetail.numberPackage" 
			 											style="height:24px;width:80px" headerValue="N/A" ></s:select>
				      							</td>
			      							</tr>
		      							</table>
		      						</td >
		      						<td>
		      							<input type="text" id="wfDetail.goodsWeight" name="wfDetail.goodsWeight"  class="base blue bigheight textstyle" 
															style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.goodsWeight"/>">
		      						</td>
		      						<td >
		      							<input type="text" id="wfDetail.goodsSize" name="wfDetail.goodsSize"  class="base blue bigheight textstyle" 
															style="width:60%;margin:5px 0px;" value="<s:property value="wfDetail.goodsSize"/>">
		      						</td>
		      						<td >
		      							<textarea id="wfDetail.goodsDesc" name="wfDetail.goodsDesc" style="width:200px;height:100px;" ><s:property value="wfDetail.goodsDesc"/></textarea>
		      						</td>
		      						<td >
		      							<textarea id="wfDetail.maiTou" name="wfDetail.maiTou" style="width:200px;height:100px;" ><s:property value="wfDetail.maiTou"/></textarea>
		      						</td>
		      						
		      					</tr>
		      				</table>
		      				<table width="100%">
		      					<tr height="50px">
		      						<td width="35%">
		      							<delmar:message key="wfDetail.column.sizeDescription" />:
		      						</td >
		      						<td width="40%">
		      							<delmar:message key="wfDetail.column.cargoRemark" />:
		      						</td>
		      						<td width="25%">
		      							<delmar:message key="wfDetail.column.totalAMount" />：<s:textfield name="wfDetail.totalAMount" id="wfDetail.totalAMount" value="0" class="base blue required bigheight numberdigit textstyle"
		      							onkeypress="if(!this.value.match(/^[\+\-]?\d*?\.?\d*?$/))this.value=this.t_value;else this.t_value=this.value;if(this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?)?$/))this.o_value=this.value" 
		      							onkeyup="if(!this.value.match(/^(?:[\+\-]?\d+(?:\.\d+)?|\.\d*?)?$/))this.value=this.o_value;else{if(this.value.match(/^\.\d+$/))this.value=0+this.value;if(this.value.match(/^\.$/))this.value=0;this.o_value=this.value}"></s:textfield><br>
		      							<delmar:message key="wfDetail.column.chargeData" />：
		      						</td>
		      					</tr>
		      					<tr>
		      						<td >
		      							<textarea id="wfDetail.sizeDescription" name="wfDetail.sizeDescription"  style="height:85px;width:80%;margin-top:5px;"><s:property value="wfDetail.sizeDescription"/></textarea>
		      						</td>
		      						<td>
		      							<textarea id="wfDetail.cargoRemark" name="wfDetail.cargoRemark" style="height:85px;width:80%;margin-top:5px;" ><s:property value="wfDetail.cargoRemark"/></textarea>
		      						</td>
		      						<td >
		      							<textarea id="wfDetail.chargeData" name="wfDetail.chargeData" style="height:85px;width:80%;margin-top:5px;" ><s:property value="wfDetail.chargeData"/></textarea>
		      						</td>
		      					</tr>
		      				</table>
					      </div>
					      
					   </div>
					</div>					
					
  				   </td>
				</tr>
				<tr align="center">
                	<td>
                		<c:if test="${wfDetail.id == null}">
                			<input type="button" name="btnsubmit" id="btnsubmitNew" value="<delmar:message key="wfDetail.button.add" />" 
                				Class="input_submit" style="border:0;" onclick="whrecordsubmit(0)" >
                				
               				<input type="button" name="btnsubmit" id="btnsubmitNewPrint" value="<delmar:message key="wfDetail.column.add&print" />" 
               					Class="input_submit" style="border:0;" onclick="whrecordsubmit(1)" />
               					
            				<input onclick="window.location='<c:url value="/station/warehouseForwarder_list.action"/>'"  type="button" 
            					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                		</c:if>
                		<c:if test="${wfDetail.id != null}">
                			<c:if test="${updateFlag != null}">
		                		<delmar:operatePriv operator="update" idpk="${wfDetail.id }">
									<input onclick="whrecordsubmit(0);"  type="button" value="<delmar:message key="wfDetail.button.update" />"  class="input_submit" >
								</delmar:operatePriv>
								<delmar:operatePriv operator="update" idpk="${wfDetail.id }">
									<input onclick="whrecordsubmit(1);"  type="button" value="<delmar:message key="wfDetail.button.updateAndPrint" />"  class="input_submit" >
								</delmar:operatePriv>
	                		</c:if>
                			<!-- 从进仓单列表过来的，返回到进仓单列表 -->
                			<c:if test="${updateFlag == 'wareHouseForwardUpdate'}">
                				<input onclick="window.location='<c:url value="/station/warehouseForwarder_list.action"/>'"  type="button" 
            					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                			</c:if>
                			<!-- 从进仓明细记录过来的，返回到进仓明细记录 -->
                			<c:if test="${updateFlag != 'wareHouseForwardUpdate'}">
                				<input onclick="window.location='<c:url value="/station/wfDetail_list.action"/>'"  type="button" 
            					value="<delmar:message key="common.button.back" />"  class="input_submit" >
                			</c:if>
                			
                		</c:if>
                	</td>
                </tr>	
                </table>
                </td>
                </tr>
                
                </table></td></tr></table>
</div>
<input id="wfDetail.id" name="wfDetail.id" type="hidden" value="<s:property value="wfDetail.id"/>"/>
<input name="wfDetail.masterID" id="wfDetail.masterID" type="hidden" value="<s:property value="wfDetail.masterID"/>"/>
<input name="wfDetail.trustFileCode" id="wfDetail.trustFileCode" type="hidden" value="<s:property value="wfDetail.trustFileCode"/>"/>
<input name="wfDetail.companyID" id="wfDetail.companyID" type="hidden" value="<s:property value="wfDetail.companyID"/>"/>
<input name="wfDetail.toBranch" id="wfDetail.toBranch" type="hidden" value="<s:property value="wfDetail.toBranch"/>"/>
<input name="wfDetail.status" id="wfDetail.status" type="hidden" value="<s:property value="wfDetail.status"/>"/>
<input name="updateFlag" id="updateFlag" type="hidden" value="<s:property value="updateFlag"/>"/>
<input name="printFlag" id="printFlag" type="hidden" value="<s:property value="printFlag"/>"/>

<input name="warehouseForwarder.operatorEMail" id="warehouseForwarder.operatorEMail" type="hidden" value="<s:property value="warehouseForwarder.operatorEMail"/>"/>
<input id="warehouseForwarder.status" name="warehouseForwarder.status" type="hidden" value="<s:property value="warehouseForwarder.status"/>"/>
<input id="warehouseForwarder.id" name="warehouseForwarder.id" type="hidden" value="<s:property value="warehouseForwarder.id"/>"/>
</s:form>
	</s:if>
</s:if>



<script language="javascript">
 
</script>







</body>
</html>
