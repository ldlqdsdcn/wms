<%@page import="com.delmar.system.web.bean.SelectList"%>
<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="org.title" />
</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
</head>

<body >
<input type="hidden" id="id"/>

<table id="list" width="100%" border="0" cellpadding="0" cellspacing="1">
<tr class="query_one">
<td width="15%" ><delmar:message key="org.column.value" /></td>
<td width="35%" >
	<input type="text" id="value" style="width: 250px"/>
</td>
<td width="15%" ><delmar:message key="org.column.client" /></td>
<td width="35%" >
	<%=SelectList.getClientList(request) %>
</td>
</tr>
<tr class="query_two">
<td width="15%" class="td1"><delmar:message key="org.column.name" /></td>
<td>
	<input type="text" id="name" style="width: 250px"/>
</td>
<td width="15%" class="td1"><delmar:message key="org.column.level" /></td>
<td>
<div id="levelName"></div>
<input type="hidden" id="level"/>



</td>
</tr>
<tr class="query_one">
<td width="15%" class="td1"><delmar:message key="org.column.parentorg" /></td>
<td id="parentOrgIdTD" colspan="3">
<div id="parentOrgIdDIV"></div>
<input type="hidden" name="parentOrgId" id="parentOrgId"/>
</td>
</tr>


<tr class="query_two"><td width="15%" class="td1"><delmar:message key="org.column.remark" /></td><td colspan="3">
<input id='remark' style="width:500px;"/>
</td></tr>
<tr>
   <td colspan="4" class="td_page_right">
   <input type="button" value="<delmar:message key="common.button.save" />" class="input_submit" onclick="javascript:saveForm()" />		</td></tr>
   <tr><td align="center" style="color: red;" id="msg" colspan="4">
                        
											
						
</td></tr>
</table>







<script type="text/javascript">
	function saveForm()
	{
		var org={};
		
		org.id=dwr.util.getValue('id');
		if(org.id=='') org.id=null;
		else
		{
			org.id=parseInt(org.id,10);
		}
		org.parentOrgId=dwr.util.getValue('parentOrgId');
		
		if(isEmpty(org.parentOrgId))
		{
			org.parentOrgId=null;
		}
		else
		{
			org.parentOrgId=parseInt(org.parentOrgId,10);
		}
		var value= document.getElementById('value');
		if(isEmpty(value.value))
		{
			document.getElementById('msg').innerHTML='<delmar:message key="org.message.valuenotnull" />';
			value.focus();
			return;
		}		
		var name= document.getElementById('name');
		if(isEmpty(name.value))
		{
			document.getElementById('msg').innerHTML='<delmar:message key="org.message.namenotnull" />';
			name.focus();
			return;
		}
		org.value=value.value;
		org.name=name.value;
		
		org.orgLevel=dwr.util.getValue('level');
		org.parentOrgId=dwr.util.getValue('parentOrgId');
		org.remark=dwr.util.getValue('remark');
		document.getElementById('msg').innerHTML='';
		org.sysClientId=dwr.util.getValue('clientId');
		
		orgDwr.saveOrg(org,function(data)
		{
			window.parent.closeDialog(org.parentOrgId);
		});	
	}
	function init(depaId,superDeapId)
	{

		
		

		orgDwr.getOrg(depaId,superDeapId,function(data)
		{

			setMenuValue(data);

			
		});	
		
	}
	
	function setMenuValue(data)
	{
	
		dwr.util.setValue('id',data.id);
		if(data.parentOrg!=null)
		{
			document.getElementById('parentOrgIdDIV').innerHTML=data.parentOrg.name;
			if(data.sysClientId==null)
				{
					dwr.util.setValue('clientId',data.parentOrg.sysClientId);
					document.getElementById("clientId").disabled=true;
				}
		}
		if(data.sysClientId!=null)
			{
			dwr.util.setValue('clientId',data.sysClientId);
			document.getElementById("clientId").disabled=true;
			}
		document.getElementById('levelName').innerHTML=data.orgLevel;
		dwr.util.setValue('value',data.value);
		dwr.util.setValue('name',data.name);
		dwr.util.setValue('level',data.orgLevel);
		dwr.util.setValue('remark',data.remark);
		dwr.util.setValue('parentOrgId',data.parentOrgId);
		
	}
	//		alert('<c:url value='/system/orgForm.jsp'/>?orgId='+orgId+'&parentOrgId='+parentOrgId);
	init(<%=request.getParameter("orgId")%>,<%=request.getParameter("parentOrgId")%>);
</script>

</body>
</html>
