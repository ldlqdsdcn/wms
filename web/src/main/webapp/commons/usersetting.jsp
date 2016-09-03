<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户设置</title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
 <script type='text/javascript' src='../js/ea.validate.js'></script>
 <script type="text/javascript">
 
 		function validate()
 		{
 			
 			var oldpassword=document.getElementById('oldpassword');
 			if(isEmpty(oldpassword.value))
 				{
 					alert('原始密码不允许为空');
 					oldpassword.focus();
 					return false;
 				}
 			var password=document.getElementById('password');
 			if(isEmpty(password.value))
				{
					alert('新密码不允许为空');
					password.focus();
					return false;
				}
 			var confirmpassword=document.getElementById('confirmpassword');
 			if(isEmpty(confirmpassword.value))
			{
				alert('新密码确认不允许为空');
				confirmpassword.focus();
				return false;
			}
 			if(password.value!=confirmpassword.value)
 			{
 				alert('两次密码输入不一致');
 				password.focus();
 				return false;
 			}
 			return true;
 		}
 </script>
</head>
<body>
<s:form id="editForm" action="usersetting" namespace='/commons' theme="simple">


<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">位置：用户设置</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">

		</div></td>
        </tr>
      </table>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="table_blue">
        <tr align="center" valign="top"> 
          <td>
          
           
            <table width="100%" cellpadding="10" cellspacing="0" >
              <tr> 
                <td align="center"> <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="table_blue">
                <tr>
                <td>
                
                <table width="100%" border="0" cellpadding="0" cellspacing="1">
               <tr  class="query_one">
               	<td width="20%">
               	<s:label for="name" value="原始密码"/>
               	</td>
               	<td colspan="3">
               	<s:password name="oldpassword" id="oldpassword" cssStyle="width:500px;"></s:password>
               	</td>
				</tr>
				<tr  class="query_two">
				<td ><s:label for="password" value="新密码"/></td>
							<td colspan="3">
							<s:password name="password" id="password" cssStyle="width:500px;" ></s:password>
							</td>
				</tr>
				<tr  class="query_one">
							<td ><s:label for="confirmpassword" value="新密码确认"/></td>
							<td colspan="3">
							<s:password name="confirmpassword" id="confirmpassword" cssStyle="width:500px;"></s:password>
							</td>
				</tr>
				<tr>
                <td colspan="4" class="td_page_right">
         			<s:submit method="save" value="保存" cssClass="input_submit" onclick=" return validate();"/>
				</td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>







</body>

