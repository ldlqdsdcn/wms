<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

		<table id="userThirdPartyTable" class="table">
            	<thead>
            	<th  style="width:30%">
            	<input type="checkbox"  onclick="selectAll('tpids',this);"/>
            	</th>
            	<th ><delmar:message key="user.column.thirdparty.system"/></th>
				<th><delmar:message key="user.column.thirdparty.partytype"/></th>
				<th><delmar:message key="user.column.thirdparty.partyuser"/></th>				
                <th><delmar:message key="user.column.thirdparty.remark"/>
                <c:if test="${user.id>0}">
                <div style="display:inline-block;float:right">
                <a  href="#"  onclick="javascript:addThirdParty()"><delmar:message key="userthirdparty.operate.add"/></a>
                </div>
                </c:if>
                </th>				
            	</thead>
            	<tbody id="detailthridpartytbody">
              	<s:iterator value="userThirdPartyList" status="st" var="tpa" >
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>" id="tptr<s:property value="%{#tpa.id}"/>">
		              	<td style="text-align:center">
		              	<input type="checkbox" name="tpids"  value="<s:property value="%{#tpa.id}"/>"  />
		              	</td>
		              	<td>
		           			<s:property value="%{#tpa.systemName}"/>
		              	</td>
		              	<td>
		           			<s:property value="%{#tpa.partyTypeName}"/>
		              	</td>
		              	<td>
		           			<span><s:property value="%{#tpa.partyUser}"/></span>
							<div id="tpoperate<s:property value="%{#tpa.id}"/>" class="d-rowactions">
			                <a  href="#"  onclick="javascript:editThirdParty('<s:property value="%{#tpa.id}"/>')"><delmar:message key="userthirdparty.operate.edit"/></a>|
		                    <span class="d-trash"><a href="#" onClick="deleteThirdParty('<s:property value="%{#tpa.id}"/>')"><delmar:message key="userthirdparty.operate.delete"/></a></span>
                			</div> 
		              	</td>	
		                 <td>
		           			<s:property value="%{#tpa.remark}"/>
		              	</td>		
		              	
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
            
            
            


<div id="userthirdparty_form" title="<delmar:message key='userthirdparty.dialog.title'/>" >

 
  <form class="dialog-form" >
    <fieldset>
		<s:hidden id="thirdPartyId" name="thirdPartyId"/>
		<s:label for="thirdPartySystem"  value="%{#session.resource.get('userthirdparty.column.system')}" />
		<s:select list="thirdpartySysList" listKey="datadictId" listValue="name"  name="thirdPartySystem" id="thirdPartySystem" style="width:200px"></s:select>
		<p>
		<s:label for="thirdPartyType"  value="%{#session.resource.get('userthirdparty.column.partytype')}" />
		<s:select list="thirdpartyTypeList" listKey="datadictId" listValue="name"  name="thirdPartyType" id="thirdPartyType" style="width:200px"></s:select>
		<p>
		<s:label for="thirdPartyUser"  value="%{#session.resource.get('userthirdparty.column.partyuser')}" />
		<s:textfield name="thirdPartyUser" id="thirdPartyUser"/>
		<p>
		<s:label for="thirdPartyPassword"  value="%{#session.resource.get('userthirdparty.column.password')}" />
		<s:textfield name="thirdPartyPassword" id="thirdPartyPassword"/>
		<p>
		<s:label for="thirdPartyRemark"  value="%{#session.resource.get('userthirdparty.column.remark')}" />
		<s:textfield name="thirdPartyRemark" id="thirdPartyRemark"/>
		
		<s:set name="addThirdParty" value="%{#session.resource.get('common.button.save')}"/>
		<s:set name="closeThirdParty" value="%{#session.resource.get('common.button.close')}"/>
	</fieldset>
  </form>
</div>

<script type="text/javascript">
var dialog;

var thirdPartySystem = $( "#thirdPartySystem" ),
thirdPartyType = $( "#thirdPartyType" ),
thirdPartyUser = $( "#thirdPartyUser" ),
thirdPartyPassword = $( "#thirdPartyPassword" ),
thirdPartyRemark = $( "#thirdPartyRemark" ),
allFields = $( [] ).add( thirdPartySystem ).add( thirdPartyType ).add( thirdPartyUser ),
tips = $( ".validateTips" );

dialog = $( "#userthirdparty_form" ).dialog({
    autoOpen: false,
    height: 400,
    width: 400,
    modal: true,
    buttons: {
      "<s:property value='%{#addThirdParty}' escape='false'/>": saveThirdParty,
      "<s:property value='%{#closeThirdParty}' escape='false'/>": function() {
         dialog.dialog( "close" );
      }
    },
    close: function() {      
      allFields.removeClass( "ui-state-error" );
    }
  });
  
  

function addThirdParty()
{
	  $("#thirdPartyId").val(0);
	  $("#thirdPartySystem").val('');
	  $("#thirdPartyType").val("");
	  $("#thirdPartyUser").val("");
	  $("#thirdPartyPassword").val("");
	  $("#thirdPartyRemark").val("");
	  
	  dialog.dialog( "open" );
	  
	  
}


function saveThirdParty()
{
    var valid = true;
    allFields.removeClass( "ui-state-error" );

    valid = valid && checkIsNull( thirdPartySystem, "<delmar:message key='userthirdparty.column.system'/>");
    valid = valid && checkIsNull( thirdPartyType, "<delmar:message key='userthirdparty.column.partytype'/>" );
    valid = valid && checkIsNull( thirdPartyUser, "<delmar:message key='userthirdparty.column.partyuser'/>" );

    if (valid==false)
       return; 
    
	  var params = {
			  id:$("#thirdPartyId").val(),
			  sysUserId:$("#id").val(),
			  systemId:$("#thirdPartySystem").val(),
			  systemName:$("#thirdPartySystem").find("option:selected").text(),
			  partyTypeId:$("#thirdPartyType").val(),
			  partyTypeName:$("#thirdPartyType").find("option:selected").text(),
			  partyUser:$("#thirdPartyUser").val(),
			  password:$("#thirdPartyPassword").val(),
			  remark:$("#thirdPartyRemark").val()
		  };
	 
 
	  var id=$("#thirdPartyId").val();
	  
	  $.ajax({  
         url:'userthird_save.action',  
         type:'post',  
         data:{data:encodeURI(JSON2.stringify(params))},
         dataType:'json',  
         success:function (data) {  
       	  if (id==0)
       	  {
       		  addToThirdPartyBody(data);        		  
       	  }
       	  else
       	  {
       		  refreshThirdPartyTr(data);  
       	  }
       	  
       	  
         }  
     });  
	  

}



function addToThirdPartyBody(data)
{
	 var newtrhtml="<tr id='tptr"+data.id+"'>"+
	 "<td style='text-align:center'><input type='checkbox' name='tpids' value="+data.id+"/></td>"+
	 "<td>"+data.systemName+"</td>"+
	 "<td>"+data.partyTypeName+"</td>"+
	 "<td>"+data.partyUser+
	 "<div id='tpoperate'"+data.id+"' class='d-rowactions'>"+
	 "<a href='#' onclick='javascript: editThirdParty("+data.id+")'><delmar:message key='userthirdparty.operate.edit'/></a>|"+
	 "<a href='#' onclick='javascript: deleteThirdParty("+data.id+")'><delmar:message key='userthirdparty.operate.delete'/></a>"+			 
	 "</div></td>"+	
	 "<td>"+data.remark+"</td>"+
	 "</tr>";
	 
	 $( "#detailthridpartytbody" ).append(newtrhtml);

	 dialog.dialog( "close" );	 
}




function refreshThirdPartyTr(data)
{
 
     $("#tptr"+data.id).find("td:eq(1)").text(data.systemName);
     $("#tptr"+data.id).find("td:eq(2)").text(data.partyTypeName);
     $("#tptr"+data.id).find("td:eq(3)").find("span:eq(0)").text(data.partyUser);
     $("#tptr"+data.id).find("td:eq(4)").text(data.remark);
     
   

	 dialog.dialog( "close" );	 
} 



 function editThirdParty(id)
 {
	  $.getJSON("userthird_edit.action?id="+id,
		   function(data){
		      fullThirdParty(data);
		      
		 });	  
 }

 
 function fullThirdParty(data)
 {
	  $("#thirdPartyId").val(data.id);
	  $("#thirdPartySystem").val(data.systemId);
	  $("#thirdPartyType").val(data.partyTypeId);
	  $("#thirdPartyUser").val(data.partyUser);
	  $("#thirdPartyPassword").val(data.password);
	  $("#thirdPartyRemark").val(data.remark);
	  
	  dialog.dialog( "open" );	  
	  
 }
 

 
 function deleteThirdParty(id)
 {
	  $.post("userthird_delete.action?id="+id, 
		  function(data){
		     $("#tptr"+id).remove();     
		 });	 	  
 }
 
 

 
  
  </script>
            