<%@ include file="/commons/taglib.jsp" %>
<%@include file="/commons/realpath.jsp" %>
<%@page import="com.powere2e.reporttool.config.DB"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
   		<link rel="stylesheet" type="text/css" media="all"	href="css/contentpage.css" />
		<link rel="stylesheet" type="text/css" media="all"	href="css/listtable.css" />
  </head>
  
  <body>
  <table width="100%" cellpadding="0" align="center" >
					<tr>
						<td class="C_printline">
							<div class="C_bigtitle">
								<fmt:message key="scheduler.title"/>
							</div>



						</td>
					</tr>
					<tr>
						<td>
							<div class="C_ico">
							
								
								&nbsp;&nbsp;
								<a href="schedulerForm.jsp?oper=new">
									<img src="images/<fmt:message key="lan.code"/>/ico_new.gif"/>
								</a>
								&nbsp;&nbsp;
								

							</div>
						</td>
					</tr>
					<tr>
					<td style="text-align: center;">
    <table  class="table">
    	<thead>
    	<tr>
    	<th  class="stev"><fmt:message key="scheduler.name"/></th>
    	<th  class="stev"><fmt:message key="scheduler.interval"/></th>
    	<th  class="stev"><fmt:message key="scheduler.classname"/></th>
    	<th  class="stev"><fmt:message key="scheduler.active"/></th>
    	<th  class="stev"><fmt:message key="scheduler.bgndate"/></th>
    	<th  class="stev"><fmt:message key="scheduler.enddate"/></th>
    	</tr>
    	</thead>
    	<tbody>
    	<%java.util.List list=DB.getInstance().query("select * from scheduler");
    	System.out.println(list.size());
    	for(int i=0;i<list.size();i++)
    	{
    	java.util.Map map=(java.util.Map)list.get(i);
    	 %>
    	 <tr class="<%=i%2==0?"odd":"even" %>">
    	 <td><a href="schedulerForm.jsp?oper=edit&id=<%=map.get("ID") %>"><%=map.get("NAME") %></a></td>
    	 
    	 <td><%=map.get("INTERVAL") %></td>
    	 <td><%=map.get("CLASSNAME")%></td><td><%=map.get("ACTIVE")%></td><td><%=map.get("BGNDATE")%></td><td><%=map.get("ENDDATE")%></td>
    	 </tr>
    	 
    	 <%} %>
    	</tbody>
    </table>
    </td></tr></table>
  </body>
</html>
