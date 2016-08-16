<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <script type='text/javascript' src='../dwr/interface/pageMenuDwr.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
   <script type='text/javascript' src='../js/ea.effect.js'></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<script type="text/javascript" src="<c:url value="/js/jquery-1.4.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-ui-1.8.5.custom.min.js"/>"></script>
<link rel="Stylesheet" href="../css/smoothness/jquery-ui-1.8.5.custom.css" type="text/css" />
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />


 <style type="text/css" media="all">
		.folder{ cursor: pointer;}
		*{font-size: 12px;}
 </style>
 
 <script type="text/javascript">
 $(document).ready(function() {
		var fileTable= document.getElementById('list');
   		 var rows=fileTable.rows;
    	 var rowNum=rows.length;
    	 pageMenuDwr.getPagemenus(null,function(data)
    	 {
			insertRow(data,rowNum,'10');  	 
    	 });	
	
	$("#selectDiv").dialog({
			autoOpen: false,
			height: 300,
			width: 700,
			modal: true,
			title:'<fmt:message key="dymenuForm.title"></fmt:message>',
			resizable:false});

	highlightTableRows("list");
	});
	
	function openDialog(menuId,parentMenuId)
	{
		$('#selectDiv').dialog('open');
		document.getElementById('selectIframe').src='<c:url value='/system/dymenuForm.jsp'/>?menuId='+menuId+'&parentMenuId='+parentMenuId;
	}
	function closeDialog(parentMenuId)
 	{
 	
 		
 		$("#selectDiv").dialog('close');
 		var fileTable= document.getElementById('list');
	
		var inputs=document.getElementsByTagName('input');
		deleteRow(fileTable,parentMenuId,inputs);
		
		if(parentMenuId==null||parentMenuId==0)
		{
		 pageMenuDwr.getPagemenus(null,function(data)
    	 {
    	 	var rows=fileTable.rows;
    	 	var rowNum=rows.length;
			insertRow(data,rowNum,'10');  	 
    	 });
    	 
    	 
		}
		else
		{
		var tr=document.getElementById(parentMenuId);
		var cell2=tr.cells[1]
		var img=cell2.getElementsByTagName('img')[0];
		
		if(img.src.indexOf('img-minus-')!=-1)
		{
			pageMenuDwr.getPagemenus(parentMenuId,function(data)
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
		$('#msg').html('<fmt:message key="dymenuForm.message.saveSuccess"></fmt:message>');
 	}
	
	function getChildMenu(img,menuId)
	{
		
		if(img.src.indexOf('/images/menu/img-plus-start.gif')!=-1)
		{
			 pageMenuDwr.getPagemenus(menuId,function(data)
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
			removeRow(menuId);
			img.src='../images/menu/img-plus-start.gif';
		}
	}
	
	
	function removeRow(menuId)
	{
	var fileTable= document.getElementById('list');
	
		var inputs=document.getElementsByTagName('input');
		deleteRow(fileTable,menuId,inputs);
	
	
	}
	function deleteRow(fileTable,menuId,inputs)
	{
			for( var i=inputs.length-1;i>=0;i--)
		{
			var input=inputs[i];
			
			if(input!=null&&input.name=='parentMenuId')
			{
				if(input.value==menuId+'')
				{
				
				  var tr=input.parentNode.parentNode;
				 if($.trim(tr.cells[4].innerHTML)=='菜单')
				 {
				  	var cell3=tr.cells[2];
				  	
				  	var menu_Id=cell3.getElementsByTagName('input')[0].value;
				 	
				 	deleteRow(fileTable,parseInt(menu_Id,10),inputs);
				 }
				  fileTable.deleteRow(tr.rowIndex);
     	     		
				}
			}
		}
	}
	function deleteMenu(menuId)
	{
	     if(confirm("<fmt:message key="dymenus.message.confirmdelete"></fmt:message>")){
		 pageMenuDwr.removeMenu(menuId,function(data)
		 {
		 	if(data=='N')
		 	{
		 		$('#msg').html('<fmt:message key="dymenus.message.deletehaschild"></fmt:message>');
		 		
		 	}
		 	else
		 	{		 	    
		 	    var fileTable= document.getElementById('list');
		 		
		 		var tr=document.getElementById(menuId+'');
		 		
		 		var cell=tr.cells[3];
		 		
		 		$('#msg').html('<fmt:message key="dymenus.message.deletesuccess1"></fmt:message>' +cell.innerHTML+ '<fmt:message key="dymenus.message.deletesuccess2"></fmt:message>');
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
					$(td1).css('text-align','center');
					$(td2).css('text-align','left');
					$(td2).css('padding-left',paddingleft);
					
					td3.innerHTML=""+da.seqNo+"<input type='hidden' value='"+da.id+"' />";
					td4.innerHTML=da.name;
					var t2html="<input type='hidden' value='"+paddingleft+"'/>";
					if(da.menuType=='Y')
					{
					 		td5.innerHTML='<fmt:message key="dymenus.label.type.menu"></fmt:message>';
					 		td6.innerHTML='';
					 		td1.innerHTML="<img src='../images/ico/dtladd.gif' class='folder' alt='添加' onclick=\"javascript:openDialog('null','"+da.id+"');\"/> ";
					 		td2.innerHTML=t2html+"<img src='../images/menu/img-plus-start.gif' class='folder'  onclick='javascript:getChildMenu(this,"+da.id+")' /><input type='hidden' name='parentMenuId' value='"+da.parentMenuId+"'/> ";
					}
					else
					{
					 		td2.innerHTML=t2html+"<img src='../images/menu/img-minus-start.gif'  /> <input type='hidden' name='parentMenuId' value='"+da.parentMenuId+"'/>";
					 		td5.innerHTML='<fmt:message key="dymenus.label.type.hyperlink"></fmt:message>';
					 		td6.innerHTML=da.pageUrl==null?"":da.pageUrl;
					 }
					 	td7.innerHTML=da.descr==null?"":da.descr;
					 	td8.innerHTML="<img src='../images/menu/edit.gif' class='folder' alt=' <fmt:message key="common.label.edit"></fmt:message>'  onclick=\"javascript:openDialog('"+da.id+"','"+da.parentMenuId+"');\"/>";
					 	
					 	td9.innerHTML="<img src='../images/ico/dtljian.gif' class='folder' alt=' <fmt:message key="common.label.delete"></fmt:message>' onclick=\"javascript:deleteMenu("+da.id+");\" />";
					 	$(td8).css('text-align','center');
					 	$(td9).css('text-align','center');
						newTr.appendChild(td1);
						newTr.appendChild(td2);
						newTr.appendChild(td3);
						newTr.appendChild(td4);
						newTr.appendChild(td5);
						newTr.appendChild(td6);
						newTr.appendChild(td7);
						newTr.appendChild(td8);
						newTr.appendChild(td9);
			}
			
		}	  
 	}
 </script>

<title>
<fmt:message key="dymenus.title"></fmt:message>
</title>
</head>


<body >


<table width="100%" border="0" cellspacing="0" cellpadding="5">

<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig"><fmt:message key="common.label.location"></fmt:message>
          	<fmt:message key="dymenus.location"></fmt:message>
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

<th style="width:50px;"><img src='../images/ico/dtladd.gif' onclick="javascript:openDialog('null','null');" class="folder" alt="<fmt:message key="dymenus.button.add"/>"/> </th>
<th style="width: 70px;padding-left: 10px;text-align: left;" ><img src='../images/menu/folder-open.gif' />
	
 </th>
<th width="60"><fmt:message key="dymenus.label.sequence"/></th>
<th width="150"><fmt:message key="dymenu.label.menuname"/></th>
<th width="50">
	<fmt:message key="dymenus.label.type"></fmt:message>
</th>

<th width="200px"><fmt:message key="dymenus.label.linkaddress"></fmt:message></th>
<th ><fmt:message key="dymenus.label.remark"></fmt:message></th>
<th ><fmt:message key="dymenus.label.edit"></fmt:message></th>
<th ><fmt:message key="common.button.delete"></fmt:message></th>
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

  
