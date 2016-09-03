<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="datadictType.title" /></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>

 <script type='text/javascript' src='../js/ea.validate.js'></script>

</head>



<body>

<s:form id="editForm" action="datadicttype_*_edit" namespace='/base' theme="simple">
<s:hidden id="id" name="datadictType.id"/>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="datadictType.location" /></td>
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
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               
               <tr class="query_one">
               		<td style="width:20%" >
               			<s:label for="value" value="%{#session.resource.get('datadictType.column.value')}" />
               			</td>
               			<td colspan="3">
               			<s:textfield name="datadictType.value" id="value" readonly="datadictType.id!=null"/>
               			
              			
               			<s:radio name="datadictType.bePublic"  list="bePublicList" listKey="id" listValue="value"/>
               			</td>
               </tr>
               
               <tr  class="query_two">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('datadictType.column.name')}" />
               	</td>
               	<td colspan="3">
               	<s:textfield name="datadictType.name" id="name"/>
               	</td>
				</tr>
				
				
						<tr  class="query_one">
							
							<td ><s:label for="descr" value="%{#session.resource.get('datadictType.column.remark')}" /></td>
							<td colspan="3">
							<s:textfield name="datadictType.remark" id="remark" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			<s:submit method="edit"  value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"/>
						<c:if test="${datadictType.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>

						<input onclick="window.location='<c:url value="/base/datadicttype_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
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

