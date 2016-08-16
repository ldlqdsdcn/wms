<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><delmar:message key="print.title" /></title>
<style type="text/css">
body{
width:700px;
text-align:center;
margin:auto;
}
table{
text-align:center;
line-height:30px;
}
h2{
text-align:center;
}

.mnfCommandButton60 {
    background-image: url(<%=request.getContextPath() %>/backend/images/Buttons/CommandButton60.gif);
    background-repeat: no-repeat;
    position: relative;
    border-style: solid;
    border:0;
    background-position: center 50%;
    width: 60px;
    height: 22px;
    font-family: Arial, Geneva, Helvetica;
    font-size: 9pt;
    color: black;
    cursor: pointer;
    margin:3px;
    
}
.main td{ height:30px; }
</style>
<style type="text/css" media="print">
.noprint{display : none }
</style>
<style type="text/css">
<!--
.STYLE7 {
	font-size: 24px;
	font-weight: bold;
}
.STYLE8 {font-size: 16px}
.STYLE11 {font-size: 14}
-->
</style>

</head>

<body>
<p class="noprint">
<input id="btnPrint" type="button" value="<delmar:message key="common.button.print" />" onclick="javascript:window.print();" Class="mnfCommandButton60" style="border:0;" />
</p>
<br/>
<span class="STYLE7"><delmar:message key="print.bill" /></span> &nbsp;<span class="STYLE8">
</span>
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0></OBJECT>

<div style="text-align:left;line-height:30px;"><delmar:message key="print.warehouseNo" />： <s:property value="warehouseForwarder.warehouseNo"/></div>
<table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#000000" class="main">
  <tr>
    <td><span class="STYLE11"><delmar:message key="print.cargoCusName" /></span></td>
    <td width="32%"><span class="STYLE11"><s:property value="warehouseForwarder.cargoCusName"/></span></td>
    <td align="center"><span class="STYLE11"><delmar:message key="print.referenceNo" /></span></td>
    <td colspan="3"><span class="STYLE11"><s:property value="wfDetail.referenceNo"/></span></td>
  </tr>
  <tr>
    <td height="64"><span class="STYLE11"><delmar:message key="print.inDate" /></span></td>
    <td height="64"><span class="STYLE11"><s:property value='wfDetail.inDate'/></span></td>
    <td><span class="STYLE11"><delmar:message key="print.goodsDesc" /></span></td>
    <td colspan="3"><span class="STYLE11"><s:property value="wfDetail.goodsDesc"/></span></td>
  </tr>
  <tr>
    <td><span class="STYLE11"><delmar:message key="print.goodsNumber" /></span></td>
    <td><span class="STYLE11"><s:property value='wfDetail.goodsNumber'/></span></td>
    <td align="center"><span class="STYLE11"><delmar:message key="print.shippingSpace" /></span></td>
    <td width="19%"><span class="STYLE11"><s:property value='wfDetail.shippingSpace'/></span></td>
    <td width="9%"><span class="STYLE11"><delmar:message key="print.totalAMount" /></span></td>
    <td width="13%"><span class="STYLE11"><s:property value='wfDetail.totalAMount'/></span></td>
  </tr>
  <tr>
     <td><span class="STYLE11"><delmar:message key="print.goodsWeight" /></span></td>
    <td><span class="STYLE11"><s:property value='wfDetail.goodsWeight'/></span></td>
    <td align="center"><span class="STYLE11"><delmar:message key="print.chargeData" /></span></td>
    <td colspan="3"><span class="STYLE11"><s:property value='wfDetail.chargeData'/></span></td>
  </tr>
  <tr>
    <td height="38"><span class="STYLE11"><delmar:message key="print.goodsSize" /></span></td>
    <td><span class="STYLE11"><s:property value='wfDetail.goodsSize'/></span></td>
    <td><span class="STYLE11"><delmar:message key="print.sizeDescription" /></span><span class="STYLE11"></span></td>
    <td colspan="3"><s:property value='wfDetail.sizeDescription'/></td>
  </tr>
  <tr>
  	<td width="13%" height="77"><span class="STYLE11"><delmar:message key="print.maiTou" /></span></td>
    <td><span class="STYLE11"><s:property value="wfDetail.maiTou"/></span></td>
    
    <td align="center"><span class="STYLE11"><delmar:message key="print.cargoRemark" /></span><span class="STYLE11"></span></td>
    <td colspan="3"><s:property value="wfDetail.cargoRemark"/></td>
  </tr>
  <tr>
    <td height="32"><span class="STYLE11"><delmar:message key="print.carLicenseNo" /></span></td>
    <td><span class="STYLE11"><s:property value="wfDetail.carLicenseNo"/></span></td>
    <td rowspan="2" align="center"><span class="STYLE11"><delmar:message key="print.receiptPerson" /><br/><delmar:message key="print.sign" /></span><span class="STYLE11"></span></td>
    <td colspan="3" rowspan="2"><s:property value="wfDetail.receiptPerson"/></td>
  </tr>
  <tr>
    <td height="38"><span class="STYLE11"><delmar:message key="print.signature" /></span></td>
    <td><span class="STYLE11"></span></td>
  </tr>
</table>
</body>
</html>

