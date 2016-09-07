<!--
@Author: leo
-->
<%@ include file="/commons/taglib.jsp" %>
<%@include file="/commons/realpath.jsp" %>
<%@ page import="java.sql.*,
		 		 com.powere2e.reporttool.ReportMonitor,
		 		 com.powere2e.reporttool.jasperprocessor.Cache,
		 		 com.powere2e.reporttool.datasource.DataSourceProvider,
		 		 com.powere2e.reporttool.jasperprocessor.ReportCompiler,
		 		 java.util.Properties,
		 		 java.io.File,
		 		 com.powere2e.reporttool.config.*
		 		 " errorPage="" %>

<% Email email=com.powere2e.reporttool.config.Config.getEmail();
   DataSource[] ds=com.powere2e.reporttool.config.Config.getDatasources();
   Prop[] props=com.powere2e.reporttool.config.Config.getProps();
   DataSource dataSource=ds[0];
   String reportHome=null;
   String cachesize=null;
   String logLevel=null;
   for(int i=0;i<props.length;i++)
   {
   	if(props[i].getKey().equals("ReportHome"))reportHome=props[i].getValue();
   	if(props[i].getKey().equals("CacheSize"))cachesize=props[i].getValue();
   	if(props[i].getKey().equals("LogLevel"))logLevel=props[i].getValue();
   }
   	
 %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Report Console</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
<link href="css/styleIE.css" type=text/css  media="all" rel='stylesheet'/>
      	<link rel="stylesheet" type="text/css" media="all"	href="css/contentpage.css" />
		<link rel="stylesheet" type="text/css" media="all"	href="css/listtable.css" />
  <script type='text/javascript' src='<%=request.getContextPath() %>/dwr/engine.js'></script>
  <script type='text/javascript' src='<%=request.getContextPath() %>/dwr/util.js'></script>
  <script type='text/javascript' src='<%=request.getContextPath() %>/dwr/interface/consoleAjax.js'></script>		
<script language="javascript">
 function getRadioValue(strName){
 
 		var radios = document.getElementsByName(strName);  
 		for(var i = 0;i<radios.length;i++){
        if(radios[i].checked)
        {
        return radios[i].value;
        }
        }
	}
	function disableInput()
	{
	 var inputs=document.getElementsByTagName('input');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	inputs[i].disabled='disabled';
	 }
	 
	}
	function enableInput()
	{
	 var inputs=document.getElementsByTagName('input');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	inputs[i].disabled='';
	 }
	 
	}
	
	function Compile()
	{
		var type=getRadioValue('radio');
		var reprotName=document.getElementById('ReportName').value;
		disableInput();
		dwr.util.setValue('completeLabel','<fmt:message key='console.compiling'/>');
		consoleAjax.compileReport(type,reprotName,function(data)
		{
			if(data=='Y')
			{
				alert("<fmt:message key='console.Compile.success'/>");
				dwr.util.setValue('completeLabel','');
				enableInput();
			}
			else
			{
				alert("<fmt:message key='console.Compile.error'/>");
				dwr.util.setValue('completeLabel','');
				enableInput();
			}
		}
		);
	}
	function cleanCache()
	{
		consoleAjax.cleanCache(
			function(data)
			{
				alert("<fmt:message key='console.cleanCache'/>"+data);
			}
		);
	}
	
	function connTest()
	{
		consoleAjax.testConnection(
			function(data)
			{
				if(data=='Y')
				{
				alert("<fmt:message key='console.conn.succ'/>");
				}
				else
				{
				alert("<fmt:message key='console.conn.error'/>");
				}
			}
		);
		
	}
	
	function openMessage()
	{
		var width=550;
		var height=350;
		var left=(screen.availWidth-400)/2;
		 var top=(screen.availHeight-300)/2;
		window.open("./UpLoad.jsp", "confirm", 'resizable=1,status=0,toolbar=0,scrollbars=1,menubar=0,location=0,directories=0,alwaysRaised=1,dependent=1,width='+width+',height='+height+',left='+left+',top='+top);
	}
	
	function changedatasource()
	{
	    if(console.dataSourceType.value == 'jdbc')
	    {
	        document.getElementById("ds_td").style.display="none";
	        document.getElementById("ds_td2").style.display="none";
	        document.getElementById("driver_td").style.display="";
	        document.getElementById("driver_td2").style.display="";
	        document.getElementById("url_td").style.display="";
	        document.getElementById("url_td2").style.display="";
	        document.getElementById("username_td").style.display="";
	        document.getElementById("username_td2").style.display="";
	        document.getElementById("password_td").style.display="";
	        document.getElementById("password_td2").style.display="";
	    }
	    else
	    {
	        document.getElementById("ds_td").style.display="";
	        document.getElementById("ds_td2").style.display="";
	        document.getElementById("driver_td").style.display="none";
	        document.getElementById("driver_td2").style.display="none";
	        document.getElementById("url_td").style.display="none";
	        document.getElementById("url_td2").style.display="none";
	        document.getElementById("username_td").style.display="none";
	        document.getElementById("username_td2").style.display="none";
	        document.getElementById("password_td").style.display="none";
	        document.getElementById("password_td2").style.display="none";
	    }
	            
	}
	
</script>
</head>

<body style="margin-top: 5px;" onload="javascript:changedatasource()">
<table style="width: 100%">
<tr>
<td align="center">

<form name="console" method="post" action="ReportMonitor" >
<input type="hidden" name="key" value="<%=dataSource.getKey() %>"/>
<input type="hidden" name="pageCommand" value=""/>
<input type="hidden" name="connmessage" value="<%=DataSourceProvider.getInstance().getConnMessage()%>" />

<table id="table1"   style="width: 900px;"  border=0>
<tbody>
  

  <tr>
    <td colspan="2" bgcolor='#ffffff' height=18><img src="images/greyStrip.gif" width="100%"  height="11px;"/></td>
	
  </tr>
  <tr>
  <td colspan="2">  <div id="tabs" class="C_twotitle_oranger"><fmt:message key="console.setParameters"/></div></td>
  </tr>
  <tr>
    <td valign=top align=left  colspan='2' style="width: 100%;">
	
  
      <table id='table5' cellspacing='0' cellpadding='0' width="100%" align='center' border='0'>
        <tbody>
        
        <tr style="height: 1px;">
          <td bgcolor=#d1d1d1 colspan=2></td>
        </tr>
        <tr style="height: 10px;">
          <td style="PADDING-BOTTOM: 5px; PADDING-TOP: 5px"></td>
        </tr>
        </tbody>
      </table>
      </td></tr>
    <tr>
    <td valign='top' align='left'  colspan='2' style="width: 100%;">  
      <table id='table6' cellspacing='0' cellpadding='0' width="100%" align='center' bgcolor='#ffffff' border='0'>
        <tbody>
        <tr bgcolor='#ffffff'>
          <td width=0></td>
          <td valign=top align="right"> </td></tr>
        <tr>
         
          <td width=15% class="C_table_list_1"><fmt:message key="console.typeofDataSource"/></td>
          <td width=35% class="C_table_list_2">
          <%
            String datasourcetype = ReportMonitor.getProperties().getProperty("dataSourceType");
            boolean isjdbc = true; 
            if(datasourcetype != null && datasourcetype.equals("jndi"))
                isjdbc = false;
          %>
            <select name="dataSourceType" class="text" onchange="javascript:changedatasource();">
                <option value="jdbc" <%=dataSource.getType().equals("jdbc")?"selected":"" %>>JDBC Connection</option>
                <option value="jndi" <%=dataSource.getType().equals("jndi")?"selected":"" %>>JNDI DataSource</option>
            </select>	  	  </td>
          
          <td width=15% class="C_table_list_1" id="ds_td"><fmt:message key="console.DataSource"/></td>
          <td width=35% id="ds_td2" class="C_table_list_2"><input name="dataResource" class="text large" value="<%=dataSource.getJNDIName()%>"/></td>
          </tr>
        <tr style="height: 10px;"><td colspan="4"></td></tr>
        
        <tr>
         
          <td width=15% id="driver_td" class="C_table_list_1"><fmt:message key="console.JDBCDriver"/> </td>
          <td width=35% id="driver_td2" class="C_table_list_2"><input name="driverClass" class="text large" value="<%=dataSource.getJdbcdriver()%>"/></td>
          
          <td width=15%  id="url_td" class="C_table_list_1"><fmt:message key="console.JDBCURL"/></td>
          <td width=35% id="url_td2" class="C_table_list_2"><input name="url" class="text large" value="<%=dataSource.getJdbcurl()%>"/></td>
         </tr>
        <tr style="height: 10px;"><td colspan="4"></td></tr>
        <tr>
          
          <td  id="username_td" class="C_table_list_1"><fmt:message key="console.UserName"/></td>
          <td  id="username_td2" class="C_table_list_2"><input name="userName" class="text medium" value="<%=dataSource.getUsername()%>"/></td>
          
          <td  id="password_td" class="C_table_list_1"><fmt:message key="console.Password"/></td>
          <td  id="password_td2" class="C_table_list_2"><input name="passWord" class="text medium" value="<%=dataSource.getPassword()%>"/></td>
         
		</tr>
		<tr style="height: 10px;"><td colspan="4"></td></tr>
		<tr style="height: 1px;">
          <td bgcolor=#d1d1d1 colspan=4></td>
        </tr>
		<tr style="height: 10px;"><td colspan="4"></td></tr>
        <tr>
        
          <td  class="C_table_list_1"><fmt:message key="console.ReportHome"/></td>
          <td class="C_table_list_2"><input name="Report_Home" class="text medium" value="<%=reportHome%>"/></td>
       
          <td></td>
          <td></td>
        
		</tr>
		<tr style="height: 15px;"><td colspan="4"></td></tr>
		<tr>
          
          <td  class="C_table_list_1"><fmt:message key="console.CacheSize"/></td>
          <td class="C_table_list_2"><input name="Cache_Size" class="text medium" value="<%=cachesize%>"/></td>
          
          <td  class="C_table_list_1"><fmt:message key="console.LogLevel"/></td>
          <td class="C_table_list_2">
            <select name="LogLevel">
                <option value="0" <%=logLevel.equals("0")?"selected":"" %>>DEBUG</option>
                <option value="1" <%=logLevel.equals("1")?"selected":"" %>>INFO</option>
                <option value="2" <%=logLevel.equals("2")?"selected":"" %>>WARN</option>
                <option value="3" <%=logLevel.equals("3")?"selected":"" %>>ERROR</option>
                <option value="4" <%=logLevel.equals("4")?"selected":"" %>>FATAL</option>  
                <option value="5" <%=logLevel.equals("5")?"selected":"" %>>OFF</option> 
            </select>     
          </td>
         
		</tr>
		<tr style="height: 20px;"><td colspan="4"></td></tr>
		
		<tr style="height: 1px;"><td colspan="4" bgcolor=#d1d1d1></td></tr>
		
		<tr style="height: 20px;"><td colspan="4"></td></tr>
        <tr>
          
          <td class="C_table_list_1" id="username_td"><fmt:message key="console.UserName"/></td>
          <td  id="username_td3" class="C_table_list_2"><input name="mailUser" class="text medium" value="<%=email.getMailuser()%>"/></td>
     
          <td  class="C_table_list_1" id="password_td"><fmt:message key="console.MailPassword"/></td>
          <td  id="password_td3" class="C_table_list_2"><input name="mailPassword" type="password" class="text medium" value="<%=email.getMailpassword()%>"/></td>
        
		</tr>
		<tr style="height: 10px;"><td colspan="4"></td></tr>
		<tr>
         
          <td  class="C_table_list_1" id="username_td"><fmt:message key="console.MailSMTPHost"/></td>
          <td  id="username_td4" class="C_table_list_2"><input name="mailSMTPHost" class="text medium" value="<%=email.getEmailserver()%>"/></td>
        
          <td class="C_table_list_1" id="password_td"><fmt:message key="console.MailFromAddress"/></td>
          <td class="C_table_list_2" id="password_td4"><input name="mailFromAddress"  class="text medium" value="<%=email.getMailfromaddress()%>"/></td>
        
		</tr>
		<tr style="height: 10px;"><td colspan="4"></td></tr>
		<tr>
       
          <td  class="C_table_list_1" id="username_td"><fmt:message key="console.MailSubject"/></td>
          <td  id="username_td5" class="C_table_list_2"><input name="mailSubject" class="text medium" value="<%=email.getMailsubject()%>"/></td>
		
         <td align=left class="C_table_list_1"><fmt:message key="console.MailBody"/></td>
         <td  id="password_td5" class="C_table_list_2"><input name="mailBody"  class="text medium" value="<%=email.getMailbody()%>"/></td>
         
		 </tr>
		<tr style="height: 15px;"><td colspan="4"></td></tr>
		<tr style="height: 15px;"><td colspan="4"></td></tr>
        <tr>
          <td colspan="3"></td>
          
          <td align="right"><input type="button" class="button"  value="<fmt:message key="console.CleanCache"/>" onclick="javascript:cleanCache();"/>
          &nbsp;<input type="button" class="button"  value="<fmt:message key="console.Save"/>" onclick="javascript:console.pageCommand.value='save';console.submit();"/>
          &nbsp;<input type="button" class="button"  value="<fmt:message key="console.connTest"/>" onclick="javascript:connTest();"/></td>
		</tr>
	  </tbody>
	  </table>
	  
	 </td></tr>
	     <tr>
    <td valign=top align=left  colspan=2 style="width: 100%;">  
	  <div id="tabs"  class="C_twotitle_oranger">
	  <fmt:message key="console.ARIRH"/>	  </div></td></tr>
		     <tr>
    <td valign='top' align='left'  colspan='2' style="width: 100%;">
	  <table id='table5' cellspacing='0' cellpadding='0' width="100%" align='center' border='0'>
        <tbody>
        
        <tr  style="height: 1px;">
          <td bgcolor='#d1d1d1' colspan='2'></td></tr>
        <tr  style="height: 10px;">
          <td style="PADDING-BOTTOM: 5px; PADDING-TOP: 5px"></td>
		</tr>
		</tbody>
	  </table>
      <table id='table7' cellspacing='0' cellpadding='0' width="100%" align='center' border='0'>
        <tbody>
        <tr bgcolor='#ffffff'>
          <td width=0></td>
          <td valign=top></td>
        </tr>
       <tr  style="height: 10px;"><td></td></tr>
       <tr>
        	<td width=3%></td>
        	<td colspan="4" align=left nowrap="nowrap">
        	<table>
        	<tr>
        	<td><input id="radio1" type="radio" name="radio" checked="checked" value="ONE"/><fmt:message key="console.ReportName"/></td>
        	<td>&nbsp;<input id="ReportName" name="ReportName" class="text large" value=""/></td>
        	
        	</tr>
        	</table>
        	</td>
        </tr>
        <tr  style="height: 10px;"><td></td></tr>
        <tr>
          <td width=3%></td>
          <td width=40% align=left>&nbsp;<input id="radoi2" type="radio" name="radio" value="ALL"/><fmt:message key="console.All"/> </td>
          </tr>
          
          <tr  style="height: 10px;"><td></td></tr>
        
		<tr>
          <td width=3%></td>
          <td width=40% align=center></td>
          
          <td width=7%></td>
          <td width=15% align=center>
          <label id="completeLabel" style="color: red;"></label>
          </td>
          <td width=15% align="right"><input type="button" class="button" value="<fmt:message key="console.Upload"/>" onclick="javascript:openMessage();"/></td>
          <td width=20% align="right">
<%--          <input type="button" class="button" value=<fmt:message key="console.Compile"/> onclick="javascript:console.pageCommand.value='recompile';--%>
<%--          																						   console.submit();"/>--%>
<input type="button" class="button" value="<fmt:message key="console.Compile"/>" onclick="javascript:Compile();"/>
</td>
		</tr>
        <tr  style="height: 10px;"><td></td></tr>
         <tr><td  class="C_table_list_1" colspan="2" style="text-align: left;padding-left: 50px;"><fmt:message key="console.TemplateFile"></fmt:message></td></tr>
   
   
   
   
        <tr  style="height: 10px;"><td></td></tr>
          <%
         java.util.List listxml=new java.util.ArrayList();
         java.util.List listxls=new java.util.ArrayList(); 
          File parent = new File(Config.reportHome);
          try{
          if(parent.isDirectory())
          {
            File child[] = parent.listFiles();
            for(int i=0;i<child.length;i++)
            {
            	if(child[i].isFile())
            	{
            		String filename = child[i].getName();
            		int index = filename.lastIndexOf(".");
            		filename = filename.substring(index);
            		if(".xml".equalsIgnoreCase(filename) || ".jrxml".equalsIgnoreCase(filename))
            		{
            		listxml.add(child[i].getName());
            		}
            		else if(".xls".equalsIgnoreCase(filename))
            		{
            		listxls.add(child[i].getName());
            		}
            	}
            }
           }
           }
           catch(Exception e)
           {
           System.out.println("a---------------n error produce");
           }
           %>
           
           <tr>
           <td></td>
           <td style="text-align:center;" colspan="5">
           <%
           try{
           
           int size=listxml.size()>listxls.size()?listxml.size():listxls.size();
           if(size>0){ %>
           		<table class="table">

           			<tbody>
    <tr>
        <td  style="height: 30px;border: 1px solid #e0e0e0;
                    text-align: left;
                    font-size: 1em;
                    font-weight: bold;
                    background: #d7e9f5;"><fmt:message key="console.xmlTemplate"/></td>
        <td  style="height: 30px;border: 1px solid #e0e0e0;
                    text-align: left;
                    font-size: 1em;
                    font-weight: bold;
                    background: #d7e9f5;"><fmt:message key="console.xlsTemplate"/></td>

    </tr>
           			<%for(int i=0;i<size;i++)
           			{%>
           			
           			<tr class='<%=i%2==0?"odd":"even" %>'>
           			<td><%=i<listxml.size()?listxml.get(i):"" %></td>
           			<td><%=i<listxls.size()?listxls.get(i):"" %>
           			</td>
           			
           			</tr>
           			<%} %>
           			</tbody>
           		</table>
           
           		<%} 
           		}catch(Exception e)
           		{
           		System.out.println("-------------------error~~~~~~~~~~~~");
           		}
           		%>
           </td>
           
           </tr>
   
   
   
   
   
   
   
   
   
   
      </tbody>
	  </table>
	  
	</td>
	</tr>
</tbody>
</table>  
	  

<table id=table1 cellspacing=0 cellpadding=0 style="width: 900px;" bgcolor=white border=0 >
<tbody>
<%

			//ReportCompiler rc = new ReportCompiler();
			String message = ReportCompiler.getCompileMessage();
			System.out.println("1111111111111"+message);
			if(message != null)
			{%>
			<tr >
				<td>
					<font color="red"><%out.print("Compile Exception :  "+message);%></font>
				</td>
			</tr>
		<%}%>
  <tr>
    <td colspan=6><img src="images/greyStripBig.gif" width="100%" height="35px;"/></td>
  </tr>
  <tr bgcolor=#386d9f style="height: 18px;">
    <td colspan=5>&nbsp;</td>
  </tr>
  <tr  style="height: 2px;">
    <td></td>
  </tr>
  <tr>
    <td align=right><font color=#386d9f>&nbsp;Copyright 2006, All Rights Reserved, Powere2e</font></td>
  </tr>
</tbody>
</table>

</form>


</td>
</tr>
</table>
</body>
</html>
