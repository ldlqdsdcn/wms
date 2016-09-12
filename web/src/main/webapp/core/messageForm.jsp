<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-09-12 10:38:52
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	消息
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
	 window.location='<c:url value="/core/message_list.action"/>';
 }
function addMessageTrl() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/message_addMessageTrl.action"/>';
editForm.submit();
}
function deleteMessageTrls() {
if(isEmptyCheckBox('MessageTrl_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/message_deleteMessageTrls.action"/>';
editForm.submit();
}
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');
     $("#saveBtn").click(function(){


         var validateLine=true;
         var lineMsg="";
$("input[name^=messageTrlList]").each(function(i, item){


        if(endWith(item.name,'language')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"消息翻译 language不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }



});
        if(!validateLine)
        {
            bootbox.alert(lineMsg);
            return false;
        }

         return true;
     });
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
                        <td width="20%"><s:label for="value" value="键值" /></td>
                        <td width="30%">
                        
                          <s:textfield name="message.value" id="value"   />
                            
                            <s:fielderror fieldName="message.value"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="msgtext" value="信息" /></td>
                        <td width="30%">
                        
                          <s:textfield name="message.msgtext" id="msgtext"   />
                            
                            <s:fielderror fieldName="message.msgtext"    cssStyle="color:red" />
                          </td>
                        </tr>

<tr>
    <td colspan="4" style="padding-left: 0;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            消息翻译
                        </td>
                <td background="../images/table_page_bg.gif" width="*"  style="height: 26px;"><div class="C_S_F_L">
                    &nbsp;
                </div></td>
            </tr>
        </table>
        <!-- table 页 end -->
    </td>

</tr>
<tr>
    <td colspan="4">
        <table id="MessageTrlTable" class="table">
            <thead>
            <th>序号</th>
            <th >language</th>
            <th >信息</th>
            </thead>
            <tbody>
            <s:iterator value="messageTrlList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'messageTrlList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'messageTrlList['+#st.index+'].language'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'messageTrlList['+#st.index+'].msgtext'}">
                    </s:textfield> 
                        <s:hidden name="%{'messageTrlList['+#st.index+'].messageId'}"/>
                </td>


                </tr>

            </s:iterator>

            </tbody>
        </table>
    </td>
</tr>
                      <tr>
                          <td colspan="4" class="td_page_right" style="text-align:right;height: 24px;">
                              <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
                              <s:submit id="saveBtn" method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" />
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
