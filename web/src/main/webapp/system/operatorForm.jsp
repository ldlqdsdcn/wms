<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>

<delmar:message key="operator.title"/>
</title>
<link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css" media="all"/>
<script type='text/javascript' src='<c:url value="/js/ea.effect.js"/>'></script>

</head>



<body>

<s:form id="editForm" action="operator_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="operator.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="operator.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"></s:submit>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne"  value="%{#session.resource.get('common.button.next')}"   cssClass="input_submit"></s:submit>
			
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
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('operator.column.name')}"></s:label>
               	</td>
               	<td width="30%">
               	<c:choose>
               	<c:when test="${operator.init=='N'}">
               	<s:textfield name="operator.name" id="name" ></s:textfield>
               	</c:when>
               	<c:otherwise>
               	<s:property value="operator.name"/>
               	 	
               	</c:otherwise>
               	</c:choose>
               	
               	
               	</td>
               	<td width="20%"><delmar:message key="operator.column.value"/></td>
               	<td width="30%">
               	
               		<c:choose>
               	<c:when test="${operator.init=='N'}">
               		<s:textfield name="operator.value" id="value"></s:textfield>
               	</c:when>
               	<c:otherwise>
               	<s:property value="operator.value"/>
               	 	
               	</c:otherwise>
               	</c:choose>
               
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><delmar:message key="operator.column.init"/></td>
							<td colspan="3">
							<s:property value="operator.init"/>
							
							<s:hidden name="operator.init"></s:hidden>
							</td>
							
				</tr>
						<tr  class="query_one">
							
							<td ><s:label for="descr" value="%{#session.resource.get('operator.column.remark')}"></s:label></td>
							<td colspan="3">
								<c:choose>
               	<c:when test="${operator.init=='N'}">
               	<s:textfield name="operator.remark" id="remark" cssStyle="width:500px;"></s:textfield>
               	</c:when>
               	<c:otherwise>
               	<s:property value="operator.remark"/>
               	 	
               	</c:otherwise>
               	</c:choose>
							
							
							</td>
							
                            
						</tr>
						
					<tr  class="query_two">
					<td>
					<delmar:message key="operator.label.module"/>
					</td>
					<td colspan="3">
					<s:checkboxlist list="moduleList" name="modIds" listKey="id" listValue="name"></s:checkboxlist>
					</td>
					
					</tr>
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="create" value="%{#session.resource.get('common.button.create')}" cssClass="input_submit"></s:submit>
						
						<s:submit method="save"  value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"></s:submit>
						
						<input onclick="window.location='<c:url value="/system/operator_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>







</body>

