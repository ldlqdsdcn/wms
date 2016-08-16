<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.effect.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script> 
 
 <script type="text/javascript">
	function validateForm()
	{
		var tableName=document.getElementById('tableName');
		if(isEmpty(tableName.value))
		{
			alert('<delmar:message key="table.message.tablenotnull" />');
			return false;
		}
		
		var className=document.getElementById('className');
		if(isEmpty(className.value))
		{
			alert('<delmar:message key="table.message.classnotnull" />');
			return false;
		}
		
		return true;
	}
	function addColumn()
	{
		var editForm=document.getElementById('editForm');
		editForm.action='<c:url value="/core/search_addColumn.action"/>';
		editForm.submit();
	}
	function deleteColumns()
	{
		var editForm=document.getElementById('editForm');
		editForm.action='<c:url value="/core/search_deleteColumns.action"/>';
		editForm.submit();
	}
</script>
</head>

<body>
<s:form id="editForm" action="search_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="search.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="search.location" /></td>
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
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               
               <tr  class="query_one">
               <td  width="20%"><s:label for="name" value="%{#session.resource.get('search.column.name')}"  ></s:label></td>		
				<td  width="30%">
						<s:textfield name="search.name" id="name" cssStyle="width:260px;"></s:textfield>
				</td>		
               	<td width="20%">
               	<s:label for="pageUrl"  value="%{#session.resource.get('search.column.pageUrl')}"  ></s:label>
               	</td>
               	<td  width="30%">
               	<s:textfield name="search.pageUrl" id="pageUrl" cssStyle="width:260px;"></s:textfield>
               	</td>
				</tr>
			<tr  class="query_two">
               <td  width="20%">
               			<s:label for="remark"  value="%{#session.resource.get('common.label.remark')}"  ></s:label>
               	</td>		
				<td colspan="3">
						<s:textfield name="search.remark" id="remark" cssStyle="width:500px;"></s:textfield>
				</td>		
            </tr>
            <tr>
            <td colspan="2" style="padding-left: 20px;text-align: left;">
            
            <input value="<delmar:message key="search.button.addsearch" />" type="button"  class="input_submit" onclick="javascript:addColumn()"/> &nbsp;&nbsp;
            <input class="input_submit" type="button"    value="<delmar:message key="search.button.deletesearch" />" onclick="javascript:deleteColumns()"/>
            </td><td colspan="2"></td>
            </tr>
            <tr>
            <td colspan="4">
            <table id="columnTable" class="table">
            	<thead>
            	<th>
            	<input type="checkbox"  onclick="selectAll('ids',this);"/>
            	</th>
            	<th width='50px'><delmar:message key="searchcolumn.column.columnname" /></th>
            	<th><delmar:message key="searchcolumn.column.datatype" /></th>
            	<th><delmar:message key="searchcolumn.column.relOper" /></th>
            	<th><delmar:message key="searchcolumn.column.showtype" /></th>
            	<th ><delmar:message key="searchcolumn.column.tableName" /></th>
            	<th>
            	<delmar:message key="searchcolumn.column.value" />
            	</th>
            	<th>
            	<delmar:message key="searchcolumn.column.name" />
            	</th>
				<th>
            	<delmar:message key="searchcolumn.column.conditions" />
            	</th>
            	<th>
            	<delmar:message key="searchcolumn.column.columnShowName" />
            	</th>
            	<th>
            	<delmar:message key="common.label.remark" />
            	</th>
            	</thead>
            	<tbody id="detailtbody">
              	<s:iterator value="columns" status="st">
              	
              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
              	<td>
              		<input type="checkbox" name="ids"  value="<s:property value="#st.index"/>"/>
              	</td>
              	<td>
              	<s:property value="#st.index+1"/>
              	<s:hidden name="%{'columns['+#st.index+'].id'}" />
              	</td>
              	<td>
              	    	<s:select name="%{'columns['+#st.index+'].dataType'}" list="#{1:'数值',2:'字符',3:'日期'}"></s:select>
              	</td>
              	<td>
              	<s:checkboxlist name="%{'columns['+#st.index+'].relOperList'}" list="#{'1':'>','2':'>=','3':'<','4':'<=','5':'=' }"></s:checkboxlist>
              	</td>
              	<td><s:textfield name="%{'columns['+#st.index+'].relOper'}" cssStyle="width:100px;"></s:textfield>	</td>
              	<td>
          				<s:textfield name="%{'columns['+#st.index+'].tableName'}"></s:textfield>	
              	</td>
              	<td>
              	<s:textfield name="%{'columns['+#st.index+'].value'}"></s:textfield>	
              	</td>
              	<td>
              		<s:textfield name="%{'columns['+#st.index+'].name'}"></s:textfield>	
              	</td>
             		<td>
              		<s:textfield name="%{'columns['+#st.index+'].coditions'}"></s:textfield>	
              	</td>
              	
              	  		<td>
              		<s:textfield name="%{'columns['+#st.index+'].columnShowName'}"></s:textfield>	
              	</td>
              	  		<td>
              		<s:textfield name="%{'columns['+#st.index+'].remark'}"></s:textfield>	
              	</td>
              	</tr>
              	
              	</s:iterator>
              	
              	</tbody>
            </table>
            </td>
            </tr>	
			<tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit"  value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" onclick="return validateForm()"></s:submit>
						<c:if test="${search.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
						</c:if>
						<input onclick="window.location='<c:url value="/core/table_list.action"/>'"  type="button" value="<delmar:message key="common.button.back" />"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <label style="color: red">
                	<%out.println(session.getAttribute("msg")==null?"":session.getAttribute("msg"));
                		session.removeAttribute("msg");
                	%>
                </label>
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>







</body>
</html>
