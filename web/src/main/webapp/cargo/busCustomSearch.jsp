<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta name="baidu-site-verification" content="sTW8Hf9QsUIz9tY5" />
		<link href="<%=request.getContextPath()%>/css/common/ls_panel_0.css"
			type="text/css" rel="stylesheet" />

		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common/jquery-1.8.3.js"></script>
<style type="text/css" media="print">
.print {
	display: block
}

.noprint {
	display: none
}

.nextpage {page-break-after:always;}
</style>
		<style type="text/css">
body {
	text-align: center;
	margin: auto;
}

.basetable {
	border: 1px solid #000000;
}

.basetd {
	border: 1px solid #000000;
}

.td_contant {
	text-align: left;
	padding: 5px;
	border: 1px solid #000000;
}

.tr_contant {
	height: 60px;
}
.showTitle{
display:inline-block;
font-weight: bold;
}
.subtabletd{border-bottom:#000000 solid 2px;padding:5px 5px 5px 5px}

.subTableTdDashed{border-bottom:#000000 dashed 1px;padding:5px 5px 5px 5px}
</style>
		<script type="text/javascript">
		var hkey_root,hkey_path,hkey_key;
        hkey_root="HKEY_CURRENT_USER";
        hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\" ;
		
			// 查询
			function doQuery(){
				var customsHblNoHid = document.getElementById("customsHblNoHid").value;
				var customsTradeNameHid = document.getElementById("customsTradeNameHid").value;
				var customsBillNoHid = document.getElementById("customsBillNoHid").value;
				
				if(customsTradeNameHid==null || customsTradeNameHid==""||customsTradeNameHid=='<fmt:message key="custom.message.inputTradeName" />'){
						
					alert('<fmt:message key="custom.message.TradeNameEmpty" />');
					return;
				}
				
				if((customsBillNoHid==null || customsBillNoHid=="" || customsBillNoHid=='<fmt:message key="custom.message.billNoEmpty" />') && 
						(customsHblNoHid==null || customsHblNoHid==""||customsHblNoHid=='<fmt:message key="custom.message.hblNoEmpty" />')){
					alert('<fmt:message key="custom.message.queryCon" />');
					return;
				}
				document.forms["customForm"].action = "<%=request.getContextPath()%>/cargo/customQuery_select.action";
				document.forms["customForm"].submit();
			}
			
			// 文章打印
			function printCustoms(id){
				
				var obj = document.getElementById("ptb" + id);
				var obj2 = document.getElementById("ptb2" + id);
				obj.setAttribute("class", "print"); 
				obj2.style.display = "";
				obj2.setAttribute("class", "print"); 
				window.print();
				obj.setAttribute("class", "noprint"); 
				obj2.style.display = "none";
				obj2.setAttribute("class", "noprint"); 
			}
			
    </script>
	</head>

	<body style="font-size: 12px">
		<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WebBrowser3" width="0" VIEWASTEXT>
</OBJECT>
		<div style="width: 1000px; margin: 0 auto">
			<div id="querybox" class="noprint">
				<br />
				<div class="pcontent">
					<div id="conditionbox" style="float: left">
						<s:form action="customQuery_select" id="customForm" name="customForm" namespace="/cargo" theme="simple">
							<fmt:message key="custom.column.trade_name" />：<s:textfield id="customsTradeNameHid" name="customsTradeNameHid" ></s:textfield> &nbsp;&nbsp;
							<fmt:message key="custom.column.hbl_no" />：<s:textfield id="customsHblNoHid" name="customsHblNoHid"></s:textfield> &nbsp;&nbsp;
							<fmt:message key="custom.column.bill_no" />：<s:textfield id="customsBillNoHid" name="customsBillNoHid"></s:textfield> &nbsp;&nbsp;
						</s:form>
					</div>
					<div id="buttonbox" style="float: left; width: 20px">
						<input type="button" name="btnquery" class="btn_white" value=" <fmt:message key="public.list.filter" /> " onclick="doQuery()"/>
					</div>
				</div>
			</div>
		</div>
		<br />
		<br />
		<div style="width: 1000px; margin: auto;">
			<div  class="noprint">
			<c:if test="${headListSize != null }">
				<c:if test="${headListSize == 0 }">
					<div style="font-size: 16px; float: left">
						<fmt:message key="custom.message.noData" />
					</div>
				</c:if>
				<c:if test="${headListSize != 0 }">
				
					<div style="font-size: 16px; float: left">
						<fmt:message key="custom.message.totalSelectCustomNum" />&nbsp;
						<s:property value='headListSize' />
						&nbsp;<fmt:message key="custom.message.fen" />
					</div>
				</c:if>
			</c:if>
			</div>
			<s:iterator value="headList" id="customHead" var="customHead" status="statu">
				<br />
				<input id="btnPrint0" type="button" class="noprint" value="<fmt:message key="common.button.print" />"
					onclick="javascript:printCustoms('<s:property value='#statu.count'/>');" />
				<br />
				<br />
				<div id="ptb<s:property value='#statu.count'/>" class="noprint" style="font-size: 16px;">
					<div style="font-weight: bold; font-size: 30px; text-decoration: underline">
						<fmt:message key="custom.message.chinaCustom" />
					</div>
					*
					<s:if test="beSettled">
						<s:property value="pre_entry_id" />
					</s:if>
					<s:else>
						<fmt:message key="custom.message.beSettled" />
					</s:else>
					*
					<br />
					<br />
					<div style="font-weight: bold; font-size: 30px; text-decoration: underline">
						<img src="<%=request.getContextPath()%>/common/Maskbarcode.bar?code=<s:property value="pre_entry_id" />"  
							mce_src="<%=request.getContextPath()%>/common/Maskbarcode.bar?code=<s:property value="pre_entry_id" />" 
							width="200px;" height="50px;"/> 
					</div>
					
					<br />

					<div style="word-break: keep-all; text-align: center; padding-bottom: 10px">
						<fmt:message key="custom.column.pre_entry_id" />：
						<span style="display: inline-block; font-weight: bold; font-size: 25px; padding-right: 50px">
							<s:if test="beSettled">
								<s:property value="pre_entry_id" />
							</s:if>
							<s:else>
								<fmt:message key="custom.message.beSettled" />
							</s:else>							
						</span>
						<span style="padding-right: 50px">Page: </span>
						<fmt:message key="custom.column.customNo" />：
						<s:if test="beSettled">
							<s:property value="pre_entry_id" />
						</s:if>
						<s:else>
							<fmt:message key="custom.message.beSettled" />
						</s:else>
					</div>
					
					<div style="padding-left: 30px">
						<table class="basetable" width="950px" cellpadding="0" cellspacing="0">
							<tr class="tr_contant">
								<td class="td_contant" width="250px">
									<p class="showTitle"><fmt:message key="custom.column.ExportPort" />:</p><s:property value="hbl_no" />
								</td>
								<td class="td_contant" width="100px" colspan="2">
									<p class="showTitle"><fmt:message key="custom.column.manual_no" />：</p>
									<s:property value="manual_no" />
								</td>
								<td class="td_contant" width="200px">
									<p class="showTitle"><fmt:message key="custom.column.i_e_date" />：</p>
									<s:property value="i_e_date" />
								</td>
								<td class="td_contant" width="200px">
									<p class="showTitle"><fmt:message key="custom.column.d_date" />：</p>
									<s:property value="d_date" />
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.trade_name" />:</p>
									<s:property value="trade_name" />
									<br />
									<s:property value="trade_co" />
								</td>
								<td class="td_contant" colspan="2">
									<p class="showTitle"><fmt:message key="custom.column.traf_mode_jie" />：</p>
									<s:property value="traf_mode_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.traf_name" />：</p>
									<s:property value="traf_name" />
									/
									<s:property value="voyage_no" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.bill_num" />：</p>
									<s:if test="beSettled">
										<s:property value="bill_no" />
									</s:if>
									<s:else >
										<fmt:message key="custom.message.beSettled" />
									</s:else>
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.owner_name" />:</p>
									<s:property value="owner_name" />
									<br />
									<s:property value="owner_code" />
								</td>
								<td class="td_contant" colspan="2">
									<p class="showTitle"><fmt:message key="custom.column.trade_mode_jie" />：</p>
									<s:property value="trade_mode_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.cut_mode_jie" />：</p>
									<s:property value="cut_mode_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.pay_way_jie" />：</p>
									<s:property value="pay_way_jie" />
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.lisence_no" />:</p>
									<s:property value="lisence_no" />
								</td>
								<td class="td_contant" colspan="2">
									<p class="showTitle"><fmt:message key="custom.column.trade_country_jie" />：</p>
									<s:property value="trade_country_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.distinate_port_jie" />：</p>
									<s:property value="distinate_port_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.district_code_jie" />：</p>
									<s:property value="district_code_jie" />
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.appr_no" />:</p>
									<s:property value="appr_no" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.trans_mode_jie" />：</p>
									<s:property value="trans_mode_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.fee_mark" />：</p>
									<s:property value="fee_mark" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.insur_mark" />：</p>
									<s:property value="insur_mark" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.other_mark" />：</p>
									<s:property value="other_mark" />
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.contr_no" />:</p>
									<s:property value="contr_no" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.pack_no" />：</p>
									<s:property value="pack_no" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.wrap_type_jie" />：</p>
									<s:property value="wrap_type_jie" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.gross_wt" />：</p>
									<s:property value="gross_wt" />
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.net_wt" />：</p>
									<s:property value="net_wt" />
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.container_no" />:</p>
									<s:property value="container_no" />
								</td>
								<td class="td_contant" colspan="3">
									<p class="showTitle"><fmt:message key="custom.column.bill" />：</p>
								</td>
								<td class="td_contant">
									<p class="showTitle"><fmt:message key="custom.column.Manufacturer" />：</p>
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant" colspan="5">
									<p class="showTitle"><fmt:message key="custom.column.maitou" />:</p>
								</td>
							</tr>
							<tr class="tr_contant">
								<td class="td_contant" colspan="5">
									<table width="900px" align="center" cellpadding="0" cellspacing="0">
										<tr >
											<td class="subtabletd" style="width: 35px">
												<p class="showTitle" ><fmt:message key="custom.list.index" /></p>
											</td>
											<td class="subtabletd" style="width: 80px">
												<p class="showTitle" ><fmt:message key="custom.list.code_t" /></p>
											</td>
											<td class="subtabletd" style="width: 200px">
												<p class="showTitle" ><fmt:message key="custom.list.g_name" /></p>
											</td>
											<td class="subtabletd" style="width: 100px">
												<p class="showTitle"><fmt:message key="custom.list.qty_1" /></p>
											</td>
											<td class="subtabletd">
												<p class="showTitle" style="width: 110px"><fmt:message key="custom.list.origin_country_jie" /></p>
											</td>
											<td class="subtabletd">
												<p class="showTitle"  style="width: 60px"><fmt:message key="custom.list.decl_price" /></p>
											</td>
											<td class="subtabletd">
												<p class="showTitle" style="width: 60px"><fmt:message key="custom.list.trade_total" /></p>
											</td>
											<td class="subtabletd">
												<p class="showTitle" style="width: 60px"><fmt:message key="custom.list.trade_curr_jie" /></p>
											</td>
											<td class="subtabletd">
												<p class="showTitle"><fmt:message key="custom.list.duty_mode_jie" /></p>
											</td>
										</tr>
										<s:iterator value="#customHead.customList" id="formDetail" var="formDetail" status="statusCode">
											<tr style="padding-top:5px;">
												<td class="subTableTdDashed">
													<s:property value="#statusCode.index+1" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="code_t" />
												</td>
												<td class="subTableTdDashed" style="width: 200px">
													<s:property value="g_name" />
													<br />
													<s:property value="g_model" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="qty_1" />
													<s:property value="g_unit_jie" />
													<s:if test="qty_2 != null">
														<br />
														<s:property value="qty_2" />
														<s:property value="unit_2_jie" />
													</s:if>
												</td>
												<td class="subTableTdDashed">
													<s:property value="origin_country_jie" />
													<br />
													<s:property value="origin_country" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="decl_price" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="trade_total" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="trade_curr_jie" />
												</td>
												<td class="subTableTdDashed">
													<s:property value="duty_mode_jie" />
												</td>
											</tr>

										</s:iterator>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div  class="nextpage"></div>
					<div style="font-size: 16px;display: none" id="ptb2<s:property value='#statu.count'/>" class="noprint">
						<div style="font-weight: bold; font-size: 30px; text-align:left;padding-left: 30px;" >
							<table  width="100%">
								<tr>
									<td style="font-weight: bold; font-size: 30px; " >
										<fmt:message key="custom.title.head" />
									</td>
									<td style="text-align:right;padding-right: 30px">
										<img src="<%=request.getContextPath()%>/common/Maskbarcode.bar?code=<s:property value="pre_entry_id" />"  
											mce_src="<%=request.getContextPath()%>/common/Maskbarcode.bar?code=<s:property value="pre_entry_id" />" 
											width="200px;" height="50px;"/> 
									</td>
								</tr>
							</table>
							
						</div>
						<br />
						<br />

						<div style="word-break: keep-all; text-align: left; padding-left: 30px">
							<fmt:message key="custom.column.declareUnit" />：<s:property value="agent_name" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<fmt:message key="custom.column.customNo" />：
							<s:if test="beSettled">
								<s:property value="pre_entry_id" />
							</s:if>
							<s:else>
								<fmt:message key="custom.message.beSettled" />
							</s:else>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Page:
						</div>
					
						<div style="padding-left: 30px">
							<table class="basetable" width="950px" cellpadding="0" cellspacing="0">
								<tr class="tr_contant">
									<td class="td_contant" width="250px">
										<p class="showTitle"><fmt:message key="custom.column.ExportPort" />:</p><s:property value="hbl_no" />
									</td>
									<td class="td_contant" width="100px" colspan="2">
										<p class="showTitle"><fmt:message key="custom.column.manual_no" />：</p>
										<s:property value="manual_no" />
									</td>
									<td class="td_contant" width="200px">
										<p class="showTitle"><fmt:message key="custom.column.i_e_date" />：</p>
										<s:property value="i_e_date" />
									</td>
									<td class="td_contant" width="200px">
										<p class="showTitle"><fmt:message key="custom.column.d_date" />：</p>
										<s:property value="d_date" />
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.trade_name" />:</p>
										<s:property value="trade_name" />
										<br />
										<s:property value="trade_co" />
									</td>
									<td class="td_contant" colspan="2">
										<p class="showTitle"><fmt:message key="custom.column.traf_mode_jie" />：</p>
										<s:property value="traf_mode_jie" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.traf_name" />：</p>
										<s:property value="traf_name" />
										/
										<s:property value="voyage_no" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.bill_num" />：</p>
										<s:if test="beSettled">
											<s:property value="bill_no" />
										</s:if>
										<s:else >
											<fmt:message key="custom.message.beSettled" />
										</s:else>
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.owner_name" />:</p>
										<s:property value="owner_name" />
										<br />
										<s:property value="owner_code" />
									</td>
									<td class="td_contant" colspan="2">
										<p class="showTitle"><fmt:message key="custom.column.trade_mode_jie" />：</p>
										<s:property value="trade_mode_jie" />
										&nbsp;&nbsp;&nbsp;
										<s:property value="trade_mode" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.cut_mode_jie" />：</p>
										<s:property value="cut_mode_jie" />
										&nbsp;&nbsp;&nbsp;
										<s:property value="mode_jie" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.pay_way_jie" />：</p>
										<s:property value="pay_way_jie" />
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.lisence_no" />:</p>
										<s:property value="lisence_no" />
									</td>
									<td class="td_contant" colspan="2">
										<p class="showTitle"><fmt:message key="custom.column.trade_country_jie" />：</p>
										<s:property value="trade_country_jie" />
										&nbsp;&nbsp;&nbsp;
										<s:property value="trade_country" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.distinate_port_jie" />：</p>
										<s:property value="distinate_port_jie" />
										&nbsp;&nbsp;&nbsp;
										<s:property value="distinate_port" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.district_code_jie" />：</p>
										<s:property value="district_code_jie" />
										&nbsp;&nbsp;&nbsp;
										<s:property value="district_code" />
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.appr_no" />:</p>
										<s:property value="appr_no" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.trans_mode_jie" />：</p>
										<s:property value="trans_mode_jie" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.fee_mark" />：</p>
										<s:property value="fee_mark" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.insur_mark" />：</p>
										<s:property value="insur_mark" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.other_mark" />：</p>
										<s:property value="other_mark" />
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.contr_no" />:</p>
										<s:property value="contr_no" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.pack_no" />：</p>
										<s:property value="pack_no" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.wrap_type_jie" />：</p>
										<s:property value="wrap_type_jie" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.gwkg" />：</p>
										<s:property value="gross_wt" />
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.nwkg" />：</p>
										<s:property value="net_wt" />
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.container_no" />:</p>
										<s:property value="container_no" />
									</td>
									<td class="td_contant" colspan="3">
										<p class="showTitle"><fmt:message key="custom.column.bill" />：</p>
									</td>
									<td class="td_contant">
										<p class="showTitle"><fmt:message key="custom.column.Manufacturer" />：</p>
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant" colspan="5">
										<p class="showTitle"><fmt:message key="custom.column.maitou" />:</p>
									</td>
								</tr>
								<tr class="tr_contant">
									<td class="td_contant" colspan="5">
										<table width="900px" align="center" cellpadding="0" cellspacing="0">
											<tr >
												<td class="subtabletd" style="width: 35px">
													<p class="showTitle" ><fmt:message key="custom.list.index" /></p>
												</td>
												<td class="subtabletd" style="width: 150px">
													<p class="showTitle" ><fmt:message key="custom.column.productName" /></p>
												</td>
												<td class="subtabletd" style="width: 150px">
													<p class="showTitle"><fmt:message key="custom.list.qty_1" /></p>
												</td>
												<td class="subtabletd">
													<p class="showTitle" style="width: 150px"><fmt:message key="custom.column.finalCountry" /></p>
												</td>
												<td class="subtabletd">
													<p class="showTitle"  style="width: 60px"><fmt:message key="custom.list.decl_price" /></p>
												</td>
												<td class="subtabletd">
													<p class="showTitle" style="width: 60px"><fmt:message key="custom.list.trade_total" /></p>
												</td>
												<td class="subtabletd">
													<p class="showTitle" style="width: 60px"><fmt:message key="custom.list.trade_curr_jie" /></p>
												</td>
												
											</tr>
											<s:iterator value="#customHead.customList" id="formDetail" var="formDetail" status="statusCode">
												<tr style="padding-top:5px;">
													<td class="subTableTdDashed">
														<s:property value="#statusCode.index+1" />
													</td>
													
													<td class="subTableTdDashed" style="width: 200px">
														<s:property value="g_name" />
													</td>
													<td class="subTableTdDashed">
														<s:property value="qty_1" />
														<s:property value="g_unit_jie" />
														<s:if test="qty_2 != null">
															<br />
															<s:property value="qty_2" />
															<s:property value="unit_2_jie" />
														</s:if>
													</td>
													<td class="subTableTdDashed">
														<s:property value="origin_country_jie" />
														<br />
														<s:property value="origin_country" />
													</td>
													<td class="subTableTdDashed">
														<s:property value="decl_price" />
													</td>
													<td class="subTableTdDashed">
														<s:property value="trade_total" />
													</td>
													<td class="subTableTdDashed">
														<s:property value="trade_curr_jie" />
													</td>
													
												</tr>
	
											</s:iterator>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<br/>
						<div style="font-weight: bold; font-size: 30px; text-align:left;padding-left: 30px" >
							<table  width="100%" >
								<tr>
									<td colspan="2">
										<fmt:message key="custom.message.aboveAndDivision" />
										<br />
										<fmt:message key="custom.message.ifInconsistent" />
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<fmt:message key="custom.message.businessUnitOrReprotingUnitSeal" />
									</td>
									<td >
										<fmt:message key="custom.message.customsSeal" />
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="custom.column.year" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="custom.column.month" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="custom.column.day" />
									</td>
									<td>
										<fmt:message key="custom.column.year" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="custom.column.month" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="custom.column.day" />
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
									<td >
										<img src="<%=request.getContextPath()%>/cargo/img/delmarSeal.jpg" width="150" height="80">
									</td>
								</tr>
							</table>
							
						</div>
				</div>
			</s:iterator>

		</div>
				</div>
				
		<br />
		<br />
		<br />
	</body>
</html>