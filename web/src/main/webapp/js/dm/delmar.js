/**
 * 获取浏览器
 * 
 * IE:  IE
 * FIRFOX  FF
 * CHROME */

function getBrowserInfo()
{
	var brow=$.browser;
	var bInfoDetail="";
	var bInfo="";
	if(brow.msie) {bInfo="IE";bInfoDetail="Microsoft Internet Explorer "+brow.version;}
	if(brow.mozilla) {bInfo="FF";bInfoDetail="Mozilla Firefox "+brow.version;}
	if(brow.safari) {bInfo="SI";bInfoDetail="Apple Safari "+brow.version;}
	if(brow.opera) {bInfo="OP";bInfoDetail="Opera "+brow.version;}
	return bInfo;
	 
}

function getBrowserDetailInfo()
{
	var brow=$.browser;
	var bInfoDetail="";
	var bInfo="";
	if(brow.msie) {bInfo="IE";bInfoDetail="Microsoft Internet Explorer "+brow.version;}
	if(brow.mozilla) {bInfo="FF";bInfoDetail="Mozilla Firefox "+brow.version;}
	if(brow.safari) {bInfo="SI";bInfoDetail="Apple Safari "+brow.version;}
	if(brow.opera) {bInfo="OP";bInfoDetail="Opera "+brow.version;}
	

	return bInfoDetail;
	 
}


/**
 * 字符类函数*/


/**
*根据字符和长度填充原来的数值，在前面进行填充
*/
function fullLengthStr(str,strlen,fullchar)
{
	var destStr="";
	for (var i=0;i<strlen-str.length;i++ )
	{
		destStr=destStr+fullchar;
	}

	destStr=destStr+str;


   	return destStr;
}


/**检查是否日时间类型*/
function CheckTime(TimeStr){
	var reg = /^(\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var r = TimeStr.match(reg); 
	if(r==null)return false; 
		var d= new Date('1900','01','01', r[1],r[2], r[3]); 
	if(d.getHours()!=r[1])return false; 
	if(d.getMinutes()!=r[2])return false; 
	if(d.getSeconds()!=r[3])return false; 
	return true; 
}

/**检查是否日时间类型，同时把相应的对象置为正确的格式*/
function CheckFormatTime(obj,TimeStr){
	var reg = /^(\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
	var r = TimeStr.match(reg); 
	if(r==null)return false; 
		var d= new Date('1900','01','01', r[1],r[2], r[3]); 
	if(d.getHours()!=r[1])return false; 
	if(d.getMinutes()!=r[2])return false; 
	if(d.getSeconds()!=r[3])return false; 
	obj.value=dt2tmstr(d);
	return true; 
}


function dt2tmstr (dt_datetime) {
	var vHour=dt_datetime.getHours();
	vHour = (vHour.toString().length < 2) ? "0" + vHour : vHour;
	var vMinute=dt_datetime.getMinutes();
	vMinute = (vMinute.toString().length < 2) ? "0" + vMinute : vMinute;	
	var vSecond=dt_datetime.getSeconds();
	vSecond = (vSecond.toString().length < 2) ? "0" + vSecond : vSecond;	
	return (new String (vHour+":"+vMinute+":"+vSecond));
}

/**检查日期类型 */

function CheckDate(DateStr){
	var ddd=DateStr;
	var reg = /^(\d{4})-(\d{1,2})-(\d{1,2})$/; 
	var r = DateStr.match(reg); 
	if(r==null)return false; 
	r[2]=r[2]-1; 
	var d= new Date(r[1], r[2],r[3]); 
	if(d.getFullYear()!=r[1])return false; 
	if(d.getMonth()!=r[2])return false; 
	if(d.getDate()!=r[3])return false; 
	return true; 
}

/**检查日期类型 同时把相应的对象附上正确的值*/
function CheckFormatDate(obj,DateStr){
	var reg = /^(\d{4})-(\d{1,2})-(\d{1,2}) (\d{1,2})$/; 
	var r = DateStr.match(reg); 
	if(r==null)return false; 
	r[2]=r[2]-1; 
	var d= new Date(r[1], r[2],r[3]); 
	if(d.getFullYear()!=r[1])return false; 
	if(d.getMonth()!=r[2])return false; 
	if(d.getDate()!=r[3])return false; 
	obj.value=dt2dtstr(d);
	return true; 
}

function dt2dtstr (dt_datetime) {
	var vYear=dt_datetime.getFullYear();
	var vMonth=dt_datetime.getMonth()+1;
	vMonth = (vMonth.toString().length < 2) ? "0" + vMonth : vMonth;
	var vDay=dt_datetime.getDate();
	vDay = (vDay.toString().length < 2) ? "0" + vDay : vDay;
	
	return (new String (vYear+"-"+vMonth+"-"+vDay));
}

/**得到当前的日期和时间 前端的*/
function getCurrDateTimeStr() {
	var dt_datetime=new Date();
	var vYear=dt_datetime.getFullYear();
	var vMonth=dt_datetime.getMonth()+1;
	vMonth = (vMonth.toString().length < 2) ? "0" + vMonth : vMonth;
	var vDay=dt_datetime.getDate();
	vDay = (vDay.toString().length < 2) ? "0" + vDay : vDay;

	var vHour=dt_datetime.getHours();
	vHour = (vHour.toString().length < 2) ? "0" + vHour : vHour;
	var vMinute=dt_datetime.getMinutes();
	vMinute = (vMinute.toString().length < 2) ? "0" + vMinute : vMinute;	
	var vSecond=dt_datetime.getSeconds();
	vSecond = (vSecond.toString().length < 2) ? "0" + vSecond : vSecond;	
	return (""+vYear+vMonth+vDay+vHour+vMinute+vSecond+"");
}



/*
* 获得时间差,时间格式为 年-月-日 小时:分钟:秒 或者 年/月/日 小时：分钟：秒
* 其中，年月日为全格式，例如 ： 2010-10-12 01:00:00
* 返回精度为：秒，分，小时，天
*/
function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));

}

 


/**检查数字类型*/
function validateInteger (value)
{
	
	var reg = /^-?[0-9]\d*$/; 
	var r = value.match(reg); 
	if(r==null)return false; 	
	
	return true;	
}

function validateIntegerObj (obj)
{
	var reg = /^-?[0-9]\d*$/; 
	var value=$(obj).val();
	
	if (value=="")
		return;	
	var r = value.match(reg); 
	if(r==null)
	 {
		var title=$(obj).attr('title');
				
		showWarnFocus(title,obj);
	 }
	
		
}


/**检查含有小数点的数字*/
function validateNumber (value)
{
	
	var reg = /(\d+)\.?\d*$/; 
	var r = value.match(reg); 
	if(r==null)return false; 	
	
	return true;	
}


function validateNumberObj (obj)
{
	
	var reg = /(\d+)\.?\d*$/; 
	var value=$(obj).val();
	
	if (value=="")
		return;	
	
	var r = value.match(reg); 
	if(r==null)
	 {
		
		var title=$(obj).attr('title');
		showWarnFocus(title,obj);		
	
	 }
	
		
}



/**只能够录入数字的*/
function keypressNumber(event)
{
	//return event.keyCode>=48&&event.keyCode<=57;	
}


/**检查电子邮件*/
function validateEMail(value)
{
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var r = value.match(reg); 
	if(r==null)return false;
	
	return true;
	
}

function validateEMailObj(obj)
{
	var reg=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	
	var value=$(obj).val();
	
	if (value=="")
		return;	
	
	var r = value.match(reg); 
	if(r==null)
	 {
		var title=$(obj).attr('title');
		showWarnFocus(title,obj);		
	 }
}



/**配置网址的*/
function validateURL(value)
{
	var reg=/^http:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?$/;	
	var r = value.match(reg); 
	if(r==null)return false;
	
	return true;
	
}

/**匹配电话号码*/
function validateTelephone(value)
{
	
	var reg=/^\d{3,4}\-+\d+$/;	
	var r = value.match(reg); 
	

	if(r==null)return false;
	
	return true;	
}

function validateTelephoneObj(obj)
{
	var reg=/^\d{3,4}\-+\d+$/;	
	
	var value=$(obj).val();
	
	if (value=="")
		return;
	
	var r = value.match(reg); 
	
	if(r==null)
	 {
		var title=$(obj).attr('title');
		showWarnFocus(title,obj);		
	 }
	
}


/**匹配手机号*/
function validateMobile(value)
{
	
	var reg=/^\d{8,14}$/;	
	var r = value.match(reg); 
	if(r==null)return false;
	
	return true;	
}

function validateMobileObj(obj)
{
	var reg=/^\d{8,14}$/;	
	
	var value=$(obj).val();
	
	if (value=="")
		return;
	
	var r = value.match(reg); 
	if(r==null)
	 {
		var title=$(obj).attr('title');
		showWarnFocus(title,obj);		
	 }
	
}





/**功能*/
/**使查找的字符串全部变为红色*/
function DoHighLigntText(ObjText){
	
    var thetext=ObjText.value;
    if (thetext=="") {
    	   alert("查找内容为空，请录入查找内容");
    	   return;
    	}
    TheRange.expand("textedit"); 
    TheRange.collapse(); 
    while(TheRange.findText(thetext)){
       
       TheRange.execCommand('ForeColor',false,'ff0000');
       TheRange.moveStart('character',thetext.length);       
       TheRange.moveEnd('textedit');
    }
}


/**在模态窗体中查找字符串*/

function FindText(ObjText)
{
    var thetext=ObjText.value;
    if (thetext=="") {
    	   alert("查找内容为空，请录入查找内容");
    	   return;
    	}

    if (TheRange.findText(thetext)==true) {
    TheRange.select();
    TheRange.scrollIntoView();
    TheRange.moveStart('character',thetext.length);
    TheRange.moveEnd('textedit');
   }
   else
    {
     var donext=confirm("已到文件尾或者没有查到，请确认是否从头重新开始查找？");
     if (donext) {
          TheRange.expand("textedit"); 
          TheRange.collapse(); 
          TheRange.findText(thetext);
          TheRange.select();
          TheRange.scrollIntoView();
          TheRange.moveStart('character',thetext.length);
          TheRange.moveEnd('textedit');	          
        }
     }
}


/**页面当中的元素*/

/**定位页面当中的元素的位置，为另外一个元素定位*/
function getElementPosition(elementId)
{
  var objId=document.getElementById(elementId);
  if (objId) {}
  else
  {
	  return 0;
  }

  var oTop=objId.offsetTop;
  while (objId=objId.offsetParent)
  {
     oTop+=objId.offsetTop;
  }

  return oTop;
}


/**找到最近以及的父对象TR*/
function findRowTR(e) {

	  if (e.tagName == "TR") {
	    return e;
	  } else if (e.tagName == "BODY") {
	    return null;
	  } else {
	    return findRowTR(e.parentElement);
	  }
	}

//得到当前对象的坐标
function getXY(Obj) 
{
	for (var sumTop=0,sumLeft=0;Obj!=document.body;sumTop+=Obj.offsetTop,sumLeft+=Obj.offsetLeft, Obj=Obj.offsetParent);
	return {left:sumLeft,top:sumTop};
}


/**生成提示信息*/
/**
* 函数名：ShowError
* 功能：显示错误消息
*/  

function show_Error(sMessage,sObj)
{

	var sMsg="录入数据有错误......";
	var iWd=300;
	var iHg=70;
	var iLPos=$(sObj).position().left+10;
	var iTPos=$(sObj).position().top+$(sObj).height()+10;
	
	if ((sMessage)&&(sMessage!='')) sMsg=sMessage;
	
	var str_buffer = new String (
		"<span id='ErrorMessage' bgcolor=\"#AAAAAA\" style=\"font-family: 宋体; font-size: 12px;\">\n"+
		sMsg+
		"</span>");

	if (document.all.ErrorDiv==null)
	{
	    var ErrorDiv = document.createElement('div');
	    ErrorDiv.id = 'ErrorDiv';
	    ErrorDiv.style.filter="alpha(opacity=80)";
	    ErrorDiv.style.position="absolute";
	    ErrorDiv.style.zIndex ="10";
	    ErrorDiv.style.visibility="hidden";
	    ErrorDiv.innerHTML=str_buffer;
		document.body.appendChild(ErrorDiv);
		ErrorDiv.style.left=iLPos; 
		ErrorDiv.style.top=iTPos;
		ErrorDiv.style.width=iWd;
		ErrorDiv.style.height=iHg;
		ErrorDiv.style.visibility="visible";
		document.all.ErrorDiv.style.display="inline";		
		
	}else{

        if (document.all.ErrorMessage!=null)    
		{
			if ((sMessage)&&(sMessage!='')) 
			{
				document.all.ErrorMessage.innerHTML=sMessage;
			}
		}


		document.all.ErrorDiv.style.left=iLPos; 
		document.all.ErrorDiv.style.top=iTPos;
		document.all.ErrorDiv.style.width=iWd;
		document.all.ErrorDiv.style.height=iHg;
		document.all.ErrorDiv.style.visibility="visible";
		document.all.ErrorDiv.style.display="inline";

	}
	
	$('#ErrorDiv').delay(2000).hide(0);


}


function showError(sMessage,sObj)
{
	$.ligerDialog.error(sMessage);

}


function showWarn(sMessage,sObj)
{
	$.ligerDialog.warn(sMessage);
}

function showWarnFocus(sMessage,sObj)
{
	show_Warn(sMessage,sObj);
}


function showConfirm(sMessage,callbackyes,callbackno)
{
	$.ligerDialog.confirm(sMessage, function (yes) { 
		if (yes)
	 	{  
			if (callbackyes!=undefined)
			 callbackyes();
	 	} else
	 	{
	 		if (callbackno!=undefined)
	 	      callbackno();	
	 	}
      });
}




function show_Warn(sMessage,sObj)
{
	
	var str_buffer = new String (
			"<div style='display: block;' class='l-dialog-image l-dialog-image-warn'></div><div id='warnMessage' style='padding-left: 64px; padding-bottom: 30px;' class='l-dialog-content'>"+
			sMessage
			+"</div>");
	
   
	if (document.all.JQueryDialogDiv==null)
	{
	    var ErrorDiv = document.createElement('div');
	    ErrorDiv.id = 'JQueryDialogDiv';
	    ErrorDiv.title="Warn";
	    ErrorDiv.innerHTML=str_buffer;
		document.body.appendChild(ErrorDiv);
		
	} else
	{
			if ((sMessage)&&(sMessage!='')) 
			{
				document.all.warnMessage.innerHTML=sMessage;
			}
	
	}
	
    $( "#JQueryDialogDiv" ).dialog({
        modal: true,
        buttons: {
          Ok: function() {
            $( this ).dialog( "close" );
            if (sObj!=undefined)
            	$(sObj).focus();
          }
        }
      });


}


function showWaiting(message,autoClose)
{
	waitingDialog=$.ligerDialog.waitting(message); 
   if (autoClose)
     setTimeout(function () { $.ligerDialog.closeWaitting(); }, 2000);	
   
   return waitingDialog;
}


function trim(originString)
{
	 return originString.replace(/(^\s*)|(\s*$)/g, "");	
}



String.prototype.format = function(args) {
    var result = this;
    if (arguments.length > 0) {    
        if (arguments.length == 1 && typeof (args) == "object") {
            for (var key in args) {
                if(args[key]!=undefined){
                    var reg = new RegExp("({" + key + "})", "g");
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] != undefined) {
                    var reg = new RegExp("({[" + i + "]})", "g");
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
}

