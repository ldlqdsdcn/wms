
/**
 * Declare an object to which we can add real functions.
 */
if (powere2e == null) var powere2e = {};
if (powere2e.math == null) powere2e.math = {};
if (pMath == null) var pMath = powere2e.math;

powere2e.math.add=function(param1,param2,precision)
{
	var p1,p2;
	if(precision==null)
	{
		precision=2;
	}
	if(typeof(param1)=='string')
	{
		p1=parseFloat(param1,10);
	}
	else
	{
	 p1=param1;
	}
	
	if(typeof(param2)=='string')
	{
		p2=parseFloat(param2,10);
	}
	else
	{
	 p2=param2;
	}
	return new Number(p1+p2).toFixed(precision);
}
powere2e.math.subtract=function(param1,param2,precision)
{
	var p1,p2;
	if(precision==null)
	{
		precision=2;
	}
	if(typeof(param1)=='string')
	{
		p1=parseFloat(param1,10);
	}
	else
	{
	 p1=param1;
	}
	
	if(typeof(param2)=='string')
	{
		p2=parseFloat(param2,10);
	}
	else
	{
	 p2=param2;
	}
	return new Number(p1-p2).toFixed(precision);
}
powere2e.math.compare=function(param1,param2,precision)
{
	var p1,p2;
	if(precision==null)
	{
		precision=2;
	}
	if(typeof(param1)=='string')
	{
		p1=parseFloat(param1,10);
	}
	else
	{
	 p1=param1;
	}
	
	if(typeof(param2)=='string')
	{
		p2=parseFloat(param2,10);
	}
	else
	{
	 p2=param2;
	}
	var result=(new Number(p1).toFixed(precision)-Number(p2).toFixed(precision)).toFixed(precision);
	if(result>0) return 1;
	if(result<0) return -1;
	if(result==0) return 0;
	return 0;
}


function removeFileTr(pfileName,pfileRealName,id,rowno)
{
	var fileRealName=document.getElementById(pfileRealName);
	var fileName=document.getElementById(pfileName);
	if(fileRealName.value==null||fileRealName.value=='')
	{
		return false;
	}
	else
	{
	taTicketsAttachmentService.removeFile(fileRealName.value,id,function(data)
	{
		if(data=='Y')
		{
			alert('remove file success！');
			fileRealName.value='';
			fileName.value='';
			if(document.getElementById('attach_id'+rowno)!=null)
			document.getElementById('attach_id'+rowno).value='';
			if(rowno!='0')
			{
			fileRealName.parentNode.parentNode.parentNode.deleteRow(fileRealName.parentNode.parentNode.rowIndex);
				
			}
			else
			{	if(document.getElementById('download0')!=null)
				document.getElementById('download0').style.display='none';
			}
		}
	}
	);	
	}
}
function uploadfile(fileName,fileRealName)
{
	
		openwin("../commons/fileupload.jsp?fileName="+fileName+"&fileRealName="+fileRealName,600,300);
		
	
}
function amounttrans()
{
   var inputs = document.getElementsByTagName('input');
   for(var i=0;i<inputs.length;i++)
   {
      if(inputs[i].type=='text' && (inputs[i].name.indexOf('amount')!=-1 ||inputs[i].name.indexOf('Amount')!=-1 || inputs[i].name=='tic_fa_balance'||inputs[i].name=='tic_fa_paid'||inputs[i].name=='tic_fa_currentPaid'||inputs[i].name=='currentPaid')
        && inputs[i].name.indexOf('tic_fa_amountTime')==-1)
      {
         re=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g
         if(inputs[i].value!='' && inputs[i].value!=0)
         {
            inputs[i].value = (parseFloat(inputs[i].value).toFixed(2)).replace(re,"$1,");
         }
      }
   }
}
function formatAmount(obj)
{
		re=/(\d{1,3})(?=(\d{3})+(?:$|\.))/g;
         if(obj.value!='' && obj.value!=0)
         {
            obj.value = (parseFloat(obj.value).toFixed(2)).replace(re,"$1,");
         }
}
function unformatAmount(obj)
{
	obj.value  = (obj.value).replace(new RegExp(",","gm"),"");
}
function amountsave()
{
   var inputs = document.getElementsByTagName('input');
   for(var i=0;i<inputs.length;i++)
   {
      if(inputs[i].type=='text' && (inputs[i].name.indexOf('amount')!=-1 ||inputs[i].name.indexOf('Amount')!=-1 || inputs[i].name=='tic_fa_balance'||inputs[i].name=='tic_fa_paid'||inputs[i].name=='tic_fa_currentPaid'||inputs[i].name=='currentPaid'))
      {
         inputs[i].value  = (inputs[i].value).replace(new RegExp(",","gm"),"");
      }
   }
}
function disableAllInput()
{
	 var inputs = document.getElementsByTagName('input');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	if(inputs[i].type=='hidden')continue;
	 	if(inputs[i].id!=null&&inputs[i].id.indexOf('download')!=-1)
	 	{
	 	continue;
	 	}
	 	if(inputs[i].type=='text')
	 	{
	 	inputs[i].readOnly=true;
	 	continue;
	 	}
	 	else
	 	{
	 	inputs[i].disabled='disabled';
	 	}
	 }
	 
	 var inputs = document.getElementsByTagName('TEXTAREA');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	//inputs[i].disabled='disabled';
	 	inputs[i].readOnly=true;
	 }
	 
	 var inputs = document.getElementsByTagName('select');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	inputs[i].disabled='disabled';
	 	//inputs[i].readOnly=true;
	 }
	 var exitbutton = document.getElementById('exit');
	exitbutton.disabled="";
	var printbutton = document.getElementById('print');
	printbutton.disabled="";
}
function disableAllButton()
{

	 var inputs = document.getElementsByTagName('input');
	 for(var i=0;i<inputs.length;i++)
	 {
	 	if(inputs[i].type=='button')	 	
	 	inputs[i].disabled='disabled';
	 }
}

function checkChange(check)
{
  if(check.name=='tic_checkOne')
   {     
       if(document.getElementById("tic_checkOne").checked == true)
       {
           document.getElementById("tic_checkTwo").checked = false;
       }
       if(document.getElementById("tic_checkOne").checked == false)
       {
           document.getElementById("tic_checkOne").checked = false;
       }
   }
   if(check.name=='tic_checkTwo')
   {
       if(document.getElementById("tic_checkTwo").checked == true)
       {
	       document.getElementById("tic_checkOne").checked = false;
       }
       if(document.getElementById("tic_checkTwo").checked == false)
       {
           document.getElementById("tic_checkTwo").checked = false;
       }
   }
}
function unDisableInput(inputName,type)
{
	var obj=document.getElementById(inputName)
	//r ==readOnly -d disabled a auto
	if(type=='a')
	{
		unDisableObj(obj);
	}
	else if(type=='r')
	{
	obj.readOnly=false;
	}
	else if(type=='d')
	{
	   obj.disabled='';	
	   obj.readOnly=false;
	}
	
}
function unDisableObj(obj)
{
	if(obj==null)return;
	if(obj.tagName=='TEXTAREA')
		{
			obj.readOnly=false;
		}else if(obj.tagName=='INPUT')
		{
			if(obj.getAttribute('type')=='text')
			{
				obj.readOnly=false;
			}
			else
			{
				obj.disabled='';
			}
		}
		else
		{
			obj.disabled='';
		}
}

function userSelect()
{
 	if(document.getElementById("submitselectDiv")!=null)
	{
	   document.getElementById("submitselectDiv").style.display ="none"; 
	}
	document.getElementById("approve_user").style.display =""; 
	document.getElementById("approve_user").disabled =false; 
}
/**
 * srr,srfc,srcc 删除明细 更新 seq_id
 * @return {String}
 */
function getSeq_ID()
{
	var dtable=document.getElementById('detailtable');
	var id_seq="";
	var inputs=dtable.getElementsByTagName('input');
	var si=0;
	var ii=0;
	var idPix="ticDet_idt";
	for(var i=0;i<inputs.length;i++)
	{
		if(inputs[i].name.indexOf('squenceIdt')!=-1)
		{
				var seq_value=inputs[i].value;
				
				var name=inputs[i].name;
				var sb="squenceIdt";
				
				var ids="";
				var zol=name.substring(name.indexOf(sb)+sb.length,name.length);
				
				for(var j=0;j<inputs.length;j++)
				{
					
					if(inputs[j].name.indexOf(idPix+zol+'d')!=-1)
					{
					ids=ids+inputs[j].value+"-";
					}
				}
				if(ids=="")continue;
				
				id_seq=id_seq+seq_value+"="+ids+",";
				
		}
	}
	return id_seq;
}

