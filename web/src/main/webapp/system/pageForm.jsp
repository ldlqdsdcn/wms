<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> 
<delmar:message key="page.title"/>
</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>

 <script type='text/javascript' src='../js/ea.validate.js'></script>
<style type="text/css">
	.navig img
	{
		-webkit-filter: grayscale(0%); 
		margin-top: 1px;
	}
	.navig img:hover  {
	-webkit-filter: grayscale(70%); 
	cursor: pointer;
}
</style>
</head>



<body>

<s:form id="editForm" action="page_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="page.id"/>
<!--table 01-->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
		<!--table 02-->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">

          <delmar:message key="page.location"/></td>
         <td background="../images/top2.png" width="*"  height="32" align="right">
         
         <div class="C_S_F_L" style="padding-top: 5px">

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
		<!--table 03-->
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          
           <!--table 04-->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center">
					<!--table 05-->
					<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                <!--table 06-->
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('page.column.name')}"/>
               	</td>
               	<td colspan="3">
               	<s:textfield name="page.name" id="name"/>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><s:label for="pageUrl" value="%{#session.resource.get('page.column.pageUrl')}"/></td>
							<td colspan="3">
							<s:textfield name="page.pageUrl" id="pageUrl" cssStyle="width:500px;"/>
							</td>
							
				</tr>
						<tr  class="query_one">
							
							<td ><s:label for="descr"  value="%{#session.resource.get('page.column.remark')}"/></td>
							<td colspan="3">
							<s:textfield name="page.remark" id="descr" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}" cssClass="input_submit"/>
						<c:if test="${page.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}" cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/page_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                
                </table>
                <!--table 06 end-->
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                </td>
                </tr>
                </table>
				<!--table 05 end-->
				</td></tr></table> <!--table 04 end-->
</td></tr></table>
		<!--table 03 end-->
	</td></tr>
</table>
	<!--table 01 end-->
</s:form>







</body>

