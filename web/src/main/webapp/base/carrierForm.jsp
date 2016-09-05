<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="carrier.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 <script type='text/javascript' src='../js/ea.effect.js'></script>

	<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/>"></script>
	<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery.alerts.css" type="text/css" />
 <script type="text/javascript">
	function addFile()
	{
		var editForm=document.getElementById('editForm');
		editForm.action='<c:url value="/base/carrier_addFile.action"/>';
		editForm.submit();
	}
	function deleteFiles()
	{
		var editForm=document.getElementById('editForm');
		<s:url var="carrier_deleteFiles_url" namespace="/base" action="carrier_deleteFiles"/>
		editForm.action='<c:url value="${carrier_deleteFiles_url}"/>';
		editForm.submit();
	}
	function openDialog(rowIndex,tablename,tableid)
	{
		alert(rowIndex);
		$('#selectDiv').dialog('open');
		document.getElementById('selectIframe').src='<c:url value='/commons/fileupload.jsp'/>?rowindex='+rowIndex+"&tablename="+tablename+"&tableid="+tableid;
	}
	function closeDialog(rowindex,jsonobj)
		{
			alert("rowindex="+rowindex+" jsonobj="+jsonobj);
			rowindex=rowindex+1;
			$("#selectDiv").dialog('close');
			var td1=$('#fileTable tr:eq('+rowindex+') td:eq(1)');
			td1.find('input[type=text]').attr('value',jsonobj.delmarFile.filename);
			td1.find('input[type=hidden]').attr('value',jsonobj.id);
			var td2=$('#fileTable tr:eq('+rowindex+') td:eq(1)');
			td2.find('input[type=hidden]').attr('value',jsonobj.fileId);
		}
/*
				open: function (event, ui) {
					$(".ui-dialog-titlebar-close", $(this).parent()).hide();
					            },
	 
					            */
	$(document).ready(function() {
		 $("#selectDiv").dialog({
				autoOpen: false,
				height: 300,
				width: 700,
				modal: true,
				title:'上传文件',
				resizable:false});
	 });
	 
	 function uploadfile(obj)
	 {
	
		var tr= obj.parentNode.parentNode.parentNode;
		var trSeq = $(obj).parent().parent().parent().find("tr").index($(obj).parent().parent());
		openDialog(trSeq,"base_carrier",$('#id').attr('value'));
	 }

 </script>
</head>



<body>

<s:form id="editForm" action="carrier_edit" namespace='/base' theme="simple">
<s:hidden id="id" name="carrier.id"/>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="carrier.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"/>
			</c:if>		
			<c:if test="${!isLast}">
				<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"   cssClass="input_submit"/>
			</c:if>
		</div></td>
        </tr>
      </table>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          
           
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
                   <tr  class="query_one">
               	<td width="20%">
               	<s:label for="code" value="%{#session.resource.get('carrier.column.code')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="carrier.code" id="code"/>
               	</td>
				</tr>
				   <tr  class="query_two">
               	<td width="20%">
               	<s:label for="code" value="%{#session.resource.get('carrier.column.scaccode')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="carrier.scaccode" id="scaccode"/>
               	</td>
				</tr>
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('carrier.column.cname')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="carrier.cname" id="name"  cssStyle="width:300px;"/>
               	</td>
				</tr>
				<tr  class="query_two">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('carrier.column.planeocean')}"  />
               	</td>
               	<td colspan="3">
               	<s:textfield name="carrier.planeocean" id="planeocean"/>
               	</td>
				</tr>
				
						<tr  class="query_one">
							
							<td ><s:label for="descr"  value="%{#session.resource.get('common.label.remark')}"  /></td>
							<td colspan="3">
							<s:textfield name="carrier.remark" id="remark" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit" value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"/>
						<c:if test="${carrier.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/base/carrier_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
                </td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                
                <tr>
                <td colspan="4">
                
                
                
                 <!-- table 页 bgn -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
            <td width="90" height="26" align="center" background="../images/table_page_1.gif">
  					文件上传
            </td>
           	<td background="../images/table_page_bg.gif" width="*"  height="26"><div class="C_S_F_L">
			&nbsp;
		</div></td>
              </tr>
            </table>
             <!-- table 页 end -->   
                
                </td>
                
                </tr>
                
                <tr>
                <td colspan="4">
                <!-- table bgn -->
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                
            <input value="添加文件" type="button"  class="input_submit" onclick="addFile()"/> &nbsp;&nbsp;
            <input class="input_submit" type="button"    value="删除文件" onclick="javascript:deleteFiles()"/>
                <tr>
                <td>
<table   border="0"  cellpadding="0" cellspacing="0" class="table" id="fileTable">
					<thead >
						<th>
            				<input type="checkbox"  onclick="selectAll('ids',this);"/>
            			</th>
						<th>
							文件名
						</th>
						<th>
							摘要
						</th>
						<th>
						关键字
						</th>
						<th>&nbsp;</th>
					</thead>
					<tbody>
						<s:iterator value="fileRelationList" status="st">
								<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
								 	<td>
              		<input type="checkbox" name="ids"  value="<s:property value="#st.index"/>"/>
              	</td>
						<td>
							<s:hidden name="%{'fileRelationList['+#st.index+'].id'}" />
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.filename'}" readonly="true"/>
						</td>
						<td>
							<s:hidden name="%{'fileRelationList['+#st.index+'].delmarFile.id'}" />
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.fileAbstract'}" cssStyle="width:300px"/>
						</td>
						<td>
							<s:textfield name="%{'fileRelationList['+#st.index+'].delmarFile.fileKeyword'}"/>
							<s:hidden name="%{'fileRelationList['+#st.index+'].delmarFile.path'}"/>
						</td>
						<td>
								<input type="button" onclick="javascript:uploadfile(this);" value="选择附件">
						</td>
						</s:iterator>
					
					</tbody>
								</table>			
				</td>
				</tr>
				</table>
				</td>
				</tr>
				</table>
	</td>
		</tr>
                
                </table>
                
                
                
                <!-- table end -->
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                </td>
                
                </tr>
                
                
                
                <tr>
                <td colspan="4">
                
                <!-- table 页 bgn -->
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr> 
            <td width="90" height="26" align="center" background="../images/table_page_1.gif">
            <delmar:message key="common.label.internationalization"/>
            </td>
           	<td background="../images/table_page_bg.gif" width="*"  height="26"><div class="C_S_F_L">
			&nbsp;
		</div></td>
              </tr>
            </table>
             <!-- table 页 end -->   
                
                </td>
                
                </tr>
                
                <tr>
                <td colspan="4">
                <!-- table bgn -->
                
                
                
                            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
<table   border="0"  cellpadding="0" cellspacing="0" class="table">
					<thead >
						<th>
								<delmar:message key="language.column.code"/>
						</th>
						<th>
							<delmar:message key="language.column.name"/>
						</th>
						<th>
							<fmt:message key="common.label.remark"/>
						</th>
					</thead>
					<tbody>
						<s:iterator value="carrierTrlList" status="st">
								<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
						<td>
							<s:hidden name="%{'carrierTrlList['+#st.index+'].id'}" />
							<s:textfield name="%{'carrierTrlList['+#st.index+'].language'}" readonly="true"/>
						</td>
						<td>
							<s:textfield name="%{'carrierTrlList['+#st.index+'].name'}" cssStyle="width:300px"/>
						</td>
						<td>
						<s:textfield name="%{'carrierTrlList['+#st.index+'].remark'}"/>
						</td>
						</s:iterator>
					
					</tbody>
</table>			
				</td>
				</tr>
				</table>
				</td>
				</tr>
				</table>
	</td>
		</tr>
                
                </table>
                
                
                
                <!-- table end -->
                </td>
                </tr>
                
                
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>

<div id="selectDiv">
		<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0; border:0; padding: 0;" id="selectIframe"></iframe>
</div>




</body>
</html>
