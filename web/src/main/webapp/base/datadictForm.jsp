<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
	<delmar:message key="datadict.title"/>
</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 <script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<link href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<SCRIPT  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></SCRIPT>
 
 
 <script type='text/javascript' src='../js/dm/delmar.js'></script>
 <script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>
 
<script type="text/javascript">
 function gotoList()
 {
	 window.location='<c:url value="/base/datadict_list.action"/>?datadictTypeId='+$("#datadictTypeId").val();
 }
</script>
</head>



<body>

<s:form id="editForm" action="datadict_edit" namespace='/base' theme="simple">
<s:hidden id="id" name="datadict.id"/>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">	<delmar:message key="datadict.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"  cssClass="input_submit"/>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"/>
			
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
               
               <tr class="query_one">
               		<td style="width:20;">
               			<s:label for="value" value="%{#session.resource.get('datadict.column.value')}" />
               			</td>
               			<td colspan="3">
               			<s:textfield name="datadict.value" id="value"/>
		               	<input type="checkbox" name="datadict.isActive" id="isActive" value="0"
		               	<s:if test="datadict.isActive==0">
		               	   checked
		               	</s:if>>
		                <span style="color:#ff0000"><delmar:message key="datadict.column.isactive"/></span>

               			
               			</td>
               </tr>
               
               <tr  class="query_two">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('datadict.column.name')}" />
               	</td>
               	<td colspan="3">
               	<s:textfield name="datadict.name" id="name"/>
               	</td>
				</tr>
				<tr class="query_one">
					<td><delmar:message key="datadict.column.type"/></td>
					<td colspan="3">
					<s:select list="datadictTypeList" listKey="id" listValue="name"  name="datadict.datadictTypeId" id="datadictTypeId"> </s:select>
					</td>
				
				</tr>
				
               <tr  class="query_two">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('datadict.column.indexorder')}" />
               	</td>
               	<td colspan="3">
               	<s:textfield name="datadict.indexOrder" id="indexOrder" blurvalidate="yes" blurtype="integer" title="%{#session.resource.get('public.input.numberonly')}"/>
               	</td>
				</tr>				
				
						<tr  class="query_one">
							
							<td ><s:label for="descr"  value="%{#session.resource.get('datadict.column.remark')}" /></td>
							<td colspan="3">
							<s:textfield name="datadict.remark" id="remark" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="create" value="%{#session.resource.get('common.button.create')}"  cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"/>
						<c:if test="${datadict.id!=null}">
						<s:submit method="copy" value="%{#session.resource.get('common.button.copy')}"  cssClass="input_submit"/>
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						<input onclick="gotoList()"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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
							<delmar:message key="datadict.column.name"/>
						</th>
						<th>
							<fmt:message key="common.label.remark"/>
						</th>
					</thead>
					<tbody>
						<s:iterator value="datadictTrlList" status="st">
								<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
						<td>
							<s:hidden name="%{'datadictTrlList['+#st.index+'].id'}" />
							<s:textfield name="%{'datadictTrlList['+#st.index+'].language'}" readonly="true"/>
						</td>
						<td>
							<s:textfield name="%{'datadictTrlList['+#st.index+'].name'}" cssStyle="width:300px"/>
						</td>
						<td>
						<s:textfield name="%{'datadictTrlList['+#st.index+'].remark'}"/>
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



<script type="text/javascript">

$(document).ready(function() {
	 $(document).D_Validate();
});

</script>



</body>

