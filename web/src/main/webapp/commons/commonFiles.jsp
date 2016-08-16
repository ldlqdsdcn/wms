<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
    <script type='text/javascript' src='../js/ea.validate.js'></script>
  <script type='text/javascript' src='../js/dm/uploadfile.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body >

<s:form action="commonFile_list" namespace="/commons"  theme="simple" >
<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">文件信息</td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
              
                  <s:submit method="deletes" cssClass="input_submit" value="%{#session.resource.get('common.button.delete')}"></s:submit>
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <display:table name="sessionScope.MAP_KEY_OF_SESSION.commonFileList" cellspacing="0" cellpadding="0"  requestURI=""
		    id="list" pagesize="7" class="table" export="true">
		<display:column style="width:30px;text-align:center" title="<input type='checkbox' name='selectall' onClick='selectAll(\"ids\",this);' style='margin:0px;'/>" media="html" >
		<input type="checkbox" name="ids"
				value="<c:out value='${list.id}'/>" style='border: none' />
	</display:column>    
      <display:column   	titleKey="delmarfile.column.filename"  sortable="true" media="html">
		      <a href="javascript:viewExport('<c:out value="${list.id}"/>')"><c:out value="${list.delmarFile.filename}"/></a>
		     </display:column>
		     <display:column property="delmarFile.fileAbstract"  escapeXml="true" titleKey="delmarfile.column.fileabstract"  sortable="true"></display:column>				
		     <display:column property="delmarFile.fileKeyword"  titleKey="delmarfile.column.filekeyword" />
             <display:column   	titleKey="delmarfile.column.operate.open"  sortable="true" media="html">
             
	
				
				             
		      <a href="javascript:openFile('<c:out value="${list.delmarFile.id}"/>')"><delmar:message key="delmarfile.column.viewfile" cn="打开"/></a>
		     </display:column>
		     
		</display:table>
</td>
</tr>
</table>


</s:form>


<script type="text/javascript">
    function viewExport(id) {
    
       window.location='<c:url value="/commons/commonFile_edit.action"/>?id='+id;
    }
    highlightTableRows("list");
    
    
    
    function openFile(id)
    {
		var fileDownloadUrl="dmfiledownload.jsp?id="+id;
		window.open(fileDownloadUrl);
    }
    
   
</script>

</body>
</html>
