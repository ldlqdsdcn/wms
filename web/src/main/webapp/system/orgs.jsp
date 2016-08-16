<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.delmarcargo.cn" prefix="delmar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <script type='text/javascript' src='../dwr/interface/orgDwr.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
  <script type='text/javascript' src='../js/ea.effect.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>






<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />


<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>


 <style type="text/css" media="all">
		.folder{ cursor: pointer;}
		*{font-size: 12px;}
		

		
 </style>
 
 <script type="text/javascript">
 $(document).ready(function() {
		var fileTable= document.getElementById('list');
   		 var rows=fileTable.rows;
    	 var rowNum=rows.length;
    	 orgDwr.getOrgs(null,function(data)
    	 {
			insertRow(data,rowNum,'10');  	 
    	 });	
	
	$("#selectDiv").dialog({
			autoOpen: false,
			height: 500,
			width: 700,
			modal: true,
			title:"<delmar:message key="org.location" />",
			resizable:false});

			highlightTableRows("list");
	}
	
	
	);
	
	function openDialog(orgId,parentOrgId)
	{
		$('#selectDiv').dialog('open');
		document.getElementById('selectIframe').src='<c:url value='/system/orgForm.jsp'/>?orgId='+orgId+'&parentOrgId='+parentOrgId;
	}
	
	function openExtraDialog(orgId)
	{
		$('#selectDiv').dialog('open');
		document.getElementById('selectIframe').src='<c:url value='/system/orgextras_list.action'/>?orgId='+orgId;
	}
	
	function closeDialog(parentOrgId)
 	{
 	
 		
 		$("#selectDiv").dialog('close');
 		var fileTable= document.getElementById('list');
	
		var inputs=document.getElementsByTagName('input');
		deleteRow(fileTable,parentOrgId,inputs);
		if(isEmpty(parentOrgId))
			{
			parentOrgId=null;
			}
		if(parentOrgId==null)
		{
		 orgDwr.getOrgs(null,function(data)
    	 {
			 //alert("fileTable.rows="+fileTable.rows.length);
			 for(var i=fileTable.rows.length-1;i>=0;i--)
				 {
				  fileTable.deleteRow(i);
				 }
			 
    	 	var rows=fileTable.rows;
    	 	var rowNum=rows.length;
			insertRow(data,rowNum,'10');  	 
    	 });
    	 
    	 
		}
		else
		{
		var tr=document.getElementById(parentOrgId);
		var cell2=tr.cells[1];
		var img=cell2.getElementsByTagName('img')[0];
		if(img.src.indexOf('/images/menu/img-minus-')!=-1)
		{
			orgDwr.getOrgs(parentOrgId,function(data)
    	 {
    	 	if(data!=null)
    	 	{
    	 		var paddingleft=parseInt(cell2.getElementsByTagName('input')[0].value,10)+10;
    	 		var rowNum= tr.rowIndex+1;
				insertRow(data,rowNum,paddingleft);
				//img.src='../images/menu/img-plus-cont.gif';
    	 	}
    	 		 
    	 });
		}
		}
		$('#msg').html('<delmar:message key="common.message.save" />');
 	}
	
	function getSubDepartment(img,orgId)
	{
		
		if(img.src.indexOf('img-plus-start.gif')!=-1)
		{
			 orgDwr.getOrgs(orgId,function(data)
    	 {
    	 	if(data!=null)
    	 	{
    	 		var cell2=img.parentNode;
    	 		
    	 		var paddingleft=parseInt(cell2.getElementsByTagName('input')[0].value,10)+10;
    	 		var tr=img.parentNode.parentNode;
    	 		var rowNum= tr.rowIndex+1;
			insertRow(data,rowNum,paddingleft);  
			img.src='../images/menu/img-minus-cont.gif';
    	 	}
    	 		 
    	 });
		}
		else
		{
			removeRow(depaId);
			img.src='../images/menu/img-plus-start.gif';
		}
	}
	
	
	function removeRow(depaId)
	{
	var fileTable= document.getElementById('list');
	
		var inputs=document.getElementsByTagName('input');
		
		deleteRow(fileTable,depaId,inputs);
	
	
	}
	function deleteRow(fileTable,depaId,inputs)
	{
			for( var i=inputs.length-1;i>=0;i--)
		{
			var input=inputs[i];
			
			if(input!=null&&input.name=='superDepaId')
			{
				
				if(input.value==depaId+'')
				{
				
				  var tr=input.parentNode.parentNode;
				  
				  var cell5=tr.cells[4];
				  var dalevel=cell5.getElementsByTagName('input')[0].value;
				  
				 if(dalevel.value!='4')
				 {
				  	var cell3=tr.cells[2];
				  	
				  	var depa_Id=cell3.getElementsByTagName('input')[0].value;
				 	
				 	deleteRow(fileTable,parseInt(depa_Id,10),inputs);
				 }
				  fileTable.deleteRow(tr.rowIndex);
     	     		
				}
			}
		}
	}
	function deleteDepartment(depaId)
	{
	     if(confirm("<delmar:message key="org.message.confirmdelete" />")){
		 orgDwr.removeDepartment(depaId,function(data)
		 {
		 	if(data=='N')
		 	{
		 		$('#msg').html('<delmar:message key="org.message.orghaschildren" />');
		 	}
		 	else
		 	{		 	    
		 	    var fileTable= document.getElementById('list');
		 		
		 		var tr=document.getElementById(depaId+'');
		 		
		 		var cell=tr.cells[2];
		 		
		 		$('#msg').html('<delmar:message key="org.message.delsuccess1" />' +cell.innerHTML+ '<delmar:message key="org.message.delsuccess2" />');
				fileTable.deleteRow(tr.rowIndex);
		 	}
		 });		 
		 }		
	}
 	function insertRow(data,rowNum,paddingleft)
 	{
 		if(data!=null)
 		{
	 		var fileTable= document.getElementById('list');
	 		for(var i=0;i<data.length;i++)
			{
					var da=data[i];
					var rowIndex=rowNum+i;
					 var newTr = fileTable.insertRow(rowIndex);
					 $(newTr).attr('id',da.id);
					 rowIndex%2==0?$(newTr).attr('class','odd'):$(newTr).attr('class','even');
					 var td1=document.createElement("td");
					 var td2=document.createElement("td");
					 var td3=document.createElement("td");
					 var td4=document.createElement("td");
					 var td5=document.createElement("td");
					 var td6=document.createElement("td");
					 var td7=document.createElement("td");
					 var td8=document.createElement("td");
					 var td9=document.createElement("td");
					 var td10=document.createElement("td");
					 var td11=document.createElement("td");					 
					$(td1).css('text-align','center');
					$(td2).css('text-align','left');
					$(td2).css('padding-left',paddingleft);
					td3.innerHTML=da.value;
					td4.innerHTML=""+da.name+"<input type='hidden' value='"+da.id+"' />";
					td5.innerHTML=da.client.name;
					
					td6.innerHTML=da.remark;
					var t2html="<input type='hidden' value='"+paddingleft+"'/>";
					td7.innerHTML=da.orgLevel+"<input type='hidden' value='"+da.orgLevel+"'/>";
					if(da.orgLevel<4)
					{
					 		
					 		
					 		td1.innerHTML="<img src='../images/ico/dtladd.gif' class='folder' alt='添加' onclick=\"javascript:openDialog('null','"+da.id+"');\"/> ";
					 		
					 		td2.innerHTML=t2html+"<img src='../images/menu/img-plus-start.gif' class='folder'  onclick='javascript:getSubDepartment(this,"+da.id+")' /><input type='hidden' name='superDepaId' value='"+da.parentOrgId+"'/> ";
					}
					else
					{
							
					 		td2.innerHTML=t2html+"<img src='../images/menu/img-minus-start.gif'  /> <input type='hidden' name='superDepaId' value='"+da.parentOrgId+"'/>";
					 		
					 }
					 if(da.superDepa!=null)
					 {
					 td8.innerHTML=da.superDepa.name;
					 }
					 	
					    td9.innerHTML="<img src='../images/menu/edit.gif' class='folder' alt='<delmar:message key="org.button.edit" />'  onclick=\"javascript:openDialog('"+da.id+"','"+da.parentOrgId+"');\"/>";
					    td10.innerHTML="<img src='../images/Settings.png' class='folder' alt='<delmar:message key="org.button.extraprop" />'  onclick=\"javascript:openExtraDialog('"+da.id+"');\"/>";
					 	
					 	td11.innerHTML="<img src='../images/ico/dtljian.gif' class='folder' alt='<delmar:message key="common.button.delete" />' onclick=\"javascript:deleteDepartment("+da.id+");\" />";
					 	$(td9).css('text-align','center');
					 	$(td10).css('text-align','center');
					 	$(td11).css('text-align','center');
						newTr.appendChild(td1);
						newTr.appendChild(td2);
						newTr.appendChild(td3);
						newTr.appendChild(td4);
						newTr.appendChild(td5);
						newTr.appendChild(td6);
						newTr.appendChild(td7);
						newTr.appendChild(td8);
						newTr.appendChild(td9);
						newTr.appendChild(td10);
						newTr.appendChild(td11);
						
			}
			
		}	  
 	}
 </script>
<title>
	<delmar:message key="org.title" />
</title>
</head>


<body >


<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
			<delmar:message key="org.location" />
</td>
          <td class="navig" align="right"> <table id="normalQuery" cellpadding="0" cellspacing="0" border="0">
              <tr> 
                <td >
                  
                 </td>
              </tr>
            </table></td>
         
        </tr>
      </table> 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->
 <table id="list" cellpadding="0" class="table" cellspacing="0">
<thead>
<tr>
<th style="width:50px;"><img src='../images/ico/dtladd.gif' onclick="javascript:openDialog('null','null');" class="folder" alt="添加"/> </th>
<th style="width: 70px;padding-left: 10px;text-align: left;" ><img src='../images/menu/folder-open.gif' />
	
 </th>
 <th width="100px"><delmar:message key="org.column.value" /></th>
<th width="200"><delmar:message key="org.column.name" /></th>
<th><delmar:message key="org.column.client" /></th>
<th width="150"><delmar:message key="org.column.remark" /></th>
<th width="80"><delmar:message key="org.column.level" /></th>

<th width="200px"><delmar:message key="org.column.parentorg" /></th>
<th ><delmar:message key="org.button.edit" /></th>
<th ><delmar:message key="org.button.extraprop" /></th>
<th ><delmar:message key="common.button.delete" /></th>
</tr></thead>
</table>  





</td>
</tr>
  <tr>
<td id="msg" style="text-align: center;color: red;"></td>
</tr>
</table>




  <div id="selectDiv">
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>

</body>
</html>

  
