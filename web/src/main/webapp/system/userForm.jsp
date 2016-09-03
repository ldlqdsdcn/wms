<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/commons/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><delmar:message key="user.title"/></title>
<link rel="stylesheet" href="../css/style.css" type="text/css" media="all"/>
<link rel="Stylesheet" href="../css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="../css/delmar.css" type="text/css" media="all"/>

<script type='text/javascript' src='../js/dm/delmar.js'></script>

  <script type='text/javascript' src='../js/ea.validate.js'></script>
<script type="text/javascript" src="../js/json2.js"></script>  
<script type='text/javascript' src='/delmar_web/dwr/interface/userDwr.js'></script>
  <script type='text/javascript' src='/delmar_web/dwr/engine.js'></script> 
   <script type='text/javascript' src='/delmar_web/dwr/util.js'></script>
   
<script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"/></script>
<script type="text/javascript" src="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.js"/></script>
<script type="text/javascript" src="../js/jquery/plugin/delmar/functions.js"/></script>

<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.min.css" type="text/css" />
<link rel="Stylesheet" href="../js/jquery/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css" type="text/css" />

<style type="text/css">
    .dialog-form label {margin-right:10px;width:100px;right:0px;} 
    .dialog-form input.text { margin-bottom:12px; width:95%; padding: .4em;display:block; }
    fieldset { padding:0; border:0; margin-top:25px; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
</style>

<script type="text/javascript">

$(document).ready(function() {

	 
	 DWREngine.setAsync(false); 				
  	 $('#managerName').autocomplete(
				{
					source : function(request, response) {
						var availableTags = [];
						var value = $("#managerName").val();
						userDwr.getUserList(value,$("#orgId").val(), function(data) {
							if(data!=null)
								
							for (var i = 0; i < data.length; i++) {
								var vendor = data[i];
								vendor.label=data[i].username;
								vendor.value=data[i].username;
								availableTags.push(vendor);
							}

						});
						
						response(availableTags);
					},
					select : function(event, ui) {
						$("#managerId").val(ui.item.id);
						$("#managerName").val(ui.item.username);

					}
				});
     });


	function getOrgs()
	{

		DWREngine.setAsync(false);
		var clientId=dwr.util.getValue('clientId');
		
		userDwr.getOrgsByClientId(clientId,function(data)
		{
				dwr.util.removeAllOptions('orgId');
				dwr.util.addOptions('orgId',data,'id','name');
		});
		DWREngine.setAsync(true);
		getUserroleGroup();
	}
	function getUserroleGroup()
	{
		var orgarray=[];
		var ids=	document.getElementsByName("ids");
		//userOrgaccessList[2].orgId

		for(var i=0;i<ids.length;i++)
			{
				if(ids[i].checked==true)
				{
						var value=ids[i].value;
						orgarray.push(value);
					
				}
			
			}
	
		DWREngine.setAsync(false);
		//var orgId	=dwr.util.getValue('orgId');
		userDwr.getUsergroupsListId(orgarray,function(data)
		{

					//dwr.util.removeAllOptions('usergroupId');
					//dwr.util.addOptions('usergroupId',data,'id','name');
						
					var detailtbody=	document.getElementById('detailusergrouptbody');
					var childNodes=detailtbody.childNodes;

					for(var i=childNodes.length-1;i>=0;i--)
					{

						detailtbody.removeChild(childNodes[i]);
					}
					var usergroupTable=document.getElementById("usergroupTable");
					for(var i=0;i<data.length;i++)
					{
						 var rowIndex=1+i;
						// var newTr = usergroupTable.insertRow(rowIndex);
						 var newTr = document.createElement("tr");
						  rowIndex%2==0?
								  $(newTr).attr('class','odd'):
								  $(newTr).attr('class','even');
						  var td1=document.createElement("td");
						  var td2=document.createElement("td");
						  var td3=document.createElement("td");
						  td1.innerHTML="<input type=\"checkbox\" name=\"ugids\"  value=\""+data[i].id+"\" />";
						  td2.innerHTML=data[i].name
						  +"<input type=\"hidden\" name=\"userGroupaccessList["+i+"].usergroupId\" value=\""+data[i].id+"\" id=\"editForm_userGroupaccessList_"+rowIndex+"__usergroupId\"/>";
						  td3.innerHTML=data[i].org.name;
							newTr.appendChild(td1);
							newTr.appendChild(td2);
							newTr.appendChild(td3);
							detailtbody.appendChild(newTr);
					}
						
				
				
				
				
				});
		
		DWREngine.setAsync(true);
	}
	function getRoles()
	{
		
		var depaValues=[];

		 depaValues.push(document.getElementById('orgId').value);
		var roleValues=[];
		var roles=document.getElementsByName('roles');
		if(roles==null)
		{
			roleValues=null;
		}
		else
		{
			for(var i=0;i<roles.length;i++)
		{
		if(roles[i].checked==true)
		 roleValues.push(roles[i].value);
		 
		}
		}
		
		userDwr.getRoles(depaValues,roleValues,5,function(data)
		{
		
			document.getElementById('roleTD').innerHTML=data;
		}
		);
		
	}
	function getUsergroups()
	{
		

		//userOrgaccessList[2].orgId
		var orgId	=dwr.util.getValue('orgId');
		var detailtbody=	document.getElementById('detailtbody');
		var childNodes=detailtbody.childNodes;
		for(var i=0;i<childNodes.length;i++)
		{
			detailtbody.removeChild(childNodes[i]);
		}
		
	
	}
	
	
	function validate()
	{
		var warnmsg='';
		var warobjs=[];
		var iswarn=false;
		if(isEmptyCheckBox('roles'))
		{
			iswarn=true;
			warnmsg=warnmsg+'<delmar:message key="user.message.rolenotnull"/>\n';
		}
		if(iswarn)
		{
			alert(warnmsg);
			if(warobjs.length>0)
			{
				warobjs[0].focus();
			}
			return false;
		}
		return true;
	}
</script>

</head>



<body>

<s:form id="editForm" action="user_edit" namespace='/system' theme="simple" ENCTYPE="multipart/form-data" accept-charset="UTF-8">
<s:hidden id="id" name="user.id"/>
<s:hidden id="nextAction" name="nextAction"/>
<table width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr> 
    <td valign="top">
    	<table border="0" cellpadding="0" cellspacing="0" class="cTableBorder">
        <tr> 
          <td align="left" class="navig">
	<delmar:message key="user.location"/>
</td>
         <td background="../images/table_page_bg.gif" width="*"  height="26" align="right">
         
         <div class="C_S_F_L">
			<c:if test="${!isFirst}">
				<s:submit method="getPrevionsOne" value="%{#session.resource.get('common.button.previous')}"   cssClass="input_submit"/>
			</c:if>		
			
			
			<c:if test="${!isLast}">
			<s:submit method="getNextOne" value="%{#session.resource.get('common.button.next')}"  cssClass="input_submit"/>
			
			</c:if>
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
               <delmar:message key="user.column.loginname"/>
               	</td>
               	<td colspan="3">
               	<s:textfield name="user.username" id="username"/>

               	<input type="checkbox" name="user.isActive" id="isActive" value="0"
               	<s:if test="user.isActive==0">
               	   checked
               	</s:if>>
                <span style="color:#ff0000"><delmar:message key="user.column.isactive"/></span>
               	</td>
				</tr>
				
				<tr  class="query_two">
				<td ><delmar:message key="user.column.password"/></td>
				<td>
  				  <s:password name="user.password" id="username" cssStyle="width:300px;" showPassword="true"></s:password>
				</td>
				<td><delmar:message key="user.label.confirmpassword"/></td>
				<td>
				<s:password name="passwordConfirm" id="passwordConfirm" cssStyle="width:300px;"  showPassword="true"></s:password>
				<s:hidden name="oldPassword" id="oldPassword"/>
				
				</td>
							
				</tr>
				<tr class="query_one">
				<td>
				<delmar:message key="user.column.name"/>
				</td>
				<td>
				<s:textfield name="user.name" id="name"  cssStyle="width:250px"/>
				</td>
				<td colspan=2 rowspan=9>
                  <img  alt="" src="../userPic/user<s:property value='user.id'/>.png" id="picUser" name="picUser" onload='AutoResizeImage(200,300,this)'></img>
                  <br>
                  <br><input type="file"  name="userPic" id="userPic" accept="image/png" readonly value="" onKeyPress="this.clear;return false" onchange="doAction('loadimage')">
                  <br><span style="color:#ff0000"><delmar:message key="user.message.acceptpng" cn="只接受扩展名为PNG的图片"/></span>
                  <br><span style="color:#ff0000"><delmar:message key="user.message.picturesize" cn="文件大小不能够超过100KB" paramValue="100"/></span>
				</td>
				</tr>
				<tr class="query_two">
				<td>
				<delmar:message key="user.column.email"/>
				</td>
				<td>
				<s:textfield name="user.email" id="email"  cssStyle="width:250px"  blurvalidate="yes" blurtype="email"  title="%{#session.resource.get('public.inputformat.email')}"/>
				</td>
				</tr>
				<tr class="query_one">
				<td>
				<delmar:message key="user.column.telephone"/>
				</td>
				<td>
				<s:textfield name="user.telephone" id="telephone"  cssStyle="width:250px" blurvalidate="yes" blurtype="telephone"  title="%{#session.resource.get('public.inputformat.telephone')}" />
				</td>
				</tr>
				<tr class="query_two">
				
				<td>
				<delmar:message key="user.column.managerid"/>
				</td>
				<td>
				<s:textfield name="user.managerName" id="managerName"  cssStyle="width:250px"/>
                <s:hidden id="managerId" name="user.managerId"/>
				</td>
				</tr>	
				<tr class="query_one">
				<td>	<delmar:message key="user.column.usertypeid"/></td>
				<td>
					<s:select id="userTypeId" list="userTypeList" listKey="id" listValue="name" name="user.userTypeId" ></s:select>
				</td>
				</tr>

				<tr class="query_two">
				<td>	<delmar:message key="user.column.client"/></td>
				<td>
					<s:select id="clientId" list="clients" listKey="id" listValue="name" name="user.clientId"  onchange="getOrgs()" disabled="!modifyClientOrg"></s:select>
				</td>
				</tr>
				<tr class="query_one">
				
				<td>	<delmar:message key="user.column.org"/></td>
				<td >
					<s:select id="orgId" list="orgs" listKey="id" listValue="name" name="user.orgId" disabled="!modifyClientOrg"></s:select>
				
				<%-- ${depaString}--%>
				</td>
				</tr>
		<%-- 		<tr class="query_one">
				<td>用户组</td><td colspan="3">
					<s:select id="usergroupId" list="usergroupList" listKey="id" listValue="name" name="user.usergroupId" ></s:select>
				</td>
				</tr> --%>
				<tr class="query_two">
				<td>
				  <delmar:message key="user.column.role"/>
				</td>
				<td  id="roleTD">
				${roleString}
				
				</td>
				</tr>
						<tr  class="query_one">
							
							<td ><s:label for="descr" value="%{#session.resource.get('user.column.remark')}" /></td>
							<td >
							<s:textfield name="user.remark" id="remark" cssStyle="width:500px;"/>
							</td>
							
                            
						</tr>
						
					<tr>
            <td colspan="4">
            <table id="columnTable" class="table">
            	<thead>
            	<th  style="width:30%">
            	<input type="checkbox"  onclick="selectAll('ids',this);getUserroleGroup();"/>
            	</th>
            	<th >
            	<delmar:message key="user.label.accessorg"/>
            	</th>
            	</thead>
            	<tbody id="detailtbody">
              	<s:iterator value="userOrgaccessList" status="st" var="userOrgAccess" >
              	
              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
              	<td style="text-align:center">
              	
              		<input type="checkbox" name="ids"  value="<s:property value="%{#userOrgAccess.orgId}"/>"  <s:if test="#userOrgAccess.id!=null">checked="checked"</s:if>  onclick="getUserroleGroup()"  />
              		
              	</td>
              	<td>
           			<s:property value="%{#userOrgAccess.org.name}"/>
           			<s:hidden name="%{'userOrgaccessList['+#st.index+'].orgId'}"   id="%{'userOrgaccessList['+#st.index+'].orgId'}" />
              	</td>
              	
              	</tr>
              	
              	</s:iterator>
              	
              	</tbody>
            </table>
            </td>
            </tr>	
			<tr>
			<td colspan="4">
			<table id="usergroupTable" class="table">
            	<thead>
            	<th style="width:30;">
            	<input type="checkbox"  onclick="selectAll('ugids',this);"/>
            	</th>
            	<th ><delmar:message key="user.label.accessusergroup"/></th>
				<th><delmar:message key="user.column.role"/></th>
            	</thead>
            	<tbody id="detailusergrouptbody">
              	<s:iterator value="userGroupaccessList" status="st" var="uoa" >
              	
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>">
		              	<td style="text-align:center">
		              	<input type="checkbox" name="ugids"  value="<s:property value="%{#uoa.usergroupId}"/>"  <s:if test="#uoa.id!=null">checked="checked"</s:if>   />
		              	</td>
		              	<td>
		           			<s:property value="%{#uoa.usergroup.name}"/>
		           			<s:hidden name="%{'userGroupaccessList['+#st.index+'].usergroupId'}" />
		              	</td>
		              	<td>
		              	<s:property value="%{#uoa.usergroup.org.name}"/>
		              	</td>
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
			</td>
			</tr>			
			
           <tr>
			<td colspan="4">
			<table id="userSubstituteTable" class="table">
            	<thead>
				 <th  style="width:30;"><img src='../images/ico/dtladd.gif'  onclick="javascript:openUserDialog();" class="folder" alt="<delmar:message key='public.button.add'/>"/> 
				 </th>                
				 <th> <delmar:message key="user.column.usersubstitute"/>
				 </th>
				 <th><delmar:message key="user.column.name"/></th>
				 <th><delmar:message key="org.column.name"/></th>
                 <th ><delmar:message key="common.button.delete" /></th>			
            	</thead>
            	<tbody id="detailsubstitutetbody">
              	<s:iterator value="userSubstituteList" status="st" id="usersubstitute" >
		              	<tr  class="<s:property value="#st.index%2==0?'odd':'even'"/>" id="tptr<s:property value="id"/>">
		              	<td>
		                 <c:out value="${st.index+1}"></c:out>
		              	</td>
		              	<td>
		           			<s:property value="username"/>
		              	</td>
		              	<td>
		           			<s:property value="name"/>
		              	</td>
		              	<td>
		           			<s:property value="org.name"/>
		              	</td>	
		                 <td>
		           			<img src='../images/ico/dtljian.gif' class='folder' alt='<delmar:message key="common.button.delete" />' onclick='javascript:deleteUserSubstitute("<s:property value='id'/>");' />
		              	</td>		
		              	
		              	</tr>
              	</s:iterator>
              	</tbody>
            </table>
			</td>
			</tr>	
									
						
           <tr>
			<td colspan="4">
			<s:action id="userthirdlist" name="userthirds_list" executeResult="true">
			  <s:param name="sysUserId" value="user.id"></s:param>
			</s:action>

			</td>
			</tr>			
						
			 <tr>
			<td colspan="4">
			
			<s:action id="userextralist" name="userextras_list" executeResult="true">
			  <s:param name="sysUserId" value="user.id"></s:param>
			</s:action>
			
			</td>
			</tr>			
						
						
                    
                <tr>
                <td colspan="4" class="td_page_right">
               			<s:submit method="create" value="%{#session.resource.get('common.button.create')}"   cssClass="input_submit"/>
						<s:submit method="save" value="%{#session.resource.get('common.button.save')}"  cssClass="input_submit" onclick="return validate();"/>
						<c:if test="${user.id!=null}">
						<s:submit method="delete"  value="%{#session.resource.get('common.button.delete')}"  cssClass="input_submit" onclick="return confirmDelete()"/>
						</c:if>
						
						<input onclick="window.location='<c:url value="/system/user_list.action"/>'"  type="button" value="<delmar:message key="common.button.back"/>"  class="input_submit" >
				</td>
                </tr>
                
                </table>
                
                
                </td>
                </tr>
                <tr>
                <td colspan="4">
                <s:actionmessage cssStyle="color:red"/>
                <s:fielderror></s:fielderror>
                <s:actionerror/>	
                </td>
                </tr>
                </table></td></tr></table>
</td></tr></table></td></tr>
</table>
</s:form>






<div id="selectDiv">
   <input type="hidden" name="cids" id="cids"/>
	<iframe frameborder="0" align="top" height="100%" width="100%" style="margin:0px; border:0px; padding: 0px;" id="selectIframe"></iframe>
</div>




<script type="text/javascript">
var userdialog;

$(document).ready(function() {
	 
	 $(document).D_Validate();
	 
	 userdialog=$("#selectDiv").dialog({
			autoOpen: false,
			height: 500,
			width: 700,
			modal: true,
			title:"<delmar:message key='user.location' />",
			resizable:false,
		    buttons: {
		          "<delmar:message key='user.button.addusersubstitute'/>":function() {
		        	  
		        	  if ($("#cids").val()=="")
		        	   {
		        		  alert("<delmar:message key='public.message.choose.atleastone'/>");
		        		  return;
		        		}
		        	  else
		        		{
		        		   addUserSubstitue($("#cids").val());
		        		   userdialog.dialog( "close" );
		        		}
		          },
		          "<delmar:message key='user.button.canceladd'/>": function() {
		        	  userdialog.dialog( "close" );
		          }
		        },
		        			
	        close: function() {
	        	userdialog.dialog( "close" );
		     }		
	});
	
	 
	 
 

});  





function openUserDialog()
{
	document.getElementById('selectIframe').src="<c:url value='/system/usersub_listadd.action'/>?id="+$("#id").val();
	userdialog=$('#selectDiv').dialog('open');
}


function addUserSubstitue(userIds)
{

	  $.ajax({  
          url:'usersub_save.action?id='+$("#id").val()+"&userIds="+userIds,  
          type:'post',  
          success:function (data) {  	        	 
        	 window.location.reload();	        
          }  
      });  
	  
}





function updateTips( t ) {
    tips
      .text( t )
      .addClass( "ui-state-highlight" );
    setTimeout(function() {
      tips.removeClass( "ui-state-highlight", 1500 );
    }, 500 );
  }
  
function checkIsNull( o,n) {
    if ( o.val().length ==0 ) {
      o.addClass( "ui-state-error" );
      updateTips("<delmar:message key='userthirdparty.dialog.error'/>".format(n));
      return false;
    } else {
      return true;
    }
  }
  
 

  

  function doAction(action) {
      var form = document.editForm;
      if (action == 'loadimage') {
    	  var FileMaxSize = 100;//限制上传的文件大小，单位(k)
    	  
    	  var f = document.getElementById("userPic").files;  
          //上次修改时间   alert(f[0].lastModifiedDate);  .name  .type  .size

    	  if(f[0].size>FileMaxSize*1024){
    		  alert("<delmar:message key='user.message.pictureoversize'/>".format(FileMaxSize));
    		  document.editForm.userPic.focus();
    		  return false;
    		}    	  
    	  
          if($("#id").val() > 0){
              form.nextAction.value = action;
              form.action="user_save.action";
              form.submit();
          }
      }
  }  
  
  
  function AutoResizeImage(maxWidth, maxHeight, objImg) {
        var img = new Image();
        img.src = objImg.src;
        var hRatio;
        var wRatio;
        var Ratio = 1;
        var w = img.width;
        var h = img.height;
        wRatio = maxWidth / w;
        hRatio = maxHeight / h;
        if (maxWidth == 0 && maxHeight == 0) {
             Ratio = 1;
         } else if (maxWidth == 0) { //
          if (hRatio < 1) Ratio = hRatio;
         } else if (maxHeight == 0) {
        if (wRatio < 1) Ratio = wRatio;
         } else if (wRatio < 1 || hRatio < 1) {
       Ratio = (wRatio <= hRatio ? wRatio : hRatio);
        }
       if (Ratio < 1) {
           w = w * Ratio;
           h = h * Ratio;
           }
          objImg.height = h;
          objImg.width = w;
        }  
  

	function deleteUserSubstitute(userId)
	{

		  $.ajax({  
	          url:'usersub_delete.action?id='+$("#id").val()+"&sid="+userId,  
	          type:'post',  
	          success:function (data) { 
	        	  window.location.reload();
	          }  
	      });  		
	}
	

	
  
</script>

</body>
</html>
