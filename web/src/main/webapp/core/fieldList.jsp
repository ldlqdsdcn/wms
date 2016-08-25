<%--
  Created by IntelliJ IDEA.
  User: 刘大磊
  Date: 2016-08-25 16:53:43
--%>
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

<s:form action="field_list" namespace="/core"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">位置：字段</td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  <input type="button"  value="查询"  class="input_submit">
                  
                  <s:submit method="create" cssClass="input_submit" value="新建" ></s:submit>
                  <s:submit method="deletes" cssClass="input_submit" value="删除"></s:submit>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 


 <display:table name="sessionScope.MAP_KEY_OF_SESSION.fieldList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="20" class="table" export="true">
		    <display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>">

            		<input type="checkbox" name="ids"
            				value="<c:out value='${list.id}'/>" style='border: none' />
            		</display:column>
            		<display:column title="序号" media="html csv excel xml pdf rtf">
                    			   <c:out value=" ${list_rowNum}"/>
                     </display:column>
                            <display:column   title="id" sortable="true" media="html">
                                <a href="javascript:viewExport('<c:out value="${list.id}"/>')">
                                        <c:out value=" ${list.id}"/>
                                </a>
                           </display:column>
                           <display:column property="id" media="csv excel xml pdf rtf"	title="id" />
                           <display:column property="name"  escapeXml="true" title="名称" sortable="true"    > </display:column>
                           <display:column property="descr"  escapeXml="true" title="描述" sortable="true"    > </display:column>
                           <display:column property="help"  escapeXml="true" title="帮助" sortable="true"    > </display:column>
                           <display:column property="created"  escapeXml="true" title="创建时间" sortable="true"    decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"  > </display:column>
                           <display:column property="createdby"  escapeXml="true" title="创建人" sortable="true"    > </display:column>
                           <display:column property="updated"  escapeXml="true" title="修改时间" sortable="true"    decorator="com.delmar.core.web.displaytag.decorator.DateDecorator"  > </display:column>
                           <display:column property="updatedby"  escapeXml="true" title="修改人" sortable="true"    > </display:column>
                           <display:column property="isactive"  escapeXml="true" title="是否有效" sortable="true"    > </display:column>
		</display:table>
</td>
</tr>
</table>


</s:form>

<script type="text/javascript">
    function viewExport(id) {
       window.location='<c:url value="//core/field_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
    
   
</script>

</body>
</html>
