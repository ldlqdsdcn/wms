
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ include file="/commons/taglib.jsp" %>

<%@page import="com.powere2e.reporttool.config.DB"%>

<% 
String path=request.getServletPath();

DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
request.setCharacterEncoding("utf-8");
String oper=request.getParameter("oper");
System.out.println("oper="+oper);
java.util.Map map=new java.util.HashMap();
	if(oper!=null)
	if(oper.equals("new"))
	{
		 session.setAttribute("contextpath",path);	
	}else if(oper.equals("delete"))
	{
		String id=request.getParameter("id");
		DB.getInstance().update("delete from scheduler where id="+id);
		pageContext.forward("schedulerList.jsp");
	}
	else if(oper.equals("edit"))
	{
		String id=request.getParameter("id");
		java.util.List list=DB.getInstance().query("select * from scheduler where id="+id);
		session.setAttribute("contextpath",path+"?oper=edit&id="+id);
		if(list.size()>0)
		{
			map.putAll((java.util.Map)list.get(0));
		}
		
	}else if(oper.equals("save"))
	{
	String id=request.getParameter("id");
	session.setAttribute("contextpath",path+"?oper=edit&id="+id);
	String name=request.getParameter("name");
	String classname=request.getParameter("classname");
	String interval=request.getParameter("interval");
	String active=request.getParameter("active");
	String description=request.getParameter("description");
	String bgndateS=request.getParameter("bgndate");
	String enddateS=request.getParameter("enddate");
	
	System.out.println("bgndateS="+bgndateS);
	java.util.Date bgnDate=null;
	java.util.Date endDate=null;
	try{
	bgnDate=df.parse(bgndateS);
	endDate=df.parse(enddateS);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	
	if(id.equals("")){
	java.sql.PreparedStatement prep=DB.getInstance().getPreparedStatement("insert into scheduler(NAME,CLASSNAME,INTERVAL,ACTIVE,BGNDATE,ENDDATE,DESCRIPTION)VALUES(?,?,?,?,?,?,?)");
	prep.setString(1,name);
	prep.setString(2,classname);
	prep.setInt(3,Integer.parseInt(interval));
	prep.setString(4,active);
	prep.setDate(5,new java.sql.Date(bgnDate.getTime()));
	prep.setDate(6,new java.sql.Date(endDate.getTime()));
	prep.setString(7,description);
	prep.execute();
	prep.close();
	prep=DB.getInstance().getPreparedStatement("select * from scheduler where name=? and CLASSNAME=? and INTERVAL=? and ACTIVE=? and BGNDATE=? and ENDDATE=? and DESCRIPTION=?");	
	prep.setString(1,name);
	prep.setString(2,classname);
	prep.setInt(3,Integer.parseInt(interval));
	prep.setString(4,active);
	prep.setDate(5,new java.sql.Date(bgnDate.getTime()));
	prep.setDate(6,new java.sql.Date(endDate.getTime()));
	prep.setString(7,description);
	java.sql.ResultSet rs=prep.executeQuery();
	while(rs.next())
	{
		map.put("ID",rs.getObject("ID"));
		map.put("NAME",rs.getObject("NAME"));
		map.put("CLASSNAME",rs.getObject("CLASSNAME"));
		map.put("INTERVAL",rs.getObject("INTERVAL"));
		map.put("ACTIVE",rs.getObject("ACTIVE"));
		map.put("BGNDATE",rs.getObject("BGNDATE"));
		map.put("ENDDATE",rs.getObject("ENDDATE"));
		map.put("DESCRIPTION",rs.getString("DESCRIPTION"));
	}
	rs.close();
	prep.close();
	}
	else
	{
	System.out.println("update");
	java.sql.PreparedStatement prep=DB.getInstance().getPreparedStatement("update scheduler set NAME=?,CLASSNAME=?,INTERVAL=?,ACTIVE=?,BGNDATE=?,ENDDATE=?,DESCRIPTION=? where id=?");
	prep.setString(1,name);
	prep.setString(2,classname);
	prep.setInt(3,Integer.parseInt(interval));
	prep.setString(4,active);
	
	prep.setTimestamp(5,new Timestamp(bgnDate.getTime()));
	
	prep.setTimestamp(6,new Timestamp(endDate.getTime()));
	prep.setString(7,description);
	prep.setInt(8,Integer.parseInt(id));
	prep.execute();
	prep.close();
	id=request.getParameter("id");
	java.util.List list=DB.getInstance().query("select * from scheduler where id="+id);
	if(list.size()>0)
		{
			map.putAll((java.util.Map)list.get(0));
		}
	}
	
	
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="all" href="css/contentpage.css" />
<link rel="stylesheet" type="text/css" media="all" href="css/listtable.css" />
<link rel="stylesheet" type="text/css" media="all" href="css/forms.css" />
<link rel="stylesheet" type="text/css" href="css/reporttool.css"/>
 
<style type="text/css">@import url(script/calendar/calendar-blue.css);</style>
<script type="text/javascript" src="script/calendar/calendar.js"></script>
<script type="text/javascript" src="script/calendar/lang/calendar_en_US.js"></script>
<script type="text/javascript" src="script/calendar/calendar-setup.js"></script>
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
   <form id="schedulerForm" action="schedulerForm.jsp" method="post">
   <input type="hidden" name="oper" value="save"/>
	<input type="hidden" name="id" value="<%=map.get("ID")!=null?map.get("ID"):"" %>"/>
	<table width="100%" cellpadding="0" align="left" height="auto">
	<tr><td class="C_printline"><div class="C_bigtitle"><fmt:message key="scheduler.title"/></div></td></tr>
	<tr><td>
		<table  class="edit_main" width="100%">
		<tr>
			<td class="C_table_list_1" width="20%"><fmt:message key="scheduler.name"/>:</td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="name" name="name" value="<%=map.get("NAME")==null?"":map.get("NAME") %>" /></td>
			<td class="C_table_list_1" width="20%"><fmt:message key="scheduler.interval"/></td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="interval" name="interval" value="<%=map.get("INTERVAL")==null?"":map.get("INTERVAL") %>" />
			<fmt:message key="scheduler.timeunit"></fmt:message>
			</td>
		</tr>
		<tr>
		<td class="C_table_list_1" width="20%"><fmt:message key="scheduler.classname"/>:</td>
		<td class="C_table_list_2" colspan='3'><input class="text large"  id="classname" name="classname" value="<%=map.get("CLASSNAME")==null?"":map.get("CLASSNAME") %>" /></td>
		</tr>
		<tr>
			<td class="C_table_list_1" width="20%"><fmt:message key="scheduler.bgndate"/>:</td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="bgndate" name="bgndate" value="<%=map.get("BGNDATE")==null?"":df.format(map.get("BGNDATE")) %>" /> 
			<input type="button" value="..." id="but1"/>
			<script type="text/javascript">
Calendar.setup(
{
inputField : "bgndate", // ID of the input field
ifFormat : "%Y-%m-%d %H:%M", // the date format
button : "but1", // ID of the button
showsTime:true
}
);
</script>
			</td>
			<td class="C_table_list_1" width="20%"><fmt:message key="scheduler.enddate"/></td>
			<td class="C_table_list_2" width="30%"><input class="text medium"  id="enddate" name="enddate" value="<%=map.get("ENDDATE")==null?"":df.format(map.get("ENDDATE")) %>" />
			<input type="button" value="..."  id="but2"/>
						<script type="text/javascript">
Calendar.setup(
{
inputField : "enddate", // ID of the input field
ifFormat : "%Y-%m-%d %H:%M", // the date format
button : "but2", // ID of the button
showsTime:true
}
);
</script>
			</td>
		</tr>
		<tr>
	<td  class="C_table_list_1" ><fmt:message key="scheduler.active"/></td>
	<td colspan="3" class="C_table_list_2">
	
	<input type="checkbox" <%="Y".equals(map.get("ACTIVE"))?"checked='checked'":"" %> value="Y" name='active'/>
	</td>	
		</tr>
		<tr>
	<td  class="C_table_list_1" ><fmt:message key="scheduler.description"/></td>
	<td colspan="3" class="C_table_list_2">
	<input type="text"  id="description"  class="text large" name="description" value="<%=map.get("DESCRIPTION")==null?"":map.get("DESCRIPTION") %>"/>
	</td>	
		</tr>
	
	
	</table>
		</td>
	</tr>
	<tr>
	<td class="C_table_list_1"  align="center">
		<div class="padbox_ico_2">
			
		<a href="schedulerForm.jsp?oper=new" id="new">
					<img src="images/<fmt:message key="lan.code"/>/ico_new.gif" alt="" />
				</a>&nbsp;&nbsp;
				
					<a href="javascript:save()">
						<img src="images/<fmt:message key="lan.code"/>/ico_save.gif"/>
					</a>&nbsp;&nbsp;
				
				<%if(map.get("ID")!=null){ %>
					<a href="schedulerForm.jsp?oper=delete&id=<%=map.get("ID") %>" onclick="javascript:if(!confirmDelete()) return false;" >
					    <img src="images/<fmt:message key="lan.code"/>/ico_delete.gif" />	
					</a>&nbsp;&nbsp;
				<%} %>
			<a href="schedulerList.jsp">
				<img src="images/<fmt:message key="lan.code"/>/ico_cancel.gif"/>
			</a>
				</div>
	</td>
	</tr>

	</table>
	</form>
  </body>
</html>
