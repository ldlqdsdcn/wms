<%--
  Created by IntelliJ IDEA.
  User: ${user}
  Date: ${datetime}
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<#include "inc/marco.ftl"/>
<html>
<head>

<title>
	${title}
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8" />
    <link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
    <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script type="text/javascript" src="../js/jquery/plugin/jquery.datetimepicker.full.min.js"></script>
    <link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" >
    <link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" >
    <link rel="Stylesheet" href="../js/jquery/plugin/jquery.datetimepicker.min.css" type="text/css" >
    <link rel="stylesheet" href="../css/bootstrap/bootstrap.min.css" type="text/css" media="all"/>
    <link rel="stylesheet" href="../css/bootstrap/bootstrap-theme.min.css" type="text/css" media="all"/>
    <script type='text/javascript' src='../js/bootstrap/bootstrap.js'></script>
    <script type='text/javascript' src='../js/jquery/plugin/bootbox.min.js'></script>
<script type="text/javascript">
 function gotoList()
 {
	 window.location='<c:url value="${namespace}/${mode? uncap_first}_list.action"/>';
 }
 <#if lineList??>
     <#include "inc/lineTableScript.ftl"/>
 </#if>
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
    <#include "inc/datapicker.ftl"/>
     $("#saveBtn").click(function(){
     <#list propertyList as prop>
         <@validateProperty prop.prop prop/>
     </#list>


     <#if lineList??>
         var validateLine=true;
         var lineMsg="";
         <#include "inc/lineValidate.ftl"/>
        if(!validateLine)
        {
            bootbox.alert(lineMsg);
            return false;
        }
     </#if>

         return true;
     });
 });
</script>
</head>
<body>
<s:form id="editForm" action="${mode? uncap_first}_edit" namespace='${namespace}' theme="simple">
<s:hidden id="id" name="${mode? uncap_first}.id"/>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	${title}</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">

         <div class="C_S_F_L">
			<c:if test="${r'${!isFirst}'}">

				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"/>
			</c:if>


			<c:if test="${r'${!isLast}'}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"/>

			</c:if>
		</div></td>
        </tr>
      </table>
        <!--table 02 end-->
        <!--table 03 bgn-->
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top">
          <td>
          <!--table 04 bgn-->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr>
                <td align="center">
                    <!--table 05 bgn-->
                    <table width="100%" border="0" cellpadding="10" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>


                    <!--table 06 bgn-->
                <table width="100%" border="0" cellpadding="0" cellspacing="1" id="formDetail">
                  <#list propertyList as prop>
                        <tr>
                        <td width="20%"><s:label for="${prop.prop}" value="${prop.label}" /></td>
                        <td width="30%">
                        
                        <#if prop.foreign>
                           <delmar:user userId="${r'${'}${mode? uncap_first}.${prop.prop}}" module="${prop.module}"/>
                        <#elseif prop.date >
                        <s:textfield name="${mode? uncap_first}.${prop.prop}" id="${prop.prop}" <#if !prop.edit>readonly="true"</#if>>
                         <s:param name="value"><s:date name="${mode? uncap_first}.${prop.prop}"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        <#if prop.required><span style="color:red">*</span></#if>
                            <s:fielderror fieldName="${mode? uncap_first}.${prop.prop}"   cssStyle="color:red" />
                         <#elseif prop.booleanTag>
                          <s:radio id="${prop.prop}" name="${mode? uncap_first}.${prop.prop}" list="#${r'{'}'Y':'Y','N':'N'${r'}'}" required="true"/>
                            <#if prop.required><span style="color:red">*</span></#if>
                            <s:fielderror fieldName="${mode? uncap_first}.${prop.prop}"    cssStyle="color:red"/>

                          <#else>
                          <s:textfield name="${mode? uncap_first}.${prop.prop}" id="${prop.prop}" <#if !prop.edit>readonly="true"</#if> ${prop.cssStyle} />
                            <#if prop.required><span style="color:red">*</span></#if>
                            <s:fielderror fieldName="${mode? uncap_first}.${prop.prop}"    cssStyle="color:red" />
                          </#if>
                          </td>
                        </tr>
                 </#list>

                 <#if lineList??>
                     <#include "inc/lineTable.ftl"/>
                 </#if>
                      <tr>
                          <td colspan="4" class="td_page_right" style="text-align:right;height: 24px;">
                              <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
                              <s:submit id="saveBtn" method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" />
                              <c:if test="${r'${'}${mode? uncap_first}.id!=null${r'}'}">
                                  <s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
                              </c:if>
                              <input onclick="gotoList()"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
                          </td>
                      </tr>
                <#--<#if needI18n>-->
                  <#--<#include "/ftl/i18njsp.ftl">-->
                <#--</#if>-->
                </table>
                    <!--table 06 end-->
                </td>
                </tr>
                <tr>
                <td >
                <s:actionmessage cssStyle="color:red"/>

                </td>
                </tr>
                </table>
                <!--table 05 end-->
                </td></tr></table>
                <!--table 04 end-->
</td></tr></table>
    </td></tr></table>
</s:form>
</body>
</html>
