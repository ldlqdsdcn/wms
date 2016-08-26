<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="language.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 <script type='text/javascript' src='../js/ea.effect.js'></script>
</head>



<body>

<s:form id="editForm" action="language_edit" namespace='/core' theme="simple">
<s:hidden id="id" name="language.id"></s:hidden>

<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><delmar:message key="language.location"/></td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
			
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"></s:submit>
			</c:if>		
			<c:if test="${!isLast}">
				<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"   cssClass="input_submit"></s:submit>
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
               	<s:label for="code" value="%{#session.resource.get('language.column.code')}"  ></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="language.code" id="code"></s:textfield>
               	</td>
				</tr>
               <tr  class="query_two">
               	<td width="20%">
               	<s:label for="name" value="%{#session.resource.get('language.column.name')}"  ></s:label>
               	</td>
               	<td colspan="3">
               	<s:textfield name="language.name" id="name"></s:textfield>
               	</td>
				</tr>
				
				
						<tr  class="query_one">
							
							<td ><s:label for="descr"  value="%{#session.resource.get('common.label.remark')}"  ></s:label></td>
							<td colspan="3">
							<s:textfield name="language.remark" id="remark" cssStyle="width:500px;"></s:textfield>
							</td>
							
                            
						</tr>
						
					
						
						
						

                    
                <tr>
                <td colspan="4" class="td_page_right">
               			
						<s:submit method="edit" value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"></s:submit>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit"></s:submit>
						<c:if test="${language.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"></s:submit>
						</c:if>
						
						<input onclick="window.location='<c:url value="/core/language_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
						
								
				
				
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
						<td align="center">
									<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
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
																							<s:iterator value="languageTrlList" status="st">
																									<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
																							<td>
																								<s:hidden name="%{'languageTrlList['+#st.index+'].id'}" />
																								<s:textfield name="%{'languageTrlList['+#st.index+'].language'}" readonly="true"></s:textfield>
																							</td>
																							<td>
																								<s:textfield name="%{'languageTrlList['+#st.index+'].name'}"></s:textfield>
																							</td>
																							<td>
																							<s:textfield name="%{'languageTrlList['+#st.index+'].remark'}"></s:textfield>
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







</body>

