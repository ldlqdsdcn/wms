<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.effect.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script> 

</head>

<body>
<s:form id="editForm" action="changeLog_view" namespace='/core' theme="simple">
<s:hidden id="id" name="changelog.id"/>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="changelog.location" /></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			
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
               <td  width="20%"><delmar:message key="changelog.column.tablename" /></td>		
				<td  width="30%">
				<s:property value="changelog.table.name"/>
						
				</td>		
               	<td width="20%">
               	<delmar:message key="changelog.column.classname" />
               	</td>
               	<td  width="30%">
               	<s:property value="changelog.table.className"/>
               
               	</td>
               
				</tr>
				<tr  class="query_two">
               <td  width="20%"><delmar:message key="changelog.column.busname" /></td>		
				<td  colspan="3" >
				
               	<s:property value="changelog.table.tableNameTr"/>
               
               	</td>
               
				</tr>
					
						 <tr  class="query_one">
               <td  width="20%">
						<delmar:message key="changelog.column.key" />
				</td>		
				<td width="30%">
						<s:property value="changelog.pk"/>
				</td>
				<td  width="20%"><delmar:message key="changelog.column.buskey" /></td>		
				<td width="30%">
						<s:property value="changelog.buPk"/>
				</td>		
            </tr>
          
            <tr class="query_two">
            <td colspan="2" style="padding-left: 20px;text-align: left;">
           <s:submit method="viewAll"  value="%{#session.resource.get('changelog.button.showall')}"   cssClass="input_submit"  />
           <s:submit method="view"   value="%{#session.resource.get('changelog.button.showone')}"    cssClass="input_submit"/>
            </td><td colspan="2"></td>
            </tr>
            <tr>
            <td colspan="4">
              		            <table width="100%" cellpadding="5" cellspacing="0" >
              <tr> 
                <td align="center">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
            
            <table id="columnTable" class="table" border="0"  cellpadding="0" cellspacing="0">
            	<thead>
            	<%Map headerMap=(Map)request.getAttribute("headerMap"); 
            	   java.util.Set<String> headerset=  headerMap.keySet();
            	   for(String key:headerset)
            	   {
            	%>
            	<th><%=headerMap.get(key) %></th>
            	<%} %>
            	

            	</thead>
            	<tbody id="detailtbody">
            	<%List<Map> bodyList=(List)request.getAttribute("bodyList");
            	System.out.println("bodyList.size()="+bodyList.size());
            	for(int i=0;i<bodyList.size();i++)
            	{%>
            	 	<tr  class="<%=i%2==0?"even":"odd" %>">
            	 	  <%  
            	 	  Map bodyMap=bodyList.get(i);
            	 	  for(String key:headerset){%>
            	 	  <td><%=bodyMap.get(key) %></td>
            	 	  <%} %>
            	 	
            	 	</tr>
            	<%	
            	}
            	%>
              
              	
              	</tbody>
            </table>
            </td></tr></table> </td>
                </tr></table>
            </td>
            </tr>	
			<tr>
                <td colspan="4" class="td_page_right">
               			
						
						
						
						<input onclick="window.location='<c:url value="/core/changeLogs.jsp"/>'"  type="button" value="返回"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <label style="color: red">
                	<%out.println(session.getAttribute("msg")==null?"":session.getAttribute("msg"));
                		session.removeAttribute("msg");
                	%>
                </label>
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>







</body>
</html>
