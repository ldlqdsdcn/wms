<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<jsp:include page="/commons/list_js.jsp">
		<jsp:param name="search_name" value="base_port"/>
		<jsp:param name="edit_url" value="/base/port_edit.action"/>
	</jsp:include>
</head>
<body >
<s:form action="port_list" namespace="/base"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="datadict.location" /></td>
          <td class="navig" align="right"> 
          <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <input type="button"  value="<delmar:message key="common.button.search" />"  class="input_submit" id="search_but">
                  
                  <s:submit method="create" cssClass="input_submit" value="%{#session.resource.get('common.button.create')}" />
                  <s:submit method="deletes" cssClass="input_submit"   value="%{#session.resource.get('common.button.delete')}"  onclick="return confirmListDelete('ids')"/>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.portList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
				
		</display:column>    
		<display:column titleKey="common.label.sequence"  media="html csv excel xml pdf rtf">
			   <c:out value="${list_rowNum}"/>
    	</display:column>
		     <display:column   titleKey="port.column.portcode" sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.portcode}"/>
     		  </a>
		     </display:column>
		     <display:column property="portcode"  escapeXml="true"  titleKey="port.column.portcode" media="csv excel xml pdf rtf"	/>
		     <display:column property="id"  titleKey="port.column.name" sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.PortDecorator" />
			<display:column property="countryCode"  titleKey="port.column.countryCode" sortable="true"  />
			<display:column property="id"  titleKey="port.column.planeocean" sortable="true"  decorator="com.delmar.base.web.displaytag.decorator.PortModeDecorator" />
		    <display:column property="cityId"  escapeXml="true" titleKey="port.column.city"  sortable="true"/>
		    <display:column property="remark"  escapeXml="true" titleKey="common.label.remark"  sortable="true"/>
		</display:table>
</td>
</tr>
</table>
</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
