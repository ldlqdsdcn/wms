

var callbackFunc;
function uploadFileDelmar(tablename,tableid,callbackfunc,titleValue)

{
	callbackFunc=callbackfunc;
	var uploadFileDiv;
	if (document.all.uploadFileDiv==null)
	{
	    uploadFileDiv = document.createElement('div');
	    uploadFileDiv.id = 'uploadFileDiv';
	    uploadFileDiv.innerHTML='<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="fileIframe"></iframe>';
		document.body.appendChild(uploadFileDiv);

		 $("#uploadFileDiv").dialog({
				autoOpen: false,
				height: 500,
				width: 700,
				modal: true,
				title:titleValue,
				resizable:false});
		
	}


    document.getElementById('fileIframe').src="../commons/fileupload.jsp?tablename="+tablename+"&tableid="+tableid;


    
	$('#uploadFileDiv').dialog('open');    
}
function viewFileList(titleValue)
{
	var uploadFileDiv;
	if (document.all.uploadFileDiv==null)
	{
	    uploadFileDiv = document.createElement('div');
	    uploadFileDiv.id = 'uploadFileDiv';
	    uploadFileDiv.innerHTML='<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="fileIframe"></iframe>';
		document.body.appendChild(uploadFileDiv);

		 $("#uploadFileDiv").dialog({
				autoOpen: false,
				height: 500,
				width: 700,
				modal: true,
				title:titleValue,
				resizable:false});
		
	}
    document.getElementById('fileIframe').src="../commons/commonFile_list.action";
	$('#uploadFileDiv').dialog('open');    
}


function viewFileDescr(id,titleValue)
{
	var uploadFileDiv;
	if (document.all.uploadFileDiv==null)
	{
	    uploadFileDiv = document.createElement('div');
	    uploadFileDiv.id = 'uploadFileDiv';
	    uploadFileDiv.innerHTML='<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="fileIframe"></iframe>';
		document.body.appendChild(uploadFileDiv);

		 $("#uploadFileDiv").dialog({
				autoOpen: false,
				height: 500,
				width: 700,
				modal: true,
				title:titleValue,
				resizable:false});
		
	}
    document.getElementById('fileIframe').src="../commons/commonFile_edit.action?id="+id;
	$('#uploadFileDiv').dialog('open');    
}
function closeFileDialogSelf(id)
{
	$("#uploadFileDiv").dialog('close');
	
	if (callbackFunc!=null)
		callbackFunc;
	
	var location=window.location+"";
	var index=location.indexOf("=");
	if(index==-1)
	{
		location=location+"?id="+id;
		
	}
	window.location=location;
}
