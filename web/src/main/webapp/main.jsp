<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<%@ page import="com.delmar.utils.ProDateUtil" %>
<c:choose>
	<c:when test="${user.preferLanguageId=='en_US' }">
	<fmt:setLocale value="en_US" scope="session"/>
	</c:when>
	<c:when test="${user.preferLanguageId=='zh_CN' }">
	<fmt:setLocale value="zh_CN" scope="session"/>
	</c:when>
</c:choose>

<%
	//set the current date of server
	String currentServerDate=ProDateUtil.getCurrentSysShortDateStr();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><fmt:message key="main.head.title"></fmt:message></title>
<link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value='/css/delmar.css'/>" type="text/css"/>
<link rel="shortcut icon" href="<c:url value='/delmar16.png'/>" type="image/x-icon">

<script type="text/javascript" src="js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<link rel="Stylesheet" href="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<link href="js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>


<script type='text/javascript' src='js/dm/delmar.js'></script>


<style type="text/css">
BODY {
	MARGIN: 0px;padding: 0px;
}
TABLE{
	border-width:0px;
}
a{text-decoration: none;
}

</style>

<script type="text/javascript">

var timer=setTimeout('refreshSystemTime()',200);
function refreshSystemTime() 
{	thistime= new Date(); var hours=thistime.getHours();
var minutes=thistime.getMinutes();	var seconds=thistime.getSeconds();if (eval(hours) <10) {hours='0'+hours;}	
if (eval(minutes) < 10) {minutes='0'+minutes;}	if (seconds < 10) {seconds='0'+seconds;}	
thistime = hours+':'+minutes+':'+seconds;	
if (getBrowserInfo()=="FF")
  document.all.systemDate.textContent=thistime;
else
  document.all.systemDate.innerText=thistime;
  
var timer=setTimeout('refreshSystemTime()',200);}

function switchSysBar(){
		var frmleft=document.getElementById("frmleft");
		var switchPoint=document.getElementById("switchPoint");
		frmleft.style.display=frmleft.style.display==""?"none":"";
		switchPoint.src=frmleft.style.display==""?"images/splitBar.gif":"images/splitBar2.gif";
	}
	function page_go(_url,id)
{
		switchHeaderButtonClass(id);
 		parent.content.location = _url;
}
function copyTaticket()
{
	switchHeaderButtonClass('copytd');
	parent.conent.document.getElementById('method').value='copy';
	parent.conent.document.form[0].submit();
}
document.oncontextmenu=function(e){ return false };
 function login_out()
 {
	 var result=showConfirm("<fmt:message key="common.message.exit"></fmt:message>",login_realout);
	 return false;
	// alert(result);
	// if(showConfirm("<fmt:message key="common.message.exit"></fmt:message>")=="true")
	// {
    //	window.location='<c:url value='/commons/loginout.jsp'/>';
	// }
 }
 
 
 function becomeTo()
 {
	 parent.content.location = '<c:url value='/system/usersub_listsub.action'/>'; 
	 //page_go('<c:url value='/system/usersub_listsub.action'/>','substitute');
 }
 
 
 function becomeToSelf()
 {
     window.location='<c:url value="/system/userLogsub.action"/>?sid=A';	 
 }
 
 
 
 function login_realout()
 {
		window.location='<c:url value='/commons/loginout.jsp'/>';	 
 }
 function switchHeaderButtonClass(id)
 {
 	document.getElementById('msgtd').className='linkBox';
 	document.getElementById('profiletd').className='linkBox';
 	document.getElementById('copytd').className='linkBox';
 	document.getElementById(id).className='linkBoxActive';
 }
</script>

<script type="text/javascript">
function openDialog()
{

	$('#selectDiv').dialog('open');
	document.getElementById('selectIframe').src='<c:url value='selectClientOrg.jsp'/>';
}
function closeDialog()
	{
	
		
		$("#selectDiv").dialog('close');
		//alert('<fmt:message key="main.message.selectorgsuccess"></fmt:message>');
		window.location.reload();

	}

$(document).ready(function() {

$("#selectDiv").dialog({
		autoOpen: false,
		height: 200,
		width: 400,
		modal: true,
		title:'<fmt:message key="main.title.selectOrg"></fmt:message>',
		open: function (event, ui) {
			$(".ui-dialog-titlebar-close", $(this).parent()).hide();
			            },
		resizable:false});

<c:if test="${userPrivileges.loginOrgId==null }">
openDialog();
</c:if>
});
</script>
</head>

<body >

<TABLE style="WIDTH: 100%; HEIGHT: 100%;"  border="0" cellspacing="0" cellpadding="0">
 
  <TR>
  <td style="height:73px;" class="groupHead">
  	<table width="100%" height="73" style="BACKGROUND: url(images/top_table_bg.jpg);" >
  	<tr>
  	<td width="500" style=" line-height:73px;">
  	  	   	
  	 <div style="height: 73px;vertical-align: middle;float:left;font-weight: bold;">
  	 <IMG src="images/delmarLogo.png"  height="73" style="float:left"><font class="black" style="font-size:25px;"><fmt:message key="main.head.title"></fmt:message></font></div>
     </td>
	
	 <td align="left"  valign="bottom" style="padding-bottom:5px" width="240" class="d-banner-middle">
			<table width="200" cellspacing="0" cellpadding="0">
				<tbody>                             
					<tr>						
						<td class="linkBox" valign="middle" align="center" id="msgtd">
							<a id="msga" class="Headerlink" href="#" onclick="page_go('<c:url value='/default.jsp'/>','msgtd')"><fmt:message key="main.label.message"/></a>
						</td>
						<td class="linkBoxActive" valign="middle" align="center" id="profiletd">
							 <A id="profilea" class="Headerlink" href="#" onclick="page_go('<c:url value='/commons/usersetting.jsp'/>','profiletd')"><fmt:message key="main.label.usersetting"/></A>
						</td>
						<td class="linkBox" valign="middle" align="center" id="copytd">
							 <a id="copya" class="Headerlink" href="#" onclick="openDialog()"><fmt:message key="main.label.changeorg"/></a>
						</td>                
						<td valign="middle" width="10">&nbsp;</td>
					</tr>
				</tbody>
			</table>
	  </td>
	  
	  <td align="right"   valign="middle">
		  <table cellspacing="0" cellpadding="0">

		   <tr>
		    <td style="padding-right:10px">
                <span class="d-banner-right" > <%=currentServerDate%>&nbsp;<span id='systemDate'></span></span>    
		    </td>
		    <td>
		      <img src="images/topuser.gif">
		      <c:if test="${sameUser=='true'}"> 
		       <span class="d-banner-right" ><delmar:message key="header.welcome"/><s:property value="%{#session.loginUser.getUsername()}"/>[<s:property value="%{#session.userPrivileges.loginOrgName}"/>]</span>
              </c:if>
		      <c:if test="${sameUser=='false'}">
		      <span class="d-banner-right" ><delmar:message key="public.loginuser.title"/>:<s:property value="%{#session.loginUser.getUsername()}"/> <delmar:message key="public.loginuser.loginas"/> <s:property value="%{#session.actualUser.getUsername()}"/></span>
		       </c:if>
		    </td>
		    </tr>
		   
		    
		   <tr>
				<td nowrap align='right' colspan=2>
			    <c:if test="${sameUser=='true'}">
				 <img border=0 src="images/toppass.png"  alt="<delmar:message key='header.becometo'/>" style="padding-left:5px"><a 
					href="#" onclick="javascript:becomeTo();" style="text-decoration:none;"
					title="<delmar:message key='header.becometo'/>" >
					<span  class="d-banner-right" ><delmar:message key='header.becometo'/></span></a>
				</c:if>
				
	            <c:if test="${sameUser=='false'}">
				 <img border=0 src="images/toppass.png"  alt="<delmar:message key='header.becometoself'/>"  style="padding-left:5px"><a 
					href="#" onclick="javascript:becomeToSelf();"  style="text-decoration:none;"
					title="<delmar:message key='header.becometoself'/>" >
					<span  class="d-banner-right" ><delmar:message key='header.becometoself'/></span></a>
				</c:if>

				<img border=0 src="images/toplogin.png"  alt="<delmar:message key='header.logout'/>"  style="padding-left:5px"><a 
					href="#" onclick="javascript:login_out();" style="text-decoration:none;">
					<span class="d-banner-right"><fmt:message key="header.logout"/></span></a>
				</td>	
					   
		   </tr>		    
			</table>
		</td>
		<td style="width:20px;"></td>
  	</tr>
  	</table>
  
  </td>
  
  </TR>
  <tr>
  <td >
  	<table width="100%" height="100%"   border="0" cellspacing="0" cellpadding="0">
  		<tr>
        <td  width="150px;" height="100%" id="frmleft">
        <iframe id="leftmenu" name="leftmenu" src="leftmenu.jsp" frameborder="0" align="top" height="100%" width="194" style="margin-top:0px; margin-left:0px; border:0px; padding: 0px ;">
				</iframe>
        </td>
        
        <td style="BACKGROUND: url(images/switchbg.gif); VERTICAL-ALIGN: middle; WIDTH: 6px; WHITE-SPACE: nowrap">
        	
        	<img 
            id='switchPoint' title='<fmt:message key="main.label.opencloseleftnav"></fmt:message>' style="cursor:pointer;" 
            onclick='switchSysBar()' src="images/splitBar.gif">
          
        </td>
        
        <td width="*" >
        <iframe id="content" name="content" src="default.jsp" frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;"></iframe>
          <div id="selectDiv">
		<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>
        </td>
        </tr>
  
  	</table>
  
  </td>
  </tr>		
</table>
</body>
</html>
