<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-31 15:25:16
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/commons/header.jsp"%>
    <jsp:include page="/commons/list_js.jsp">
        <jsp:param name="search_name" value="cargo_meeting"/>
        <jsp:param name="edit_url" value="/cargo/meeting_edit.action"/>
    </jsp:include>
</head>
<body >
<s:form action="meeting_list" namespace="/cargo"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">
<tr>
<td>
    <table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr>
            <td align="left" class="navig">位置：会议纪要</td>
            <td class="navig" align="right">
                <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
                    <tr>
                        <td>
                            <input type="button" value="查询" class="input_submit" id="search_but">
                            <s:submit method="create" cssClass="input_submit" value="新建"/>
                            <s:submit method="deletes" cssClass="input_submit" value="删除" onclick="return confirmListDelete()"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <display:table name="sessionScope.MAP_KEY_OF_SESSION.meetingList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html">
          		<input type="checkbox" name="ids" value="<c:out value='${list.id}'/>" style='border: none' />
        </display:column>
        <display:column title="序号" media="html csv excel xml pdf rtf">
              	<c:out value=" ${list_rowNum}"/>
        </display:column>
        <display:column   title="标题" sortable="true" media="html">
                 <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value=" ${list.title}"/></a>
        </display:column>
        <display:column property="title" media="csv excel xml pdf rtf"	title="标题" />


        <display:column property="bgnTime"  escapeXml="true" title="开始时间" sortable="true"
        
                 decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                                                                                    />


        <display:column property="endTime"  escapeXml="true" title="结束时间" sortable="true"
        
                 decorator="com.delmar.core.web.displaytag.decorator.DateTimeDecorator"
                                                                                    />


        <display:column property="descr"  escapeXml="true" title="描述" sortable="true"
        
                                                                                                    />


        <display:column property="created"  escapeXml="true" title="创建时间" sortable="true"
        
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                                                                                                    />


        <display:column property="createdby"  escapeXml="true" title="创建人" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="updated"  escapeXml="true" title="修改时间" sortable="true"
        
                        decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"
                                                                                                    />


        <display:column property="updatedby"  escapeXml="true" title="修改人" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="mainContent"  escapeXml="true" title="mainContent" sortable="true"
        
                                                                                                    />


        <display:column property="userId"  escapeXml="true" title="userId" sortable="true"
        
                                        decorator="com.delmar.base.web.displaytag.decorator.UserDecorator"
                                                            />


        <display:column property="orgId"  escapeXml="true" title="组织" sortable="true"
        
                                                                decorator="com.delmar.base.web.displaytag.decorator.OrgDecorator"
                                    />


        <display:column property="clientId"  escapeXml="true" title="实体" sortable="true"
        
                                                                                        decorator="com.delmar.system.web.displaytag.decorator.ClientDecorator"
            />


    </display:table>
</td>
</tr>
</table>
</s:form>
<%@include file="/commons/list_footer.jsp"%>
</body>
</html>
