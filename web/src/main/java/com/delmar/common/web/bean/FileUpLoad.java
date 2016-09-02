package com.delmar.common.web.bean;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.delmar.common.model.DelmarFile;
import com.delmar.common.model.FileRelation;
import com.delmar.common.model.FileSetting;
import com.delmar.common.service.DelmarFileService;
import com.delmar.common.service.FileRelationService;
import com.delmar.core.web.bean.EaContext;
import com.delmar.core.web.util.FacesUtils;
import com.google.gson.Gson;
/******************************************************************************
 * 刘大磊  2013-07-01												  *
 *	作者：刘大磊								                                              *
 * 电话：13336390671                                                               * 
 * email:ldlqdsd@126.com						                              *
 *****************************************************************************/




/**
 * @author 刘大磊 2015年4月16日 上午11:07:47
 */
public class FileUpLoad {
	private static DelmarFileService delmarFileService=EaContext.getBean("delmarFileService", DelmarFileService.class);
	private static FileRelationService fileRelationService=EaContext.getBean("fileRelationService", FileRelationService.class);
	public FileUpLoad() {
		// TODO Auto-generated constructor stub
	}
	private static final Logger logger=Logger.getLogger(FileUpLoad.class);
public static String doFileUpload(ServletContext application,HttpServletRequest request)

{
	FileSetting fileSetting=(FileSetting)FacesUtils.getValueInHashtableOfSession("fileSetting", request.getSession());
	String fileAbstract=(String)request.getSession().getAttribute("fileAbstract");
	String fileKeyword=(String)request.getSession().getAttribute("fileKeyword");
	String tablename=(String)request.getSession().getAttribute("tablename");
	String tableids=(String)request.getSession().getAttribute("tableid");
	Integer tableid=Integer.parseInt(tableids);
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);

     if (isMultipart) {

         DiskFileItemFactory factory = new DiskFileItemFactory();

         File tempDir = new File("E:\\temp");

         if (!tempDir.exists()) {

             tempDir.mkdirs();

         }

         factory.setRepository(tempDir);

         factory.setSizeThreshold(10 * 1024);

         ServletFileUpload fu = new ServletFileUpload(factory);

         fu.setSizeMax(1000000000);
        
         fu.setHeaderEncoding("UTF-8");
         try {

             List items = fu.parseRequest(request);

             Iterator iter = items.iterator();

             while (iter.hasNext()) {

                 FileItem item = (FileItem) iter.next();
                 if(item.getSize()>1024*1024*10)
                 {
                	 return "-1";
                 }
                 String fileName = item.getName();
                 String fileName2=fileName.substring(fileName.lastIndexOf("\\")+1);
                 if (fileName != null && !fileName.trim().equals("")) {

                     if (item.isFormField()) {

                         String name = item.getFieldName();
                        
                         String value = new String(item.getString()

                                 .getBytes("UTF-8"), "UTF-8");


                     } else {

                         // save to disk
                         String fileDir =fileSetting.getRootDirectory(); //application.getRealPath("uploadfile/file");
                         
                         //System.out.println(request.getCharacterEncoding());

                         File saveDir = new File(fileDir);

                         if (!saveDir.exists()) {

                             saveDir.mkdirs();

                         }

                         if (fileName != null && !fileName.trim().equals("")) {

                             int i = fileName.lastIndexOf(".");

                             fileName = fileName.substring(i);
                            // String filepre=(String)request.getSession().getAttribute("prefix");
                             
                             File saveFile =null;
                             if("N".equals(request.getSession().getAttribute("rename")))
                             {
                            	 saveFile = new File(saveDir, fileName2);
                             }
                             else
                             {
                            	 saveFile = new File(saveDir, System.currentTimeMillis()+fileName);
                             }
                             

                             if (!saveFile.exists()) {

                                 saveFile.createNewFile();

                             }
                             Date now=new Date();
                             item.write(saveFile);
                             DelmarFile df=new DelmarFile();
                             df.setFileCreated(now);
                             df.setFileUpdated(now);
                             df.setFileAbstract(fileAbstract);
                             df.setFileKeyword(fileKeyword);
                             df.setCreated(now);
                             df.setFileType(item.getContentType());
                             df.setFileMode(0);
                             df.setFileIshidden(0);
                             df.setFileIsreadonly(0);
                             df.setFilename(fileName2);
                             df.setCommonFileSettingId(fileSetting.getId());
                             df.setExtension(item.getName().substring(item.getName().indexOf(".")));
                             df.setFileSize(item.getSize());
                             df.setPath(saveFile.getPath());
                             Gson gson=new Gson();
                             //System.out.println("---------------------------------------------------------------------->"+gson.toJson(df));
                             delmarFileService.save(df);
                            
                             FileRelation fr=new FileRelation();
                             fr.setCreated(now);
                             fr.setFileId(df.getId());
                             fr.setTableId(tableid);
                             fr.setTableName(tablename);
                             
                             fileRelationService.save(fr);
                             fr.setDelmarFile(df);

                             
                             return tableids;
                         }

                       

                     }

                 }

             }

         } catch (Exception e) {

             
        	 logger.error("上传文件错误！",e);
             e.printStackTrace();

         }

     }
     return "0";
 }

}