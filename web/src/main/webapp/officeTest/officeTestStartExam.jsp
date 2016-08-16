<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title><delmar:message key="customer.title" /></title>

<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />
<link rel="stylesheet" href="../js/jquery/plugin/ligerUI/skins/Aqua/css/ligerui-all.css" type="text/css" />
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/i18n/datepicker-<s:property value='#session.currentlanguageoriginal'/>.js"/></script>
<script type='text/javascript' src='../js/dm/delmar.js'></script>
<script type='text/javascript' src='../js/ea.effect.js'></script>
<script type='text/javascript' src='../js/ea.validate.js'></script>
<script  type="text/javascript" src="../js/jquery/plugin/ligerUI/js/ligerui.min.js"></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<style type="text/css">

.table tbody tr {
   vertical-align:text-top;
}


#recordList{
	position:absolute;
	width:800px;
	top:15px;
	background-color:lightyellow;
	border:3px solid #7C96E2;
	padding:0px;
	display:none;
}

</style>

<script type="text/javascript">
var str_kstesttime = "<s:property value="kstesttime"/>";
var reminder = "<s:property value="reminder"/>";

if (str_kstesttime == null) {
	str_kstesttime = 30;
}

if (reminder != null && reminder.length > 0) {
	alert(reminder);
	document.all("btn_end").disabled = false;
}


$(document).ready(function() {	
	
	SetTestLeftTime();	
	gettm(0,'');
});

var gi_lefttime = str_kstesttime * 60 - 1;
var str_kscount = "<s:property value="ksstcount"/>";
if (str_kscount == null) {
	str_kscount="50";
}
   
var gi_TimeId = null;
var belast;
var view_th;
var view_tmnr;
var view_tmsm;
var view_tmlb;
var view_xz1;
var view_xz2;
var view_xz3;
var view_xz4;
var view_xz5;
var view_ksda;

			
function SetTestLeftTime() {
	
	document.all("kssc").innerHTML = Math.floor(gi_lefttime / 60 ) + 1;
	gi_lefttime = gi_lefttime - 1;
	
	gi_TimeId = setTimeout("SetTestLeftTime()",1000);

	if (gi_lefttime > 100) {
		//messages[1] = "考试剩余时间还有" + (Math.floor(gi_lefttime / 60 )+1) + "分钟";
	} else {
		//messages[1] = "请注意，" + gi_lefttime + "秒后系统将自动交卷！";
		//messages[0] =messages[1];
		//messages[2] =messages[1];
	}

	if( gi_lefttime <= 0 ) {
		//交卷
		document.all("btn_end").click();
	}
}

//记录从ajax返回数据后，应当做什么样的操作
var ajaxDoType="";

function processStateChange() {	
	
	document.all("dscheck")[0].checked = false;
	   document.all("dscheck")[1].checked = false;
	   document.all("dscheck")[2].checked = false;
	   document.all("dscheck")[3].checked = false;
	   document.all("dscheck")[4].checked = false;
	   
	   document.all("dsradio")[0].checked = false;
	   document.all("dsradio")[1].checked = false;
	   document.all("dsradio")[2].checked = false;
	   document.all("dsradio")[3].checked = false;
	   document.all("dsradio")[4].checked = false;
	
	 document.all("th").value  =  view_th;
	 document.all("tmlb").value  =  view_tmlb;
	 if (belast == "true") {
		 document.all("sp_th").innerHTML = (view_th * 1 + 1) + "<font color = #FF0000>[<delmar:message key="officeTestQuestion.button.lastQuestion" />]</font>";
	 } else {
		 document.all("sp_th").innerHTML = view_th * 1 + 1;
	 }
	 
	 document.all("sp_tmsm").innerHTML = view_tmsm;
	 document.all("td_tmnr").innerHTML = view_tmnr;
	 document.all("td_xz1").innerHTML = view_xz1;
	 document.all("td_xz2").innerHTML = view_xz2;
	 document.all("td_xz3").innerHTML = view_xz3;
	 document.all("td_xz4").innerHTML = view_xz4;
	 document.all("td_xz5").innerHTML = view_xz5;
	
	 document.all("tr_xz1").style.display = "";
	 document.all("tr_xz2").style.display = "";
	 document.all("tr_xz6").style.display = "none";
	
	 //多选题
	 if (view_tmlb == "MULTISELECT_TOPIC") {
		 
		 	document.all("tr_xz3").style.display="";
		   document.all("tr_xz4").style.display="";
		   document.all("tr_xz5").style.display = "";
		   
		   document.all("dsradio")[0].style.display = "none";
		   document.all("dsradio")[1].style.display = "none";
		   document.all("dsradio")[2].style.display = "none";
		   document.all("dsradio")[3].style.display = "none";
		   document.all("dsradio")[4].style.display = "none";
		   
		   document.all("dscheck")[0].style.display = "";
		   document.all("dscheck")[1].style.display = "";
		   document.all("dscheck")[2].style.display = "";
		   document.all("dscheck")[3].style.display = "";
		   document.all("dscheck")[4].style.display = "";
		   
		   document.all("dscheck")[0].checked=false;
		   document.all("dscheck")[1].checked=false;
		   document.all("dscheck")[2].checked=false;
		   document.all("dscheck")[3].checked=false;
		   document.all("dscheck")[4].checked=false;
		   
		   if (view_ksda.indexOf("A")!=-1) {
			   document.all("dscheck")[0].checked=true;
		    }

		   if (view_ksda.indexOf("B")!=-1) {
			   document.all("dscheck")[1].checked=true;
		    }

		   if (view_ksda.indexOf("C")!=-1) {
			   document.all("dscheck")[2].checked=true;
		    }

		   if (view_ksda.indexOf("D")!=-1) {
			   document.all("dscheck")[3].checked=true;
		    }
		   if (view_ksda.indexOf("E")!=-1) {
			   document.all("dscheck")[4].checked=true;
		    }
	   
	 //判断题
	 } else if (view_tmlb == "JUDGE_TOPIC") {
		   document.all("tr_xz3").style.display="none";
		   document.all("tr_xz4").style.display="none";
		   document.all("tr_xz5").style.display="none";
		
		   document.all("dsradio")[0].style.display = "";
		   document.all("dsradio")[1].style.display = "";
		   document.all("dsradio")[2].style.display = "none";
		   document.all("dsradio")[3].style.display = "none";
		   document.all("dsradio")[4].style.display = "none";
		   
		   document.all("dscheck")[0].style.display = "none";
		   document.all("dscheck")[1].style.display = "none";
		   document.all("dscheck")[2].style.display = "none";
		   document.all("dscheck")[3].style.display = "none";
		   document.all("dscheck")[4].style.display = "none";
		
		   if (view_ksda.indexOf("A")!=-1) {
			   document.all("dsradio")[0].checked=true;
		    }

		   if (view_ksda.indexOf("B")!=-1) {
			   document.all("dsradio")[1].checked=true;
		    }

	 //单选题
	 } else if (view_tmlb == "RADIO_TOPIC") {
	   document.all("tr_xz3").style.display = "";
	   document.all("tr_xz4").style.display = "";
	   document.all("tr_xz5").style.display = "";
	   document.all("dsradio")[0].style.display = "";
	   document.all("dsradio")[1].style.display = "";
	   document.all("dsradio")[2].style.display = "";
	   document.all("dsradio")[3].style.display = "";
	   document.all("dsradio")[4].style.display = "";
	   document.all("dscheck")[0].style.display = "none";
	   document.all("dscheck")[1].style.display = "none";
	   document.all("dscheck")[2].style.display = "none";
	   document.all("dscheck")[3].style.display = "none";
	   document.all("dscheck")[4].style.display = "none";
	   
	   if (view_ksda.indexOf("A")!=-1) {
		   document.all("dsradio")[0].checked=true;
	    }

	   if (view_ksda.indexOf("B")!=-1) {
		   document.all("dsradio")[1].checked=true;
	    }

	   if (view_ksda.indexOf("C")!=-1) {
		   document.all("dsradio")[2].checked=true;
	    }

	   if (view_ksda.indexOf("D")!=-1) {
		   document.all("dsradio")[3].checked=true;
	    }
	   if (view_ksda.indexOf("E")!=-1) {
		   document.all("dsradio")[4].checked=true;
	    }
	   
	 //开放式问答题
	 } else if (view_tmlb == "SHORT_TOPIC") {
	   document.all("tr_xz1").style.display = "none";
	   document.all("tr_xz2").style.display = "none";
	   document.all("tr_xz3").style.display = "none";
	   document.all("tr_xz4").style.display = "none";
	   document.all("tr_xz5").style.display = "none";
	   document.all("tr_xz6").style.display = "";
	
	   document.all("dsradio")[0].style.display = "none";
	   document.all("dsradio")[1].style.display = "none";
	   document.all("dsradio")[2].style.display = "none";
	   document.all("dsradio")[3].style.display = "none";
	   document.all("dsradio")[4].style.display = "none";
	   
	   document.all("dscheck")[0].style.display = "none";
	   document.all("dscheck")[1].style.display = "none";
	   document.all("dscheck")[2].style.display = "none";
	   document.all("dscheck")[3].style.display = "none";
	   document.all("dscheck")[4].style.display = "none";
	
	   document.all("openanswer").value = view_ksda;
	
	 }
	
	
	 if (view_xz5 == "") {
	   document.all("tr_xz5").style.display = "none";
	   document.all("dsradio")[4].style.display = "none";
	   document.all("dscheck")[4].style.display = "none";
	}
}


function tmfirst(){
		if (parseInt(Form1.th.value) > 0 && parseInt(Form1.th.value) < (str_kscount * 1)) {
			gettm(0,"");
		}
}

function tmlast(){
	if (parseInt(Form1.th.value) < (str_kscount * 1 - 1) && parseInt(Form1.th.value) >-1) {
		gettm((str_kscount * 1 - 1),"");
	}
}

 function tmup(){

        if (document.all("tmlb").value=="MULTISELECT_TOPIC") {
			var tempda="";
			for (var i=0;i<5;i++) {
				if (document.all("dscheck")[i].checked)
                   tempda=tempda+document.all("dscheck")[i].value;
			}

			if (parseInt(Form1.th.value)>0 && parseInt(Form1.th.value)<(str_kscount*1)) {
	    		gettm(parseInt(Form1.th.value)-1,tempda);
		     }
		} else if (document.all("tmlb").value=="SHORT_TOPIC") {
          var tempda=document.all("openanswer").value;
			if (parseInt(Form1.th.value)>0 && parseInt(Form1.th.value)<(str_kscount*1)) {
	    		gettm(parseInt(Form1.th.value)-1,tempda);
		     }

		} else {
		
			if (parseInt(Form1.th.value)>0 && parseInt(Form1.th.value)<(str_kscount*1)) {
	    		gettm(parseInt(Form1.th.value)-1,"");
		     }
		}
}

function tmnext(){
	
	   if (document.all("tmlb").value=="MULTISELECT_TOPIC") {
			var tempda="";
			for (var i=0;i<5;i++) {
				if (document.all("dscheck")[i].checked)
                   tempda=tempda+document.all("dscheck")[i].value;
			}

			if (parseInt(Form1.th.value)<=(str_kscount*1-1) && parseInt(Form1.th.value)>-1) {
	    		gettm(parseInt(Form1.th.value)+1,tempda);
		     }

		} else if (document.all("tmlb").value=="SHORT_TOPIC") {
          var tempda=document.all("openanswer").value;
			if (parseInt(Form1.th.value)<=(str_kscount*1-1) && parseInt(Form1.th.value)>-1) {
	    		gettm(parseInt(Form1.th.value)+1,tempda);
		     }

		} else {
			/* if (parseInt(Form1.th.value)<=(str_kscount*1-1) && parseInt(Form1.th.value)>-1) {
				gettm(parseInt(Form1.th.value)+1,"");
			} */
			
			var tempda="";
			for (var i=0;i<5;i++) {
				if (document.all("dsradio")[i].checked) {
					tempda=tempda+document.all("dsradio")[i].value;
					break;
				}
			}

			if (parseInt(Form1.th.value)<=(str_kscount*1-1) && parseInt(Form1.th.value)>-1) {
	    		gettm(parseInt(Form1.th.value)+1,tempda);
		     }
		}
}

// 将选择后的答案放入临时的xml中
function tmda(str_da) {
	if ('openanswer'==str_da){
		str_da=document.getElementById("openanswer").value;
	}
	if (parseInt(Form1.th.value) <= (str_kscount * 1 - 1))
		gettm(parseInt(Form1.th.value) + 1,str_da);
}

function gettm(str_bh, str_da){

	var time = new Date();
	var se = time.getTime();

	//alert(url);
   retrieveURLNoForm(str_bh, str_da, se);   
}

function retrieveURLNoForm(str_bh, str_da, se) {
	
	$.ajax({
        type: "post",
        url: "<%=request.getContextPath()%>/officeTest/officeTestExamDetail_ajaxGetTM.action?currtmbh=" + Form1.th.value + "&nexttmbh=" + str_bh + "&tmda=" + str_da + "&time=" + se,
        dataType: "json",
        success: function (data) {
        	for (var i = 0; i < data.length; i++) {
        		belast = data[i].belastquestion;
        		if (belast==null)
        	    	belast="";
        		view_th = data[i].officeTestQuestion.questionTh;
        		view_tmnr = data[i].officeTestQuestion.content;
        		view_tmsm = data[i].officeTestQuestion.categoryName;
        		view_tmlb = data[i].officeTestQuestion.typeName;
        		view_xz1 = data[i].officeTestQuestion.optionOne;
        		view_xz2 = data[i].officeTestQuestion.optionTwo;
        		view_xz3 = data[i].officeTestQuestion.optionThree;
        		view_xz4 = data[i].officeTestQuestion.optionFour;
        		view_xz5 = data[i].officeTestQuestion.optionFive;
        		view_ksda = data[i].examAnswer;
        	}
        	
        	processStateChange();
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
   });
  }
		


function ksclose(savestate){
	if (savestate == null){
		savestate="";
	}
	
	//如果都作答了 或者 没有答完题但是没有答题时间了  那就直接交卷
	uf_getCagegorys(savestate);
}

function uf_getCagegorys(savestate) {
	
	$.ajax({
        type: "post",
        url: "<%=request.getContextPath()%>/officeTest/officeTestExamDetail_ajaxValidateSave.action",
        dataType: "json",
        success: function (data) {
        	if (data) {
        		document.all("btn_end").disabled = true;
        		window.open("<%=request.getContextPath()%>/officeTest/officeTestExamDetail_saveTest.action?useTime=" + document.all("kssc").innerHTML + "&savestate=" + savestate);
        	} else {
        		if (gi_lefttime <= 0) {
        			document.all("btn_end").disabled = true;
        			window.open("<%=request.getContextPath()%>/officeTest/officeTestExamDetail_saveTest.action?useTime=" + document.all("kssc").innerHTML + "&savestate=" + savestate);
        		} else {
        			alert("<delmar:message key="officeTestQuestion.message.notAnswered" />");
        		}
        	}

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(errorThrown);
        }
   });
}
		

</script>
</head>

<body >

<form id="Form0" method="post" target="main">
<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
     <tr> 
       <td align="left" class="navig"><delmar:message key="officeTest.title" /></td>
       <td class="navig" align="right"> 
        
       </td>
     </tr>
</table>
<div style="margin:5px">
<table height="35" cellSpacing="1" cellPadding="0" width="100%" bgColor="#0154a0" border="0" style="padding-top:5px;padding-bottom:5px">
	<tr>
		<td  align="center"  bgColor="#ffffff"><delmar:message key="officeTestQuestion.message.nowDate" />：</td>
		<td  id="ksrq" align="center" bgColor="#ffffff" ><FONT face="宋体"><s:property value="ksrq"/></FONT></td>
		<td  align="center"  bgColor="#ffffff" ><delmar:message key="officeTestQuestion.message.surplusTime" />：</td>
		<td align="center"  bgColor="#ffffff" width="166" bgColor="#e3e3e3"><span id="kssc"><s:property value="kstesttime"/></span><delmar:message key="officeTestQuestion.message.minute" /></td>
		<td align="center" bgColor="#ffffff">
		<c:if test="${beOpen==1 }">
			<INPUT id="btn_end" style="FONT-SIZE: 15pt; WIDTH: 100px; HEIGHT: 30px" type="button" value="<delmar:message key="officeTestQuestion.button.suspension" />"
				onclick="javascript:ksclose('draft')">
		</c:if>
		<INPUT id="btn_end" style="FONT-SIZE: 15pt; WIDTH: 100px; HEIGHT: 30px" type="button" value="<delmar:message key="officeTestQuestion.button.Assignment" />"
				onclick="javascript:ksclose()"></td>
	</tr>
</table>
</div>								
</form>
<form id="Form1" method="post" >
<div style="margin:5px">
<!-- 题号 -->
<input type="hidden" id="th" name="th" value="0">
<!--  -->
<input type="hidden" id="tmlb" name="tmlb" value="">
<table height="30" cellSpacing="1" cellPadding="0" width="100%" bgColor="#00488d" border="0"  style="padding-top:5px;padding-bottom:5px">
	<tr>
		<td bgColor="#e4e4e4">&nbsp;&nbsp;<delmar:message key="officeTestQuestion.message.di" />
		<span  id="sp_th"></span><delmar:message key="officeTestQuestion.message.ti" />&nbsp;&nbsp;&nbsp;&nbsp;&lt;&lt;<span id="sp_tmsm"></span>&gt;&gt;</td>
	</tr>
</table>

<table  height="100"  cellSpacing="1" cellPadding="0" width="100%" bgColor="#4376ab" border="0" style="padding-top:5px;padding-bottom:5px">
	<tr>
		<td bgColor="#ffffff" width="10px">&nbsp;&nbsp;</td>
		<td  id="td_tmnr" bgColor="#ffffff">&nbsp;&nbsp;</td>
	</tr>
</table>


<table width="100%" border="1" style="padding-top:5px;padding-bottom:5px">
	<tr id="tr_xz1" height="60">
		<td align="center" width="17%" bgColor="#ffffff" id="dstd_1">
			<input type='checkbox' id='dscheck' value='A'>
			<input type="radio" id="dsradio" name="dsradio" value="A">
		</td>
		<td  width="83%" bgColor="#e4e4e4" id="td_xz1"></td>
	</tr>
	<tr id="tr_xz2" height="60">
		<td align="center" width="17%" bgColor="#ffffff" id="dstd_2">
			<input type='checkbox' id='dscheck' value='B'>
			<input type="radio" id="dsradio" name="dsradio" value="B">
		</td>
		<td  width="83%" bgColor="#e4e4e4"  id="td_xz2"></td>
	</tr>
	<tr id="tr_xz3" height="60">
		<td align="center" width="17%" bgColor="#ffffff" id="dstd_3">
			<input type='checkbox' id='dscheck' value='C'>
			<input type="radio" id="dsradio" name="dsradio" value="C">
		</td>
		<td  width="83%" bgColor="#e4e4e4" id="td_xz3"></td>
	</tr>
	<tr id="tr_xz4" height="60">
		<td align="center"  width="17%" bgColor="#ffffff" id="dstd_4">
			<input type='checkbox' id='dscheck' value='D'>
			<input type="radio" id="dsradio" name="dsradio" value="D">
		</td>
		<td width="83%" bgColor="#e4e4e4" id="td_xz4"></td>
	</tr>
	<tr id="tr_xz5" height="60">
		<td align="center"  width="17%" bgColor="#ffffff" id="dstd_5">
			<input type='checkbox' id='dscheck' value='E'>
			<input type="radio" id="dsradio" name="dsradio" value="E">
		</td>
		<td width="83%" bgColor="#e4e4e4" id="td_xz5"></td>
	</tr>
	<tr id="tr_xz6" height="100">
		<td align="center" height="80" width="17%" bgColor="#ffffff" id="dstd_5"><delmar:message key="officeTestQuestion.message.pleaseInput" />:</td>
		<td width="83%" bgColor="#e4e4e4" id="td_xz6">
		<textarea id="openanswer" name="openanswer" 
			style='border:1px;overflow-y:visible;overflow-x:auto; width:100%;height:200px;' 
			onkeyup="this.value = this.value.substring(0, 300)" 
			onblur="tmda('openanswer')"></textarea>
	<br/>
	<font color="red" style="font-weight: bold;"><delmar:message key="officeTestQuestion.message.most300" /></font>
	</td>
	</tr>

</table>


<table height="61" cellSpacing="0" cellPadding="0" width="100%" border="0">
<tr>
<td vAlign="middle" align="center" background="images/kaoshi_dxbg.jpg">
<table height="41" cellSpacing="0" cellPadding="0" width="80%" border="0">
<tr>
<td width="129"><input type="button" value="<delmar:message key="officeTestQuestion.column.firseQuestion" />" Class="input_submit" onclick="javascript:tmfirst()" style="border:0;"/></td>
<td width="5%">&nbsp;</td>
<td width="16%"><input type="button" value="<delmar:message key="officeTestQuestion.column.lastQuestion" />" Class="input_submit" onclick="javascript:tmup()" style="border:0;"/></td>
<td width="26">&nbsp;</td>
<td width="129"><input type="button" value="<delmar:message key="officeTestQuestion.column.nestQuestion" />" Class="input_submit" onclick="javascript:tmnext()" style="border:0;"/></td>
<td width="25">&nbsp;</td>
<td width="129"><input type="button" value="<delmar:message key="officeTestQuestion.column.finalQuestion" />" Class="input_submit" onclick="javascript:tmlast()" style="border:0;"/></td>
			
</tr>
</table>
</td>
</tr>
</table>
</div>
</form>


<script type="text/javascript">

</script>

</body>
</html>
