<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-09-03 23:33:53
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	标签
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
	 window.location='<c:url value="/core/label_list.action"/>';
 }
function addLabelTrl() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/label_addLabelTrl.action"/>';
editForm.submit();
}
function deleteLabelTrls() {
if(isEmptyCheckBox('LabelTrl_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/label_deleteLabelTrls.action"/>';
editForm.submit();
}
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');
        $('#compDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        timepicker:false,
        format:"Y-m-d",
        step:10
        });

        $('#bgnTime').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        format:"Y-m-d H:i:s"
        });
     $("#saveBtn").click(function(){
            if (isEmpty($("#value").val())) {
            alert("键值不允许为空");
            $("#value").focus();
            return false;
            }


            if (isEmpty($("#msgtext").val())) {
            alert("信息不允许为空");
            $("#msgtext").focus();
            return false;
            }


            if (!validateDate($("#compDate").val())) {
            alert("compDate必须为日期");
            $("#compDate").focus();
            return false;
            }


            if (!validateDate($("#compDate").val())) {
            alert("compDate必须为日期");
            $("#compDate").focus();
            return false;
            }


            if (!validateDateTime($("#bgnTime").val())) {
            alert("开始时间必须为日期时间");
            $("#bgnTime").focus();
            return false;
            }



            if (!validateDateTime($("#bgnTime").val())) {
            alert("开始时间必须为日期时间");
            $("#bgnTime").focus();
            return false;
            }





         var validateLine=true;
         var lineMsg="";
$("input[name^=labelTrlList]").each(function(i, item){

        if(endWith(item.name,'language')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"标签翻译 language不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }


        if(endWith(item.name,'msgtext')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"标签翻译 信息不允许为空";
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
<s:form id="editForm" action="label_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="label.id"/>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	标签</td>
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
                        
                          <s:textfield name="label.value" id="value"   />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="label.value"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="msgtext" value="信息" /></td>
                        <td width="30%">
                        
                          <s:textfield name="label.msgtext" id="msgtext"   />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="label.msgtext"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="compDate" value="compDate" /></td>
                        <td width="30%">
                        
                        <s:textfield name="label.compDate" id="compDate" >
                         <s:param name="value"><s:date name="label.compDate"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        
                            <s:fielderror fieldName="label.compDate"   cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="bgnTime" value="开始时间" /></td>
                        <td width="30%">
                        
                        <s:textfield name="label.bgnTime" id="bgnTime" >
                         <s:param name="value"><s:date name="label.bgnTime"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        
                            <s:fielderror fieldName="label.bgnTime"   cssStyle="color:red" />
                          </td>
                        </tr>

<tr>
    <td colspan="4" style="padding-left: 0;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            标签翻译
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
        <table id="LabelTrlTable" class="table">
            <thead>
            <th>
                <input type="checkbox" onclick="selectAll('LabelTrl_ids',this);"/>
            </th>
            <th>序号</th>
            <th >language</th>
            <th >信息</th>
            </thead>
            <tbody>
            <s:iterator value="labelTrlList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="LabelTrl_ids"  value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'labelTrlList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'labelTrlList['+#st.index+'].language'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'labelTrlList['+#st.index+'].msgtext'}">
                    </s:textfield> <span style="color:red">*</span>
                        <s:hidden name="%{'labelTrlList['+#st.index+'].labelId'}"/>
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
                              <c:if test="${label.id!=null}">
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
