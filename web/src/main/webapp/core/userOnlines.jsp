<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
  <script type='text/javascript' src='../js/ea.validate.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body >

<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="online.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                 <delmar:message key="online.label.onlineuser" />
                 <c:out value="${sessionScope.MAP_KEY_OF_SESSION.onlinecount}"></c:out>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->





</td>
</tr>
<tr>
<td>
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.userOnlines" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">

		<display:column 
	titleKey="common.label.sequence"
media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		<display:column property="user.username" sortable="true"  titleKey="online.label.loginname"/>
					
		    <display:column property="loginDate"  escapeXml="true" titleKey="online.label.logindate" sortable="true"
		     decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"/>
		    <display:column property="sessionId"  escapeXml="true"  titleKey="online.label.sessionId" sortable="true"/>
			<display:column property="remoteAddr"  escapeXml="true" titleKey="online.label.IP"  sortable="true"/>
			<display:column property="remoteHost"  escapeXml="true" titleKey="online.label.loginhost"  sortable="true"/>
		</display:table>
</td>
</tr>
</table>





</body>
</html>
