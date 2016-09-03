<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="role.tile"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
 <script type='text/javascript' src='../js/ea.validate.js'></script>

<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
<script type="text/javascript" src="../js/json2.js"></script>

<script type="text/javascript">
		var editPri=false;
		function editPrivilege()
		{
			if(editPri)
				{
				$("#editTable").css("display","none"); 
				$("#viewTable").css("display","block"); 
				$("#editPrivBtn").val('<delmar:message key="role.button.edit_privileges"/>');
				
				editPri=false;
				}
			else
				{
				$("#editTable").css("display","block"); 
				$("#viewTable").css("display","none"); 
				$("#editPrivBtn").val('<delmar:message key="role.button.cancel_edit_privileges"/>');
				editPri=true;
				}
		}


</script>
</head>



<body>

<s:form id="editForm" action="role_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="role.id"/>
<!-- table 01 bgn -->
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    <!-- table 02 bgn -->
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="role.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"/>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"/>
			
			</c:if>
		</div></td>
        </tr>
      </table>
      <!-- table 02 end -->
    	</td>
    	</tr>
    	<tr>
    	<td>
    	<!-- table 03 bgn -->
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          
           <!-- table 04 bgn -->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                <!-- table 05 bgn -->
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('role.column.name')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="role.name" id="name"/>
               	</td>
				</tr>
				
				
						<tr  class="query_two">
							
							<td ><s:label for="descr" value="%{#session.resource.get('role.column.remark')}" /></td>
							<td colspan="3">
							<s:textfield name="role.remark" id="descr" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit"  value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
						<s:submit method="save"   value="%{#session.resource.get('common.button.save')}"    cssClass="input_submit"/>
						<c:if test="${role.id!=null}">
						<s:submit method="delete"   value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/role_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                   <tr>
                <td colspan="4">
                	<input type="button" value="<delmar:message key="role.button.edit_privileges"/>"  onclick="editPrivilege()" id="editPrivBtn">
                </td>
                </tr>
                <tr>
                <td colspan="4">
                 <table width="100%" cellpadding="10" cellspacing="0" >
               <tr> 
                <td align="center">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                <table  id="editTable"   border="0"  cellpadding="0" cellspacing="0"  class="table"  style="display: none;">
                <thead >

						<th> <delmar:message key="role.label.module"/>
						</th>
						<th><delmar:message key="role.label.privileges"/></th>
						<th><delmar:message key="role.label.datafilter"/></th>
					</thead>
                <tbody>
					<s:iterator value="rmContentList" status="st" id="rmc">
					<tr class='<c:out value="${st.index%2==0?'odd':'even'}"></c:out>'>
					<td>
					<s:property value="module.name"/>
					</td>
					<td>
					<!--value="operIds" -->
					<s:checkboxlist list="operatorList" listKey="id" listValue="name" name="%{'rmContentList['+#st.index+'].operIds'}" ></s:checkboxlist>
					
					</td>
					<td>
					<s:select list="datadictList" listKey="datadictId" listValue="name"  name="%{'rmContentList['+#st.index+'].accessLevel'}"></s:select>
						
					</td>
					</tr>
					</s:iterator>
                </table>
                <table id="viewTable"  border="0"  cellpadding="0" cellspacing="0"  class="table"  >
                <thead >

						<th> <delmar:message key="role.label.module"/>
						</th>
						<th><delmar:message key="role.label.privileges"/></th>
						<th><delmar:message key="role.label.datafilter"/></th>
					</thead>
					 <tbody>
					 <c:out value="${MAP_KEY_OF_SESSION.privilegesString }" escapeXml="false"></c:out>
					 
					 </tbody>
                </table>
                
                
                
                
                </td></tr></table> </td>
                </tr></table>
			</td>
                </tr>
              
                
           
               <tr>
                <td colspan="4">
                 <table width="100%" cellpadding="10" cellspacing="0" >
               <tr> 
                <td align="center">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                <table   border="0"  cellpadding="0" cellspacing="0" class="table">
                <thead >
						<th><img src='../images/ico/dtladd.gif'  onclick="javascript:openUserDialog();" class="folder" alt="<delmar:message key='public.button.add'/>"/> 
						</th>                
						<th> <delmar:message key="user.column.loginname"/>
						</th>
						<th><delmar:message key="user.column.name"/></th>
						<th><delmar:message key="org.column.name"/></th>
                        <th ><delmar:message key="common.button.delete" /></th>
					
					</thead>
                <tbody>
					<s:iterator value="#session.MAP_KEY_OF_SESSION.rmUserList" status="st" id="rmuser">
					<tr class='<c:out value="${st.index%2==0?'odd':'even'}"></c:out>'>
					<td>
					<c:out value="${st.index+1}"></c:out>
					</td>					
					<td>
					<s:property value="username"/>
					</td>
					<td>
					<s:property value="name"/>
					
					</td>
					<td>
					<s:property value="org.name"/>
						
					</td>
					<td>
					<img src='../images/ico/dtljian.gif' class='folder' alt='<delmar:message key="common.button.delete" />' onclick='javascript:deleteUserOrg("<s:property value='id'/>");' />
					</td>
					</tr>
					</s:iterator>
                </table>
                </td></tr></table> </td>
                </tr></table>
			</td>
                </tr>
                                
                <tr>
                <td colspan="4">
             
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table>
                <!-- table 05 end -->	
                
                </td></tr>
                
                </table>
                <!-- table 04 end -->	
</td></tr>

</table>
<!-- table 03 end -->	
    	</td>
    	</tr>
</table>
<!-- table 01 end -->	
</s:form>



<div id="selectDiv">
   <input type="hidden" name="cids" id="cids"/>
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>

 <script type="text/javascript">
 
 var userroledialog;
 $(document).ready(function() {
	 userroledialog=$("#selectDiv").dialog({
			autoOpen: false,
			height: 500,
			width: 700,
			modal: true,
			title:"<delmar:message key='role.location' />",
			resizable:false,
		    buttons: {
		          "<delmar:message key='role.button.addroleuser'/>":function() {
		        	  
		        	  if ($("#cids").val()=="")
		        	   {
		        		  alert("至少选择一个");
		        		  return;
		        		}
		        	  else
		        		{
		        		   addRoleUser($("#cids").val());
		        		   userroledialog.dialog( "close" );
		        		}
		          },
		          "<delmar:message key='role.button.canceladd'/>": function() {
		        	  userroledialog.dialog( "close" );
		          }
		        },
		        			
	        close: function() {
	        	userroledialog.dialog( "close" );
		     }		
	});
	

	});
 
 
	function openUserDialog()
	{
		document.getElementById('selectIframe').src="<c:url value='/system/role_addRoleUser.action'/>?roleId="+$("#id").val();
		userroledialog=$('#selectDiv').dialog('open');
	}
	
	
	function addRoleUser(userIds)
	{

		  $.ajax({  
	          url:'role_saveRoleUser.action?roleId='+$("#id").val()+"&userIds="+userIds,  
	          type:'post',  
	          success:function (data) {  	        	 
	        	 window.location.reload();	        
	          }  
	      });  
		  
	}
	
	
	function deleteUserOrg(userId)
	{

		  $.ajax({  
	          url:'role_delRoleUser.action?roleId='+$("#id").val()+"&userId="+userId,  
	          type:'post',  
	          success:function (data) { 
	        	  window.location.reload();
	          }  
	      });  		
	}
	

	
	
 </script>

</body>
</html>
