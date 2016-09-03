<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<script type="text/javascript" src="../js/json2.js"></script>  

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />


<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<style type="text/css">
    .dialog-form label {margin-right:10px;width:100px;right:0px;} 
    .dialog-form input.text { margin-bottom:12px; width:95%; padding: .4em;display:block; }
    fieldset { padding:0; border:0; margin-top:25px; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

</head>
<body>

<s:hidden name="sysOrgId" id="sysOrgId" value="%{#request.sysOrgId}"/>		
	
			<table id="orgextraTable" class="table">
            	<thead>
            	<th>
            	<input type="checkbox"  onclick="selectAll('tporgextraids',this);"/>
            	</th>
            	<th ><delmar:message key="orgextra.column.propid"/></th>
				<th><delmar:message key="orgextra.column.value"/></th>
                <th><delmar:message key="orgextra.column.remark"/>
                <div style="display:inline-block;float:right">
                <a  href="#"  onclick="javascript:addOrgExtra()"><delmar:message key="orgextra.operate.add"/></a>
                </div>
                </th>				
            	</thead>
            	<tbody id="detailorgextra">
              	<s:iterator value="orgExtraList" status="st" var="tpa" >
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>" id="tptrorgextra<s:property value="%{#tpa.id}"/>">
		              	<td>
		              	<input type="checkbox" name="tporgextraids"  value="<s:property value="%{#tpa.id}"/>"  />
		              	</td>
		              	<td>
		           			<s:property value="%{#tpa.propName}"/>
		              	</td>
		              	<td>
		           			<span><s:property value="%{#tpa.value}"/></span>
							<div id="tporgextraoperate<s:property value="%{#tpa.id}"/>" class="d-rowactions">
			                <a  href="#"  onclick="javascript:editOrgExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="orgextra.operate.edit"/></a>|
		                    <span class="d-trash"><a href="#" onClick="deleteOrgExtra('<s:property value="%{#tpa.id}"/>')"><delmar:message key="orgextra.operate.delete"/></a></span>
                			</div> 
		              	</td>	
		                 <td>
		           			<s:property value="%{#tpa.remark}"/>
		              	</td>		
		              	
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
			


<div id="orgextra_form" title="<delmar:message key='orgextra.dialog.title'/>" >

 
  <form class="dialog-form" >
    <fieldset>
		<s:hidden id="extraId" name="extraId"/>
		<s:label for="propId"  value="%{#session.resource.get('orgextra.column.propid')}" />
		<s:select list="extraPropList" listKey="datadictId" listValue="name"  name="extraPropId" id="extraPropId" style="width:200px"></s:select>
		<p>
		<s:label for="extraValue"  value="%{#session.resource.get('orgextra.column.value')}" />
		<s:textfield name="extraValue" id="extraValue"/>
		<p>
		<s:label for="extraRemark"  value="%{#session.resource.get('orgextra.column.remark')}" />
		<s:textfield name="extraRemark" id="extraRemark"/>
		<p>
		<s:set name="addOrgExtra" value="%{#session.resource.get('common.button.save')}"/>
		<s:set name="closeOrgExtra" value="%{#session.resource.get('common.button.close')}"/>
	</fieldset>
  </form>
</div>





<script type="text/javascript">

$(document).ready(function() {
	 
 
 

});  

var orgExtraDialog=$( "#orgextra_form" ).dialog({
    autoOpen: false,
    height: 400,
    width: 400,
    modal: true,
    buttons: {
      "<s:property value='%{#addOrgExtra}' escape='false'/>": saveOrgExtra,
      "<s:property value='%{#closeOrgExtra}' escape='false'/>": function() {
    	  orgExtraDialog.dialog( "close" );
      }
    },
    close: function() {      
      
    }
  });




function addOrgExtra()
{
	  $("#extraId").val(0);
	  $("#extraPropId").val('');
	  $("#extraValue").val("");
	  $("#extraRemark").val("");
  
	  orgExtraDialog.dialog( "open" );  
}


 
 function saveOrgExtra()
 {
    
	  var params = {
			  id:$("#extraId").val(),
			  orgId:$("#sysOrgId").val(),
			  propId:$("#extraPropId").val(),
			  propName:$("#extraPropId").find("option:selected").text(),			  
			  value:$("#extraValue").val(),
			  remark:$("#extraRemark").val()
		  };
	 
	  var id=$("#extraId").val();
	  
	  $.ajax({  
          url:'orgextra_save.action',  
          type:'post',  
          data:{data:encodeURI(JSON2.stringify(params))},
          dataType:'json',  
          success:function (data) {  
        	  if (id==0)
        	  {
        		  addToOrgExtraBody(data);        		  
        	  }
        	  else
        	  {
        		  refreshOrgExtraTr(data);  
        	  }
        	  
        	  
          }  
      });  
	  
}
 
 
 
 function addToOrgExtraBody(data)
 {
	 var newtrhtml="<tr id='tptrorgextra"+data.id+"'>"+
	 "<td><input type='checkbox' name='tporgextraids' value="+data.id+"/></td>"+
	 "<td>"+data.propName+"</td>"+
	 "<td>"+data.value+
	 "<div id='tporgextraoperate'"+data.id+"' class='d-rowactions'>"+
	 "<a href='#' onclick='javascript: editOrgExtra("+data.id+")'><delmar:message key='orgextra.operate.edit'/></a>|"+
	 "<a href='#' onclick='javascript: deleteOrgExtra("+data.id+")'><delmar:message key='orgextra.operate.delete'/></a>"+			 
	 "</div></td>"+	
	 "<td>"+data.remark+"</td>"+
	 "</tr>";
	 
	 $( "#detailorgextra" ).append(newtrhtml);

	 orgExtraDialog.dialog( "close" );	 
 }
 
 
 function refreshOrgExtraTr(data)
 {
  
      $("#tptrorgextra"+data.id).find("td:eq(1)").text(data.propName);
      $("#tptrorgextra"+data.id).find("td:eq(2)").find("span:eq(0)").text(data.value);
      $("#tptrorgextra"+data.id).find("td:eq(3)").text(data.remark);
      
    

      orgExtraDialog.dialog( "close" );	 
 } 
 
  
  function editOrgExtra(id)
  {
	  $.getJSON("orgextra_edit.action?id="+id,
		   function(data){
		      fullOrgExtra(data);
		      
		 });	  
  }
  
  
  function fullOrgExtra(data)
  {
	  $("#extraId").val(data.id);
	  $("#extraPropId").val(data.propId);
	  $("#extraValue").val(data.value);
	  $("#extraRemark").val(data.remark);
	  
	  orgExtraDialog.dialog( "open" );	  
	  
  }
  
 
  function deleteOrgExtra(id)
  {
	  $.post("orgextra_delete.action?id="+id, 
		  function(data){
		     $("#tptrorgextra"+id).remove();     
		 });	 	  
  }
  
  

  
</script>

</body>
</html>
