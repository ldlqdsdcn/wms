
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="client.title"/>
</title>
<link rel="stylesheet" href="../css/map.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>

<script type='text/javascript' src='../js/dm/delmar.js'></script>

  <script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript" src="../js/json2.js"></script>  
<script type='text/javascript' src='/delmar_web/dwr/interface/userDwr.js'></script>
  <script type='text/javascript' src='/delmar_web/dwr/engine.js'></script> 
   <script type='text/javascript' src='/delmar_web/dwr/util.js'></script>
   
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />


 <script type="text/javascript">
 

 $(document).ready(function() {
		
	
	$("#selectDiv").dialog({
			autoOpen: false,
			height: 300,
			width: 700,
			modal: true,
			title:'位置：场馆位置设定',
			resizable:false});
	}

	
	);
 </script>

</head>



<body >

<s:form id="editForm" action="client_edit" namespace='/system' theme="simple">
<s:hidden id="id" name="client.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="client.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne"  value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"></s:submit>
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
               	<td width="20%">
               	<s:label for="value"  value="%{#session.resource.get('client.column.value')}" ></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="client.value" id="value"></s:textfield>
               	</td>
				</tr>
               <tr  class="query_two">
               	<td width="20%">
               	<s:label for="name"  value="%{#session.resource.get('client.column.name')}" ></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="client.name" id="name"></s:textfield>
               	</td>
				</tr>

				
	
				<tr  class="query_two">
				<td ><s:label for="descr"  value="%{#session.resource.get('client.column.remark')}" ></s:label></td>
							<td colspan="3">
							<s:textarea name="client.remark" id="descr" cssStyle="width:500px;height:100px;"></s:textarea>
							</td>
							
				</tr>
				
				 <tr>
				<td colspan="4">
				
				<s:action id="clientextralist" name="clientextras_list" executeResult="true">
				  <s:param name="sysClientId" value="client.id"></s:param>
				</s:action>
				
				</td>
				</tr>					
										
				
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit"  value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"></s:submit>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"   cssClass="input_submit"></s:submit>
						<c:if test="${javabean.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"   cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/client_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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





  <div id="selectDiv">
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>

</body>
</html>
