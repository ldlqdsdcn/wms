<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>

<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
  <script type='text/javascript' src='../js/ea.validate.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="text/javascript">
function chooseIds()
{
   var ids="";
   $("#ids:checked").each(function(i){
	   
	   if (ids=="") 
    	  ids=$(this).val();
	    else
		  ids=ids+","+$(this).val();
   });
   
   window.parent.document.getElementById("cids").value=ids;
  // $("#cids").val(ids);
  // alert($("#cids").val());
}
</script>
</head>

<body >

<s:form action="role_addRoleUser" namespace="/system"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<display:table name="sessionScope.MAP_KEY_OF_SESSION.nonUserList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="100" class="table" export="false">
		<display:column style="width:30px;text-align:center"  media="html"  >
		<input type="checkbox" name="ids"  id="ids" value="<c:out value='${list.id}'/>"  style='border: none'  onclick='chooseIds()'/>
		</display:column>    
		<display:column titleKey="common.label.sequence">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column  property="username"  titleKey="user.column.loginname" sortable="true" media="html"/>
		     <display:column property="name"titleKey="user.column.name"/>
			<display:column property="org.name"  escapeXml="true"  titleKey="user.column.org" sortable="true" ></display:column>			
		</display:table>

</td>
</tr>
</table>


</s:form>



</body>
</html>
