<!--
@Author: leo
-->
 
<%@ include file="/commons/taglib.jsp" %>
<% session.setAttribute("contextpath","/Console.jsp"); %>
<c:choose>
	<c:when test="${param.locale=='en_US' }">
		<fmt:setLocale value="en_US" scope="session"/>
	</c:when>
	<c:otherwise>
		<fmt:setLocale value="zh_CN" scope="session"/>
	</c:otherwise>
</c:choose>
<fmt:setBundle basename="ApplicationResources" var="text" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Login</title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
<meta content="MSHTML 6.00.2900.2802" name='GENERATOR'/>
<link href="css/styleIE.css" type='text/css' rel='stylesheet'/>
<script type="text/javascript">
	
    function changeLan()
    {
    		document.all['name'].value = ' ';
    	    document.all['password'].value = ' ';
    	 	login.action="login.jsp?locale=" + login.LanguageBox.options[login.LanguageBox.selectedIndex].value;
    		login.submit();
    		return;
    }

</script>
</head>
<body style="margin-top: 5px;">
<table style="width: 100%"><tr>
<td align="center">
<jsp:useBean id="r" scope="session" class="com.powere2e.reporttool.ReportMonitor" />
<%

    
  String name=request.getParameter("name");
  String password=request.getParameter("password");
  System.out.println("the name is :"+name);
  System.out.println("the password is :"+password);
  if(name != null && !name.trim().equalsIgnoreCase(""))
  {
  	String m = r.logging(name,password);
  	if(m.equalsIgnoreCase("x"))
  	{
  		out.print("user not exist,please login reply!");
  	}
  		
  	else if(m.equalsIgnoreCase("y") )
  	{
  		out.print("password error,please input reply!");
  	}
  	else
  	{
  		session.setAttribute("n",m);
  		session.setAttribute("username",name);
  		session.setAttribute("password",password);
  		session.setAttribute("contextpath","/Console.jsp");	
  		pageContext.forward("main.jsp");
  	}
        
  }
   
%>

<form name="login" method="post">
<table id=Table1 style="height: 538px;" cellspacing=0 cellpadding=0 style="width: 900px;" bgcolor=white border=0>
<tbody>
  <tr><td colspan=5><img src="images/greyStrip.gif" width="100%" height="11px;" /></td></tr>
  
  <tr>
  	<td valign=top  bgcolor=white></td>
    <td>
		<table cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" border="0" width="50%" align="center">
          	 <tr bgcolor="#386d9f" style="height: 27px;">
            	<td bgcolor=#6098c0 colspan=2><font color=#ffffff><b>&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="login.login"/></b></font></td>
				<td align="right"></td>
          	</tr>
          	
    	    <tr>
    	    <td>
		    </td>
		</tr>
		</table>
		    
		    <table width="50%" border="1s" align="center">
			    <tr bordercolor="#FFFFFF">
				    <td align="right" style="width: 20%">
				    	<label> <fmt:message key="login.username"/> :</label> 
			        </td>
			        <td align="left"  style="width: 30%">
			        	<input type="text" name="name" size="20"/>
				    </td>
				</tr>
				<tr bordercolor="#FFFFFF">
					<td align="right">
						<label> <fmt:message key="login.pass"/> :</label>
					</td>
					<td align="left">
						<input type="password" name="password" size="20"/>
					</td>
				</tr>
				<tr  bordercolor="#FFFFFF"> 
					<%
									String locale=request.getParameter("locale");
									if(locale==null||locale.equals(""))
									locale=request.getLocale().toString();
					
									String chinese_checked = "zh_CN".equals(locale)?"selected":"";
									String english_checked = "";
									if(chinese_checked.equals("")) english_checked = "selected";			
									
								%>
					 
					<td align="right">
					<label>
					 <fmt:message key="login.language"/> :</label>
					</td><td align="left">
					<select name="LanguageBox" id="LanguageBox"  onChange="javascript:changeLan();">
					<option value="zh_CN" <%= chinese_checked%>><fmt:message key="lan.Chinese"/></option>
					<option value="en_US" <%= english_checked%>><fmt:message key="lan.English"/></option>
					</select> </td>
					</tr>
				<tr bordercolor="#FFFFFF">
					<td></td>
					<td height="39" colspan="2" align="left" >
						<input type="submit" class="button"  value="<fmt:message key="login.login"/>"/>
					</td>
			    </tr>
			</table>
			
		
		</td>
	</tr>
   

</tbdoy>
</table>
<table id=Table9 cellspacing=0 cellpadding=0 style="width:900px;" bgcolor=white border=0>
<tbody>
  <tr>
    <td colspan=5><img src="images/greyStripBig.gif" width="100%" height="35px;"/></td>
  </tr>
  
  <tr bgcolor=#386d9f style="height: 18px;">
    <td colspan=5>&nbsp;</td>
  </tr>
  
  <tr style="height: 2px;">
    <td></td>
  </tr>
  
  <tr>
    <td align=right><font color="#386d9f">&nbsp;Copyright 2006, All Rights Reserved, Powere2e</font></td>
  </tr>
</tbdoy>
</table>
</form>

</td></tr>
</table>
</body>
</html>
