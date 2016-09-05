<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<%@include file="/commons/header.jsp"%>
	<jsp:include page="/commons/list_js.jsp">
		<jsp:param name="search_name" value="system_user"/>
		<jsp:param name="edit_url" value="/system/user_edit.action"/>
	</jsp:include>
</head>

<body >

<s:form action="user_list" namespace="/system"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="user.location" /></td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <input type="button"  value="<delmar:message key="common.button.search" />" id="search_but"  class="input_submit"/>
                  <s:submit method="create"  value="%{#session.resource.get('common.button.create')}" cssClass="input_submit"/>
                 
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}" onclick="return confirmListDelete('ids')"/>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 
      
<div id="filterpage">
        <div class="d-filterheader">
            <div id="filterpageheader" style="cursor:pointer; width:90%">
                <img id="filterpagetoggleimage" class="toggle" alt="toggler"
                     src="../images/core/icon_expand_l.gif"/>
                <delmar:message key="public.list.filter"/>
            </div>
        </div>
        
        <div id="filterForm" >
            <s:hidden id="queryStatus" value="%{#session.MAP_KEY_OF_SESSION.queryStatus}"/>
			<table border="0" cellpadding="0" cellspacing="0"  class="cTableBorder" style="padding-top:5px;padding-bottom:5px">
		       <tr>
		       <td class="d-tdlabel">      	
			     <delmar:message key="user.column.name" /> 
			   </td>
			   <td   class="d-tdinput"> 
			      <s:textfield name="name" value="%{#session.MAP_KEY_OF_SESSION.name}" class="d-inputtext"/>
			      <s:checkbox name="invalid" id="invalid" fieldvalue="Y" value="%{#session.MAP_KEY_OF_SESSION.invalid}"/><delmar:message key="user.column.includeisactive" />  
			   </td>

			       <td  style="text-align:right; padding:5px;">     
			       <s:submit method="list" cssClass="input_submit" id="btnQuery" value="%{#session.resource.get('common.button.search')}" />
                   <s:reset cssClass="input_submit" id="btnReset" value="%{#session.resource.get('common.button.reset')}" ></s:reset>			       
			       </td>    
			       </tr>
			       </table>        
        </div>

 </div>
            

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.userList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
        <display:setProperty name="sort.amount" value="list"/> 		    
		<display:column style="width:30px;text-align:center"  media="html"  title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>"  >
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
		</display:column>    
		<display:column titleKey="common.label.sequence" media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column  titleKey="user.column.loginname" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
     				 <c:out value="${list.username}"/></a>
		     </display:column>
		     <display:column  titleKey="user.column.name" sortable="true">
		       <c:if test="${list.isActive=='0'}">
		        <span style="color:#ff0000"><c:out value="${list.name}"/></span>
		       </c:if>
		         <c:if test="${list.isActive=='1'}">
		         <c:out value="${list.name}"/>
		         </c:if>
		     </display:column>
			 <display:column property="client.name"  escapeXml="true"  titleKey="user.column.client" sortable="true" />
			 <display:column property="org.name"  escapeXml="true"  titleKey="user.column.org" sortable="true" />
			<%--
			<display:column property="departments"  escapeXml="true" title="部门" sortable="true" decorator="com.ea.system.web.display.decorator.UserDepartmentDecorator"/>
			 --%>				
		    <display:column property="id"  escapeXml="true" titleKey="user.column.role"  sortable="true" decorator="com.delmar.system.web.displaytag.decorator.UserRoleDecorator"/>
		   
			<display:column property="init"  escapeXml="true"  titleKey="user.column.init" sortable="true" />
		</display:table>




</td>
</tr>
</table>


</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
