<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-25 22:44:28
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>

<title>
	页面
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" charset="utf-8" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
 <script type='text/javascript' src='../js/dm/delmar.js'></script>
 <script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"></script>

<script type="text/javascript">
 function gotoList()
 {
	 window.location='<c:url value="/core/page_list.action"/>';
 }
 $(document).ready(function(){
    $('#formDetail tr:odd').addClass("query_one");
     $('#formDetail tr:even').addClass("query_two");
     $("#formDetail").find("tr:last").removeClass("query_one").removeClass("query_two");
 });
</script>
</head>
<body>
<s:form id="editForm" action="page_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="page.id"></s:hidden>
<!--table 01 bgn-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td valign="top">
        <!--table 02 bgn-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
          <td align="left" class="navig">	页面</td>
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
                <td align="center"> <table width="100%" border="0" cellpadding="10" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>


                <!--table 05 bgn-->
                <table width="100%" border="0" cellpadding="0" cellspacing="1" id="formDetail">
                        <tr>
                        <td width="20%"><s:label for="name" value="名称" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.name" id="name"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="descr" value="描述" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.descr" id="descr"  cssStyle="width:500px;" ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="help" value="帮助" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.help" id="help"  cssStyle="width:500px;" ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="windowId" value="windowId" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.windowId" id="windowId"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="tableId" value="tableId" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.tableId" id="tableId"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="filterColumnId" value="filterColumnId" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.filterColumnId" id="filterColumnId"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="isactive" value="是否有效" ></s:label></td>
                        <td width="30%">
                          <s:radio id="isactive" name="page.isactive" list="#{'Y':'Y','N':'N'}" required="true"></s:radio>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="showInTab" value="showInTab" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.showInTab" id="showInTab"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="seqNo" value="seqNo" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.seqNo" id="seqNo"   ></s:textfield>
                          </td>
                        </tr>
                        <tr>
                        <td width="20%"><s:label for="filterSql" value="filterSql" ></s:label></td>
                        <td width="30%">
                          <s:textfield name="page.filterSql" id="filterSql"   ></s:textfield>
                          </td>
                        </tr>
                <tr>
                <td colspan="4" class="td_page_right">
                    <s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"></s:submit>
						<c:if test="${page.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
						</c:if>
						<input onclick="gotoList()"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
                </td>
                </tr>
                </table>
                <!--table 05 end-->
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>

                </td>
                </tr>
                </table>
                <!--table 04 end-->
                </td></tr></table>
                <!--table 03 end-->
</td></tr></table>
</s:form>
</body>
</html>
