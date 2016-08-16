<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/commons/taglib.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
  <% 
 	String fileRealName=request.getParameter("fileRealName");
 	String fileName=request.getParameter("fileName");
 	session.setAttribute("fileRealName",fileRealName);
  	session.setAttribute("fileName",fileName);
    session.setAttribute("uploadtype",request.getParameter("type"));
  	session.setAttribute("rename",request.getParameter("rename"));

   %>
    <title><fmt:message key="fileupload.page" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="all" href="../css/fileupload.css" />
<style type="text/css">
    <!--
    body {
        background-color: #FFFFFF;
    }

    #filelist li {
        line-height: 20px;
        border-bottom: #154BA0 dashed 1px;
        background: #FFFFFF
    			}
.main_td2{ border-bottom:1px #154BA0; border-bottom-style:solid; border-left:1px #154BA0; 
border-left-style:solid;  height:22px;line-height:22px; font-size:12px; color:#FFFFFF; 
font-weight:bold;background:#154BA0;text-align:center}
#admin_container{width:98%;margin-top:10px;}
.main_td3{ border-bottom:1px #154BA0; border-bottom-style:solid;
 border-left:1px #154BA0; border-left-style:solid; font-size:12px; 
 color:#000000;height:22px;text-align:left}
 .main_table{width:100%; margin:0px; padding:0px;border-top:1px solid #154BA0;border-right:1px solid #154BA0; }
 .main_td3_xuhao{ border-bottom:1px #154BA0; border-bottom-style:solid; border-left:1px #154BA0; border-left-style:solid;
  font-size:12px; color:#000000;height:22px;text-align:center} 
  #admin_top_center{float: none;height:22px;margin-top:5px;margin-bottom:5px; text-align:center}
    -->
</style>
  <script type='text/javascript' src='/delmar_web/dwr/interface/fileDwr.js'></script>
  <script type='text/javascript' src='/delmar_web/dwr/engine.js'></script>
  <script type='text/javascript' src='/delmar_web/dwr/util.js'></script>
<script type="text/javascript">
	function uploadfile()
	{
		//document.getElementById('file').readOnly=true;
	//	document.getElementById('Button').visiable=false;
		document.getElementById('message').innerHTML="<delmar:message key='public.uploadfile.uploading'/>";
		var fileAbstract=document.getElementById('fileAbstract').value;
		var fileKeyword=document.getElementById('fileKeyword').value;
		fileDwr.setFileAttribute(fileAbstract,fileKeyword);
		//document.getElementById('fileupload').submit();
	}
</script>
<% 
	  session.setAttribute("tablename", request.getParameter("tablename"));
	  session.setAttribute("tableid", request.getParameter("tableid"));
%>
  </head>
  
  <body>
    <div id="admin_container">
    <div class="main_td2"><delmar:message key='public.uploadfile.title'/></div>

    <div id="upload">
        <form action="<c:url value="/common/FileUploadServlet.do"/>"  method="post"    enctype="multipart/form-data"  name="fileupload" id="fileupload" target="_self">
          
            <table border="0" cellspacing="0" cellpadding="0" class="main_table" width="1086" height="23">
                <tr>
                    <td class="main_td3"></td>
                </tr>
            </table>
            <br/>
            <table border="0" cellspacing="0" cellpadding="0" class="main_table">
                <tr >
                
                    <td class="main_td3">
                    <input  name="file" id="file" type="file" size="60" accept="*.gif,*.jpg" />
                    </td>
                    
                    <td>
                    
                    </td>
                </tr>
                <tr>
                <td class="main_td3">
                摘要：&nbsp;<input name="fileAbstract"  style="width:300px" id="fileAbstract">
                </td><td></td>
                </tr>
                 <tr>
                <td class="main_td3">
                关键字：<input name="fileKeyword"  style="width:300px" id="fileKeyword">
                </td><td></td>
                </tr>
                <tr>
                    <td colspan="2" class="main_td3_xuhao">
                        <div id="admin_top_center">

                            <input type="submit"  name="Button" id="Button" onclick="javascript:uploadfile();" value="<delmar:message key='public.uploadfile.upload'/>"/>
                       </div>
                    </td>
                </tr>
                <tr>
                <td class="main_td3" colspan="2">
                	&nbsp;<label id="message" style="color: red;"></label>
                </td>
                </tr>
            </table>

        </form>
    </div>
  </body>
</html>
