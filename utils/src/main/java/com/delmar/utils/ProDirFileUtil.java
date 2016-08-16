/**
 * <p>
 * @Title: 文件及文件夹操作
 * @Description:
 * @Copyright: 
 * @Company: 
 * @version 
 */

package com.delmar.utils;


import java.io.*;


public class ProDirFileUtil {

	/**
	 * 复制文件，考虑文件不存在，不是文件，目标文件存在情况
	 * 
	 * @param src
	 * @param des
	 * @param overcast 是否覆盖目标
	 * @throws IOException
	 */
	public static void copyFile(String src, String des, boolean overcast)
			throws IOException {
		
		//准备复制的文件
		File file1 = new File(src);
		
		//判断复制路径下是否同名文件
		File file2 = new File(des);

		
		//判断文件是否存在并且是一个文件
		if(file1.exists() && file1.isFile()){
			FileInputStream fis = new FileInputStream(file1);
			FileOutputStream fos = null;
			byte [] bytegroup = new byte[512];
			int temp = 0;
			
			//如果选择覆盖并且复制路径上不存在同名文件
			if(file2.exists() == false || file2.isFile() == false){
					fos = new FileOutputStream(des);
					while((temp = fis.read(bytegroup)) != -1 ){
						fos.write(bytegroup,0,temp);
					}
			}else if(overcast){
					fos = new FileOutputStream(des);
					while((temp = fis.read(bytegroup)) != -1 ){
						fos.write(bytegroup,0,temp);
					}
			}
			//关闭文件流
				fis.close();
			if(fos != null)
				fos.close();
			
		}
	}

	/**
	 * 复制文件夹，考虑文件夹不存在
	 * 
	 * @param srcPath
	 * @param desPath
	 * @param overcast 是否覆盖目标
	 * @throws IOException
	 */
	public static void copyDir(String srcPath, String desPath, 
			boolean overcast) throws IOException {
		
		//准备复制的目录
		File file1 = new File(srcPath);
		
		//复制到的位置
		File file2 = new File(desPath);
		
		//判断复制目录是否存在
		if (file1.exists()){
			
			//判断复制到目录是否存在如果不存在就新建
			if(file2.exists() == false || overcast){
				(new File(desPath)).mkdirs();
			
			//取得file1目录下所有子文件和文件夹
			String [] file = file1.list();
			File temp = null;
			
			//取得file1目录下所有文件和文件夹
			for(int i = 0; i < file.length; i++){
				if(srcPath.endsWith(File.separator)){
					temp = new File(srcPath + file[i]);
				}else{
					temp = new File(srcPath + File.separator + file[i]);
				}
				//如果是文件直接复制
				if(temp.isFile()){
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(
							desPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
			          int len;
			          while ( (len = input.read(b)) != -1) {
			            output.write(b, 0, len);
			          }
			          output.flush();
			          output.close();
			          input.close();
				}
				//如果是文件夹递归调用自己
				if (temp.isDirectory()) { //如果是子文件夹
					copyDir(srcPath + "/" + file[i], 
							desPath + "/" + file[i],overcast);
				}
			}
			
			}
		}
		
	}

	/**
	 * 移动文件
	 * 
	 * @param src
	 * @param des
	 * @param overcast 是否覆盖目标 
	 * @throws IOException
	 */
	public static void moveFile(String src, String des, 
			boolean overcast) throws IOException {
//		准备复制的文件
		File file1 = new File(src);
		
		//判断复制路径下是否同名文件
		File file2 = new File(des);

		
		//判断文件是否存在并且是一个文件
		if(file1.exists() && file1.isFile()){
			FileInputStream fis = new FileInputStream(file1);
			FileOutputStream fos = null;
			byte [] bytegroup = new byte[512];
			int temp = 0;
			
			//如果选择覆盖并且复制路径上不存在同名文件
			if(file2.exists() == false || file2.isFile() == false){
					fos = new FileOutputStream(des);
					while((temp = fis.read(bytegroup)) != -1 ){
						fos.write(bytegroup,0,temp);
					}
					//删除旧文件
					file1.deleteOnExit();
			}else if(overcast){
					fos = new FileOutputStream(des);
					while((temp = fis.read(bytegroup)) != -1 ){
						fos.write(bytegroup,0,temp);
					}
					//删除旧文件
					file1.deleteOnExit();
			}
			//关闭文件流
				fis.close();
			if(fos != null)
				fos.close();
	}

	}

	/**
	 * 移动文件夹
	 * 
	 * @param srcDir
	 * @param desDir
	 * @param overcast 是否覆盖目标 
	 * @throws IOException
	 */
	public static void moveDir(String srcDir, String desDir, boolean overcast) 
	throws IOException {
		
		//准备复制的目录
		File file1 = new File(srcDir);
		
		//复制到的位置
		File file2 = new File(desDir);
		
		//判断复制目录是否存在
		if (file1.exists()){
			
			//判断复制到目录是否存在如果不存在就新建
			if(file2.exists() == false || overcast){
				(new File(desDir)).mkdirs();
			
			//取得file1目录下所有子文件和文件夹
			String [] file = file1.list();
			File [] list = file1.listFiles();
			File temp = null;
			
			//取得file1目录下所有文件和文件夹
			for(int i = 0; i < file.length; i++){
				if(srcDir.endsWith(File.separator)){
					temp = new File(srcDir + file[i]);
				}else{
					temp = new File(srcDir + File.separator + file[i]);
				}
				//如果是文件直接复制
				if(temp.isFile()){
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(
							desDir + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
			          int len;
			          while ( (len = input.read(b)) != -1) {
			            output.write(b, 0, len);
			          }
			          output.flush();
			          output.close();
			          input.close();
			          list[i].delete();
				}
				//如果是文件夹递归调用自己
				if (temp.isDirectory()) { //如果是子文件夹
					File [] list1 = temp.listFiles();
					if(list1.length == 0){
						list[i].delete();
					}else{
					moveDir(srcDir + "/" + file[i], 
							desDir + "/" + file[i],overcast);
					}
				}
				
			}
				file1.delete();
				
			}
		}
		

	}
	
	 /** 
	 * @Title:        getBytes 
	 * @Description:  TODO
	 * @param:        @param filePath
	 * @param:        @return    
	 * @return:       byte[]    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月10日 下午4:59:42 
	 */
	public static byte[] getBytes(String filePath){  
	        byte[] buffer = null;  
	        try {  
	            File file = new File(filePath);  
	            FileInputStream fis = new FileInputStream(file);  
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            byte[] b = new byte[1000];  
	            int n;  
	            while ((n = fis.read(b)) != -1) {  
	                bos.write(b, 0, n);  
	            }  
	            fis.close();  
	            bos.close();  
	            buffer = bos.toByteArray();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer;  
	    }  
	 
	 
	  /** 
	 * @Title:        getFile 
	 * @Description:  TODO
	 * @param:        @param bfile
	 * @param:        @param filePath
	 * @param:        @param fileName    
	 * @return:       void    
	 * @throws 
	 * @author        Charles Luo
	 * @Date          2015年6月10日 下午4:59:35 
	 */
	public static void getFile(byte[] bfile, String filePath,String fileName) {  
		
		   if (bfile==null) 
			   return;
		
		   if (bfile.length==0)
		    	return;
		    
		    
	        BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;  
	        File file = null;  
	        try {  
	            File dir = new File(filePath);  
	            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
	                dir.mkdirs();  
	            }  
	            file = new File(filePath+"\\"+fileName);  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(bfile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (bos != null) {  
	                try {  
	                    bos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	            if (fos != null) {  
	                try {  
	                    fos.close();  
	                } catch (IOException e1) {  
	                    e1.printStackTrace();  
	                }  
	            }  
	        }  
	    }  
	  	 



}
