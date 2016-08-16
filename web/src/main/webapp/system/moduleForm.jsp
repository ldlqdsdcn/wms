<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="module.title"></fmt:message></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
 <script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript">
	function validate()
	{
		var name=document.getElementById("name");
		if(isEmpty(name.value))
			{
				alert("<fmt:message key="module.message.namenotnull"/>");
				name.focus();
				return false;
			}
		return true;

	}
</script>
</head>



<body>

<s:form id="editForm" action="module_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="module.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><fmt:message key="module.title"></fmt:message></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}" cssClass="input_submit"></s:submit>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}" cssClass="input_submit"></s:submit>
			
			</c:if>
		</div></td>
        </tr>
      </table>
</td></tr>
<tr><td>
<table width="100%" border="0" cellpadding="" cellspacing="0"  class="table_blue">
    	
        <tr align="center" valign="top"> 
          <td>
          
           
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('module.column.name')}"></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="module.name" id="name"  required="true"></s:textfield>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><s:label for="dataFilter" value="%{#session.resource.get('module.column.datafilter')}"></s:label></td>
							<td >
							<s:radio id="dataFilter" name="module.dataFilter" list="#{'N':'N','Y':'Y'}" required="true"></s:radio>
							</td>
					<td ><s:label for="init" value="%{#session.resource.get('module.column.init')}"></s:label></td>
							<td >
								<s:textfield name="module.init" readonly="true"></s:textfield>
							</td>		
				</tr>
						<tr  class="query_one">
							<td ><s:label for="descr" value="%{#session.resource.get('module.column.remark')}"></s:label></td>
							<td colspan="3">
							<s:textfield name="module.remark" id="remark" cssStyle="width:500px;"></s:textfield>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit"  value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"></s:submit>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}" cssClass="input_submit" onclick="return validate()"></s:submit>
						<c:if test="${module.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}" cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/module_list.action"/>'"  type="button" value="<fmt:message key="common.button.back"></fmt:message>"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
               
<tr>
    	<td colspan="4">
    	<!-- table 页 bgn -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
            <td width="90" height="26" align="center" background="../images/table_page_1.gif"><fmt:message key="module.label.menusetting"/></td>
           <c:choose>
			<c:when test="${module.id!=null}">
				<td width="90" height="26" align="center" background="../images/table_page_2.gif">
				<a href="<c:url value="/system/module_editPage.action?id=${module.id}"></c:url>"><fmt:message key="module.label.pagesetting"/></a>
				</td>
				<td width="90" height="26" align="center" background="../images/table_page_2.gif">
			<a href="<c:url value="/system/module_editJavabean.action?id=${module.id}"></c:url>">
				<fmt:message key="module.label.javaclasssetting"/>
			</a>
		</td>
			</c:when>
			<c:otherwise>
			<td width="90" height="26" align="center" background="../images/table_page_3.gif">
				<fmt:message key="module.label.pagesetting"/>
			</td>
			<td width="90" height="26" align="center" background="../images/table_page_3.gif">
				<fmt:message key="module.label.javaclasssetting"/>
			</td>
			</c:otherwise>
		</c:choose>
        <td background="../images/table_page_bg.gif" width="*"  height="26"><div class="C_S_F_L">
			&nbsp;
		</div></td>
              </tr>
            </table>
             <!-- table 页 end -->   
    	</td>
    	
    	</tr>
		<tr>
		<td colspan="4">
		
		
		
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
<table   border="0"  cellpadding="0" cellspacing="0" class="table">
					<thead >
						<th><input type='checkbox' name='selectall' onClick="selectAll('ids',this);" style='margin:0px;border: none'/>
						</th>
						<th><fmt:message key="module.label.menuname"/></th>
						<th>	<fmt:message key="module.label.menuaddress"/></th>
						<th>	<fmt:message key="module.label.parentmenu"/></th>
					</thead>
					<tbody>
					<c:forEach items="${moduleMenuList}" var="mp" varStatus="status">
					<tr class='<c:out value="${status.index%2==0?'odd':'even'}"></c:out>'>
					<td>
			<input type="checkBox" name="ids"	value="<c:out value='${mp.menuId}'/>"  style='border: none' <c:if test="${mp.id!=null}">checked="checked"</c:if> />		
					</td>
					<td>
					<c:out value="${mp.pageMenu.name}"></c:out>
					</td>	
					<td>
					<c:out value="${mp.pageMenu.pageUrl}"></c:out>
					</td>
					<td>
					<c:out value="${mp.pageMenu.parentMenu.name}"></c:out>
					</td>
					</tr>
					
					</c:forEach>
					</tbody>
</table>			
				</td>
				</tr>
				</table>
				</td>
				</tr>
				</table>
	</td>
		</tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td>
                </tr>
                </table>
</td></tr></table>
</td>
</tr>
</table>
</s:form>







</body>

