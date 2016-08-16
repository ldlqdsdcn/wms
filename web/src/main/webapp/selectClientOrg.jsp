<%@page import="com.delmar.system.web.bean.SelectList"%>
<%@page import="com.delmar.system.web.bean.UserLoginPageSelectList"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<fmt:message key="selectClientOrg.title"></fmt:message>
</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='dwr/interface/userLoginDwr.js'></script>
  <script type='text/javascript' src='dwr/engine.js'></script>
  <script type='text/javascript' src='dwr/util.js'></script>
  
<script type="text/javascript" src="js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

  
  <script type='text/javascript' >
	    function getOrgs()
	    {
	
	     var clientId=	dwr.util.getValue('clientId');
	     userLoginDwr.getOrgSelectByClientId(clientId,function(data)
	     {
	    	 			document.getElementById("orgTD").innerHTML="<select id='orgId' name='orgId'>"+data+"</select>";
	    });
	    }
	    function saveForm()
	    {
	    	var clientId=	dwr.util.getValue('clientId');
	    	var clientName=	dwr.util.getText('clientId');	    	
	    	var orgId=	dwr.util.getValue('orgId');
	    	var orgName=dwr.util.getText('orgId');
	    	userLoginDwr.selectClientAndOrg(clientId,clientName,orgId,orgName,function(data)
	    			{
	    					if(data=="Y")
	    					{
	    						window.parent.closeDialog();
	    						$("#loginOrgName").val(orgName);
	    					}
	    			});
	    }
	    
</script>
</head>

<body >
<%UserLoginPageSelectList ulpsl=new UserLoginPageSelectList(); %>

<table id="list" width="100%" border="0" cellpadding="0" cellspacing="1">


<tr  class="query_one">
<td   width="20%" ><fmt:message key="selectClientOrg.label.client"></fmt:message></td>

<td  width="80%" >
	<%=ulpsl.getClientSelectList(session) %>
</td>

</tr>
<tr  class="query_two">
<td   width="20%" ><fmt:message key="selectClientOrg.label.org"></fmt:message></td>
<td align=left  id="orgTD" width="80%" >
	<%=ulpsl.getOrgsSelectList(session)%>
</td>
</tr>

<tr>
   <td colspan="4" class="td_page_right">
   <input type="button" value="<fmt:message key="common.label.confirm"></fmt:message>" class="input_submit" onclick="javascript:saveForm()" />		</td></tr>
   <tr><td align="center" style="color: red;" id="msg" colspan="4">
                        
											
						
</td></tr>
</table>









</body>
</html>
