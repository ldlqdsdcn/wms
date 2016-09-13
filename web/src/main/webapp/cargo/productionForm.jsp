<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-09-13 11:28:19
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	产品
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
	 window.location='<c:url value="/cargo/production_list.action"/>';
 }
function addProductionLine() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/production_addProductionLine.action"/>';
editForm.submit();
}
function deleteProductionLines() {
if(isEmptyCheckBox('ProductionLine_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/cargo/production_deleteProductionLines.action"/>';
editForm.submit();
}
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');
        $('#completeDate').datetimepicker({
        dayOfWeekStart : 1,
        lang:'en',
        timepicker:false,
        format:"Y-m-d",
        step:10
        });
     $("#saveBtn").click(function(){
            if (isEmpty($("#documentno").val())) {
            alert("documentno不允许为空");
            $("#documentno").focus();
            return false;
            }


            if (isEmpty($("#name").val())) {
            alert("名称不允许为空");
            $("#name").focus();
            return false;
            }


            if (!validateDate($("#completeDate").val())) {
            alert("completeDate必须为日期");
            $("#completeDate").focus();
            return false;
            }


            if (isEmpty($("#completeDate").val())) {
            alert("completeDate不允许为空");
            $("#completeDate").focus();
            return false;
            }


            if (!validateDate($("#completeDate").val())) {
            alert("completeDate必须为日期");
            $("#completeDate").focus();
            return false;
            }


            if (isEmpty($("#status").val())) {
            alert("status不允许为空");
            $("#status").focus();
            return false;
            }




         var validateLine=true;
         var lineMsg="";
$("input[name^=productionLineList]").each(function(i, item){

        if(endWith(item.name,'productName')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"产品明细 productName不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }

        if(endWith(item.name,'amount')){
                if (!isInt($(item).val())) {
                lineMsg+="<br>"+"产品明细 amount必须为整数";

                $(item).focus();
                validateLine=false;
                }


                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"产品明细 amount不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }


        if(endWith(item.name,'batch')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"产品明细 batch不允许为空";
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
<s:form id="editForm" action="production_edit" namespace='/cargo' theme="simple">
<s:hidden id="id" name="production.id"/>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	产品</td>
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
                        <td width="20%"><s:label for="documentno" value="documentno" /></td>
                        <td width="30%">
                        
                          <s:textfield name="production.documentno" id="documentno"   />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="production.documentno"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="name" value="名称" /></td>
                        <td width="30%">
                        
                          <s:textfield name="production.name" id="name"   />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="production.name"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="completeDate" value="completeDate" /></td>
                        <td width="30%">
                        
                        <s:textfield name="production.completeDate" id="completeDate" >
                         <s:param name="value"><s:date name="production.completeDate"  format="yyyy-MM-dd HH:mm:ss"/></s:param>
                        </s:textfield>
                        <span style="color:red">*</span>
                            <s:fielderror fieldName="production.completeDate"   cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="created" value="创建时间" /></td>
                        <td width="30%">
                        
                          <s:textfield name="production.created" id="created" readonly="true"  />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="production.created"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="createdby" value="创建人" /></td>
                        <td width="30%">
                        
                           <delmar:user userId="${production.createdby}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="updated" value="修改时间" /></td>
                        <td width="30%">
                        
                          <s:textfield name="production.updated" id="updated" readonly="true"  />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="production.updated"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="updatedby" value="修改人" /></td>
                        <td width="30%">
                        
                           <delmar:user userId="${production.updatedby}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="orgId" value="组织" /></td>
                        <td width="30%">
                        
                           <delmar:user userId="${production.orgId}" module="org"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="clientId" value="实体" /></td>
                        <td width="30%">
                        
                           <delmar:user userId="${production.clientId}" module="client"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="userId" value="userId" /></td>
                        <td width="30%">
                        
                           <delmar:user userId="${production.userId}" module="user"/>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="status" value="status" /></td>
                        <td width="30%">
                        
                          <s:textfield name="production.status" id="status"   />
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="production.status"    cssStyle="color:red" />
                          </td>
                        </tr>

<tr>
    <td colspan="4" style="padding-left: 0;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            产品明细
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
    <td colspan="2" style="padding-left: 20px;text-align: left;">
        <input value="添加产品明细"
        type="button" class="input_submit"
        onclick="javascript:addProductionLine()"/> &nbsp;&nbsp;
        <input class="input_submit" type="button"  value="删除选中产品明细"
        onclick="javascript:deleteProductionLines()"/>
    </td>
    <td colspan="2"></td>
</tr>
<tr>
    <td colspan="4">
        <table id="ProductionLineTable" class="table">
            <thead>
            <th>
                <input type="checkbox" onclick="selectAll('ProductionLine_ids',this);"/>
            </th>
            <th>序号</th>
            <th >productName</th>
            <th >amount</th>
            <th >batch</th>
            </thead>
            <tbody>
            <s:iterator value="productionLineList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="ProductionLine_ids"  value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'productionLineList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'productionLineList['+#st.index+'].productName'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'productionLineList['+#st.index+'].amount'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'productionLineList['+#st.index+'].batch'}">
                    </s:textfield> <span style="color:red">*</span>
                        <s:hidden name="%{'productionLineList['+#st.index+'].productionId'}"/>
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
                              <c:if test="${production.id!=null}">
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
