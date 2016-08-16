<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<%
		out.clear(); 
		out = pageContext.pushBody();
		ServletOutputStream outStream = response.getOutputStream();
		java.io.BufferedOutputStream bos=new java.io.BufferedOutputStream(outStream);
		//response.setCharacterEncoding("utf-8");
		response.setContentType("application/x-download");
		String filepath=request.getParameter("filepath");
		File file=new File(filepath);
		response.setHeader("Content-Disposition","attachment;" + "filename=" + file.getName());
		java.io.File srcFile = file;
		java.io.FileInputStream stream = new java.io.FileInputStream(srcFile);
		 int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) 
        {
        	bos.write(buffer, 0, bytesRead);
        }
        
        bos.close();
        outStream.close();
        stream.close(); 

%>