<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-26 17:30:05
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>

<title>
	消息
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>

<script type="text/javascript">
 function gotoList()
 {
	 window.location='<c:url value="/core/message_list.action"/>';
 }
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
 });
</script>
</head>
<body>
<s:form id="editForm" action="message_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="message.id"/>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	消息</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">

         <div class="C_S_F_L">
			<c:if test="${!isFirst}">

				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"/>
			</c:if>


			<c:if test="${!isLast}">
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
                        <tr>
                        <td width="20%"><s:label for="value" value="value" /></td>
                        <td width="30%">
                          <s:textfield name="message.value" id="value"   />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="msgtext" value="msgtext" /></td>
                        <td width="30%">
                          <s:textfield name="message.msgtext" id="msgtext"   />
                          </td>
                        </tr>
                <tr>
                <td colspan="4" class="td_page_right">
                    <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"/>
						<c:if test="${message.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
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
