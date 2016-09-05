<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="javaclass.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.validate.js'></script>

	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/>"></script>
	<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<script type='text/javascript' src='../js/dm/uploadfile.js'></script>
<script type="text/javascript">




function validateData()
{
	var versionCode=	document.getElementById('versionCode');
	if(!isInt(versionCode.value))
		{
			alert('版本号必须为整数');
			versionCode.focus();
			return false;
		}
	return true;
}

</script>
</head>
<body>
<s:form id="editForm" action="mobileclient_edit" namespace='/system'  theme="simple">
<s:hidden id="id" name="mobileClient.id"/>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="mobileclient.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"/>
			</c:if>		
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"   cssClass="input_submit"/>
			
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
               	<s:label for="versionCode" value="%{#session.resource.get('mobileclient.versionCode')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="mobileClient.versionCode" id="versionCode"/>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><s:label for="versionName" value="%{#session.resource.get('mobileclient.versionName')}"  /></td>
							<td colspan="3">
							<s:textfield name="mobileClient.versionName" id="versionName" cssStyle="width:500px;"/>
							</td>
							
				</tr>
						<tr  class="query_one">
							
							<td ><s:label for="remark"  value="%{#session.resource.get('javaclass.column.remark')}"  /></td>
							<td colspan="3">
							<s:textfield name="mobileClient.remark" id="remark" cssStyle="width:500px;"/>
							</td>
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit" value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" onclick="return validateData()"/>
						<c:if test="${mobileClient.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/mobileclient_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td>
                <delmar:file tableName="sys_mobile_client"  tableId="${mobileClient.id }"/>
                
                </td>
                
                </tr>
                
                
                	<c:if test="${mobileClient.id!=null}">
                <tr>
                <td>
                
                <!-- table 页 bgn -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
            <td width="90" height="26" align="center" background="../images/table_page_1.gif">
  					文件上传
            </td>
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
                <!-- table bgn -->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                
                     <tr>
                <td>
<table   border="0"  cellpadding="0" cellspacing="0" class="table" id="fileTable">
					<thead >
						<th>
            				<input type="checkbox"  onclick="selectAll('ids',this);"/>
            			</th>
						<th>
							文件名
						</th>
						<th>
							摘要
						</th>
						<th>
						关键字
						</th>
						<th>&nbsp;</th>
					</thead>
					<tbody>
						<s:iterator value="fileRelationList" status="st">
								<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
								 	<td>
              		<input type="checkbox" name="ids"  value="<s:property value="#st.index"/>"/>
              	</td>
						<td>
							<s:hidden name="%{'fileRelationList['+#st.index+'].id'}" />
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.filename'}" readonly="true"/>
						</td>
						<td>
							<s:hidden name="%{'fileRelationList['+#st.index+'].delmarFile.id'}" />
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.fileAbstract'}" cssStyle="width:300px"/>
						</td>
						<td>
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.fileKeyword'}"/>
							<s:hidden name="%{'fileRelationList['+#st.index+'].delmarFile.path'}"/>
						</td>
						<td>
								<input type="button" onclick="javascript:uploadFileSelf(this);" value="选择附件">
						</td>
						</s:iterator>
					
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
                
                
                
                <!-- table end -->
                
                
                
                </td>
                 </tr>
                </c:if>
                
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>

<%
System.out.println("------------------------->"+request.getServletPath());
System.out.println("------------------------->"+request.getContextPath());
System.out.println("------------------------->"+request.getServletPath());
%>





</body>
</html>
