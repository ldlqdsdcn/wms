<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="javaclass.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>

 <script type='text/javascript' src='../js/ea.validate.js'></script>

</head>



<body>

<s:form id="editForm" action="commonFile_edit" namespace='/commons' theme="simple">
<s:hidden id="id" name="fileRelation.id"/>
<s:hidden  name="fileRelation.delmarFile.id"/>
<s:hidden name="fileRelation.fileId"/>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">位置：文件详情</td>
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
               	<s:label for="name" value="文件名"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="fileRelation.delmarFile.filename" id="name" disabled="true"/>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><s:label for="pageUrl" value="摘要"  /></td>
							<td colspan="3">
							<s:textfield name="fileRelation.delmarFile.fileAbstract" id="fileAbstract" cssStyle="width:500px;"/>
							</td>
							
				</tr>
						<tr  class="query_one">
							
							<td ><s:label for="descr"  value="关键字"  /></td>
							<td colspan="3">
							<s:textfield name="fileRelation.delmarFile.fileKeyword" id="remark" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit" value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"/>
						<c:if test="${fileRelation.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/commons/commonFile_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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

