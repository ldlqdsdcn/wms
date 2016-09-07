<%@ include file="/commons/taglib.jsp" %>
<%@page import="com.powere2e.reporttool.config.DB"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
      	<link rel="stylesheet" type="text/css" media="all"	href="../css/contentpage.css" />
		<link rel="stylesheet" type="text/css" media="all"	href="../css/listtable.css" />
  </head>
  
  <body>
      <table width="100%" cellpadding="0" align="center" >
					<tr>
						<td class="C_printline">
							<div class="C_bigtitle">
								<fmt:message key="emailaddress.title"/>
							</div>



						</td>
					</tr>
					<tr>
						<td>
							<div class="C_ico">
							
								
								&nbsp;&nbsp;
								<a href="emailForm.jsp?oper=new">
									<img src="../images/<fmt:message key="lan.code"/>/ico_new.gif"/>
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
    	<th  class="stev"><fmt:message key="emailaddress.username"/> </th>
    	<th  class="stev"><fmt:message key="emailaddress.address"/></th>
    	</tr>
    </thead>
    <tbody>
    <%java.util.List list=DB.getInstance().query("select * from emailaddress");
    	System.out.println(list.size());
    	for(int i=0;i<list.size();i++)
    	{
    	java.util.Map map=(java.util.Map)list.get(i);
    	 %>
    	 <tr class="<%=i%2==0?"odd":"even" %>">
    	 <td><a href="emailForm.jsp?oper=edit&id=<%=map.get("ID") %>"><%=map.get("USERNAME") %></a></td>
    	 
    	 <td><%=map.get("EMAIL") %></td>
    	
    	 </tr>
    	 
    	 <%} %>
    </tbody>
    </table>
    </td>
    </tr>
    </table>
    
  </body>
</html>
