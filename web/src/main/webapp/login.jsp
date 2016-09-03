
<%@page import="java.util.Locale"%>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<c:if test="${param.language!=null}">
	<fmt:setLocale value="${param.language}" scope="session"/>
</c:if>
<%
if(session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session")!=null)
{
//	System.out.println("session.getAttribute(\"javax.servlet.jsp.jstl.fmt.locale.session\")="+session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
	com.opensymphony.xwork2.ActionContext.getContext().setLocale((Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
	com.delmar.core.web.bean.UserResource ur=new com.delmar.core.web.bean.UserResource( (Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
	session.setAttribute("resource", ur);
	session.setAttribute("WW_TRANS_I18N_LOCALE", session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
	session.setAttribute("currentlanguageoriginal",((Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session")).toLanguageTag());
	session.setAttribute("currentlanguagelowercase",((Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session")).toLanguageTag().toLowerCase());	
	request.getLocale().setDefault((Locale)session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session"));
}
else
{
	com.delmar.core.web.bean.UserResource ur=new com.delmar.core.web.bean.UserResource( request.getLocale());
	session.setAttribute("resource", ur);
	session.setAttribute("WW_TRANS_I18N_LOCALE", request.getLocale());
	session.setAttribute("currentlanguageoriginal",request.getLocale().toLanguageTag());
	session.setAttribute("currentlanguagelowercase",request.getLocale().toLanguageTag().toLowerCase());	
}

//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName=(String)request.getAttribute("username");
if(userName==null)
{

Cookie cookie[]=request.getCookies();
if(cookie!=null)
		for(int i=0;i<cookie.length;i++)
		{
			if(cookie[i].getName().equals("userName"))
			{
				userName=cookie[i].getValue();
				break;
			}
		}

}
//System.out.println("request.getLocale()="+request.getLocale());
%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="delmar16.png" type="image/x-icon"> 
<title><delmar:message key="main.head.title"/></title>
<link href="css/style.css" rel="stylesheet" type="text/css">


<script type="text/javascript" src="js/ea.validate.js"></script>
<script type="text/javascript">

	    function validateForm() {
        var userName=document.getElementById('userName');
        var password=document.getElementById('password');

        return true;
      }
	    

</script>
<style>
td{
font-weight: bold;
}
</style>
</head>

<body>

<BR><BR><BR><BR><BR>
<CENTER>

<FORM name=loginform action='<c:url value="/userLogin.action"></c:url>' method=post onsubmit="return validateForm()" autoComplete='off'>
<TABLE 
style="BORDER-RIGHT: #6483b9 1px solid; BORDER-TOP: #6483b9 1px solid; BORDER-LEFT: #6483b9 1px solid; BORDER-BOTTOM: #6483b9 1px solid" 
height="50%" width="90%">
<TBODY>
<TR vAlign="middle">
<TD 
style="BORDER-RIGHT: #6483b9 1px solid; BORDER-TOP: #6483b9 1px solid; BORDER-LEFT: #6483b9 1px solid; BORDER-BOTTOM: #6483b9 1px solid"><IMG 
src="images/left.png"> </TD>
<TD align="center" width="100%">
<TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0>
<TBODY>
<TR bgColor=#174785 height=27>
<TD width="100%"></TD>
<TD 
style="BORDER-RIGHT: #6483b9 1px solid; BORDER-TOP: #6483b9 1px solid; BORDER-LEFT: #6483b9 1px solid; BORDER-BOTTOM: #6483b9 1px solid" 
align=right><IMG src="images/delmarLogo.png" style="margin: 5px"></TD></TR>
<TR>
<TD colSpan=2 height=290>
<TABLE width=200 align=center border=0>
<TBODY>
<TR>
<TD vAlign=middle align=center height=100 class="pageTitle"><fmt:message key="login.memberlogin"/></TD></TR>
<TR>
<TD height="30%">
<TABLE height=100 width=348 align=center>
<TBODY>
<TR>
<TD align=center width=100 height=25><LABEL><fmt:message key="login.userName"/></LABEL></TD>
<TD align=left width=250><input  style="WIDTH: 200px;" name="username"  id="userName" value="<%=userName==null?"":userName %>"></TD></TR>
<TR>
<TD align="center" width=100 height=25><LABEL><fmt:message key="login.password"/> </LABEL></TD>
<TD align=left><input  style="WIDTH: 200px" type='password'  name='password'  id="password"> </TD>
</TR>
<TR>
<TD>
</TD>
<TD align=left colSpan=2 height=31><input class="CButtonEnableLogin" type=submit  value="<fmt:message key='login.login'/>"></TD>
</TR>
<tr>
<td >


</td><td align="left">
  <fmt:message key="login.language"></fmt:message> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="lookup.jsp"><fmt:message key="login.forget"/></a></td><td></td>
</tr>

</TBODY></TABLE></TD></TR>

<TR>
<TD>
<table width="100%">

<tr>

<td colspan="3"  align="center" class="subtext">
Support Hotline 86-0532-66707118
</td>
</tr>
</table>

</TD>
</TR>
<TR>
<TD height="30%"><table cellspacing="5">
	<tr>
		<td valign="middle" height="140" width="79">
		<img border="0" src="images/ie.png" style="float:left">
		<img border="0" src="images/firefox.png"></td>
		<td valign="middle">
			<p align="left" class="subtext">
			请使用Internet Explorer 7 或 Firefox 2.0 以上版本的浏览器访问本网站 <br> 从<a href="http://www.microsoft.com">Internet Explorer 7</a> 或 <a href="http://www.mozilla.com/firefox/"> Firefox </a>进行下载
			</p>
		</td>
	</tr>
</table></TD></TR></TBODY></TABLE></TD></TR>
<TR>
<TD style="color: red;">
<s:fielderror />  
<s:actionmessage/>
<s:actionerror/>
<c:out value="${msg}"></c:out>


</TD>
</TR></TBODY></TABLE></TD></TR></TBODY></TABLE></FORM></CENTER>
<script language="javascript">

	if(document.getElementById('userName').value=='')
	{
      document.getElementById('userName').focus();
     }
      else
      {
      document.getElementById('password').focus();
      }
     
</script>
</body>
</html>
