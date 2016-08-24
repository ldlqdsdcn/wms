<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="/commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>
<fmt:message key="dymenuForm.title2"></fmt:message>
</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../dwr/interface/pageMenuDwr.js'></script>
 <script type='text/javascript' src='../dwr/engine.js'></script>
 <script type='text/javascript' src='../dwr/util.js'></script>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
</head>

<body >
<input type="hidden" id="id"/>

<table id="list" width="100%" border="0" cellpadding="0" cellspacing="1">
<tr class="query_one">


<td width="15%" class="td1"><fmt:message key="dymenuForm.label.sequence"></fmt:message></td>
<td>
	<input type="text" id="seqNo"/>
</td>

<td width="15%" class="td1"><fmt:message key="dymenuForm.label.name"></fmt:message></td>
<td>
<input type="text" id="name"/>


</td>
</tr>
<tr class="query_two">
<td width="15%" class="td1"><fmt:message key="dymenuForm.label.type"></fmt:message></td>
<td><fmt:message key="dymenuForm.label.type.menu"></fmt:message>
<input type="radio" name="menuType" value="Y"/>&nbsp;&nbsp;<fmt:message key="dymenuForm.label.type.hyperlink"></fmt:message><input type="radio" name="menuType" value="N"/> &nbsp;&nbsp;窗体<input type="radio" name="menuType" value="Z"/>
</td>
<td width="15%" class="td1">
<fmt:message key="dymenuForm.label.parentMenu"></fmt:message>
</td>
<td id="parentMenuIdTD">
<div id="parentMenuIdDIV"></div>
<input type="hidden" name="parentMenuId" id="parentMenuId"/>
</td>
</tr>


<tr class="query_two"><td width="15%" class="td1">
<fmt:message key="dymenuForm.label.linkaddress"></fmt:message>
</td><td colspan="3">
<input id='pageUrl' style="width:500px;"/>
</td></tr>
<tr class="query_one"><td width="15%" class="td1"><fmt:message key="dymenuForm.label.propertieskey"></fmt:message></td><td colspan="3">
<input id='messagekey' style="width:500px;"/>
</td></tr>
<tr class="query_two"><td width="15%" class="td1">
	<fmt:message key="dymenuForm.label.remark"></fmt:message>
</td><td colspan="3">
<input id='descr' style="width:500px;"/>
</td></tr>
<tr class="query_one"><td width="15%" class="td1">
<fmt:message key="dymenuForm.label.ico"></fmt:message>
</td><td colspan="3">
<input id='icoPath' style="width:500px;"/>
</td></tr>
				

                <td colspan="4" class="td_page_right">
                <input type="button" value="<fmt:message key="common.button.save"></fmt:message>" class="input_submit" onclick="javascript:saveForm()"/>		</td></tr>
                <tr><td align="center" style="color: red;" id="msg" colspan="4">
                        
											
						
</td></tr>
</table>







<script type="text/javascript">
	function saveForm()
	{
		var menu={};
		menu.id=dwr.util.getValue('id');
		if(menu.id=='') menu.id=null;
		else
		{
			menu.id=parseInt(menu.id,10);
		}
		menu.parentMenuId=dwr.util.getValue('parentMenuId');
		
		if(menu.parentMenuId=='')
		{
		 menu.parentMenuId=null;
		}
		else
		{
			menu.parentMenuId=parseInt(menu.parentMenuId,10);
		}
		menu.seqNo=dwr.util.getValue('seqNo');
		if(isEmpty(menu.seqNo))
		{
		document.getElementById('msg').innerHTML='<fmt:message key="dymenuForm.message.sequencenotnull"></fmt:message>';
		document.getElementById('seqNo').focus();
		return;
		}
		if(!isInt(menu.seqNo))
		{
		document.getElementById('msg').innerHTML='<fmt:message key="dymenuForm.message.sequenceisnumber"></fmt:message>';
		document.getElementById('seqNo').focus();
			return ;
		}
		else
		{
			var seqNo=parseInt(menu.seqNo,10);
			
			if(seqNo<0||seqNo>=99999999)
			{
				document.getElementById('msg').innerHTML='<fmt:message key="dymenuForm.message.sequencescare"></fmt:message>';
				document.getElementById('seqNo').focus();
				return;
			}
		}
		
		var name= document.getElementById('name');
		if(isEmpty(name.value))
		{
			document.getElementById('msg').innerHTML='<fmt:message key="dymenuForm.message.namenotnull"></fmt:message>';
			name.focus();
			return;
		}
		menu.name=name.value;
		
		menu.menuType=dwr.util.getValue('menuType');
		menu.icoPath=dwr.util.getValue('icoPath');
		
		if(menu.menuType=='N')
		{
			menu.pageUrl=dwr.util.getValue('pageUrl');
			if(isEmpty(menu.pageUrl))
			{
				document.getElementById('msg').innerHTML='<fmt:message key="dymenuForm.message.pageurlnotnull"></fmt:message>';
				document.getElementById('pageUrl').focus();
				return;
			}
		}
		
		var messagekey= document.getElementById('messagekey');
		if(isEmpty(messagekey.value))
		{
			/* document.getElementById('msg').innerHTML='属性文件键值不允许为空！';
			messagekey.focus();
			return; */
		}
		else
		{
			menu.messagekey=messagekey.value;
		}
				
		menu.descr=dwr.util.getValue('descr');
		document.getElementById('msg').innerHTML='';
		pageMenuDwr.savePagemenu(menu,function(data)
		{
			
			setMenuValue(data);
			window.parent.closeDialog(<%=request.getParameter("parentMenuId")%>);
		});	
	}
	function init(menuId,parentMenuId)
	{
	
		pageMenuDwr.getPageMenu(menuId,parentMenuId,function(data)
		{
			setMenuValue(data);
		});	
		
	}
	
	function setMenuValue(data)
	{
		dwr.util.setValue('id',data.id);
		if(data.parentMenu!=null)
		{
			document.getElementById('parentMenuIdDIV').innerHTML=data.parentMenu.name;
		}
		dwr.util.setValue('seqNo',data.seqNo);
		dwr.util.setValue('name',data.name);
		dwr.util.setValue('menuType',data.menuType);
		dwr.util.setValue('icoPath',data.icoPath);
		dwr.util.setValue('messagekey',data.messagekey);
		//dwr.util.setValue('target',data.target);
		dwr.util.setValue('pageUrl',data.pageUrl);
		dwr.util.setValue('descr',data.descr);
		dwr.util.setValue('parentMenuId',data.parentMenuId);
	}
	
	init(<%=request.getParameter("menuId")%>,<%=request.getParameter("parentMenuId")%>);
</script>

</body>
</html>
