<%@page import="com.delmar.core.web.bean.PagePrivilege"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>



<%
//session.removeAttribute(com.ea.core.web.util.FacesUtils.MAP_KEY_OF_SESSION);
String strUrl=request.getParameter("url");
String menuKey=request.getParameter("menuKey"); 
if(com.delmar.utils.StringUtil.isNotEmpty(menuKey))
session.setAttribute("menuKey",menuKey);
PagePrivilege.build(session, strUrl);
response.sendRedirect(com.delmar.core.web.bean.EaContext.CONTEXTPATH+strUrl);
session.removeAttribute(com.delmar.core.web.util.FacesUtils.MAP_KEY_OF_SESSION);
 %>
<%--<%session.removeAttribute(com.ea.web.util.FacesUtils.MAP_KEY_OF_SESSION);
 String strUrl=request.getParameter("url");
 String menuKey=request.getParameter("menuKey"); 
session.setAttribute("menuKey",menuKey);
		//strUrl = strUrl.replaceAll(".jsp",".faces");
		//session.setAttribute("USER_WRITE_FLAG","false");
		//Hashtable hbTemp = (Hashtable)request.getSession().getAttribute("USER_PAGE_CONTROL");
		//Enumeration emu = hbTemp.keys();
		while(emu.hasMoreElements()){
			String pageUrl = (String)emu.nextElement();
			String url = pageUrl;
			if(url.indexOf("?") != -1) {
				url = url.substring(0, pageUrl.indexOf("?"));
			}
			
			if(strUrl.endsWith(url)){
				String strTemp = (String)hbTemp.get(pageUrl);
				if(strTemp!=null && strTemp.toString().trim().equalsIgnoreCase("Y")){
					request.getSession().setAttribute("USER_WRITE_FLAG","true");
					break;
				}else{
					request.getSession().setAttribute("USER_WRITE_FLAG","false");
				}
			}
		}
 
 

 
 response.sendRedirect(strUrl);

 %>--%>
