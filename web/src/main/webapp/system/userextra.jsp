<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

			
			<table id="userextraTable" class="table">
            	<thead>
            	<th  style="width:30;">
            	<input type="checkbox"  onclick="selectAll('tpuserextraids',this);"/>
            	</th>
            	<th ><delmar:message key="userextra.column.propid"/></th>
				<th><delmar:message key="userextra.column.value"/></th>
                <th><delmar:message key="userextra.column.remark"/>
                  <c:if test="${user.id>0}">
                <div style="display:inline-block;float:right">
                <a  href="#"  onclick="javascript:addUserExtra()"><delmar:message key="userextra.operate.add"/></a>
                </div>
                </c:if>
                </th>				
            	</thead>
            	<tbody id="detailuserextra">
              	<s:iterator value="userExtraList" status="st" var="tpa" >
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>" id="tptruserextra<s:property value="%{#tpa.id}"/>">
		              	<td style="text-align:center">
		              	<input type="checkbox" name="tpuserextraids"  value="<s:property value="%{#tpa.id}"/>"  />
		              	</td>
		              	<td>
		           			<s:property value="%{#tpa.propName}"/>
		              	</td>
		              	<td>
		           			<span><s:property value="%{#tpa.value}"/></span>
							<div id="tpuserextraoperate<s:property value="%{#tpa.id}"/>" class="d-rowactions">
			                <a  href="#"  onclick="javascript:editUserExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="userextra.operate.edit"/></a>|
		                    <span class="d-trash"><a href="#" onClick="deleteUserExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="userextra.operate.delete"/></a></span>
                			</div> 
		              	</td>	
		                 <td>
		           			<s:property value="%{#tpa.remark}"/>
		              	</td>		
		              	
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
			


<div id="userextra_form" title="<delmar:message key='userextra.dialog.title'/>" >

 
  <form class="dialog-form" >
    <fieldset>
		<s:hidden id="extraId" name="extraId"/>
		<s:label for="propId"  value="%{#session.resource.get('userextra.column.propid')}" />
		<s:select list="extraPropList" listKey="datadictId" listValue="name"  name="extraPropId" id="extraPropId" style="width:200px"></s:select>
		<p>
		<s:label for="extraValue"  value="%{#session.resource.get('userextra.column.value')}" />
		<s:textfield name="extraValue" id="extraValue"/>
		<p>
		<s:label for="extraRemark"  value="%{#session.resource.get('userextra.column.remark')}" />
		<s:textfield name="extraRemark" id="extraRemark"/>
		<p>
		<s:set name="addUserExtra" value="%{#session.resource.get('common.button.save')}"/>
		<s:set name="closeUserExtra" value="%{#session.resource.get('common.button.close')}"/>
	</fieldset>
  </form>
</div>





<script type="text/javascript">

$(document).ready(function() {
	 
 
 

});  

var userExtraDialog=$( "#userextra_form" ).dialog({
    autoOpen: false,
    height: 400,
    width: 400,
    modal: true,
    buttons: {
      "<s:property value='%{#addUserExtra}' escape='false'/>": saveUserExtra,
      "<s:property value='%{#closeUserExtra}' escape='false'/>": function() {
    	  userExtraDialog.dialog( "close" );
      }
    },
    close: function() {      
      
    }
  });




function addUserExtra()
{
	  $("#extraId").val(0);
	  $("#extraPropId").val('');
	  $("#extraValue").val("");
	  $("#extraRemark").val("");
  
	  userExtraDialog.dialog( "open" );  
}


 
 function saveUserExtra()
 {
    
	  var params = {
			  id:$("#extraId").val(),
			  userId:$("#id").val(),
			  propId:$("#extraPropId").val(),
			  propName:$("#extraPropId").find("option:selected").text(),
			  value:$("#extraValue").val(),
			  remark:$("#extraRemark").val()
		  };
	 
 
	  var id=$("#extraId").val();
	  
	  $.ajax({  
          url:'userextra_save.action',  
          type:'post',  
          data:{data:encodeURI(JSON2.stringify(params))},
          dataType:'json',  
          success:function (data) {  
        	  if (id==0)
        	  {
        		  addToUserExtraBody(data);        		  
        	  }
        	  else
        	  {
        		  refreshUserExtraTr(data);  
        	  }
        	  
        	  
          }  
      });  
	  
}
 
 
 
 function addToUserExtraBody(data)
 {
	 var newtrhtml="<tr id='tptruserextra"+data.id+"'>"+
	 "<td style='text-align:center'><input type='checkbox' name='tpuserextraids' value="+data.id+"/></td>"+
	 "<td>"+data.propName+"</td>"+
	 "<td>"+data.value+
	 "<div id='tpuserextraoperate'"+data.id+"' class='d-rowactions'>"+
	 "<a href='#' onclick='javascript: editUserExtra("+data.id+")'><delmar:message key='userextra.operate.edit'/></a>|"+
	 "<a href='#' onclick='javascript: deleteUserExtra("+data.id+")'><delmar:message key='userextra.operate.delete'/></a>"+			 
	 "</div></td>"+	
	 "<td>"+data.remark+"</td>"+
	 "</tr>";
	 
	 $( "#detailuserextra" ).append(newtrhtml);

	 userExtraDialog.dialog( "close" );	 
 }
 
 
 function refreshUserExtraTr(data)
 {
  
      $("#tptruserextra"+data.id).find("td:eq(1)").text(data.propName);
      $("#tptruserextra"+data.id).find("td:eq(2)").find("span:eq(0)").text(data.value);
      $("#tptruserextra"+data.id).find("td:eq(3)").text(data.remark);
      
    

      userExtraDialog.dialog( "close" );	 
 } 
 
  
  function editUserExtra(id)
  {
	  $.getJSON("userextra_edit.action?id="+id,
		   function(data){
		      fullUserExtra(data);
		      
		 });	  
  }
  
  
  function fullUserExtra(data)
  {
	  $("#extraId").val(data.id);
	  $("#extraPropId").val(data.propId);
	  $("#extraValue").val(data.value);
	  $("#extraRemark").val(data.remark);
	  
	  userExtraDialog.dialog( "open" );	  
	  
  }
  
 
  function deleteUserExtra(id)
  {
	  $.post("userextra_delete.action?id="+id, 
		  function(data){
		     $("#tptruserextra"+id).remove();     
		 });	 	  
  }
  
  

  
</script>

</body>
</html>
