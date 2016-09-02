<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-09-02 10:18:25
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	Struts日志
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
	 window.location='<c:url value="/system/userFootmark_list.action"/>';
 }
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');
        $('#visiteDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        timepicker:false,
        format:"Y-m-d",
        step:10
        });
     $("#saveBtn").click(function(){
            if (!validateDate($("#visiteDate").val())) {
            alert("visiteDate必须为日期");
            $("#visiteDate").focus();
            return false;
            }


            if (isEmpty($("#visiteDate").val())) {
            alert("visiteDate不允许为空");
            $("#visiteDate").focus();
            return false;
            }


            if (!validateDate($("#visiteDate").val())) {
            alert("visiteDate必须为日期");
            $("#visiteDate").focus();
            return false;
            }





         return true;
     });
 });
</script>
</head>
<body>
<s:form id="editForm" action="userFootmark_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="userFootmark.id"></s:hidden>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	Struts日志</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">

         <div class="C_S_F_L">
			<c:if test="${!isFirst}">

				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"></s:submit>
			</c:if>


			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"></s:submit>

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
                        <tr>
                        <td width="20%"><s:label for="actionMethod" value="actionMethod" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.actionMethod" id="actionMethod"   ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.actionMethod"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="visiteDate" value="visiteDate" ></s:label></td>
                        <td width="30%">
                        
                        <s:textfield name="userFootmark.visiteDate" id="visiteDate" >
                         <s:param name="value"><s:date name="userFootmark.visiteDate"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        <span style="color:red">*</span>
                            <s:fielderror fieldName="userFootmark.visiteDate"   cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="actionName" value="actionName" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.actionName" id="actionName"   ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.actionName"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="actionPurpose" value="actionPurpose" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.actionPurpose" id="actionPurpose"   ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.actionPurpose"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="userId" value="userId" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${userFootmark.userId}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="orgId" value="组织" ></s:label></td>
                        <td width="30%">
                        
                           <delmar:user userId="${userFootmark.orgId}" module="org"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="remark" value="备注" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.remark" id="remark"  cssStyle="width:500px;" ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.remark"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="remoteAddr" value="remoteAddr" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.remoteAddr" id="remoteAddr"   ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.remoteAddr"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="remoteHost" value="remoteHost" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="userFootmark.remoteHost" id="remoteHost"   ></s:textfield>
                            
                            <s:fielderror fieldName="userFootmark.remoteHost"    cssStyle="color:red" />
                          </td>
                        </tr>

                      <tr>
                          <td colspan="4" class="td_page_right" style="text-align:right;height: 24px;">
                              <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
                              <s:submit id="saveBtn" method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" ></s:submit>
                              <c:if test="${userFootmark.id!=null}">
                                  <s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
                              </c:if>
                              <input onclick="gotoList()"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
                          </td>
                      </tr>
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
