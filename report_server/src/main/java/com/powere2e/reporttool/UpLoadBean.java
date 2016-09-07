package com.powere2e.reporttool;


import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.powere2e.reporttool.config.Config;
import com.powere2e.reporttool.jasperprocessor.Cache;

/**
 * 
 * @author leo
 * @version $Id: UpLoadBean.java,v 1.5 2008/07/29 02:49:11 solomon Exp $
 *
 */
public class UpLoadBean
{
	private String savePath = null;//文件存放路径
	private String fileName = null;
	private String sperator = null;
	private String errMsg = "正常返回";
	private byte[] bt = new byte[4096];//文件流缓冲区
	private int t = -1; //记录从输入流中实际读取的字符数
	//private long fileSize = -1;
	private boolean userDefineFileName = false;
	
	long start = System.currentTimeMillis();
	
	/**
	 * Constructor
	 *
	 */
	
	public UpLoadBean()
	{
		
	}
	
	/**
	 * Upload fiels.
	 * @param request 
	 * @throws Exception
	 */
	public boolean upLoad(HttpServletRequest request) throws Exception
	{
		
		savePath = Config.getReportHome();
		System.out.println("SavePath is ------="+savePath);
		if(savePath == null)
		{
			setErrorMessage("未设置保存上传文件的路径！");
			return false;
		}
		
		//设置Request端字符编码方式
		//request.setCharacterEncoding("GBK");
		request.setCharacterEncoding("utf-8");
		//request.setCharacterEncoding(request.getCharacterEncoding());
		//setCharacterEncoding(request.getCharacterEncoding());
		
        	//初始化输入流
		ServletInputStream in = request.getInputStream();
		
		//循环从读取输入流中读取字节
		t = in.readLine(bt,0,bt.length);
		
		//获取一行的分割标志 
		if(t != -1)
		{
			sperator = new String(bt,0,t);
			sperator = sperator.substring(0,28);
			t = -1;
			System.out.println("The sperator is -------="+sperator);
		}
		
		//取扩展名并设定文件全名
		do
		{
			t = in.readLine(bt,0,bt.length);
			//System.out.println("The t is ------="+t);
			String s = new String(bt, 0, t);
			System.out.println("The 2 s is -------="+s);
			int index = s.indexOf("filename=\"");
			int nameindex;
			System.out.println("The index is -----="+index);
			if(index != -1)
			{
				s = s.substring(index + 10);
				index = s.indexOf("\"");
				s = s.substring(0,index);
				index = s.lastIndexOf(".");
				
				nameindex = s.lastIndexOf("\\");
				fileName = s.substring(nameindex + 1,index);
				if(!userDefineFileName)
				{
					fileName = fileName + s.substring(index);// 设定文件全名
				}
				t = -1;
			}
		}while(t != -1);
		System.out.println("The fileName is -------="+fileName);
		
		//如果上载的报表文件cache中已存在，则清楚；
		if(Cache.getInstance().getContainer().containsKey(fileName))
		{
			Cache.getInstance().removeReport(fileName);
		}
			
			
		t = in.readLine(bt,0,bt.length);
		String s = new String(bt,0,t);
		System.out.println("The 3 s is -------="+s);
		
		//判断上传的是否是文件
		int i = s.indexOf("Content-Type:");
		System.out.println("The i is -----="+i);
		if(i == -1)
		{
			setErrorMessage("上传的不是文件");
			return false;
		}
		else
		{
			in.readLine(bt,0,bt.length);//去掉一个空行
			t = -1;
		}
		boolean state = true;
		//long trancsize = 0;
		
        	//初始化一个输出流(到文件)
		File file = new File(savePath,fileName);
		FileOutputStream out = new FileOutputStream(file);
		System.out.println(" 要保存的文件名－－＝"+savePath+"\\"+fileName);
		try
		{
			
			do
			{
				t = in.readLine(bt,0,bt.length);
				s = new String(bt,0,t);
				
				System.out.println("The next s is -----="+s);
				//去掉最后一行
				if(s != null && s.length() > 28 && s.substring(0,28).equals(sperator))
				{
					break;
				}
				else
				{
					out.write(bt,0,t);
					
				}
			
			}while(t !=-1);
			
		}
		catch(Exception e)
		{
			out.close();
			setErrorMessage(e.getMessage());
		}
		finally
		{
			out.close();
			
		}
		
		 long end = System.currentTimeMillis();
		System.out.println("UpLoadBean upLoad runtime================"+(end-start));
			
		
		return state;
	}
	
	/**
	 * Set erro message.
	 * @param errormessage
	 */
	public void setErrorMessage(String errormessage)
	{
		errMsg = errormessage;
	}
	
	/**
	 * Get error message.
	 * @return Error Message on upload.
	 */
	public String getErrorMessage()
	{
		return errMsg;
	}
}
