<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

			
			<table id="clientextraTable" class="table">
            	<thead>
            	<th style="width:30">
            	<input type="checkbox"  onclick="selectAll('tpclientextraids',this);"/>
            	</th>
            	<th ><delmar:message key="clientextra.column.propid"/></th>
				<th><delmar:message key="clientextra.column.value"/></th>
                <th><delmar:message key="clientextra.column.remark"/>
                <div style="display:inline-block;float:right">
                <a  href="#"  onclick="javascript:addClientExtra()"><delmar:message key="clientextra.operate.add"/></a>
                </div>
                </th>				
            	</thead>
            	<tbody id="detailclientextra">
              	<s:iterator value="clientExtraList" status="st" var="tpa" >
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>" id="tptrclientextra<s:property value="%{#tpa.id}"/>">
		              	<td  style='text-align:center'>
		              	<input type="checkbox" name="tpclientextraids"  value="<s:property value="%{#tpa.id}"/>"  />
		              	</td>
		              	<td>
		           			<s:property value="%{#tpa.propName}"/>
		              	</td>
		              	<td>
		           			<span><s:property value="%{#tpa.value}"/></span>
							<div id="tpclientextraoperate<s:property value="%{#tpa.id}"/>" class="d-rowactions">
			                <a  href="#"  onclick="javascript:editClientExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="clientextra.operate.edit"/></a>|
		                    <span class="d-trash"><a href="#" onClick="deleteClientExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="clientextra.operate.delete"/></a></span>
                			</div> 
		              	</td>	
		                 <td>
		           			<s:property value="%{#tpa.remark}"/>
		              	</td>		
		              	
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
			


<div id="clientextra_form" title="<delmar:message key='clientextra.dialog.title'/>" >

 
  <form class="dialog-form" >
    <fieldset>
		<s:hidden id="extraId" name="extraId"></s:hidden>
		<s:label for="propId"  value="%{#session.resource.get('clientextra.column.propid')}" ></s:label>
		<s:select list="extraPropList" listKey="datadictId" listValue="name"  name="extraPropId" id="extraPropId" style="width:200px"></s:select>
		<p>
		<s:label for="extraValue"  value="%{#session.resource.get('clientextra.column.value')}" ></s:label>
		<s:textfield name="extraValue" id="extraValue"></s:textfield>
		<p>
		<s:label for="extraRemark"  value="%{#session.resource.get('clientextra.column.remark')}" ></s:label>
		<s:textfield name="extraRemark" id="extraRemark"></s:textfield>
		<p>
		<s:set name="addClientExtra" value="%{#session.resource.get('common.button.save')}"/>
		<s:set name="closeClientExtra" value="%{#session.resource.get('common.button.close')}"/>
	</fieldset>
  </form>
</div>





<script type="text/javascript">

$(document).ready(function() {
	 
 
 

});  

var clientExtraDialog=$( "#clientextra_form" ).dialog({
    autoOpen: false,
    height: 400,
    width: 400,
    modal: true,
    buttons: {
      "<s:property value='%{#addClientExtra}' escape='false'/>": saveClientExtra,
      "<s:property value='%{#closeClientExtra}' escape='false'/>": function() {
    	  clientExtraDialog.dialog( "close" );
      }
    },
    close: function() {      
      
    }
  });




function addClientExtra()
{
	  $("#extraId").val(0);
	  $("#extraPropId").val('');
	  $("#extraValue").val("");
	  $("#extraRemark").val("");
  
	  clientExtraDialog.dialog( "open" );  
}


 
 function saveClientExtra()
 {
    
	  var params = {
			  id:$("#extraId").val(),
			  clientId:$("#id").val(),
			  propId:$("#extraPropId").val(),
			  propName:$("#extraPropId").find("option:selected").text(),			  
			  value:$("#extraValue").val(),
			  remark:$("#extraRemark").val()
		  };
	 
 
	  var id=$("#extraId").val();
	  
	  $.ajax({  
          url:'clientextra_save.action',  
          type:'post',  
          data:{data:encodeURI(JSON2.stringify(params))},
          dataType:'json',  
          success:function (data) {  
        	  if (id==0)
        	  {
        		  addToClientExtraBody(data);        		  
        	  }
        	  else
        	  {
        		  refreshClientExtraTr(data);  
        	  }
        	  
        	  
          }  
      });  
	  
}
 
 
 
 function addToClientExtraBody(data)
 {
	 var newtrhtml="<tr id='tptrclientextra"+data.id+"'>"+
	 "<td  style='text-align:center'><input type='checkbox' name='tpclientextraids' value="+data.id+"/></td>"+
	 "<td>"+data.propName+"</td>"+
	 "<td>"+data.value+
	 "<div id='tpclientextraoperate'"+data.id+"' class='d-rowactions'>"+
	 "<a href='#' onclick='javascript: editClientExtra("+data.id+")'><delmar:message key='clientextra.operate.edit'/></a>|"+
	 "<a href='#' onclick='javascript: deleteClientExtra("+data.id+")'><delmar:message key='clientextra.operate.delete'/></a>"+			 
	 "</div></td>"+	
	 "<td>"+data.remark+"</td>"+
	 "</tr>";
	 
	 $( "#detailclientextra" ).append(newtrhtml);

	 clientExtraDialog.dialog( "close" );	 
 }
 
 
 function refreshClientExtraTr(data)
 {
  
      $("#tptrclientextra"+data.id).find("td:eq(1)").text(data.propName);
      $("#tptrclientextra"+data.id).find("td:eq(2)").find("span:eq(0)").text(data.value);
      $("#tptrclientextra"+data.id).find("td:eq(3)").text(data.remark);
      
    

      clientExtraDialog.dialog( "close" );	 
 } 
 
  
  function editClientExtra(id)
  {
	  $.getJSON("clientextra_edit.action?id="+id,
		   function(data){
		      fullClientExtra(data);
		      
		 });	  
  }
  
  
  function fullClientExtra(data)
  {
	  $("#extraId").val(data.id);
	  $("#extraPropId").val(data.propId);
	  $("#extraValue").val(data.value);
	  $("#extraRemark").val(data.remark);
	  
	  clientExtraDialog.dialog( "open" );	  
	  
  }
  
 
  function deleteClientExtra(id)
  {
	  $.post("clientextra_delete.action?id="+id, 
		  function(data){
		     $("#tptrclientextra"+id).remove();     
		 });	 	  
  }
  
  

  
</script>

</body>
</html>
