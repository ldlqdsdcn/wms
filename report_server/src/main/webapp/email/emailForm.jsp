
<%@ include file="/commons/taglib.jsp" %>

<%@page import="com.powere2e.reporttool.config.DB"%>

<% 
String path=request.getServletPath();
request.setCharacterEncoding("utf-8");
String oper=request.getParameter("oper");
System.out.println("oper="+oper);
java.util.Map map=new java.util.HashMap();
	if(oper!=null)
	if(oper.equals("new"))
	{
		 session.setAttribute("contextpath",path);	
	}
	else if(oper.equals("delete"))
	{
		String id=request.getParameter("id");
		DB.getInstance().update("delete from emailaddress where id="+id);
		pageContext.forward("emailList.jsp");
	}
	
	
	else if(oper.equals("edit"))
	{
		String id=request.getParameter("id");
		java.util.List list=DB.getInstance().query("select * from emailaddress where id="+id);
		session.setAttribute("contextpath",path+"?oper=edit&id="+id);
		if(list.size()>0)
		{
			map.putAll((java.util.Map)list.get(0));
		}
		
	}else if(oper.equals("save"))
	{
	String id=request.getParameter("id");
	session.setAttribute("contextpath",path+"?oper=edit&id="+id);
	String username=request.getParameter("username");
	String email=request.getParameter("email");

	
	if(id.equals("")){
	java.sql.PreparedStatement prep=DB.getInstance().getPreparedStatement("insert into emailaddress(USERNAME,EMAIL)VALUES(?,?)");
	prep.setString(1,username);
	prep.setString(2,email);
	
	prep.execute();
	prep.close();
	java.util.List list=DB.getInstance().query("select * from emailaddress where username='"+username+"' and  email='"+email+"'");
	map=(java.util.Map)list.get(0);
	
	}
	else
	{
	System.out.println("update");
	java.sql.PreparedStatement prep=DB.getInstance().getPreparedStatement("update emailaddress set username=?,email=?  where id=?");
	prep.setString(1,username);
	prep.setString(2,email);
	prep.setInt(3,Integer.parseInt(id));
	prep.execute();
	prep.close();
	id=request.getParameter("id");
	java.util.List list=DB.getInstance().query("select * from emailaddress where id="+id);
	map.putAll((java.util.Map)list.get(0));
	
	}
	
	
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="all" href="../css/contentpage.css" />
<link rel="stylesheet" type="text/css" media="all" href="../css/listtable.css" />
<link rel="stylesheet" type="text/css" media="all" href="../css/forms.css" />
<link rel="stylesheet" type="text/css" href="../css/reporttool.css"/>


<script type="text/javascript">
	function save()
	{
	document.forms[0].submit();
	}
	function confirmDelete()
	{
	 return confirm("<fmt:message key='message.delete'/>");
	}
</script>
 </head>
 <body>
   <form id="emailForm" action="emailForm.jsp" method="post">
   <input type="hidden" name="oper" value="save"/>
	<input type="hidden" name="id" value="<%=map.get("ID")!=null?map.get("ID"):"" %>"/>
	<table width="100%" cellpadding="0" align="left">
	<tr><td class="C_printline"><div class="C_bigtitle"><fmt:message key="emailaddress.title"/></div></td></tr>
	<tr><td>
		<table  class="edit_main" width="100%">
		<tr>
			<td class="C_table_list_1" width="20%"><fmt:message key="emailaddress.username"/>:</td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="username" name="username" value="<%=map.get("USERNAME")==null?"":map.get("USERNAME") %>" /></td>
			<td class="C_table_list_1" width="20%"><fmt:message key="emailaddress.address"/></td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="email" name="email" value="<%=map.get("EMAIL")==null?"":map.get("EMAIL") %>" />
			</td>
		</tr>
		

	
	
	
	</table>
		</td>
	</tr>
	<tr>
	<td class="C_table_list_1"  align="center">
		<div class="padbox_ico_2">
			
		<a href="emailForm.jsp?oper=new" id="new">
					<img src="../images/<fmt:message key="lan.code"/>/ico_new.gif" alt="" />
				</a>&nbsp;&nbsp;
				
					<a href="javascript:save()">
						<img src="../images/<fmt:message key="lan.code"/>/ico_save.gif"/>
					</a>&nbsp;&nbsp;
				
				<%if(map.get("ID")!=null){ %>
					<a href="emailForm.jsp?oper=delete&id=<%=map.get("ID") %>" onclick="javascript:if(!confirmDelete()) return false;">
					    <img src="../images/<fmt:message key="lan.code"/>/ico_delete.gif" />	
					</a>
					
					&nbsp;&nbsp;
		<%} %>
			<a href="emailList.jsp">
				<img src="../images/<fmt:message key="lan.code"/>/ico_cancel.gif"/>
			</a>
				</div>
	</td>
	</tr>

	</table>
	</form>
  </body>
</html>
