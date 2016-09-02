/******************************************************************************
 * 版权所有 刘大磊 2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/
package com.delmar.common.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.delmar.common.web.bean.FileUpLoad;
import com.delmar.core.web.bean.UserResource;
import com.google.gson.Gson;

/**
 * @author 刘大磊 2015年4月17日 下午3:45:53
 */
public class FileUploadServlet extends HttpServlet {
	
	
	
	public String getText(String aTextName,HttpServletRequest request) {
		
		UserResource ur=(UserResource)request.getSession().getAttribute("resource");
		String resource=ur.get(aTextName);
		 if(resource==null)
			 resource=aTextName;
		 
	   	  return resource;
		 
		
	}
	

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws javax.servlet.ServletException if an error occurred
	 * @throws java.io.IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		
		out.println("  <script type=\"text/javascript\">");
		String id=FileUpLoad.doFileUpload(getServletContext(), request);
		out.println("    var jsonobj="+id+";");
		
		out.println(" if(jsonobj==0)  {  }");
		out.println("  else if(jsonobj=='-1'){	document.writeln('<h1>"+getText("public.uploadfile.filesizeover",request)+"10M</h1>');");
		out.println("}else {");
		out.println("window.parent.closeFileDialogSelf("+id+")");
		out.println("}  </script>");
		out.println("  <h1>"+getText("public.uploadfile.failure",request)+"</h1>");

		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
