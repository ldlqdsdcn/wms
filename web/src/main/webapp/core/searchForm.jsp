<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-29 16:03:22
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>

<title>
	查询模块
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
	 window.location='<c:url value="/core/search_list.action"/>';
 }
function addSearchColumn() {
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/search_addSearchColumn.action"/>';
editForm.submit();
}
function deleteSearchColumns() {
if(isEmptyCheckBox('SearchColumn_ids'))
{
alert('请先选择再删除');
return;
}
var editForm = document.getElementById('editForm');
editForm.action = '<c:url value="/core/search_deleteSearchColumns.action"/>';
editForm.submit();
}
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
        $.datetimepicker.setLocale('en');
     $("#saveBtn").click(function(){
            if (isEmpty($("#name").val())) {
            alert("名称不允许为空");
            $("#name").focus();
            return false;
            }


            if (isEmpty($("#pageUrl").val())) {
            alert("pageUrl不允许为空");
            $("#pageUrl").focus();
            return false;
            }




         var validateLine=true;
         var lineMsg="";
$("input[name^=searchColumnList]").each(function(i, item){


        if(endWith(item.name,'columnName')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"查询字段 columnName不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }

        if(endWith(item.name,'dataType')){
                if (!isInt($(item).val())) {
                lineMsg+="<br>"+"查询字段 dataType必须为整数";

                $(item).focus();
                validateLine=false;
                }


                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"查询字段 dataType不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }

        if(endWith(item.name,'showType')){
                if (!isInt($(item).val())) {
                lineMsg+="<br>"+"查询字段 showType必须为整数";

                $(item).focus();
                validateLine=false;
                }


                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"查询字段 showType不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }

        if(endWith(item.name,'relOper')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"查询字段 relOper不允许为空";
                $(item).focus();
                validateLine=false;
                }


        }











        if(endWith(item.name,'columnLabel')){
                if (isEmpty($(item).val())) {
                lineMsg+="<br>"+"查询字段 columnLabel不允许为空";
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
<s:form id="editForm" action="search_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="search.id"></s:hidden>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	查询模块</td>
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
                        <td width="20%"><s:label for="name" value="名称" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="search.name" id="name"   ></s:textfield>
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="search.name"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="remark" value="备注" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="search.remark" id="remark"  cssStyle="width:500px;" ></s:textfield>
                            
                            <s:fielderror fieldName="search.remark"    cssStyle="color:red" />
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="pageUrl" value="pageUrl" ></s:label></td>
                        <td width="30%">
                        
                          <s:textfield name="search.pageUrl" id="pageUrl"   ></s:textfield>
                            <span style="color:red">*</span>
                            <s:fielderror fieldName="search.pageUrl"    cssStyle="color:red" />
                          </td>
                        </tr>

<tr>
    <td colspan="4" style="padding-left: 0px;">
        <!-- table 页 bgn -->
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                        <td  style="height: 26px;width: 90px;" align="center" background="../images/table_page_1.gif">
                            查询字段
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
        <input value="添加查询字段"
        type="button" class="input_submit"
        onclick="javascript:addSearchColumn()"/> &nbsp;&nbsp;
        <input class="input_submit" type="button"  value="删除选中查询字段"
        onclick="javascript:deleteSearchColumns()"/>
    </td>
    <td colspan="2"></td>
</tr>
<tr>
    <td colspan="4">
        <table id="SearchColumnTable" class="table">
            <thead>
            <th>
                <input type="checkbox"
                       onclick="selectAll('SearchColumn_ids',this);"/>
            </th>
            <th>序号</th>
            <th >columnName</th>
            <th >columnLabel</th>
            <th >dataType</th>
            <th >showType</th>
            <th >relOper</th>
            <th >fkTable</th>
            <th >fkKeyColumn</th>
            <th >fkLabelColumn</th>
            <th >coditions</th>
            <th >备注</th>

            </thead>
            <tbody>
            <s:iterator value="searchColumnList" status="st">

                <tr class="<s:property value="#st.index%2==0?'odd':'even'"/>">
                    <td>
                        <input type="checkbox"  name="SearchColumn_ids"
                               value="<s:property value="#st.index"/>"/>
                    </td>
                    <td>
                        <s:property value="#st.index+1"/>
                        <s:hidden
                                name="%{'searchColumnList['+#st.index+'].id'}"/>
                    </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].columnName'}">
                    </s:textfield> <span style="color:red">*</span>
                </td>
                    <td>
                        <s:textfield
                                name="%{'searchColumnList['+#st.index+'].columnLabel'}">
                        </s:textfield> <span style="color:red">*</span>
                        <s:hidden name="%{'searchColumnList['+#st.index+'].searchId'}"></s:hidden>
                    </td>
                <td>
                    <s:select name="%{'searchColumnList['+#st.index+'].dataType'}" list="dataTypeMap" listKey="key" listValue="value"></s:select>
                    <span style="color:red">*</span>
                </td>
                <td>
                    <s:select name="%{'searchColumnList['+#st.index+'].showType'}" list="showTypeMap" listKey="key" listValue="value" ></s:select> <span style="color:red">*</span>
                </td>
                <td>
                    <s:checkboxlist name="%{'searchColumnList['+#st.index+'].relOperList'}" list="operMap" listKey="key" listValue="value" ></s:checkboxlist> <span style="color:red">*</span>
                </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].fkTable'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].fkKeyColumn'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].fkLabelColumn'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].coditions'}">
                    </s:textfield> 
                </td>
                <td>
                    <s:textfield
                            name="%{'searchColumnList['+#st.index+'].remark'}">
                    </s:textfield> 
                </td>



                </tr>

            </s:iterator>

            </tbody>
        </table>
    </td>
</tr>
                      <tr>
                          <td colspan="4" class="td_page_right" style="text-align:right;height: 24px;">
                              <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
                              <s:submit id="saveBtn" method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" ></s:submit>
                              <c:if test="${search.id!=null}">
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
